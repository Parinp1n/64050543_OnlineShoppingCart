/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Shoppingcart;
import model.ShoppingcartPK;
import model.ShoppingcartTable;
import model.ProductsTable;

/**
 *
 * @author pinpin
 */
public class AddShoppingCartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    
    HttpSession session = request.getSession();
    // Synchronize on the session to ensure thread safety
    synchronized (session) {
        Enumeration<String> paramList = request.getParameterNames();
        int cartID = (ShoppingcartTable.findLastestCartID() + 1);
        
        while (paramList.hasMoreElements()) {
            String singleParam = paramList.nextElement();
            int id = 0;
            int qt = 0;
            
            if (singleParam.startsWith("qt_")) {
                id = Integer.parseInt(singleParam.replace("qt_", ""));
                
                if (!(request.getParameter("qt_" + id).equals(""))) {
                    qt = Integer.parseInt(request.getParameter("qt_" + id));
                    
                    // Use the session to store the shopping cart data
                    List<Shoppingcart> cartList = (List<Shoppingcart>) session.getAttribute("cartList");
                    
                    if (cartList == null) {
                        cartList = new ArrayList<>();
                    }
                    
                    Shoppingcart cart = new Shoppingcart();
                    ShoppingcartPK cartpk = new ShoppingcartPK();
                    cartpk.setCartId(cartID);
                    cartpk.setMovieId(id);
                    cart.setShoppingcartPK(cartpk);
                    cart.setQuantity(qt);
                    cartList.add(cart);
                    
                    // Update the session attribute
                    session.setAttribute("cartList", cartList);
                    
                }
            }
        }
    }

    request.getRequestDispatcher("add_result.jsp").forward(request, response);
}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        // Handle POST requests (e.g., adding a shopping cart)
//        Shoppingcart cart = new Shoppingcart();
//        ShoppingcartPK cartpk = new ShoppingcartPK();
//        int CartID = (ShoppingcartTable.findLastestCartID())+1;
//        cartpk.setCartId(CartID);
//        cartpk.setMovieId(Integer.parseInt(request.getParameter("movieId")));
//        cart.setShoppingcartPK(cartpk);
//        // Set other properties of the shopping cart as needed
//        cart.setQuantity(Integer.valueOf(request.getParameter("quantity")));
//
//        int rowInserted = ShoppingcartTable.insertShoppingcart(cart);
//        request.setAttribute("rowInserted", rowInserted);
//        request.getRequestDispatcher("app_result.jsp").forward(request, response);
          processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

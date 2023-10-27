<%-- 
    Document   : add_result
    Created on : Nov 22, 2020, 4:00:53 PM
    Author     : sarun
--%>
<%@page import="model.ShoppingcartTable"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.ProductsTable"%>
<%@page import="model.Products"%>
<%@page import="model.Shoppingcart"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Products</title>
    </head>
    <jsp:useBean id="pd" class="model.Products" scope="request"/>
    <jsp:useBean id="ct" class="model.Shoppingcart" scope="request"/>
     <%
                       
            List<Products> pdList = ProductsTable.findAllProducts();
            Iterator<Products> itr = pdList.iterator();
            List<Shoppingcart> ctList = (List<Shoppingcart>)request.getSession().getAttribute("cartList");
            Iterator<Shoppingcart> itr2 = ctList.iterator();
            
     %>
    <body>
        <center>
        <h1>Shopping Cart</h1>
        <table border="1">
          <tr>
            <th>DVD Names</th>
            <th>Rate</th>
            <th>Year</th>
            <th>Price/Unit</th>
            <th>Quantity</th>
            <th>Price</th>
          </tr>
          <%
                int sum = 0;
                while(itr2.hasNext()) {
                   pd = itr.next();
                   ct = itr2.next();
                   out.println("<tr>");
                   out.println("<td>" + pd.getMovie() + "</td>");
                   out.println("<td> "+ pd.getRating() + "</td>");
                   out.println("<td> "+ pd.getYearcreate() + "</td>");
                   out.println("<td> "+ pd.getPrice() + "</td>");
                   out.println("<td>"+ct.getQuantity()+"</td>");
                   out.println("<td>"+(ct.getQuantity()*pd.getPrice())+"</td>");
                   sum += (ct.getQuantity() * pd.getPrice());
                   out.println("<tr>");
                }
                out.println("<tr>");
                out.println("<td colspan='5'><b>Total: </b></td>");
                out.println("<td><b>" + sum + "</b></td>");
                out.println("</tr>");

          %>
    </table>
    <form name='Check out' action='CheckOut'>
        <input type="hidden" name='totalSum' value='<%= sum %>'>
        <button type="submit">Check out</button>
    </form>
 </center>
    </body>
</html>

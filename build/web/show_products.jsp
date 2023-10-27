<%-- 
    Document   : showemployee
    Created on : Nov 22, 2020, 3:13:07 PM
    Author     : sarun
--%>

<%@page import="java.util.Iterator"%>
<%@page import="model.ProductsTable"%>
<%@page import="model.Products"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Products</title>
    </head>
    <jsp:useBean id="pd" class="model.Products" scope="request"/>
     <%
                       
            List<Products> pdList = ProductsTable.findAllProducts();
            Iterator<Products> itr = pdList.iterator();
            
     %>
    <body>
        <center>
        <h1>DVD Catalog</h1>
        <form name='addToCart' action='AddShoppingCartController' method='POST'>
        <table border="1">
          <tr>
            <th>DVD Names</th>
            <th>Rate</th>
            <th>Year</th>
            <th>Price</th>
            <th>Quantity</th>
          </tr>
          <%
              
                while(itr.hasNext()) {
                   pd = itr.next();
                   out.println("<tr>");
                   out.println("<td><input type='checkbox' name= movie_"+pd.getId()+ "> " + pd.getMovie() + "</td>");
                   //out.println("<td> "+ pd.getMovie() + "</td>");
                   out.println("<td> "+ pd.getRating() + "</td>");
                   out.println("<td> "+ pd.getYearcreate() + "</td>");
                   out.println("<td> "+ pd.getPrice() + "</td>");
                   out.println("<td><input type='text' name= qt_"+pd.getId()+" size='8'></td>");
                   out.println("<tr>");
                }
          %>
    </table>
    <button type="submit">Add to Cart</button>
    </form>
 </center>
    </body>
</html>

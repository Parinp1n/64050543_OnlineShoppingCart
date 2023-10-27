<%-- 
    Document   : Summary
    Created on : 27 ต.ค. 2566, 22:04:49
    Author     : pinpin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmation Page</title>
    </head>
    <body>
        <h1>Your order is confirmed!</h1>
        <h1>The Total Amount is <%= request.getParameter("totalSum") %></h1>
    </body>
</html>

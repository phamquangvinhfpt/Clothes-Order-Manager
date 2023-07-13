<%@page import="sample.user.UserDTO"%>
<%@page import="sample.shopping.Order"%>
<%@page import="java.util.List"%>
<%@page import="sample.shopping.OrderedDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bill Management | Clothes Store</title>

        <style>
            body {
                font-family: Arial, sans-serif;
            }

            .text-center {
                text-align: center;
            }

            .mt-5 {
                margin-top: 5em;
            }

            .border-success {
                border-color: #28a745;
            }

            .pt-3 {
                padding-top: 3em;
            }

            .input-group {
                display: flex;
                justify-content: center;
                align-items: center;
            }

            .form-control {
                width: 300px;
                margin-right: 10px;
            }

            .input-group-append {
                margin-left: 10px;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }

            th, td {
                border: 1px solid black;
                padding: 5px;
                text-align: left;
            }

            th {
                background-color: #f2f2f2;
                font-weight: bold;
            }

            form {
                display: inline;
                margin-bottom: 0;
            }

            h1 {
                color: red;
                margin-top: 20px;
            }

            button {
                background-color: #FCF89a;
                color: white;
                padding: 10px 20px;
                border: none;
                cursor: pointer;
                font-size: 16px;
                text-decoration: none;
                margin-bottom: 10px;
            }
        </style>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !"AD".equals(loginUser.getRoleID())) {
                response.sendRedirect("index.jsp");
                return;
            }

        %>

        <form action="MainController">
            <input type="submit" name="action" value="Logout" />
        </form>

    <center> 
        <button> <a href="home.jsp" style="text-decoration: none; color: #08609a;">Home</a> </button>
        <button> <a href="shopping.jsp" style="text-decoration: none; color: #08609a;">Back To Shop</a> </button>
    </center>

    <table border="1">
        <thead>
            <tr>
                <th>No</th>
                <th>Order ID</th>
                <th>User ID</th>

                <th>Date</th>
                <th>Total</th>

                <th>Delete</th>
                <th>Detail</th>
            </tr>
        </thead>
        <tbody>
            <%                OrderedDAO dao = new OrderedDAO();
                List<Order> list = dao.getListOrder();
                int count = 1;
                for (Order order : list) {

            %>
        <form action="MainController">
            <tr>
                <td><%= count++%></td>
                <td><%= order.getOrderID()%></td>
                <td>
                    <%= order.getUserID()%>
                </td>

                <td>
                    <%= order.getDate()%>
                </td>

                <td>
                    <%= order.getTotal()%>
                </td>

                <td>
                    <a href="MainController?action=Delete&orderID=<%= order.getOrderID()%>">Delete</a>
                </td>

                <td>
                    <a href="MainController?action=Bill&orderID=<%= order.getOrderID()%>">Detail</a>
                </td>
            </tr>


        </form>

        <%
            }
        %>

    </tbody>
</table>

<%
    String error_message = (String) request.getAttribute("ERROR");
    if (error_message == null) {
        error_message = "";
    }
%>
<h1><%= error_message%></h1>
</body>
</html>

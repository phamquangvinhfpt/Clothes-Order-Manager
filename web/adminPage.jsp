
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page | Clothes Store</title>
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

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
            String search = (String) request.getParameter("search");
            if (search == null) {
                search = "";
            }

        %>

        <div class="col-lg-12 text-center mt-5">
            <h2>Hello Admin: <%= loginUser.getFullName()%></h2>
        </div>

        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="home.jsp">Home</a>
                <a class="navbar-brand" href="shopping.jsp">Manage Shop</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
                    <c:if test="${sessionScope.AD != null}">

                        <a class="navbar-brand" href="addProduct.jsp">Add Product</a>

                    </c:if>  
                    <form class="nav-item" action="MainController">
                        <input style="background: #666; color: white;" type="submit" name="action" value="Logout" />
                    </form>

                </div>
            </div>
        </nav>


    <form action="MainController">
        <div class="col-md-4 offset-md-4 mt-5 border border-success pt-3">
            <div class="input-group mb-3">
                <input type="text" class="form-control" name="search" value="<%= search%>" placeholder="Search users......" aria-label="Recipient's username">
                <input type="hidden" name="roleID" value="<%= loginUser.getRoleID()%>" />
                <div class="input-group-append">
                    <input class="input-group-text" type="submit" name="action" value="Search" />
                </div>
            </div>
        </div>

    </form>

    <%
        List<UserDTO> list = (List<UserDTO>) request.getAttribute("LIST_USER");
        if (list != null) {
            if (!list.isEmpty()) {
    %>
    <table border="1">
        <thead>
            <tr>
                <th>No</th>
                <th>User ID</th>
                <th>Full Name</th>

                <th>Phone</th>
                <th>Address</th>
                <th>Email</th>
                <th>Role ID</th>
                <th>Password</th>
                <th>Delete</th>
                <th>Update</th>
            </tr>
        </thead>
        <tbody>
            <%
                int count = 1;
                for (UserDTO user : list) {

            %>
        <form action="MainController">
            <tr>
                <td><%= count++%></td>
                <td><%= user.getUserID()%></td>
                <td>
                    <input type="text" name="fullName" value="<%= user.getFullName()%>" />
                </td>

                <td>
                    <input type="text" name="phone" value="<%= user.getPhone()%>" />
                </td>

                <td>
                    <input type="text" name="address" value="<%= user.getAddress()%>" />
                </td>

                <td>
                    <input type="text" name="email" value="<%= user.getEmail()%>" />
                </td>

                <td>
                    <input type="text" name="roleID" value="<%= user.getRoleID()%>" />
                </td>

                <td><input type="password" name="password" value="<%= user.getPassword()%>"/></td>

                <td>
                    <a href="MainController?action=Delete&userID=<%= user.getUserID()%>&search=<%= search%>">Delete</a>
                </td>

                <td>
                    <input type="submit" name="action" value="Update" />
                    <input type="hidden" name="userID" value="<%= user.getUserID()%>" />
                    <input type="hidden" name="search" value="<%= search%>" />
                </td>
            </tr>


        </form>
        <% if (loginUser.getFullName().equals(user.getFullName())) {
            } %>
        <%
            }
        %>

    </tbody>
</table>

<%                }
    String error_message = (String) request.getAttribute("ERROR");
    if (error_message == null) {
        error_message = "";
    }
%>
<h1><%= error_message%></h1>
<%
    }
%>

</body>
</html>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page | Clothes Store</title>

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


        <form action="MainController">
            <input type="submit" name="action" value="Logout" />
        </form>
        <a href="addProduct.jsp">Create Product</a>

    <center> 
        <button> <a href="home.jsp" style="text-decoration: none; color: #08609a;">Home</a> </button>
    </center>


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

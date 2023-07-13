<%@page import="java.util.List"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page | Clothes Store</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !"US".equals(loginUser.getRoleID())) {
                response.sendRedirect("login.jsp");
                return;
            }
            String search = (String) request.getParameter("search");
            if (search == null) {
                search = "";
            }

        %>

        <div class="col-lg-12 text-center mt-5">
            <h2>Hello User:  
                <%                    
                    if (loginUser.getFullName() != null) {

                %>
                <%= loginUser.getFullName()%>

                <%
                    } else if (loginUser.getLastName() != null && loginUser.getFirstName() != null) {
                %>
                <%= loginUser.getFirstName()%> <%= loginUser.getLastName()%>
                <% }%>
            </h2>
        </div>


        <form action="MainController">
            <input type="submit" name="action" value="Logout" />
        </form>

    <center> 
        <button> <a href="home.jsp" style="text-decoration: none; color: #08609a;">Home</a> </button>
        <button><a href="shopping.jsp" style="text-decoration: none; color: #08609a;">SHOP NOW</a></button>
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
        List<UserDTO> list = (List<UserDTO>) request.getAttribute("LIST_US");
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
            </tr>
        </thead>
        <tbody>
            <%
                int count = 1;
                for (UserDTO user : list) {
            %>

            <tr>
                <td><%= count++%></td>
                <td><%= user.getUserID()%></td>
                <td><%= user.getFullName()%></td>

                <td><%= user.getPhone()%></td>

                <td><%= user.getAddress()%></td>

                <td><%= user.getEmail()%></td>

                <td><%= user.getRoleID()%></td>

                <td><%= user.getPassword()%></td>
            </tr>

            <%                    }
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
    <% }
    %>
</body>
</html>

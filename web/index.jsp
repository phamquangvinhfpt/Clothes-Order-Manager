<%-- 
    Document   : index.jsp
    Created on : Jul 11, 2023, 8:36:15 PM
    Author     : Admin
--%>

<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./css/style.css">
    </head>
    <%
        UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
        if(loginUser!=null) {
            if(loginUser.getRoleID().equals("AD")){
                response.sendRedirect("adminPage.jsp");
            } else{
                response.sendRedirect("userPage.jsp");
            }
        }
    %>
    <body>
        <!-- partial:index.partial.html -->
        <div class="login-page">
            <div class="form">
                <form class="register-form">
                    <input type="text" placeholder="name"/>
                    <input type="password" placeholder="password"/>
                    <input type="text" placeholder="email address"/>
                    <button>create</button>
                    <p class="message">Already registered? <a href="#">Sign In</a></p>
                </form>
                <form class="login-form" action="MainController" method="POST" id="form">
                    <input type="text" name="userID" id="username" placeholder="Username" />
                    <input type="password" name="password" id="password" placeholder="Password" />
                    <p id="error" style="color: red;">${ERROR}</p>
                    <input name="action" value="Login" hidden=""/>
                    <input id="log" type="submit" value="Login"/>
                    <p class="message">Not registered? <a href="addUser.jsp">Create an account</a></p>
                </form>
            </div>
        </div>
        <!-- partial -->
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script><script  src="./js/script.js"></script>

    </body>
</html>

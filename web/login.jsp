<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/login.css" />
        <title>Login Page | Clothes Store</title>
    </head>
    <body>

        <div class="container">
            <h1>Login Page</h1>
            <form action="MainController" method="POST" id="form">

                <div class="form-control">
                    <input type="text" name="userID" id="username" placeholder="Username" />
                    <span></span>
                    <small></small>
                </div>

                <div class="form-control">
                    <input type="password" name="password" id="password" placeholder="Password" />
                    <span></span>
                    <small></small>
                </div>
                <p id="error" style="color: red;">${ERROR}</p>
                <div class="g-recaptcha" data-sitekey="6LfF5XghAAAAAIA4Dz7k4jWLGvDzhKd2zj7GMeDV"></div>
                <input name="action" value="Login" hidden=""/>
                <input type="submit" value="Login"/>

                <div class="signup_link">
                    <input type="reset" value="Reset">
                </div>                

            </form>
            <a href="adminPage.jsp" style="text-decoration: none; text-align: center; color: burlywood;">Forgot password (Login admin role to update information!)</a>

            <script src="https://www.google.com/recaptcha/api.js" async defer></script>
            <script>
                window.onload = function () {
                    let isValid = false;
                    const form = document.getElementById("form");
                    const error = document.getElementById("error");

                    form.addEventListener("submit", function (event) {
                        event.preventDefault();
                        const response = grecaptcha.getResponse();
                        if (response) {
                            form.submit();
                        } else {
                            error.innerHTML = "Please check the recaptcha";
                        }
                    });
                };
            </script>

            <!-- Login by google account -->
            <div class ="col-lg-6 pb-3">
                <div class = "h5 text-center mt-3" style="top: 20px; width: 150px; height: 22px; margin:0 145px;">
                    Google Account
                </div> 

                <a class = "position-absolute custom-horizontal-center" style ="top: 30%" href="https://accounts.google.com/o/oauth2/auth?scope=email profile&redirect_uri=http://localhost:8084/Watch-Store/GoogleSignInServlet&response_type=code
                   &client_id=250087058378-ti2oiavvu8p4h6prbf7krco28tobr6f4.apps.googleusercontent.com&approval_prompt=force">
                    <div class = "horizontal-center hover-white-image" style = "top: 80px; width: 80px; height: 80px; margin:0 180px;"> 
                        <img style ="width: 50%; height: 50%;" src = "img/GoogleIcon.png"> 
                    </div>
                </a>

            </div>
        </div>

    <center>
        <div>
            <a href="addUser.jsp" style="text-decoration: none; color: burlywood;">Sign Up</a>
        </div>

        <br><a href="home.jsp" style="text-decoration: none; color: burlywood;">HOME</a>
    </center>


</body>
</html>
<%@page import="sample.user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up | Clothes Store</title>    
        <style>
            input[type=text] {
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }

            input[type=submit] {
                width: 100%;
                background-color: #4CAF50;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            input[type=submit]:hover {
                background-color: #45a049;
            }

            div {
                border-radius: 5px;
                background-color: #f2f2f2;
                padding: 20px;
            }
        </style>
    </head>
    <body>

        <%
            UserError userError = (UserError) request.getAttribute("USER_ERROR");
            if (userError == null) {
                userError = new UserError();
            }
        %>
    <center>

        <div>
            <form action="MainController" method="POST">
                <div><h1>Sign Up</h1></div>

                <input name="userID" type="text" class="form-control" minlength=2 maxlength=10 required="" placeholder="User ID..">
                <%= userError.getUserIDError()%></br>

                <input type="text" name="fullName" placeholder="Fullname.." required="">
                <%= userError.getFullNameError()%></br>


                <input type="text" name="roleID" placeholder="RoleID.." required=""/>
                <%= userError.getRoleIDError()%></br>

                <input type="text" name="phone" placeholder="Phone.." required=""/>
                <%= userError.getPhoneError()%></br>

                <input type="text" name="address" placeholder="Address.." required=""/>
                <%= userError.getAddressError()%></br>

                <input type="text" name="email" placeholder="Email.." required=""/>
                <%= userError.getEmailError()%></br>

                <input type="password" name="password" placeholder="Password.." required=""/>

                <input type="password" name="confirm" placeholder="Confirm.." required=""/>
                <%= userError.getConfirmError()%></br>


                <input type="submit" name="action" value="Create" />
                <div>
                    <input type="reset" value="Reset">
                </div>                
                <%= userError.getMessageError()%>
            </form>

        </div>
    </center>

</body>
</html>


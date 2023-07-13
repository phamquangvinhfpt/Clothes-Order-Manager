<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Cart | Clothes Store</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    </head>
    <body>
        <jsp:include page="menu.jsp"></jsp:include>
        <p> Welcome, ${sessionScope.LOGIN_USER.getFullName()}</p>

        <c:if test = "${empty sessionScope.Cart.getCart()}">
            <p> Your Cart is currently empty.</p>
        </c:if>
        <c:if test = "${not empty sessionScope.Cart.getCart()}">
            <form action = "RemoveCartController" method = "GET" id = "DeleteCart"></form>
            <table border="1">
                <thead>
                    <tr>
                        <th>No. </th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Image</th>
                        <th>Category ID</th>
                        <th>Purchase Quantity</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var = "current" items = "${sessionScope.Cart.getCart()}" varStatus = "status">
                        <tr>
                            <td>${status.count}</td>
                            <td>${current.getKey()}</td>
                            <td>
                                <c:forEach var = "details" items = "${sessionScope.CartDetails}" >
                                    <c:if test = "${current.getKey() == details.getId()}"> 
                                        ${details.getName()}
                                    </c:if> 
                                </c:forEach>

                            </td>
                            <td><c:forEach var = "details" items = "${sessionScope.CartDetails}" >
                                    <c:if test = "${current.getKey() == details.getId()}"> 
                                        ${details.getPrice()}
                                    </c:if> 
                                </c:forEach></td>
                            <td><c:forEach var = "details" items = "${sessionScope.CartDetails}" >
                                    <c:if test = "${current.getKey() == details.getId()}"> 
                                        ${details.getQuantity()}
                                    </c:if> 
                                </c:forEach></td>
                            <td><c:forEach var = "details" items = "${sessionScope.CartDetails}" >
                                    <c:if test = "${current.getKey() == details.getId()}"> 

                                        <img src="${details.getImage()}" alt="Watch" width="250" height="350" />
                                    </c:if> 
                                </c:forEach></td>
                            <td><c:forEach var = "details" items = "${sessionScope.CartDetails}" >
                                    <c:if test = "${current.getKey() == details.getId()}"> 
                                        ${details.getName()}
                                    </c:if> 
                                </c:forEach></td>
                            <td>  ${current.getValue()} </td>
                            <td> <input type ="CHECKBOX" name = "DeleteValue" value ="${current.getKey()}" form = "DeleteCart"> </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td> </td>
                        <td> <input type ="SUBMIT" name = "btAction_Cart" value ="Remove" form ="DeleteCart"> </td>
                    </tr>  
                </tbody>
            </table>
            <form action = "MainController" >
                <input type = "SUBMIT" name = "action" value = "Check Out">
                <input type="SUBMIT" name ="action" value ="Buy More">
            </form>
        </c:if>  

        <c:if test = "${requestScope.Success_CheckOut}" >
            <script>
                swal({
                                        title: "Check out!",
                                        text: "Success!",
                                        icon: "success",
                                        button: "OK"
                                    }).then((value) => {
                                        window.location.href = 'bill.jsp';
                                    });
            </script>
        </c:if>
        <c:if test = "${requestScope.Fail_CheckOut}" >
            <h3> You must login first to buy products. </h3>
            <a href="index.jsp">Go to login page</a>
        </c:if>
    </body>
</html>

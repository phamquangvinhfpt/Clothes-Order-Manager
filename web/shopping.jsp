
<%@page import="sample.shopping.Clothes"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="sample.shopping.ClothesDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping | Clothes Store</title>
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            .w3-sidebar a {
                font-family: "Roboto", sans-serif
            }

            body,
            h1,
            h2,
            h3,
            h4,
            h5,
            h6,
            .w3-wide {
                font-family: "Montserrat", sans-serif;
            }
        </style>
    </head>
    <body>
        <jsp:include page="menu.jsp"></jsp:include>
        <c:if test = "${!requestScope.FirstTime}">
            <c:if test = "${empty requestScope.Watch}">
                <br><p>Please search products that you'd like to buy</p>
                <p> Note: Type "all" if you want to display all products.</p>
            </c:if>
            <c:if test = "${requestScope.Success_Purchase}">
                <p style="color: red;"> Purchase successfully. Please check your Cart for more details.</p>
            </c:if>
            <c:if test = "${not empty requestScope.Watch}">
                <%
                    String error = (String) request.getAttribute("ERROR");
                    if (error == null) {
                        error = "";
                    }
                %>
                <p style="color: red; ">
                    <%= error%>
                </p>
                <p id="error" style="color: red;">${NotEnoughQuantity}</p>

                <form action = "AddController" method = "GET" id = "Add" > </form> 
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
                                <c:if test="${sessionScope.AD != null}">
                                <th>Delete</th>
                                </c:if>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var = "Watch" items = "${requestScope.Watch}" varStatus = "status">
                            <tr>
                                <td>${status.count}</td>
                                <td>${Watch.getId()}</td>
                                <td>${Watch.getName()}</td>
                                <td>${Watch.getPrice()}</td>
                                <td>${Watch.getQuantity()}</td>
                                <td><img src="${Watch.getImage()}" alt="Watch" width="250" height="350" /></td>
                                <td>${Watch.getCategoryName()}</td>
                                <td>
                                    <input type = "number" min="1" max="${Watch.getQuantity()}" name = "${Watch.getId()}_Quantity" value = "${requestScope.QuantityValues.get(Watch)}" form = "Add">    
                                </td>
                                <c:if test="${sessionScope.AD != null}">
                                    <td>
                                        <a href="MainController?action=RemoveClothes&id=${Watch.getId()}&search=${requestScope.SearchValue}">Delete</a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>
                        <input type = "HIDDEN" name = "SearchValue" value = "${requestScope.SearchValue}" form = "Add">
                        <input type = "SUBMIT" name = "watchStore" value = "Purchase" form = "Add">    
                    </td>
                </tbody>
            </table>
        </c:if>
    </c:if>
    <%
        String message = (String) request.getAttribute("MESSAGE");
        if (message == null) {
            message = "";
        }

    %>
    <p style="color: red; ">
        <%= message%>
    </p>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>

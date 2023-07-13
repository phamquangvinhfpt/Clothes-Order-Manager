<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--begin of menu-->
<script>
    function showResult(str) {
        if (str.length == 0) {
            document.getElementById("livesearch").innerHTML = "";
            document.getElementById("livesearch").style.border = "0px";
            return;
        }
        if (window.XMLHttpRequest) {
            // code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp = new XMLHttpRequest();
        } else {  // code for IE6, IE5
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("livesearch").innerHTML = this.responseText;
                document.getElementById("livesearch").style.border = "1px solid #A5ACB2";
            }
        }
        xmlhttp.open("GET", "SearchController?search=" + str, true);
        xmlhttp.send();
    }
</script>
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="home.jsp">Clothes</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
            <ul class="navbar-nav m-auto">
                <c:if test="${sessionScope.AD == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="adminPage.jsp">Manager Account</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.AD != null}">
                    <li class="nav-item">
                        <a class="nav-link" href="addProduct.jsp">Add Product</a>
                    </li>
                </c:if>    

                <c:if test="${sessionScope.LOGIN_USER != null}" >
                    <li class="nav-item">
                        <form class="nav-item" action="MainController">
                            <input style="background: #666; color: white;" type="submit" name="action" value="Logout" />
                        </form> 
                    </li>

                </c:if>
                <c:if test="${sessionScope.LOGIN_USER == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="index.jsp">Login</a>
                    </li>
                </c:if>

            </ul>

            <form action="WatchStoreController" method="post" class="form-inline my-2 my-lg-0">
                <div class="input-group input-group-sm">
                    <input value="${requestScope.SearchValue}" name="txtSearch" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" 
                           placeholder="Search..." onkeyup="showResult(this.value)">
                    <div class="input-group-append">
                        <div class="btn btn-secondary btn-number">
                            <input style="background-color: grey; color: white;" type = "submit" name = "watchStore">

                        </div>

                    </div>
                </div>
                <a class="btn btn-success btn-sm ml-3" href="viewCart.jsp">
                    <i class="fa fa-shopping-cart"></i> Cart
                    <!--<span class="badge badge-light"></span>-->
                </a>
            </form> 

        </div>
    </div>
</nav>



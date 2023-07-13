<%-- 
    Document   : bill
    Created on : Jul 12, 2023, 7:11:14 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />

        <title>Invoice</title>

        <!-- Favicon -->
        <link rel="icon" href="./images/favicon.png" type="image/x-icon" />

        <!-- Invoice styling -->
        <style>
            body {
                font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;
                text-align: center;
                color: #777;
            }

            body h1 {
                font-weight: 300;
                margin-bottom: 0px;
                padding-bottom: 0px;
                color: #000;
            }

            body h3 {
                font-weight: 300;
                margin-top: 10px;
                margin-bottom: 20px;
                font-style: italic;
                color: #555;
            }

            body a {
                color: #06f;
            }

            .invoice-box {
                max-width: 800px;
                margin: auto;
                padding: 30px;
                border: 1px solid #eee;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);
                font-size: 16px;
                line-height: 24px;
                font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;
                color: #555;
            }

            .invoice-box table {
                width: 100%;
                line-height: inherit;
                text-align: left;
                border-collapse: collapse;
            }

            .invoice-box table td {
                padding: 5px;
                vertical-align: top;
            }

            .invoice-box table tr td:nth-child(2) {
                text-align: right;
            }

            .invoice-box table tr.top table td {
                padding-bottom: 20px;
            }

            .invoice-box table tr.top table td.title {
                font-size: 45px;
                line-height: 45px;
                color: #333;
            }

            .invoice-box table tr.information table td {
                padding-bottom: 40px;
            }

            .invoice-box table tr.heading td {
                background: #eee;
                border-bottom: 1px solid #ddd;
                font-weight: bold;
            }

            .invoice-box table tr.details td {
                padding-bottom: 20px;
            }

            .invoice-box table tr.item td {
                border-bottom: 1px solid #eee;
            }

            .invoice-box table tr.item.last td {
                border-bottom: none;
            }

            .invoice-box table tr.total td:nth-child(2) {
                border-top: 2px solid #eee;
                font-weight: bold;
            }

            @media only screen and (max-width: 600px) {
                .invoice-box table tr.top table td {
                    width: 100%;
                    display: block;
                    text-align: center;
                }

                .invoice-box table tr.information table td {
                    width: 100%;
                    display: block;
                    text-align: center;
                }
            }
        </style>
    </head>

    <body>
        <div class="invoice-box">
            <table>
                <tr class="top">
                    <td colspan="2">
                    </td>
                </tr>

                <tr class="information">
                    <td colspan="2">
                        <table>
                            <tr>
                                <td>Name: ${sessionScope.LOGIN_USER.getFullName()}<br/>
                                    Phone: ${sessionScope.LOGIN_USER.getPhone()}<br/>
                                    Address: ${sessionScope.LOGIN_USER.getAddress()}<br/>
                                    Email: ${sessionScope.LOGIN_USER.getEmail()}
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>

                <tr class="heading">
                    <td>Payment Method</td>

                    <td>Check #</td>
                </tr>

                <tr class="details">
                    <td>Check</td>

                    <td>1000</td>
                </tr>

                <tr class="heading">
                    <td>Item</td>
                    <td>Price</td>
                </tr>

                <c:forEach var = "current" items = "${sessionScope.Cart.getCart()}" varStatus = "status">
                        <tr class="item">
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
                                </c:forEach> (x${current.getValue()})</td>
                                                        <%-- td hide calculate money --%>
                            <td style="display: none;"><c:forEach var = "details" items = "${sessionScope.CartDetails}" >
                                    <c:if test = "${current.getKey() == details.getId()}"> 
                                        ${details.getPrice() * current.getValue()}
                                    </c:if> 
                                </c:forEach></td>
                        </tr>
                </c:forEach>
                <tr class="total">
                    <td></td>

                    <td>Total: $
                    <%-- sum all getvalue * price --%>
                    <c:set var = "total" value = "0"/>
                    <c:forEach var = "current" items = "${sessionScope.Cart.getCart()}">
                        <c:forEach var = "details" items = "${sessionScope.CartDetails}" >
                            <c:if test = "${current.getKey() == details.getId()}"> 
                                <c:set var = "total" value = "${total + details.getPrice() * current.getValue()}"/>
                            </c:if> 
                        </c:forEach>
                    </c:forEach>
                    ${total}
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>
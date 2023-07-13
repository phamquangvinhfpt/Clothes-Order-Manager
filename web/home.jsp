
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Clothes Store</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css" />
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="css/home.css" rel="stylesheet" />
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

    <body class="w3-content" style="max-width:1200px">

        <!-- Sidebar/menu -->
        <nav class="w3-sidebar w3-bar-block w3-white w3-collapse w3-top" style="z-index:3;width:250px" id="mySidebar">
            <div class="w3-container w3-display-container w3-padding-16">
                <i onclick="w3_close()" class="fa fa-remove w3-hide-large w3-button w3-display-topright"></i>
                <h3 class="w3-wide"><b>CLOTHES STORE</b></h3>
            </div>
            <div class="w3-padding-64 w3-large w3-text-grey" style="font-weight:bold">
                <a href="#" class="w3-bar-item w3-button">Home</a>

                <a onclick="myAccFunc()" href="javascript:void(0)" class="w3-button w3-block w3-white w3-left-align"
                   id="myBtn">
                    Category <i class="fa fa-caret-down"></i>
                </a>
                <div id="demoAcc" class="w3-bar-block w3-hide w3-padding-large w3-medium">
                    <ul>
                        <li>Casual Wear</li>
                        <li>Formal Wear</li>
                        <li>Business Wear</li>
                    </ul>
                </div>

                <a href="#" class="w3-bar-item w3-button">New</a>

            </div>
            <a href="#footer" class="w3-bar-item w3-button w3-padding">Contact</a>
            <a href="javascript:void(0)" class="w3-bar-item w3-button w3-padding"
               onclick="document.getElementById('newsletter').style.display = 'block'">Newsletter</a>

        </nav>

        <!-- Top menu on small screens -->
        <header class="w3-bar w3-top w3-hide-large w3-black w3-xlarge">
            <div class="w3-bar-item w3-padding-24 w3-wide">LOGO</div>
            <a href="javascript:void(0)" class="w3-bar-item w3-button w3-padding-24 w3-right" onclick="w3_open()"><i
                    class="fa fa-bars"></i></a>
        </header>

        <!-- Overlay effect when opening sidebar on small screens -->
        <div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu"
             id="myOverlay"></div>

        <!-- !PAGE CONTENT! -->
        <div class="w3-main" style="margin-left:250px">

            <!-- Push down content on small screens -->
            <div class="w3-hide-large" style="margin-top:83px"></div>

            <!-- Top header -->
            <header class="w3-container w3-xlarge">
                <p class="w3-left">Home</p>

                <p class="w3-right">

                    <a href="index.jsp"><i class='bx bx-log-in'></i></a>                    
                </p>

            </header>

            <!-- Image header -->
            <div class="w3-display-container w3-container">
                <img src="img/background.jpg"
                     alt="Watch Store" style="width:90%">
                <div class="w3-display-topleft w3-text-white" style="padding:24px 48px">


                    <h1 class="w3-hide-small"></h1>
                    <p><a href="shopping.jsp" class="w3-button w3-black w3-padding-large w3-large">SHOP NOW</a></p>
                </div>
            </div>


            <!-- Top-Selling section-->
            <section class="shop_section layout_padding">
                <div class="container">
                    <div class="heading_container heading_center">
                        <h2>
                            Top-Selling Clothes 
                        </h2>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <div class="box">


                                <div class="item-row">
                                    <a href="shopping.jsp">
                                        <div class="item">
                                            <img src="https://images.pexels.com/photos/2698844/pexels-photo-2698844.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" alt="Item Image">
                                            <h3 class="item-title">T-shirt</h3>
                                            <p class="item-price">490.000</p>
                                            <a href="index.jsp" class="item-add-to-cart">Add to Cart</a>
                                        </div>
                                    </a>

                                    <a href="shopping.jsp">
                                        <div class="item">
                                            <img src="https://images.pexels.com/photos/6046220/pexels-photo-6046220.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" alt="Item Image">
                                            <h3 class="item-title">Dress pants</h3>
                                            <p class="item-price">490.000</p>
                                            <a href="index.jsp" class="item-add-to-cart">Add to Cart</a>
                                        </div>
                                    </a>

                                    <a href="shopping.jsp">
                                        <div class="item">
                                            <img src="https://images.pexels.com/photos/4210866/pexels-photo-4210866.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" alt="Item Image">
                                            <h3 class="item-title">Jeans</h3>
                                            <p class="item-price">790.000</p>
                                            <a href="index.jsp" class="item-add-to-cart">Add to Cart</a>
                                        </div>
                                    </a>

                                    <a href="shopping.jsp">
                                        <div class="item">
                                            <img src="https://images.pexels.com/photos/9594694/pexels-photo-9594694.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" alt="Item Image">
                                            <h3 class="item-title">Sweatshirt</h3>
                                            <p class="item-price">300.000</p>
                                            <a href="index.jsp" class="item-add-to-cart">Add to Cart</a>
                                        </div>
                                    </a>

                                </div>

                                <div class="item-row">
                                    <a href="shopping.jsp">
                                        <div class="item">
                                            <img src="https://images.pexels.com/photos/9563082/pexels-photo-9563082.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" alt="Item Image">
                                            <h3 class="item-title">Evening gown</h3>
                                            <p class="item-price">390.000</p>
                                            <a href="index.jsp" class="item-add-to-cart">Add to Cart</a>
                                        </div>
                                    </a>

                                    <a href="shopping.jsp">
                                        <div class="item">
                                            <img src="https://images.pexels.com/photos/3737918/pexels-photo-3737918.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" alt="Item Image">
                                            <h3 class="item-title">Leggings</h3>
                                            <p class="item-price">300.000</p>
                                            <a href="index.jsp" class="item-add-to-cart">Add to Cart</a>
                                        </div>
                                    </a>
                                    <a href="shopping.jsp">
                                        <div class="item">
                                            <img src="https://images.pexels.com/photos/13530383/pexels-photo-13530383.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" alt="Item Image">
                                            <h3 class="item-title">Formal dress</h3>
                                            <p class="item-price">990.000</p>
                                            <a href="index.jsp" class="item-add-to-cart">Add to Cart</a>
                                        </div>
                                    </a>
                                    <a href="shopping.jsp">
                                        <div class="item">
                                            <img src="https://images.pexels.com/photos/9558265/pexels-photo-9558265.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" alt="Item Image">
                                            <h3 class="item-title">Blouse</h3>
                                            <p class="item-price">990.000</p>
                                            <a href="index.jsp" class="item-add-to-cart">Add to Cart</a>
                                        </div>
                                    </a>
                                </div>

                            </div>
                        </div>

                    </div>
                    <div class="btn-box">
                        <a href="shopping.jsp">
                            View All
                        </a>
                    </div>
                </div>
            </section>


            <!-- Footer -->
            <footer class="w3-padding-64 w3-light-grey w3-small w3-center" id="footer">
                <div class="w3-row-padding">
                    <div class="w3-col s4">
                        <h4>Contact</h4>

                        <form action="#" target="_blank">
                            <p><input class="w3-input w3-border" type="text" placeholder="Name" name="Name" required></p>
                            <p><input class="w3-input w3-border" type="text" placeholder="Email" name="Email" required></p>
                            <p><input class="w3-input w3-border" type="text" placeholder="Subject" name="Subject" required>
                            </p>
                            <p><input class="w3-input w3-border" type="text" placeholder="Message" name="Message" required>
                            </p>
                            <button type="submit" class="w3-button w3-block w3-black">Send</button>
                        </form>
                    </div>

                    <div class="w3-col s4">
                        <h4>About</h4>
                        <p><a href="#">About us</a></p>

                        <p><a href="#">Support</a></p>
                        <p><a href="#">Find store</a></p>
                        <p><a href="#">Shipment</a></p>
                        <p><a href="#">Payment</a></p>

                    </div>

                    <div class="w3-col s4 w3-justify">
                        <h4>Store</h4>
                        <p><i class="fa fa-fw fa-map-marker"></i> Clothes Store</p>
                        <p><i class="fa fa-fw fa-phone"></i> 0885766419</p>
                        <p><i class="fa fa-fw fa-envelope"></i> clothes47store@gmail.com</p>
                        <h4>We accept</h4>
                        <p><i class="fa fa-fw fa-cc-amex"></i> Momo</p>
                        <p><i class="fa fa-fw fa-credit-card"></i> Credit Card</p>
                        <br>
                        <a href="#"><i class="fa fa-facebook-official w3-hover-opacity w3-large"></i></a>
                        <a href="#"><i class="fa fa-instagram w3-hover-opacity w3-large"></i></a>
                        <a href="#"><i class="fa fa-pinterest-p w3-hover-opacity w3-large"></i></a>
                        <a href="#"><i class="fa fa-twitter w3-hover-opacity w3-large"></i></a>

                    </div>
                </div>
            </footer>

            <div class="w3-black w3-center w3-padding-24"></div>

            <!-- End page content -->
        </div>

        <!-- Newsletter Modal -->
        <div id="newsletter" class="w3-modal">
            <div class="w3-modal-content w3-animate-zoom" style="padding:32px">
                <div class="w3-container w3-white w3-center">
                    <i onclick="document.getElementById('newsletter').style.display = 'none'"
                       class="fa fa-remove w3-right w3-button w3-transparent w3-xxlarge"></i>
                    <h2 class="w3-wide">NEWSLETTER</h2>
                    <p>Join our mailing list to receive updates on new and special offers.</p>
                    <p><input class="w3-input w3-border" type="text" placeholder="Enter e-mail"></p>
                    <button type="button" class="w3-button w3-padding-large w3-red w3-margin-bottom"
                            onclick="document.getElementById('newsletter').style.display = 'none'">Subscribe</button>
                </div>
            </div>
        </div>

        <script>
            // Accordion 
            function myAccFunc() {
                var x = document.getElementById("demoAcc");
                if (x.className.indexOf("w3-show") === -1) {
                    x.className += " w3-show";
                } else {
                    x.className = x.className.replace(" w3-show", "");
                }
            }

            document.getElementById("myBtn").click();


            // Open and close sidebar
            function w3_open() {
                document.getElementById("mySidebar").style.display = "block";
                document.getElementById("myOverlay").style.display = "block";
            }

            function w3_close() {
                document.getElementById("mySidebar").style.display = "none";
                document.getElementById("myOverlay").style.display = "none";
            }
        </script>

    </body>

</html>

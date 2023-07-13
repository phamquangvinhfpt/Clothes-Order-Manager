
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product | Clothes Store</title>
        <style>
            input[type=text], select {
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
        <div>
            <form action="MainController" method="POST">
                <div>						
                    <h1 class="modal-title">Add Product</h1>
                </div>

                <label>Product ID</label>
                <input name="productID" type="text" class="form-control" minlength="5" maxlength="5" required placeholder="Product ID..">
                <p style="color: red;">${ERROR}</p>

                <label for="fname">Product Name</label>
                <input type="text" name="name" placeholder="Product name..">

                <label>Price</label>
                <input name="price" type="text" class="form-control" required placeholder="Price..">

                <label>Quantity</label><br>
                <input name="quantity" type="number" min="1" max="20" class="form-control" required><br>

                <br><label>Image</label>
                <input type="text" name="image" placeholder="Images..">

                <label for="country">Category</label>
                <select id="country" name="category">
                    <option value="1">Casual Wear</option>
                    <option value="2">Formal Wear</option>
                    <option value="3">Business Wear</option>
                </select>

                <input type="submit" name="action" value="Add">
                <button style="border: solid 2px;"><a style="text-decoration: none;" href="shopping.jsp" class="btn btn-default" data-dismiss="modal"/>Cancel</button>
                <p style="color: red;">${FFF}</p>
            </form>
        </div>

    </body>
</html>

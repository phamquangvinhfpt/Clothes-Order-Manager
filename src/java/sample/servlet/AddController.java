package sample.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.shopping.Cart;
import sample.shopping.Clothes;
import sample.shopping.ClothesDAO;

public class AddController extends HttpServlet {

    private static final String SUCCESS = "shopping.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SUCCESS;
        try {
            boolean faultWrongNumberFormat = false;
            String searchValue = request.getParameter("SearchValue").trim();
            request.setAttribute("SearchValue", searchValue);

            ArrayList<Clothes> clothes = new ArrayList<>();
            if (!searchValue.equals("")) {
                clothes = (ArrayList<Clothes>) ClothesDAO.getProductByID(searchValue);
            }
            request.setAttribute("Watch", clothes);

            HttpSession session = request.getSession(false);
            Cart cart = null;
            if (session != null) {
                cart = (Cart) session.getAttribute("Cart");
                if (cart == null) {
                    cart = new Cart();
                }

                for (Clothes t : clothes) {
                    String currentPara = request.getParameter(t.getId() + "_Quantity");
                    if (!currentPara.equals("")) {
                        int currentQuantity = 0;
                        try {
                            currentQuantity = Integer.parseInt(currentPara);
                            if (currentQuantity < 1) {
                                throw new NumberFormatException();
                            }
                            // Check if the product's quantity is still enough to add more to cart
                            if (isProductQuantityEnough(request, t.getId(), currentQuantity)) {
                                cart.addToCart(t.getId(), currentQuantity);
                            } else {
                                // Quantity is not enough to add more to cart
                                //session.setAttribute("", true);
                                session.setAttribute("NotEnoughQuantity", "The quantity is not enough to add more!");
                            }
                        } catch (NumberFormatException ex) {
                            faultWrongNumberFormat = true;
                            request.setAttribute("FaultWrongNumberFormat", faultWrongNumberFormat);
                        } catch (SQLException | NamingException | ClassNotFoundException ex) {
                            // Handle any other exceptions that may occur
                            log("Error at AddController: " + ex.toString());
                        }
                    }
                }
            }

            if (!faultWrongNumberFormat) {
                session.setAttribute("Cart", cart);
                ArrayList<Clothes> cartDetails = new ArrayList<>();
                for (String current : cart.getCart().keySet()) {
                    Clothes t = null;
                    try {
                        t = (Clothes) ClothesDAO.getWatch(current);
                    } catch (SQLException ex) {
                        log("Error at AddController: " + ex.toString());
                    }
                    cartDetails.add(t);
                }
                session.setAttribute("CartDetails", cartDetails);
                request.setAttribute("SuccessPurchase", true);
            }

            Map<Clothes, String> quantityValues = new HashMap<>();
            for (Clothes t : clothes) {
                quantityValues.put(t, request.getParameter(t.getId() + "_Quantity"));
            }
            request.setAttribute("QuantityValues", quantityValues);
        } catch (SQLException e) {
            log("Error at AddController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private boolean isProductQuantityEnough(HttpServletRequest request, String productId, int quantity) throws SQLException, NamingException, ClassNotFoundException {
        Clothes product = ClothesDAO.getProduct(productId);
        return product != null && (product.getQuantity() - getProductQuantityInCart(request, productId)) >= quantity;
    }

    private int getProductQuantityInCart(HttpServletRequest request, String productId) {
        HttpSession session = request.getSession(false);
        Cart cart = null;
        if (session != null) {
            cart = (Cart) session.getAttribute("Cart");
            if (cart != null && cart.getCart().containsKey(productId)) {
                return cart.getCart().get(productId);
            }
        }
        return 0;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(AddController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(AddController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

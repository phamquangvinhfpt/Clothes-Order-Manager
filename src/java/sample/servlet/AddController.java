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
            boolean Fault_WrongNumberFormat = false;
            String SearchValue = request.getParameter("SearchValue").trim();
            request.setAttribute("SearchValue", SearchValue);

            ArrayList<Clothes> watch = new ArrayList<>();
            if (!SearchValue.equals("")) {
                watch = (ArrayList<Clothes>) ClothesDAO.getProductByID(SearchValue);
            }
            request.setAttribute("Watch", watch);

            HttpSession session = request.getSession(false);
            Cart cart = null;
            if (session != null) {
                cart = (Cart) session.getAttribute("Cart");
                if (cart == null) {
                    cart = new Cart();
                }

                for (Clothes t : watch) {
                    String currentPara = request.getParameter(t.getId() + "_Quantity");
                    if (!currentPara.equals("")) {
                        int currentQuantity = 0;
                        try {
                            currentQuantity = Integer.parseInt(currentPara);
                            if (currentQuantity < 1) {
                                throw new NumberFormatException();
                            }
                        } catch (NumberFormatException ex) {
                            Fault_WrongNumberFormat = true;
                            request.setAttribute("Fault_WrongNumberFormat", Fault_WrongNumberFormat);
                        }
                        if (currentQuantity != -1) {
                            cart.addToCart(t.getId(), currentQuantity);
                        }
                    }
                }

            }

            if (!Fault_WrongNumberFormat) {
                session.setAttribute("Cart", cart);
                ArrayList<Clothes> cartDetails = new ArrayList<>();
                for (String current : cart.getCart().keySet()) {
                    Clothes t = null;
                    try {
                        t = (Clothes) ClothesDAO.getWatch(current);
                    } catch (SQLException ex) {
                    }
                    cartDetails.add(t);
                }
                session.setAttribute("CartDetails", cartDetails);
                request.setAttribute("Success_Purchase", true);
            }

            Map<Clothes, String> QuantityValues = new HashMap<>();
            for (Clothes t : watch) {
                QuantityValues.put(t, request.getParameter(t.getId() + "_Quantity"));
            }
            request.setAttribute("QuantityValues", QuantityValues);
        } catch (SQLException e) {
            log("Error at AddController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
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

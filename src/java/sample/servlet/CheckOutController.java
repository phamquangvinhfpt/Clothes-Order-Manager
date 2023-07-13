/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.shopping.Cart;
import sample.shopping.OrderDetailDAO;
import sample.shopping.OrderedDAO;
import sample.shopping.Clothes;
import sample.shopping.ClothesDAO;
import sample.user.UserDTO;

public class CheckOutController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "viewCart.jsp";
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                Cart cart = (Cart) session.getAttribute("Cart");
                ArrayList<Clothes> CartDetails
                        = (ArrayList<Clothes>) session.getAttribute("CartDetails");
                UserDTO account = (UserDTO) session.getAttribute("LOGIN_USER");

                String OrderID = null;
                int TotalPayment = OrderedDAO.TotalPayment(cart);
                if (account != null) {
                    try {
                        OrderID = OrderedDAO.addOrder(account.getUserID(), TotalPayment);
                    } catch (ClassNotFoundException | SQLException | NamingException ex) {
                    }
                    try {
                        OrderDetailDAO.addOrderDetails(OrderID, cart);
                    } catch (ClassNotFoundException | SQLException | NamingException ex) {
                    }
                    request.setAttribute("Success_CheckOut", true);
                }else{
                    request.setAttribute("Fail_CheckOut", true);
                }

            }
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
            Logger.getLogger(CheckOutController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CheckOutController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CheckOutController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CheckOutController.class.getName()).log(Level.SEVERE, null, ex);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainController extends HttpServlet {

    private static final String LOGIN = "LoginController";
    private static final String LOGOUT = "LogoutController";
    private static final String ERROR = "error.jsp";
    private static final String ADDUS = "CreateController";
    private static final String SEARCH = "SearchController";
    private static final String UPDATEUS = "UpdateController";
    private static final String DELETEUS = "DeleteController";
    private static final String ADDCART = "AddController";
    private static final String VIEWCART = "viewCart.jsp";
    private static final String REMOVECART = "RemoveCartController";
    private static final String UPDATECART = "UpdateCartController";
    private static final String CHECKOUT = "CheckOutController";
    private static final String ADDPRODUCT = "AddProController";
    private static final String REMOVE = "RemoveController";
    private static final String BUYMORE = "BuyMoreServlet";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String action = request.getParameter("action");
            if ("Login".equals(action)) {
                url = LOGIN;
            } else if ("Logout".equals(action)) {
                url = LOGOUT;
            } else if ("Create".equals(action)) {
                url = ADDUS;
            } else if ("Search".equals(action)) {
                url = SEARCH;
            } else if ("Update".equals(action)) {
                url = UPDATEUS;
            } else if ("Cart".equals(action)) {
                url = VIEWCART;
            } else if ("Delete".equals(action)) {
                url = DELETEUS;
            } else if ("Order".equals(action)) {
                url = ADDCART;
            } else if ("Remove".equals(action)) {
                url = REMOVECART;
            } else if ("Update Cart".equals(action)) {
                url = UPDATECART;
            } else if ("Check Out".equals(action)) {
                url = CHECKOUT;
            } else if ("Add".equals(action)) {
                url = ADDPRODUCT;
            } else if ("RemoveClothes".equals(action)) {
                url = REMOVE;
            } else if ("Buy More".equals(action)) {
                url = BUYMORE;
            }
            else {
                session.setAttribute("Error", "Function is not supported !");
            }
        } catch (Exception e) {
            log("Error at MainController" + e.toString());
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
        processRequest(request, response);
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
        processRequest(request, response);
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

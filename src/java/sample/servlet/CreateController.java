/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.user.UserDAO;
import sample.user.UserError;

public class CreateController extends HttpServlet {

    private static final String ERROR = "addUser.jsp";
    private static final String SUCCESS = "shopping.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;
        try {
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String roleID = request.getParameter("roleID");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            boolean check = true;
            UserError userError = new UserError();
            if (userID.length() > 10 || userID.length() < 5) {
                userError.setUserIDError("UserID length must be [5,10]");
                check = false;
            }

            if (fullName.length() > 50 || fullName.length() < 5) {
                userError.setFullNameError("FullName length must be [5,50]");
                check = false;
            }
            
            if(!email.contains("@") || !email.contains(".")){
                userError.setEmailError("Email does not exist!");
                check = false;
            }

            if (roleID.length() > 5 || userID.length() < 2) {
                userError.setRoleIDError("RoleID length must be [2,5]");
                check = false;
            }
            if (!password.equals(confirm)) {
                userError.setConfirmError("Two passwords are not the same!");
                check = false;
            }
            if (check) {
                UserDAO dao = new UserDAO();
                
                boolean checkDuplicate = dao.checkDuplicate(userID);
                if (checkDuplicate) {
                    userError.setUserIDError("Duplicate userID: " + userID + " !");
                    request.setAttribute("USER_ERROR", userError);
                } else {
                    boolean checkInsert = dao.insert(userID, fullName, roleID, phone, address, email, password);
                    if (checkInsert) {
                        url = SUCCESS;

                    } else {
                        userError.setMessageError("Cannot insert, unknown error !");
                        request.setAttribute("USER_ERROR", userError);
                    }
                }
            } else {
                request.setAttribute("USER_ERROR", userError);
            }
        } catch (SQLException e) {
            log("Error at CreateController: " + e.toString());
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateController.class.getName()).log(Level.SEVERE, null, ex);
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


package Controllers.Authenication;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.user.UserDAO;
import sample.user.UserDTO;

public class SignInServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
              String URL = "login.jsp";
        try {
            String username = request.getParameter("userID");
            String password = request.getParameter("password");
            request.setAttribute("userID", username);
            

            UserDTO user = null;
            try {
                user = UserDAO.getUserUsernamePassword(username, password);
            } catch (SQLException | NullPointerException| ClassNotFoundException e) {}
            
            if (user == null) {
                request.setAttribute("ERROR", true);
                URL = "login.jsp";
                
            } else {
                Cookie usernameC = new Cookie("username", username);
                Cookie passwordC = new Cookie("password", password);
                usernameC.setMaxAge(60 * 5);
                passwordC.setMaxAge(60 * 5);
                response.addCookie(usernameC);
                response.addCookie(passwordC);
                 
                HttpSession session = request.getSession(true);
                session.setAttribute("LOGIN_USER", user);

                URL = "home.jsp";
            }
        } finally {
            request.getRequestDispatcher(URL).forward(request, response);
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
            Logger.getLogger(SignInServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SignInServlet.class.getName()).log(Level.SEVERE, null, ex);
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

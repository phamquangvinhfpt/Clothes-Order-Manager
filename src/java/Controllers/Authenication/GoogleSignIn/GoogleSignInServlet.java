package Controllers.Authenication.GoogleSignIn;

import java.io.IOException;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.user.UserDAO;
import sample.user.UserDTO;

public class GoogleSignInServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException {
        response.setContentType("text/html;charset=UTF-8");

        String URL = "home.jsp";
        try {
            String code = request.getParameter("code");
            String accessToken = GoogleSupport.getToken(code);
            GoogleDTO userToken = GoogleSupport.getUserInfo(accessToken);
            String username = userToken.getId();
            String email = userToken.getEmail();
            String firstName = userToken.getGiven_name();
            String lastName = userToken.getFamily_name();
            String picture = userToken.getPicture();

            //insert google account
            UserDTO user = new UserDTO(username, "US", null, null, null, email, firstName,
                    lastName, picture, null, null, true);
            HttpSession session = request.getSession();
            try {

                if (UserDAO.addUser(user)) {
                    session.setAttribute("LOGIN_USER", user);
                    session.setMaxInactiveInterval(600);
                    String role = user.getRoleID();
                    if ("US".equals(role)) {
                        URL = "userPage.jsp";
                    } else {
                        session.setAttribute("ERROR", "Your role is not supported");
                    }

                } else {
                    request.setAttribute("ERROR", "Can not access your account!");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } catch (SQLException | ClassNotFoundException ex) {
            }

        } finally {
//            request.getRequestDispatcher(URL).forward(request, response);
            response.sendRedirect(URL);
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
            Logger.getLogger(GoogleSignInServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GoogleSignInServlet.class.getName()).log(Level.SEVERE, null, ex);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.shopping.ClothesDAO;

public class AddProController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "addProduct.jsp";
        try {
            String pid = request.getParameter("productID");
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            String qty = request.getParameter("quantity");
            String image = request.getParameter("image");
            String cate = request.getParameter("category");
            ClothesDAO tea = new ClothesDAO();
            boolean check = tea.checkDuplicate(pid);
            if (check) {
                request.setAttribute("ERROR", "Duplicate product ID!");
                request.getRequestDispatcher("addProduct.jsp").forward(request, response);
            } else {
                boolean add = tea.insert(pid, name, price, qty, image, cate);
                if (add) {
                    url = "shopping.jsp";
                } else {
                    request.setAttribute("FFF", "Can not insert product!");
                    request.getRequestDispatcher("addProduct.jsp").forward(request, response);
                }

            }
        } catch (SQLException e) {
            log("Error at AddProController: " + e.toString());
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

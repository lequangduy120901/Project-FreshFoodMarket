/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.FeedbackDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import util.Feedback;
import util.extra.AccessPermission;

/**
 *
 * @author HuyCQ
 */
public class FilterFeedbackController extends UserAuthenticationController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @return 
     */
   @Override
    protected AccessPermission setUpAccessPermission() {
        AccessPermission ap = new AccessPermission(false, false, true, false);
        return ap;
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
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {//
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
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FeedbackDAO fb = new FeedbackDAO();
        String rated = request.getParameter("rated");
        if (rated == null) {
            response.sendRedirect("/FreshFoodMarket/feedbackManager");
            return;
        }
        if (rated.equalsIgnoreCase("p1")) {
            List<Feedback> listFB = fb.SortAscByRated();
            request.setAttribute("listFB", listFB);
            request.getRequestDispatcher("view/feedbackManager.jsp").forward(request, response);
        } else if (rated.equalsIgnoreCase("p2")) {
            List<Feedback> listFB = fb.SortDescByRated();
            request.setAttribute("listFB", listFB);
            request.getRequestDispatcher("view/feedbackManager.jsp").forward(request, response);
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

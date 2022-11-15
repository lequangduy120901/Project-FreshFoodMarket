/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.PostDAO;
import DAO.ProductDAO;
import DAO.SliderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import util.Post;
import util.Product;
import util.Slider;

/**
 *
 * @author Dinh Nam
 */
public class HomeController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            //    -----------NamDD code in the part below-------------
            SliderDAO slideDao = new SliderDAO();
            List<Slider> slideList = slideDao.getSliderList();
            
            PostDAO postDao = new PostDAO();
            List<Post> postList = postDao.getPostList();
            
            ProductDAO proDao = new ProductDAO();
            List<Product> proList = proDao.getFeatureProductList();
            
            request.setAttribute("slideList", slideList);
            request.setAttribute("postList", postList);
            request.setAttribute("proList", proList);
            request.getRequestDispatcher("view/homePage.jsp").forward(request, response);
    
            //    -----------BaoLTT code in the part below------------
    
    
            //    -----------DuyLQ code in the part below-------------
    
    
            //    -----------HuyCQ code in the part below-------------
    
    
            //    -----------BaoNN code in the part below-------------
    
    
            //    -----------QuangTM code in the part below-----------
            
            
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

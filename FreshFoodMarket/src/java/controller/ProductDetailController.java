/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.CustomerDAO;
import DAO.FeedbackDetailDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Product;
import DAO.ProductDAO;
import java.util.ArrayList;
import java.util.List;
import util.Account;
import util.Customer;
import util.Feedback;

/**
 *
 * @author zedqu
 */
public class ProductDetailController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        ProductDAO db = new ProductDAO();
        Product choosedP = db.getProductByProid(request.getParameter("proid"));
        CustomerDAO db1 = new CustomerDAO();
        ArrayList products = db.getRelatedProductByCategory(choosedP.getCategory().getCateID(), choosedP.getProID());
        if (request.getSession().getAttribute("currUser") != null) {
            Account currentAccount = (Account) request.getSession().getAttribute("currUser");
            Customer currentCus = db1.getCustomerByAccID(currentAccount.getAccID());
            request.setAttribute("currentCus", currentCus);
            request.setAttribute("currentAcc", currentAccount);
        } else {
            request.setAttribute("currentCus", null);
        }
        //BaoNN
        FeedbackDetailDAO feedDao = new FeedbackDetailDAO();
        List<Feedback> listFeedback = feedDao.getAllFeedBackByProID(choosedP.getProID());
        
        int rated = db.averageRating(choosedP.getProID());
        db.addRatedToProduct(rated, choosedP.getProID());
               
        request.setAttribute("listFeedback", listFeedback);
        request.setAttribute("numberCom", listFeedback.size());
        
        //--------
        choosedP = db.getProductByProid(request.getParameter("proid"));
        request.setAttribute("choosedP", choosedP);
        request.setAttribute("products", products);
        request.getRequestDispatcher("view/ProductDetail.jsp").forward(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

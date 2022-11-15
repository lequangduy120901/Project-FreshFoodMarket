/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import util.Product;

/**
 *
 * @author bao nguyen
 */
public class CategoryController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CategoryController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoryController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

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
        ProductDAO d = new ProductDAO();
        CategoryDAO dal = new CategoryDAO();
        String CategoryId = request.getParameter("idC");
        if (CategoryId.equals("0") || CategoryId.isEmpty()) {
            int page, numPerPage = 8;
            int size = d.getAllProduct().size();
            int num = (size % numPerPage == 0 ? (size / numPerPage) : (size / numPerPage) + 1);
            String xpage = request.getParameter("page");
            if (xpage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xpage);
            }
            int start, end;
            start = (page - 1) * numPerPage;
            end = Math.min(page * numPerPage, size);

            List<Product> listP = d.getListByPage(d.getAllProduct(), start, end);
            request.setAttribute("listP", listP);

            request.setAttribute("num", num);

            request.setAttribute("page", page);

            request.setAttribute("idC", CategoryId);
            request.setAttribute("listCate", dal.getAllCate());
            request.getRequestDispatcher("view/productsList.jsp").forward(request, response);
        } else {
            request.setAttribute("idC", CategoryId);
            request.setAttribute("listP", d.getListByCate(CategoryId));
            request.setAttribute("listCate", dal.getAllCate());
            request.getRequestDispatcher("view/productsList.jsp").forward(request, response);
        }
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
        processRequest(request, response);
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

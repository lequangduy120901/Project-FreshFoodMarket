/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import util.Category;
import util.Product;
import util.extra.AccessPermission;

/**
 *
 * @author Dinh Nam
 */
public class AddProductController extends UserAuthenticationController {

    /**
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
            throws ServletException, IOException {
        request.getRequestDispatcher("view/addProduct.jsp").forward(request, response);
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
        PrintWriter out = response.getWriter();
                
        String img = request.getParameter("imgInput");
        String proID = request.getParameter("proID");
        String cate = request.getParameter("category");
        String proName = request.getParameter("proName");
        String price = request.getParameter("price");
        String type = request.getParameter("type");
        String quant = request.getParameter("quantity");
        String des = request.getParameter("description");
        
        if (proID.isEmpty() || img.isEmpty() || proName.isEmpty() || price.isEmpty() 
                || type.isEmpty() || quant.isEmpty() || des.isEmpty()) {
            request.setAttribute("mess", "Không được để trống thông tin!");
            request.getRequestDispatcher("view/addProduct.jsp").forward(request, response);
            return;
        }
        
        if (!price.matches("[0-9]*") || !quant.matches("[0-9]*")) {
            request.setAttribute("mess", "Thông tin sản phẩm không hợp lệ!");
            request.getRequestDispatcher("view/addProduct.jsp").forward(request, response);
            return;
        }
        
        double xPrice = Double.parseDouble(price);
        int xQuant = Integer.parseInt(quant);
        
        CategoryDAO cDao = new CategoryDAO();
        Category category = cDao.getCategoryByID(cate);
        
        Date date = new Date();
        
        Product pro = new Product(proID, category, proName, xPrice, type, xQuant, "img/product/" + img, des, 0, date);
        
        ProductDAO pDao = new ProductDAO();
        boolean addProCheck = pDao.insertProduct(pro);
        if (addProCheck) {
            request.setAttribute("status", "1");
        } else {
            request.setAttribute("status", "-1");
        }
        request.getRequestDispatcher("view/addProduct.jsp").forward(request, response);
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

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
import util.Category;
import util.Product;
import util.extra.AccessPermission;

/**
 *
 * @author Dinh Nam
 */
public class EditProductController extends UserAuthenticationController {

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
        String proID = request.getParameter("proID");
        ProductDAO pDao = new ProductDAO();
        Product pro = new Product();
        if (proID == null || proID.isEmpty()) {
            response.sendRedirect("/FreshFoodMarket/productListSale");
        } else {
            pro = pDao.getProductbyID(proID);
        }
        request.setAttribute("product", pro);
        request.getRequestDispatcher("view/productDetailSale.jsp").forward(request, response);
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
        String img = request.getParameter("imgInput");
        String proID = request.getParameter("proID");
        String cate = request.getParameter("category");
        String proName = request.getParameter("proName").trim();
        String price = request.getParameter("price").trim().replace(",", "");
        String type = request.getParameter("type").trim();
        String quant = request.getParameter("quantity").trim();
        String des = request.getParameter("description").trim();
        
        ProductDAO pDao = new ProductDAO();
        Product pro = pDao.getProductbyID(proID);
        
        if (proName.isEmpty() || price.isEmpty() || type.isEmpty() 
                || quant.isEmpty() || des.isEmpty()) {
            request.setAttribute("mess", "Không được để trống thông tin!");
            request.setAttribute("product", pro);
            request.getRequestDispatcher("view/productDetailSale.jsp").forward(request, response);
            return;
        }
        if (!price.matches("[0-9]*") || !quant.matches("[0-9]*")) {
            request.setAttribute("mess", "Thông tin sản phẩm không hợp lệ!");
            request.setAttribute("product", pro);
            request.getRequestDispatcher("view/productDetailSale.jsp").forward(request, response);
            return;
        }
        if (!img.isEmpty()) {
            pro.setImage("img/product/" + img);
        }
        CategoryDAO cDao = new CategoryDAO();
        Category xCate = cDao.getCategoryByID(cate);
        double xPrice = Double.parseDouble(price);
        int xQuant = Integer.parseInt(quant);
        
        pro.setCategory(xCate);
        pro.setProName(proName);
        pro.setPrice(xPrice);
        pro.setType(type);
        pro.setQuantity(xQuant);
        pro.setDescription(des);
        
        pDao.updateProduct(pro);
        pro = pDao.getProductbyID(proID);
        request.setAttribute("product", pro);
        request.getRequestDispatcher("view/productDetailSale.jsp").forward(request, response);
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.Collator;
import java.util.*;
import util.Product;
import util.extra.AccessPermission;

/**
 *
 * @author Dinh Nam
 */
public class ManageProductController extends UserAuthenticationController {
    
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
        ProductDAO proDao = new ProductDAO();
        List<Product> proList = proDao.getProductList();
        request.setAttribute("listP", proList);
        request.getRequestDispatcher("view/productListSale.jsp").forward(request, response);
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
        String key = request.getParameter("key");
        String cate = request.getParameter("category");
        
        ProductDAO proDao = new ProductDAO();
        List<Product> proList = new ArrayList<>();
        
        //check list null
        
        if(key != null) {
            if (cate == null) {
                cate = "";
            }
            proList = proDao.getProductBySearchAndCate(key, cate);
        } else {
            if (cate != null) {
                proList = proDao.getProductByCate(cate);
            } else {
                proList = proDao.getProductList();
            }
        }
        
        String proName = request.getParameter("proName");
        String price = request.getParameter("price");
        String quant = request.getParameter("quantity");
        
        if (proName == null) {
            //skip
        } else if (proName.equals("pro1")) {
            Collections.sort(proList, cmpName);
        } else if (proName.equals("pro2")) {
            Collections.sort(proList, cmpName);
            Collections.reverse(proList);
        }

        if (price == null) {
            //skip
        } else if (price.equals("p1")) {
            Collections.sort(proList, cmpPrice);
        } else if (price.equals("p2")){
            Collections.sort(proList, cmpPrice);
            Collections.reverse(proList);
        }
        
        if (quant == null) {
            //skip
        } else if (quant.equals("q1")) {
            Collections.sort(proList, cmpQuantity);
        } else if (quant.equals("q2")){
            Collections.sort(proList, cmpQuantity);
            Collections.reverse(proList);
        }
        
        request.setAttribute("key", key);
        request.setAttribute("category", cate);
        request.setAttribute("proName", proName);
        request.setAttribute("price", price);
        request.setAttribute("quant", quant);
        request.setAttribute("listP", proList);
        request.getRequestDispatcher("view/productListSale.jsp").forward(request, response);
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
    
    public Comparator<Product> cmpName = new Comparator<Product>() {
        Collator cl = Collator.getInstance(new Locale("vi", "VN"));
        @Override
        public int compare(Product o1, Product o2) {
            String proName1 = o1.getProName();
            String proName2 = o2.getProName();
            return cl.compare(proName1, proName2);
        }
    };
    
    public Comparator<Product> cmpPrice = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            int proPrice1 = (int) o1.getPrice();
            int proPrice2 = (int) o2.getPrice();
            return proPrice1 - proPrice2;
        }
    };
    
    public Comparator<Product> cmpQuantity = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            int proQuant1 = o1.getQuantity();
            int proQuant2 = o2.getQuantity();
            return proQuant1 - proQuant2;
        }
    };

}

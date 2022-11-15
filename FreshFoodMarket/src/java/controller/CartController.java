/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CartDAO;
import DAO.CartDetailDAO;
import DAO.CustomerDAO;
import DAO.DBContext;
import DAO.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.Account;
import util.Cart;
import util.CartDetail;
import util.Customer;
import util.Product;
import util.extra.AccessPermission;

/**
 *
 * @author THAI BAO
 */
public class CartController extends UserAuthenticationController {

   
     @Override
    protected AccessPermission setUpAccessPermission() {
        AccessPermission ap = new AccessPermission(false, true, false, false);
        return ap;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        try ( PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
 
            Account acc = (Account) request.getSession().getAttribute("currUser");

            if (acc == null) {
                request.getRequestDispatcher("view/login.jsp").forward(request, response);
            }

            CartDAO daoCA = new CartDAO();
            CartDetailDAO daoCD = new CartDetailDAO();
            
            ProductDAO daoP = new ProductDAO();
            
            CustomerDAO cDao = new CustomerDAO();
            Customer cus = cDao.getCustomerByAccID(acc.getAccID());
            // set gia tri truyen vao cart contact
            session.setAttribute("user", cus);
            
            Cart cart = daoCA.getCartbyID(cus.getCusID());
            
            if (!daoCA.checkCart(cus.getCusID())) {
                cart = daoCA.insertCart(cus.getCusID());
            }
            
            List<CartDetail> listC = daoCD.getCartDetailByCusID(cus.getCusID());
            
            request.setAttribute("listC", listC);

            if (action == null) {
                request.getRequestDispatcher("view/showCart.jsp").forward(request, response);
            }

            if (action.equals("")) {
                request.getRequestDispatcher("view/showCart.jsp").forward(request, response);
            }

            if (action.equals("updateCart")) {
                String pid = request.getParameter("pid");
                String submit = request.getParameter("submit");
                CartDetail CD = daoCD.getCartDetailByPK(pid, cus.getCusID());
                Product p = daoP.getProductbyID(pid);
                if (submit == null) {
                    request.getRequestDispatcher("view/showCart.jsp").forward(request, response);
                }
                if (submit.equals("+")) {
                    if (CD.getQuantity() == p.getQuantity()) {
                        CD.setQuantity(p.getQuantity());
                    } else {
                        CD.setQuantity(CD.getQuantity() + 1);
                    }
                }
                if (submit.equals("-")) {
                    if (CD.getQuantity() <= 1) {
                        CD.setQuantity(1);
                    } else {
                        CD.setQuantity(CD.getQuantity() - 1);
                    }
                }
                daoCD.update(CD.getQuantity(), pid);
                request.getRequestDispatcher("cartcontroller?action=").forward(request, response);
            }

            if (action.equals("delete")) {
                String pid = request.getParameter("pid");
                daoCD.deletebyproID(pid);
                response.sendRedirect("cartcontroller");
            }

            if (action.equals("addtocart")) {
                String pid = request.getParameter("pid");
                String sQuantity = request.getParameter("quantity");
                int quantity = Integer.parseInt(sQuantity);
                
                Product p = daoP.getProductbyID(pid);

                if (daoCD.checkCartDetail(pid, cus.getCusID())) {
                    CartDetail CD = daoCD.getCartDetailByPK(pid, cus.getCusID());

                    if (CD.getQuantity() == p.getQuantity()) {
                        CD.setQuantity(p.getQuantity());
                    } else {
                        CD.setQuantity(CD.getQuantity() + 1);
                    }

                    daoCD.update(CD.getQuantity(), pid);
                    request.getRequestDispatcher("cartcontroller?action=").forward(request, response);
                } else {
                    daoCD.insertProduct(cart, p, quantity);
                    request.getRequestDispatcher("cartcontroller?action=").forward(request, response);
                }
               
            }
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
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
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
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
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

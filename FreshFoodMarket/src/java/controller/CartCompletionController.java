/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.OrderDAO;
import DAO.OrderDetailDAO;
import DAO.CartDetailDAO;
import DAO.CustomerDAO;
import DAO.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import util.Account;
import util.Order;
import util.OrderDetail;
import util.CartDetail;
import util.Customer;
import util.Email;
import util.Product;
import util.extra.AccessPermission;

/**
 *
 * @author Dinh Nam
 */
public class CartCompletionController extends UserAuthenticationController {

    /**
     *
     * @return
     */
    @Override
    protected AccessPermission setUpAccessPermission() {
        AccessPermission ap = new AccessPermission(false, true, false, false);
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
        try {
            String cusName = request.getParameter("cusName");
            String email = request.getParameter("email");
            String cusPhone = request.getParameter("cusPhone");
            String cusAddress = request.getParameter("cusAddress");

            //check parameters
            if (cusName == null || email == null || cusPhone == null || cusAddress == null
                    || cusName.isEmpty() || email.isEmpty() || cusPhone.isEmpty() || cusAddress.isEmpty()) {
                request.setAttribute("orderStatus", "3");
                request.getRequestDispatcher("view/cartCompletion.jsp").forward(request, response);
            }

            OrderDAO bDao = new OrderDAO();
            OrderDetailDAO bdDao = new OrderDetailDAO();
            CustomerDAO cusDao = new CustomerDAO();
            CartDetailDAO cdDao = new CartDetailDAO();
            ProductDAO pDao = new ProductDAO();

            Account acc = (Account) request.getSession().getAttribute("currUser");

            Customer cus = cusDao.getCustomerByAccID(acc.getAccID());
            //set delivery information
            cus.setCusName(cusName);
            cus.setEmail(email);
            cus.setCusPhone(cusPhone);
            cus.setCusAddress(cusAddress);

            List<CartDetail> listCD = cdDao.getCartDetailByCusID(cus.getCusID());
            //check cart
            if (listCD == null || listCD.isEmpty()) {
                request.setAttribute("orderStatus", "3");
                request.getRequestDispatcher("view/cartCompletion.jsp").forward(request, response);
                return;
            }
            List<CartDetail> listCD2 = new ArrayList<>();

            double bTotal = 0;

            //check order quantity
            for (CartDetail o : listCD) {
                if (o.getQuantity() > o.getProduct().getQuantity()) {
                    listCD2.add(o);
                }
                bTotal += o.getProduct().getPrice() * o.getQuantity();
            }

            if (listCD2.isEmpty()) {
                
                int status = 0;
                int count = bDao.countOrder() + 1;
                Order x = new Order("OID" + count,cus, bTotal, status);
                boolean checkOrder = bDao.insertNewOrder(x);
                
                if (!checkOrder) {
                    request.setAttribute("orderStatus", "3");
                    request.getRequestDispatcher("view/cartCompletion.jsp").forward(request, response);
                }
                Order lastOrder = bDao.getLastestOrder(cus.getCusID());
                
                for (CartDetail c : listCD) {
                    Product pro = c.getProduct();
                    int cdQuant = c.getQuantity();
                    double bdTotal = c.getQuantity() * c.getProduct().getPrice();

                    OrderDetail db = new OrderDetail(pro, lastOrder, cdQuant, bdTotal);
                    boolean bdCheck = bdDao.insertOrderDetail(db);
                    //check insert order detail
                    if (!bdCheck) {
                        status = -1;
                        bDao.updateOrderStatus(lastOrder, status);
                        request.setAttribute("orderStatus", "3");
                        request.getRequestDispatcher("view/cartCompletion.jsp").forward(request, response);
                        return;
                    } else {
                        boolean updateQuant = pDao.updateProductQuantity(pro.getProID(), (pro.getQuantity() - cdQuant));
                        //check update quantity
                        if (!updateQuant) {
                            status = -1;
                            bDao.updateOrderStatus(lastOrder, status);
                            request.setAttribute("orderStatus", "3");
                            request.getRequestDispatcher("view/cartCompletion.jsp").forward(request, response);
                        }
                    }
                }

                cdDao.deleteCartDetailByCusID(cus.getCusID());

                //send email
                boolean mailCheck = Email.sendConfirmOrderEmail(cus.getEmail(), lastOrder);
                //check that email is sent or not
                if (!mailCheck) {
                    status = -1;
                    bDao.updateOrderStatus(lastOrder, status);
                    request.setAttribute("orderStatus", "3");
                    request.getRequestDispatcher("view/cartCompletion.jsp").forward(request, response);
                }

                request.setAttribute("orderStatus", "1");
                request.getRequestDispatcher("view/cartCompletion.jsp").forward(request, response);
            } else {
                request.setAttribute("listCD", listCD2);
                request.setAttribute("orderStatus", "2");
                request.getRequestDispatcher("view/cartCompletion.jsp").forward(request, response);
            }
        } catch (MessagingException ex) {
            Logger.getLogger(CartCompletionController.class.getName()).log(Level.SEVERE, null, ex);
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
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

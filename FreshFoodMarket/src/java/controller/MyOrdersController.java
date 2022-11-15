/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.OrderDAO;
import DAO.OrderDetailDAO;
import DAO.CustomerDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import util.Account;
import util.Order;
import util.OrderDetail;
import util.Customer;
import util.extra.AccessPermission;

/**
 *
 * @author zedqu
 */
public class MyOrdersController extends UserAuthenticationController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected AccessPermission setUpAccessPermission() {
        AccessPermission ap = new AccessPermission(false, true, false, false);
        return ap;
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        OrderDAO bd = new OrderDAO();
        OrderDetailDAO bdd = new OrderDetailDAO();
        CustomerDAO cd = new CustomerDAO();
        Account currUser = (Account) request.getSession().getAttribute("currUser");
        Customer cus = cd.getCustomerByAccID(currUser.getAccID());
        ArrayList<Order> orders = bd.getOrdersByCusID(cus.getCusID());
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        ArrayList<OrderDetail> firstOrderDetails = new ArrayList<>();
        for (Order order : orders) {
            ArrayList<OrderDetail> orderDetailsTemp = bdd.getOrderDetailsByOrderID(order.getOrderID());
            for (OrderDetail orderDetail : orderDetailsTemp) {
                orderDetails.add(orderDetail);
            }
        }
        
        firstOrderDetails = bdd.getFirstOrderDetails(orderDetails);
        int page, numperpage = 6;
        int size = orders.size();
        int num = (size % 6 == 0 ? (size / 6) : (size / 6) + 1);
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        
        
        
        int start, end;
        start = (page - 1) * 6;
        end = Math.min(page * numperpage, size);
        ArrayList<Order> ordersPerPage = bd.getListOrdersByPage(orders, start, end);
        request.setAttribute("cus", cus);
        request.setAttribute("orders", ordersPerPage);
        request.setAttribute("firsts", firstOrderDetails);
        request.setAttribute("orderDetails", orderDetails);
        request.setAttribute("num", num);
        request.setAttribute("page", page);
        request.getRequestDispatcher("view/myOrders.jsp").forward(request, response);    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}

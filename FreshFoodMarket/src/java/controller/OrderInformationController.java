/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.OrderDAO;
import DAO.OrderDetailDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import util.Account;
import util.Order;
import util.OrderDetail;
import util.extra.AccessPermission;

/**
 *
 * @author zedqu
 */
public class OrderInformationController extends UserAuthenticationController {

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
        AccessPermission ap = new AccessPermission(false, true, true, false);
        return ap;
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderID = request.getParameter("orderID");
        OrderDAO bd = new OrderDAO();
        Order order = bd.getOrderByOrderID(orderID);
        Account currUser = (Account) request.getSession().getAttribute("currUser");
        if (order.getCustomer().getAccount().getAccID() == currUser.getAccID()) {
            request.setAttribute("orderID", orderID);
            OrderDetailDAO bdd = new OrderDetailDAO();
            ArrayList<OrderDetail> orderDetails = bdd.getOrderDetailsByOrderID(orderID);
            double total = 0;
            for (OrderDetail bd1 : orderDetails) {
                bd1.setTotal(bd1.getProduct().getPrice() * bd1.getQuantity());
                total += bd1.getTotal();
            }
            order.setTotal(total);
            request.setAttribute("order", order);
            request.setAttribute("orderDetails", orderDetails);
            request.getRequestDispatcher("view/orderInformation.jsp").forward(request, response);
        }else{
            response.sendRedirect("homeController");
        }
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        OrderDAO bd = new OrderDAO();
        if (Integer.parseInt(request.getParameter("status")) == -1) {
            bd.updateStatus(request.getParameter("orderID"), Integer.parseInt(request.getParameter("status")));
        }
        String orderID = request.getParameter("orderID");
        request.setAttribute("orderID", orderID);
        Order order = bd.getOrderByOrderID(orderID);
        OrderDetailDAO bdd = new OrderDetailDAO();
        ArrayList<OrderDetail> orderDetails = bdd.getOrderDetailsByOrderID(orderID);
        double total = 0;
        for (OrderDetail bd1 : orderDetails) {
            bd1.setTotal(bd1.getProduct().getPrice() * bd1.getQuantity());
            total += bd1.getTotal();
        }
        order.setTotal(total);
        request.setAttribute("order", order);
        request.setAttribute("orderDetails", orderDetails);
        response.sendRedirect("orderInformation?orderID=" + order.getOrderID());
    }

}

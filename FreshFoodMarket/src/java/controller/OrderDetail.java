/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.OrderDAO;
import DAO.OrderDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import javax.mail.Transport;
import util.Order;
import util.extra.AccessPermission;

/**
 *
 * @author Admin
 */
public class OrderDetail extends UserAuthenticationController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected AccessPermission setUpAccessPermission() {
        AccessPermission ap = new AccessPermission(false, false, true, false);
        return ap;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderDetail at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String OrderID = request.getParameter("orderID");
        Order order = new Order();
        OrderDAO bDao = new OrderDAO();
        order = bDao.getOrderByOrderID(OrderID);
        OrderDetailDAO bdDAO = new OrderDetailDAO();
        ArrayList<util.OrderDetail> list = bdDAO.getOrderDetailsByOrderID(OrderID);
        request.setAttribute("order", order);
        request.setAttribute("list", list);
        request.getRequestDispatcher("view/orderDetail.jsp").forward(request, response);

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
        String OrderID = request.getParameter("orderID");
        String status = request.getParameter("status");
        
        if (OrderID == null) {
            OrderID = "";
        }
        
        int xStatus = 0;
        if (status != null && !status.isEmpty()) {
            xStatus = Integer.parseInt(status);
        }
        
        OrderDAO bDao = new OrderDAO();
        Order order = bDao.getOrderByOrderID(OrderID);
        bDao.updateOrderStatus(order, xStatus);
        
        response.sendRedirect("orderDetail?orderID="+OrderID);
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import util.Order;
import util.extra.AccessPermission;

/**
 *
 * @author Admin
 */
public class ManageOrder extends UserAuthenticationController {

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
            out.println("<title>Servlet ManageOrder</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageOrder at " + request.getContextPath() + "</h1>");
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
            OrderDAO bDao = new OrderDAO();
            List<Order> list = bDao.getOrderList();
            request.setAttribute("listO", list);
            request.getRequestDispatcher("view/orderList.jsp").forward(request, response);
//        response.sendRedirect("orderList.jsp");
        
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
        String status = request.getParameter("status");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        
        List<Order> list = new ArrayList<>(); // khai bao list
        OrderDAO bdao = new OrderDAO();
        
        if (from != null && !from.isEmpty()) { //check from khac null hoac khong co khoang trong
            request.setAttribute("from", from);
        } else {
            from = "2020-01-01";
        }
        if (to != null && !to.isEmpty()) { // check to khac null hoac khong co khoang trong
            request.setAttribute("to", to);
        } else {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            to = dateFormat.format(date);
        }
        if (key == null || key.isEmpty()) key = "";// 
        if (status != null && !status.isEmpty()){
            int st = Integer.parseInt(status);
            list = bdao.getListOrderByStatus(key, st, from, to);
            request.setAttribute("status", status);
        } else {
            list = bdao.getListOrderByFilter(key, from, to);
        }
        
        request.setAttribute("listO", list);
        request.setAttribute("key", key);
        request.getRequestDispatcher("view/orderList.jsp").forward(request, response);
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

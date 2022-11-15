/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CustomerDAO;
import DAO.EmployeeDAO;
import DAO.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import util.Customer;
import util.Employee;
import util.extra.AccessPermission;

/**
 *
 * @author HuyCQ
 */
public class UserListController extends UserAuthenticationController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @return 
     */
   @Override
    protected AccessPermission setUpAccessPermission() {
        AccessPermission ap = new AccessPermission(false, false, false, true);
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
        int userID;
        UserDAO u = new UserDAO();
        String uID = request.getParameter("userID");
        if (uID == null) {
            userID = 1;
        } else {
            userID = Integer.parseInt(uID);
        }
        if(userID ==1){
            List<Customer> list = u.getAllCustomer();
            request.setAttribute("list", list);
            request.setAttribute("userID", userID);
            request.getRequestDispatcher("view/UserList.jsp").forward(request, response);
        }else if(userID == 2){
            List<Employee> list = u.getAllEmpolyee();
            request.setAttribute("list", list);
            request.setAttribute("userID", userID);
            request.getRequestDispatcher("view/UserList.jsp").forward(request, response);
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
        String uID = request.getParameter("userID");
        int userID = Integer.parseInt(uID);
        if(userID ==1){
            CustomerDAO cus = new CustomerDAO();
            String key = request.getParameter("key");
            List<Customer> list = cus.getCusByKey(key);
            request.setAttribute("list", list);
            request.setAttribute("userID", userID);
            request.setAttribute("key", key);
            request.getRequestDispatcher("view/UserList.jsp").forward(request, response);
        }else if(userID == 2){
            EmployeeDAO em = new EmployeeDAO();
            String key = request.getParameter("key");
            List<Employee> list = em.getEmByKey(key);
            request.setAttribute("list", list);
            request.setAttribute("userID", userID);
            request.getRequestDispatcher("view/UserList.jsp").forward(request, response);
        }
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

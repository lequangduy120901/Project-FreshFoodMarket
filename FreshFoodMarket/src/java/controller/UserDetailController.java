/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CustomerDAO;
import DAO.EmployeeDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import util.Customer;
import util.Employee;
import util.extra.AccessPermission;

/**
 *
 * @author HuyCQ
 */
public class UserDetailController extends UserAuthenticationController {

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
        String uid = request.getParameter("uID");
        int userID = Integer.parseInt(uid);
        if (userID == 1) {
            CustomerDAO d = new CustomerDAO();
            String idCus = request.getParameter("cusID");
            Customer cus = d.getCusbyID(idCus);
            LocalDate date = LocalDate.now();
            int month = date.getMonthValue();
            request.setAttribute("month", month);
            request.setAttribute("cus", cus);
            request.setAttribute("numOrder", d.getNumOrderOfCus(idCus));
            request.setAttribute("uID", userID);
        } else if (userID == 2) {
            String idEm = request.getParameter("emID");
            EmployeeDAO em = new EmployeeDAO();
            Employee employ = em.getEmployeeByID(idEm);
            request.setAttribute("em", employ);
            request.setAttribute("uID", userID);
        }
        request.getRequestDispatcher("view/UserDetail.jsp").forward(request, response);
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
        CustomerDAO cus = new CustomerDAO();
        int uID = Integer.parseInt(request.getParameter("uID"));
        if (request.getParameter("cusID") != null) {
            try {
                String cusID = request.getParameter("cusID");
                String gender = request.getParameter("gender");
                boolean g;
                if (gender.equals("male")) {
                    g = true;
                } else {
                    g = false;
                }
                String image = request.getParameter("image");
                String emailC = request.getParameter("email");
                String phoneC = request.getParameter("phone");
                String addressC = request.getParameter("address");
                cus.updateCus("img/user/customer/" + image, emailC, phoneC, addressC, cusID, g);
            } catch (NumberFormatException e) {
            }

        }
        if (request.getParameter("emID") != null) {
            try {
                String eID = request.getParameter("emID");
                EmployeeDAO em = new EmployeeDAO();
                String gender = request.getParameter("gender");
                boolean g;
                if (gender.equals("male")) {
                    g = true;
                } else {
                    g = false;
                }
                String image = request.getParameter("image");
                String emailE = request.getParameter("email");
                String phoneE = request.getParameter("phone");
                String addressE = request.getParameter("address");
                em.updateEMployee("img/user/employee/" + image, emailE, phoneE, addressE, eID, g);

            } catch (Exception e) {
            }

        }
        response.sendRedirect("userlist?userID="+uID+"");
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

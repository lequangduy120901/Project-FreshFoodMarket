/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.AccountDAO;
import DAO.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import util.Account;
import util.Customer;

/**
 *
 * @author Admin
 */
public class RegisterController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter pr = response.getWriter()) {
            AccountDAO dao = new AccountDAO();
            CustomerDAO cusDao = new CustomerDAO();
            String action = request.getParameter("action");
            if (action.equals("0")) {
                request.getRequestDispatcher("view/register.jsp").include(request, response);
            }
            if (action.equals("register")) {
                String xCusName = request.getParameter("cusName").trim();
                String xGender = request.getParameter("gender");
                String xEmail = request.getParameter("email").trim();
                String xCusPhone = request.getParameter("cusPhone").trim();
                String xCusAddress = request.getParameter("cusAddress").trim();
                String xUsername = request.getParameter("username").trim();
                String xPassword = request.getParameter("password").trim();

                if (xCusName.length() == 0) {
                    request.setAttribute("mess", "Không được để trống thông tin");
                    request.getRequestDispatcher("view/register.jsp").include(request, response);
                    return;
                }
                if (xGender.length() == 0) {
                    request.setAttribute("mess", "Không được để trống thông tin");
                    request.getRequestDispatcher("view/register.jsp").include(request, response);
                    return;
                }
                if (xEmail.length() == 0) {
                    request.setAttribute("mess", "Không được để trống thông tin");
                    request.getRequestDispatcher("view/register.jsp").include(request, response);
                    return;
                }
                if (xCusPhone.length() == 0) {
                    request.setAttribute("mess", "Không được để trống thông tin");
                    request.getRequestDispatcher("view/register.jsp").include(request, response);
                    return;
                }
                if (xCusAddress.length() == 0) {
                    request.setAttribute("mess", "Không được để trống thông tin");
                    request.getRequestDispatcher("view/register.jsp").include(request, response);
                    return;
                }
                if (xUsername.length() == 0) {
                    request.setAttribute("mess", "Không được để trống thông tin");
                    request.getRequestDispatcher("view/register.jsp").include(request, response);
                    return;
                }
                if (xPassword.length() < 8) {
                    request.setAttribute("mess", "Mật khảu chưa ít nhất 8 ký tự");
                    request.getRequestDispatcher("view/register.jsp").include(request, response);
                    return;
                }
                Boolean gender = true;
                if (xGender.equals("0")) {
                    gender = false;

                }
                boolean checkUser = dao.checkUserNameExist(xUsername);
                boolean checkMail = cusDao.checkMailExist(xEmail);
                if (checkMail == true) {
                    request.setAttribute("mess", "Email đã tồn tại");
                    request.getRequestDispatcher("view/register.jsp").include(request, response);
                }
                if (checkUser == true) {
                    request.setAttribute("mess", "Tên đăng nhập đã tồn tại");
                    request.getRequestDispatcher("view/register.jsp").include(request, response);
                }

                AccountDAO a = new AccountDAO();
                Account acc = new Account(0, xUsername, xPassword, 0);
                a.insertAccount(acc);
                Account acc2 = a.getAccount(xUsername, xPassword);

                String xUpdateBy = "";
                Date xUpdateDate = null;
                CustomerDAO c = new CustomerDAO();
                
                int count = c.countCustomer() + 1;
                Customer cus = new Customer("CUS" + count, acc2, xCusName, gender, xEmail, xCusPhone, xCusAddress, "", xUpdateBy, xUpdateDate);
                c.insertCustomer(cus);
                request.getRequestDispatcher("view/login.jsp").include(request, response);

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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

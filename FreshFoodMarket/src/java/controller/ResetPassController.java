/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.AccountDAO;
import DAO.CustomerDAO;
import DAO.EmployeeDAO;
import util.SendMail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Account;
import util.Customer;
import util.Employee;

/**
 *
 * @author bao nguyen
 */
public class ResetPassController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ResetPassController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPassController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.sendRedirect("view/ResetPass.jsp");
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String username = request.getParameter("username");
        if (username.equals("")) {
            request.setAttribute("mess", "Nhập lại username");
            request.getRequestDispatcher("view/ResetPass.jsp").forward(request, response);
        }
        
        AccountDAO dao = new AccountDAO();
        Account acc = dao.getAccByUserName(username);
        if(acc.getUsername() == null){
            request.setAttribute("mess", "Username không tồn tại");
            request.getRequestDispatcher("view/ResetPass.jsp").forward(request, response);
        }
        SendMail rs = new SendMail();
        int code = (int) Math.floor(((Math.random() * 899999) + 100000));
        int role = acc.getRole();
        if (role == 0) {
            CustomerDAO cus = new CustomerDAO();
            Customer c = cus.getCustomerByAccID(acc.getAccID());
            String email = c.getEmail();
            String subject = "OTP code";
            String message = "<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "\n"
                    + "<head>\n"
                    + "</head>\n"
                    + "\n"
                    + "<body>\n"
                    + "    <h3 style=\"color: blue;\">Đừng chia sẻ mã cho ai.</h3>\n"
                    + "    <div>Mã OTP của bạn là <strong>" + code + "</strong></div>\n"
                    + "    <h3 style=\"color: blue;\">Không nhớ mật khẩu? Không sao máy lo!</h3>\n"
                    + "\n"
                    + "</body>\n"
                    + "\n"
                    + "</html>";
            rs.send(email, subject, message, "baonnhe150673@fpt.edu.vn", "12112001");
            request.setAttribute("otp", code);
            request.setAttribute("accID", acc.getAccID());
            request.getRequestDispatcher("view/CodeOTP.jsp").forward(request, response);
        } else if (role == 1) {
            EmployeeDAO em = new EmployeeDAO();
            Employee e = em.getEmployeeByAccID(acc.getAccID());
            String email = e.getEmail();
            String subject = "OTP code";
            String message = "<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "\n"
                    + "<head>\n"
                    + "</head>\n"
                    + "\n"
                    + "<body>\n"
                    + "    <h3 style=\"color: blue;\">Đừng chia sẻ mã cho ai.</h3>\n"
                    + "    <div>Mã OTP của bạn là <strong>" + code + "</strong></div>\n"
                    + "    <h3 style=\"color: blue;\">Không nhớ mật khẩu? Không sao máy lo!</h3>\n"
                    + "\n"
                    + "</body>\n"
                    + "\n"
                    + "</html>";

            rs.send(email, subject, message, "baonnhe150673@fpt.edu.vn", "12112001");
            response.sendRedirect("view/CodeOTP.jsp");
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

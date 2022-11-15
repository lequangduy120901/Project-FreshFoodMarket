/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.regex.Pattern;
import util.Account;

/**
 *
 * @author bao nguyen
 */
public class ChangePassController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangePassController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassController at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/ChangePass.jsp").forward(request, response);
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
        AccountDAO d = new AccountDAO();
        Account acc = (Account) request.getSession().getAttribute("currUser");
        String oldPass = request.getParameter("oldPass");
        String newPass = request.getParameter("newPass");
        String rePass = request.getParameter("rePass");
        String pass = d.getSHAHash(oldPass);
        if (!acc.getPassword().equals(pass)) {
            request.setAttribute("mess", "Nhập sai mật khẩu");
            request.getRequestDispatcher("view/ChangePass.jsp").forward(request, response);
        }
        Pattern p = Pattern.compile("^[0-9A-Za-z]{8,20}$");
        Pattern pDigit = Pattern.compile("^[0-9A-Za-z]*[0-9]+[0-9A-Za-z]*$");
        Pattern pAnpha = Pattern.compile("^[0-9A-Za-z]*[a-zA-Z]+[0-9A-Za-z]*$");
        if (!(p.matcher(newPass).find() && pDigit.matcher(newPass).find()
                && pAnpha.matcher(newPass).find())) {
            request.setAttribute("mess", "Nhập không đúng định dạng mật khẩu");
            request.getRequestDispatcher("view/ChangePass.jsp").forward(request, response);
        } else if (!newPass.equalsIgnoreCase(rePass)) {
            request.setAttribute("mess", "Mật khẩu nhập lại sai");
            request.getRequestDispatcher("view/ChangePass.jsp").forward(request, response);
        } else {
            d.changePassword(newPass, acc.getUsername());
            request.getSession().setAttribute("currUser", null);
            response.sendRedirect("loginController?action=0");
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.extra.AccessPermission;
import util.Account;

/**
 *
 * @author zedqu
 */
public abstract class UserAuthenticationController extends HttpServlet {
    
    protected boolean isGuest(HttpServletRequest request){
        return request.getSession().getAttribute("currUser") == null;
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
        int currRole;
        if (isGuest(request)) {
            currRole = setUpAccessPermission().getGuest();
        } else {
            Account currAccount = (Account)request.getSession().getAttribute("currUser");
            currRole = currAccount.getRole();
        }
        if(setUpAccessPermission().getDictionary().get(currRole)){
            processGet(request, response);
        }
        else{
            response.getWriter().println("Access Denied");
            response.sendRedirect("homeController");
        }
    }

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int currRole;
        if (isGuest(request)) {
            currRole = setUpAccessPermission().getGuest();
        } else {
            Account currAccount = (Account)request.getSession().getAttribute("currUser");
            currRole = currAccount.getRole();
        }
        if(setUpAccessPermission().getDictionary().get(currRole)){
            processPost(request, response);
        }
        else{
            response.getWriter().println("Access Denied");
            response.sendRedirect("homeController");
        }
    }
    protected abstract AccessPermission setUpAccessPermission();

    protected abstract void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    protected abstract void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;


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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import util.Customer;
import util.Product;
import util.extra.AccessPermission;

/**
 *
 * @author THAI BAO
 */
public class CustomerListSaleController extends UserAuthenticationController {
 
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
            out.println("<title>Servlet CustomerListSaleController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerListSaleController at " + request.getContextPath() + "</h1>");
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
        CustomerDAO daoCus = new CustomerDAO();
         List<Customer> List = daoCus.getAllCus();
        request.setAttribute("listC", List);
        request.getRequestDispatcher("view/CustomerListSale.jsp").forward(request, response);
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
        CustomerDAO daoCus = new CustomerDAO();
        List<Customer> List = new ArrayList<>();
        
        //Search
        String txt = request.getParameter("search");
        
        if(txt!=null){
          List = daoCus.getAllCusbySearch(txt);
        } else List = daoCus.getAllCus();
        
        
        String cusName = request.getParameter("cusName");
        String cusID = request.getParameter("cusID"); 
        if(cusName == null){
         //skip   
        }
        else if(cusName.equals("AZ")){
            Collections.sort(List, compareName);
        } else if(cusName.equals("ZA")){
             Collections.sort(List, compareName);
             Collections.reverse(List);
        } 
        
         if(cusID == null){
         //skip   
        }
        else if(cusID.equals("MintoMax")){
            Collections.sort(List, compareID);
        } else if(cusID.equals("MaxtoMin")){
             Collections.sort(List, compareID);
             Collections.reverse(List);
        } 
         
         if(cusID!=null & cusName!= null){
             request.setAttribute("mess", "Không nên lọc 2 yếu tố cùng lúc");
             List = null;
             }
                 
        request.setAttribute("listC", List);
        request.getRequestDispatcher("view/CustomerListSale.jsp").forward(request, response);
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
  
     public Comparator<Customer> compareName = new Comparator<Customer>() {
//        Collator cl = Collator.getInstance(new Locale("vi", "VN"));
        @Override
        public int compare(Customer o1, Customer o2) {
            String cusName1 = o1.getCusName();
            String cusName2 = o2.getCusName();
//            return cl.compare(proName1, proName2);
           return cusName1.compareToIgnoreCase(cusName2);
        }
        };
     
    
 public Comparator<Customer> compareID = new Comparator<Customer>() {
        @Override
        public int compare(Customer o1, Customer o2) {
            String cusID1 = o1.getCusID();
            String cusID2 = o2.getCusID();
            return cusID1.compareTo(cusID2);
        }
    };
    
}


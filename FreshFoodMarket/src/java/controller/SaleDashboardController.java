/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.OrderDAO;
import DAO.OrderDetailDAO;
import DAO.CustomerDAO;
import DAO.DBContext;
import DAO.FeedbackDetailDAO;
import DAO.PostDAO;
import DAO.ProductDAO;
import DAO.SliderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Customer;
import util.Product;
import util.extra.AccessPermission;

/**
 *
 * @author THAI BAO
 */
public class SaleDashboardController extends UserAuthenticationController {

   protected AccessPermission setUpAccessPermission() {
        AccessPermission ap = new AccessPermission(false, false, true, false);
        return ap;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            OrderDetailDAO daoBD = new OrderDetailDAO();
            OrderDAO daoB = new OrderDAO();
            ProductDAO daoP = new ProductDAO();
            PostDAO daoPost = new PostDAO();
            CustomerDAO  daoC = new CustomerDAO();
            SliderDAO daoS = new SliderDAO();
            FeedbackDetailDAO daoFBD = new FeedbackDetailDAO();
            DBContext db = new DBContext();
          
 //Product 
            String xdatePro1,sdatePro1, xdatePro2, sdatePro2;
            xdatePro1 = request.getParameter("datePro1");
            sdatePro1 = request.getParameter("datePro1");
            xdatePro2 = request.getParameter("datePro2");
            sdatePro2 = request.getParameter("datePro2");
            if(sdatePro1==null){
                sdatePro1 = "2020-01-01";
            }
            
            if(sdatePro2==null||sdatePro2.equals("")){
                java.util.Date date=new java.util.Date();
                DateFormat dateFormat = null;  
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                sdatePro2 = dateFormat.format(date);
            }
            ResultSet rsPro1 = db.getData("select proID, sum(quantity) as soluong from (OrderDetail join [Order] on OrderDetail.orderID = [Order].orderID) where [Order].dateCreate between '" + sdatePro1 + "' and '" + sdatePro2 + " 23:59:59' group by OrderDetail.proID order by soluong desc");
            int soluong = 0,count = 0; String xProID; int xQuantity;
            while(rsPro1.next()){
                count = count +1;
                soluong = soluong + rsPro1.getInt(2);
            }
            
            List<Product> list = daoP.getProductList();int quantity =0;
            for(int i=0;i<list.size();i++){
                 quantity = quantity + list.get(i).getQuantity();
            }

            request.setAttribute("soluong", soluong);
            request.setAttribute("count", count);
            request.setAttribute("quantity", quantity);
            
            ResultSet rsPro2 = db.getData("select top(10) proID, sum(quantity) as soluong from (OrderDetail join [Order] on OrderDetail.orderID = [Order].orderID) where [Order].dateCreate between '" + sdatePro1 + "' and '" + sdatePro2 + " 23:59:59' group by OrderDetail.proID order by soluong desc");
            request.setAttribute("rsPro2", rsPro2);
            request.setAttribute("xdatePro1", xdatePro1);
            request.setAttribute("xdatePro2", xdatePro2);
            
            
             
 //Order
            String xdateOrder1,sdateOrder1,xdateOrder2,sdateOrder2;
            xdateOrder1 = request.getParameter("dateOrder1");
            sdateOrder1 = request.getParameter("dateOrder1");
            xdateOrder2 = request.getParameter("dateOrder2");
            sdateOrder2 = request.getParameter("dateOrder2");
            if(sdateOrder1==null){
                sdateOrder1 = "2020-01-01";
            }
            
            if(sdateOrder2==null||sdateOrder2.equals("")){
                java.util.Date date=new java.util.Date();
                DateFormat dateFormat = null;  
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                sdateOrder2 = dateFormat.format(date);
            }
             //Numbers of order
             int NOrders = daoB.countNumbersOfOrder(sdateOrder1,sdateOrder2);
             //Order success, Order fail
             int SOrders = daoB.countSuccessfulOrder(sdateOrder1,sdateOrder2);
             int FOrders = daoB.countFailedOrder(sdateOrder1,sdateOrder2);
             
            request.setAttribute("NOrders",NOrders );
            request.setAttribute("SOrders", SOrders);
            request.setAttribute("FOrders", FOrders );
            
             ResultSet rsOrder = db.getData("select * from [Order] where [Order].dateCreate between '" + sdateOrder1 + "' and '" + sdateOrder2 + " 23:59:59'and status <=0 and status !=-1");
             request.setAttribute("rsOrder", rsOrder );
             request.setAttribute("xdateOrder1", xdateOrder1);
             request.setAttribute("xdateOrder2", xdateOrder2);


             
 //Post
            int NPosts = daoPost.countPost();
            int FPosts = daoPost.countFeaturedPost();
            int IPosts = daoPost.countInactivePost();
            request.setAttribute("NPosts",NPosts);
            request.setAttribute("FPosts", FPosts);
            request.setAttribute("IPosts", IPosts);

        
            
 //Slider
            int NSlide = daoS.countSlider();
            int ASlide = daoS.countActiveSlider();
            request.setAttribute("NSlide",NSlide);
            request.setAttribute("ASlide", ASlide);
            
       
            
 //Feedback
            int NFb = daoFBD.countFeedback();
            request.setAttribute("NFb",NFb);
            
            String xdateFB1,sdateFB1, xdateFB2, sdateFB2;
            xdateFB1 = request.getParameter("dateFB1");
            sdateFB1 = request.getParameter("dateFB1");
            xdateFB2 = request.getParameter("dateFB2");
            sdateFB2 = request.getParameter("dateFB2");
            if(sdateFB1==null){
                sdateFB1 = "2020-01-01";
            }
            
            if(sdateFB2==null||sdateFB2.equals("")){
                java.util.Date date=new java.util.Date();
                DateFormat dateFormat = null;  
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                sdateFB2 = dateFormat.format(date);
            }
            //dateCreate trong sql là datetime(có giờ phút giây), con cac bảng khác là date(ko có)
             ResultSet rsFB = db.getData("select top(10) * from Feedback where Feedback.dateCreate between '" + sdateFB1 + " 00:00:00' and '" + sdateFB2 + " 23:59:59'order by dateCreate desc");
             request.setAttribute("rsFB", rsFB );
             request.setAttribute("xdateFB1", xdateFB1);
             request.setAttribute("xdateFB2", xdateFB2);
   
             
             
 //Customer
            String xdateCus1,sdateCus1 ,xdateCus2, sdateCus2;
            xdateCus1 = request.getParameter("dateCus1");
            sdateCus1 = request.getParameter("dateCus1");
            xdateCus2 = request.getParameter("dateCus2");
            sdateCus2 = request.getParameter("dateCus2");
            if(sdateCus1==null){
                sdateCus1 = "2020-01-01";
            }
            
            if(sdateCus2==null||sdateCus2.equals("")){
                java.util.Date date=new java.util.Date();
                DateFormat dateFormat = null;  
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                sdateCus2 = dateFormat.format(date);
            }
            
            int NCustomers = daoC.countCustomer();
            int CushasOrder = daoC.countCustomerhasOrder();
            request.setAttribute("NCustomers",NCustomers);
            request.setAttribute("CushasOrder", CushasOrder);
            
            ResultSet rsCus = db.getData("select top(10) * from Customer where Customer.updateDate between '" + sdateCus1 + "' and '" + sdateCus2 + " 23:59:59'order by updateDate desc");
            request.setAttribute("rsCus", rsCus );
            request.setAttribute("xdateCus1", xdateCus1);
            request.setAttribute("xdateCus2", xdateCus2);
           
//All            
            request.getRequestDispatcher("view/SaleDashboard.jsp").forward(request, response);   
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SaleDashboardController.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SaleDashboardController.class.getName()).log(Level.SEVERE, null, ex);
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

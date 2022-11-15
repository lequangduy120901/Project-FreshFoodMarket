/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.PostDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import util.Post;
import util.extra.AccessPermission;


/**
 *
 * @author THAI BAO
 */
public class PostListController extends UserAuthenticationController {

    @Override
    protected AccessPermission setUpAccessPermission() {
        AccessPermission ap = new AccessPermission(true, true, true, true);
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
            out.println("<title>Servlet PostListController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PostListController at " + request.getContextPath() + "</h1>");
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
       PostDAO daoPost = new PostDAO();
         List<Post> List = daoPost.getAllPost();
        request.setAttribute("listPost", List);
        request.getRequestDispatcher("view/PostList.jsp").forward(request, response);
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
        
         PostDAO daoPost = new PostDAO();
         List<Post> List = new ArrayList<>() ;
         
         //Search
         String txt = request.getParameter("search");
         
         if(txt!=null){
          List = daoPost.getAllPostbySearch(txt);
        } else List = daoPost.getAllPost();
        
         String title = request.getParameter("title");
        String postID = request.getParameter("postID"); 
        if(title == null){
         //skip   
        }
        else if(title.equals("AZ")){
            Collections.sort(List, compareTitle);
        } else if(title.equals("ZA")){
             Collections.sort(List, compareTitle);
             Collections.reverse(List);
        } 
        
         if(postID == null){
         //skip   
        }
        else if(postID.equals("MintoMax")){
            Collections.sort(List, compareID);
        } else if(postID.equals("MaxtoMin")){
             Collections.sort(List, compareID);
             Collections.reverse(List);
        } 
         
         
         
         if(postID!=null & title!= null){
             request.setAttribute("mess", "Không nên lọc 2 yếu tố cùng lúc");
             List = null;
             }
        request.setAttribute("listPost", List);
        request.getRequestDispatcher("view/PostList.jsp").forward(request, response);
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
  public Comparator<Post> compareTitle = new Comparator<Post>() {
//        Collator cl = Collator.getInstance(new Locale("vi", "VN"));
        @Override
        public int compare(Post o1, Post o2) {
            String title1 = o1.getTitle();
            String title2 = o2.getTitle();
//            return cl.compare(proName1, proName2);
           return title1.compareToIgnoreCase(title2);
        }
        };
     
    
 public Comparator<Post> compareID = new Comparator<Post>() {
        @Override
        public int compare(Post o1, Post o2) {
            int cusID1 = (int) o1.getPostID();
            int cusID2 = (int) o2.getPostID();
//            String xcus1 = ;
//            String xcus2 = ;
            return cusID1- cusID2;
        }
    };
}

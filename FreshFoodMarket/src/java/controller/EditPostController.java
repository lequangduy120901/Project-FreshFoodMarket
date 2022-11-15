/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.PostDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Post;
import util.extra.AccessPermission;

/**
 *
 * @author Admin
 */
public class EditPostController extends UserAuthenticationController {

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
            out.println("<title>Servlet EditPostController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditPostController at " + request.getContextPath() + "</h1>");
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
        String postID = request.getParameter("postID");
        int xPostID = 0;
        if (postID != null && !postID.isEmpty()) {
            
         xPostID = Integer.parseInt(postID);
        }
        PostDAO pDAO = new PostDAO();
        Post post = pDAO.getPostByID(xPostID);
        request.setAttribute("post", post);
        
        request.getRequestDispatcher("view/editPost.jsp").forward(request, response);
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
        
        String title = request.getParameter("title");
        String url = request.getParameter("in");
        String content = request.getParameter("content");
        String author = request.getParameter("author");
        String flag = request.getParameter("flag");
        String status = request.getParameter("status");
        String postID = request.getParameter("postID");
        int xPostID = 0;
        if (postID != null && !postID.isEmpty()) {
            
         xPostID = Integer.parseInt(postID);
        }
        if (title== null || title.isEmpty() || content== null || content.isEmpty() || author== null || author.isEmpty() ) {
            response.sendRedirect("editPost?postID="+postID);
            return;
        }
        boolean xFlag = false;
        boolean xStatus = false;
        if (flag.equals("1")) {
            xFlag = true;
        }
        if (status.equals("1")) {
            xStatus = true;
        }
        
        
        PostDAO pDAO = new PostDAO();
        Post post = pDAO.getPostByID(xPostID);
        post.setTitle(title);
        if (url!= null && !url.isEmpty()) {
            post.setThumbnail(url);
        }
        
        post.setAuthor(author);
        post.setDescription(content);
        post.setFlag(xFlag);
        post.setStatus(xStatus);
        pDAO.updatePost(post);
        response.sendRedirect("editPost?postID="+postID);
        
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

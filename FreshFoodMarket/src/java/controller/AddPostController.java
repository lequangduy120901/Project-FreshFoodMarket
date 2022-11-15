/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.EmployeeDAO;
import DAO.PostListDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.Account;
import util.Employee;
import util.extra.AccessPermission;

/**
 *
 * @author bao nguyen
 */
public class AddPostController extends UserAuthenticationController {

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected AccessPermission setUpAccessPermission() {
        AccessPermission ap = new AccessPermission(false, false, true, false);
        return ap;
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("view/NewPost.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String image = "img/post/" + request.getParameter("image");
        String title = request.getParameter("title");
        String des = request.getParameter("description");

        if (des.equals("") || title.equals("") || image.equals("img/post")) {
            request.setAttribute("mess", "vui lòng điền đầy đủ các trường");
            request.getRequestDispatcher("view/NewPost.jsp").forward(request, response);
        }

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("currUser");
        EmployeeDAO emDao = new EmployeeDAO();
        Employee em = emDao.getEmployeeByAccID(acc.getAccID());

        String author = em.getEmName();
        PostListDAO postDao = new PostListDAO();
        postDao.addPost(image, title, des, author);
        response.sendRedirect("postlist");
    }

}

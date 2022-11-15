/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.PostListDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import util.Account;
import util.Post;
import util.extra.AccessPermission;

/**
 *
 * @author bao nguyen
 */
public class ManagePostController extends UserAuthenticationController {

    
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
        PostListDAO postDao = new PostListDAO();
        List<Post> listPost = postDao.getListPost();
        //pagging
        int page, numperpage = 3;
        int size = listPost.size();
        int num = (size % numperpage == 0 ? (size / numperpage) : (size / numperpage) + 1);
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * numperpage;
        end = Math.min(page * numperpage, size);
        List<Post> listPostByPage = postDao.getListByPage(listPost, start, end);

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("currUser");
        int accR = acc.getRole();

        request.setAttribute("accR", accR);
        request.setAttribute("num", num);

        request.setAttribute("page", page);
        request.setAttribute("listPost", listPostByPage);
        request.getRequestDispatcher("view/postListSale.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

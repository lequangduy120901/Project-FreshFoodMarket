/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.SliderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.extra.AccessPermission;

/**
 *
 * @author Dinh Nam
 */
public class DeleteSliderController extends UserAuthenticationController {

    /**
     *
     * @return
     */
    @Override
    protected AccessPermission setUpAccessPermission() {
        AccessPermission ap = new AccessPermission(false, false, true, false);
        return ap;
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
        PrintWriter out = response.getWriter();
        
        String slideID = request.getParameter("id");
        SliderDAO sDao = new SliderDAO();
        boolean checkDel = sDao.deleteSlider(slideID);
        if (checkDel) {
            out.print("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Document</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <script>\n" +
                    "        alert(\"Xóa slide thành công!\");\n" +
                    "        window.location = \"/FreshFoodMarket/sliderList\";\n" +
                    "    </script>\n" +
                    "</body>\n" +
                    "</html>");
        } else {
            out.print("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Document</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <script>\n" +
                    "        alert(\"Xóa slide thất bại!\");\n" +
                    "        window.location = \"/FreshFoodMarket/sliderList\";\n" +
                    "    </script>\n" +
                    "</body>\n" +
                    "</html>");
        }
    }
    
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

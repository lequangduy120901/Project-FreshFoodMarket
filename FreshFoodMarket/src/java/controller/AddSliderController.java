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
import util.Slider;
import util.extra.AccessPermission;

/**
 *
 * @author Dinh Nam
 */
public class AddSliderController extends UserAuthenticationController {

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
        request.getRequestDispatcher("view/addSlide.jsp").forward(request, response);
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
        PrintWriter out = response.getWriter();
        
        String img = request.getParameter("img");
        String title = request.getParameter("title");
        String backlink = request.getParameter("backlink");
        String status = request.getParameter("status");
        String des = request.getParameter("des");
        String note = request.getParameter("note");
        
        if (img.isEmpty() || title.isEmpty() || backlink.isEmpty() || des.isEmpty()) {
            request.setAttribute("mess", "Kh??ng ???????c ????? tr???ng th??ng tin");
            request.getRequestDispatcher("view/addSlide.jsp").forward(request, response);
            return;
        }
        if (title.length() > 50 || des.length() > 255) {
            request.setAttribute("mess", "Ti??u ????? ho???c m?? t??? v?????t qu?? k?? t??? cho ph??p");
            request.getRequestDispatcher("view/addSlide.jsp").forward(request, response);
            return;
        }
        Slider slide = new Slider();
        slide.setSlideImage("img/slider/" + img);
        slide.setTitle(title);
        slide.setBacklink(backlink);
        boolean xStatus = false;
        if (status.equals("1")) {
            xStatus = true;
        }
        slide.setStatus(xStatus);
        slide.setDescription(des);
        slide.setNotes(note);
        
        SliderDAO sDao = new SliderDAO();
        boolean checkAdd = sDao.insertSlider(slide);
        if (checkAdd) {
            out.print("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Document</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <script>\n" +
                    "        alert(\"Th??m slide th??nh c??ng!\");\n" +
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
                    "        alert(\"Th??m slide th???t b???i!\");\n" +
                    "        window.location = \"/FreshFoodMarket/sliderList\";\n" +
                    "    </script>\n" +
                    "</body>\n" +
                    "</html>");
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

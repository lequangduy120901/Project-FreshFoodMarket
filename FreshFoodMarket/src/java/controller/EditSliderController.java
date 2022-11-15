/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.SliderDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Slider;
import util.extra.AccessPermission;

/**
 *
 * @author Dinh Nam
 */
public class EditSliderController extends UserAuthenticationController {
    
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
        String id = request.getParameter("id");
        SliderDAO sDao = new SliderDAO();
        Slider slide = sDao.getSliderByID(id);
        request.setAttribute("slide", slide);
        request.getRequestDispatcher("view/sliderDetail.jsp").forward(request, response);
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
        String id = request.getParameter("id");
        String img = request.getParameter("img");
        String title = request.getParameter("title");
        String backlink = request.getParameter("backlink");
        String status = request.getParameter("status");
        String des = request.getParameter("des");
        String note = request.getParameter("note");
        
        SliderDAO sDao = new SliderDAO();
        Slider slide = sDao.getSliderByID(id);
        
        if (title.isEmpty() || backlink.isEmpty() || des.isEmpty()) {
            request.setAttribute("mess", "Không được để trống thông tin!");
            request.setAttribute("slide", slide);
            request.getRequestDispatcher("view/sliderDetail.jsp").forward(request, response);
            return;
        }
        
        boolean xStatus = false;
        if (status.equals("1")) xStatus = true;
        if (!img.isEmpty()) slide.setSlideImage("img/slider/" + img);
        slide.setTitle(title);
        slide.setBacklink(backlink);
        slide.setStatus(xStatus);
        slide.setDescription(des);
        slide.setNotes(note);
        
        sDao.updateSlider(slide);
        slide = sDao.getSliderByID(id);
        request.setAttribute("slide", slide);
        request.getRequestDispatcher("view/sliderDetail.jsp").forward(request, response);        
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

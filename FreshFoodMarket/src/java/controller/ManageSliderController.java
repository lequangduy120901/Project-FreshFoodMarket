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
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import util.Slider;
import util.extra.AccessPermission;

/**
 *
 * @author Dinh Nam
 */
public class ManageSliderController extends UserAuthenticationController {
    
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
        SliderDAO sDao = new SliderDAO();
        List<Slider> list = sDao.getAllSliderList();
        request.setAttribute("listS", list);
        request.getRequestDispatcher("view/sliderList.jsp").forward(request, response);
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
        String key = request.getParameter("key").trim();
        String title = request.getParameter("title");
        String status = request.getParameter("status");
        
        SliderDAO sDao = new SliderDAO();
        List<Slider> list = new ArrayList<>();
        
        if (status != null && !status.isEmpty()) {
            boolean xStatus = false;
            if (status.equals("1")) xStatus = true;
            list = sDao.getListSliderByStatus(xStatus, key);
        } else {
            list = sDao.getListSliderByKey(key);
        }
        
        if (title == null) {
            //skip
        } else if (title.equals("1")) {
            Collections.sort(list, cmpTitle);
        } else if (title.equals("0")) {
            Collections.sort(list, cmpTitle);
            Collections.reverse(list);
        }
        
        request.setAttribute("key", key);
        request.setAttribute("title", title);
        request.setAttribute("status", status);
        request.setAttribute("listS", list);
        request.getRequestDispatcher("view/sliderList.jsp").forward(request, response);
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

    public Comparator<Slider> cmpTitle = new Comparator<Slider>() {
        Collator cl = Collator.getInstance(new Locale("vi", "VN"));
        @Override
        public int compare(Slider o1, Slider o2) {
            String proName1 = o1.getTitle();
            String proName2 = o2.getTitle();
            return cl.compare(proName1, proName2);
        }
    };
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import util.Product;
import util.extra.AccessPermission;


public class SortProductController extends UserAuthenticationController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @return
     */
    
    @Override
    protected AccessPermission setUpAccessPermission() {
        AccessPermission ap = new AccessPermission(true, true, true, true);
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
        String idS = request.getParameter("idS");
        String idC = request.getParameter("idC");
        ProductDAO d = new ProductDAO();
        CategoryDAO dal = new CategoryDAO();
        
        request.setAttribute("idC", idC);

        if (idC.equals("0")) {
            if (idS.equals("1")) {
                List<Product> listP = d.sortDESCListAll();
                request.setAttribute("listP", listP);
                request.setAttribute("listCate", dal.getAllCate());
                request.getRequestDispatcher("view/productsList.jsp").forward(request, response);
            } else if (idS.equals("2")) {
                List<Product> listP = d.sortASCListAll();
                request.setAttribute("listP", listP);
                request.setAttribute("listCate", dal.getAllCate());
                request.getRequestDispatcher("view/productsList.jsp").forward(request, response);
            }
        }

        if (idS.equals("1")) {
            List<Product> listP = d.sortDESCByCate(idC);
            request.setAttribute("listP", listP);
            request.setAttribute("listCate", dal.getAllCate());
            request.getRequestDispatcher("view/productsList.jsp").forward(request, response);
        } else if (idS.equals("2")) {
            List<Product> listP = d.sortASCByCate(idC);
            request.setAttribute("listP", listP);
            request.setAttribute("listCate", dal.getAllCate());
            request.getRequestDispatcher("view/productsList.jsp").forward(request, response);
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
        ProductDAO d = new ProductDAO();
        CategoryDAO dal = new CategoryDAO();
        try {
            
            String fromPrice = request.getParameter("from");
            String toPrice = request.getParameter("to");
            int from, to;
            if(fromPrice.isEmpty()){
                from = 0;
            } else{
                from = Integer.parseInt(fromPrice);
            }
            if(toPrice.isEmpty()){
                to = Integer.MAX_VALUE;
            } else{
                to = Integer.parseInt(toPrice);
            }
            if (from > to) {
                request.setAttribute("mess", "Giá ban đầu nhỏ hơn giá cuối");
                request.getRequestDispatcher("view/productsList.jsp").forward(request, response);
            } else if(from <= to) {

                List<Product> listP = d.getListByRangePrice(from, to);
                request.setAttribute("listP", listP);
                request.setAttribute("from", from);
                if(toPrice.isEmpty()){
                    
                }else{
                request.setAttribute("to", to);
                }
                request.setAttribute("listCate", dal.getAllCate());
                request.getRequestDispatcher("view/productsList.jsp").forward(request, response);
            } else {
                List<Product> listP = d.getListByFromPrice(from);
                request.setAttribute("listP", listP);
                request.setAttribute("from", from);
                request.setAttribute("listCate", dal.getAllCate());
                request.getRequestDispatcher("view/productsList.jsp").forward(request, response);
            }
            

        } catch (Exception e) {
            request.setAttribute("mess", "Chưa nhập giá cần tìm");
            request.setAttribute("listCate", dal.getAllCate());
            request.getRequestDispatcher("view/productsList.jsp").forward(request, response);
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
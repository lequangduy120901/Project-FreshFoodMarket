/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CategoryDAO;
import DAO.OrderDAO;
import DAO.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import util.Category;
import util.Order;
import util.Customer;
import util.extra.AccessPermission;

/**
 *
 * @author bao nguyen
 */
public class AdminDashController extends UserAuthenticationController {

    
    public int[] Moth = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected AccessPermission setUpAccessPermission() {
        AccessPermission ap = new AccessPermission(false, false, false, true);
        return ap;
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDAO d = new OrderDAO();
        CustomerDAO cus = new CustomerDAO();
        int month = 0;
        String monthC = request.getParameter("monthC");
        if (monthC == null) {
            LocalDate date = LocalDate.now();
            month = date.getMonthValue();

        } else {
            try {
                month = Integer.parseInt(monthC);
            } catch (Exception e) {
            }
        }
        request.setAttribute("num", month);

        // số lượng tổng quát
        List<Customer> totalC = cus.getAllCus();
        int pGrowth;
        int cusMonthCur = cus.getCusByMonth(month);
        int cusMonthBefo = cus.getCusByMonth(month - 1);
        if (cusMonthBefo == 0) {
            pGrowth = cusMonthCur;
        } else {
            pGrowth = (cusMonthCur - cusMonthBefo) / cusMonthBefo;
        }
        request.setAttribute("pGrowth", pGrowth * 100);

        int totalOrder = d.getAllOrder(month);
        request.setAttribute("totalOrder", totalOrder);

        //-- end số lượng tổng quát
        //list order theo tháng
        List<Order> listS = d.getAllOrderSuccess(month);
        request.setAttribute("listS", listS);

        List<Order> listC = d.getAllOrderWait(month);
        request.setAttribute("listC", listC);

        request.setAttribute("month ", month);
        //---endlist order theo tháng

        //ti le san pham mua trong thang
        List<Double> listPercent = new ArrayList<>();
        CategoryDAO cateDao = new CategoryDAO();
        List<Category> listCate = cateDao.getAllCate();
        double sumOfPro = (double) d.sumOfProduct(month);
        if (sumOfPro != 0) {
            for (Category category : listCate) {
                NumberFormat nf = new DecimalFormat("##.##");
                String percentC = nf.format((double) (d.sumOfProductByCateID(month, category.getCateID()) / sumOfPro * 100));
                double C = Double.parseDouble(percentC);
                listPercent.add(C);
            }
            request.setAttribute("listPercent", listPercent);
            request.setAttribute("listCate", listCate);

        } else {
            for (Category category : listCate) {
                listPercent.add(0.0);
            }
            request.setAttribute("listPercent", listPercent);
            request.setAttribute("listCate", listCate);
        }
        //--ti le san pham mua trong thang
        request.setAttribute("sumMon", d.SumPriceOrder(month)); // tong tien thang
        request.setAttribute("listM", Moth);
        request.getRequestDispatcher("view/AdminDash.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

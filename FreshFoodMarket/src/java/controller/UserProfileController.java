/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.AccountDAO;
import DAO.CustomerDAO;
import DAO.EmployeeDAO;
import DAO.extra.DVHCDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import util.Account;
import util.Customer;
import util.Employee;
import util.extra.AccessPermission;
import util.extra.DVHC;

/**
 *
 * @author zedqu
 */
public class UserProfileController extends UserAuthenticationController {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected AccessPermission setUpAccessPermission() {
        AccessPermission ap = new AccessPermission();
        ap.setGuestAllowed(false);
        ap.setCusAllowed(true);
        ap.setSaleAllowed(true);
        ap.setAdminAllowed(true);
        return ap;
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String choosedTinh = request.getParameter("choosedTinh");
        String choosedHuyen = request.getParameter("choosedHuyen");
        String choosedXa = request.getParameter("choosedXa");

        CustomerDAO cusDAO = new CustomerDAO();
        EmployeeDAO empDAO = new EmployeeDAO();
        Account currUser = (Account) request.getSession().getAttribute("currUser");
        DVHCDAO dvhcDAO = new DVHCDAO();
        ArrayList<DVHC> capTinhs = dvhcDAO.getListDvhcCapTinh();
        if (currUser.getRole() == 0) {
            Customer cus = cusDAO.getCustomerByAccID(currUser.getAccID());

            String[] tokens = cus.getCusAddress().split(",");
            String cus_soNha = tokens[0].trim();
            String cus_xa = tokens[1].trim();
            String cus_huyen = tokens[2].trim();
            String cus_tinh = tokens[3].trim();
            if (choosedTinh == null) {
                choosedTinh = dvhcDAO.getDvhcByTen(cus_tinh).getMaDVHC();
            }
            if (choosedHuyen == null) {
                choosedHuyen = dvhcDAO.getDvhcByTen(cus_huyen).getMaDVHC();
            }
            if (choosedXa == null) {
                choosedXa = dvhcDAO.getDvhcByTen(cus_xa).getMaDVHC();
            }
            request.setAttribute("cus_soNha", cus_soNha);
            request.setAttribute("cus", cus);
        } else {
            Employee em = empDAO.getEmployeeByAccID(currUser.getAccID());
            request.setAttribute("em", em);
        }
        ArrayList<DVHC> capHuyens = dvhcDAO.getListDvhcCapHuyen(choosedTinh);
        ArrayList<DVHC> capXas = dvhcDAO.getListDvhcCapXa(choosedHuyen);
        request.setAttribute("choosedTinh", choosedTinh);
        request.setAttribute("choosedHuyen", choosedHuyen);
        request.setAttribute("choosedXa", choosedXa);
        request.setAttribute("capXas", capXas);
        request.setAttribute("capHuyens", capHuyens);
        request.setAttribute("capTinhs", capTinhs);
        request.getRequestDispatcher("view/userProfile.jsp").forward(request, response);    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AccountDAO ad = new AccountDAO();
        Account account = ad.getAccountbyID(Integer.parseInt(request.getParameter("accID")));
        DVHCDAO dvhcdao = new DVHCDAO();
        if (account.getRole() == 0) {
            String cusID = request.getParameter("cus_id");
            String cusName = request.getParameter("cus_name");
            boolean gender = false;
            if (request.getParameter("cus_gender").equalsIgnoreCase("true")) {
                gender = true;
            }
            String cusPhone = request.getParameter("cus_phone");
            String cusImage = request.getParameter("last_avatar");
            if (request.getParameter("avatar_image") != "") {
                cusImage = "img/user/customer/" + request.getParameter("avatar_image");
            }

            String cusAddress = request.getParameter("cus_sonha") + ", " 
                    + dvhcdao.getDvhcByMaDvhc(request.getParameter("cus_xa")).getTen() + ", " 
                    + dvhcdao.getDvhcByMaDvhc(request.getParameter("cus_huyen")).getTen() + ", " 
                    + dvhcdao.getDvhcByMaDvhc(request.getParameter("cus_tinh")).getTen();
            String upDateBy = account.getUsername();
            Date updatedDate = Date.valueOf(request.getParameter("updateDate"));
            Customer cus = new Customer();
            cus.setCusID(cusID);
            cus.setAccount(account);
            cus.setCusName(cusName);
            cus.setGender(gender);
            cus.setCusAddress(cusAddress);
            cus.setCusPhone(cusPhone);
            cus.setCusImage(cusImage);
            cus.setUpddateBy(upDateBy);
            cus.setUpdateDate(updatedDate);
            CustomerDAO db = new CustomerDAO();
            db.update(cus);

        } else {
            String emID = request.getParameter("em_id");
            String emName = request.getParameter("em_name");
            boolean gender = false;
            if (request.getParameter("em_gender").equalsIgnoreCase("true")) {
                gender = true;
            }
            String emPhone = request.getParameter("em_phone");
            String emImage = "img/user/employee/" + request.getParameter("avatar_image");
            String emAddress = request.getParameter("em_address");
            Employee em = new Employee();
            em.setEmID(emID);
            em.setAccount(account);
            em.setEmName(emName);
            em.setGender(gender);
            em.setEmPhone(emPhone);
            em.setEmImage(emImage);
            em.setEmAddress(emAddress);
            EmployeeDAO db = new EmployeeDAO();
            db.update(em);

        }
        response.sendRedirect("userProfile");    }

}

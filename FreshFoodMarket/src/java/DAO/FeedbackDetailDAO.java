/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Customer;
import util.Feedback;
import util.Product;

/**
 *
 * @author Dinh Nam
 */
public class FeedbackDetailDAO {
//    -----------NamDD code in the part below-------------
    
    
//    -----------BaoLTT code in the part below------------
     public int countFeedback(){
       int m = 0;
       String sql = "select count(feID) from Feedback";
        
        try {
            Connection conn = new DBContext().connection;
            PreparedStatement prs = conn.prepareStatement(sql);
            ResultSet res = prs.executeQuery();
            if (res.next()) {
                m = res.getInt(1);
            }
            res.close();
            prs.close();
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return m;
    } 
    
//    -----------DuyLQ code in the part below-------------
    
    
//    -----------HuyCQ code in the part below-------------
    
    
//    -----------BaoNN code in the part below-------------
    public void addFeedback(String feedback, Product product, int rate, Customer cus) {
        String sql = "insert into Feedback (proID,cusID,rated,content)\n"
                + "values(?,?,?,?)";
        try {
            Connection conn = new DBContext().connection;
            PreparedStatement ps = conn.prepareStatement(sql);
            int count = countFeedback();
            ps.setString(1, product.getProID());
            ps.setString(2, cus.getCusID());
            ps.setInt(3, rate);
            ps.setString(4, feedback);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
        }
    }

    public List<Feedback> getAllFeedBackByProID(String ProID) {
        List<Feedback> listFeedback = new ArrayList<>();
        String sql = "select * from Feedback where proID = ? order by dateCreate desc";
        try {
            Connection conn = new DBContext().connection;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ProID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductDAO proDao = new ProductDAO();
                Product product = proDao.getProductbyID(ProID);
                CustomerDAO cusDao = new CustomerDAO();
                Customer cus = cusDao.getCusbyID(rs.getString("cusID"));
                listFeedback.add(new Feedback(rs.getInt("feID"), cus, product, rs.getInt("rated"),
                        rs.getString("content"), rs.getDate("dateCreate")));
            }
        } catch (SQLException e) {
        }
        return listFeedback;
    }

    

    public void deleteFeedback(int feID) {
        String sql = "delete  from Feedback where feID= ?";
        try {
            Connection conn = new DBContext().connection;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, feID);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
        }
    }

    public void editFeedBack(String content, String proID, int feID, int rated) {
        String sql = "UPDATE Feedback\n"
                + "SET content = ? , rated = ?\n"
                + "WHERE proID= ? and feID = ?";
        try {
            Connection conn = new DBContext().connection;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, content);
            ps.setInt(2, rated);
            ps.setString(3, proID);
            ps.setInt(4, feID);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
        }
    }
    
    public static void main(String[] args) {
        FeedbackDetailDAO dao = new FeedbackDetailDAO();
        dao.editFeedBack("11122", "10053469", 18, 3);
    }
    public Feedback getFeedbackByID(int feID, String proID, int accID) {
        String sql = "select * from Feedback\n"
                + "where feID = ?";
        Feedback fe = new Feedback();
        try {
            Connection conn = new DBContext().connection;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, feID);
            ResultSet rs = ps.executeQuery();
            ProductDAO proDao = new ProductDAO();
            Product product = proDao.getProductByProid(proID);
            CustomerDAO cusDao = new CustomerDAO();
            Customer cus = cusDao.getCustomerByAccID(accID);

            while (rs.next()) {
                fe = new Feedback(rs.getInt("feID"), cus, product,
                        rs.getInt("rated"), rs.getString("content"),
                        rs.getDate("dateCreate"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
        }
        return fe;
    }
    
//    -----------QuangTM code in the part below-----------
}

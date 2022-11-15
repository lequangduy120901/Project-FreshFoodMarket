/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Customer;
import util.Feedback;
import util.Product;

/**
 *
 * @author Dinh Nam
 */
public class FeedbackDAO {
    //    -----------NamDD code in the part below-------------
    
    
//    -----------BaoLTT code in the part below------------
    
    
//    -----------DuyLQ code in the part below-------------
    
    
//    -----------HuyCQ code in the part below-------------
    public List<Feedback> getAllFeedback() {
        String sql = "select * from feedback";
        List<Feedback> listFeedback = new ArrayList<>();
        try {
            Connection connection = new DBContext().connection;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listFeedback.add(new Feedback(rs.getInt(1),
                        new Customer(rs.getString(2)),
                        new Product(rs.getString(3)),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getDate(6)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            //
        }

        return listFeedback;
    }

    public List<Feedback> SearchFeedbackByProID(String key) {
        String sql = "select * from feedback where proID like '%" + key + "%'";
        List<Feedback> listFeedback = new ArrayList<>();
        try {
            Connection connection = new DBContext().connection;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listFeedback.add(new Feedback(rs.getInt(1),
                        new Customer(rs.getString(2)),
                        new Product(rs.getString(3)),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getDate(6)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return listFeedback;
    }

    public List<Feedback> SortDescByRated() {
        String sql = "select * from feedback order by rated desc";
        List<Feedback> listFeedback = new ArrayList<>();
        try {
            Connection connection = new DBContext().connection;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listFeedback.add(new Feedback(rs.getInt(1),
                        new Customer(rs.getString(2)),
                        new Product(rs.getString(3)),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getDate(6)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return listFeedback;
    }

    public List<Feedback> SortAscByRated() {
        String sql = "select * from feedback order by rated asc";
        List<Feedback> listFeedback = new ArrayList<>();
        try {
            Connection connection = new DBContext().connection;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listFeedback.add(new Feedback(rs.getInt(1),
                        new Customer(rs.getString(2)),
                        new Product(rs.getString(3)),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getDate(6)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return listFeedback;
    }

    public Feedback GetFeedbackByFeID(int feID) {
        String sql = "select fb.*, cus.cusName, pd.proName\n"
                + "from (\n"
                + "(feedback fb join customer cus on fb.cusID = cus.cusID)\n"
                + "join product pd on fb.proID = pd.proID)\n"
                + "where feID = ? ";
        Feedback fb = null;
        try {
            Connection connection = new DBContext().connection;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, feID);
            ResultSet rs = ps.executeQuery();
            CustomerDAO cDao = new CustomerDAO();
            ProductDAO pDao = new ProductDAO();
            while (rs.next()) {
                Customer cus = cDao.getCusbyID(rs.getString(2));
                Product pro = pDao.getProductByProid(rs.getString(3));
                fb = new Feedback(rs.getInt(1),
                        cus,
                        pro,
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getDate(6));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            //
        }
        return fb;
    }
    
//    -----------BaoNN code in the part below-------------
    
    
//    -----------QuangTM code in the part below-----------
}

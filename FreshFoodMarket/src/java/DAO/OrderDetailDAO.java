/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import util.OrderDetail;

/**
 *
 * @author Dinh Nam
 */
public class OrderDetailDAO {
//    -----------NamDD code in the part below-------------
    public boolean insertOrderDetail(OrderDetail od) {
        String xSql = "insert into OrderDetail values (?,?,?,?,?)";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(xSql);
            ps.setString(1, od.getProduct().getProID());
            ps.setString(2, od.getOrder().getOrderID());
            ps.setInt(3, od.getQuantity());
            ps.setDouble(4, od.getProduct().getPrice());
            ps.setDouble(5, od.getTotal());
            ps.executeUpdate();
            ps.close();
            con.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
//    -----------BaoLTT code in the part below------------
    
    
//    -----------DuyLQ code in the part below-------------
    
    
//    -----------HuyCQ code in the part below-------------
    
    
//    -----------BaoNN code in the part below-------------
    
    
//    -----------QuangTM code in the part below-----------
    public ArrayList<OrderDetail> getOrderDetailsByOrderID(String orderID) {
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        OrderDAO dbOrder = new OrderDAO();
        ProductDAO dbPro = new ProductDAO();
        try {
            String sql = "SELECT [proID]\n"
                    + "      ,[orderID]\n"
                    + "      ,[quantity]\n"
                    + "      ,[price]\n"
                    + "      ,[total]\n"
                    + "  FROM [OrderDetail]\n"
                    + "  WHERE [orderID] = ?";
            Connection con = new DBContext().connection;
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, orderID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(dbPro.getProductByProid(rs.getString("proID")));
                orderDetail.setOrder(dbOrder.getOrderByOrderID(orderID));
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetail.setTotal(rs.getDouble("total"));
                orderDetails.add(orderDetail);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderDetails;
    }

    public ArrayList<OrderDetail> getFirstOrderDetails(ArrayList<OrderDetail> orderDetails) {
        ArrayList<OrderDetail> res = new ArrayList<>();
        for (OrderDetail bd : orderDetails) {
            if (res.isEmpty()) {
                res.add(bd);
            } else {
                boolean check = true;
                for (OrderDetail bd1 : res) {
                    if (bd1.getOrder().getOrderID().equals(bd.getOrder().getOrderID())) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    res.add(bd);
                }
            }
        }
        return res;
    }
    
}

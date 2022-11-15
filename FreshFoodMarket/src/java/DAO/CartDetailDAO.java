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
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Cart;
import util.CartDetail;
import util.Product;

/**
 *
 * @author Dinh Nam
 */
public class CartDetailDAO {

    //    -----------NamDD code in the part below-------------
    public boolean deleteCartDetailByCusID(String cusID) {
        String sql = "delete from CartDetail where cartID = ?";
        CartDAO cDao = new CartDAO();
        Cart cart = cDao.getCartbyID(cusID);
        try {
            Connection conn = new DBContext().connection;
            PreparedStatement prs = conn.prepareStatement(sql);
            prs.setInt(1, cart.getCartID());
            prs.executeUpdate();
            prs.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public List<CartDetail> getCartDetailByCusID(String cusID) {
        String sql = "select * from CartDetail where cartID = ?";
        
        List<CartDetail> list = new ArrayList<>();
        
        CartDAO cDao = new CartDAO();
        ProductDAO pDao = new ProductDAO();
        Cart cart = cDao.getCartbyID(cusID);
        CartDetail cd = null;
        try {
            Connection conn = new DBContext().connection;
            PreparedStatement prs = conn.prepareStatement(sql);
            prs.setInt(1, cart.getCartID());
            ResultSet res = prs.executeQuery();
            while (res.next()) {
                String proID = res.getString("proID");
                int quantity = res.getInt("quantity");
                Product pro = pDao.getProductByProid(proID);
                cd = new CartDetail(cart, pro, quantity);
                list.add(cd);
            }
            res.close();
            prs.close();
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(CartDetailDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

//    -----------BaoLTT code in the part below------------
//    public CartDetail getCart() { 
//    
//    xSql = "select * from CartDetail";
//    CartDetail t = null;
//    try {
//    ps = con.prepareStatement(xSql);
//    rs = ps.executeQuery();
//    if (rs.next()) {
//    int cartID = rs.getInt(1);
//    String proID = rs.getString(2);
//    int quantity = rs.getInt(3);
//     t = new CartDetail(cartID,proID,quantity);       
//     rs.close();
//     ps.close();
//      }
//     }
//     catch(Exception e) {
//        e.printStackTrace();
//     }
//    return(t);
//   }
//  
//    
//       
//    public int getCartID(int CusID){
//        xSql = "select cartID from Cart where cusID = ?";
//        int CartID = 0;
//        try {
//            ps = con.prepareStatement(xSql);
//            ps.setInt(1, CusID);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                CartID = rs.getInt(1);
//            }
//            rs.close();
//            ps.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//        return (CartID);
//    }
//    
//    
    public CartDetail getCartDetailByPK(String proID, String cusID) {
        String xSql = "select * from CartDetail where proID = ? and cartID = ?";
        CartDetail cd = null;
        ProductDAO daoPr = new ProductDAO();
        Product pro = daoPr.getProductbyID(proID);
        CartDAO daoC = new CartDAO();
        Cart cart = daoC.getCartbyID(cusID);
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(xSql);
            ps.setString(1, proID);
            ps.setInt(2, cart.getCartID());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
//                 cart = daoC.getCartbyID(rs.getInt(1)) ;
//                 pro = daoPr.getProductbyID(proID);
                int quantity = rs.getInt(3);
                cd = new CartDetail(cart, pro, quantity);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (cd);
    }

    public void update(int quantity, String proID) {
        String xSql = "update CartDetail set quantity = ? where proID = ?";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(xSql);
            ps.setInt(1, quantity);
            ps.setString(2, proID);

            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    public void deletebyproID(String proID) {
        String xSql = "delete from CartDetail where proID =?";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(xSql);
            ps.setString(1, proID);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertProduct(Cart cart, Product p, int quantity) {
        String xSql = "insert into CartDetail values(?,?,?)";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(xSql);
            ps.setInt(1, cart.getCartID());
            ps.setString(2, p.getProID());
            ps.setInt(3, quantity);

            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkCartDetail(String proID, String cusID) {
        String xSql = "select * from CartDetail where proID = ? and cartID = ?";
        CartDAO daoC = new CartDAO();
        Cart cart = daoC.getCartbyID(cusID);
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(xSql);
            ps.setString(1, proID);
            ps.setInt(2, cart.getCartID());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

//    public static void main(String[] args) {
//        CartDetailDAO dao = new CartDetailDAO();
        //  dao.deletebyproID("2");
        // dao.update(12, "2");
        // CartDetail t = dao.getCDbyproID("2");
        // List t = dao.getProductName();
        //   System.out.println(t);
//    }

//    -----------DuyLQ code in the part below-------------
//    -----------HuyCQ code in the part below-------------
//    -----------BaoNN code in the part below-------------
//    -----------QuangTM code in the part below-----------    
}

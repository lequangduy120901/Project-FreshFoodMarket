/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.Cart;
import util.Customer;
import util.Product;

/**
 *
 * @author Dinh Nam
 */
public class CartDAO {
//    -----------NamDD code in the part below-------------

//    -----------BaoLTT code in the part below------------
    public Cart getCartbyID(String CusID) {
        String xSql = "select * from Cart where cusID = ?";
        Cart cart = null;
        CustomerDAO daoCus = new CustomerDAO();
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(xSql);
            ps.setString(1, CusID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int cartID = rs.getInt(1);
                Customer cus = daoCus.getCusbyID(CusID);
                cart = new Cart(cartID, cus);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (cart);

    }
    
    public boolean checkCart(String cusID) {
        String xSql = "select * from Cart where cusID = ?";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(xSql);
            ps.setString(1, cusID);
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

    public Cart insertCart(String cusID) {
        String xSql = "insert into Cart (cusID) values (?)";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(xSql);
            ps.setString(1, cusID);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        CartDAO cDao = new CartDAO();
        return cDao.getCartbyID(cusID);
    }

    public static void main(String[] args) {
        CartDAO daoCa = new CartDAO();
        Cart c = daoCa.getCartbyID("iui");
        System.out.println(c);
    }

//    -----------DuyLQ code in the part below-------------
    
//    -----------HuyCQ code in the part below-------------
//    -----------BaoNN code in the part below-------------
//    -----------QuangTM code in the part below-----------    
}

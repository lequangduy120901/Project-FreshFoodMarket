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
import util.Category;

/**
 *
 * @author Dinh Nam
 */
public class CategoryDAO {
//    -----------NamDD code in the part below-------------
//    public Category getCategory(String xCateID) {
//        if (xCateID == null || xCateID.trim().equals("")) {
//            return (null);
//        }
//        Category x = null;
//        xSql = "select * from Category where cateID = ?";
//        try {
//            ps = con.prepareStatement(xSql);
//            ps.setString(1, xCateID);
//            rs = ps.executeQuery();
//            String xCateName;
//            if (rs.next()) {
//                xCateID = rs.getString("cateID");
//                xCateName = rs.getString("cateName");
//                x = new Category(xCateID, xCateName);
//            }
//            rs.close();
//            ps.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return (x);
//    }
    
//    -----------BaoLTT code in the part below------------
      public Category getCategoryByID(String xCateID) {
        if (xCateID == null || xCateID.trim().equals("")) {
            return (null);
        }
        Category x = null;
        String xSql = "select * from Category where cateID = ?";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(xSql);
            ps.setString(1, xCateID);
            ResultSet rs = ps.executeQuery();
            String xCateName;
            if (rs.next()) {
                xCateID = rs.getString("cateID");
                xCateName = rs.getString("cateName");
                x = new Category(xCateID, xCateName);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }

    
//    -----------DuyLQ code in the part below-------------
    
    
//    -----------HuyCQ code in the part below-------------
    
    
//    -----------BaoNN code in the part below-------------
    public List<Category> getAllCate(){
        List<Category> listCate = new ArrayList<>();
        String sql = "select * from Category Order by cateName ASC";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                listCate.add(new Category(rs.getString(1),
                        rs.getString(2)
                        ));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
        }
        return listCate;
        
        
    }

    
//    -----------QuangTM code in the part below-----------    

   
}

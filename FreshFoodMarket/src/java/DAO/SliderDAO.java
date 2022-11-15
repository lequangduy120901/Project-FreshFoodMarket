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
import util.Slider;

/**
 *
 * @author Dinh Nam
 */
public class SliderDAO {
//    -----------NamDD code in the part below-------------
    public List<Slider> getSliderList() {
        List<Slider> list = new ArrayList<>();
        String xSql = "select * from Slider where status = 1";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(xSql);
            ResultSet rs = ps.executeQuery();
            Slider x;
            while (rs.next()) {
                String xSlideID = rs.getString("slideID");
                String xSlideImage = rs.getString("slideImage");
                String xTitle = rs.getString("title");
                String xDescription = rs.getString("description");
                String xBacklink = rs.getString("backlink");
                boolean xStatus = rs.getBoolean("status");
                String xNotes = rs.getString("notes");
                x = new Slider(xSlideID, xSlideImage, xTitle, xDescription, xBacklink, xStatus, xNotes);
                list.add(x);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return (list);
    }
    
    public List<Slider> getAllSliderList() {
        List<Slider> list = new ArrayList<>();
        String xSql = "select * from Slider";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(xSql);
            ResultSet rs = ps.executeQuery();
            Slider x;
            while (rs.next()) {
                String xSlideID = rs.getString("slideID");
                String xSlideImage = rs.getString("slideImage");
                String xTitle = rs.getString("title");
                String xDescription = rs.getString("description");
                String xBacklink = rs.getString("backlink");
                boolean xStatus = rs.getBoolean("status");
                String xNotes = rs.getString("notes");
                x = new Slider(xSlideID, xSlideImage, xTitle, xDescription, xBacklink, xStatus, xNotes);
                list.add(x);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return (list);
    }
    
    public Slider getSliderByID(String id) {
        String sql = "select * from Slider where slideID=?";
        Slider x = null;
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String xSlideImage = rs.getString("slideImage");
                String xTitle = rs.getString("title");
                String xDescription = rs.getString("description");
                String xBacklink = rs.getString("backlink");
                boolean xStatus = rs.getBoolean("status");
                String xNotes = rs.getString("notes");
                x = new Slider(id, xSlideImage, xTitle, xDescription, xBacklink, xStatus, xNotes);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return x;
    }
    
    public List<Slider> getListSliderByKey(String key) {
        String sql = "select * from Slider where (title like ? or backlink like ?)";
        List<Slider> list = new ArrayList<>();
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ps.setString(2, "%" + key + "%");
            ResultSet rs = ps.executeQuery();
            Slider x;
            while (rs.next()) {
                String xSlideID = rs.getString("slideID");
                String xSlideImage = rs.getString("slideImage");
                String xTitle = rs.getString("title");
                String xDescription = rs.getString("description");
                String xBacklink = rs.getString("backlink");
                boolean xStatus = rs.getBoolean("status");
                String xNotes = rs.getString("notes");
                x = new Slider(xSlideID, xSlideImage, xTitle, xDescription, xBacklink, xStatus, xNotes);
                list.add(x);
            }
        } catch (Exception e) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
    
    public List<Slider> getListSliderByStatus(boolean status, String key) {
        String sql = "select * from Slider where (title like ? or backlink like ?) and status=?";
        List<Slider> list = new ArrayList<>();
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ps.setString(2, "%" + key + "%");
            ps.setBoolean(3, status);
            ResultSet rs = ps.executeQuery();
            Slider x;
            while (rs.next()) {
                String xSlideID = rs.getString("slideID");
                String xSlideImage = rs.getString("slideImage");
                String xTitle = rs.getString("title");
                String xDescription = rs.getString("description");
                String xBacklink = rs.getString("backlink");
                boolean xStatus = rs.getBoolean("status");
                String xNotes = rs.getString("notes");
                x = new Slider(xSlideID, xSlideImage, xTitle, xDescription, xBacklink, xStatus, xNotes);
                list.add(x);
            }
        } catch (Exception e) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
    
    public boolean insertSlider(Slider slider) {
        String sql = "insert into Slider(slideID, slideImage, title, description, backlink, status, notes) values (?,?,?,?,?,?,?)";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            Integer count = countSlider() + 1;
            String sID = "SLI" + count.toString();
            ps.setString(1, sID);
            ps.setString(2, slider.getSlideImage());
            ps.setString(3, slider.getTitle());
            ps.setString(4, slider.getDescription());
            ps.setString(5, slider.getBacklink());
            ps.setBoolean(6, slider.isStatus());
            ps.setString(7, slider.getNotes());
            ps.executeUpdate();
            ps.close();
            con.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void updateSlider(Slider slider) {
        String sql = "update Slider set slideImage=?, title=?, description=?, backlink=?, status=?, notes=? where slideID=?";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, slider.getSlideImage());
            ps.setString(2, slider.getTitle());
            ps.setString(3, slider.getDescription());
            ps.setString(4, slider.getBacklink());
            ps.setBoolean(5, slider.isStatus());
            ps.setString(6, slider.getNotes());
            ps.setString(7, slider.getSlideID());
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public boolean deleteSlider(String slideID) {
        String sql = "delete from Slider where slideID=?";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, slideID);
            ps.executeUpdate();
            ps.close();
            con.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
//    -----------BaoLTT code in the part below------------
       public int countSlider(){
       int m = 0;
       String sql = "select COUNT(slideID) from Slider";
        
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
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return m;
    } 
       
       public int countActiveSlider(){
       int m = 0;
       String sql = "select COUNT(slideID) from Slider where status=1";
        
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
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return m;
    } 
    
//    -----------DuyLQ code in the part below-------------
    
    
//    -----------HuyCQ code in the part below-------------
    
    
//    -----------BaoNN code in the part below-------------
    
    
//    -----------QuangTM code in the part below-----------
}

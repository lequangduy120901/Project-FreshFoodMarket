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
import util.Post;

/**
 *
 * @author Dinh Nam
 */
public class PostDAO {
//    -----------NamDD code in the part below-------------

    public List<Post> getPostList() {
        List<Post> list = new ArrayList<>();
        String xSql = "select top (9) * from Post where status = 1 order by postID desc";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(xSql);
            ResultSet rs = ps.executeQuery();
            Post x;
            while (rs.next()) {
                int xPostID = rs.getInt("postID");
                String xThumbnail = rs.getString("thumbnail");
                String xTitle = rs.getString("title");
                String xDescription = rs.getString("description");
                String xAuthor = rs.getString("author");
                boolean xFlag = rs.getBoolean("flag");
                boolean xStatus = rs.getBoolean("status");
                x = new Post(xPostID, xThumbnail, xTitle, xDescription, xAuthor, xFlag, xStatus);
                list.add(x);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return (list);
    }

//    -----------BaoLTT code in the part below------------
    public int countPost() {
        int m = 0;
        String sql = "select COUNT(postID) from Post";

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
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return m;
    }

    public int countFeaturedPost() {
        int m = 0;
        String sql = "select COUNT(postID) from Post where flag = 1";

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
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return m;
    }

    public int countInactivePost() {
        int m = 0;
        String sql = "select COUNT(postID) from Post where status = 0";

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
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return m;
    } 
    
      public List<Post> getAllPost() {
        List<Post> list = new ArrayList<>();
        String xSql = "select * from Post where status = 1";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(xSql);
            ResultSet rs = ps.executeQuery();
            Post x;
            while (rs.next()) {
                int xPostID = rs.getInt("postID");
                String xThumbnail = rs.getString("thumbnail");
                String xTitle = rs.getString("title");
                String xDescription = rs.getString("description");
                String xAuthor = rs.getString("author");
                boolean xFlag = rs.getBoolean("flag");
                boolean xStatus = rs.getBoolean("status");
                x = new Post(xPostID, xThumbnail, xTitle, xDescription, xAuthor, xFlag, xStatus);
                list.add(x);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return (list);
    }
      
        public List<Post> getAllPostbySearch(String xpost) {
        List<Post> list = new ArrayList<>();
        String xSql = "select * from Post where (title like ? or author like ?) and status = 1";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(xSql);
            ps.setString(1, "%"+xpost+"%");
            ps.setString(2, "%"+xpost+"%");
            ResultSet rs = ps.executeQuery();
            Post x;
            while (rs.next()) {
                int xPostID = rs.getInt("postID");
                String xThumbnail = rs.getString("thumbnail");
                String xTitle = rs.getString("title");
                String xDescription = rs.getString("description");
                String xAuthor = rs.getString("author");
                boolean xFlag = rs.getBoolean("flag");
                boolean xStatus = rs.getBoolean("status");
                x = new Post(xPostID, xThumbnail, xTitle, xDescription, xAuthor, xFlag, xStatus);
                list.add(x);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
           e.printStackTrace(); 
        }
        return (list);
    }
        
        public Post getPostbyID(int postID){
            Post x = null;
            String xSql = "select * from Post where postID=?";
           try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(xSql);
            ps.setInt(1, postID );
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                int xPostID = rs.getInt("postID");
                String xThumbnail = rs.getString("thumbnail");
                String xTitle = rs.getString("title");
                String xDescription = rs.getString("description");
                String xAuthor = rs.getString("author");
                boolean xFlag = rs.getBoolean("flag");
                boolean xStatus = rs.getBoolean("status");
                x = new Post(xPostID, xThumbnail, xTitle, xDescription, xAuthor, xFlag, xStatus);
                
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
           e.printStackTrace(); 
        }
        return x;
        }
        
//        public static void main(String[] args) {
//        PostDAO dao = new PostDAO();
////        List list = dao.getAllPostbySearch("Bí");
//            System.out.println(dao.getPostbyID(1));
//    }
//    -----------DuyLQ code in the part below-------------
    public void updatePost(Post post) {
        String sql = "update Post set title=?,thumbnail=?, description=?, author=?, flag=?, status=? where postID=?";
        try {
            Connection conn = new DBContext().connection;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getThumbnail());
            ps.setString(3, post.getDescription());
            ps.setString(4, post.getAuthor());
            ps.setBoolean(5, post.isFlag());
            ps.setBoolean(6, post.isStatus());
            ps.setInt(7, post.getPostID());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
        }
    }

    public Post getPostByID(int postID) {
        String xSql = "select * from Post where postID= ? ";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(xSql);
            ps.setInt(1, postID);
            ResultSet rs = ps.executeQuery();
            Post x = null;
            while (rs.next()) {
                int xPostID = rs.getInt("postID");
                String xThumbnail = rs.getString("thumbnail");
                String xTitle = rs.getString("title");
                String xDescription = rs.getString("description");
                String xAuthor = rs.getString("author");
                boolean xFlag = rs.getBoolean("flag");
                boolean xStatus = rs.getBoolean("status");
                x = new Post(xPostID, xThumbnail, xTitle, xDescription, xAuthor, xFlag, xStatus);
            }
            rs.close();
            ps.close();
            return x;
        } catch (SQLException e) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

//    -----------HuyCQ code in the part below-------------
//    -----------BaoNN code in the part below-------------
//    -----------QuangTM code in the part below-----------
}

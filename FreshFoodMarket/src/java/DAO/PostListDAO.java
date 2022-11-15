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
import util.Post;

/**
 *
 * @author Dinh Nam
 */
public class PostListDAO {
    //BaoNN
    public List<Post> getListPost() {
        String sql = "select * from Post";
        List<Post> listPost = new ArrayList<>();
        try {
            Connection conn = new DBContext().connection;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listPost.add(new Post(rs.getInt("postID"), rs.getString("thumbnail"),
                        rs.getString("title"), rs.getString("description"),
                        rs.getString("author"), rs.getBoolean("flag"), rs.getBoolean("status")));
            }
        } catch (SQLException e) {
        }
        return listPost;
    }

    public List<Post> searchPost(String key) {
        String sql = "select * from Post\n"
                + "where title like ?";
        List<Post> listPost = new ArrayList<>();
        try {
            Connection conn = new DBContext().connection;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listPost.add(new Post(rs.getInt("postID"), rs.getString("thumbnail"),
                        rs.getString("title"), rs.getString("description"),
                        rs.getString("author"), rs.getBoolean("flag"), rs.getBoolean("status")));
            }
        } catch (SQLException e) {
        }
        return listPost;
    }

    public List<Post> getListByPage(List<Post> listPost, int start, int end) {
        List<Post> list = new ArrayList<>();
        for (int i = start; i < end; i++) {
            list.add(listPost.get(i));
        }
        return list;

    }

    public void addPost(String image, String title, String des, String author) {
        String sql = "insert into Post (thumbnail,title,[description],author)\n"
                + "values(?,?,?,?)";
        try {
            Connection conn = new DBContext().connection;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, image);
            ps.setString(2, title);
            ps.setString(3, des);
            ps.setString(4, author);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
        }
    }
}

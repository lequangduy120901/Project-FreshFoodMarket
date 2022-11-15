/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Account;

/**
 *
 * @author Dinh Nam
 */
public class AccountDAO {
//    -----------NamDD code in the part below-------------

//    -----------BaoLTT code in the part below------------
    public Account getAccountbyID(int accID) {
        Account x = null;
        String xSql = "select * from Account where accID = ?";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(xSql);
            ps.setInt(1, accID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                int role = rs.getInt(4);
                x = new Account(accID, username, password, role);
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
    public Account getAccount(String username, String password) {
        String hashedText = getSHAHash(password);
        Account acc = null;
        String xSql = "select * from Account where username = ? and password = ? ";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(xSql);
            ps.setString(1, username);
            ps.setString(2, hashedText);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                acc = new Account(rs.getInt("accID"), rs.getString("username"), rs.getString("password"), rs.getInt("role"));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
        }
        return acc;
    }

    public boolean checkUserNameExist(String user) {
        String sql = "select * from Account where username = ?";
        boolean check = true;
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                check = true;
            } else {
                check = false;
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public void insertAccount(Account x) {
        String hashedText = getSHAHash(x.getPassword());
        String xSql = "insert into Account (username, password, role) values (?,?,?)";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = null;
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getUsername());
            ps.setString(2, hashedText);
            ps.setInt(3, x.getRole());
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    public static String getSHAHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(input.getBytes());
            return convertByteToHex(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertByteToHex(byte[] data) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

//    public static void main(String[] args) {
//        AccountDAO a = new AccountDAO();
////         System.out.println(a.getAccount("kien123", "12345678"));
//         Account acc = new Account(0,"kien","12345678",0);
//         a.insertAccount(acc);
////        boolean check = a.checkUserNameExist("leduy120901");
////        System.out.println(check);
//    }
//    -----------HuyCQ code in the part below-------------
    //test
//    -----------BaoNN code in the part below-------------
    public void changePassword(String Pass, String username) {
        String newPass = getSHAHash(Pass);
        String sql = "update Account \n"
                + "set password = ? where username = ?";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, newPass);
            ps.setString(2, username);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
        }
    }

    public List<Account> getAllAccount() {
        List<Account> list = new ArrayList();
        String sql = "select * from Account";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            ps.close();
            rs.close();
            con.close();
        } catch (Exception e) {
        }
        return list;
    }
    
    public Account getAccByUserName(String username) {
        String sql = "select * from Account where username = ? ";
        Account acc = new Account();
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                acc = new Account(rs.getInt("accID"), rs.getString("username"),
                        rs.getString("password"), rs.getInt("role"));
            }
            ps.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
        }
        return acc;
    }

//    -----------QuangTM code in the part below-----------    
}

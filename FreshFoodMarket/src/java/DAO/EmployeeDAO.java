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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Account;
import util.Employee;

/**
 *
 * @author Dinh Nam
 */
public class EmployeeDAO{
//    -----------NamDD code in the part below-------------
    
    
//    -----------BaoLTT code in the part below------------
    
    
//    -----------DuyLQ code in the part below-------------
    
    
//    -----------HuyCQ code in the part below-------------
    public Employee getEmployeeByID(String idE) {
        String sql = "select * from Employee where emID = ?";
        Employee em = new Employee();
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AccountDAO d = new AccountDAO();
                Account acc = d.getAccountbyID(rs.getInt("accID"));
                em = new Employee(rs.getString("emID"), acc, rs.getString("emName"),
                        rs.getBoolean("gender"), rs.getString("email"), rs.getString("emPhone"),
                        rs.getString("emAddress"), rs.getString("emImage"));
            }
            ps.close();
            rs.close();
        } catch (Exception e) {
        }
        return em;
    }

    public List<Employee> getEmByKey(String key) {
        List<Employee> em = new ArrayList<>();
        String sql = "select * from Employee\n"
                + "               where emName like ?";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AccountDAO d = new AccountDAO();
                Account acc = d.getAccountbyID(rs.getInt("accID"));
                em.add(new Employee(rs.getString("emID"), acc, rs.getString("emName"),
                        rs.getBoolean("gender"), rs.getString("email"), rs.getString("emPhone"),
                        rs.getString("emAddress"), rs.getString("emImage")));
            }
            ps.close();
            rs.close();
        } catch (Exception e) {
        }
        return em;
    }

    public void updateEMployee(String image, String email, String phone, String address, String emID, boolean gender) {
        String sql = "update Employee\n"
                + "set emImage=?,email = ?, emPhone = ?, emAddress = ?, gender = ?\n"
                + "where emID = ?";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, image);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setBoolean(5, gender);
            ps.setString(6, emID);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
        }

    }
    
//    -----------BaoNN code in the part below-------------

    
//    -----------QuangTM code in the part below----------- 
    public Employee getEmployeeByAccID(int accID) {
        Employee employee = new Employee();
        AccountDAO accDAO = new AccountDAO();
        try {
            String sql = "SELECT [emID]\n"
                    + "      ,[accID]\n"
                    + "      ,[emName]\n"
                    + "      ,[gender]\n"
                    + "      ,[email]\n"
                    + "      ,[emPhone]\n"
                    + "      ,[emAddress]\n"
                    + "      ,[emImage]\n"
                    + "  FROM [Employee]\n"
                    + "  WHERE [accID] = ?";
            Connection con = new DBContext().connection;
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, accID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                employee.setEmID(rs.getString("emID"));
                Account account = new Account();
                account = accDAO.getAccountbyID(accID);
                employee.setAccount(account);
                employee.setEmName(rs.getString("emName"));
                employee.setGender(rs.getBoolean("gender"));
                employee.setEmail(rs.getString("email"));
                employee.setEmPhone(rs.getString("emPhone"));
                employee.setEmAddress(rs.getString("emAddress"));
                employee.setEmImage(rs.getString("emImage"));
            }
            stm.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employee;
    }
    
    public void update(Employee model) {
        try {
            String sql = "UPDATE [Employee]\n"
                    + "   SET [emName] = ?\n"
                    + "      ,[gender] = ?\n"
                    + "      ,[emPhone] = ?\n"
                    + "      ,[emAddress] = ?\n"
                    + "      ,[emImage] = ?\n"
                    + " WHERE [emID] = ?";
            Connection con = new DBContext().connection;
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, model.getEmName());
            stm.setBoolean(2, model.isGender());
            stm.setString(3, model.getEmPhone());
            stm.setString(4, model.getEmAddress());
            stm.setString(5, model.getEmAddress());
            stm.setString(6, model.getEmImage());
            stm.setString(8, model.getEmID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

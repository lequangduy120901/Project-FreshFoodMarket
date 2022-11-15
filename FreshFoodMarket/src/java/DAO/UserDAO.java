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
import util.Account;
import util.Customer;
import util.Employee;

/**
 *
 * @author HuyCQ
 */
public class UserDAO {

    public List<Customer> getAllCustomer() {
        String sql = "select c.*, acc.[role] from [dbo].[customer] as c join account as acc \n"
                + "on c.accID = acc.accID \n"
                + "order by cusID desc";
        List<Customer> listCus = new ArrayList<>();
        try {
            Connection connection = new DBContext().connection;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listCus.add(new Customer(rs.getString(1),
                        new Account(rs.getInt(2), rs.getInt(11)),
                        rs.getString(3),
                        rs.getBoolean(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getDate(10)));
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            //
        }

        return listCus;
    }

    public List<Employee> getAllAdmin() {
        String sql = "select e.*, a.[role] from employee as e join account as a\n"
                + "on e.accID = a.accID\n"
                + "where a.[role] = 2\n"
                + "order by emID desc";
        List<Employee> listAdmin = new ArrayList<>();
        try {
            Connection connection = new DBContext().connection;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listAdmin.add(new Employee(rs.getString(1),
                        new Account(rs.getInt(2), rs.getInt(9)),
                        rs.getString(3),
                        rs.getBoolean(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)));
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            //
        }
        return listAdmin;
    }

    public List<Employee> getAllEmpolyee() {
        String sql = "select e.*, a.[role] from employee as e join account as a\n"
                + "on e.accID = a.accID\n"
                + "order by emID desc";
        List<Employee> listAdmin = new ArrayList<>();
        try {
            Connection connection = new DBContext().connection;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listAdmin.add(new Employee(rs.getString(1),
                        new Account(rs.getInt(2), rs.getInt(9)),
                        rs.getString(3),
                        rs.getBoolean(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)));
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            //
        }
        return listAdmin;
    }


}

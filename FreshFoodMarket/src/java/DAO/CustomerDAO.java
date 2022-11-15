/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Account;
import util.Customer;

/**
 *
 * @author Dinh Nam
 */
public class CustomerDAO {
//    -----------NamDD code in the part below-------------

//    -----------BaoLTT code in the part below------------
    public Customer getCusbyID(String cusID) {
        Customer x = null;
        AccountDAO daoA = new AccountDAO();
        String xSql = "select * from Customer where cusID = ?";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(xSql);
            ps.setString(1, cusID);
            ResultSet rs = ps.executeQuery();
            String cusName, email, Phone, Address, Image, updateBy;
            boolean gender;
            Date updateDate;

            if (rs.next()) {
                cusName = rs.getString("cusName");
                gender = rs.getBoolean("gender");
                email = rs.getString("email");
                Phone = rs.getString("cusPhone");
                Address = rs.getString("cusAddress");
                Image = rs.getString("cusImage");
                updateBy = rs.getString("updateBy");
                updateDate = rs.getDate("updateDate");
                Account a = daoA.getAccountbyID(rs.getInt("accID"));
                x = new Customer(cusID, a, cusName, gender, email, Phone, Address, Image, updateBy, updateDate);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }
    
     public int countCustomer(){
       int m = 0;
       String sql = "select count(cusID) from Customer";
        
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
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return m;
    }  
       
        public int countCustomerhasOrder(){
       int m = 0;
       String sql = "select count(distinct cusID) from [Order]";
        
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
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return m;
    }  
        
           public List<Customer> getAllCusbySearch(String xname){
        AccountDAO d = new AccountDAO();
        List<Customer> list = new ArrayList<>();
        String sql = "select * from Customer where cusName like ? or email like ? or cusPhone like ?";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%"+xname+"%");
            ps.setString(2, "%"+xname+"%");
            ps.setString(3, "%"+xname+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account acc = d.getAccountbyID(rs.getInt(2));
                list.add(new Customer(rs.getString(1), acc, rs.getString(3),
                        rs.getBoolean(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getDate(10)));
            }
            ps.close();
            rs.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

           public void deleteCus(String cusID){
          String sql = "delete from Customer where cusID=?";
          try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cusID);
            ps.executeUpdate();
            ps.close();
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
           }
    public static void main(String[] args) {
        CustomerDAO daoCus = new CustomerDAO();
        daoCus.deleteCus("56");
       List list = daoCus.getAllCus();
        System.out.println(list);
    }

//    -----------DuyLQ code in the part below-------------
    public void insertCustomer(Customer x) {
        String xSql = "insert into Customer (cusID, accID, cusName, gender, email, cusPhone, cusAddress, cusImage, updateBy) values (?,?,?,?,?,?,?,?,?)";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = null;
            ps = con.prepareStatement(xSql);
            int count = countCustomer();
            ps.setString(1, x.getCusID());
            ps.setInt(2, x.getAccount().getAccID());
            ps.setString(3, x.getCusName());
            ps.setBoolean(4, x.isGender());
            ps.setString(5, x.getEmail());
            ps.setString(6, x.getCusPhone());
            ps.setString(7, x.getCusAddress());
            ps.setString(8, x.getCusImage());
            ps.setString(9, x.getUpddateBy());
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    public boolean checkMailExist(String mail) {
        String sql = "select * from Customer where email = ?";
        boolean check = true;
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, mail);
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
    
//    public static void main(String[] args) {
//        AccountDAO b = new AccountDAO();
//        Account acc = b.getAccount("kien", "11111111");
//        CustomerDAO a = new CustomerDAO();
//        boolean gender = true;
////         System.out.println(a.getAccount("leduy120901", "12345678"));
//         Customer c = new Customer(0,acc,"kien",gender,"kien112222@gmail","17237193713","hanoi","adhadhkak","ahdadhka",new Date(2022, 03, 04));
//         a.insertCustomer(c);
//    }

//    -----------HuyCQ code in the part below-------------
    public List<Customer> getCusByKey(String key) {
        AccountDAO d = new AccountDAO();
        String sql = "select * from Customer\n"
                + "where cusName like ? ";
        List<Customer> cus = new ArrayList<>();
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account acc = d.getAccountbyID(rs.getInt(2));
                cus.add(new Customer(rs.getString(1), acc, rs.getString(3),
                        rs.getBoolean(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getDate(10)));
            }
            ps.close();
            rs.close();
            con.close();
        } catch (Exception e) {
        }
        return cus;
    }

    public int getNumOrderOfCus(String cusID) {
        int count = 0;
        String sql = "select COUNT(b.orderID) from [Order] b\n"
                + "inner join Customer c\n"
                + "on c.cusID = b.cusID\n"
                + "where c.cusID = ?  and MONTH(b.dateCreate) = ?";
        try {
            LocalDate date = LocalDate.now();
            int month = date.getMonthValue();
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cusID);
            ps.setInt(2, month);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("");
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
        }
        return count;
    }

    public void updateCus(String image, String email, String phone, String address, String cusID, boolean gender) {
        String sql = "update Customer\n"
                + "set cusImage=?,email = ?, cusPhone = ?, cusAddress = ?,gender = ?\n"
                + "where cusID = ?";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, image);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setBoolean(5, gender);
            ps.setString(6, cusID);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
        }
        
    }
    
//    -----------BaoNN code in the part below-------------
    public List<Customer> getAllCus(){
        AccountDAO d = new AccountDAO();
        List<Customer> list = new ArrayList<>();
        String sql = "select * from Customer";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account acc = d.getAccountbyID(rs.getInt(2));
                list.add(new Customer(rs.getString(1), acc, rs.getString(3),
                        rs.getBoolean(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getDate(10)));
            }
            ps.close();
            rs.close();
            con.close();
        } catch (Exception e) {
        }
        return list;
    }
    public int getCusByMonth(int month){
        String sql = "select count(cusID) from Customer where MONTH(updateDate) <= ?";
        int cus = 0;
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, month);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cus = rs.getInt("");
            }
            ps.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
        }
        return cus;
    }
    
//    -----------QuangTM code in the part below-----------    


//    -----------QuangTM code in the part below----------- 
    public Customer getCustomerByAccID(int accID) {
        Customer customer = new Customer();
        try {
            String sql = "SELECT [cusID]\n"
                    + "      ,[accID]\n"
                    + "      ,[cusName]\n"
                    + "      ,[gender]\n"
                    + "      ,[email]\n"
                    + "      ,[cusPhone]\n"
                    + "      ,[cusAddress]\n"
                    + "      ,[cusImage]\n"
                    + "      ,[updateBy]\n"
                    + "      ,[updateDate]\n"
                    + "  FROM [Customer]\n"
                    + "  WHERE [accID] = ?";
            Connection con = new DBContext().connection;
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, accID);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                customer.setCusID(rs.getString("cusID"));
                Account account = new Account();
                account.setAccID(accID);
                customer.setAccount(account);
                customer.setCusName(rs.getString("cusName"));
                customer.setGender(rs.getBoolean("gender"));
                customer.setEmail(rs.getString("email"));
                customer.setCusPhone(rs.getString("cusPhone"));
                customer.setCusAddress(rs.getString("cusAddress"));
                customer.setCusImage(rs.getString("cusImage"));
                customer.setUpddateBy(rs.getString("updateBy"));
                customer.setUpdateDate(rs.getDate("updateDate"));
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }
    
    public void update(Customer model) {
        try {
            String sql = "UPDATE [Customer]\n"
                    + "   SET [cusName] = ?\n"
                    + "      ,[gender] = ?\n"
                    + "      ,[cusPhone] = ?\n"
                    + "      ,[cusAddress] = ?\n"
                    + "      ,[cusImage] = ?\n"
                    + "      ,[updateBy] = ?\n"
                    + "      ,[updateDate] = ?\n"
                    + " WHERE [cusID] = ?";
            Connection con = new DBContext().connection;
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, model.getCusName());
            stm.setBoolean(2, model.isGender());
            stm.setString(3, model.getCusPhone());
            stm.setString(4, model.getCusAddress());
            stm.setString(5, model.getCusImage());
            stm.setString(6, model.getUpddateBy());
            stm.setDate(7, (Date) model.getUpdateDate());
            stm.setString(8, model.getCusID());
            stm.executeUpdate();
            stm.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Order;
import util.Customer;

/**
 *
 * @author Dinh Nam
 */
public class OrderDAO {
//    -----------NamDD code in the part below-------------

    public boolean insertNewOrder(Order x) {
        String xSql = "insert into [Order](orderID, cusID, cusName, email, cusPhone, cusAddress, total, status) values (?,?,?,?,?,?,?,?)";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(xSql);
            ps.setString(1, x.getOrderID());
            ps.setString(2, x.getCustomer().getCusID());
            ps.setString(3, x.getCustomer().getCusName());
            ps.setString(4, x.getCustomer().getEmail());
            ps.setString(5, x.getCustomer().getCusPhone());
            ps.setString(6, x.getCustomer().getCusAddress());
            ps.setDouble(7, x.getTotal());
            ps.setInt(8, x.getStatus());
            ps.executeUpdate();
            ps.close();
            con.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public Order getLastestOrder(String cusID) {
        String xSql = "select top (1) * from [Order] where cusID = (?) order by orderID desc";
        Order order = null;
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(xSql);
            ps.setString(1, cusID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String orderID = rs.getString("orderID");
                String cusName = rs.getString("cusName");
                String email = rs.getString("email");
                String cusPhone = rs.getString("cusPhone");
                String cusAddress = rs.getString("cusAddress");
                Date dateCreate = rs.getDate("dateCreate");
                double total = rs.getDouble("total");
                int status = rs.getInt("status");
                Customer cus = new Customer(cusID, cusName, email, cusPhone, cusAddress);
                order = new Order(orderID, cus, dateCreate, total, status);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return order;
    }

    public void updateOrderStatus(Order order, int status) {
        String xSql = "update [Order] set status = ? where orderID = ?";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(xSql);
            ps.setInt(1, status);
            ps.setString(2, order.getOrderID());
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public int countOrder(){
        int m = 0;
        String sql = "select count(orderID) from [Order]" ;
        
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

//    -----------BaoLTT code in the part below------------
      public int countNumbersOfOrder(String xdateOrder1, String xdateOrder2){
        int m = 0;
        String sql = "select count(orderID) from [Order] where dateCreate between '" + xdateOrder1 + "' and '" + xdateOrder2 + " 23:59:59'" ;
        
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
    
    
       public int countSuccessfulOrder(String xdateOrder1, String xdateOrder2){
        int m = 0;
        String sql = "select count(orderID) from [Order] where status = 3 and dateCreate between '" + xdateOrder1 + "' and '" + xdateOrder2 + " 23:59:59'";
        
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
       
       
        public int countFailedOrder(String xdateOrder1, String xdateOrder2){
        int m = 0;
        String sql = "select count(orderID) from [Order] where status = -1 and dateCreate between '" + xdateOrder1 + "' and '" + xdateOrder2 + " 23:59:59'";
        
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
    
        public List<Order> getProcessOrderList() {
           String sql = "select * from [Order] where status <=0 and status !=-1";
           List list = new ArrayList();
           try {
            Connection conn = new DBContext().connection;
            PreparedStatement prs = conn.prepareStatement(sql);
            ResultSet res = prs.executeQuery();
            while (res.next()) {
                String orderID = res.getString(1);
                String cusID = res.getString(2);
                CustomerDAO daoC = new CustomerDAO();
                Customer cus = daoC.getCusbyID(cusID);
//                String cusName = res.getString(3);
//                String email = res.getString(4);
//                String cusAddress = res.getString(6);
                java.util.Date dateCreate = res.getDate(7);
//               String xDate = res.getString(6);
//                Date dateCreate =  new SimpleDateFormat("yyyy-MM-dd").parse(xDate);
                double total = res.getDouble(8);
                int status = res.getInt(9);
                Order x = new Order(orderID, cus ,dateCreate, total, status);
                list.add(x);
            }
            res.close();
            prs.close();
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    
//    -----------DuyLQ code in the part below-------------
        public List<Order> getOrderList() {
            String sql = "select * from [Order]";
            List<Order> list = new ArrayList<>();
            try {
                Connection con = new DBContext().connection;
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                CustomerDAO dbCus = new CustomerDAO();
                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderID(rs.getString("orderID"));
                    Customer customer = dbCus.getCusbyID(rs.getString("cusID"));
                    order.setCustomer(customer);
                    order.setDateCreate(rs.getDate("dateCreate"));
                    order.setTotal(rs.getDouble("total"));
                    order.setStatus(rs.getInt("status"));
                    list.add(order);
                }
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
            }
            return list;
        }
        
        public List<Order> getListOrderByStatus(String key, int status, String from, String to) {
            List<Order> list = new ArrayList<>();
            String sql = "select * from [Order] where cusName like ? and status = ? and [Order].dateCreate between '"+ from +"' and '" + to + " 23:59:59'";
            try {
                Connection con = new DBContext().connection;
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, "%" + key + "%");
                ps.setInt(2, status);
                ResultSet rs = ps.executeQuery();
                CustomerDAO dbCus = new CustomerDAO();
                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderID(rs.getString("orderID"));
                    Customer customer = dbCus.getCusbyID(rs.getString("cusID"));
                    order.setCustomer(customer);
                    order.setDateCreate(rs.getDate("dateCreate"));
                    order.setTotal(rs.getDouble("total"));
                    order.setStatus(rs.getInt("status"));
                    list.add(order);
                }
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
            }
            return list; 
        }
        
        public List<Order> getListOrderByFilter(String key, String from, String to) {
            List<Order> list = new ArrayList<>();
            String sql = "select * from [Order] where cusName like ? and [Order].dateCreate between '"+ from +"' and '" + to + " 23:59:59'";
            try {
                Connection con = new DBContext().connection;
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, "%" + key + "%");
                ResultSet rs = ps.executeQuery();
                CustomerDAO dbCus = new CustomerDAO();
                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderID(rs.getString("orderID"));
                    Customer customer = dbCus.getCusbyID(rs.getString("cusID"));
                    order.setCustomer(customer);
                    order.setDateCreate(rs.getDate("dateCreate"));
                    order.setTotal(rs.getDouble("total"));
                    order.setStatus(rs.getInt("status"));
                    list.add(order);
                }
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
            }
            return list;
        }
        
//    -----------HuyCQ code in the part below-------------
//    -----------BaoNN code in the part below-------------

    public int getAllOrder(int month) {
        int count =0;
        String sql = "select COUNT(orderID )from [Order] where MONTH(dateCreate) = ? ";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, month);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                count = rs.getInt("");
            }
            ps.close();
            rs.close();
            con.close();
        } catch (Exception e) {
        }
        return count;
    }

    public List<Order> getAllOrderSuccess(int month) {
        CustomerDAO cus = new CustomerDAO();
        List<Order> listB = new ArrayList<>();
        String sql = "select * from [Order] where status >= 0 and MONTH(dateCreate) = ?";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, month);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = cus.getCusbyID(rs.getString("cusID"));
                listB.add(new Order(rs.getString("orderID"), customer,
                        rs.getDate("dateCreate"), sumOfOrderByIdSuc(rs.getString("orderID")),
                        rs.getInt("status")));
            }
            ps.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
        }
        return listB;
    }

    public double sumOfOrderByIdSuc(String idorder) {
        double sum = 0;
        String sql = "select SUM(bd.total) from [Order] b\n"
                + "inner join OrderDetail bd \n"
                + "on b.orderID =bd.orderID\n"
                + "where bd.orderID = ? and b.status >= 0";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idorder);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sum = rs.getDouble("");
            }
            ps.close();
            rs.close();
            con.close();
        } catch (Exception e) {
        }
        return sum;
    }

    public List<Order> getAllOrderWait(int month) {
        CustomerDAO cus = new CustomerDAO();
        List<Order> listB = new ArrayList<>();
        String sql = "select * from [Order] where status < 0 and MONTH(dateCreate) = ?";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, month);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = cus.getCusbyID(rs.getString("cusID"));
                listB.add(new Order(rs.getString("orderID"), customer,
                        rs.getDate("dateCreate"), sumOfOrderByIdWait(rs.getString("orderID")),
                        rs.getInt("status")));
            }
            ps.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
        }
        return listB;
    }

    public double sumOfOrderByIdWait(String orderID) {
        double sum = 0;
        String sql = "select SUM(bd.total) from [Order] b\n"
                + "inner join OrderDetail bd \n"
                + "on b.orderID =bd.orderID\n"
                + "where bd.orderID = ? and b.status < 0";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, orderID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sum = rs.getDouble("");
            }
            ps.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
        }
        return sum;
    }

    public int sumOfProduct(int month) {
        int sum = 0;
        String sql = "select SUM(bd.quantity) from OrderDetail bd\n"
                + "inner join [Order] b \n"
                + "on b.orderID = bd.orderID\n"
                + "where b.status >= 0 and MONTH(b.dateCreate) = ?";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, month);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sum = rs.getInt("");
            }
            ps.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
        }
        return sum;
    }
 
    public int sumOfProductByCateID(int month, String idCate) {
        int sum = 0;
        String sql = "select SUM(bd.quantity) from [Order]  b\n"
                + "inner join OrderDetail bd \n"
                + "on b.orderID = bd.orderID\n"
                + "inner join Product p \n"
                + "on bd.proID = p.proID\n"
                + "inner join Category c\n"
                + "on c.cateID = p.cateID\n"
                + "where b.status >= 0 and MONTH(b.dateCreate) = ? and c.cateID = ?";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, month);
            ps.setString(2, idCate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sum = rs.getInt("");
            }
            ps.close();
            rs.close();
            con.close();
        } catch (Exception e) {
        }
        return sum;
    }

    public double SumPriceOrder(int month) {
        double sum = 0;
        String sql = "select SUM(bd.total) from [Order] b\n"
                + "inner join OrderDetail bd\n"
                + "on b.orderID = bd.orderID\n"
                + "where MONTH(b.dateCreate) = ? and b.status >= 0";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, month);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sum = rs.getDouble("");
            }
            ps.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
        }
        return sum;
    }

//    -----------QuangTM code in the part below-----------
    public Order getOrderByOrderID(String orderID) {
        Order order = new Order();
        CustomerDAO dbCus = new CustomerDAO();
        try {
            String sql = "SELECT [orderID]\n"
                    + "      ,[cusID]\n"
                    + "      ,[cusName]\n"
                    + "      ,[email]\n"
                    + "      ,[cusPhone]\n"
                    + "      ,[cusAddress]\n"
                    + "      ,[dateCreate]\n"
                    + "      ,[total]\n"
                    + "      ,[status]\n"
                    + "  FROM [Order]\n"
                    + "  WHERE [orderID] = ?";
            Connection con = new DBContext().connection;
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, orderID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                order.setOrderID(rs.getString("orderID"));
                Customer customer = dbCus.getCusbyID(rs.getString("cusID"));
                order.setCustomer(customer);
                order.setDateCreate(rs.getDate("dateCreate"));
                order.setTotal(rs.getDouble("total"));
                order.setStatus(rs.getInt("status"));
            }
            stm.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return order;
    }

    public ArrayList<Order> getOrdersByCusID(String cusID) {
        ArrayList<Order> orders = new ArrayList<>();

        CustomerDAO dbCus = new CustomerDAO();
        try {
            String sql = "SELECT [orderID]\n"
                    + "      ,[cusID]\n"
                    + "      ,[cusName]\n"
                    + "      ,[email]\n"
                    + "      ,[cusPhone]\n"
                    + "      ,[cusAddress]\n"
                    + "      ,[dateCreate]\n"
                    + "      ,[total]\n"
                    + "      ,[status]\n"
                    + "  FROM [Order]\n"
                    + "  WHERE[cusID] = ?\n"
                    + "  ORDER BY [dateCreate] DESC";
            Connection con = new DBContext().connection;
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, cusID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrderID(rs.getString("orderID"));
                Customer customer = dbCus.getCusbyID(rs.getString("cusID"));
                order.setCustomer(customer);
                order.setDateCreate(rs.getDate("dateCreate"));
                order.setTotal(rs.getDouble("total"));
                order.setStatus(rs.getInt("status"));
                orders.add(order);
            }
            stm.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    public ArrayList<Order> getListOrdersByPage(ArrayList<Order> orders, int start, int end) {
        ArrayList<Order> ordersPerPage = new ArrayList<>();
        for (int i = start; i < end; i++) {
            ordersPerPage.add(orders.get(i));
        }
        return ordersPerPage;
    }
    
    public void updateStatus(String orderID, int status) {
        try {
            String sql = "UPDATE [dbo].[Order]\n"
                    + "   SET [status] = ?\n"
                    + " WHERE [orderID] = ?";
            PreparedStatement stm = new DBContext().connection.prepareStatement(sql);
            stm.setInt(1, status);
            stm.setString(2, orderID);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

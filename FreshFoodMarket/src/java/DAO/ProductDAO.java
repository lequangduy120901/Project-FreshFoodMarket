/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import java.util.List;
import util.Category;
import util.Product;

/**
 *
 * @author Dinh Nam
 */
public class ProductDAO {
//    -----------NamDD code in the part below-------------

    public List<Product> getFeatureProductList() {
        List<Product> t = new ArrayList<>();
        String sql = "select top (12) un.proID,\n"
                + "sum(un.q) \n"
                + "as qtt \n"
                + "from\n"
                + "(select bd.proID,\n"
                + "sum(bd.quantity)\n"
                + "as q\n"
                + "from Product as p \n"
                + "join OrderDetail as bd \n"
                + "on p.proID = bd.proID \n"
                + "group by bd.proID\n"
                + "union\n"
                + "select proID,\n"
                + "q = 0 from Product)\n"
                + "as un\n"
                + "group by un.proID\n"
                + "order by qtt desc";
        try {
            Connection conn = new DBContext().connection;
            PreparedStatement prs = conn.prepareStatement(sql);
            ResultSet res = prs.executeQuery();
            String xProID;
            ProductDAO dao = new ProductDAO();
            Product x;
            while (res.next()) {
                xProID = res.getString("proID");
                x = dao.getProductbyID(xProID);
                t.add(x);
            }
            res.close();
            prs.close();
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return (t);
    }
    
    public boolean updateProductQuantity(String proID, int quantity) {
        String sql = "update Product set quantity = (?) where proID = (?)";
        try {
            Connection conn = new DBContext().connection;
            PreparedStatement prs = conn.prepareStatement(sql);
            prs.setInt(1, quantity);
            prs.setString(2, proID);
            prs.executeUpdate();
            prs.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public List<Product> getProductByCate(String cateID) {
        List<Product> list = new ArrayList<>();
        CategoryDAO cateDao = new CategoryDAO();
        String sql = "select * from Product where cateID = ?";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cateID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString(1),
                        cateDao.getCategoryByID(rs.getString(2)),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getDate(10)));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
    
    public List<Product> getProductBySearchAndCate(String key, String cateID) {
        List<Product> list = new ArrayList<>();
        CategoryDAO cateDao = new CategoryDAO();
        String sql = "select * from Product where proName like ? and cateID like ?";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ps.setString(2, "%" + cateID + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString(1),
                        cateDao.getCategoryByID(rs.getString(2)),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getDate(10)));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
    
    public boolean insertProduct(Product product){
        String sql = "insert into Product"
                + "(proID, cateID, proName, price, type, quantity, image, description, rated) "
                + "values (?,?,?,?,?,?,?,?,?)";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, product.getProID());
            ps.setString(2, product.getCategory().getCateID());
            ps.setString(3, product.getProName());
            ps.setDouble(4, product.getPrice());
            ps.setString(5, product.getType());
            ps.setInt(6, product.getQuantity());
            ps.setString(7, product.getImage());
            ps.setString(8, product.getDescription());
            ps.setInt(9, product.getRated());
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void updateProduct(Product product) {
        String sql = "update Product set cateID=?, proName=?, price=?, type=?, quantity=?, image=?, description=? where proID=?";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, product.getCategory().getCateID());
            ps.setString(2, product.getProName());
            ps.setDouble(3, product.getPrice());
            ps.setString(4, product.getType());
            ps.setInt(5, product.getQuantity());
            ps.setString(6, product.getImage());
            ps.setString(7, product.getDescription());
            ps.setString(8, product.getProID());
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public boolean deleteProduct(String proID) {
        String sql = "delete from Product where proID=?";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, proID);
            ps.executeUpdate();
            ps.close();
            con.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
//    -----------BaoLTT code in the part below------------
    public Product getProductbyID(String proID) {
        Product x = null;
        String xSql = "select * from Product where proID = ?";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(xSql);
            ps.setString(1, proID);
            ResultSet rs = ps.executeQuery();
            String xProID, xCateID, xProName, xImage, xDescription, xType;
            double xPrice;
            int xQuantity, xRated;
            Date xUpdateDate;

            CategoryDAO cateDao = new CategoryDAO();
            Category category = null;
            while (rs.next()) {
                xProID = rs.getString("proID");
                xCateID = rs.getString("cateID");
                xProName = rs.getString("proName");
                xPrice = rs.getDouble("price");
                xType = rs.getString("type");
                xQuantity = rs.getInt("quantity");
                xImage = rs.getString("image");
                xDescription = rs.getString("description");
                xRated = rs.getInt("rated");
                xUpdateDate = rs.getDate("updatedate");
                category = cateDao.getCategoryByID(xCateID);
                x = new Product(xProID, category, xProName, xPrice, xType, xQuantity, xImage, xDescription, xRated, xUpdateDate);

            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }
    
      public List<Product> getProductList() {
        List<Product> list =  new ArrayList<>();
        String xSql = "select * from Product";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(xSql);
            ResultSet rs = ps.executeQuery();
            String xProID, xCateID, xProName, xImage, xDescription, xType ;
            double xPrice;
            int xQuantity, xrated ;
            java.util.Date xUpdateDate;
            Product x;
            CategoryDAO cateDao = new CategoryDAO();
            Category category = null;
            while (rs.next()) {
                xProID = rs.getString("proID");
                xCateID = rs.getString("cateID");
                xProName = rs.getString("proName");
                xPrice = rs.getDouble("price");
                xType = rs.getString("type");
                xQuantity = rs.getInt("quantity");
                xImage = rs.getString("image");
                xDescription = rs.getString("description");
                xrated = rs.getInt("rated");
                xUpdateDate = rs.getDate("updatedate");
                category = cateDao.getCategoryByID(xCateID);
                x = new Product(xProID, category, xProName, xPrice, xType, xQuantity, xImage, xDescription,xrated , xUpdateDate);
                list.add(x);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (list);
    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        Product t = dao.getProductbyID("1");
        System.out.println(t);
    }
//    -----------DuyLQ code in the part below-------------
//    -----------HuyCQ code in the part below-------------    

    public List<Product> sortDESCByCate(String cateID) {
        String sql = "select * from product where quantity > 0 and cateID = ? \n"
                + "order by price desc";
        List<Product> list = new ArrayList<>();
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cateID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString(1),
                        new Category(rs.getString(2)),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getDate(10)));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
        }

        return list;
    }

    public List<Product> sortASCByCate(String cateID) {
        String sql = "select * from product where quantity > 0 and cateID = ? \n"
                + "order by price asc";
        List<Product> list = new ArrayList<>();
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cateID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString(1),
                        new Category(rs.getString(2)),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getDate(10)));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
        }

        return list;
    }

    public List<Product> sortASCListAll() {
        String sql = "select * from Product where quantity > 0\n"
                + "order by price asc";
        List<Product> list = new ArrayList<>();
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString(1),
                        new Category(rs.getString(2)),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getDate(10)));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
        }

        return list;
    }

    public List<Product> sortDESCListAll() {
        String sql = "select * from Product where quantity > 0 \n"
                + "order by price desc";
        List<Product> list = new ArrayList<>();
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString(1),
                        new Category(rs.getString(2)),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getDate(10)));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
        }

        return list;
    }

    public List<Product> getListByRangePrice(int from, int to) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from product\n"
                + "where price >= ? and price <= ? and quantity > 0";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, from);
            ps.setInt(2, to);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString(1),
                        new Category(rs.getString(2)),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getDate(10)));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getListByFromPrice(int from) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from product\n"
                + "where price >= ? and quantity > 0";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, from);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString(1),
                        new Category(rs.getString(2)),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getDate(10)));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
        }
        return list;
    }

//    -----------BaoNN code in the part below-------------
    public List<Product> getAllProduct() {
        List<Product> listPro = new ArrayList<>();
        String sql = "select * from Product where quantity > 0 order by updateDate desc";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listPro.add(new Product(rs.getString(1),
                        new Category(rs.getString(2)),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getDate(10)));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
        }
        return listPro;

    }

    public List<Product> getListByPage(List<Product> listPro, int start, int end) {
        List<Product> list = new ArrayList<>();
        for (int i = start; i < end; i++) {
            list.add(listPro.get(i));
        }
        return list;

    }

    public List<Product> getListByCate(String cId) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Product\n"
                + "where cateID = ? and quantity > 0 order by updateDate desc";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString(1),
                        new Category(rs.getString(2)),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getDate(10)));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
        }

        return list;
    }

    public List<Product> getListBykey(String key) {

        List<Product> list = new ArrayList<>();
        String sql = "select * from Product\n"
                + "where proName like ? and quantity > 0";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString(1),
                        new Category(rs.getString(2)),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getDate(10)));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
        }

        return list;

    }
    
    public int averageRating(String proID) {
        String sql = "SELECT AVG(f.rated)\n"
                + "FROM Feedback f\n"
                + "where f.proID = ?";
        int avg = 0;
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, proID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                avg = rs.getInt("");
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
        }
        return avg;
    }

    public void addRatedToProduct(int rated, String proID) {
        String sql = "UPDATE Product\n"
                + "SET rated = ?\n"
                + "WHERE proID = ?";
        try {
            Connection con = new DBContext().connection;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, rated);
            ps.setString(2, proID);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
        }
    }

//    -----------QuangTM code in the part below-----------   
//    public Category getCategoryByID(String cateID) {
//        Category cate = new Category();
//        try {
//            String sql = "SELECT [cateID]\n"
//                    + "      ,[cateName]\n"
//                    + "  FROM [Category]\n"
//                    + "  WHERE [cateID] = ?";
//            PreparedStatement stm = connection.prepareStatement(sql);
//            stm.setString(1, cateID);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                cate.setCateID(rs.getString("cateID"));
//                cate.setCateName(rs.getString("cateName"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return cate;
//    }
    public Product getProductByProid(String proID) {
        Product product = new Product();
        CategoryDAO cateDao = new CategoryDAO();
        try {
            String sql = "SELECT [proID]\n"
                    + "      ,[cateID]\n"
                    + "      ,[proName]\n"
                    + "      ,[price]\n"
                    + "      ,[type]\n"
                    + "      ,[quantity]\n"
                    + "      ,[image]\n"
                    + "      ,[description]\n"
                    + "      ,[rated]\n"
                    + "      ,[updateDate]\n"
                    + "  FROM [Product]\n"
                    + "  WHERE [proID] = ?";
            Connection con = new DBContext().connection;
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, proID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Category category = cateDao.getCategoryByID(rs.getString("cateID"));
                product.setCategory(category);
                product.setProID(rs.getString("proID"));
                product.setProName(rs.getString("proName"));
                product.setPrice(rs.getFloat("price"));
                product.setType(rs.getString("type"));
                product.setQuantity(rs.getInt("quantity"));
                product.setImage(rs.getString("image"));
                product.setDescription(rs.getString("description"));
                product.setRated(rs.getInt("rated"));
                product.setUpdateDate(rs.getDate("updateDate"));
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }

    public ArrayList<Product> getRelatedProductByCategory(String cateID, String proID) {
        ArrayList<Product> products = new ArrayList<>();
        CategoryDAO cateDao = new CategoryDAO();
        try {
            String sql = "SELECT TOP (4) [proID]\n"
                    + "      ,[cateID]\n"
                    + "      ,[proName]\n"
                    + "      ,[price]\n"
                    + "      ,[type]\n"
                    + "      ,[quantity]\n"
                    + "      ,[image]\n"
                    + "      ,[description]\n"
                    + "      ,[rated]\n"
                    + "      ,[updateDate]\n"
                    + "  FROM [Product]\n"
                    + "  WHERE [cateID] = ? AND [proID] != ?\n"
                    + "  ORDER BY NEWID()";
            Connection con = new DBContext().connection;
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, cateID);
            stm.setString(2, proID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                Category category = cateDao.getCategoryByID(rs.getString("cateID"));
                product.setCategory(category);
                product.setProID(rs.getString("proID"));
                product.setProName(rs.getString("proName"));
                product.setPrice(rs.getFloat("price"));
                product.setType(rs.getString("type"));
                product.setQuantity(rs.getInt("quantity"));
                product.setImage(rs.getString("image"));
                product.setDescription(rs.getString("description"));
                product.setRated(rs.getInt("rated"));
                product.setUpdateDate(rs.getDate("updateDate"));
                products.add(product);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

}

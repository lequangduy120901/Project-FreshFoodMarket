/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ConnectDAO {
    Connection conn = null;

    public ConnectDAO(String URL, String userName, String password) {
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn=DriverManager.getConnection(URL, userName, password);
            System.out.println("connected");
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
           ex.printStackTrace();
        }

    }
    public ConnectDAO(){
        this("jdbc:sqlserver://localhost:1433;databaseName=FreshFoodMarket","sa","sa");
    }
    public ResultSet getData(String sql){
        ResultSet rs=null;
        try {
            Statement state=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs=state.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public static void main(String[] args) {
        new ConnectDAO();
    }
}

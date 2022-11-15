/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.extra;

import DAO.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.extra.DVHC;

/**
 *
 * @author zedqu
 */
public class DVHCDAO{

    public DVHC getDvhcByMaDvhc(String maDvhc) {
        DVHC dvhc = new DVHC();
        try {
            String sql = "SELECT [maDVHC]\n"
                    + "      ,[ten]\n"
                    + "      ,[capTren]\n"
                    + "      ,[cap]\n"
                    + "  FROM [DVHC]\n"
                    + "  Where maDVHC = ?";
            PreparedStatement stm = new DBContext().connection.prepareStatement(sql);
            stm.setString(1, maDvhc);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                dvhc.setMaDVHC(rs.getString("maDVHC"));
                dvhc.setTen(rs.getString("ten"));
                dvhc.setCap(rs.getString("cap"));
                if (rs.getString("capTren") != null) {
                    dvhc.setCapTren(rs.getString("capTren"));
                } else {
                    dvhc.setCapTren("Khong Co");
                }
            }
            stm.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DVHCDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dvhc;
    }
    public DVHC getDvhcByTen(String ten) {
        DVHC dvhc = new DVHC();
        try {
            String sql = "SELECT [maDVHC]\n"
                    + "      ,[ten]\n"
                    + "      ,[capTren]\n"
                    + "      ,[cap]\n"
                    + "  FROM [DVHC]\n"
                    + "  Where ten = N'"
                    + ten
                    +"'";
            PreparedStatement stm = new DBContext().connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                dvhc.setMaDVHC(rs.getString("maDVHC").trim());
                dvhc.setTen(rs.getString("ten"));
                dvhc.setCap(rs.getString("cap"));
                if (rs.getString("capTren") != null) {
                    dvhc.setCapTren(rs.getString("capTren"));
                } else {
                    dvhc.setCapTren("Khong Co");
                }
            }
            stm.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DVHCDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dvhc;
    }

    public ArrayList<DVHC> getListDvhcCapTinh() {

        ArrayList<DVHC> capTinhs = new ArrayList<>();
        String cap = "TINH";
        try {
            String sql = "SELECT [maDVHC]\n"
                    + "      ,[ten]\n"
                    + "      ,[capTren]\n"
                    + "      ,[cap]\n"
                    + "  FROM [DVHC]\n"
                    + "  Where cap = ?\n"
                    + "  Order By ten ASC";
            PreparedStatement stm = new DBContext().connection.prepareStatement(sql);
            stm.setString(1, cap);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                DVHC dvhc = new DVHC();
                dvhc.setMaDVHC(rs.getString("maDVHC").trim());
                dvhc.setTen(rs.getString("ten"));
                dvhc.setCapTren("Khong Co");
                dvhc.setCap(rs.getString("cap"));
                capTinhs.add(dvhc);
            }
            stm.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DVHCDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return capTinhs;
    }

    public ArrayList<DVHC> getListDvhcCapHuyen(String tinh) {

        ArrayList<DVHC> CapHuyens = new ArrayList<>();
        String cap = "HUYEN";
        try {
            String sql = "SELECT [maDVHC]\n"
                    + "      ,[ten]\n"
                    + "      ,[capTren]\n"
                    + "      ,[cap]\n"
                    + "  FROM [DVHC]\n"
                    + "  WHERE cap = ? AND capTren = ?";
            PreparedStatement stm = new DBContext().connection.prepareStatement(sql);
            stm.setString(1, cap);
            stm.setString(2, tinh);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                DVHC dvhc = new DVHC();
                dvhc.setMaDVHC(rs.getString("maDVHC").trim());
                dvhc.setTen(rs.getString("ten"));
                dvhc.setCapTren(rs.getString("capTren"));
                dvhc.setCap(rs.getString("cap"));
                CapHuyens.add(dvhc);
            }
            stm.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DVHCDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CapHuyens;
    }

    public ArrayList<DVHC> getListDvhcCapXa(String huyen) {

        ArrayList<DVHC> CapXas = new ArrayList<>();
        String cap = "XA";
        try {
            String sql = "SELECT [maDVHC]\n"
                    + "      ,[ten]\n"
                    + "      ,[capTren]\n"
                    + "      ,[cap]\n"
                    + "  FROM [DVHC]\n"
                    + "  WHERE cap = ? AND capTren = ?";
            PreparedStatement stm = new DBContext().connection.prepareStatement(sql);
            stm.setString(1, cap);
            stm.setString(2, huyen);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                DVHC dvhc = new DVHC();
                dvhc.setMaDVHC(rs.getString("maDVHC").trim());
                dvhc.setTen(rs.getString("ten"));
                dvhc.setCapTren(rs.getString("capTren"));
                dvhc.setCap(rs.getString("cap"));
                CapXas.add(dvhc);
            }
            stm.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DVHCDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CapXas;
    }

    public static void main(String[] args) {
        DVHCDAO dvhcdao = new DVHCDAO();
        DVHC dz = dvhcdao.getDvhcByTen("Thành phố Đà Nẵng");
        
                System.out.println(dz);
        
    }
}

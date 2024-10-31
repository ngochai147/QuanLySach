/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.ChiTietKhoHang;
import entity.KhoHang;
import entity.Sach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author The Bao
 */
public class ChiTietKhoHang_DAO {
    private final List<ChiTietKhoHang> ct_khoHang;

    public ChiTietKhoHang_DAO() {
        ct_khoHang = new ArrayList<>();
    }

    public ChiTietKhoHang_DAO(List<ChiTietKhoHang> ctKhoHang) {
        ct_khoHang = ctKhoHang;
    }
   
    public List<String> getMaChiTietKhoHang(){
        List<String> dsMaChiTietKhoHang = new ArrayList<>();
        String sql = "select maChiTietKhoHang from ChiTietKhoHang";
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                dsMaChiTietKhoHang.add(rs.getString("maChiTietKhoHang"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsMaChiTietKhoHang;
    }
    public boolean themChiTietKhoHang(ChiTietKhoHang chiTietKhoHang) throws SQLException {
        String sql = "insert into ChiTietKhoHang values (?,?,?,?)";
        if(ct_khoHang.contains(chiTietKhoHang)){
            return false;
        }else {
            Connection con = ConnectDB.getInstance().getConnection();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement(sql);
            stmt.setString(1, chiTietKhoHang.getMaChiTietKhoHang());
            stmt.setInt(4, chiTietKhoHang.getSoLuong());
            stmt.setString(2, chiTietKhoHang.getSach().getISBN());
            stmt.setString(3, chiTietKhoHang.getKhoHang().getMaKhoHang());
            stmt.executeUpdate();
            stmt.close();
            return ct_khoHang.add(chiTietKhoHang);
        }
    }
    public List<ChiTietKhoHang> getAllChiTietKhoHang(){
        List<ChiTietKhoHang> dsChiTietKhoHang = new ArrayList<>();
        String sql = "select * from ChiTietKhoHang";
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String maCTKhoHang = rs.getString(1);
                String ISBN = rs.getString(2);
                String maKhoHang = rs.getString(3);
                int soLuong = rs.getInt(4);
                dsChiTietKhoHang.add(new ChiTietKhoHang(maCTKhoHang,soLuong,new Sach(ISBN),new KhoHang(maKhoHang)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsChiTietKhoHang;
    }
    public String timTenKhoTheoMaSach(String maSach) throws SQLException {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        String tenKho = null;
        try {
            // Câu truy vấn JOIN để lấy tenKho từ bảng KhoHang
            String sql = "SELECT k.tenKho " +
                    "FROM ChiTietKhoHang c " +
                    "JOIN KhoHang k ON c.maKhoHang = k.maKhoHang " +
                    "WHERE c.ISBN = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maSach);

            // Thực thi truy vấn
            ResultSet rs = stmt.executeQuery();

            // Lấy tên kho từ kết quả
            if (rs.next()) {
                tenKho = rs.getString("tenKho"); // Lấy tên kho từ cột tenKho
            }
        } finally {
            if (stmt != null) {
                stmt.close(); // Đảm bảo đóng PreparedStatement
            }
        }
        return tenKho;
    }
    public boolean xoaChiTietKhoHang(String ISBN) throws SQLException {
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        stmt = con.prepareStatement("delete ChiTietKhoHang where ISBN = ?");
        stmt.setString(1, ISBN);
        stmt.executeUpdate();
        return true;
    }
    public boolean capNhatChiTietKhoHang(String ISBN,String maKho,int soLuong) throws SQLException {
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        stmt = con.prepareStatement("update ChiTietKhoHang " +
                "set soLuong=? where ISBN = ? and maKhoHang=?");
        stmt.setInt(1, soLuong);
        stmt.setString(2, ISBN);
        stmt.setString(3, maKho);
        stmt.executeUpdate();
        return true;
    }
}

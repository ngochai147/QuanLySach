/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.ChucVu;
import entity.HinhAnh;
import entity.LoaiSach;
import entity.NhanVien;

import java.sql.*;

import entity.Sach;
import entity.TaiKhoan;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tan Nghi
 */
public class TaiKhoan_DAO {

    private ArrayList<TaiKhoan> dsTK;

    public TaiKhoan_DAO() {
        dsTK=new ArrayList<>();
    }
    
    public ArrayList<TaiKhoan> getDSTk() throws SQLException {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String sql = "Select * from TaiKhoan join NhanVien on TaiKhoan.tenDangNhap = NhanVien.maNV";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            String maTK = rs.getString(1);
            String tenDangNhap = rs.getString(2);
            
            String matKhau = rs.getString(3);
            String chucVu = rs.getString(10);
            NhanVien nv = new NhanVien(tenDangNhap, new ChucVu(chucVu));
            dsTK.add(new TaiKhoan(maTK, nv, matKhau));
        }
        return dsTK;
    }
    public TaiKhoan getTaiKhoanTheoTenDN(String tenDangNhap) throws SQLException {
        Connection con = ConnectDB.getInstance().getConnection();
        TaiKhoan taiKhoan = null;
        String sql = "select * from TaiKhoan " +
                "join NhanVien on TaiKhoan.tenDangNhap = NhanVien.maNV where TaiKhoan.tenDangNhap = ?";
        PreparedStatement statement = null;
        statement = con.prepareStatement(sql);
        statement.setString(1, tenDangNhap);
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            String maTK = rs.getString(1);
            String matKhau = rs.getString(3);
            String chucVu = rs.getString(10);
            NhanVien nv = new NhanVien(tenDangNhap, new ChucVu(chucVu));
            taiKhoan = new TaiKhoan(maTK, nv, matKhau);
        }
        statement.close();
        return taiKhoan;
    }
    public boolean themTaiKhoan(TaiKhoan taiKhoan) throws SQLException {
        String sql = "insert into TaiKhoan values(?,?,?)";
        if (dsTK.contains(taiKhoan)) {
            return false;
        } else {
            Connection con = ConnectDB.getInstance().getConnection();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement(sql);
            stmt.setString(1, taiKhoan.getMaTK());
            stmt.setString(2, taiKhoan.getNhanVien().getMaNV());
            stmt.setString(3, taiKhoan.getMatKhau());
            stmt.executeUpdate();
            stmt.close();
            return dsTK.add(taiKhoan);
        }
    }
    public List<String> getMaTK() throws SQLException {
        List<String> dsMaTK = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        String sql = "select maTK from TaiKhoan";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            dsMaTK.add(rs.getString(1));
        }
        return dsMaTK;
    }
    public boolean xoaTaiKhoan(String tenDangNhap) throws SQLException {
        Connection con = ConnectDB.getInstance().getConnection();
        String sql = "delete from TaiKhoan where tenDangNhap = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, tenDangNhap);
        int rs = statement.executeUpdate();
        statement.close();
        return rs > 0;
    }
}

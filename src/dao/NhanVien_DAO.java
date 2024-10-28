/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.ChucVu;
import entity.HinhAnh;
import entity.NhanVien;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tan Nghi
 */
public class NhanVien_DAO {

    private List<NhanVien> dsNhanVien = null;

    public NhanVien_DAO() {
        dsNhanVien = new ArrayList<>();
    }

    public List<NhanVien> getDSNhanVien() throws SQLException {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        dsNhanVien.clear();
        String sql = "select * from NhanVien";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            String maNV = rs.getString(1);
            String hoTen = rs.getString(2);
            String soDienThoai = rs.getString(3);
            String diaChi = rs.getString(4);
            String email = rs.getString(5);
            boolean gioiTinh = rs.getBoolean(6);
            ChucVu chucVu = new ChucVu(rs.getString(7));
            LocalDate ngaySinh = rs.getDate(8).toLocalDate();
            HinhAnh anh = new HinhAnh(rs.getString(9));
            dsNhanVien.add(new NhanVien(hoTen, soDienThoai, diaChi, email, gioiTinh, ngaySinh, maNV, chucVu, anh));
        }
        return dsNhanVien;
    }

    public NhanVien getNhanVienTheoMaNV(String ma) throws SQLException {
        NhanVien nhanVien = null;
        Connection con = ConnectDB.getConnection();
        String sql = "select * from TaiKhoan " +
                "join NhanVien on TaiKhoan.tenDangNhap = NhanVien.maNV where TaiKhoan.tenDangNhap = ?";
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
        }
        statement.setString(1, ma);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String maNhanVien = rs.getString(4);
            String hoTen = rs.getString(5);
            String soDienThoai = rs.getString(6);
            String diaChi = rs.getString(7);
            String email = rs.getString(8);
            boolean gioiTinh = rs.getBoolean(9);
            ChucVu chucVu = new ChucVu(rs.getString(10));
            LocalDate ngaySinh = rs.getDate(11).toLocalDate();
            HinhAnh anh = new HinhAnh(rs.getString(12));
            nhanVien = new NhanVien(hoTen, soDienThoai, diaChi, email, gioiTinh, ngaySinh, maNhanVien, chucVu, anh);
        }
        statement.close();
        return nhanVien;
    }
    public boolean themNhanVien(NhanVien nhanVien) throws SQLException {
        String sql = "insert into NhanVien values (?,?,?,?,?,?,?,?,?)";
        if (dsNhanVien.contains(nhanVien)) {
            return false;
        } else {
            Connection con = ConnectDB.getInstance().getConnection();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement(sql);
            stmt.setString(1, nhanVien.getMaNV());
            stmt.setString(2, nhanVien.getHoTen());
            stmt.setString(3, nhanVien.getSoDienThoai());
            stmt.setString(4, nhanVien.getDiaChi());
            stmt.setString(5, nhanVien.getEmail());
            stmt.setBoolean(6, nhanVien.isGioiTinh());
            stmt.setString(7, nhanVien.getChucVu().getChucVu());
            stmt.setDate(8, Date.valueOf(nhanVien.getNgaySinh()));
            stmt.setString(9, nhanVien.getAnh().getUrl());
            stmt.executeUpdate();
            stmt.close();
            return dsNhanVien.add(nhanVien);
        }
    }
    public boolean xoaNhanVien(String maNhanVien) throws SQLException {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        stmt = con.prepareStatement("delete from NhanVien where maNV = ?");
        stmt.setString(1, maNhanVien);
        int n = stmt.executeUpdate();
        return n > 0;
    }
    public boolean capNhatNhanVien(NhanVien nhanVien) throws SQLException {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        stmt = con.prepareStatement("update NhanVien " +
                "set hoTen = ?, soDienThoai = ?, diaChi = ?, email = ?, gioiTinh = ?, chucVu = ?, ngaySinh = ?, hinhAnh = ? " +
                "where maNV = ?");
        stmt.setString(1, nhanVien.getHoTen());
        stmt.setString(2, nhanVien.getSoDienThoai());
        stmt.setString(3, nhanVien.getDiaChi());
        stmt.setString(4, nhanVien.getEmail());
        stmt.setBoolean(5, nhanVien.isGioiTinh());
        stmt.setString(6, nhanVien.getChucVu().getChucVu());
        stmt.setDate(7, Date.valueOf(nhanVien.getNgaySinh()));
        stmt.setString(8, nhanVien.getAnh().getUrl());
        stmt.setString(9, nhanVien.getMaNV());
        int n = stmt.executeUpdate();
        return n > 0;
    }
    public ArrayList<NhanVien> getDSNhanVienTheoChucVu(String chucVu) throws SQLException{
        ArrayList<NhanVien> dsNhanVienTheoChucVu = new ArrayList<>();
        Connection con = ConnectDB.getConnection();
        String sql = "select * from NhanVien where chucVu = ?";
        PreparedStatement statement = null;
        statement = con.prepareStatement(sql);
        statement.setString(1, chucVu);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String maNV = rs.getString(1);
            String hoTen = rs.getString(2);
            String soDienThoai = rs.getString(3);
            String diaChi = rs.getString(4);
            String email = rs.getString(5);
            boolean gioiTinh = rs.getBoolean(6);
            ChucVu cv = new ChucVu(rs.getString(7));
            LocalDate ngaySinh = rs.getDate(8).toLocalDate();
            HinhAnh anh = new HinhAnh(rs.getString(9));
            dsNhanVienTheoChucVu.add(new NhanVien(hoTen, soDienThoai, diaChi, email, gioiTinh, ngaySinh, maNV, cv, anh));
        }
        statement.close();
        return dsNhanVienTheoChucVu;
    }
    public ArrayList<NhanVien> getDSNhanVienTheoTenNhanVien(String tenNhanVien) throws SQLException{
        ArrayList<NhanVien> dsNhanVienTheoTen = new ArrayList<>();
        Connection con = ConnectDB.getConnection();
        String sql = "select * from NhanVien where hoTen = ?";
        PreparedStatement statement = null;
        statement = con.prepareStatement(sql);
        statement.setString(1, tenNhanVien);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String maNV = rs.getString(1);
            String hoTen = rs.getString(2);
            String soDienThoai = rs.getString(3);
            String diaChi = rs.getString(4);
            String email = rs.getString(5);
            boolean gioiTinh = rs.getBoolean(6);
            ChucVu cv = new ChucVu(rs.getString(7));
            LocalDate ngaySinh = rs.getDate(8).toLocalDate();
            HinhAnh anh = new HinhAnh(rs.getString(9));
            dsNhanVienTheoTen.add(new NhanVien(hoTen, soDienThoai, diaChi, email, gioiTinh, ngaySinh, maNV, cv, anh));
        }
        statement.close();
        return dsNhanVienTheoTen;
    }
}

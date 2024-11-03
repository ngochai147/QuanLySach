/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package dao;
import connectDB.ConnectDB;

import entity.KhoHang;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
public class KhoHang_DAO {
    private final List<KhoHang> dsKhoHang;
    public KhoHang_DAO() {
        dsKhoHang = new ArrayList<>();
    }

    public List<KhoHang> getDSKhoHang() throws SQLException {
        Connection con = ConnectDB.getConnection();
        String sql = "select * from KhoHang";
        PreparedStatement statement = con.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while(rs.next()) {
            String maKho = rs.getString(1);
            String tenKho = rs.getString(2);
            int sucChua = rs.getInt(3);
            String diaChi = rs.getString(4);
            KhoHang khoHang = new KhoHang(maKho, tenKho, sucChua, diaChi);
            dsKhoHang.add(khoHang);
        }
        statement.close();
        return dsKhoHang;
    }

    public KhoHang getKhoTheoTenKho(String tk) throws SQLException {
        Connection con = ConnectDB.getConnection();
        KhoHang khoHang = null;
        String sql = "select *\n"
                + "from KhoHang\n"
                + "where tenKho = ?";
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
        }
        statement.setString(1, tk);
        ResultSet rs = statement.executeQuery();
        while(rs.next()) {
            String maKho = rs.getString(1);
            String tenKho = rs.getString(2);
            int sucChua = rs.getInt(3);
            String diaChi = rs.getString(4);

            khoHang = new KhoHang(maKho, tenKho, sucChua, diaChi);
        }
        statement.close();
        return khoHang;
    }

    public String getMaKhoTheoTenKho(String tk) throws SQLException {
        Connection con = ConnectDB.getConnection();
        String maKho = null;
        String sql = "SELECT maKhoHang FROM KhoHang WHERE tenKho = ?";
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = con.prepareStatement(sql);
            statement.setString(1, tk); // Truyền vào mã kho
            rs = statement.executeQuery();

            // Lấy tên kho từ kết quả truy vấn
            if (rs.next()) {
                maKho = rs.getString("maKhoHang"); // Lấy tên kho từ cột "tenKho"
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo đóng ResultSet và PreparedStatement
            if (rs != null) rs.close();
            if (statement != null) statement.close();
        }

        return maKho;
    }

    public KhoHang getKhoHangTheoMaKho(String maKhoHang) {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        KhoHang khoHang = null;

        try {
            String sql = "SELECT * FROM KhoHang WHERE maKhoHang = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maKhoHang);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String tenKho = rs.getString(2);
                int sucChua = rs.getInt(3);
                String diaChi = rs.getString(4);

                khoHang = new KhoHang(maKhoHang, tenKho, sucChua, diaChi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return khoHang;
    }

    public boolean kiemTraTenKho(String tenKho) {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            // Câu truy vấn kiểm tra tên kho đã tồn tại chưa
            String sql = "SELECT maKhoHang FROM KhoHang WHERE tenKho = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, tenKho);

            // Thực thi truy vấn
            return stmt.executeQuery().next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy kho hàng theo mã kho

}

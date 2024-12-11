package dao;

import connectDB.ConnectDB;
import entity.ThongKeTonKho_model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ThongKeTonKho_DAO {

    public List<String> getListTenKho() {
        List<String> listTenKho = new ArrayList<>();
        String sql = "SELECT tenKho FROM KhoHang";

        try {
            Connection con = ConnectDB.getInstance().getConnection();
            PreparedStatement p = con.prepareStatement(sql);
            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                String tenKho = rs.getString("tenKho");
                listTenKho.add(tenKho);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listTenKho;
    }

    public List<ThongKeTonKho_model> getThongKeTonKho(String tenKho) {
        List<ThongKeTonKho_model> dataList = new ArrayList<>();
        String sql = """
                SELECT s.ISBN, s.tenSach, kh.tenKho, ctkh.soLuong, s.trangThai, kh.diaChi, kh.sucChua, s.tacGia
                FROM ChiTietKhoHang ctkh
                JOIN Sach s ON ctkh.ISBN = s.ISBN
                JOIN KhoHang kh ON ctkh.maKhoHang = kh.maKhoHang 
                WHERE 1 = 1
                """;

        if (tenKho != null && !tenKho.equals("Tất cả")) {
            sql += " AND kh.tenKho = ?";
        }

        try {
            Connection conn = ConnectDB.getInstance().getConnection();
            PreparedStatement p = conn.prepareStatement(sql);

            int paramIndex = 1;
            if (tenKho != null && !tenKho.equals("Tất cả")) {
                p.setString(paramIndex++, tenKho);
            }
            try (ResultSet rs = p.executeQuery()) {
                while (rs.next()) {
                    String iSBN = rs.getString("ISBN");
                    String tenSach = rs.getString("tenSach");
                    String tacGia = rs.getString("tacGia");
                    tenKho = rs.getString("tenKho");
                    String diaChi = rs.getString("diaChi");
                    int soLuong = rs.getInt("soLuong");
                    int sucChua = rs.getInt("sucChua");
                    String trangThai = rs.getString("trangThai");
                    dataList.add(new ThongKeTonKho_model(iSBN, tenSach, tacGia, tenKho, diaChi, soLuong, sucChua, trangThai));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dataList;
    }

    public List<ThongKeTonKho_model> getBarChartData() {
        List<ThongKeTonKho_model> listData = new ArrayList<>();
        String sql = """
            select kh.tenKho, kh.sucChua, sum(ctkh.soLuong) as tongTonKho
            from KhoHang as kh
            join ChiTietKhoHang as ctkh
            on kh.maKhoHang = ctkh.maKhoHang
            GROUP BY kh.tenKho, kh.sucChua
            """;
        try {
            Connection conn = ConnectDB.getInstance().getConnection();
            PreparedStatement p = conn.prepareStatement(sql);
            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                String tenKho = rs.getString("tenKho");
                int sucChua = rs.getInt("sucChua");
                int tongTonKho = rs.getInt("tongTonKho");

                listData.add(new ThongKeTonKho_model(tenKho, sucChua, tongTonKho));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listData;
    }

    public List<ThongKeTonKho_model> getThongKeTheoKho(String tenKho) {
        List<ThongKeTonKho_model> listData = new ArrayList<>();
        String sql = """
            SELECT ctkh.ISBN, SUM(ctkh.soLuong) AS tongSoLuong
            FROM ChiTietKhoHang ctkh
            JOIN KhoHang kh ON ctkh.maKhoHang = kh.maKhoHang
            WHERE kh.tenKho = ?
            GROUP BY ctkh.ISBN
        """;
        try (Connection conn = ConnectDB.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenKho);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String isbn = rs.getString("ISBN");
                int tongSoLuong = rs.getInt("tongSoLuong");
                listData.add(new ThongKeTonKho_model(tenKho, isbn, tongSoLuong));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listData;
    }

}

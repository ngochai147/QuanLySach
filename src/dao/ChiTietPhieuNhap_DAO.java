/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.ChiTietPhieuNhapKho;
import entity.PhieuNhapKho;
import entity.Sach;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class ChiTietPhieuNhap_DAO {
    private final List<ChiTietPhieuNhapKho> ds_ctpnk;

      public ChiTietPhieuNhap_DAO() {
        ds_ctpnk = new ArrayList<>();
    }

    public List<ChiTietPhieuNhapKho> getAllChiTietPhieuNhap() {
        ArrayList<ChiTietPhieuNhapKho> listCTPN = new ArrayList<>();
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT * FROM ChiTietPhieuNhapKho";
            statement = con.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                String maCTPN = rs.getString(1);
                String maPN = rs.getString(2);
                String maSach = rs.getString(4);
                int soLuong = rs.getInt(3);
                ChiTietPhieuNhapKho ctpn =new ChiTietPhieuNhapKho(maCTPN, new PhieuNhapKho(maPN), soLuong, new Sach(maSach));
                listCTPN.add(ctpn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listCTPN;
    }

    public boolean insertChiTietPhieuNhapKho(String maChiTietPhieuNhapKho, String maPhieuNhapKho, int soLuong, String isbn) {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;

        String sql = "INSERT INTO ChiTietPhieuNhapKho (maChiTietPhieuNhapKho, maPhieuNhapKho, soLuong, ISBN) VALUES (?, ?, ?, ?)";

        try {
            stmt = con.prepareStatement(sql);

            // Thiết lập giá trị cho các tham số trong câu lệnh SQL
            stmt.setString(1, maChiTietPhieuNhapKho);
            stmt.setString(2, maPhieuNhapKho);
            stmt.setInt(3, soLuong);
            stmt.setString(4, isbn);

            // Thực thi câu truy vấn
            int rowsAffected = stmt.executeUpdate();

            // Trả về true nếu insert thành công, false nếu không thành công
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Đảm bảo đóng PreparedStatement sau khi sử dụng
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
      
    // Hàm lấy mã chi tiết phiếu nhập kho mới nhất từ cơ sở dữ liệu
    public String getLastMaChiTietPhieuNhapKho() {
        Connection con = ConnectDB.getInstance().getConnection();
        
        // Kiểm tra kết nối không null
        if (con == null) {
            System.out.println("Kết nối CSDL chưa được thiết lập.");
            return null; // Hoặc ném một ngoại lệ nếu cần thiết
        }

        PreparedStatement stmt = null;
        String lastMa = null;
        
        try {
            String sql = "SELECT TOP 1 maChiTietPhieuNhapKho FROM ChiTietPhieuNhapKho ORDER BY maChiTietPhieuNhapKho DESC";
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                lastMa = rs.getString("maChiTietPhieuNhapKho");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo đóng PreparedStatement và ResultSet sau khi sử dụng
            try {
                if (stmt != null) stmt.close();
                // Không đóng kết nối con tại đây, vì ConnectDB sẽ quản lý nó
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lastMa;
    }
}

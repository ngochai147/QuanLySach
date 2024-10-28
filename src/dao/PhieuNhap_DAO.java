/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.PhieuNhapKho;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Dell
 */
public class PhieuNhap_DAO {
    private final List<PhieuNhapKho> ds_pnk;

    public PhieuNhap_DAO() {
        ds_pnk = new ArrayList<>();
    }
    
    public boolean insertPhieuNhapKho(String maPhieuNhapKho, Date ngayLap, String maNV, String maKhoHangNhap, int soLuong) {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;

        String sql = "INSERT INTO PhieuNhapKho (maPhieuNhapKho, ngayLap, maNV, maKhoHangNhap, soLuong) VALUES (?, ?, ?, ?, ?)";

        try {
            stmt = con.prepareStatement(sql);

            // Thiết lập giá trị cho các tham số trong câu lệnh SQL
            stmt.setString(1, maPhieuNhapKho);
            stmt.setDate(2, ngayLap);
            stmt.setString(3, maNV);
            stmt.setString(4, maKhoHangNhap);
            stmt.setInt(5, soLuong);

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
    
    public String getLastMaPhieuNhapKho() {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String lastMaPhieuNhapKho = null;

        try {
            
            // Câu truy vấn SQL
            String query = "SELECT TOP 1 maPhieuNhapKho FROM PhieuNhapKho ORDER BY maPhieuNhapKho DESC";
            
            // Chuẩn bị statement
            stmt = con.prepareStatement(query);
            
            // Thực thi truy vấn
            rs = stmt.executeQuery();
            
            // Kiểm tra và lấy mã chi tiết phiếu nhập kho mới nhất
            if (rs.next()) {
                lastMaPhieuNhapKho = rs.getString("maPhieuNhapKho");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng tài nguyên
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lastMaPhieuNhapKho;
    }
    
      
    
}

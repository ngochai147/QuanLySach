package dao;

import connectDB.ConnectDB;
import entity.ChiTietPhieuXuatKho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChiTietPhieuXuatKho_DAO {
    private final List<ChiTietPhieuXuatKho> ds_ctpnk;

    public ChiTietPhieuXuatKho_DAO() {
        ds_ctpnk = new ArrayList<>();
    }

    public boolean insertChiTietPhieuXuatKho(String maChiTietPhieuXuatKho, String maPhieuXuatKho, int soLuong, String isbn) {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;

        String sql = "INSERT INTO ChiTietPhieuXuatKho (maChiTietPhieuXuatKho, maPhieuXuatKho, soLuong, ISBN) VALUES (?, ?, ?, ?)";

        try {
            stmt = con.prepareStatement(sql);

            // Thiết lập giá trị cho các tham số trong câu lệnh SQL
            stmt.setString(1, maChiTietPhieuXuatKho);
            stmt.setString(2, maPhieuXuatKho);
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
    public String getLastMaChiTietPhieuXuatKho() {
        Connection con = ConnectDB.getInstance().getConnection();

        // Kiểm tra kết nối không null
        if (con == null) {
            System.out.println("Kết nối CSDL chưa được thiết lập.");
            return null; // Hoặc ném một ngoại lệ nếu cần thiết
        }

        PreparedStatement stmt = null;
        String lastMa = null;

        try {
            String sql = "SELECT TOP 1 maChiTietPhieuXuatKho FROM ChiTietPhieuXuatKho ORDER BY maChiTietPhieuXuatKho DESC";
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                lastMa = rs.getString("maChiTietPhieuXuatKho");
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
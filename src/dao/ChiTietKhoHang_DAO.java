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

    public List<ChiTietKhoHang> getChiTietKhoHangTheoMaKho(String maKhoHang) {
        List<ChiTietKhoHang> dsChiTietKhoHang = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietKhoHang WHERE maKhoHang = ?";
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maKhoHang); // Gán tham số mã kho hàng vào câu lệnh SQL
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maCTKhoHang = rs.getString("maChiTietKhoHang");
                String ISBN = rs.getString("ISBN");
                String maKho = rs.getString("maKhoHang");
                int soLuong = rs.getInt("soLuong");
                dsChiTietKhoHang.add(new ChiTietKhoHang(maCTKhoHang, soLuong, new Sach(ISBN), new KhoHang(maKho)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return dsChiTietKhoHang;
    }

    public List<ChiTietKhoHang> getDSSachTrongKho(String maKhoHang, String ISBN) {
        String sql = "SELECT * FROM ChiTietKhoHang WHERE maKhoHang = ? AND ISBN = ?";
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        List<ChiTietKhoHang> ds_Sach = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maKhoHang);
            stmt.setString(2, ISBN);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String maCTKhoHang = rs.getString("maChiTietKhoHang");
                int soLuong = rs.getInt("soLuong");
                ChiTietKhoHang chiTietKhoHang = new ChiTietKhoHang(maCTKhoHang, soLuong, new Sach(ISBN), new KhoHang(maKhoHang));
                ds_Sach.add(chiTietKhoHang);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi xảy ra khi truy vấn dữ liệu từ ChiTietKhoHang: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close(); // Đảm bảo đóng kết nối sau khi sử dụng
                }
            } catch (SQLException e) {
                System.out.println("Lỗi xảy ra khi đóng kết nối: " + e.getMessage());
                e.printStackTrace();
            }
        }

        if (ds_Sach.isEmpty()) {
            System.out.println("Không tìm thấy dữ liệu cho maKhoHang: " + maKhoHang + " và ISBN: " + ISBN);
        }

        return ds_Sach;
    }

    public ChiTietKhoHang kiemTraTonTaiISBNTrongKho(String isbn, String maKhoHang) {
        String sql = "SELECT * FROM ChiTietKhoHang WHERE maKhoHang = ? AND ISBN = ?";
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        ChiTietKhoHang chiTietKhoHang = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maKhoHang);
            stmt.setString(2, isbn);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String maCTKhoHang = rs.getString("maChiTietKhoHang");
                String ISBN = rs.getString("ISBN");
                int soLuong = rs.getInt("soLuong");
                chiTietKhoHang = new ChiTietKhoHang(maCTKhoHang, soLuong, new Sach(ISBN), new KhoHang(maKhoHang));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return chiTietKhoHang;
    }

    public List<String> getMaChiTietKhoHang() {
        List<String> dsMaChiTietKhoHang = new ArrayList<>();
        String sql = "select maChiTietKhoHang from ChiTietKhoHang";
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                dsMaChiTietKhoHang.add(rs.getString("maChiTietKhoHang"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsMaChiTietKhoHang;
    }

    public boolean themChiTietKhoHang(ChiTietKhoHang chiTietKhoHang) throws SQLException {
        String sql = "insert into ChiTietKhoHang values (?,?,?,?)";
        if (ct_khoHang.contains(chiTietKhoHang)) {
            return false;
        } else {
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

    public List<ChiTietKhoHang> getAllChiTietKhoHang() {
        List<ChiTietKhoHang> dsChiTietKhoHang = new ArrayList<>();
        String sql = "select * from ChiTietKhoHang";
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maCTKhoHang = rs.getString(1);
                String ISBN = rs.getString(2);
                String maKhoHang = rs.getString(3);
                int soLuong = rs.getInt(4);
                dsChiTietKhoHang.add(new ChiTietKhoHang(maCTKhoHang, soLuong, new Sach(ISBN), new KhoHang(maKhoHang)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsChiTietKhoHang;
    }

//    public String timTenKhoTheoMaSach(String maSach) throws SQLException {
//        Connection con = ConnectDB.getInstance().getConnection();
//        PreparedStatement stmt = null;
//        String tenKho = null;
//        try {
//            // Câu truy vấn JOIN để lấy tenKho từ bảng KhoHang
//            String sql = "SELECT k.tenKho " +
//                    "FROM ChiTietKhoHang c " +
//                    "JOIN KhoHang k ON c.maKhoHang = k.maKhoHang " +
//                    "WHERE c.ISBN = ?";
//            stmt = con.prepareStatement(sql);
//            stmt.setString(1, maSach);
//
//            // Thực thi truy vấn
//            ResultSet rs = stmt.executeQuery();
//
//            // Lấy tên kho từ kết quả
//            if (rs.next()) {
//                tenKho = rs.getString("tenKho"); // Lấy tên kho từ cột tenKho
//            }
//        } finally {
//            if (stmt != null) {
//                stmt.close(); // Đảm bảo đóng PreparedStatement
//            }
//        }
//        return tenKho;
//    }
public List<String> timTenKhoTheoMaSach(String maSach) throws SQLException {
    Connection con = ConnectDB.getInstance().getConnection();
    PreparedStatement stmt = null;
    List<String> tenKhoList = new ArrayList<>();

    try {
        String sql = "SELECT k.tenKho " +
                "FROM ChiTietKhoHang c " +
                "JOIN KhoHang k ON c.maKhoHang = k.maKhoHang " +
                "WHERE c.ISBN = ?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, maSach);

        // Thực thi truy vấn
        ResultSet rs = stmt.executeQuery();

        // Lặp qua tất cả các kết quả và thêm tên kho vào danh sách
        while (rs.next()) {
            String tenKho = rs.getString("tenKho");
            tenKhoList.add(tenKho);
        }
    } finally {
        if (stmt != null) {
            stmt.close(); // Đảm bảo đóng PreparedStatement
        }
    }

    return tenKhoList;
}


    public boolean xoaChiTietKhoHang(String ISBN) throws SQLException {
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        stmt = con.prepareStatement("delete ChiTietKhoHang where ISBN = ?");
        stmt.setString(1, ISBN);
        stmt.executeUpdate();
        return true;
    }

    public boolean capNhatChiTietKhoHang(String ISBN, String maKho, int soLuong) throws SQLException {
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

    public String getLastMaChiTietKhoHang() {
        String query = "SELECT TOP 1 maChiTietKhoHang FROM ChiTietKhoHang ORDER BY maChiTietKhoHang DESC";
        String lastMaChiTietKhoHang = null;

        try (Connection con = ConnectDB.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                lastMaChiTietKhoHang = rs.getString("maChiTietKhoHang");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastMaChiTietKhoHang; // Trả về mã cuối cùng hoặc null nếu không có mã nào
    }
    public int getSoLuongSachTheoKho(String maKhoHang) {
        String sql = "select sum(soLuong) from ChiTietKhoHang where maKhoHang = ?";
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        int soLuong = 0;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maKhoHang);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                soLuong = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return soLuong;
    }
    public int getSoLuongSachTheoMaKhoVaMaSach(String maKhoHang, String maSach) {
        String sql = "select sum(soLuong) from ChiTietKhoHang where maKhoHang = ? and ISBN = ?";
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        int soLuong = 0;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maKhoHang);
            stmt.setString(2, maSach);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                soLuong = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return soLuong;
    }
}

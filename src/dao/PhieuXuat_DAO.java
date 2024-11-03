package dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import connectDB.ConnectDB;
import entity.KhoHang;
import entity.NhanVien;
import entity.PhieuNhapKho;
import entity.PhieuXuatKho;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
/**
 *
 * @author Thế Bảo
 */
public class PhieuXuat_DAO {
    public PhieuXuat_DAO() {
    }

    public static ArrayList<PhieuXuatKho> getAllphieuXuatKho() {
        ArrayList<PhieuXuatKho> dsPXK =new ArrayList<PhieuXuatKho>();
        try {
            Connection con=ConnectDB.getConnection();

            String sql="select * from PhieuXuatKho";
            Statement statement= con.createStatement();

            ResultSet rs=statement.executeQuery(sql);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while(rs.next()) {
                String maPXK =rs.getString(1);
                // Chuyển đổi chuỗi ngày thành LocalDate
                LocalDate ngayLap = LocalDate.parse(rs.getString(2), formatter);
                NhanVien maNV = new NhanVien(rs.getString(3));
                KhoHang maKhoHangXuat = new KhoHang(rs.getString(4));
                KhoHang maKhoHangNhap = new KhoHang(rs.getString(5));
                int soLuong = rs.getInt(6);
                PhieuXuatKho pxk = new PhieuXuatKho(maPXK, ngayLap, maNV, maKhoHangXuat, maKhoHangNhap, soLuong);
                dsPXK.add(pxk);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return dsPXK;
    }

    public PhieuXuatKho getPhieuXuatKhoTheoMaPXK(String maPhieuXuatKho) {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PhieuXuatKho pxk = null;

        try {
            String sql = "SELECT * FROM PhieuXuatKho WHERE maPhieuXuatKho = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maPhieuXuatKho);
            rs = stmt.executeQuery();

            while (rs.next()) {
                LocalDate ngayLap = rs.getDate(2).toLocalDate();
                String maNV = rs.getString(3);
                String maKhoHangXuat = rs.getString(4);
                String maKhoHangNhap = rs.getString(5);
                int soLuong = rs.getInt(6);

                NhanVien nv = new NhanVien_DAO().getNhanVienTheoMaNV(maNV);
                KhoHang khoHangXuat = new KhoHang_DAO().getKhoHangTheoMaKho(maKhoHangXuat);
                KhoHang khoHangNhap = new KhoHang_DAO().getKhoHangTheoMaKho(maKhoHangNhap);

                pxk = new PhieuXuatKho(maPhieuXuatKho, ngayLap, nv, khoHangXuat, khoHangNhap, soLuong);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                // Không đóng kết nối ở đây
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pxk;
    }

    public static ArrayList<PhieuXuatKho> getDSPhieuXuatKhoTheoMa(String tuKhoaNhapVao) {
        ArrayList<PhieuXuatKho> dsPXK = new ArrayList<PhieuXuatKho>();
        String keyword = "%" + tuKhoaNhapVao + "%";
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();

            String sql = "select * from PhieuXuatKho where maPhieuXuatKho LIKE ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, keyword); // Gán giá trị cho tham số

            ResultSet rs = preparedStatement.executeQuery();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while (rs.next()) {
                String maPXK = rs.getString(1);
                // Chuyển đổi chuỗi ngày thành LocalDate
                LocalDate ngayLap = LocalDate.parse(rs.getString(2), formatter);
                NhanVien maNV = new NhanVien(rs.getString(3));
                KhoHang maKhoHangXuat = new KhoHang(rs.getString(4));
                KhoHang maKhoHangNhap = new KhoHang(rs.getString(5));
                int soLuong = rs.getInt(6);
                PhieuXuatKho pxk = new PhieuXuatKho(maPXK, ngayLap, maNV, maKhoHangXuat, maKhoHangNhap, soLuong);
                dsPXK.add(pxk);
            }
        } catch (Exception e) {
        }
        return dsPXK;
    }

    public static ArrayList<PhieuXuatKho> getPhieuXuatKhoTheoMaThuKho(String tuKhoaNhapVao) {
        ArrayList<PhieuXuatKho> dsPXK = new ArrayList<PhieuXuatKho>();
        String keyword = "%" + tuKhoaNhapVao + "%";
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();

            String sql = "select * from PhieuXuatKho where maNV LIKE ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, keyword); // Gán giá trị cho tham số

            ResultSet rs = preparedStatement.executeQuery();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while (rs.next()) {
                String maPXK = rs.getString(1);
                // Chuyển đổi chuỗi ngày thành LocalDate
                LocalDate ngayLap = LocalDate.parse(rs.getString(2), formatter);
                NhanVien maNV = new NhanVien(rs.getString(3));
                KhoHang maKhoHangXuat = new KhoHang(rs.getString(4));
                KhoHang maKhoHangNhap = new KhoHang(rs.getString(5));
                int soLuong = rs.getInt(6);
                PhieuXuatKho pxk = new PhieuXuatKho(maPXK, ngayLap, maNV, maKhoHangXuat, maKhoHangNhap, soLuong);
                dsPXK.add(pxk);
            }
        } catch (Exception e) {
        }
        return dsPXK;
    }

    public static ArrayList<PhieuXuatKho> getPhieuXuatKhoTheoMaKhoNhap(String tuKhoaNhapVao) {
        ArrayList<PhieuXuatKho> dsPXK = new ArrayList<PhieuXuatKho>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();

            String sql = "select * from PhieuXuatKho where maKhoHangNhap = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, tuKhoaNhapVao); // Gán giá trị cho tham số

            ResultSet rs = preparedStatement.executeQuery();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while (rs.next()) {
                String maPXK = rs.getString(1);
                // Chuyển đổi chuỗi ngày thành LocalDate
                LocalDate ngayLap = LocalDate.parse(rs.getString(2), formatter);
                NhanVien maNV = new NhanVien(rs.getString(3));
                KhoHang maKhoHangXuat = new KhoHang(rs.getString(4));
                KhoHang maKhoHangNhap = new KhoHang(rs.getString(5));
                int soLuong = rs.getInt(6);
                PhieuXuatKho pxk = new PhieuXuatKho(maPXK, ngayLap, maNV, maKhoHangXuat, maKhoHangNhap, soLuong);
                dsPXK.add(pxk);
            }
        } catch (Exception e) {
        }
        return dsPXK;
    }

    public static ArrayList<PhieuXuatKho> getPhieuXuatKhoTheoMaKhoXuat(String tuKhoaNhapVao) {
        ArrayList<PhieuXuatKho> dsPXK = new ArrayList<PhieuXuatKho>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();

            String sql = "select * from PhieuXuatKho where maKhoHangXuat = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, tuKhoaNhapVao); // Gán giá trị cho tham số

            ResultSet rs = preparedStatement.executeQuery();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while (rs.next()) {
                String maPXK = rs.getString(1);
                // Chuyển đổi chuỗi ngày thành LocalDate
                LocalDate ngayLap = LocalDate.parse(rs.getString(2), formatter);
                NhanVien maNV = new NhanVien(rs.getString(3));
                KhoHang maKhoHangXuat = new KhoHang(rs.getString(4));
                KhoHang maKhoHangNhap = new KhoHang(rs.getString(5));
                int soLuong = rs.getInt(6);
                PhieuXuatKho pxk = new PhieuXuatKho(maPXK, ngayLap, maNV, maKhoHangXuat, maKhoHangNhap, soLuong);
                dsPXK.add(pxk);
            }
        } catch (Exception e) {
        }
        return dsPXK;
    }

    public static ArrayList<PhieuXuatKho> getPhieuXuatKhoTheoNgayLapDon(String tuKhoaNhapVao) {
        ArrayList<PhieuXuatKho> dsPXK = new ArrayList<PhieuXuatKho>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();

            // Xử lý chuỗi nhập vào thành định dạng yyyy-MM-dd
            DateTimeFormatter[] inputFormatters = {
                    DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                    DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                    DateTimeFormatter.ofPattern("yyyy/MM/dd"),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd")
            };
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate ngayLap = null;

            // Kiểm tra các định dạng đầu vào và chuyển đổi thành LocalDate
            for (DateTimeFormatter formatter : inputFormatters) {
                try {
                    ngayLap = LocalDate.parse(tuKhoaNhapVao, formatter);
                    break;
                } catch (DateTimeParseException e) {
                    // Bỏ qua lỗi và thử định dạng tiếp theo
                }
            }

            if (ngayLap == null) {
                System.out.println("Định dạng ngày không hợp lệ: " + tuKhoaNhapVao); // In ra thông tin để kiểm tra
                throw new IllegalArgumentException("Định dạng ngày không hợp lệ");
            }

            String sql = "select * from PhieuXuatKho where ngayLap = ?";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, ngayLap.format(outputFormatter));

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String maPXK = rs.getString(1);
                // Chuyển đổi chuỗi ngày thành LocalDate
                LocalDate ngayLapDon = LocalDate.parse(rs.getString(2), outputFormatter);
                NhanVien maNV = new NhanVien(rs.getString(3));
                KhoHang maKhoHangXuat = new KhoHang(rs.getString(4));
                KhoHang maKhoHangNhap = new KhoHang(rs.getString(5));
                int soLuong = rs.getInt(6);
                PhieuXuatKho pxk = new PhieuXuatKho(maPXK, ngayLapDon, maNV, maKhoHangXuat, maKhoHangNhap, soLuong);
                dsPXK.add(pxk);
            }
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi ra console để kiểm tra
        }
        return dsPXK;
    }

    public boolean insertPhieuXuatKho(String maPhieuXuatKho, Date ngayLap, String maNV, String maKhoHangXuat, String maKhoHangNhap, int soLuong) {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;

        String sql = "INSERT INTO PhieuXuatKho (maPhieuXuatKho, ngayLap, maNV, maKhoHangXuat, maKhoHangNhap, soLuong) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            stmt = con.prepareStatement(sql);

            // Thiết lập giá trị cho các tham số trong câu lệnh SQL
            stmt.setString(1, maPhieuXuatKho);
            stmt.setDate(2, ngayLap);
            stmt.setString(3, maNV);
            stmt.setString(4, maKhoHangXuat);
            stmt.setString(5, maKhoHangNhap);
            stmt.setInt(6, soLuong);

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

    public String getLastMaPhieuXuatKho() {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String lastMaPhieuXuatKho = null;
        try {
            // Câu truy vấn SQL
            String query = "SELECT TOP 1 maPhieuXuatKho FROM PhieuXuatKho ORDER BY maPhieuXuatKho DESC";

            // Chuẩn bị statement
            stmt = con.prepareStatement(query);

            // Thực thi truy vấn
            rs = stmt.executeQuery();

            // Kiểm tra và lấy mã chi tiết phiếu nhập kho mới nhất
            if (rs.next()) {
                lastMaPhieuXuatKho = rs.getString("maPhieuXuatKho");
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
        return lastMaPhieuXuatKho;
    }

}

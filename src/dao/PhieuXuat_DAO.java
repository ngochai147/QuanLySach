package dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import connectDB.ConnectDB;
import entity.KhoHang;
import entity.NhanVien;
import entity.PhieuXuatKho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
/**
 *
 * @author Thế Bảo
 */
public class PhieuXuat_DAO {
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

    public static ArrayList<PhieuXuatKho> getPhieuXuatKhoTheoMa(String tuKhoaNhapVao) {
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

}

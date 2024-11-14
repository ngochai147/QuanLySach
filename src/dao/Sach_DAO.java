/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.HinhAnh;
import entity.LoaiSach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import entity.Sach;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Huu Thai
 */
public class Sach_DAO {
    private final List<Sach> dsSach;

    public Sach_DAO() {
        dsSach = new ArrayList<>();
    }

    public List<Sach> getDSSach() throws SQLException {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        dsSach.clear();
        String sql = "select ISBN, tenSach, tacGia, namXB, nhaXB, soLuongTrongKho, giaGoc, tenLoai, hinhAnh, trangThai\n" +
                "from Sach join LoaiSach on Sach.maLoaiSach = LoaiSach.maLoai\n";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()){
            String ISBN = rs.getString(1);
            String tenSach = rs.getString(2);
            String tacGia = rs.getString(3);
            Integer namXB = rs.getInt(4);
            String nhaXB = rs.getString(5);
            Integer soLuong = rs.getInt(6);
            double giaGoc = rs.getDouble(7);
            LoaiSach loaiSach = new LoaiSach("", rs.getString(8));
            HinhAnh anh = new HinhAnh(rs.getString(9));
            String trangThai = rs.getString(10);
            dsSach.add(new Sach(ISBN, tenSach, tacGia, namXB, nhaXB, soLuong, giaGoc, loaiSach, anh, trangThai));
        }
        return dsSach;
    }
    public ArrayList<Sach> getAllSP() {
        ArrayList<Sach> dsSP = new ArrayList<Sach>();
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT * FROM Sach s join LoaiSach ls on s.maLoaiSach=ls.maLoai";
            statement = con.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                String ISBN = rs.getString(1);
                String tenSach = rs.getString(2);
                String tacGia = rs.getString(3);
                int namXB = rs.getInt(4);
                String nhaXB = rs.getString(5);
                int soLuong = rs.getInt(6);
                double giaGoc = rs.getDouble(7);
                LoaiSach loaiSach=new LoaiSach(rs.getString(8), rs.getString(12));
                Sach s= new Sach(ISBN,tenSach,tacGia,namXB,nhaXB,soLuong,giaGoc,loaiSach);
                dsSP.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                // Không đóng kết nối ở đây
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return dsSP;
    }
    public ArrayList<String> getISBN() throws SQLException{
        ArrayList<String> ISBNs = new ArrayList<String>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String sql = "select ISBN from Sach";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            String isbn = rs.getString(1);
            ISBNs.add(isbn);
        }
        return ISBNs;
    }
    public Sach getSachTheoMaSach(String ma) throws SQLException{
        Sach sach = null;
        Connection con = ConnectDB.getConnection();
        String sql = "select ISBN, tenSach, tacGia, namXB, nhaXB, soLuongTrongKho, giaGoc, tenLoai, hinhAnh, trangThai\n"
                + "from Sach join LoaiSach on Sach.maLoaiSach = LoaiSach.maLoai\n"
                + "where ISBN = ?";
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
        }
        statement.setString(1, ma);
        ResultSet rs = statement.executeQuery();
        while(rs.next()) {
            String ISBN = rs.getString(1);
            String tenSach = rs.getString(2);
            String tacGia = rs.getString(3);
            Integer namXB = rs.getInt(4);
            String nhaXB = rs.getString(5);
            Integer soLuong = rs.getInt(6);
            Double giaGoc = rs.getDouble(7);
            LoaiSach loaiSach = new LoaiSach("",rs.getString(8));
            HinhAnh anh = new HinhAnh(rs.getString(9));
            String trangThai = rs.getString(10);
            sach = new Sach(ISBN, tenSach, tacGia, namXB, nhaXB, soLuong, giaGoc, loaiSach, anh, trangThai);
        }
        statement.close();
        return sach;
    }
    public ArrayList<Sach> getDSSachTheoTenLoai(String tenLoai) throws SQLException{
        ArrayList<Sach> dsSach = new ArrayList<>();
        Connection con = ConnectDB.getConnection();
        String sql = "select ISBN, tenSach, tacGia, namXB, nhaXB, soLuongTrongKho, giaGoc, tenLoai, hinhAnh, trangThai\n"
                + "from Sach join LoaiSach on Sach.maLoaiSach = LoaiSach.maLoai "
                + "where tenLoai = ?";

        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
        }
        statement.setString(1, tenLoai);
        ResultSet rs = statement.executeQuery();
        while(rs.next()) {
            String ISBN = rs.getString(1);
            String tenSach = rs.getString(2);
            String tacGia = rs.getString(3);
            Integer namXB = rs.getInt(4);
            String nhaXB = rs.getString(5);
            Integer soLuong = rs.getInt(6);
            Double giaGoc = rs.getDouble(7);
            LoaiSach loaiSach = new LoaiSach("",rs.getString(8));
            HinhAnh anh = new HinhAnh(rs.getString(9));
            String trangThai = rs.getString(10);
            dsSach.add(new Sach(ISBN, tenSach, tacGia, namXB, nhaXB, soLuong, giaGoc, loaiSach, anh, trangThai));
        }
        statement.close();
        return dsSach;
    }
    public ArrayList<Sach> getDSSachTheoTacGia(String tenLoai) throws SQLException{
        ArrayList<Sach> dsSach = new ArrayList<>();
        Connection con = ConnectDB.getConnection();
        String sql = "select ISBN, tenSach, tacGia, namXB, nhaXB, soLuongTrongKho, giaGoc, tenLoai, hinhAnh, trangThai\n"
                + "from Sach join LoaiSach on Sach.maLoaiSach = LoaiSach.maLoai "
                + "where tacGia = ?";

        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
        }
        statement.setString(1, tenLoai);
        ResultSet rs = statement.executeQuery();
        while(rs.next()) {
            String ISBN = rs.getString(1);
            String tenSach = rs.getString(2);
            String tacGia = rs.getString(3);
            Integer namXB = rs.getInt(4);
            String nhaXB = rs.getString(5);
            Integer soLuong = rs.getInt(6);
            Double giaGoc = rs.getDouble(7);
            LoaiSach loaiSach = new LoaiSach("",rs.getString(8));
            HinhAnh anh = new HinhAnh(rs.getString(9));
            String trangThai = rs.getString(10);
            dsSach.add(new Sach(ISBN, tenSach, tacGia, namXB, nhaXB, soLuong, giaGoc, loaiSach, anh, trangThai));
        }
        statement.close();
        return dsSach;
    }
    public boolean themSach(Sach sach) throws SQLException{
        String sql = "insert into Sach values (?,?,?,?,?,?,?,(select maLoai from LoaiSach where tenLoai = ?),?,?)";
        if(dsSach.contains(sach)){
            return false;
        }else {
            Connection con = ConnectDB.getInstance().getConnection();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement(sql);
            stmt.setString(1, sach.getISBN());
            stmt.setString(2, sach.getTenSach());
            stmt.setString(3, sach.getTacGia());
            stmt.setInt(4, sach.getNamXB());
            stmt.setString(5, sach.getNhaXB());
            stmt.setInt(6, sach.getSoLuong());
            stmt.setDouble(7, sach.getGiaGoc());
            stmt.setString(8, sach.getLoaiSach().getTenLoai());
            stmt.setString(9, sach.getAnh().getUrl());
            stmt.setString(10, sach.getTrangThai());
            stmt.executeUpdate();
            stmt.close();
            return dsSach.add(sach);
        }
    }
    public boolean xoaSach(String maSach) throws SQLException{
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        stmt = con.prepareStatement("update Sach set trangThai = ? where ISBN = ?");
        stmt.setString(1, "Ngừng bán");
        stmt.setString(2, maSach);
        stmt.executeUpdate();
        return dsSach.removeIf(x -> x.getISBN().equalsIgnoreCase(maSach));
    }
        public boolean capNhatSach(Sach sach) throws SQLException {
        Connection con = ConnectDB.getConnection();
        int n = 0;
        String sql = "update Sach " +
                     "set tenSach = ?, tacGia = ?, namXB = ?, nhaXB = ?, soLuongTrongKho = ?, giaGoc = ?, "
                     + "maLoaiSach = (select maLoai from LoaiSach where tenLoai = ?), hinhAnh = ? "
                     + "where ISBN = ?";
        PreparedStatement stmt = null;
        stmt = con.prepareStatement(sql);
        stmt.setString(1, sach.getTenSach());
        stmt.setString(2, sach.getTacGia());
        stmt.setInt(3, sach.getNamXB());
        stmt.setString(4, sach.getNhaXB());
        stmt.setInt(5, sach.getSoLuong());
        stmt.setDouble(6, sach.getGiaGoc());
        stmt.setString(7, sach.getLoaiSach().getTenLoai());
        stmt.setString(8, sach.getAnh().getUrl());
        stmt.setString(9, sach.getISBN());
        n = stmt.executeUpdate();
        stmt.close();
        return n > 0;
    }

    public boolean capNhatSoLuongSach(String ISBN, int soLuongTrongKho) {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;

        String sql = "UPDATE Sach SET soLuongTrongKho = soLuongTrongKho + ? WHERE ISBN = ?";

        try {
            stmt = con.prepareStatement(sql);

            // Thiết lập giá trị cho các tham số trong câu lệnh SQL
            stmt.setInt(1, soLuongTrongKho);
            stmt.setString(2, ISBN);

            // Thực thi câu truy vấn
            int rowsAffected = stmt.executeUpdate();

            // Trả về true nếu update thành công, false nếu không thành công
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
    public boolean capNhatSoLuongSachTon(String ISBN, int soLuongTrongKho) {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;

        String sql = "UPDATE Sach SET soLuongTrongKho = ? WHERE ISBN = ?";

        try {
            stmt = con.prepareStatement(sql);

            // Thiết lập giá trị cho các tham số trong câu lệnh SQL
            stmt.setInt(1, soLuongTrongKho);
            stmt.setString(2, ISBN);

            // Thực thi câu truy vấn
            int rowsAffected = stmt.executeUpdate();

            // Trả về true nếu update thành công, false nếu không thành công
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
}

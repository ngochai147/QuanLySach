package dao;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.Sach;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class
ChiTietHoaDon_DAO {
    public List<ChiTietHoaDon> getAllChiTietHoaDon() {
        ArrayList<ChiTietHoaDon> listCTHD = new ArrayList<>();
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT * FROM ChiTietHoaDon  ";
            statement = con.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                String maCTHD = rs.getString(1);
                String maHD = rs.getString(2);
                String maSach = rs.getString(3);
                int soLuong = rs.getInt(4);
                double donGia = rs.getDouble(5);
                ChiTietHoaDon cthd=new ChiTietHoaDon(maCTHD, new HoaDon(maHD), new Sach(maSach), soLuong, donGia);
                listCTHD.add(cthd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listCTHD;
    }
    public boolean create(ChiTietHoaDon cthd) {
        int result = 0;
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "Insert Into ChiTietHoaDon Values (?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cthd.getMaChiTietHoaDon());
            pst.setString(2, cthd.getHoaDon().getMaHoaDon());
            pst.setString(3, cthd.getSach().getISBN());
            pst.setInt(4, cthd.getSoLuong());
            pst.setDouble(5, cthd.getDonGia());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;

    }
    public boolean deleteById(String maCTHD) {
        int result = 0;
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "Delete ChiTietHoaDon where maChiTietHoaDon = ?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, maCTHD);

            result = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result > 0;
    }

}

package dao;
import connectDB.ConnectDB;
import entity.HoaDon;
import entity.NhanVien;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HoaDon_DAO {
    public ArrayList<HoaDon> getAllHoaDon(){
        ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT * FROM HoaDon";
            statement = con.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                String maHD = rs.getString(1);
                String maNV = rs.getString(3);
                HoaDon hd=new HoaDon(maHD, LocalDate.now(), new NhanVien(maNV));
                dsHD.add(hd);
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

        return dsHD;
    }
    public boolean create(HoaDon e) {
        int result = 0;
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "Insert Into HoaDon Values(?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, e.getMaHoaDon());
            pst.setDate(2,Date.valueOf(e.getNgayTaoDon()));
            pst.setString(3, e.getNhanVien().getMaNV());
            result = pst.executeUpdate();
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
        return result>0;
    }


}

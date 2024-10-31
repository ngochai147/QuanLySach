package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connectDB.ConnectDB;
import entity.ThongKe_model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

public class ThongKe_Dao {
    
    public Map<String, Integer> getStatisticIn7Days(){
        Map<String, Integer> ressult = new HashMap<>();
        String sql = """
                SELECT COUNT(DISTINCT HoaDon.maHoaDon) AS tongSoHoaDon, SUM(ChiTietHoaDon.soLuong) AS tongSoLuongSach, SUM(ChiTietHoaDon.soLuong * ChiTietHoaDon.donGia) as TongDoanhThu
                FROM HoaDon
                JOIN ChiTietHoaDon ON HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon
                WHERE ngayTaoDon >= DATEADD(day, -7, GETDATE())
                """;
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                ressult.put("TongSoLuongSachDaBan", rs.getInt(2));
                ressult.put("TongSoLuongHoaDon", rs.getInt(1));
                ressult.put("TongDoanhThu", rs.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ressult;
    }
    
    public List<ThongKe_model> getChartThongKeLast7Days() {
        List<ThongKe_model> dsTK = new ArrayList<ThongKe_model>();

        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = """
                    select FORMAT(ngayTaoDon, 'dd/MM') as ngayTaoDon, 
                    	Sum(ChiTietHoaDon.soLuong * ChiTietHoaDon.donGia) as TongDoanhThu, 
                    	Sum(ChiTietHoaDon.soLuong * ChiTietHoaDon.donGia * 0.2) as LoiNhuan
                    from HoaDon 
                    join ChiTietHoaDon 
                    on HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon 
                    where ngayTaoDon >= DATEADD(DAY, -7, GETDATE())
                    group by ngayTaoDon
                    order by ngayTaoDon DESC
                    """;
            PreparedStatement p = con.prepareStatement(sql);
            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                String ngayTaoDon = rs.getString("ngayTaoDon");
                double TongDoanhThu = rs.getDouble("TongDoanhThu");
                double LoiNhuan = rs.getDouble("LoiNhuan");
                ThongKe_model tk = new ThongKe_model(ngayTaoDon, TongDoanhThu, LoiNhuan);
                dsTK.add(tk);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsTK;
    }

    // Lấy  dữ liệu thống kê theo tháng
    public List<ThongKe_model> getChartThongKeTheoThang(Date fromDate, Date toDate) {
        List<ThongKe_model> month_statistic = new ArrayList<>();
        String sql = """
            SELECT MONTH(ngayTaoDon) AS thang, YEAR(ngayTaoDon) AS nam, 
                   SUM(cthd.donGia * cthd.soLuong) AS doanhThu, 
                   SUM(cthd.donGia * cthd.soLuong * 0.2) AS loiNhuan
            FROM HoaDon AS hd
            JOIN ChiTietHoaDon AS cthd ON hd.maHoaDon = cthd.maHoaDon
            WHERE ngayTaoDon BETWEEN ? AND ?
            GROUP BY YEAR(ngayTaoDon), MONTH(ngayTaoDon)
            ORDER BY nam, thang""";
        try (Connection con = ConnectDB.getInstance().getConnection();
             PreparedStatement p = con.prepareStatement(sql)) {

            // Kiểm tra null cho ngày
            if (fromDate == null || toDate == null) {
                throw new IllegalArgumentException("Vui lòng chọn tháng bắt đầu và tháng kết thúc.");
            }

            // Chuyển fromDate thành ngày đầu tháng
            Calendar calFrom = Calendar.getInstance();
            calFrom.setTime(fromDate);
            calFrom.set(Calendar.DAY_OF_MONTH, 1);
            String fromDateString = new SimpleDateFormat("yyyy-MM-dd").format(calFrom.getTime());

            // Chuyển toDate thành ngày cuối tháng
            Calendar calTo = Calendar.getInstance();
            calTo.setTime(toDate);
            calTo.set(Calendar.DAY_OF_MONTH, calTo.getActualMaximum(Calendar.DAY_OF_MONTH));
            String toDateString = new SimpleDateFormat("yyyy-MM-dd").format(calTo.getTime());

            // Set các tham số ngày vào câu truy vấn
            p.setString(1, fromDateString);
            p.setString(2, toDateString);

            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                String thang = rs.getString("thang");
                String nam = rs.getString("nam");
                double doanhThu = rs.getDouble("doanhThu");
                double loiNhuan = rs.getDouble("loiNhuan");

                ThongKe_model tk = new ThongKe_model(thang, nam, doanhThu, loiNhuan);
                month_statistic.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return month_statistic;
    }

}
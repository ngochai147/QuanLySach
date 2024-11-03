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

    public Map<String, Integer> getStatisticIn7Days() {
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
            while (rs.next()) {
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
                    WITH DateRange AS (
                        SELECT CAST(GETDATE() AS DATE) AS ngayTaoDon
                        UNION ALL
                        SELECT DATEADD(DAY, -1, ngayTaoDon)
                        FROM DateRange
                        WHERE DATEADD(DAY, -1, ngayTaoDon) >= DATEADD(DAY, -7, GETDATE())
                    )
                    SELECT 
                        FORMAT(DateRange.ngayTaoDon, 'dd/MM') AS ngayTaoDon,
                        COALESCE(SUM(ChiTietHoaDon.soLuong * ChiTietHoaDon.donGia), 0) AS TongDoanhThu,
                        COALESCE(SUM(ChiTietHoaDon.soLuong * ChiTietHoaDon.donGia * 0.4), 0) AS LoiNhuan
                    FROM DateRange
                    LEFT JOIN HoaDon ON CAST(HoaDon.ngayTaoDon AS DATE) = DateRange.ngayTaoDon
                    LEFT JOIN ChiTietHoaDon ON HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon
                    GROUP BY DateRange.ngayTaoDon
                    ORDER BY DateRange.ngayTaoDon DESC;                     
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
        WITH DateRange AS (
            SELECT DATEADD(MONTH, number, CAST(? AS DATE)) AS ngayTaoDon
            FROM master.dbo.spt_values
            WHERE type = 'P' AND number BETWEEN 0 AND DATEDIFF(MONTH, CAST(? AS DATE), CAST(? AS DATE))
        )
        SELECT 
            MONTH(DateRange.ngayTaoDon) AS thang, 
            YEAR(DateRange.ngayTaoDon) AS nam, 
            COALESCE(SUM(cthd.donGia * cthd.soLuong), 0) AS doanhThu, 
            COALESCE(SUM(cthd.donGia * cthd.soLuong * 0.4), 0) AS loiNhuan
        FROM DateRange
        LEFT JOIN HoaDon AS hd ON CAST(hd.ngayTaoDon AS DATE) >= CAST(DateRange.ngayTaoDon AS DATE)
        LEFT JOIN ChiTietHoaDon AS cthd ON hd.maHoaDon = cthd.maHoaDon AND MONTH(hd.ngayTaoDon) = MONTH(DateRange.ngayTaoDon) AND YEAR(hd.ngayTaoDon) = YEAR(DateRange.ngayTaoDon)
        GROUP BY YEAR(DateRange.ngayTaoDon), MONTH(DateRange.ngayTaoDon)
        ORDER BY nam DESC, thang DESC
    """;

        try (Connection con = ConnectDB.getInstance().getConnection(); PreparedStatement p = con.prepareStatement(sql)) {

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
            p.setString(2, fromDateString);
            p.setString(3, toDateString);

            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                int thang = rs.getInt("thang");
                int nam = rs.getInt("nam");
                double doanhThu = rs.getDouble("doanhThu");
                double loiNhuan = rs.getDouble("loiNhuan");

                // Định dạng tháng và năm theo MM/yyyy
                String thangNam = String.format("%02d/%d", thang, nam);

                ThongKe_model tk = new ThongKe_model(thangNam, String.valueOf(nam), doanhThu, loiNhuan);
                month_statistic.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return month_statistic;
    }

    public List<ThongKe_model> getChartThongKeTheoNgay(Date fromDate, Date toDate) {
        List<ThongKe_model> day_statistic = new ArrayList<>();
        String sql = """
            WITH DateRange AS (
                SELECT CAST(? AS DATE) AS ngayTaoDon
                UNION ALL
                SELECT DATEADD(DAY, 1, ngayTaoDon)
                FROM DateRange
                WHERE DATEADD(DAY, 1, ngayTaoDon) <= CAST(? AS DATE)
            )
            SELECT 
                DAY(DateRange.ngayTaoDon) AS ngay, 
                MONTH(DateRange.ngayTaoDon) AS thang, 
                YEAR(DateRange.ngayTaoDon) AS nam, 
                COALESCE(SUM(cthd.donGia * cthd.soLuong), 0) AS doanhThu, 
                COALESCE(SUM(cthd.donGia * cthd.soLuong * 0.4), 0) AS loiNhuan
            FROM DateRange
            LEFT JOIN HoaDon AS hd ON CAST(hd.ngayTaoDon AS DATE) = DateRange.ngayTaoDon
            LEFT JOIN ChiTietHoaDon AS cthd ON hd.maHoaDon = cthd.maHoaDon
            GROUP BY YEAR(DateRange.ngayTaoDon), MONTH(DateRange.ngayTaoDon), DAY(DateRange.ngayTaoDon)
            ORDER BY nam DESC, thang DESC, ngay DESC
            """;

        try {
            Connection con = ConnectDB.getInstance().getConnection();
            PreparedStatement p = con.prepareStatement(sql);

            // Kiểm tra null cho ngày
            if (fromDate == null || toDate == null) {
                throw new IllegalArgumentException("Vui lòng chọn ngày bắt đầu và ngày kết thúc.");
            }

            // Chuyển fromDate và toDate thành định dạng chỉ ngày
            String fromDateString = new SimpleDateFormat("yyyy-MM-dd").format(fromDate);
            String toDateString = new SimpleDateFormat("yyyy-MM-dd").format(toDate);

            // Set các tham số ngày vào câu truy vấn
            p.setString(1, fromDateString);
            p.setString(2, toDateString);

            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                int ngay = rs.getInt("ngay");
                int thang = rs.getInt("thang");
                int nam = rs.getInt("nam");
                double doanhThu = rs.getDouble("doanhThu");
                double loiNhuan = rs.getDouble("loiNhuan");

                // Định dạng ngày tháng năm theo dd/MM/yyyy
                String ngayThangNam = String.format("%02d/%02d/%d", ngay, thang, nam);

                ThongKe_model tk = new ThongKe_model(ngayThangNam, String.valueOf(nam), doanhThu, loiNhuan);
                day_statistic.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return day_statistic;
    }

    public List<ThongKe_model> getChartThongKeTheoNam(Date fromDate, Date toDate) {
        List<ThongKe_model> nam_statistic = new ArrayList<>();
        String sql = """
        WITH DateRange AS (
            SELECT YEAR(DATEADD(YEAR, number, CAST(? AS DATE))) AS nam
            FROM master.dbo.spt_values
            WHERE type = 'P' AND number BETWEEN 0 AND DATEDIFF(YEAR, CAST(? AS DATE), CAST(? AS DATE))
        )
        SELECT 
            dr.nam, 
            COALESCE(SUM(cthd.donGia * cthd.soLuong), 0) AS doanhThu, 
            COALESCE(SUM(cthd.donGia * cthd.soLuong * 0.4), 0) AS loiNhuan
        FROM DateRange dr
        LEFT JOIN HoaDon AS hd ON YEAR(hd.ngayTaoDon) = dr.nam
        LEFT JOIN ChiTietHoaDon AS cthd ON hd.maHoaDon = cthd.maHoaDon
        GROUP BY dr.nam
        ORDER BY dr.nam DESC
    """;

        try (Connection con = ConnectDB.getInstance().getConnection(); PreparedStatement p = con.prepareStatement(sql)) {

            // Kiểm tra null cho ngày
            if (fromDate == null || toDate == null) {
                throw new IllegalArgumentException("Vui lòng chọn năm bắt đầu và năm kết thúc.");
            }

            // Chuyển fromDate và toDate thành định dạng chỉ năm
            String fromDateString = new SimpleDateFormat("yyyy").format(fromDate);
            String toDateString = new SimpleDateFormat("yyyy").format(toDate);

            // Set các tham số ngày vào câu truy vấn
            p.setString(1, fromDateString);
            p.setString(2, fromDateString);
            p.setString(3, toDateString);

            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                int nam = rs.getInt("nam");
                double doanhThu = rs.getDouble("doanhThu");
                double loiNhuan = rs.getDouble("loiNhuan");

                // Định dạng năm theo yyyy
                String ngayThangNam = String.format("%d", nam);

                ThongKe_model tk = new ThongKe_model(ngayThangNam, String.valueOf(nam), doanhThu, loiNhuan);
                nam_statistic.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return nam_statistic;
    }

}

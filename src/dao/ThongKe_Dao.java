package dao;

import chart.ModelPieChart;
import chart.PieChart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import connectDB.ConnectDB;
import entity.ThongKe_model;
import java.awt.Color;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

public class ThongKe_Dao {
    //

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

    public Map<String, Integer> getTongThongKe(Date fromDate, Date toDate) {
        String sql = """
        SELECT 
            COUNT(DISTINCT HoaDon.maHoaDon) AS TongSoHoaDon,
            SUM(ChiTietHoaDon.soLuong) AS TongSoLuongSach,
            SUM(ChiTietHoaDon.soLuong * ChiTietHoaDon.donGia) AS TongDoanhThu
        FROM HoaDon
        JOIN ChiTietHoaDon ON HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon
        WHERE ngayTaoDon BETWEEN ? AND ?
        """;

        Map<String, Integer> result = new HashMap<>();
        try (Connection con = ConnectDB.getInstance().getConnection(); 
             PreparedStatement p = con.prepareStatement(sql)) {
            p.setDate(1, new java.sql.Date(fromDate.getTime()));
            p.setDate(2, new java.sql.Date(toDate.getTime()));
            
            try (ResultSet rs = p.executeQuery()) {
                if (rs.next()) {
                    result.put("TongSoHoaDon", rs.getInt("TongSoHoaDon"));
                    result.put("TongSoLuongSach", rs.getInt("TongSoLuongSach"));
                    result.put("TongDoanhThu", rs.getInt("TongDoanhThu"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
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
            SELECT 
                DAY(hd.ngayTaoDon) AS ngay,
                MONTH(hd.ngayTaoDon) AS thang,
                YEAR(hd.ngayTaoDon) AS nam,
                COALESCE(SUM(cthd.donGia * cthd.soLuong), 0) AS doanhThu,
                COALESCE(SUM(cthd.donGia * cthd.soLuong * 0.4), 0) AS loiNhuan
            FROM HoaDon hd
            LEFT JOIN ChiTietHoaDon cthd ON hd.maHoaDon = cthd.maHoaDon
            WHERE CAST(hd.ngayTaoDon AS DATE) BETWEEN ? AND ?
            GROUP BY YEAR(hd.ngayTaoDon), MONTH(hd.ngayTaoDon), DAY(hd.ngayTaoDon)
            ORDER BY nam DESC, thang DESC, ngay DESC;
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
                String ngaytk = String.format("%d/%d", ngay, thang);

                ThongKe_model tk = new ThongKe_model(ngaytk, String.valueOf(nam), doanhThu, loiNhuan);
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

    public void showPieChart_LoaiSach(PieChart pieChart, Date tuNgay, Date denNgay) {

        String sql = """
                select ls.tenLoai, SUM(cthd.donGia*cthd.soLuong) as doanhThu
                from HoaDon hd 
                join ChiTietHoaDon cthd
                on hd.maHoaDon = cthd.maHoaDon
                join Sach s 
                on cthd.ISBN = s.ISBN
                join LoaiSach ls
                on ls.maLoai = s.maLoaiSach
                where hd.ngayTaoDon BETWEEN ? AND ?
                group by ls.tenLoai
                order by ls.tenLoai
                """;

        // Using try-with-resources to automatically close the resources
        try (Connection con = ConnectDB.getInstance().getConnection(); PreparedStatement p = con.prepareStatement(sql)) {

            // Set the dates as java.sql.Date to ensure correct format for the database
            p.setDate(1, new java.sql.Date(tuNgay.getTime()));
            p.setDate(2, new java.sql.Date(denNgay.getTime()));

            try (ResultSet rs = p.executeQuery()) {
                pieChart.clearData();
                int index = 0;
                while (rs.next()) {
                    String tenLoai = rs.getString("tenLoai");
                    double doanhThu = rs.getDouble("doanhThu");
                    // Handle color index in a circular way to avoid exceeding the available colors
                    pieChart.addData(new ModelPieChart(tenLoai, doanhThu, getColor(index++)));
                }
                p.close();
                rs.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showPieChart_TacGia(PieChart pieChart, Date tuNgay, Date denNgay) {

        String sql = """
                select s.tacGia, SUM(cthd.donGia*cthd.soLuong) as doanhThu
                from HoaDon hd 
                join ChiTietHoaDon cthd
                on hd.maHoaDon = cthd.maHoaDon
                join Sach s 
                on cthd.ISBN = s.ISBN
                join LoaiSach ls
                on ls.maLoai = s.maLoaiSach
                where hd.ngayTaoDon BETWEEN ? AND ?
                group by s.tacGia
                order by s.tacGia
                """;

        try (Connection con = ConnectDB.getInstance().getConnection(); PreparedStatement p = con.prepareStatement(sql)) {

            p.setDate(1, new java.sql.Date(tuNgay.getTime()));
            p.setDate(2, new java.sql.Date(denNgay.getTime()));

            try (ResultSet rs = p.executeQuery()) {
                pieChart.clearData();
                int index = 0;
                while (rs.next()) {
                    String tacGia = rs.getString("tacGia");
                    double doanhThu = rs.getDouble("doanhThu");
                    // Handle color index in a circular way to avoid exceeding the available colors
                    pieChart.addData(new ModelPieChart(tacGia, doanhThu, getColor(index++)));
                }
                p.close();
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Color getColor(int index) {
        Random random = new Random(index);
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return new Color(red, green, blue);
    }

    // Hàm tải dữ liệu từ cơ sở dữ liệu và trả về danh sách các đối tượng thống kê
    public List<ThongKe_model> loadStatisticData(Date fromDate, Date toDate) {
        List<ThongKe_model> statisticData = new ArrayList<>();

        String sql = """
            SELECT 
                hd.maHoaDon, 
                s.tenSach, 
                s.tacGia, 
                ls.tenLoai, 
                cthd.soLuong, 
                cthd.donGia, 
                cthd.donGia * cthd.soLuong AS doanhThu, 
                (cthd.donGia * cthd.soLuong * 0.4) AS loiNhuan
            FROM HoaDon hd
            JOIN ChiTietHoaDon cthd ON hd.maHoaDon = cthd.maHoaDon
            JOIN Sach s ON cthd.ISBN = s.ISBN
            JOIN LoaiSach ls ON s.maLoaiSach = ls.maLoai
            WHERE hd.ngayTaoDon BETWEEN ? AND ?
        """;

        try (Connection con = ConnectDB.getInstance().getConnection(); PreparedStatement p = con.prepareStatement(sql)) {

            // Chuyển đổi từ Date sang java.sql.Date
            p.setDate(1, new java.sql.Date(fromDate.getTime()));
            p.setDate(2, new java.sql.Date(toDate.getTime()));

            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                String maHoaDon = rs.getString("maHoaDon");
                String tenSach = rs.getString("tenSach");
                String tacGia = rs.getString("tacGia");
                String tenLoai = rs.getString("tenLoai");
                int soLuong = rs.getInt("soLuong");
                double donGia = rs.getDouble("donGia");
                double doanhThu = rs.getDouble("doanhThu");
                double loiNhuan = rs.getDouble("loiNhuan");

                // Tạo đối tượng ThongKeModel để lưu thông tin
                ThongKe_model model = new ThongKe_model(maHoaDon, tenSach, tacGia, tenLoai, soLuong, donGia, doanhThu, loiNhuan);
                statisticData.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statisticData;
    }
}

package dao;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.toedter.calendar.JDateChooser;
import connectDB.ConnectDB;
import javax.swing.*;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.Date;

public class ExportExcel_DAO {

    public void exportExcel(PreparedStatement p, String filePath) {
        try (ResultSet rs = p.executeQuery()) {

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("ThongKe");

            // Tạo tiêu đề
            createHeader(sheet, rs.getMetaData());

            // Ghi dữ liệu từ ResultSet vào Excel
            int rowNum = 1;
            while (rs.next()) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    Cell cell = row.createCell(i - 1);
                    cell.setCellValue(rs.getString(i));
                }
            }

            // Ghi file Excel
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createHeader(Sheet sheet, ResultSetMetaData metaData) throws SQLException {
        Row headerRow = sheet.createRow(0);
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            Cell cell = headerRow.createCell(i - 1);
            cell.setCellValue(metaData.getColumnName(i));
        }
    }

    public void exportExcel_Ngay(String filePath, Date fromDate, Date toDate) {
        String sql = """
        SELECT FORMAT(ngayTaoDon, 'dd-MM-yyyy') AS Ngay,
            COUNT(DISTINCT HoaDon.maHoaDon) AS tongSoLuongHoaDon,
            SUM(ChiTietHoaDon.soLuong) AS tongSoLuongSachDaBan,
            SUM(ChiTietHoaDon.soLuong * ChiTietHoaDon.donGia) AS TongDoanhThu,
            SUM(ChiTietHoaDon.soLuong * ChiTietHoaDon.donGia * 0.2) AS LoiNhuan
        FROM HoaDon
        JOIN ChiTietHoaDon ON HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon
        WHERE ngayTaoDon BETWEEN ? AND ?
        GROUP BY FORMAT(ngayTaoDon, 'dd-MM-yyyy')
        ORDER BY Ngay;
    """;
        try (Connection con = ConnectDB.getInstance().getConnection(); PreparedStatement p = con.prepareStatement(sql)) {

            p.setDate(1, new java.sql.Date(fromDate.getTime()));
            p.setDate(2, new java.sql.Date(toDate.getTime()));
            exportExcel(p, filePath);  // Thay đổi ở đây
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void exportExcel_Thang(String filePath, Date fromDate, Date toDate) {
        String sql = """
            SELECT FORMAT(ngayTaoDon, 'MM-yyyy') AS Thang,
                COUNT(DISTINCT HoaDon.maHoaDon) AS tongSoLuongHoaDon,
                SUM(ChiTietHoaDon.soLuong) AS tongSoLuongSachDaBan,
                SUM(ChiTietHoaDon.soLuong * ChiTietHoaDon.donGia) AS TongDoanhThu,
                SUM(ChiTietHoaDon.soLuong * ChiTietHoaDon.donGia * 0.2) AS LoiNhuan
            FROM HoaDon
            JOIN ChiTietHoaDon ON HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon
            WHERE ngayTaoDon BETWEEN ? AND ?
            GROUP BY FORMAT(ngayTaoDon, 'MM-yyyy')
            ORDER BY Thang;
        """;
        try (Connection con = ConnectDB.getInstance().getConnection(); PreparedStatement p = con.prepareStatement(sql)) {

            p.setDate(1, new java.sql.Date(fromDate.getTime()));
            p.setDate(2, new java.sql.Date(toDate.getTime()));
            exportExcel(p, filePath);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void exportExcel_Nam(String filePath, Date fromDate, Date toDate) {
        String sql = """
            SELECT FORMAT(ngayTaoDon, 'yyyy') AS Nam,
                COUNT(DISTINCT HoaDon.maHoaDon) AS tongSoLuongHoaDon,
                SUM(ChiTietHoaDon.soLuong) AS tongSoLuongSachDaBan,
                SUM(ChiTietHoaDon.soLuong * ChiTietHoaDon.donGia) AS TongDoanhThu,
                SUM(ChiTietHoaDon.soLuong * ChiTietHoaDon.donGia * 0.2) AS LoiNhuan
            FROM HoaDon
            JOIN ChiTietHoaDon ON HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon
            WHERE ngayTaoDon BETWEEN ? AND ?
            GROUP BY FORMAT(ngayTaoDon, 'yyyy')
            ORDER BY Nam;
        """;
        try (Connection con = ConnectDB.getInstance().getConnection(); PreparedStatement p = con.prepareStatement(sql)) {

            p.setDate(1, new java.sql.Date(fromDate.getTime()));
            p.setDate(2, new java.sql.Date(toDate.getTime()));
            exportExcel(p, filePath);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public class ExportData {

        private JDateChooser fromDateChooser; // JDateChooser cho ngày bắt đầu
        private JDateChooser toDateChooser;   // JDateChooser cho ngày kết thúc

        public ExportData(JDateChooser fromDateChooser, JDateChooser toDateChooser) {
            this.fromDateChooser = fromDateChooser;
            this.toDateChooser = toDateChooser;
        }

        public void exportData(String type, String filePath) {
            Date fromDate = fromDateChooser.getDate();
            Date toDate = toDateChooser.getDate();

            if (fromDate == null || toDate == null) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn cả hai ngày.");
                return;
            }

            // Đảm bảo rằng fromDate nhỏ hơn hoặc bằng toDate
            if (fromDate.after(toDate)) {
                JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc.");
                return;
            }

            switch (type) {
                case "ngay":
                    exportExcel_Ngay(filePath, fromDate, toDate);
                    break;
                case "thang":
                    exportExcel_Thang(filePath, fromDate, toDate);
                    break;
                case "nam":
                    exportExcel_Nam(filePath, fromDate, toDate);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Loại xuất không hợp lệ.");
                    break;
            }
        }
    }
}

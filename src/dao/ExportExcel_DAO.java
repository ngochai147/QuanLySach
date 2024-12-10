package dao;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.toedter.calendar.JDateChooser;
import connectDB.ConnectDB;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Date;

public class ExportExcel_DAO {
    public void exportExcel(PreparedStatement p, String filePath) {
        try (ResultSet rs = p.executeQuery()) {

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("ThongKe");

            // Tạo CellStyle cho tiền tệ VND
            CellStyle currencyStyle = workbook.createCellStyle();
            DataFormat format = workbook.createDataFormat();
            currencyStyle.setDataFormat(format.getFormat("#,##0 \"VND\""));

            // Tạo tiêu đề
            createHeader(sheet, rs.getMetaData());

            // Ghi dữ liệu từ ResultSet vào Excel
            int rowNum = 1;
            while (rs.next()) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    Cell cell = row.createCell(i - 1);
                    String columnName = rs.getMetaData().getColumnName(i);

                    // Áp dụng định dạng VND cho các cột 'TongDoanhThu' và 'LoiNhuan'
                    if ("TongDoanhThu".equals(columnName) || "LoiNhuan".equals(columnName)) {
                        cell.setCellValue(rs.getDouble(i));
                        cell.setCellStyle(currencyStyle); // Định dạng tiền tệ
                    } else {
                        cell.setCellValue(rs.getString(i));
                    }
                }
            }
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                sheet.autoSizeColumn(i);
            }
            // Ghi file Excel
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                JOptionPane.showMessageDialog(null, "Xuất file Excel thành công: " + filePath);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Lỗi khi ghi file Excel: " + e.getMessage());
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
                    SELECT CONVERT(varchar, ngayTaoDon, 103) AS Ngay,
                           COUNT(DISTINCT HoaDon.maHoaDon) AS tongSoLuongHoaDon,
                           SUM(ChiTietHoaDon.soLuong) AS tongSoLuongSachDaBan,
                           SUM(ChiTietHoaDon.soLuong * ChiTietHoaDon.donGia) AS TongDoanhThu,
                           SUM(ChiTietHoaDon.soLuong * ChiTietHoaDon.donGia * 0.4) AS LoiNhuan
                    FROM HoaDon
                    JOIN ChiTietHoaDon ON HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon
                    WHERE ngayTaoDon BETWEEN ? AND ?
                    GROUP BY CONVERT(varchar, ngayTaoDon, 103)
                    ORDER BY Ngay;
                """;
        executeExport(filePath, fromDate, toDate, sql);
    }

    public void exportExcel_Thang(String filePath, Date fromDate, Date toDate) {
        String sql = """
                    SELECT RIGHT(CONVERT(varchar, ngayTaoDon, 103), 7) AS Thang,
                           COUNT(DISTINCT HoaDon.maHoaDon) AS tongSoLuongHoaDon,
                           SUM(ChiTietHoaDon.soLuong) AS tongSoLuongSachDaBan,
                           SUM(ChiTietHoaDon.soLuong * ChiTietHoaDon.donGia) AS TongDoanhThu,
                           SUM(ChiTietHoaDon.soLuong * ChiTietHoaDon.donGia * 0.4) AS LoiNhuan
                    FROM HoaDon
                    JOIN ChiTietHoaDon ON HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon
                    WHERE ngayTaoDon BETWEEN ? AND ?
                    GROUP BY RIGHT(CONVERT(varchar, ngayTaoDon, 103), 7)
                    ORDER BY Thang;
                """;
        executeExport(filePath, fromDate, toDate, sql);
    }

    public void exportExcel_Nam(String filePath, Date fromDate, Date toDate) {
        String sql = """
                    SELECT YEAR(ngayTaoDon) AS Nam,
                           COUNT(DISTINCT HoaDon.maHoaDon) AS tongSoLuongHoaDon,
                           SUM(ChiTietHoaDon.soLuong) AS tongSoLuongSachDaBan,
                           SUM(ChiTietHoaDon.soLuong * ChiTietHoaDon.donGia) AS TongDoanhThu,
                           SUM(ChiTietHoaDon.soLuong * ChiTietHoaDon.donGia * 0.4) AS LoiNhuan
                    FROM HoaDon
                    JOIN ChiTietHoaDon ON HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon
                    WHERE ngayTaoDon BETWEEN ? AND ?
                    GROUP BY YEAR(ngayTaoDon)
                    ORDER BY Nam;
                """;
        executeExport(filePath, fromDate, toDate, sql);
    }

    private void executeExport(String filePath, Date fromDate, Date toDate, String sql) {
        try (Connection con = ConnectDB.getInstance().getConnection();
             PreparedStatement p = con.prepareStatement(sql)) {

            p.setDate(1, new java.sql.Date(fromDate.getTime()));
            p.setDate(2, new java.sql.Date(toDate.getTime()));
            exportExcel(p, filePath);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public class ExportData {

        private JDateChooser fromDateChooser;
        private JDateChooser toDateChooser;

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

            if (fromDate.after(toDate)) {
                JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc.");
                return;
            }

            switch (type) {
                case "ngay" -> exportExcel_Ngay(filePath, fromDate, toDate);
                case "thang" -> exportExcel_Thang(filePath, fromDate, toDate);
                case "nam" -> exportExcel_Nam(filePath, fromDate, toDate);
                default -> JOptionPane.showMessageDialog(null, "Loại xuất không hợp lệ.");
            }
        }
    }


    public void exportThongKeTonKhoToExcel(String filePath) {
        String sql = """
                SELECT 
                    k.maKhoHang AS 'Mã Kho',
                    k.TenKho AS 'Tên Kho',
                    k.SucChua AS 'Sức Chứa',
                    ctkh.soLuong AS 'Số Lượng Tồn Kho',
                    s.TenSach AS 'Tên Sách',
                    s.TacGia AS 'Tác Giả',
                    s.TrangThai AS 'Trạng Thái'
                FROM KhoHang k
                JOIN ChiTietKhoHang as ctkh on k.maKhoHang = ctkh.maKhoHang
                join Sach as s on s.ISBN = ctkh.ISBN
                GROUP BY k.maKhoHang, k.TenKho, k.SucChua, s.tenSach, s.trangThai, s.tacGia, ctkh.soLuong	
                ORDER BY k.maKhoHang, s.TenSach        
                """;

        try {
            Connection conn = ConnectDB.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            Workbook workbook = new XSSFWorkbook();
            // Tạo sheet và đặt tên
            Sheet sheet = workbook.createSheet("ThongKeTonKho");

            // Tạo header cho các cột
            String[] headers = {
                    "Mã Kho", "Tên Kho", "Sức Chứa", "Số Lượng Tồn Kho",
                    "Tên Sách", "Tác Giả", "Trạng Thái"
            };

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Duyệt qua từng dòng kết quả và ghi vào file Excel
            int rowNum = 1;
            while (resultSet.next()) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(resultSet.getString("Mã kho"));
                row.createCell(1).setCellValue(resultSet.getString("Tên Kho"));
                row.createCell(2).setCellValue(resultSet.getInt("Sức Chứa"));
                row.createCell(3).setCellValue(resultSet.getInt("Số Lượng Tồn Kho"));
                row.createCell(4).setCellValue(resultSet.getString("Tên Sách"));
                row.createCell(5).setCellValue(resultSet.getString("Tác Giả"));
                row.createCell(6).setCellValue(resultSet.getString("Trạng Thái"));
            }

            // Tự động điều chỉnh độ rộng các cột
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Ghi file Excel ra đĩa
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                System.out.println("Xuất file Excel thành công!");
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi file Excel: " + e.getMessage());
        }
    }
}

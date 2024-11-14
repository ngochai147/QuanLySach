package view;

import dao.PhieuXuat_DAO;
import dao.Sach_DAO;
import entity.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Report_PhieuXuatKho {
    private PhieuXuat_DAO dsPXK = new PhieuXuat_DAO();

    private Sach_DAO dss = new Sach_DAO();

    public static String formatNgayLap(LocalDate ngayLap) {
        if (ngayLap == null) {
            return "Ngày không xác định"; // Trả về chuỗi mặc định nếu `ngayLap` là null
        }

        int day = ngayLap.getDayOfMonth();   // Lấy ngày
        int month = ngayLap.getMonthValue(); // Lấy tháng
        int year = ngayLap.getYear();        // Lấy năm

        // Ghép chuỗi theo định dạng "Ngày ... tháng ... năm ..."
        return String.format("Ngày %d tháng %d năm %d", day, month, year);
    }

    public void ViewReport_PhieuXuatKho(ArrayList<ChiTietPhieuXuatKho> dsCTPXK, String maPhieuXuatKho) {
        try {
            PhieuXuatKho pxk = dsPXK.getPhieuXuatKhoTheoMaPXK(maPhieuXuatKho);

            LocalDate nl = pxk.getNgayLap();
            String ngayLap = formatNgayLap(nl);

            // Nạp file .jrxml
            InputStream reportStream = getClass().getResourceAsStream("/img/Report_PhieuXuatKho.jrxml");
            if (reportStream == null) {
                System.out.println("Không tìm thấy file .jrxml");
                return;
            }
            JasperReport reportPay = JasperCompileManager.compileReport(reportStream);

            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("maPhieuXuatKho", maPhieuXuatKho);
            parameters.put("tenNV", pxk.getNhanVien().getHoTen());
            parameters.put("tenKhoHangXuat", pxk.getKhoHangXuat().getTenKho());
            parameters.put("tenKhoHangNhap", pxk.getKhoHangNhap().getTenKho());
            parameters.put("diaChi", pxk.getKhoHangNhap().getDiaChi());
            parameters.put("ngayLap", ngayLap);

            List<FieldCTPNK> field = new ArrayList<>();
            DecimalFormat df = new DecimalFormat("#,###");

            double tongTien = 0;
            for (ChiTietPhieuXuatKho ctpx : dsCTPXK) {
                String tenSach = "";
                String loaiSach = "";
                double giaGoc = ctpx.getSach().getGiaGoc();
                for (Sach s : dss.getAllSP()) {
                    if (s.getISBN().equalsIgnoreCase(ctpx.getSach().getISBN())) {
                        tenSach = s.getTenSach();
                        loaiSach = s.getLoaiSach().getTenLoai();
                    }
                }
                field.add(new FieldCTPNK(ctpx.getSach().getISBN(), tenSach, loaiSach, ctpx.getSoLuong(), giaGoc));
                tongTien += ctpx.getSoLuong() * giaGoc;
            }

            String formattedTongTien = df.format(tongTien);
            parameters.put("tongTien", formattedTongTien);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(field);
            JasperPrint print = JasperFillManager.fillReport(reportPay, parameters, dataSource);

            // Lưu báo cáo dưới dạng PDF vào thư mục chỉ định
            String outputPath = "test/PhieuXuatKho/" + maPhieuXuatKho + ".pdf";
            JasperExportManager.exportReportToPdfFile(print, outputPath);
//            System.out.println("Báo cáo PDF đã được tạo thành công tại: " + outputPath);

            // Hiển thị báo cáo
            JasperViewer.viewReport(print, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}

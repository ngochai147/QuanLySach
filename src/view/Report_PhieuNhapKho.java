package view;

import dao.ChiTietPhieuNhap_DAO;
import dao.PhieuNhap_DAO;
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

public class Report_PhieuNhapKho {
    private PhieuNhap_DAO dsPN = new PhieuNhap_DAO();
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

    public void ViewReport_PhieuNhapKho(ArrayList<ChiTietPhieuNhapKho> dsCTPNK, String maPhieuNhapKho) {
        try {
//            System.out.println("Mã PNK:" + maPhieuNhapKho);
            PhieuNhapKho pnk = dsPN.getPhieuNhapKhoTheoMaPNK(maPhieuNhapKho);

            System.out.println("Ma phieu nhap kho: " + pnk);

            LocalDate nl = pnk.getNgayLap();
            String ngayLap = formatNgayLap(nl);

            // Nạp file .jrxml
            InputStream reportStream = getClass().getResourceAsStream("/img/Report_PhieuNhapKho.jrxml");
            if (reportStream == null) {
                System.out.println("Không tìm thấy file .jrxml");
                return;
            }
            JasperReport reportPay = JasperCompileManager.compileReport(reportStream);

            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("maPhieuNhapKho", maPhieuNhapKho);
            parameters.put("tenNV", pnk.getNhanVien().getHoTen());
            parameters.put("tenKhoHangNhap", pnk.getKhoHangNhap().getTenKho());
            parameters.put("diaChi", pnk.getKhoHangNhap().getDiaChi());
            parameters.put("ngayLap", ngayLap);

            List<FieldCTPNK> field = new ArrayList<>();
            DecimalFormat df = new DecimalFormat("#,###");

            double tongTien = 0;
            for (ChiTietPhieuNhapKho ctpn : dsCTPNK) {
                String tenSach = "";
                String loaiSach = "";
                double giaGoc = ctpn.getSach().getGiaGoc();
                for (Sach s : dss.getAllSP()) {
                    if (s.getISBN().equalsIgnoreCase(ctpn.getSach().getISBN())) {
                        tenSach = s.getTenSach();
                        loaiSach = s.getLoaiSach().getTenLoai();
                    }
                }
                field.add(new FieldCTPNK(ctpn.getSach().getISBN(), tenSach, loaiSach, ctpn.getSoLuong(), giaGoc));
                tongTien += ctpn.getSoLuong() * giaGoc;
            }

            String formattedTongTien = df.format(tongTien);
            parameters.put("tongTien", formattedTongTien);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(field);
            JasperPrint print = JasperFillManager.fillReport(reportPay, parameters, dataSource);

            // Hiển thị báo cáo
            JasperViewer.viewReport(print, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}

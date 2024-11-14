/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package function;

/**
 *
 * @author PC
 */

import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.Sach_DAO;
import entity.ChiTietHoaDon;
import entity.FieldCTHD;
import entity.HoaDon;
import entity.Sach;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import view.DangNhap;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PhieuHoaDon {
private HoaDon_DAO dsHD = new HoaDon_DAO();
private List<HoaDon> listhD = dsHD.getAllHoaDon();
private Sach_DAO dss = new Sach_DAO();
private List<Sach> sachList = dss.getAllSP();

private ChiTietHoaDon_DAO dsCTHD = new ChiTietHoaDon_DAO();
private List<ChiTietHoaDon> listCTHD = dsCTHD.getAllChiTietHoaDon();
private    DateTimeFormatter dfDay= DateTimeFormatter.ofPattern("dd-MM-YYYY");



    public void viewReport(ArrayList<ChiTietHoaDon> dsCTHD, String maHoaDon,double tienKhachTra) {
    try {
        // Biên dịch file .jrxml thành file .jasper
        JasperReport reportPay = JasperCompileManager.compileReport(getClass().getResourceAsStream("/img/report_pay.jrxml"));
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("maHD",maHoaDon);
        parameters.put("maNV", DangNhap.ma);
        parameters.put("ngayTao", dfDay.format(LocalDate.now()));

        List<FieldCTHD> field = new ArrayList<>();
//
        DecimalFormat df = new DecimalFormat("#,###");

        double tongTien = 0;
        int tongSL = 0;
        for (ChiTietHoaDon cthd : dsCTHD) {

            String tenSach = "";
            for (Sach s : dss.getAllSP()) {
                if (s.getISBN().equalsIgnoreCase(cthd.getSach().getISBN())) {
                    tenSach = s.getTenSach();
                }
            }
            field.add(new FieldCTHD(tenSach, cthd.getSoLuong(), cthd.getDonGia()));
            tongSL += cthd.getSoLuong();
            tongTien += cthd.getSoLuong() * cthd.getDonGia();
        }

        double tienThua=tienKhachTra-tongTien;
        // Giả sử đây là số bạn muốn định dạng
        String formattedTongTien = df.format(tongTien);
        parameters.put("tongSL", tongSL);
        parameters.put("tongTien", formattedTongTien);
        parameters.put("tienKhachTra",df.format(tienKhachTra));
        parameters.put("tienThua",df.format(tienThua));
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(field);
        JasperPrint print = JasperFillManager.fillReport(reportPay, parameters, dataSource);
        // Hiển thị báo cáo bằng JasperViewer
        JasperViewer.viewReport(print, false); // false để không đóng ứng dụng khi tắt cửa sổ báo cáo
    } catch (JRException e) {
        e.printStackTrace(); // In ra lỗi nếu có
    }
}
    public void viewHoaDon(ArrayList<ChiTietHoaDon> dsCTHD, String maHoaDon) {
        try {
            // Biên dịch file .jrxml thành file .jasper
            JasperReport reportPay = JasperCompileManager.compileReport(getClass().getResourceAsStream("/img/report_HoaDon.jrxml"));
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("maHD",maHoaDon);
            parameters.put("maNV", DangNhap.ma);
            for(HoaDon hd: dsHD.getAllHoaDon()){
                if(maHoaDon.equalsIgnoreCase(hd.getMaHoaDon())){
                    parameters.put("ngayTao", dfDay.format(hd.getNgayTaoDon()));
                }
            }

            List<FieldCTHD> field = new ArrayList<>();
//
            DecimalFormat df = new DecimalFormat("#,###");

            double tongTien = 0;
            int tongSL = 0;
            for (ChiTietHoaDon cthd : dsCTHD) {

                String tenSach = "";
                for (Sach s : dss.getAllSP()) {
                    if (s.getISBN().equalsIgnoreCase(cthd.getSach().getISBN())) {
                        tenSach = s.getTenSach();
                    }
                }
                field.add(new FieldCTHD(tenSach, cthd.getSoLuong(), cthd.getDonGia()));
                tongSL += cthd.getSoLuong();
                tongTien += cthd.getSoLuong() * cthd.getDonGia();
            }


            // Giả sử đây là số bạn muốn định dạng
            String formattedTongTien = df.format(tongTien);
            parameters.put("tongSL", tongSL);
            parameters.put("tongTien", formattedTongTien);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(field);
            JasperPrint print = JasperFillManager.fillReport(reportPay, parameters, dataSource);
            // Hiển thị báo cáo bằng JasperViewer
            JasperViewer.viewReport(print, false); // false để không đóng ứng dụng khi tắt cửa sổ báo cáo
        } catch (JRException e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }
    }
}


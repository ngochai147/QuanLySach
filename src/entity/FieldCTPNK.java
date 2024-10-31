package entity;

import java.text.DecimalFormat;

public class FieldCTPNK {
    private String ISBN;
    private String tenSach;
    private String loaiSach;
    private int soLuong;
    private double giaGoc;
    private String formattedGiaGoc;
    private String formattedThanhTien;

    // Constructor
    public FieldCTPNK(String ISBN, String tenSach, String loaiSach, int soLuong, double giaGoc) {
        this.ISBN = ISBN;
        this.tenSach = tenSach;
        this.loaiSach = loaiSach;
        this.soLuong = soLuong;
        this.giaGoc = giaGoc;

        // Định dạng donGia và thanhTien khi khởi tạo đối tượng
        DecimalFormat df = new DecimalFormat("#,###");
        this.formattedGiaGoc = df.format(giaGoc);
        double thanhTien = giaGoc * soLuong;  // Tính tổng tiền
        this.formattedThanhTien = df.format(thanhTien);
    }

    // Getters
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getLoaiSach() {
        return loaiSach;
    }

    public void setLoaiSach(String loaiSach) {
        this.loaiSach = loaiSach;
    }

    public double getDonGia() {
        return giaGoc;
    }

    public void setDonGia(double donGia) {
        this.giaGoc = donGia;
    }

    public String getFormattedGiaGoc() {
        return formattedGiaGoc;
    }
    public String getFormattedThanhTien() {
        return formattedThanhTien;
    }

    @Override
    public String toString() {
        return "ChiTietPhieuNhapKho{" +
                "sach=" + tenSach +
                ", loaiSach=" + loaiSach +
                ", soLuong=" + soLuong +
                ", donGia=" + giaGoc +
                '}';
    }
}

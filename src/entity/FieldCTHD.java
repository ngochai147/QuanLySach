package entity;

import java.text.DecimalFormat;

public class FieldCTHD {
    private String tenSach;
    private int SL;
    private double donGia;
    private String formattedDonGia;
    private String formattedThanhTien;

    // Constructor
    public FieldCTHD(String tenSach, int SL, double donGia) {
        this.tenSach = tenSach;
        this.SL = SL;
        this.donGia = donGia;

        // Định dạng donGia và thanhTien khi khởi tạo đối tượng
        DecimalFormat df = new DecimalFormat("#,###");
        this.formattedDonGia = df.format(donGia);
        double thanhTien = donGia * SL;  // Tính tổng tiền
        this.formattedThanhTien = df.format(thanhTien);
    }

    // Getters
    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getSL() {
        return SL;
    }

    public void setSL(int soLuong) {
        this.SL = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public String getFormattedDonGia() {
        return formattedDonGia;
    }
    public String getFormattedThanhTien() {
        return formattedThanhTien;
    }



    @Override
    public String toString() {
        return "ChiTietHoaDon{" +
                "sach=" + tenSach +
                ", soLuong=" + SL +
                ", donGia=" + donGia +
                '}';
    }
}

package entity;

import java.awt.Color;

/**
 *
 * @author phamd
 */
public class ThongKe_model {

    private String tenSach;
    private String maHoaDon;
    private int soLuong;
    private Double donGia;
    private String Date, Month, Year;
    private Double doanhThu;
    private Double loiNhuan;
    private String tacGia;
    private String loaiSach;
    private Color color;

    public ThongKe_model() {

    }

    public ThongKe_model(String maHoaDon, String tenSach, String tacGia, String loaiSach, int soLuong, Double donGia, Double doanhThu, Double loiNhuan) {
        this.tenSach = tenSach;
        this.maHoaDon = maHoaDon;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.doanhThu = doanhThu;
        this.loiNhuan = loiNhuan;
        this.tacGia = tacGia;
        this.loaiSach = loaiSach;
    }

    public ThongKe_model(String Date, String Month, String Year, Double doanhThu, Double loiNhuan) {
        this.Date = Date;
        this.Month = Month;
        this.Year = Year;
        this.doanhThu = doanhThu;
        this.loiNhuan = loiNhuan;
    }

    public ThongKe_model(String Date, Double doanhThu, Double loiNhuan) {
        this.Date = Date;
        this.doanhThu = doanhThu;
        this.loiNhuan = loiNhuan;
    }

    public ThongKe_model(String thang, String nam, double doanhThu, double loiNhuan) {
        this.Month = thang;
        this.Year = nam;
        this.doanhThu = doanhThu;
        this.loiNhuan = loiNhuan;
    }

    public ThongKe_model(String tacGia, double doanhThu, Color color) {
        this.tacGia = tacGia;
        this.doanhThu = doanhThu;
        this.color = color;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public double getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(double doanhThu) {
        this.doanhThu = doanhThu;
    }

    public Double getLoiNhuan() {
        return loiNhuan;
    }

    public void setLoiNhuan(Double loiNhuan) {
        this.loiNhuan = loiNhuan;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getLoaiSach() {
        return loaiSach;
    }

    public void setLoaiSach(String loaiSach) {
        this.loaiSach = loaiSach;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

}

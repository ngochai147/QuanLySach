package entity;

import java.util.Objects;

public class ChiTietHoaDon {
    private String maChiTietHoaDon;
    private HoaDon hoaDon;
    private Sach sach;
    private int soLuong;
    private double donGia;

    public ChiTietHoaDon(){

    }
    public ChiTietHoaDon(String maChiTietHoaDon){
        this.maChiTietHoaDon=maChiTietHoaDon;
    }

    public ChiTietHoaDon(String maChiTietHoaDon, HoaDon hoaDon, Sach sach, int soLuong, double donGia) {
        this.maChiTietHoaDon = maChiTietHoaDon;
        this.hoaDon = hoaDon;
        this.sach = sach;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getMaChiTietHoaDon() {
        return maChiTietHoaDon;
    }

    public void setMaChiTietHoaDon(String maChiTietHoaDon) {
        this.maChiTietHoaDon = maChiTietHoaDon;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
        ChiTietHoaDon that = (ChiTietHoaDon) o;
        return Objects.equals(maChiTietHoaDon, that.maChiTietHoaDon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maChiTietHoaDon);
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon{" +
                "maChiTietHoaDon='" + maChiTietHoaDon + '\'' +
                ", hoaDon=" + hoaDon +
                ", sach=" + sach +
                ", soLuong=" + soLuong +
                ", donGia=" + donGia +
                '}';
    }
}

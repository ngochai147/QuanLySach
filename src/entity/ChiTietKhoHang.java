package entity;

import java.util.Objects;

public class ChiTietKhoHang {
    private  String maChiTietKhoHang;
    private Sach sach;
    private  KhoHang khoHang;
    private int soLuong;
    public ChiTietKhoHang(){

    }
    public ChiTietKhoHang(String maChiTietKhoHang){
        this.maChiTietKhoHang=maChiTietKhoHang;
    }

    public ChiTietKhoHang(String maChiTietKhoHang, int soLuong, Sach sach, KhoHang khoHang) {
        this.maChiTietKhoHang = maChiTietKhoHang;
        this.sach = sach;
        this.khoHang = khoHang;
        this.soLuong = soLuong;
    }

    public String getMaChiTietKhoHang() {
        return maChiTietKhoHang;
    }

    public void setMaChiTietKhoHang(String maChiTietKhoHang) {
        this.maChiTietKhoHang = maChiTietKhoHang;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public KhoHang getKhoHang() {
        return khoHang;
    }

    public void setKhoHang(KhoHang khoHang) {
        this.khoHang = khoHang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
        ChiTietKhoHang that = (ChiTietKhoHang) o;
        return Objects.equals(maChiTietKhoHang, that.maChiTietKhoHang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maChiTietKhoHang);
    }

    @Override
    public String toString() {
        return "ChiTietKhoHang{" +
                "maChiTietKhoHang='" + maChiTietKhoHang + '\'' +
                ", sach=" + sach +
                ", khoHang=" + khoHang +
                ", soLuong=" + soLuong +
                '}';
    }
}


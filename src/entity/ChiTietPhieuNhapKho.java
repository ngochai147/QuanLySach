package entity;

import java.util.Objects;

public class ChiTietPhieuNhapKho {
    private String maChiTietPhieuNhapKho;
    private PhieuNhapKho phieuNhapKho;
    private int soLuong;
    private Sach sach;

    public ChiTietPhieuNhapKho(String maChiTietPhieuNhapKho, String maPhieuNhapKho, int soLuong, String bn){

    }
    public ChiTietPhieuNhapKho(String maChiTietPhieuNhapKho){
        this.maChiTietPhieuNhapKho=maChiTietPhieuNhapKho;
    }

    public ChiTietPhieuNhapKho(String maChiTietPhieuNhapKho, PhieuNhapKho phieuNhapKho, int soLuong, Sach sach) {
        this.maChiTietPhieuNhapKho = maChiTietPhieuNhapKho;
        this.phieuNhapKho = phieuNhapKho;
        this.soLuong = soLuong;
        this.sach = sach;
    }

    public String getMaChiTietPhieuNhapKho() {
        return maChiTietPhieuNhapKho;
    }

    public void setMaChiTietPhieuNhapKho(String maChiTietPhieuNhapKho) {
        this.maChiTietPhieuNhapKho = maChiTietPhieuNhapKho;
    }

    public PhieuNhapKho getPhieuNhapKho() {
        return phieuNhapKho;
    }

    public void setPhieuNhapKho(PhieuNhapKho phieuNhapKho) {
        this.phieuNhapKho = phieuNhapKho;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
        ChiTietPhieuNhapKho that = (ChiTietPhieuNhapKho) o;
        return Objects.equals(maChiTietPhieuNhapKho, that.maChiTietPhieuNhapKho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maChiTietPhieuNhapKho);
    }

    @Override
    public String toString() {
        return "ChiTietPhieuNhapKho{" +
                "maChiTietPhieuNhapKho='" + maChiTietPhieuNhapKho + '\'' +
                ", phieuNhapKho=" + phieuNhapKho +
                ", soLuong=" + soLuong +
                ", sach=" + sach +
                '}';
    }
}

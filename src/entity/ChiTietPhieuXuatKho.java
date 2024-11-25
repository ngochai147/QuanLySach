package entity;

import java.util.Objects;

public class ChiTietPhieuXuatKho {
    private String maChiTietPhieuXuatKho;
    private PhieuXuatKho phieuXuatKho;
    private int soLuong;
    private Sach sach;

    public ChiTietPhieuXuatKho(String maChiTietPhieuXuatKho, PhieuXuatKho phieuXuatKho, int soLuong, Sach sach) {
        this.maChiTietPhieuXuatKho = maChiTietPhieuXuatKho;
        this.phieuXuatKho = phieuXuatKho;
        this.soLuong = soLuong;
        this.sach = sach;
    }

    public String getMaChiTietPhieuXuatKho() {
        return maChiTietPhieuXuatKho;
    }

    public void setMaChiTietPhieuXuatKho(String maChiTietPhieuXuatKho) {
        this.maChiTietPhieuXuatKho = maChiTietPhieuXuatKho;
    }

    public PhieuXuatKho getPhieuXuatKho() {
        return phieuXuatKho;
    }

    public void setPhieuXuatKho(PhieuXuatKho phieuXuatKho) {
        this.phieuXuatKho = phieuXuatKho;
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
        ChiTietPhieuXuatKho that = (ChiTietPhieuXuatKho) o;
        return Objects.equals(maChiTietPhieuXuatKho, that.maChiTietPhieuXuatKho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maChiTietPhieuXuatKho);
    }

    @Override
    public String toString() {
        return "ChiTietPhieuXuatKho{" +
                "maChiTietPhieuXuatKho='" + maChiTietPhieuXuatKho + '\'' +
                ", phieuXuatKho=" + phieuXuatKho +
                ", soLuong=" + soLuong +
                ", sach=" + sach +
                '}';
    }
}

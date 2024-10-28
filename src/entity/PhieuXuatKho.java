package entity;

import java.time.LocalDate;
import java.util.Objects;

public class PhieuXuatKho {
    private  String maPhieuXuatKho;
    private LocalDate ngayLap;
    private NhanVien nhanVien;
    private KhoHang khoHangXuat;
    private KhoHang khoHangNhap;
    private  int soLuong;

    public PhieuXuatKho(){

    }
    public PhieuXuatKho(String maPhieuXuatKho){
        this.maPhieuXuatKho=maPhieuXuatKho;

    }

    public PhieuXuatKho(String maPhieuXuatKho, LocalDate ngayLap, NhanVien nhanVien, KhoHang khoHangXuat, KhoHang khoHangNhap, int soLuong) {
        this.maPhieuXuatKho = maPhieuXuatKho;
        this.ngayLap = ngayLap;
        this.nhanVien = nhanVien;
        this.khoHangXuat = khoHangXuat;
        this.khoHangNhap = khoHangNhap;
        this.soLuong = soLuong;
    }

    public String getMaPhieuXuatKho() {
        return maPhieuXuatKho;
    }

    public void setMaPhieuXuatKho(String maPhieuXuatKho) {
        this.maPhieuXuatKho = maPhieuXuatKho;
    }

    public LocalDate getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDate ngayLap) {
        this.ngayLap = ngayLap;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public KhoHang getKhoHangXuat() {
        return khoHangXuat;
    }

    public void setKhoHangXuat(KhoHang khoHangXuat) {
        this.khoHangXuat = khoHangXuat;
    }

    public KhoHang getKhoHangNhap() {
        return khoHangNhap;
    }

    public void setKhoHangNhap(KhoHang khoHangNhap) {
        this.khoHangNhap = khoHangNhap;
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
        PhieuXuatKho that = (PhieuXuatKho) o;
        return Objects.equals(maPhieuXuatKho, that.maPhieuXuatKho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maPhieuXuatKho);
    }

    @Override
    public String toString() {
        return "PhieuXuatKho{" +
                "maPhieuXuatKho='" + maPhieuXuatKho + '\'' +
                ", ngayLap=" + ngayLap +
                ", nhanVien=" + nhanVien +
                ", khoHangXuat=" + khoHangXuat +
                ", khoHangNhap=" + khoHangNhap +
                ", soLuong=" + soLuong +
                '\'' +
                '}';
    }
}

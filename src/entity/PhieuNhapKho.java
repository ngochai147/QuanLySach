package entity;

import java.time.LocalDate;
import java.util.Objects;

public class PhieuNhapKho {
    private  String maPhieuNhapKho;
    private LocalDate ngayLap;
    private NhanVien nhanVien;
    private KhoHang khoHangNhap;
    private KhoHang khoHangXuat;
    private  int soLuong;

    public PhieuNhapKho(){

    }
    public PhieuNhapKho(String maPhieuNhapKho){
        this.maPhieuNhapKho=maPhieuNhapKho;

    }

    public PhieuNhapKho(String maPhieuNhapKho, LocalDate ngayLap, NhanVien nhanVien, KhoHang khoHangNhap, KhoHang khoHangXuat, int soLuong) {
        this.maPhieuNhapKho = maPhieuNhapKho;
        this.ngayLap = ngayLap;
        this.nhanVien = nhanVien;
        this.khoHangNhap = khoHangNhap;
        this.khoHangXuat = khoHangXuat;
        this.soLuong = soLuong;
    }

    public String getMaPhieuNhapKho() {
        return maPhieuNhapKho;
    }

    public void setMaPhieuNhapKho(String maPhieuNhapKho) {
        this.maPhieuNhapKho = maPhieuNhapKho;
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

    public KhoHang getKhoHangNhap() {
        return khoHangNhap;
    }

    public void setKhoHangNhap(KhoHang khoHangNhap) {
        this.khoHangNhap = khoHangNhap;
    }

    public KhoHang getKhoHangXuat() {
        return khoHangXuat;
    }

    public void setKhoHangXuat(KhoHang khoHangXuat) {
        this.khoHangXuat = khoHangXuat;
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
        PhieuNhapKho that = (PhieuNhapKho) o;
        return Objects.equals(maPhieuNhapKho, that.maPhieuNhapKho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maPhieuNhapKho);
    }

    @Override
    public String toString() {
        return "PhieuNhapKho{" +
                "maPhieuNhapKho='" + maPhieuNhapKho + '\'' +
                ", ngayLap=" + ngayLap +
                ", nhanVien=" + nhanVien +
                ", khoHangNhap=" + khoHangNhap +
                ", khoHangXuat=" + khoHangXuat +
                ", soLuong=" + soLuong +
                '\'' +
                '}';
    }
}

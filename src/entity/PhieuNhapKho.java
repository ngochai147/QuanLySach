package entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class PhieuNhapKho {
    private  String maPhieuNhapKho;
    private LocalDate ngayLap;
    private NhanVien nhanVien;
    private KhoHang khoHangNhap;
    private  int soLuong;

    public PhieuNhapKho(String maPNK, Date ngayLap, NhanVien nhanVien, KhoHang khoHang, int soLuong){

    }
    public PhieuNhapKho(String maPhieuNhapKho){
        this.maPhieuNhapKho=maPhieuNhapKho;

    }

    public PhieuNhapKho(String maPhieuNhapKho, LocalDate ngayLap, NhanVien nhanVien, KhoHang khoHangNhap, int soLuong) {
        this.maPhieuNhapKho = maPhieuNhapKho;
        this.ngayLap = ngayLap;
        this.nhanVien = nhanVien;
        this.khoHangNhap = khoHangNhap;
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
                ", soLuong=" + soLuong +
                '\'' +
                '}';
    }
}

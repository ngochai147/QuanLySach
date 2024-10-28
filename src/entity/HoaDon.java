package entity;

import java.time.LocalDate;
import java.util.Objects;

public class HoaDon {
    private  String maHoaDon;
    private LocalDate ngayTaoDon;
    private  NhanVien nhanVien;

    public HoaDon(){

    }
    public HoaDon(String maHoaDon){
        this.maHoaDon=maHoaDon;
    }

    public HoaDon(String maHoaDon, LocalDate ngayTaoDon, NhanVien nhanVien) {
        this.maHoaDon = maHoaDon;
        this.ngayTaoDon = ngayTaoDon;
        this.nhanVien = nhanVien;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public LocalDate getNgayTaoDon() {
        return ngayTaoDon;
    }

    public void setNgayTaoDon(LocalDate ngayTaoDon) {
        this.ngayTaoDon = ngayTaoDon;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
        HoaDon hoaDon = (HoaDon) o;
        return Objects.equals(maHoaDon, hoaDon.maHoaDon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maHoaDon);
    }

    @Override
    public String toString() {
        return "HoaDon{" +
                "maHoaDon='" + maHoaDon + '\'' +
                ", ngayTaoDon=" + ngayTaoDon +
                ", nhanVien=" + nhanVien +
                '}';
    }
}

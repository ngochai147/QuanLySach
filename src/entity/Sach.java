package entity;

import java.util.Objects;

public class Sach {
    private  String ISBN;
    private  String tenSach;
    private  String tacGia;
    private  int namXB;
    private String nhaXB;
    private int soLuong;
    private double giaGoc;
    private LoaiSach loaiSach;
    private HinhAnh anh;
    private String trangThai;
    
    public Sach(String ISBN){
        this.ISBN=ISBN;
    }
    public Sach(){}
    public Sach(String ISBN, String tenSach, String tacGia, int namXB, String nhaXB, int soLuong, double giaGoc, LoaiSach loaiSach, HinhAnh anh, String trangThai) {
        this.ISBN = ISBN;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.namXB = namXB;
        this.nhaXB = nhaXB;
        this.soLuong = soLuong;
        this.giaGoc = giaGoc;
        this.loaiSach = loaiSach;
        this.anh = anh;
        this.trangThai = trangThai;
    }
    public Sach(String ISBN, String tenSach, String tacGia, int namXB, String nhaXB, int soLuong, double giaGoc, LoaiSach loaiSach) {
        this.ISBN = ISBN;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.namXB = namXB;
        this.nhaXB = nhaXB;
        this.soLuong = soLuong;
        this.giaGoc = giaGoc;
        this.loaiSach = loaiSach;
    }
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public int getNamXB() {
        return namXB;
    }

    public void setNamXB(int namXB) {
        this.namXB = namXB;
    }

    public String getNhaXB() {
        return nhaXB;
    }

    public void setNhaXB(String nhaXB) {
        this.nhaXB = nhaXB;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaGoc() {
        return giaGoc;
    }

    public void setGiaGoc(double giaGoc) {
        this.giaGoc = giaGoc;
    }

    public LoaiSach getLoaiSach() {
        return loaiSach;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public void setLoaiSach(LoaiSach loaiSach) {
        this.loaiSach = loaiSach;
    }
    public HinhAnh getAnh(){
        return anh;
    }
    public void setAnh(HinhAnh anh){
        this.anh = anh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
        Sach sach = (Sach) o;
        return Objects.equals(ISBN, sach.ISBN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ISBN);
    }

    @Override
    public String toString() {
        return "Sach{" +
                "ISBN='" + ISBN + '\'' +
                ", tenSach='" + tenSach + '\'' +
                ", tacGia='" + tacGia + '\'' +
                ", namXB=" + namXB +
                ", nhaXB='" + nhaXB + '\'' +
                ", soLuong=" + soLuong +
                ", giaGoc=" + giaGoc +
                ", loaiSach=" + loaiSach +
                '}';
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author phamd
 */
public class ThongKeTonKho_model {
    private String ISBN;
    private String tenSach;
    private String tenKho;
    private int soLuongTonKho;

    public int getTongTonKho() {
        return tongTonKho;
    }

    public void setTongTonKho(int tongTonKho) {
        this.tongTonKho = tongTonKho;
    }
    private String trangThai;
    private String tacGia;
    private int tongTonKho;

    public ThongKeTonKho_model(String tenKho, int tongTonKho, int sucChua) {
        this.tenKho = tenKho;
        this.tongTonKho = tongTonKho;
        this.sucChua = sucChua;
    }

    public ThongKeTonKho_model(String ISBN, String tenKho, int tongTonKho) {
        this.ISBN = ISBN;
        this.tenKho = tenKho;
        this.tongTonKho = tongTonKho;
    }
    
    

    public int getSucChua() {
        return sucChua;
    }

    public void setSucChua(int sucChua) {
        this.sucChua = sucChua;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    private int sucChua;
    private String diaChi;

    // Constructor
    public ThongKeTonKho_model(String ISBN, String tenSach, String tacGia, String tenKho,String diaChi, int soLuongTonKho,int sucChua, String trangThai) {
        this.ISBN = ISBN;
        this.tenSach = tenSach;
        this.tenKho = tenKho;
        this.soLuongTonKho = soLuongTonKho;
        this.trangThai = trangThai;
        this.tacGia = tacGia;
        this.sucChua = sucChua;
        this.diaChi = diaChi;
    }

    // Getter và Setter
    public String getISBN() { return ISBN; }
    public void setISBN(String ISBN) { this.ISBN = ISBN; }

    public String getTenSach() { return tenSach; }
    public void setTenSach(String tenSach) { this.tenSach = tenSach; }

    public String getTenKho() { return tenKho; }
    public void setTenKho(String tenKho) { this.tenKho = tenKho; }

    public int getSoLuongTonKho() { return soLuongTonKho; }
    public void setSoLuongTonKho(int soLuongTonKho) { this.soLuongTonKho = soLuongTonKho; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
    
    public String getTacGia(){
        return tacGia;
    }
    public void setTacGia(String tacGia){
        this.tacGia = tacGia;
    }
}

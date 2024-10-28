package entity;

import java.util.Objects;

public class KhoHang {
    private  String maKhoHang;
    private String tenKho;
    private  int sucChua;
    private  String diaChi;

    public KhoHang(){

    }
    public KhoHang(String maKhoHang){
        this.maKhoHang=maKhoHang;
    }

    public KhoHang(String maKhoHang, String tenKho, int sucChua, String diaChi) {
        this.maKhoHang = maKhoHang;
        this.tenKho = tenKho;
        this.sucChua = sucChua;
        this.diaChi = diaChi;
    }

    public String getMaKhoHang() {
        return maKhoHang;
    }

    public void setMaKhoHang(String maKhoHang) {
        this.maKhoHang = maKhoHang;
    }

    public String getTenKho() {
        return tenKho;
    }

    public void setTenKho(String tenKho) {
        this.tenKho = tenKho;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
        KhoHang khoHang = (KhoHang) o;
        return Objects.equals(maKhoHang, khoHang.maKhoHang);
    }
    @Override
    public int hashCode() {
        return Objects.hash(maKhoHang);
    }
    @Override
    public String toString() {
        return "KhoHang{" +
                "maKhoHang='" + maKhoHang + '\'' +
                ", tenKho='" + tenKho + '\'' +
                ", sucChua='" + sucChua + '\'' +
                ", diaChi='" + diaChi + '\'' +
                '}';
    }
}


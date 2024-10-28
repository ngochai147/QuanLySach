package entity;

import java.util.Objects;

public class TaiKhoan {
    private String maTK;
    private NhanVien nhanVien;
    private String matKhau;

    public  TaiKhoan(){
    }

    public TaiKhoan(String maTK, NhanVien nhanVien, String matKhau) {
        this.maTK=maTK;
        this.nhanVien=nhanVien;
        this.matKhau=matKhau;

    }

    public String getMaTK() {
        return maTK;
    }

    public void setMaTK(String maNV) {
        this.maTK = maNV;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
        TaiKhoan taiKhoan = (TaiKhoan) o;
        return Objects.equals(maTK, taiKhoan.maTK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maTK);
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "maNV='" + maTK + '\'' +
                ", tenDangNhap='" + nhanVien + '\'' +
                ", matKhau='" + matKhau + '\'' +
                '}';
    }
}

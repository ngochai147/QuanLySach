package entity;

import java.time.LocalDate;

public class ThuKho extends Nguoi{
    private String maThuKho;
    public  ThuKho(){

    }
    public ThuKho(String maThuKho){
        this.maThuKho=maThuKho;
    }

    public ThuKho(String hoTen, String soDienThoai, String diaChi, String email, boolean gioiTinh, LocalDate ngaySinh, String maThuKho, KhoHang khoHang) {
        super(hoTen, soDienThoai, diaChi, email, gioiTinh, ngaySinh);
        this.maThuKho = maThuKho;
    }

    public String getMaThuKho() {
        return maThuKho;
    }

    public void setMaThuKho(String maThuKho) {
        this.maThuKho = maThuKho;
    }


    @Override
    public String toString() {
        return "ThuKho{" +
                "maThuKho='" + maThuKho + '\'' +
                '}';
    }
}

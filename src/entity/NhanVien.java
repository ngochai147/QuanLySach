package entity;
import java.time.LocalDate;
import java.util.Objects;

public class NhanVien extends Nguoi{
    private  String maNV;
    private ChucVu chucVu;
    private HinhAnh anh;
    private String trangThai;

    
    public NhanVien(String maNV){
        super();
        this.maNV=maNV;
    }
    public NhanVien(String hoTen, String soDienThoai, String diaChi, String email, boolean gioiTinh, LocalDate ngaySinh, String maNV, ChucVu chucVu, HinhAnh anh, String trangThai) {
        super(hoTen, soDienThoai, diaChi, email, gioiTinh, ngaySinh);
        this.maNV = maNV;
        this.chucVu = chucVu;
        this.anh = anh;
        this.trangThai = trangThai;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public NhanVien(String maNV, ChucVu chucVu) {
        super();
        this.maNV = maNV;
        this.chucVu = chucVu;
    }
    public NhanVien() {
         // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public HinhAnh getAnh() {
        return anh;
    }

    public void setAnh(HinhAnh anh) {
        this.anh = anh;
    }

    public ChucVu getChucVu() {
        return chucVu;
    }

    public void setChucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NhanVien other = (NhanVien) obj;
        return Objects.equals(this.maNV, other.maNV);
    }


    @Override
    public String toString() {
        return "NhanVien{" +
                "maNV='" + maNV + '\'' +
                "ngaySinh=" +getNgaySinh()+'\''+
                ", chucVu=" + chucVu +
                ", anh=" + anh +
                '}';
    }
}

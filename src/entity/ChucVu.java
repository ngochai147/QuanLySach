package entity;

public class ChucVu {
    
    private  String chucVu;
    public  ChucVu(String chucVu){
        this.chucVu=chucVu;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    @Override
    public String toString() {
        return "ChucVu{" +
                "chucVu='" + chucVu + '\'' +
                '}';
    }
}

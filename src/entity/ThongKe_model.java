
package entity;

/**
 *
 * @author phamd
 */
public class ThongKe_model {
    private String Date, Month, Year;
    private Double doanhThu;
    private Double loiNhuan;

    public ThongKe_model() {

    }

    public ThongKe_model(String Date, String Month, String Year, Double doanhThu, Double loiNhuan) {
        this.Date = Date;
        this.Month = Month;
        this.Year = Year;
        this.doanhThu = doanhThu;
        this.loiNhuan = loiNhuan;
    }

    public ThongKe_model(String Date, Double doanhThu, Double loiNhuan) {
        this.Date = Date;
        this.doanhThu = doanhThu;
        this.loiNhuan = loiNhuan;
    }

    public ThongKe_model(String thang, String nam, double doanhThu, double loiNhuan) {
        this.Month = thang;
        this.Year = nam;
        this.doanhThu = doanhThu;
        this.loiNhuan = loiNhuan;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public double getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(double doanhThu) {
        this.doanhThu = doanhThu;
    }

    public Double getLoiNhuan() {
        return loiNhuan;
    }

    public void setLoiNhuan(Double loiNhuan) {
        this.loiNhuan = loiNhuan;
    }
}

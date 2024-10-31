package dao;

public class ExportExcel_DAO {
    public void exportExcel() {
        String sql = """
                select FORMAT(ngayTaoDon, 'MM-yyyy') as thang,
                					count(DISTINCT HoaDon.maHoaDon) as tongSoLuongHoaDon,
                					SUM(ChiTietHoaDon.soLuong) as tongSoLuongSachDaBan,
                					Sum(ChiTietHoaDon.soLuong * ChiTietHoaDon.donGia) as TongDoanhThu,
                                    Sum(ChiTietHoaDon.soLuong * ChiTietHoaDon.donGia * 0.2) as LoiNhuan						              \s
                                    from HoaDon
                                    join ChiTietHoaDon
                                    on HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon
                                    group by FORMAT(ngayTaoDon, 'MM-yyyy')
                                    order by thang""";
    }
}

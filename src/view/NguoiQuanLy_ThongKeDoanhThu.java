/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import java.awt.Color;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import chart.ModelChart_BarChart;
import dao.ExportExcel_DAO;
import dao.ThongKe_Dao;
import entity.ThongKe_model;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author phamd
 */
public class NguoiQuanLy_ThongKeDoanhThu extends javax.swing.JInternalFrame {

    /**
     * Creates new form NguoiQuanLy_ThongKeDoanhThu
     */
    NguoiQuanLy_ThongKeTongQuan thongKeTongQuan = new NguoiQuanLy_ThongKeTongQuan();
    final ThongKe_Dao thongKe_Dao = new ThongKe_Dao();
    final ExportExcel_DAO excel_DAO = new ExportExcel_DAO();
    private String selectedPeriod = "";
    private final Color clickColor = new Color(153, 153, 0);
    private final Color defaultColor = new Color(102, 102, 0);
    private Date tuNgay;
    private Date denNgay;

    public NguoiQuanLy_ThongKeDoanhThu() {
        initComponents();
        setupInternalFrame(this);
        barChart1.addLegend("Doanh Thu", Color.red);
        barChart1.addLegend("Lợi nhuận", Color.blue);
        jPanel1.setVisible(false);
    }

    public void formatVNDJLablel(JLabel jLabel, Integer value) {
        if (value == null) {
            value = 0; // Giá trị mặc định
        }
        DecimalFormat vndFormat = (DecimalFormat) NumberFormat.getNumberInstance();
        vndFormat.setGroupingUsed(true); // Thêm dấu phân cách hàng nghìn
        vndFormat.setMaximumFractionDigits(0); // Không hiển thị phần lẻ

        String formattedValue = vndFormat.format(value) + " VND"; // Thêm hậu tố "VND" không có $
        jLabel.setText(formattedValue);
    }

    public static void setupInternalFrame(JInternalFrame frame) {
        frame.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) frame.getUI();
        ui.setNorthPane(null);
    }

    public void Restart_JdesktopPane() {
        jDateChooser_From.setDate(null);
        jDateChooser_To.setDate(null);

    }

    public void setDataIntoBarChart_Ngay(Date tuNgay, Date denNgay) {
        List<ThongKe_model> data = thongKe_Dao.getChartThongKeTheoNgay(tuNgay, denNgay);
        for (int i = data.size() - 1; i >= 0; i--) {
            ThongKe_model tk = data.get(i);
            barChart1.addData(new ModelChart_BarChart(tk.getMonth(), new double[]{tk.getDoanhThu(), tk.getLoiNhuan()}));
        }
    }

    public void setDataIntoBarChart_Thang(Date tuNgay, Date denNgay) {
        List<ThongKe_model> data = thongKe_Dao.getChartThongKeTheoThang(tuNgay, denNgay);
        for (int i = data.size() - 1; i >= 0; i--) {
            ThongKe_model tk = data.get(i);
            barChart1.addData(new ModelChart_BarChart(tk.getMonth(), new double[]{tk.getDoanhThu(), tk.getLoiNhuan()}));
        }
    }

    public void setDataIntoBarChart_Nam(Date tuNgay, Date denNgay) {
        List<ThongKe_model> data = thongKe_Dao.getChartThongKeTheoNam(tuNgay, denNgay);
        for (int i = data.size() - 1; i >= 0; i--) {
            ThongKe_model tk = data.get(i);
            barChart1.addData(new ModelChart_BarChart(tk.getYear(), new double[]{tk.getDoanhThu(), tk.getLoiNhuan()}));
        }
    }

    private void loadDataIntoTable(JTable table, Date tuNgay, Date denNgay) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        int stt = 1;
        List<ThongKe_model> list = thongKe_Dao.loadStatisticData(tuNgay, denNgay);
        for (ThongKe_model thongKe : list) {
            model.addRow(new Object[]{
                stt++,
                thongKe.getMaHoaDon(),
                thongKe.getTenSach(),
                thongKe.getTacGia(),
                thongKe.getLoaiSach(),
                thongKe.getSoLuong(),
                thongKe.getDonGia(),
                thongKe.getDoanhThu(),
                thongKe.getLoiNhuan()
            });

        }

    }

    private void LamMoi() {
        barChart1.clear();
        pieChart_LoaiSach.clearData();
        pieChart_TacGia.clearData();
        jLabel_SLHD.setText("0");
        jLabel_DT.setText("0");
        jLabel_SLS.setText("0");
        DefaultTableModel model = (DefaultTableModel) jTable_DoanhThu.getModel();
        model.setRowCount(0);
        jDateChooser_From.setDate(null);
        jDateChooser_To.setDate(null);
    }

    private Date[] getDateRangeByPeriod(String period, Date tuNgay, Date denNgay) {
        Calendar cal = Calendar.getInstance();
        Date chartTuNgay = null;
        Date chartDenNgay = null;

        switch (period) {
            case "ngay":
                chartTuNgay = tuNgay;
                chartDenNgay = denNgay;
                break;
            case "thang":
                cal.setTime(tuNgay);
                cal.set(Calendar.DAY_OF_MONTH, 1); // Ngày đầu tháng
                chartTuNgay = cal.getTime();

                cal.setTime(denNgay);
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); // Ngày cuối tháng
                chartDenNgay = cal.getTime();
                break;
            case "nam":
                cal.setTime(tuNgay);
                cal.set(Calendar.MONTH, 0); // Tháng 1
                cal.set(Calendar.DAY_OF_MONTH, 1); // Ngày đầu năm
                chartTuNgay = cal.getTime();

                cal.setTime(denNgay);
                cal.set(Calendar.MONTH, 11); // Tháng 12
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); // Ngày cuối năm
                chartDenNgay = cal.getTime();
                break;
            default:
                break;
        }

        return new Date[]{chartTuNgay, chartDenNgay};
    }

    private void updateStatistics(Map<String, Integer> thongKe) {
        jLabel_SLS.setText(String.valueOf(thongKe.getOrDefault("TongSoLuongSach", 0)));
        jLabel_SLHD.setText(String.valueOf(thongKe.getOrDefault("TongSoHoaDon", 0)));
        formatVNDJLablel(jLabel_DT, thongKe.getOrDefault("TongDoanhThu", 0));
    }

    private void updateChartsAndTable(Date chartTuNgay, Date chartDenNgay) {
        barChart1.clear();
        switch (selectedPeriod) {
            case "ngay":
                setDataIntoBarChart_Ngay(chartTuNgay, chartDenNgay);
                break;
            case "thang":
                setDataIntoBarChart_Thang(chartTuNgay, chartDenNgay);
                break;
            case "nam":
                setDataIntoBarChart_Nam(chartTuNgay, chartDenNgay);
                break;
            default:
                break;
        }
        barChart1.start();

        thongKe_Dao.showPieChart_LoaiSach(pieChart_LoaiSach, chartTuNgay, chartDenNgay);
        thongKe_Dao.showPieChart_TacGia(pieChart_TacGia, chartTuNgay, chartDenNgay);
        loadDataIntoTable(jTable_DoanhThu, chartTuNgay, chartDenNgay);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_DoanhThu = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel_SLDonHang = new javax.swing.JPanel();
        jLabel_TongSLHD = new javax.swing.JLabel();
        jLabel_SLHD = new javax.swing.JLabel();
        jScrollPane_DoanhThu = new javax.swing.JScrollPane();
        jTable_DoanhThu = new javax.swing.JTable();
        jPanel_TongDoanhThu = new javax.swing.JPanel();
        jLabel_DoanhThu = new javax.swing.JLabel();
        jLabel_DT = new javax.swing.JLabel();
        jPanel_SL_Sach = new javax.swing.JPanel();
        jLabel_SoLuongSach = new javax.swing.JLabel();
        jLabel_SLS = new javax.swing.JLabel();
        jPanel_barChart = new javax.swing.JPanel();
        barChart1 = new chart.BarChart();
        jPanel_PieChartLoaiSach = new javax.swing.JPanel();
        jLabel_LoaiSach = new javax.swing.JLabel();
        pieChart_LoaiSach = new chart.PieChart();
        jPanel_PieChartTacGia = new javax.swing.JPanel();
        jLabel_TacGia = new javax.swing.JLabel();
        pieChart_TacGia = new chart.PieChart();
        jPanel_Back = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel_Ngay = new javax.swing.JPanel();
        jLabel_Ngay = new javax.swing.JLabel();
        jPanel_Thang = new javax.swing.JPanel();
        jLabel_Thang = new javax.swing.JLabel();
        jPanel_Nam = new javax.swing.JPanel();
        jLabel_Nam = new javax.swing.JLabel();
        jDateChooser_From = new com.toedter.calendar.JDateChooser();
        jDateChooser_To = new com.toedter.calendar.JDateChooser();
        jButton_Xem = new javax.swing.JButton();
        jButton_XuatExcel = new javax.swing.JButton();
        jButton_LamMoi = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1500, 677));

        jPanel_DoanhThu.setLayout(null);

        jPanel1.setOpaque(false);

        jPanel_SLDonHang.setBackground(new java.awt.Color(102, 102, 0));
        jPanel_SLDonHang.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_SLDonHang.setPreferredSize(new java.awt.Dimension(336, 50));

        jLabel_TongSLHD.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel_TongSLHD.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_TongSLHD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_TongSLHD.setText("Tổng số lượng đơn hàng");

        jLabel_SLHD.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel_SLHD.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel_SLDonHangLayout = new javax.swing.GroupLayout(jPanel_SLDonHang);
        jPanel_SLDonHang.setLayout(jPanel_SLDonHangLayout);
        jPanel_SLDonHangLayout.setHorizontalGroup(
            jPanel_SLDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SLDonHangLayout.createSequentialGroup()
                .addComponent(jLabel_TongSLHD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_SLHD, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel_SLDonHangLayout.setVerticalGroup(
            jPanel_SLDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SLDonHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_SLDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel_TongSLHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_SLHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTable_DoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã đơn hàng", "Tên sách", "Tác giả", "Loại sách", "Số lượng bán", "Đơn giá", "Doanh thu", "Lợi nhuận"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable_DoanhThu.getTableHeader().setReorderingAllowed(false);
        jScrollPane_DoanhThu.setViewportView(jTable_DoanhThu);

        jPanel_TongDoanhThu.setBackground(new java.awt.Color(102, 102, 0));
        jPanel_TongDoanhThu.setForeground(new java.awt.Color(255, 255, 255));

        jLabel_DoanhThu.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel_DoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_DoanhThu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_DoanhThu.setText("Tổng doanh thu");

        jLabel_DT.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel_DT.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel_TongDoanhThuLayout = new javax.swing.GroupLayout(jPanel_TongDoanhThu);
        jPanel_TongDoanhThu.setLayout(jPanel_TongDoanhThuLayout);
        jPanel_TongDoanhThuLayout.setHorizontalGroup(
            jPanel_TongDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TongDoanhThuLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jLabel_DoanhThu)
                .addGap(18, 18, 18)
                .addComponent(jLabel_DT, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_TongDoanhThuLayout.setVerticalGroup(
            jPanel_TongDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TongDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_TongDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel_DT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_DoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel_SL_Sach.setBackground(new java.awt.Color(102, 102, 0));
        jPanel_SL_Sach.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel_SL_Sach.setForeground(new java.awt.Color(255, 255, 255));

        jLabel_SoLuongSach.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel_SoLuongSach.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_SoLuongSach.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_SoLuongSach.setText("Tổng số lượng sách đã bán");

        jLabel_SLS.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel_SLS.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel_SL_SachLayout = new javax.swing.GroupLayout(jPanel_SL_Sach);
        jPanel_SL_Sach.setLayout(jPanel_SL_SachLayout);
        jPanel_SL_SachLayout.setHorizontalGroup(
            jPanel_SL_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SL_SachLayout.createSequentialGroup()
                .addComponent(jLabel_SoLuongSach)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_SLS, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_SL_SachLayout.setVerticalGroup(
            jPanel_SL_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_SL_SachLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_SL_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_SLS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_SoLuongSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        barChart1.setBackground(new java.awt.Color(255, 255, 255));
        barChart1.setPreferredSize(new java.awt.Dimension(300, 461));

        javax.swing.GroupLayout jPanel_barChartLayout = new javax.swing.GroupLayout(jPanel_barChart);
        jPanel_barChart.setLayout(jPanel_barChartLayout);
        jPanel_barChartLayout.setHorizontalGroup(
            jPanel_barChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barChart1, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
        );
        jPanel_barChartLayout.setVerticalGroup(
            jPanel_barChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_barChartLayout.createSequentialGroup()
                .addComponent(barChart1, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel_PieChartLoaiSach.setBackground(new java.awt.Color(255, 255, 255));

        jLabel_LoaiSach.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_LoaiSach.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel_LoaiSach.setText("Biểu đồ tỉ lệ doanh thu theo loại sách ");

        pieChart_LoaiSach.setBackground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout jPanel_PieChartLoaiSachLayout = new javax.swing.GroupLayout(jPanel_PieChartLoaiSach);
        jPanel_PieChartLoaiSach.setLayout(jPanel_PieChartLoaiSachLayout);
        jPanel_PieChartLoaiSachLayout.setHorizontalGroup(
            jPanel_PieChartLoaiSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pieChart_LoaiSach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_PieChartLoaiSachLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel_LoaiSach)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_PieChartLoaiSachLayout.setVerticalGroup(
            jPanel_PieChartLoaiSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_PieChartLoaiSachLayout.createSequentialGroup()
                .addComponent(pieChart_LoaiSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_LoaiSach)
                .addContainerGap())
        );

        jPanel_PieChartTacGia.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_PieChartTacGia.setPreferredSize(new java.awt.Dimension(353, 400));

        jLabel_TacGia.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_TacGia.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel_TacGia.setText("Biểu đồ tỉ lệ doanh thu theo tác giả ");

        pieChart_TacGia.setBackground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout jPanel_PieChartTacGiaLayout = new javax.swing.GroupLayout(jPanel_PieChartTacGia);
        jPanel_PieChartTacGia.setLayout(jPanel_PieChartTacGiaLayout);
        jPanel_PieChartTacGiaLayout.setHorizontalGroup(
            jPanel_PieChartTacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pieChart_TacGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_PieChartTacGiaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel_TacGia)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel_PieChartTacGiaLayout.setVerticalGroup(
            jPanel_PieChartTacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_PieChartTacGiaLayout.createSequentialGroup()
                .addComponent(pieChart_TacGia, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_TacGia)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1529, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel_SLDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel_TongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel_SL_Sach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(4, 4, 4)
                            .addComponent(jScrollPane_DoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 1160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel_PieChartTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6)
                            .addComponent(jPanel_PieChartLoaiSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6)
                            .addComponent(jPanel_barChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel_SLDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4)
                            .addComponent(jPanel_TongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4)
                            .addComponent(jPanel_SL_Sach, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane_DoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(14, 14, 14)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel_barChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel_PieChartLoaiSach, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel_PieChartTacGia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel_DoanhThu.add(jPanel1);
        jPanel1.setBounds(0, 190, 1529, 540);

        jPanel_Back.setBackground(new java.awt.Color(203, 203, 10));
        jPanel_Back.setPreferredSize(new java.awt.Dimension(154, 40));
        jPanel_Back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel_BackMousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Quay về");

        javax.swing.GroupLayout jPanel_BackLayout = new javax.swing.GroupLayout(jPanel_Back);
        jPanel_Back.setLayout(jPanel_BackLayout);
        jPanel_BackLayout.setHorizontalGroup(
            jPanel_BackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 154, Short.MAX_VALUE)
            .addGroup(jPanel_BackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_BackLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel_BackLayout.setVerticalGroup(
            jPanel_BackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(jPanel_BackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_BackLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel_DoanhThu.add(jPanel_Back);
        jPanel_Back.setBounds(0, 13, 154, 40);

        jPanel_Ngay.setBackground(new java.awt.Color(102, 102, 0));
        jPanel_Ngay.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_Ngay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel_NgayMousePressed(evt);
            }
        });

        jLabel_Ngay.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_Ngay.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Ngay.setText("Thống kê theo ngày");

        javax.swing.GroupLayout jPanel_NgayLayout = new javax.swing.GroupLayout(jPanel_Ngay);
        jPanel_Ngay.setLayout(jPanel_NgayLayout);
        jPanel_NgayLayout.setHorizontalGroup(
            jPanel_NgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
            .addGroup(jPanel_NgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_NgayLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel_Ngay)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel_NgayLayout.setVerticalGroup(
            jPanel_NgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel_NgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_NgayLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel_Ngay)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel_DoanhThu.add(jPanel_Ngay);
        jPanel_Ngay.setBounds(0, 66, 504, 44);

        jPanel_Thang.setBackground(new java.awt.Color(102, 102, 0));
        jPanel_Thang.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_Thang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel_ThangMousePressed(evt);
            }
        });

        jLabel_Thang.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_Thang.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Thang.setText("Thống kê theo tháng");

        javax.swing.GroupLayout jPanel_ThangLayout = new javax.swing.GroupLayout(jPanel_Thang);
        jPanel_Thang.setLayout(jPanel_ThangLayout);
        jPanel_ThangLayout.setHorizontalGroup(
            jPanel_ThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
            .addGroup(jPanel_ThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_ThangLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel_Thang)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel_ThangLayout.setVerticalGroup(
            jPanel_ThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel_ThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_ThangLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel_Thang)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel_DoanhThu.add(jPanel_Thang);
        jPanel_Thang.setBounds(504, 66, 504, 44);

        jPanel_Nam.setBackground(new java.awt.Color(102, 102, 0));
        jPanel_Nam.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_Nam.setPreferredSize(new java.awt.Dimension(504, 44));
        jPanel_Nam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel_NamMousePressed(evt);
            }
        });

        jLabel_Nam.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_Nam.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Nam.setText("Thống kê theo năm");

        javax.swing.GroupLayout jPanel_NamLayout = new javax.swing.GroupLayout(jPanel_Nam);
        jPanel_Nam.setLayout(jPanel_NamLayout);
        jPanel_NamLayout.setHorizontalGroup(
            jPanel_NamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel_NamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_NamLayout.createSequentialGroup()
                    .addGap(0, 172, Short.MAX_VALUE)
                    .addComponent(jLabel_Nam)
                    .addGap(0, 173, Short.MAX_VALUE)))
        );
        jPanel_NamLayout.setVerticalGroup(
            jPanel_NamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(jPanel_NamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_NamLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel_Nam)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel_DoanhThu.add(jPanel_Nam);
        jPanel_Nam.setBounds(1008, 66, 530, 44);

        jDateChooser_From.setBackground(new java.awt.Color(102, 102, 0));
        jDateChooser_From.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Từ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel_DoanhThu.add(jDateChooser_From);
        jDateChooser_From.setBounds(360, 120, 250, 60);

        jDateChooser_To.setBackground(new java.awt.Color(102, 102, 0));
        jDateChooser_To.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Đến", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel_DoanhThu.add(jDateChooser_To);
        jDateChooser_To.setBounds(620, 120, 250, 60);

        jButton_Xem.setBackground(new java.awt.Color(102, 102, 0));
        jButton_Xem.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_Xem.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Xem.setText("Xem");
        jButton_Xem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_XemMouseClicked(evt);
            }
        });
        jPanel_DoanhThu.add(jButton_Xem);
        jButton_Xem.setBounds(900, 130, 166, 40);

        jButton_XuatExcel.setBackground(new java.awt.Color(102, 102, 0));
        jButton_XuatExcel.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_XuatExcel.setForeground(new java.awt.Color(255, 255, 255));
        jButton_XuatExcel.setText("Xuất Excel");
        jButton_XuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_XuatExcelActionPerformed(evt);
            }
        });
        jPanel_DoanhThu.add(jButton_XuatExcel);
        jButton_XuatExcel.setBounds(1080, 130, 133, 40);

        jButton_LamMoi.setBackground(new java.awt.Color(255, 0, 0));
        jButton_LamMoi.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_LamMoi.setForeground(new java.awt.Color(255, 255, 255));
        jButton_LamMoi.setText("Làm mới");
        jButton_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LamMoiActionPerformed(evt);
            }
        });
        jPanel_DoanhThu.add(jButton_LamMoi);
        jButton_LamMoi.setBounds(1220, 130, 115, 40);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/anhnen.jpg"))); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(1520, 716));
        jPanel_DoanhThu.add(jLabel3);
        jLabel3.setBounds(0, 0, 1690, 730);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_DoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, 1484, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_DoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel_BackMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_BackMousePressed
        jPanel_DoanhThu.removeAll();
        thongKeTongQuan.setSize(jPanel_DoanhThu.getSize());
        thongKeTongQuan.setVisible(true);
        jPanel_DoanhThu.add(thongKeTongQuan);
        jPanel_DoanhThu.repaint();
        jPanel_DoanhThu.revalidate();
    }//GEN-LAST:event_jPanel_BackMousePressed

    private void jButton_XemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_XemMouseClicked

        tuNgay = jDateChooser_From.getDate();
        denNgay = jDateChooser_To.getDate();

        // Kiểm tra giá trị ngày hợp lệ
        if (tuNgay == null || denNgay == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn từ ngày và đến ngày hợp lệ.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (tuNgay.after(denNgay)) {
            JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (selectedPeriod == null || selectedPeriod.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn loại thống kê (ngày, tháng, hoặc năm).");
            return;
        }

        // Chuyển đổi ngày sang java.sql.Date
        java.sql.Date sqlTuNgay = new java.sql.Date(tuNgay.getTime());
        java.sql.Date sqlDenNgay = new java.sql.Date(denNgay.getTime());

        // Tính toán ngày bắt đầu và kết thúc dựa trên loại thống kê
        Date[] dateRange = getDateRangeByPeriod(selectedPeriod, tuNgay, denNgay);
        Date chartTuNgay = dateRange[0];
        Date chartDenNgay = dateRange[1];

        // Thực hiện thống kê
        Map<String, Integer> thongKe = thongKe_Dao.getTongThongKe(chartTuNgay, chartDenNgay);

        // Cập nhật kết quả thống kê vào giao diện
        updateStatistics(thongKe);

        // Hiển thị biểu đồ và bảng dữ liệu
        updateChartsAndTable(chartTuNgay, chartDenNgay);
    }//GEN-LAST:event_jButton_XemMouseClicked

    private void jButton_XuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_XuatExcelActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Hiển thị hộp thoại để người dùng chọn vị trí lưu file
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Lấy đường dẫn file
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            // Đảm bảo đuôi file là .xlsx
            if (!filePath.endsWith(".xlsx")) {
                filePath += ".xlsx";
            }
            Date fromDate = jDateChooser_From.getDate();
            Date toDate = jDateChooser_To.getDate();

            // Kiểm tra nếu xuất theo năm, tính ngày đầu và cuối năm
            if ("nam".equals(selectedPeriod)) {
                Calendar cal = Calendar.getInstance();

                // Tính ngày đầu năm
                cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)); // Năm hiện tại
                cal.set(Calendar.MONTH, Calendar.JANUARY); // Tháng 1
                cal.set(Calendar.DAY_OF_MONTH, 1); // Ngày đầu năm
                fromDate = cal.getTime();

                // Tính ngày cuối năm
                cal.set(Calendar.MONTH, Calendar.DECEMBER); // Tháng 12
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); // Ngày cuối tháng 12
                toDate = cal.getTime();
            }

            // Gọi phương thức DAO để xuất dữ liệu tồn kho ra Excel
            try {
                switch (selectedPeriod) {
                    case "ngay" ->
                        excel_DAO.exportExcel_Ngay(filePath, fromDate, toDate);
                    case "thang" ->
                        excel_DAO.exportExcel_Thang(filePath, fromDate, toDate);
                    case "nam" ->
                        excel_DAO.exportExcel_Nam(filePath, fromDate, toDate);
                    default ->
                        JOptionPane.showMessageDialog(null, "Loại xuất không hợp lệ.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Xuất file Excel thất bại: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton_XuatExcelActionPerformed

    private void jButton_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LamMoiActionPerformed
        LamMoi();
    }//GEN-LAST:event_jButton_LamMoiActionPerformed

    private void jPanel_NgayMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_NgayMousePressed
        jPanel_Nam.setBackground(defaultColor);
        jPanel_Thang.setBackground(defaultColor);
        jPanel_Ngay.setBackground(clickColor);
        Restart_JdesktopPane();
        jPanel1.setVisible(true);
        jDateChooser_From.setDateFormatString("dd-MM-yyyy");
        jDateChooser_To.setDateFormatString("dd-MM-yyyy");
        selectedPeriod = "ngay";
        LamMoi();
    }//GEN-LAST:event_jPanel_NgayMousePressed

    private void jPanel_ThangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_ThangMousePressed
        jPanel_Nam.setBackground(defaultColor);
        jPanel_Thang.setBackground(clickColor);
        jPanel_Ngay.setBackground(defaultColor);
        Restart_JdesktopPane();
        jPanel1.setVisible(true);
        jDateChooser_From.setDateFormatString("MM-yyyy");
        jDateChooser_To.setDateFormatString("MM-yyyy");
        selectedPeriod = "thang";
        LamMoi();
    }//GEN-LAST:event_jPanel_ThangMousePressed

    private void jPanel_NamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_NamMousePressed
        jPanel_Nam.setBackground(clickColor);
        jPanel_Thang.setBackground(defaultColor);
        jPanel_Ngay.setBackground(defaultColor);
        Restart_JdesktopPane();
        jPanel1.setVisible(true);
        jDateChooser_From.setDateFormatString("yyyy");
        jDateChooser_To.setDateFormatString("yyyy");
        selectedPeriod = "nam";
        LamMoi();
    }//GEN-LAST:event_jPanel_NamMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private chart.BarChart barChart1;
    private javax.swing.JButton jButton_LamMoi;
    private javax.swing.JButton jButton_Xem;
    private javax.swing.JButton jButton_XuatExcel;
    private com.toedter.calendar.JDateChooser jDateChooser_From;
    private com.toedter.calendar.JDateChooser jDateChooser_To;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel_DT;
    private javax.swing.JLabel jLabel_DoanhThu;
    private javax.swing.JLabel jLabel_LoaiSach;
    private javax.swing.JLabel jLabel_Nam;
    private javax.swing.JLabel jLabel_Ngay;
    private javax.swing.JLabel jLabel_SLHD;
    private javax.swing.JLabel jLabel_SLS;
    private javax.swing.JLabel jLabel_SoLuongSach;
    private javax.swing.JLabel jLabel_TacGia;
    private javax.swing.JLabel jLabel_Thang;
    private javax.swing.JLabel jLabel_TongSLHD;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_Back;
    private javax.swing.JPanel jPanel_DoanhThu;
    private javax.swing.JPanel jPanel_Nam;
    private javax.swing.JPanel jPanel_Ngay;
    private javax.swing.JPanel jPanel_PieChartLoaiSach;
    private javax.swing.JPanel jPanel_PieChartTacGia;
    private javax.swing.JPanel jPanel_SLDonHang;
    private javax.swing.JPanel jPanel_SL_Sach;
    private javax.swing.JPanel jPanel_Thang;
    private javax.swing.JPanel jPanel_TongDoanhThu;
    private javax.swing.JPanel jPanel_barChart;
    private javax.swing.JScrollPane jScrollPane_DoanhThu;
    private javax.swing.JTable jTable_DoanhThu;
    private chart.PieChart pieChart_LoaiSach;
    private chart.PieChart pieChart_TacGia;
    // End of variables declaration//GEN-END:variables
}

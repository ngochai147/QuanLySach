package view;


import chart.ModelChart;
import chart.ModelPieChart;
import view.NguoiQuanLy;

import connectDB.ConnectDB;
import java.awt.*;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import dao.ThongKe_Dao;
import entity.ThongKe_model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;


/**
 *
 * @author phamd
 */
public final class NguoiQuanLy_ThongKeTongQuan extends javax.swing.JInternalFrame {

    /**
     * Creates new form NguoiQuanLy_ThongKeTongQuan
     */
    private ThongKe_Dao thongKe_Dao = new ThongKe_Dao();


    

    public NguoiQuanLy_ThongKeTongQuan() {
        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);

        DecimalFormat vndFormat = (DecimalFormat) NumberFormat.getCurrencyInstance();
        vndFormat.setGroupingUsed(true); 
        vndFormat.setMaximumFractionDigits(0); 
        vndFormat.setPositivePrefix("VND "); 
        
        Map<String, Integer> thongKe = thongKe_Dao.getStatisticIn7Days();
        jTextField_SoLuongSach.setText(String.valueOf(thongKe.get("TongSoLuongSachDaBan")));
        jTextField_TongSLHD.setText(String.valueOf(thongKe.get("TongSoLuongHoaDon")));

        formatVNDTextField(jTextField_TongDoanhThu, thongKe.get("TongDoanhThu"));

        chart.setTitle("Thống kê tổng quan 7 ngày gần nhất");
        chart.addLegend("Doanh Thu", Color.decode("#DFFF0F"), Color.decode("#F89B29"));
        chart.addLegend("Lợi nhuận", Color.decode("#F89B29"), Color.decode("#FF0F7B"));
        
        setDataToChart();
        showPieChart_TacGia();
        showPieChart_LoaiSach();
        
    }

    private void showPieChart_TacGia(){
        String sql = """
                     select s.tacGia, SUM(cthd.donGia*cthd.soLuong) as doanhThu
                     from HoaDon hd 
                     join ChiTietHoaDon cthd
                     on hd.maHoaDon = cthd.maHoaDon
                     join Sach s 
                     on cthd.ISBN = s.ISBN
                     where hd.ngayTaoDon >= DATEADD(DAY, -7, GETDATE())
                     group by s.tacGia
                     order by s.tacGia
                     """;
        try {
            pieChart_TacGia.clearData();
            Connection con = ConnectDB.getInstance().getConnection();
            PreparedStatement p = con.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            
            int index = 0;
            while(rs.next()){
                String tacGia = rs.getString("tacGia");
                double doanhThu = rs.getDouble("doanhThu");
                pieChart_TacGia.addData(new ModelPieChart(tacGia, doanhThu, getColor(index++)));
                
            }
            rs.close();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void showPieChart_LoaiSach(){
        String sql = """
                    select ls.tenLoai, SUM(cthd.donGia*cthd.soLuong) as doanhThu
                    from HoaDon hd 
                    join ChiTietHoaDon cthd
                    on hd.maHoaDon = cthd.maHoaDon
                    join Sach s 
                    on cthd.ISBN = s.ISBN
                    join LoaiSach ls
                    on ls.maLoai = s.maLoaiSach
                    where hd.ngayTaoDon >= DATEADD(DAY, -7, GETDATE())
                    group by ls.tenLoai
                    order by ls.tenLoai
                    """;
        try {
            pieChart_LoaiSach.clearData();
            Connection con = ConnectDB.getInstance().getConnection();
            PreparedStatement p = con.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            
            int index = 0;
            while(rs.next()){
                String tenLoai = rs.getString("tenLoai");
                double doanhThu = rs.getDouble("doanhThu");
                pieChart_LoaiSach.addData(new ModelPieChart(tenLoai, doanhThu, getColor(index++)));
                
            }
            rs.close();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Color getColor(int index){
        Random random = new Random(index); 
        int red = random.nextInt(256);    
        int green = random.nextInt(256);  
        int blue = random.nextInt(256);   
        return new Color(red, green, blue);
    }
    public void formatVNDTextField(JTextField textField, double value) {
        DecimalFormat vndFormat = (DecimalFormat) NumberFormat.getNumberInstance();
        vndFormat.setGroupingUsed(true); // Thêm dấu phân cách hàng nghìn
        vndFormat.setMaximumFractionDigits(0); // Không hiển thị phần lẻ
    
        String formattedValue = vndFormat.format(value) + " VND"; // Thêm hậu tố "VND" không có $
        textField.setText(formattedValue);
}
    
    private void setDataToChart(){
        List<ThongKe_model> dsTK = thongKe_Dao.getChartThongKeLast7Days();
        for (int i = dsTK.size()-1; i>=0; i--){
            ThongKe_model tk = dsTK.get(i);
            chart.addData(new ModelChart(tk.getDate(), new double[]{tk.getDoanhThu(), tk.getLoiNhuan()}));
        }
        chart.start();
    }
    
    public static void setupInternalFrame(JInternalFrame frame) {
        frame.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) frame.getUI();
        ui.setNorthPane(null);
    }


   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel_TacGia = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pieChart_TacGia = new chart.PieChart();
        jPanel_SLNhanVien = new javax.swing.JPanel();
        jTextField_TongSLHD = new javax.swing.JTextField();
        jLabel_TongSLHD = new javax.swing.JLabel();
        jPanel_LoaiSach = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pieChart_LoaiSach = new chart.PieChart();
        panelShadow1 = new panel.PanelShadow();
        chart = new chart.CurveLineChart();
        pn_ChiTietThongKe = new javax.swing.JPanel();
        lbl_ChiTietThongKe = new javax.swing.JLabel();
        jPanel_TongDoanhThu = new javax.swing.JPanel();
        jTextField_TongDoanhThu = new javax.swing.JTextField();
        jLabel_NhanVien1 = new javax.swing.JLabel();
        jPanel_SL_Sach = new javax.swing.JPanel();
        jTextField_SoLuongSach = new javax.swing.JTextField();
        jLabel_SoLuongSach = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1495, 677));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setText("Biểu đồ tròn thống kê tỉ lệ doanh thu theo tác giả ");

        javax.swing.GroupLayout jPanel_TacGiaLayout = new javax.swing.GroupLayout(jPanel_TacGia);
        jPanel_TacGia.setLayout(jPanel_TacGiaLayout);
        jPanel_TacGiaLayout.setHorizontalGroup(
            jPanel_TacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TacGiaLayout.createSequentialGroup()
                .addComponent(pieChart_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
            .addGroup(jPanel_TacGiaLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_TacGiaLayout.setVerticalGroup(
            jPanel_TacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TacGiaLayout.createSequentialGroup()
                .addComponent(pieChart_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_SLNhanVien.setBackground(new java.awt.Color(102, 102, 0));
        jPanel_SLNhanVien.setForeground(new java.awt.Color(255, 255, 255));

        jTextField_TongSLHD.setBackground(new java.awt.Color(102, 102, 0));
        jTextField_TongSLHD.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jTextField_TongSLHD.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_TongSLHD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_TongSLHD.setBorder(null);

        jLabel_TongSLHD.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_TongSLHD.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_TongSLHD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_TongSLHD.setText("Tổng số lượng đơn hàng");

        javax.swing.GroupLayout jPanel_SLNhanVienLayout = new javax.swing.GroupLayout(jPanel_SLNhanVien);
        jPanel_SLNhanVien.setLayout(jPanel_SLNhanVienLayout);
        jPanel_SLNhanVienLayout.setHorizontalGroup(
            jPanel_SLNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SLNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_TongSLHD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_TongSLHD, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_SLNhanVienLayout.setVerticalGroup(
            jPanel_SLNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SLNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_SLNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_TongSLHD)
                    .addComponent(jTextField_TongSLHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Biểu đồ tròn thống kê tỉ lệ doanh thu theo loại sách");

        javax.swing.GroupLayout jPanel_LoaiSachLayout = new javax.swing.GroupLayout(jPanel_LoaiSach);
        jPanel_LoaiSach.setLayout(jPanel_LoaiSachLayout);
        jPanel_LoaiSachLayout.setHorizontalGroup(
            jPanel_LoaiSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pieChart_LoaiSach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_LoaiSachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel_LoaiSachLayout.setVerticalGroup(
            jPanel_LoaiSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_LoaiSachLayout.createSequentialGroup()
                .addComponent(pieChart_LoaiSach, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        panelShadow1.setBackground(new java.awt.Color(22, 22, 94));
        panelShadow1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 1, 1));
        panelShadow1.setColorGradient(new java.awt.Color(2, 2, 71));
        panelShadow1.setPreferredSize(new java.awt.Dimension(900, 558));

        chart.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
                .addContainerGap())
        );

        pn_ChiTietThongKe.setBackground(new java.awt.Color(198, 198, 53));
        pn_ChiTietThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_ChiTietThongKeMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pn_ChiTietThongKeMousePressed(evt);
            }
        });

        lbl_ChiTietThongKe.setBackground(new java.awt.Color(255, 255, 255));
        lbl_ChiTietThongKe.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lbl_ChiTietThongKe.setForeground(new java.awt.Color(255, 255, 255));
        lbl_ChiTietThongKe.setText("Xem chi tiết");

        javax.swing.GroupLayout pn_ChiTietThongKeLayout = new javax.swing.GroupLayout(pn_ChiTietThongKe);
        pn_ChiTietThongKe.setLayout(pn_ChiTietThongKeLayout);
        pn_ChiTietThongKeLayout.setHorizontalGroup(
            pn_ChiTietThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_ChiTietThongKeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_ChiTietThongKe)
                .addGap(20, 20, 20))
        );
        pn_ChiTietThongKeLayout.setVerticalGroup(
            pn_ChiTietThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_ChiTietThongKeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_ChiTietThongKe)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_TongDoanhThu.setBackground(new java.awt.Color(102, 102, 0));
        jPanel_TongDoanhThu.setForeground(new java.awt.Color(255, 255, 255));

        jTextField_TongDoanhThu.setBackground(new java.awt.Color(102, 102, 0));
        jTextField_TongDoanhThu.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jTextField_TongDoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_TongDoanhThu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_TongDoanhThu.setBorder(null);

        jLabel_NhanVien1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_NhanVien1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_NhanVien1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_NhanVien1.setText("Tổng doanh thu");

        javax.swing.GroupLayout jPanel_TongDoanhThuLayout = new javax.swing.GroupLayout(jPanel_TongDoanhThu);
        jPanel_TongDoanhThu.setLayout(jPanel_TongDoanhThuLayout);
        jPanel_TongDoanhThuLayout.setHorizontalGroup(
            jPanel_TongDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TongDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_NhanVien1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_TongDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_TongDoanhThuLayout.setVerticalGroup(
            jPanel_TongDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TongDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_TongDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_NhanVien1)
                    .addComponent(jTextField_TongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel_SL_Sach.setBackground(new java.awt.Color(102, 102, 0));
        jPanel_SL_Sach.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel_SL_Sach.setForeground(new java.awt.Color(255, 255, 255));

        jTextField_SoLuongSach.setBackground(new java.awt.Color(102, 102, 0));
        jTextField_SoLuongSach.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jTextField_SoLuongSach.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_SoLuongSach.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_SoLuongSach.setBorder(null);

        jLabel_SoLuongSach.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_SoLuongSach.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_SoLuongSach.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_SoLuongSach.setText("Tổng số lượng sách đã bán");

        javax.swing.GroupLayout jPanel_SL_SachLayout = new javax.swing.GroupLayout(jPanel_SL_Sach);
        jPanel_SL_Sach.setLayout(jPanel_SL_SachLayout);
        jPanel_SL_SachLayout.setHorizontalGroup(
            jPanel_SL_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SL_SachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_SoLuongSach)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_SoLuongSach, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_SL_SachLayout.setVerticalGroup(
            jPanel_SL_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SL_SachLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_SL_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_SoLuongSach)
                    .addComponent(jTextField_SoLuongSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/anhnen.jpg"))); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(1520, 716));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, 943, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel_LoaiSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel_SLNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel_TongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel_SL_Sach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(176, 176, 176)
                        .addComponent(pn_ChiTietThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1690, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_SLNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel_TongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel_SL_Sach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pn_ChiTietThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jPanel_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel_LoaiSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1540, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pn_ChiTietThongKeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_ChiTietThongKeMousePressed
        NguoiQuanLy_ThongKeDoanhThu thongKeDoanhThu = new NguoiQuanLy_ThongKeDoanhThu();
        jPanel1.removeAll();
        thongKeDoanhThu.setSize(jPanel1.getSize());
        thongKeDoanhThu.setVisible(true);
        jPanel1.add(thongKeDoanhThu);
        jPanel1.repaint();
        jPanel1.revalidate();
    }//GEN-LAST:event_pn_ChiTietThongKeMousePressed

    private void pn_ChiTietThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_ChiTietThongKeMouseClicked
        NguoiQuanLy_ThongKeDoanhThu thongKeDoanhThu = new NguoiQuanLy_ThongKeDoanhThu();
        jPanel1.removeAll();
        thongKeDoanhThu.setSize(jPanel1.getSize());
        thongKeDoanhThu.setVisible(true);
        jPanel1.add(thongKeDoanhThu);
        jPanel1.repaint();
        jPanel1.revalidate();
    }//GEN-LAST:event_pn_ChiTietThongKeMouseClicked



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private chart.CurveLineChart chart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel_NhanVien1;
    private javax.swing.JLabel jLabel_SoLuongSach;
    private javax.swing.JLabel jLabel_TongSLHD;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_LoaiSach;
    private javax.swing.JPanel jPanel_SLNhanVien;
    private javax.swing.JPanel jPanel_SL_Sach;
    private javax.swing.JPanel jPanel_TacGia;
    private javax.swing.JPanel jPanel_TongDoanhThu;
    private javax.swing.JTextField jTextField_SoLuongSach;
    private javax.swing.JTextField jTextField_TongDoanhThu;
    private javax.swing.JTextField jTextField_TongSLHD;
    private javax.swing.JLabel lbl_ChiTietThongKe;
    private panel.PanelShadow panelShadow1;
    private chart.PieChart pieChart_LoaiSach;
    private chart.PieChart pieChart_TacGia;
    private javax.swing.JPanel pn_ChiTietThongKe;
    // End of variables declaration//GEN-END:variables
}

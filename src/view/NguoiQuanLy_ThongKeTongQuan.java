package view;


import chart.ModelChart;

import java.awt.*;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import dao.ThongKe_Dao;
import entity.ThongKe_model;

import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
 *
 * @author phamd
 */
public final class NguoiQuanLy_ThongKeTongQuan extends javax.swing.JInternalFrame {

    /**
     * Creates new form NguoiQuanLy_ThongKeTongQuan
     */
    private ThongKe_Dao thongKe_Dao = new ThongKe_Dao();
    private NguoiQuanLy_ThongKeTonKho thongKeTonKho = new NguoiQuanLy_ThongKeTonKho();
    private NguoiQuanLy_ThongKeDoanhThu1 thongKeDoanhThu = new NguoiQuanLy_ThongKeDoanhThu1();
    

    public NguoiQuanLy_ThongKeTongQuan() {
        initComponents();
        setupInternalFrame(this);
        setupInternalFrame(thongKeTongQuan);

        DecimalFormat vndFormat = (DecimalFormat) NumberFormat.getCurrencyInstance();
        vndFormat.setGroupingUsed(true); 
        vndFormat.setMaximumFractionDigits(0); 
        vndFormat.setPositivePrefix("VND "); 
        
        Map<String, Integer> stats = thongKe_Dao.getStatisticIn7Days();
        jTextField_SoLuongSach.setText(String.valueOf(stats.get("TongSoLuongSachDaBan")));
        jTextField_TongSLHD.setText(String.valueOf(stats.get("TongSoLuongHoaDon")));
        formatVNDTextField(jTextField_TongDoanhThu, stats.get("TongDoanhThu"));

        chart.setTitle("Thống kê tổng quan 7 ngày gần nhất");
        chart.addLegend("Doanh Thu", Color.decode("#DFFF0F"), Color.decode("#F89B29"));
        chart.addLegend("Lợi nhuận", Color.decode("#F89B29"), Color.decode("#FF0F7B"));
        
        setDataToChart();
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
        if (dsTK.size() < 3) { // Kiểm tra nếu dsTK có ít nhất 3 phần tử
            System.err.println("Không đủ dữ liệu để vẽ biểu đồ");
            return;
        }
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

        jComboBox_ThongKe = new javax.swing.JComboBox<>();
        jDesktopPane_ThongKe = new javax.swing.JDesktopPane();
        thongKeTongQuan = new javax.swing.JInternalFrame();
        jPanel_TongQuan = new javax.swing.JPanel();
        jPanel_SLNhanVien = new javax.swing.JPanel();
        jTextField_TongSLHD = new javax.swing.JTextField();
        jLabel_TongSLHD = new javax.swing.JLabel();
        jPanel_SL_Sach = new javax.swing.JPanel();
        jTextField_SoLuongSach = new javax.swing.JTextField();
        jLabel_SoLuongSach = new javax.swing.JLabel();
        jPanel_TongDoanhThu = new javax.swing.JPanel();
        jTextField_TongDoanhThu = new javax.swing.JTextField();
        jLabel_NhanVien1 = new javax.swing.JLabel();
        panelHoldChart = new javax.swing.JPanel();
        panelShadow1 = new panel.PanelShadow();
        chart = new chart.CurveLineChart();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1495, 436));

        jComboBox_ThongKe.setBackground(new java.awt.Color(102, 102, 0));
        jComboBox_ThongKe.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jComboBox_ThongKe.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox_ThongKe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Tổng quan", "Doanh thu", "Tồn kho"}));
        jComboBox_ThongKe.setBorder(null);

        jComboBox_ThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jComboBox_ThongKeActionPerformed(evt);
                } catch (PropertyVetoException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        jDesktopPane_ThongKe.setBackground(new java.awt.Color(255, 255, 255));
        jDesktopPane_ThongKe.setLayout(new java.awt.CardLayout());

        thongKeTongQuan.setVisible(true);

        jPanel_TongQuan.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_TongQuan.setPreferredSize(new java.awt.Dimension(1491, 350));

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

        panelHoldChart.setPreferredSize(new java.awt.Dimension(900, 558));

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
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 1491, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelHoldChartLayout = new javax.swing.GroupLayout(panelHoldChart);
        panelHoldChart.setLayout(panelHoldChartLayout);
        panelHoldChartLayout.setHorizontalGroup(
            panelHoldChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, 1451, Short.MAX_VALUE)
        );
        panelHoldChartLayout.setVerticalGroup(
            panelHoldChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel_TongQuanLayout = new javax.swing.GroupLayout(jPanel_TongQuan);
        jPanel_TongQuan.setLayout(jPanel_TongQuanLayout);
        jPanel_TongQuanLayout.setHorizontalGroup(
            jPanel_TongQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TongQuanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_SL_Sach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel_SLNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel_TongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(panelHoldChart, javax.swing.GroupLayout.DEFAULT_SIZE, 1451, Short.MAX_VALUE)
        );
        jPanel_TongQuanLayout.setVerticalGroup(
            jPanel_TongQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TongQuanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_TongQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_SL_Sach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel_TongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel_SLNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelHoldChart, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout thongKeTongQuanLayout = new javax.swing.GroupLayout(thongKeTongQuan.getContentPane());
        thongKeTongQuan.getContentPane().setLayout(thongKeTongQuanLayout);
        thongKeTongQuanLayout.setHorizontalGroup(
            thongKeTongQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, thongKeTongQuanLayout.createSequentialGroup()
                .addComponent(jPanel_TongQuan, javax.swing.GroupLayout.DEFAULT_SIZE, 1451, Short.MAX_VALUE)
                .addContainerGap())
        );
        thongKeTongQuanLayout.setVerticalGroup(
            thongKeTongQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongKeTongQuanLayout.createSequentialGroup()
                .addComponent(jPanel_TongQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 33, Short.MAX_VALUE))
        );

        jDesktopPane_ThongKe.add(thongKeTongQuan, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox_ThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jDesktopPane_ThongKe)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jComboBox_ThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jDesktopPane_ThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_ThongKeActionPerformed(java.awt.event.ActionEvent evt) throws PropertyVetoException {//GEN-FIRST:event_jComboBox_ThongKeActionPerformed
        String selectedItem = jComboBox_ThongKe.getSelectedItem().toString();

        if (selectedItem.equals("Tổng quan")) {
            jDesktopPane_ThongKe.removeAll();
            thongKeTongQuan.setSize(jDesktopPane_ThongKe.getSize());
            thongKeTongQuan.setVisible(true);
            jDesktopPane_ThongKe.add(thongKeTongQuan);
            try {
                thongKeTongQuan.setSelected(true);
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
        } else if (selectedItem.equals("Doanh thu")) {
            jDesktopPane_ThongKe.removeAll();
            thongKeDoanhThu.setSize(jDesktopPane_ThongKe.getSize());
            thongKeDoanhThu.setVisible(true);
            jDesktopPane_ThongKe.add(thongKeDoanhThu);
            try {
                thongKeDoanhThu.setSelected(true);
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
        } else if (selectedItem.equals("Tồn kho")) {
            jDesktopPane_ThongKe.removeAll();
            thongKeTonKho.setSize(jDesktopPane_ThongKe.getSize());
            thongKeTonKho.setVisible(true);
            jDesktopPane_ThongKe.add(thongKeTonKho);
            try {
                thongKeTonKho.setSelected(true);
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
        }
        jDesktopPane_ThongKe.revalidate();
        jDesktopPane_ThongKe.repaint();

    }//GEN-LAST:event_jComboBox_ThongKeActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private chart.CurveLineChart chart;
    private javax.swing.JComboBox<String> jComboBox_ThongKe;
    private javax.swing.JDesktopPane jDesktopPane_ThongKe;
    private javax.swing.JLabel jLabel_NhanVien1;
    private javax.swing.JLabel jLabel_SoLuongSach;
    private javax.swing.JLabel jLabel_TongSLHD;
    private javax.swing.JPanel jPanel_SLNhanVien;
    private javax.swing.JPanel jPanel_SL_Sach;
    private javax.swing.JPanel jPanel_TongDoanhThu;
    private javax.swing.JPanel jPanel_TongQuan;
    private javax.swing.JTextField jTextField_SoLuongSach;
    private javax.swing.JTextField jTextField_TongDoanhThu;
    private javax.swing.JTextField jTextField_TongSLHD;
    private javax.swing.JPanel panelHoldChart;
    private panel.PanelShadow panelShadow1;
    private javax.swing.JInternalFrame thongKeTongQuan;
    // End of variables declaration//GEN-END:variables
}

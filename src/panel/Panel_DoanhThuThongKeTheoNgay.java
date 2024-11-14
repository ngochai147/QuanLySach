/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package panel;

import chart.ModelChart;
import dao.ThongKe_Dao;
import entity.ThongKe_model;
import java.awt.Color;
import java.util.Date;
import java.util.List;

/**
 *
 * @author phamd
 */
public class Panel_DoanhThuThongKeTheoNgay extends javax.swing.JPanel{

    final ThongKe_Dao thongKe_Dao = new ThongKe_Dao();
    public Panel_DoanhThuThongKeTheoNgay() {
        initComponents();
        chart_DoanhThuTheoNgay.setTitle("BIỂU ĐỒ THỐNG KÊ DOANH THU VÀ LỢI NHUẬN THEO NGÀY");
        chart_DoanhThuTheoNgay.addLegend("Doanh thu", Color.decode("#439CFB"), Color.decode("#F187FB"));
        chart_DoanhThuTheoNgay.addLegend("Lợi nhuận", Color.decode("#00EE6E"), Color.decode("#0C75E6"));
    }

    public void setChart_DoanhThuTheoNgay(Date fromDate, Date toDate) {
        List<ThongKe_model> thongKe_ngay = thongKe_Dao.getChartThongKeTheoNgay(fromDate, toDate);
        for (int i = thongKe_ngay.size() - 1; i >= 0; i--) {
            ThongKe_model thongKe = thongKe_ngay.get(i);
            chart_DoanhThuTheoNgay.addData(new ModelChart(thongKe.getMonth(), new double[]{thongKe.getDoanhThu(), thongKe.getLoiNhuan()}));
        }
        chart_DoanhThuTheoNgay.start();
    }
    
    public void clearChart(){
        chart_DoanhThuTheoNgay.clear();
        chart_DoanhThuTheoNgay.revalidate();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelShadow1 = new PanelShadow();
        chart_DoanhThuTheoNgay = new chart.CurveLineChart();

        panelShadow1.setBackground(new Color(62, 38, 103));
        panelShadow1.setColorGradient(new Color(5, 5, 75));

        chart_DoanhThuTheoNgay.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        chart_DoanhThuTheoNgay.setForeground(new Color(204, 255, 204));

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addComponent(chart_DoanhThuTheoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 1518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(chart_DoanhThuTheoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private chart.CurveLineChart chart_DoanhThuTheoNgay;
    private PanelShadow panelShadow1;
    // End of variables declaration//GEN-END:variables
}

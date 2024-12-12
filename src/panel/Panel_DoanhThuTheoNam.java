
package panel;

import chart.ModelChart;
import entity.ThongKe_model;
import java.util.Date;
import java.util.List;
import dao.ThongKe_Dao;
import java.awt.Color;
/**
 *
 * @author phamd
 */
public class Panel_DoanhThuTheoNam extends javax.swing.JPanel {
//
    final ThongKe_Dao thongKe_Dao = new ThongKe_Dao();
    public Panel_DoanhThuTheoNam() {
        initComponents();
        chart_DoanhThuTheoNam.setTitle("BIỂU ĐỒ THỐNG KÊ DOANH THU VÀ LỢI NHUẬN THEO NĂM");
        chart_DoanhThuTheoNam.addLegend("Doanh thu", Color.decode("#439CFB"), Color.decode("#F187FB"));
        chart_DoanhThuTheoNam.addLegend("Lợi nhuận", Color.decode("#00EE6E"), Color.decode("#0C75E6"));
    }

    public void setChart_DoanhThuTheoNam(Date fromDate, Date toDate) {
        List<ThongKe_model> thongKe_ngay = thongKe_Dao.getChartThongKeTheoNam(fromDate, toDate);
        for (int i = thongKe_ngay.size() - 1; i >= 0; i--) {
            ThongKe_model thongKe = thongKe_ngay.get(i);
            chart_DoanhThuTheoNam.addData(new ModelChart(thongKe.getYear(), new double[]{thongKe.getDoanhThu(), thongKe.getLoiNhuan()}));
        }
        chart_DoanhThuTheoNam.start();
    }
    
    public void clearChart(){
        chart_DoanhThuTheoNam.clear();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelShadow1 = new PanelShadow();
        chart_DoanhThuTheoNam = new chart.CurveLineChart();

        setPreferredSize(new java.awt.Dimension(1544, 560));

        panelShadow1.setBackground(new Color(62, 38, 103));
        panelShadow1.setColorGradient(new Color(5, 5, 75));

        chart_DoanhThuTheoNam.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        chart_DoanhThuTheoNam.setForeground(new Color(204, 255, 204));

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addComponent(chart_DoanhThuTheoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 1518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(chart_DoanhThuTheoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private chart.CurveLineChart chart_DoanhThuTheoNam;
    private PanelShadow panelShadow1;
    // End of variables declaration//GEN-END:variables
}

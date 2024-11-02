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
public class panel_DoanhThuTheoThang extends javax.swing.JPanel {

    final ThongKe_Dao thongKe_Dao = new ThongKe_Dao();
    public panel_DoanhThuTheoThang() {
        initComponents();
        chart_DoanhThuTheoThang.setTitle("BIỂU ĐỒ THỐNG KÊ DOANH THU VÀ LỢI NHUẬN THEO THÁNG");
        chart_DoanhThuTheoThang.addLegend("Doanh thu", Color.decode("#439CFB"), Color.decode("#F187FB"));
        chart_DoanhThuTheoThang.addLegend("Lợi nhuận", Color.decode("#00EE6E"), Color.decode("#0C75E6"));
    }
    
    public void setChart_DoanhThuTheoThang(Date fromDate, Date toDate) {

        List<ThongKe_model> thongKe_thang = thongKe_Dao.getChartThongKeTheoThang(fromDate, toDate);
        for (int i = thongKe_thang.size() - 1; i >= 0; i--) {
            ThongKe_model thongKe = thongKe_thang.get(i);
            chart_DoanhThuTheoThang.addData(new ModelChart(thongKe.getMonth(), new double[]{thongKe.getDoanhThu(), thongKe.getLoiNhuan()}));
        }
        chart_DoanhThuTheoThang.start();
    }
    
    public void clearChart(){
        chart_DoanhThuTheoThang.clear();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelShadow1 = new panel.PanelShadow();
        chart_DoanhThuTheoThang = new chart.CurveLineChart();

        setPreferredSize(new java.awt.Dimension(1544, 560));

        panelShadow1.setBackground(new java.awt.Color(62, 38, 103));
        panelShadow1.setColorGradient(new java.awt.Color(5, 5, 75));

        chart_DoanhThuTheoThang.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        chart_DoanhThuTheoThang.setForeground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addComponent(chart_DoanhThuTheoThang, javax.swing.GroupLayout.PREFERRED_SIZE, 1518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(chart_DoanhThuTheoThang, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(0, 16, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private chart.CurveLineChart chart_DoanhThuTheoThang;
    private panel.PanelShadow panelShadow1;
    // End of variables declaration//GEN-END:variables
}

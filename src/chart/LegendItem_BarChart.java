package chart;

public class LegendItem_BarChart extends javax.swing.JPanel {
//
    public LegendItem_BarChart(ModelLegend_BarChart data) {
        initComponents();
        setOpaque(false);
        lbColor.setBackground(data.getColor());
        lbName.setText(data.getName());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbName = new javax.swing.JLabel();
        lbColor = new LabelColor_BarChart();

        setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N

        lbName.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        lbName.setForeground(new java.awt.Color(100, 100, 100));
        lbName.setText("Name");

        lbColor.setBackground(new java.awt.Color(0, 255, 255));
        lbColor.setText("labelColor_BarChart1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbColor, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbName)
                    .addComponent(lbColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private LabelColor_BarChart lbColor;
    private javax.swing.JLabel lbName;
    // End of variables declaration//GEN-END:variables
}

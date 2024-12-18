/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Huu Thai
 */
public class NhanVien extends javax.swing.JFrame {
    //

    private final Color clickColor;
    private final Color defaultColor;

    /**
     * Creates new form TrangThuKho
     */
    public NhanVien() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        clickColor = new Color(205,175,149);
        defaultColor = new Color(139, 125, 107);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu = new javax.swing.JPanel();
        jPanel_HoaDon = new javax.swing.JPanel();
        jLabel_HoaDon = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(139, 125, 107));

        jMenu.setBackground(new java.awt.Color(139, 125, 107));

        jPanel_HoaDon.setBackground(new java.awt.Color(139, 125, 107));
        jPanel_HoaDon.setPreferredSize(new java.awt.Dimension(202, 67));
        jPanel_HoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_HoaDonMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel_HoaDonMousePressed(evt);
            }
        });

        jLabel_HoaDon.setFont(new java.awt.Font("Arial", 1, 25)); // NOI18N
        jLabel_HoaDon.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_HoaDon.setText("Quản lý hóa đơn");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_QuanLyHoaDon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel_HoaDonLayout = new javax.swing.GroupLayout(jPanel_HoaDon);
        jPanel_HoaDon.setLayout(jPanel_HoaDonLayout);
        jPanel_HoaDonLayout.setHorizontalGroup(
            jPanel_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_HoaDonLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );
        jPanel_HoaDonLayout.setVerticalGroup(
            jPanel_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_HoaDonLayout.createSequentialGroup()
                .addComponent(jLabel_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
            .addGroup(jPanel_HoaDonLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(139, 125, 107));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/log_out_.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(150, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(35, 35, 35))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jMenuLayout = new javax.swing.GroupLayout(jMenu);
        jMenu.setLayout(jMenuLayout);
        jMenuLayout.setHorizontalGroup(
            jMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMenuLayout.createSequentialGroup()
                .addComponent(jPanel_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jMenuLayout.setVerticalGroup(
            jMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jMenuLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_HoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        jDesktopPane1.setBackground(new java.awt.Color(139, 125, 107));
        jDesktopPane1.setPreferredSize(new java.awt.Dimension(1585, 700));
        jDesktopPane1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jDesktopPane1ComponentResized(evt);
            }
        });

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1585, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 724, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel_HoaDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_HoaDonMousePressed
        // TODO add your handling code here:
       
        jPanel_HoaDon.setBackground(clickColor);
        jLabel_HoaDon.setForeground(Color.BLACK);
        jPanel1.setBackground(defaultColor);
        jLabel2.setForeground(Color.BLACK);
    }//GEN-LAST:event_jPanel_HoaDonMousePressed

    private void jDesktopPane1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jDesktopPane1ComponentResized
      
        NhanVien_QuanLyHoaDon_GUI qlhdgui=null;
        qlhdgui = new NhanVien_QuanLyHoaDon_GUI();
        qlhdgui.setSize(jDesktopPane1.getSize());
        qlhdgui.setVisible(true);
        jDesktopPane1.add(qlhdgui);  
        // TODO add your handling code here:
        jPanel_HoaDon.setBackground(clickColor);
        jLabel_HoaDon.setForeground(Color.BLACK);
        
    }//GEN-LAST:event_jDesktopPane1ComponentResized

    private void jPanel_HoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_HoaDonMouseClicked
        // TODO add your handling code here:
        jDesktopPane1.removeAll();
        NhanVien_QuanLyHoaDon_GUI quanLyHoaDon_GUI = new NhanVien_QuanLyHoaDon_GUI();
        quanLyHoaDon_GUI.setSize(jDesktopPane1.getSize());
        quanLyHoaDon_GUI.setVisible(true);
        jDesktopPane1.add(quanLyHoaDon_GUI);
    }//GEN-LAST:event_jPanel_HoaDonMouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 12));
        UIManager.put("OptionPane.messageForeground", Color.BLACK);
        if(JOptionPane.showConfirmDialog(this, "Bạn muốn đăng xuất?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION){
            setVisible(false);
            DangNhap dangNhap = null;
            try {
                dangNhap = new DangNhap();
            } catch (SQLException ex) {
                Logger.getLogger(NhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }
            dangNhap.setVisible(true);
        }
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        // TODO add your handling code here:
        jPanel_HoaDon.setBackground(defaultColor);
        jLabel_HoaDon.setForeground(Color.WHITE);
        jPanel1.setBackground(clickColor);
        jLabel2.setForeground(Color.BLACK);
    }//GEN-LAST:event_jPanel1MousePressed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel_HoaDon;
    private javax.swing.JPanel jMenu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_HoaDon;
    // End of variables declaration//GEN-END:variables
}

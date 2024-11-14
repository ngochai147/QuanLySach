/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class NguoiQuanLy extends javax.swing.JFrame {

    private final Color clickColor;
    private final Color defaultColor;

    public NguoiQuanLy() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        clickColor = new Color(205, 175, 149);
        defaultColor = new Color(139, 125, 107);
//        jPanel_QLNhanVien.setBackground(clickColor);
//        jLabel_QLNhanVien.setForeground(Color.BLACK);

        jPanel_QLSach.setBackground(clickColor);
        jLabel_QLSach.setForeground(Color.BLACK);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu = new javax.swing.JPanel();
        jPanel_QLSach = new javax.swing.JPanel();
        jLabel_QLSach = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel_HoaDon = new javax.swing.JPanel();
        jLabel_HoaDon = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel_ThongKe = new javax.swing.JPanel();
        jLabel_ThongKe = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel_QLNhanVien = new javax.swing.JPanel();
        jLabel_QLNhanVien = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(139, 125, 107));

        jMenu.setBackground(new java.awt.Color(139, 125, 107));

        jPanel_QLSach.setBackground(new java.awt.Color(139, 125, 107));
        jPanel_QLSach.setPreferredSize(new java.awt.Dimension(202, 54));
        jPanel_QLSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_QLSachMouseClicked(evt);
            }
        });

        jLabel_QLSach.setFont(new java.awt.Font("Arial", 1, 25)); // NOI18N
        jLabel_QLSach.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_QLSach.setText("Quản lý sách");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_QuanLySach.png"))); // NOI18N

        javax.swing.GroupLayout jPanel_QLSachLayout = new javax.swing.GroupLayout(jPanel_QLSach);
        jPanel_QLSach.setLayout(jPanel_QLSachLayout);
        jPanel_QLSachLayout.setHorizontalGroup(
            jPanel_QLSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_QLSachLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_QLSach)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel_QLSachLayout.setVerticalGroup(
            jPanel_QLSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_QLSachLayout.createSequentialGroup()
                .addGroup(jPanel_QLSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_QLSachLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel3))
                    .addGroup(jPanel_QLSachLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel_QLSach, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_HoaDon.setBackground(new java.awt.Color(139, 125, 107));
        jPanel_HoaDon.setPreferredSize(new java.awt.Dimension(202, 67));
        jPanel_HoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_HoaDonMouseClicked(evt);
            }
        });
        jPanel_ThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_ThongKeMouseClicked(evt);
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
                .addContainerGap(53, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel_HoaDonLayout.setVerticalGroup(
            jPanel_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_HoaDonLayout.createSequentialGroup()
                .addComponent(jLabel_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
            .addGroup(jPanel_HoaDonLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_ThongKe.setBackground(new java.awt.Color(139, 125, 107));
        jPanel_ThongKe.setPreferredSize(new java.awt.Dimension(202, 67));

        jLabel_ThongKe.setFont(new java.awt.Font("Arial", 1, 25)); // NOI18N
        jLabel_ThongKe.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_ThongKe.setText("Thống kê");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_ThongKe.png"))); // NOI18N

        javax.swing.GroupLayout jPanel_ThongKeLayout = new javax.swing.GroupLayout(jPanel_ThongKe);
        jPanel_ThongKe.setLayout(jPanel_ThongKeLayout);
        jPanel_ThongKeLayout.setHorizontalGroup(
            jPanel_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ThongKeLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_ThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel_ThongKeLayout.setVerticalGroup(
            jPanel_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ThongKeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel_ThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );

        jPanel1.setBackground(new java.awt.Color(139, 125, 107));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/log_out_.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(110, 110, 110))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel_QLNhanVien.setBackground(new java.awt.Color(139, 125, 107));
        jPanel_QLNhanVien.setPreferredSize(new java.awt.Dimension(202, 54));
        jPanel_QLNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_QLNhanVienMouseClicked(evt);
            }
        });

        jLabel_QLNhanVien.setFont(new java.awt.Font("Arial", 1, 25)); // NOI18N
        jLabel_QLNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_QLNhanVien.setText("Quản lý nhân viên");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_QuanLyNhanVien.png"))); // NOI18N

        javax.swing.GroupLayout jPanel_QLNhanVienLayout = new javax.swing.GroupLayout(jPanel_QLNhanVien);
        jPanel_QLNhanVien.setLayout(jPanel_QLNhanVienLayout);
        jPanel_QLNhanVienLayout.setHorizontalGroup(
            jPanel_QLNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_QLNhanVienLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel_QLNhanVien)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel_QLNhanVienLayout.setVerticalGroup(
            jPanel_QLNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_QLNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_QLNhanVienLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jMenuLayout = new javax.swing.GroupLayout(jMenu);
        jMenu.setLayout(jMenuLayout);
        jMenuLayout.setHorizontalGroup(
            jMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMenuLayout.createSequentialGroup()
                .addComponent(jPanel_QLSach, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel_QLNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel_ThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jMenuLayout.setVerticalGroup(
            jMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jMenuLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_HoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                    .addComponent(jPanel_QLSach, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                    .addComponent(jPanel_ThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                    .addComponent(jPanel_QLNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jDesktopPane1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jDesktopPane1ComponentResized
        Sach_QuanLySach sach_QuanLySach = null;
        try {
            sach_QuanLySach = new Sach_QuanLySach();
        } catch (SQLException ex) {
            Logger.getLogger(NguoiQuanLy.class.getName()).log(Level.SEVERE, null, ex);
        }
        sach_QuanLySach.setSize(jDesktopPane1.getSize());
        sach_QuanLySach.setVisible(true);
        jDesktopPane1.add(sach_QuanLySach);


    }//GEN-LAST:event_jDesktopPane1ComponentResized

    private void jPanel_QLNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_QLNhanVienMouseClicked
        jPanel_HoaDon.setBackground(defaultColor);
        jLabel_HoaDon.setForeground(Color.WHITE);
        jPanel_QLSach.setBackground(defaultColor);
        jLabel_QLSach.setForeground(Color.WHITE);
        jPanel_QLNhanVien.setBackground(clickColor);
        jLabel_QLNhanVien.setForeground(Color.BLACK);
        jPanel_ThongKe.setBackground(defaultColor);
        jLabel_ThongKe.setForeground(Color.WHITE);
        jPanel1.setBackground(defaultColor);
        jLabel2.setForeground(Color.BLACK);
        jDesktopPane1.removeAll();
        NguoiQuanLy_QuanLyNhanVien nguoiQuanLy_QuanLyNhanVien = null;
        try {
            nguoiQuanLy_QuanLyNhanVien = new NguoiQuanLy_QuanLyNhanVien();
        } catch (SQLException ex) {
            Logger.getLogger(NguoiQuanLy.class.getName()).log(Level.SEVERE, null, ex);
        }
        nguoiQuanLy_QuanLyNhanVien.setSize(jDesktopPane1.getSize());
        nguoiQuanLy_QuanLyNhanVien.setVisible(true);
        jDesktopPane1.add(nguoiQuanLy_QuanLyNhanVien);
    }//GEN-LAST:event_jPanel_QLNhanVienMouseClicked

    private void jPanel_HoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_HoaDonMouseClicked
        jPanel_HoaDon.setBackground(clickColor);
        jLabel_HoaDon.setForeground(Color.BLACK);
        jPanel_QLSach.setBackground(defaultColor);
        jLabel_QLSach.setForeground(Color.WHITE);
        jPanel_QLNhanVien.setBackground(defaultColor);
        jLabel_QLNhanVien.setForeground(Color.WHITE);
        jPanel_ThongKe.setBackground(defaultColor);
        jLabel_ThongKe.setForeground(Color.WHITE);
        jPanel1.setBackground(defaultColor);
        jLabel2.setForeground(Color.BLACK);
        jDesktopPane1.removeAll();
        QuanLyHoaDon_GUI quanLyHoaDon_GUI = new QuanLyHoaDon_GUI();
        quanLyHoaDon_GUI.setSize(jDesktopPane1.getSize());
        quanLyHoaDon_GUI.setVisible(true);
        jDesktopPane1.add(quanLyHoaDon_GUI);
    }//GEN-LAST:event_jPanel_HoaDonMouseClicked

    private void jPanel_QLSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_QLSachMouseClicked
        jPanel_HoaDon.setBackground(defaultColor);
        jLabel_HoaDon.setForeground(Color.WHITE);
        jPanel_QLSach.setBackground(clickColor);
        jLabel_QLSach.setForeground(Color.BLACK);
        jPanel_QLNhanVien.setBackground(defaultColor);
        jLabel_QLNhanVien.setForeground(Color.WHITE);
        jPanel_ThongKe.setBackground(defaultColor);
        jLabel_ThongKe.setForeground(Color.WHITE);
        jPanel1.setBackground(defaultColor);
        jLabel2.setForeground(Color.BLACK);
        try {
            jDesktopPane1.removeAll();
            Sach_QuanLySach sach_QuanLySach = new Sach_QuanLySach();
            sach_QuanLySach.setSize(jDesktopPane1.getSize());
            sach_QuanLySach.setVisible(true);
            jDesktopPane1.add(sach_QuanLySach);
        } catch (SQLException ex) {
            Logger.getLogger(NguoiQuanLy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jPanel_QLSachMouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        jPanel_HoaDon.setBackground(defaultColor);
        jLabel_HoaDon.setForeground(Color.WHITE);
        jPanel_QLSach.setBackground(defaultColor);
        jLabel_QLSach.setForeground(Color.WHITE);
        jPanel_QLNhanVien.setBackground(defaultColor);
        jLabel_QLNhanVien.setForeground(Color.WHITE);
        jPanel_ThongKe.setBackground(defaultColor);
        jLabel_ThongKe.setForeground(Color.BLACK);
        jPanel1.setBackground(clickColor);
        jLabel2.setForeground(Color.BLACK);
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 12));
        UIManager.put("OptionPane.messageForeground", Color.BLACK);
        if (JOptionPane.showConfirmDialog(this, "Bạn muốn đăng xuất?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
            setVisible(false);
            DangNhap dangNhap = null;
            try {
                dangNhap = new DangNhap();
            } catch (SQLException ex) {
                Logger.getLogger(NguoiQuanLy.class.getName()).log(Level.SEVERE, null, ex);
            }
            dangNhap.setVisible(true);
        }
    }
    private void jPanel_ThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_ThongKeMouseClicked
        jPanel_HoaDon.setBackground(defaultColor);
        jLabel_HoaDon.setForeground(Color.WHITE);
        jPanel_QLSach.setBackground(defaultColor);
        jLabel_QLSach.setForeground(Color.WHITE);
        jPanel_QLNhanVien.setBackground(defaultColor);
        jLabel_QLNhanVien.setForeground(Color.WHITE);
        jPanel_ThongKe.setBackground(clickColor);
        jLabel_ThongKe.setForeground(Color.BLACK);
        jPanel1.setBackground(defaultColor);
        jLabel2.setForeground(Color.WHITE);
        jDesktopPane1.removeAll();
        NguoiQuanLy_ThongKeTongQuan tk_TongQuan = new NguoiQuanLy_ThongKeTongQuan();
        tk_TongQuan.setSize(jDesktopPane1.getSize());
        tk_TongQuan.setVisible(true);
        jDesktopPane1.add(tk_TongQuan);
    }//GEN-LAST:event_jPanel1MouseClicked

    public static void main(String args[]) {

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
                new NguoiQuanLy().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel_HoaDon;
    private javax.swing.JLabel jLabel_QLNhanVien;
    private javax.swing.JLabel jLabel_QLSach;
    private javax.swing.JLabel jLabel_ThongKe;
    private javax.swing.JPanel jMenu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_HoaDon;
    private javax.swing.JPanel jPanel_QLNhanVien;
    private javax.swing.JPanel jPanel_QLSach;
    private javax.swing.JPanel jPanel_ThongKe;
    // End of variables declaration//GEN-END:variables
}

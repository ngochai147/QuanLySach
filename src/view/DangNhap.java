package view;

import dao.TaiKhoan_DAO;
import entity.TaiKhoan;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DangNhap extends javax.swing.JFrame {
    
    public static String ma;
    private boolean ktNV = false;
    private boolean ktNQL = false;
    private boolean ktTK = false;
    
    
    
    private TaiKhoan_DAO taiKhoan_DAO;

    public DangNhap() throws SQLException {
        initComponents();
        taiKhoan_DAO = new TaiKhoan_DAO();
        this.setLocationRelativeTo(null);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel_DangNhap = new javax.swing.JLabel();
        jPasswordField_MatKhau = new javax.swing.JPasswordField();
        jButton_DangNhap = new javax.swing.JButton();
        jRadioButton_NhanVien = new javax.swing.JRadioButton();
        jRadioButton_QuanLy = new javax.swing.JRadioButton();
        jRadioButton_ThuKho = new javax.swing.JRadioButton();
        jTextField_Email = new javax.swing.JTextField();
        jCheckBox_HienThiMatKhau = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(200, 183, 157));
        jPanel1.requestFocus();

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo (2).png"))); // NOI18N

        jLabel_DangNhap.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel_DangNhap.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_DangNhap.setText("ĐĂNG NHẬP");

        jPasswordField_MatKhau.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jPasswordField_MatKhau.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPasswordField_MatKhau.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jPasswordField_MatKhau.setPreferredSize(new java.awt.Dimension(58, 20));

        jButton_DangNhap.setBackground(new java.awt.Color(102, 102, 0));
        jButton_DangNhap.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jButton_DangNhap.setForeground(new java.awt.Color(255, 255, 255));
        jButton_DangNhap.setText("Đăng nhập");
        jButton_DangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_DangNhapMouseClicked(evt);
            }
        });

        buttonGroup1
        .add(jRadioButton_NhanVien);
        jRadioButton_NhanVien.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jRadioButton_NhanVien.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton_NhanVien.setText("Nhân viên");
        jRadioButton_NhanVien.setBorder(null);

        buttonGroup1
        .add(jRadioButton_QuanLy);
        jRadioButton_QuanLy.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jRadioButton_QuanLy.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton_QuanLy.setText("Quản lý");
        jRadioButton_QuanLy.setBorder(null);

        buttonGroup1.add(jRadioButton_ThuKho);
        jRadioButton_ThuKho.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jRadioButton_ThuKho.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton_ThuKho.setText("Thủ kho");
        jRadioButton_ThuKho.setBorder(null);

        jTextField_Email.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jTextField_Email.setToolTipText("Hãy nhập Email của bạn");
        jTextField_Email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_EmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_EmailFocusLost(evt);
            }
        });
        jTextField_Email.requestFocus();

        jCheckBox_HienThiMatKhau.setBackground(new java.awt.Color(162, 193, 138));
        jCheckBox_HienThiMatKhau.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jCheckBox_HienThiMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_HienThiMatKhau.setText("Hiển thị mật khẩu");
        jCheckBox_HienThiMatKhau.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jCheckBox_HienThiMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox_HienThiMatKhauMouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/DangNhap.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                        .addComponent(jLabel_DangNhap)
                        .addGap(158, 158, 158))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox_HienThiMatKhau)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPasswordField_MatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField_Email)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jRadioButton_NhanVien)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                    .addComponent(jRadioButton_QuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(33, 33, 33)
                                    .addComponent(jRadioButton_ThuKho))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_DangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(194, 194, 194))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(209, 209, 209))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_DangNhap)
                .addGap(26, 26, 26)
                .addComponent(jTextField_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jPasswordField_MatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton_NhanVien)
                    .addComponent(jRadioButton_QuanLy)
                    .addComponent(jRadioButton_ThuKho))
                .addGap(18, 18, 18)
                .addComponent(jCheckBox_HienThiMatKhau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_DangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_EmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_EmailFocusGained
        // TODO add your handling code here:
        if (jTextField_Email.getText().equals("Tài khoản")) {
            jTextField_Email.setText("");
            jTextField_Email.setForeground(new java.awt.Color(0x0, 0x0, 0x0));
        }
    }//GEN-LAST:event_jTextField_EmailFocusGained

    private void jTextField_EmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_EmailFocusLost
        // TODO add your handling code here:
        if (jTextField_Email.getText().equals("")) {
            jTextField_Email.setText("Tài khoản");
            jTextField_Email.setForeground(new java.awt.Color(0xD9, 0xD9, 0xD9));

        }
    }//GEN-LAST:event_jTextField_EmailFocusLost

    private void jCheckBox_HienThiMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox_HienThiMatKhauMouseClicked

        if (jCheckBox_HienThiMatKhau.isSelected()) {
            jPasswordField_MatKhau.setEchoChar('\0');
        } else {
            jPasswordField_MatKhau.setEchoChar('*');
        }
    }//GEN-LAST:event_jCheckBox_HienThiMatKhauMouseClicked

    private void jButton_DangNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_DangNhapMouseClicked
        // TODO add your handling code here:
        boolean ktCheck=false;
        if (jRadioButton_NhanVien.isSelected()) {
            ktCheck=true;
            boolean found = false;
            try {
                for (TaiKhoan tk : taiKhoan_DAO.getDSTk()) {
                    if (tk.getNhanVien().getChucVu().getChucVu().equalsIgnoreCase("Nhân viên")) {
                        if (jTextField_Email.getText().equalsIgnoreCase(tk.getNhanVien().getMaNV()) && jPasswordField_MatKhau.getText().equalsIgnoreCase(tk.getMatKhau())) {
                            ma=jTextField_Email.getText();
                            this.setVisible(false);
                            NhanVien nhanVien = new NhanVien();
                            nhanVien.setVisible(true);
                            found = true;
                            break;
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(DangNhap.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!found) {
                JOptionPane.showMessageDialog(this, "Thông tin không chính xác", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                jTextField_Email.requestFocus();
                jTextField_Email.selectAll();
            }

        } else if (jRadioButton_QuanLy.isSelected()) {
            ktCheck=true;
            boolean found = false;
            try {
                for (TaiKhoan tk : taiKhoan_DAO.getDSTk()) {
                    if (tk.getNhanVien().getChucVu().getChucVu().equalsIgnoreCase("Quản lý")) {
                        if (jTextField_Email.getText().equalsIgnoreCase(tk.getNhanVien().getMaNV()) && jPasswordField_MatKhau.getText().equalsIgnoreCase(tk.getMatKhau())) {
                            ma=jTextField_Email.getText();
                            this.setVisible(false);
                            NguoiQuanLy nguoiQuanLy = new NguoiQuanLy();
                            nguoiQuanLy.setVisible(true);
                            found = true;
                            break;
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(DangNhap.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!found) {
                JOptionPane.showMessageDialog(this, "Thông tin không chính xác", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                jTextField_Email.requestFocus();
                jTextField_Email.selectAll();
            }

        } else if (jRadioButton_ThuKho.isSelected()) {
            ktCheck=true;
            boolean found = false;
            try {
                for (TaiKhoan tk : taiKhoan_DAO.getDSTk()) {
                    if (tk.getNhanVien().getChucVu().getChucVu().equalsIgnoreCase("Thủ kho")) {
                        if (jTextField_Email.getText().equalsIgnoreCase(tk.getNhanVien().getMaNV()) && jPasswordField_MatKhau.getText().equalsIgnoreCase(tk.getMatKhau())) {
                            ma=jTextField_Email.getText();
                            this.setVisible(false);
                            ThuKho thuKho = new ThuKho();
                            thuKho.setVisible(true);
                            found = true;
                            break;
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(DangNhap.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!found) {
                JOptionPane.showMessageDialog(this, "Thông tin không chính xác", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                jTextField_Email.requestFocus();
                jTextField_Email.selectAll();
            }

        }
        if(!ktCheck){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn chức vụ phù hợp", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton_DangNhapMouseClicked

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new DangNhap().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(DangNhap.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton_DangNhap;
    private javax.swing.JCheckBox jCheckBox_HienThiMatKhau;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_DangNhap;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField_MatKhau;
    private javax.swing.JRadioButton jRadioButton_NhanVien;
    private javax.swing.JRadioButton jRadioButton_QuanLy;
    private javax.swing.JRadioButton jRadioButton_ThuKho;
    private javax.swing.JTextField jTextField_Email;
    // End of variables declaration//GEN-END:variables
}

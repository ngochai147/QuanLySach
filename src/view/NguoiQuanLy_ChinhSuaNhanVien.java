/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import dao.TaiKhoan_DAO;
import entity.ChucVu;
import entity.HinhAnh;
import entity.TaiKhoan;
import function.AddImageToData;
import function.ImageScale;
import entity.NhanVien;

import java.awt.*;
import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Tan Nghi
 */
public class NguoiQuanLy_ChinhSuaNhanVien extends javax.swing.JDialog {
    //

    /**
     * Creates new form NewJDialog
     */
    private NguoiQuanLy_QuanLyNhanVien quanLy;
    private NhanVien nhanVien;
    private String anh;
    private AddImageToData image_url;
    private TaiKhoan_DAO tkDAO;
    private TaiKhoan taiKhoan;
    private boolean checkTrue = false;

    public NguoiQuanLy_ChinhSuaNhanVien(java.awt.Frame parent, boolean modal, NguoiQuanLy_QuanLyNhanVien quanLy, NhanVien nhanVien) {
        super(parent, modal);
        this.setUndecorated(true);
        this.quanLy = quanLy;
        tkDAO = new TaiKhoan_DAO();
        initComponents();
        setLocationRelativeTo(null);
        try {
            initialData(nhanVien);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private NguoiQuanLy_ChinhSuaNhanVien(java.awt.Frame parent, boolean modal) {

    }

    class jPanelGradient extends JPanel {

        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            Color colorl = new Color(238, 223, 204);
            Color color2 = new Color(255, 245, 238);
            GradientPaint gp = new GradientPaint(0, 0, colorl, 180, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        }
    }

    class jPanelTongGradient extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // Ensures proper painting behavior
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            Color colorl = new Color(139, 119, 101);
            Color color2 = new Color(205, 175, 149);
            GradientPaint gp = new GradientPaint(0, 0, colorl, 180, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new jPanelTongGradient();
        jLabel_ThemNhanVien = new javax.swing.JLabel();
        jPanel2 = new jPanelGradient();
        jLabel_MaNhanVien = new javax.swing.JLabel();
        jLabel_TenNhanVien = new javax.swing.JLabel();
        jLabel_GioiTinh = new javax.swing.JLabel();
        jLabel_SoDienThoai = new javax.swing.JLabel();
        jLabel_ChucVu = new javax.swing.JLabel();
        jButton_Luu = new javax.swing.JButton();
        jButton_HuyBo = new javax.swing.JButton();
        jTextField_TenNhanVien = new javax.swing.JTextField();
        jTextField_MaNhanVien = new javax.swing.JTextField();
        jComboBox_GioiTinh = new javax.swing.JComboBox<>();
        jLabel_NgaySinh = new javax.swing.JLabel();
        jTextField_SoDienThoai = new javax.swing.JTextField();
        jLabel_Email = new javax.swing.JLabel();
        jTextField_Email = new javax.swing.JTextField();
        jDateChooser_NgaySinh = new com.toedter.calendar.JDateChooser();
        jButton_ThemAnh = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel_Anh = new javax.swing.JLabel();
        jLabel_ChucVu1 = new javax.swing.JLabel();
        jTextField_DiaChi = new javax.swing.JTextField();
        jComboBox_ChucVu = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1214, 773));

        jLabel_ThemNhanVien.setFont(new java.awt.Font("Arial", 3, 28)); // NOI18N
        jLabel_ThemNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_ThemNhanVien.setText("Cập nhật thông tin nhân viên");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setFont(new java.awt.Font("Times New Roman", 1, 20));
        jPanel2.setPreferredSize(new java.awt.Dimension(1169, 716));

        jLabel_MaNhanVien.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_MaNhanVien.setText("Mã nhân viên");

        jLabel_TenNhanVien.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_TenNhanVien.setText("Tên nhân viên");

        jLabel_GioiTinh.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_GioiTinh.setText("Giới tính");

        jLabel_SoDienThoai.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_SoDienThoai.setText("Số điện thoại");

        jLabel_ChucVu.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_ChucVu.setText("Chức vụ");

        jButton_Luu.setBackground(new java.awt.Color(102, 102, 0));
        jButton_Luu.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_Luu.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Luu.setText("Lưu");
        jButton_Luu.setBorder(null);
        jButton_Luu.setPreferredSize(new java.awt.Dimension(52, 24));
        jButton_Luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LuuActionPerformed(evt);
            }
        });

        jButton_HuyBo.setBackground(new java.awt.Color(153, 0, 51));
        jButton_HuyBo.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_HuyBo.setForeground(new java.awt.Color(255, 255, 255));
        jButton_HuyBo.setText("Hủy");
        jButton_HuyBo.setMaximumSize(new java.awt.Dimension(52, 24));
        jButton_HuyBo.setMinimumSize(new java.awt.Dimension(52, 24));
        jButton_HuyBo.setPreferredSize(new java.awt.Dimension(52, 24));
        jButton_HuyBo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_HuyBoActionPerformed(evt);
            }
        });

        jTextField_TenNhanVien.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jTextField_TenNhanVien.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField_TenNhanVien.setOpaque(true);

        jTextField_MaNhanVien.setBackground(new java.awt.Color(240, 240, 240));
        jTextField_MaNhanVien.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jTextField_MaNhanVien.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField_MaNhanVien.setFocusable(false);

        jComboBox_GioiTinh.setBackground(new java.awt.Color(240, 240, 240));
        jComboBox_GioiTinh.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jComboBox_GioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        jComboBox_GioiTinh.setBorder(null);
        jComboBox_GioiTinh.setPreferredSize(new java.awt.Dimension(64, 28));

        jLabel_NgaySinh.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_NgaySinh.setText("Ngày sinh");
        jLabel_NgaySinh.setPreferredSize(new java.awt.Dimension(79, 22));

        jTextField_SoDienThoai.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jTextField_SoDienThoai.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel_Email.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_Email.setText("Email");

        jTextField_Email.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jTextField_Email.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jDateChooser_NgaySinh.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N

        jButton_ThemAnh.setBackground(new java.awt.Color(102, 102, 0));
        jButton_ThemAnh.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_ThemAnh.setForeground(new java.awt.Color(255, 255, 255));
        jButton_ThemAnh.setText("Thêm ảnh");
        jButton_ThemAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ThemAnhActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(327, 342));
        jPanel3.setRequestFocusEnabled(false);

        jLabel_Anh.setPreferredSize(new java.awt.Dimension(327, 342));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel_Anh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel_Anh, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jLabel_ChucVu1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_ChucVu1.setText("Địa chỉ");

        jTextField_DiaChi.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jTextField_DiaChi.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jComboBox_ChucVu.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jComboBox_ChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân viên", "Thủ kho" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_TenNhanVien)
                            .addComponent(jLabel_GioiTinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_SoDienThoai, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel_ChucVu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_ChucVu1, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel_MaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(68, 68, 68)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField_SoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_Email)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_Email))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jComboBox_GioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(jLabel_NgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooser_NgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField_TenNhanVien, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_MaNhanVien, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_DiaChi)
                    .addComponent(jComboBox_ChucVu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_Luu, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_HuyBo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton_ThemAnh)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 25, Short.MAX_VALUE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_MaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_MaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_TenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_TenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jDateChooser_NgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel_SoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_SoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_Email)
                                    .addComponent(jTextField_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBox_GioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel_NgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel_GioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_ChucVu1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(147, 147, 147))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_ThemAnh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_Luu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_HuyBo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(91, 91, 91))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_ThemNhanVien)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel_ThemNhanVien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(443, 443, 443))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_HuyBoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_HuyBoActionPerformed
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn hủy, các thông tin sẽ không đươc lưu?", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            this.dispose();
        } else {
            jTextField_TenNhanVien.requestFocus();
        }
    }//GEN-LAST:event_jButton_HuyBoActionPerformed

    private void jButton_ThemAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ThemAnhActionPerformed
        // TODO add your handling code here:
        JFileChooser frame_chonAnh = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("File ảnh", "png", "jpq", "jpeg", "gif");
        frame_chonAnh.setFileFilter(filter);
        frame_chonAnh.setAcceptAllFileFilterUsed(false);
        int returnValue = frame_chonAnh.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String filePath = frame_chonAnh.getSelectedFile().getPath();
            Icon icon = new ImageScale().load(filePath, jLabel_Anh.getWidth(), jLabel_Anh.getHeight());
            jLabel_Anh.setIcon(icon);
            image_url = new AddImageToData();
            String fileName = image_url.duaFileVaoThuMuc(new File(filePath), "src\\ServiceImage\\NhanVien_IMG", "../ServiceImage/NhanVien_IMG/");
            anh = fileName;
        }
    }//GEN-LAST:event_jButton_ThemAnhActionPerformed

    private void jButton_LuuActionPerformed(java.awt.event.ActionEvent evt)   {//GEN-FIRST:event_jButton_LuuActionPerformed
        // TODO add your handling code here:
        NhanVien nv = newData();

        try {
            if (checkTrue) {
                quanLy.editDataToTable(nv);
                this.dispose();
                JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công", "Thông báo", JOptionPane.WARNING_MESSAGE);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }//GEN-LAST:event_jButton_LuuActionPerformed

    private void initialData(NhanVien nhanVien) throws SQLException {
        taiKhoan = tkDAO.getTaiKhoanTheoTenDN(nhanVien.getMaNV());
        jTextField_MaNhanVien.setText(nhanVien.getMaNV());
        jTextField_TenNhanVien.setText(nhanVien.getHoTen());
        jComboBox_GioiTinh.setSelectedItem(nhanVien.isGioiTinh() ? "Nữ" : "Nam");
        jDateChooser_NgaySinh.setDate(java.sql.Date.valueOf(nhanVien.getNgaySinh()));
        jTextField_SoDienThoai.setText(nhanVien.getSoDienThoai());
        jTextField_SoDienThoai.setText(nhanVien.getSoDienThoai());
        jTextField_Email.setText(nhanVien.getEmail());
        jTextField_DiaChi.setText(nhanVien.getChucVu().getChucVu());
        jTextField_DiaChi.setText(nhanVien.getDiaChi());
        jComboBox_ChucVu.setSelectedItem(nhanVien.getChucVu().getChucVu());
        String duongDanChinh = nhanVien.getAnh().getUrl().replace("..", "src");
        File fileAnh = new File(duongDanChinh);
        String duongDanTuyetDoi = fileAnh.getAbsolutePath();
        ImageIcon image = new ImageIcon(duongDanTuyetDoi);
        Image imageFit = image.getImage().getScaledInstance(jLabel_Anh.getWidth(), jLabel_Anh.getHeight(), Image.SCALE_SMOOTH);
        jLabel_Anh.setIcon(new ImageIcon(imageFit));
        anh = nhanVien.getAnh().getUrl();
    }

    private boolean kiemTraTenNV(String ten) {
//        1. [A-ZÁÀÃẢẠĂẮẰẴẲẶÂẤẦẪẨẬÊẾỀỄỂỆÍÌĨỈỊÓÒÕỎỌÔỐỒỖỔỘƠỚỜỠỞỢÚÙŨỦỤƯỨỪỮỬỰÝÝỸỶỴĐ]Nhóm này cho phép ký tự đầu tiên trong chuỗi phải là một chữ cái in hoa hoặc các ký tự đặc biệt có dấu trong tiếng Việt (chữ cái có dấu, dấu thanh, v.v.).
//        2. [a-záàãảạăắằẵẳặâấầẫẩậêếềễểệíìĩỉịóòõỏọôốồỗổộơớờỡởợúùũủụưứừữửựýỳỹỷỵ]* Sau ký tự đầu tiên, chuỗi có thể chứa bất kỳ ký tự nào là chữ cái in thường hoặc các chữ cái có dấu trong tiếng Việt. Dấu * có nghĩa là nhóm này có thể lặp lại 0 lần hoặc nhiều lần, tức là không giới hạn số lượng chữ cái trong chuỗi.
//        3. \\s Cho phép một khoảng trắng (space). Đây là phần dành cho việc có thể có tên đầy đủ với hai từ, ví dụ như họ và tên.
//        4. [A-ZÁÀÃẢẠĂẮẰẴẲẶÂẤẦẪẨẬÊẾỀỄỂỆÍÌĨỈỊÓÒÕỎỌÔỐỒỖỔỘƠỚỜỠỞỢÚÙŨỦỤƯỨỪỮỬỰÝÝỸỶỴĐ] Yêu cầu ký tự đầu tiên của từ thứ hai (sau khoảng trắng) phải là một chữ cái in hoa hoặc ký tự đặc biệt có dấu trong tiếng Việt.
//        5. [a-záàãảạăắằẵẳặâấầẫẩậêếềễểệíìĩỉịóòõỏọôốồỗổộơớờỡởợúùũủụưứừữửựýỳỹỷỵ]* Tương tự như phần trước, sau chữ cái đầu tiên, phần còn lại của từ có thể là các chữ cái in thường hoặc chữ cái có dấu trong tiếng Việt.
//        6. * Lặp lại toàn bộ nhóm sau dấu khoảng trắng bao nhiêu lần cũng được, nghĩa là có thể có nhiều hơn một từ trong chuỗi.
        

//        Ví dụ hợp lệ:
//            Nguyễn Văn A
//            Trần Thị B
//            Lê Anh Đức
//            Hà Nội
//        Ví dụ không hợp lệ:
//            nguyễn Văn A (chữ đầu tiên không in hoa)
//            Trần123 (chứa chữ số, không hợp lệ)
//            @Nguyễn (chứa ký tự đặc biệt không hợp lệ)
        String regex = "^[A-ZÁÀÃẢẠĂẮẰẴẲẶÂẤẦẪẨẬÊẾỀỄỂỆÍÌĨỈỊÓÒÕỎỌÔỐỒỖỔỘƠỚỜỠỞỢÚÙŨỦỤƯỨỪỮỬỰÝỲỸỶỴĐ][a-záàãảạăắằẵẳặâấầẫẩậêếềễểệíìĩỉịóòõỏọôốồỗổộơớờỡởợúùũủụưứừữửựýỳỹỷỵ]*(\\s[A-ZÁÀÃẢẠĂẮẰẴẲẶÂẤẦẪẨẬÊẾỀỄỂỆÍÌĨỈỊÓÒÕỎỌÔỐỒỖỔỘƠỚỜỠỞỢÚÙŨỦỤƯỨỪỮỬỰÝỲỸỶỴĐ][a-záàãảạăắằẵẳặâấầẫẩậêếềễểệíìĩỉịóòõỏọôốồỗổộơớờỡởợúùũủụưứừữửựýỳỹỷỵ]*)*$";
        return ten.matches(regex);
    }

    private boolean kiemTraNgaySinh(LocalDate ngaySinh) {
        if (ngaySinh != null) {
            LocalDate today = LocalDate.now();
            int age = Period.between(ngaySinh, today).getYears();
            return age >= 18;
        } else {
            return false;
        }
    }

    private boolean kiemTraSDT(String sDT) {
        String regex = "^\\d{10}$";
        return sDT != null && sDT.matches(regex);
    }

    private boolean kiemTraEmail(String email) {
//    (?!\\.):Nó đảm bảo rằng chuỗi không bắt đầu bằng dấu chấm (.)
//    [a-zA-Z0-9._%+-]+: Phần này kiểm tra phần tên người dùng (trước ký tự @). Nó cho phép các ký tự sau:
        //    Chữ cái viết hoa và viết thường (a-zA-Z),
        //    Các chữ số (0-9),
        //    Dấu chấm (.), dấu gạch dưới (_), dấu phần trăm (%), dấu cộng (+), và dấu gạch nối (-).
        //    Dấu + có nghĩa là ít nhất một ký tự trong số này phải có mặt.
//    (?<!\\.):Nó đảm bảo rằng chuỗi không kết thúc bằng dấu chấm (.). Nói cách khác, không được có dấu chấm ở cuối phần tên người dùng.
//    @gmail\\.com: Kiểm tra phần tên miền, yêu cầu nó phải là @gmail.com. Lưu ý rằng dấu chấm (.) phải được escape (\\.) vì trong regex, dấu chấm có ý nghĩa đặc biệt.



//     Ví dụ hợp lệ:
//            test.email@gmail.com
//            john.doe123@gmail.com
//            user_name+test@gmail.com
//     Ví dụ không hợp lệ:
//            .username@gmail.com (bắt đầu bằng dấu chấm)
//            username.@gmail.com (kết thúc bằng dấu chấm)
//            username@gmail..com (có hai dấu chấm liên tiếp)
//            username@gmailcom (không có dấu chấm giữa gmail và com)
        String regex = "^(?!\\.)[a-zA-Z0-9._%+-]+(?<!\\.)@gmail\\.com$";
        return email.matches(regex);
    }

    private boolean kiemTraDiaChi(String diaChi) {
        //[a-zA-Z0-9.,\\s\\u00C0-\\u1EF9-]: Đây là một nhóm ký tự (character class) cho phép các ký tự sau:
            //a-z: Chữ cái in thường từ a đến z.
            //A-Z: Chữ cái in hoa từ A đến Z.
            //0-9: Các chữ số từ 0 đến 9.
            //.: Dấu chấm.
            //,: Dấu phẩy.
            //\\s: Khoảng trắng (bao gồm khoảng trắng, tab, và dòng mới).
            //\\u00C0-\\u1EF9: Phạm vi Unicode từ \u00C0 đến \u1EF9 đại diện cho các ký tự đặc biệt tiếng Việt có dấu (ví dụ: à, á, ả, ạ, ả, ă, ắ, ằ, ẵ, ẳ, ẵ, v.v.). Đây là một phạm vi Unicode để bao phủ các ký tự có dấu trong tiếng Việt.
            //-: Dấu gạch ngang (cho phép dấu này trong chuỗi).
        String regex = "^[a-zA-Z0-9.,\\s\\u00C0-\\u1EF9-]+$";
        return diaChi.matches(regex);
    }

    private boolean kiemTraTatCa() {
        String tenNhanVien = jTextField_TenNhanVien.getText();
        Date date = jDateChooser_NgaySinh.getDate();
        String soDienThoai = jTextField_SoDienThoai.getText();
        String email = jTextField_Email.getText();
        String diaChi = jTextField_DiaChi.getText();
        if (tenNhanVien.trim().isEmpty() || !kiemTraTenNV(tenNhanVien)) {
            JOptionPane.showMessageDialog(this, "Tên nhân viên không hợp lệ!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            jTextField_TenNhanVien.requestFocus();
            jTextField_TenNhanVien.selectAll();
            return false;
        }
        if (date == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày sinh!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        LocalDate ngaySinh = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        if (!kiemTraNgaySinh(ngaySinh)) {
            JOptionPane.showMessageDialog(this, "Nhân viên phải trên 18 tuổi!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (soDienThoai.trim().isEmpty() || !kiemTraSDT(soDienThoai)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            jTextField_SoDienThoai.requestFocus();
            jTextField_SoDienThoai.selectAll();
            return false;
        }

        if (email.trim().isEmpty() || !kiemTraEmail(email)) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            jTextField_Email.requestFocus();
            jTextField_Email.selectAll();
            return false;
        }

        if (diaChi.trim().isEmpty() || !kiemTraDiaChi(diaChi)) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không hợp lệ!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            jTextField_DiaChi.requestFocus();
            jTextField_DiaChi.selectAll();
            return false;
        }
        if (anh == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ảnh đại diện!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public NhanVien newData() {
        NhanVien nhanVien = new NhanVien();
        boolean kiemTra = kiemTraTatCa();
        if (kiemTra) {
            nhanVien.setMaNV(jTextField_MaNhanVien.getText());
            nhanVien.setHoTen(jTextField_TenNhanVien.getText());
            nhanVien.setGioiTinh(jComboBox_GioiTinh.getSelectedItem().toString().equals("Nam") ? false : true);
            nhanVien.setNgaySinh(jDateChooser_NgaySinh.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            nhanVien.setSoDienThoai(jTextField_SoDienThoai.getText());
            nhanVien.setEmail(jTextField_Email.getText());
            nhanVien.setDiaChi(jTextField_DiaChi.getText());
            nhanVien.setChucVu(new ChucVu((String) jComboBox_ChucVu.getSelectedItem()));
            nhanVien.setAnh(new HinhAnh(anh));
            checkTrue = true;
        }
        return nhanVien;
    }

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(NguoiQuanLy_ChinhSuaNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NguoiQuanLy_ChinhSuaNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NguoiQuanLy_ChinhSuaNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NguoiQuanLy_ChinhSuaNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NguoiQuanLy_ChinhSuaNhanVien dialog = new NguoiQuanLy_ChinhSuaNhanVien(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_HuyBo;
    private javax.swing.JButton jButton_Luu;
    private javax.swing.JButton jButton_ThemAnh;
    private javax.swing.JComboBox<String> jComboBox_ChucVu;
    private javax.swing.JComboBox<String> jComboBox_GioiTinh;
    private com.toedter.calendar.JDateChooser jDateChooser_NgaySinh;
    private javax.swing.JLabel jLabel_Anh;
    private javax.swing.JLabel jLabel_ChucVu;
    private javax.swing.JLabel jLabel_ChucVu1;
    private javax.swing.JLabel jLabel_Email;
    private javax.swing.JLabel jLabel_GioiTinh;
    private javax.swing.JLabel jLabel_MaNhanVien;
    private javax.swing.JLabel jLabel_NgaySinh;
    private javax.swing.JLabel jLabel_SoDienThoai;
    private javax.swing.JLabel jLabel_TenNhanVien;
    private javax.swing.JLabel jLabel_ThemNhanVien;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField_DiaChi;
    private javax.swing.JTextField jTextField_Email;
    private javax.swing.JTextField jTextField_MaNhanVien;
    private javax.swing.JTextField jTextField_SoDienThoai;
    private javax.swing.JTextField jTextField_TenNhanVien;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import dao.*;
import entity.*;
import function.AddImageToData;
import function.ImageScale;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Tan Nghi
 */
public class Sach_ThemSach extends javax.swing.JDialog {

    /**
     * Creates new form NewJDialog
     */
    // Biến để giữ tham chiếu đến JFormattedTextField của jSpinner_SoLuong
    private JFormattedTextField spinnerTextField;
    private boolean isCanceling = false;

    // Biến để giữ tham chiếu đến InputVerifier của jSpinner_SoLuong
    private InputVerifier spinnerInputVerifier;

    private Sach_QuanLySach dsSach;
    private Sach_DAO sach_dao;
    private String anh;
    private AddImageToData image_url;
    private KhoHang_DAO khoHang_dao;
    private ChiTietKhoHang_DAO chiTietKhoHang_dao;
    private PhieuNhap_DAO phieuNhapDao;
    private ChiTietPhieuNhap_DAO chiTietPhieuNhap_dao;
    private static final String ma_CT_PNK = "CTPNK";
    private static final String ma_PNK = "PNK";

    public Sach_ThemSach(java.awt.Frame parent, boolean modal, Sach_QuanLySach dsSach) {
        super(parent, modal);
        this.setUndecorated(true);
        this.dsSach = dsSach;
        sach_dao = new Sach_DAO();
        khoHang_dao = new KhoHang_DAO();
        chiTietKhoHang_dao = new ChiTietKhoHang_DAO();
        phieuNhapDao = new PhieuNhap_DAO();
        chiTietPhieuNhap_dao = new ChiTietPhieuNhap_DAO();
        initComponents();
        setLocationRelativeTo(null);

        spinnerTextField = ((JSpinner.NumberEditor) jSpinner_SoLuong.getEditor()).getTextField();

        // Tạo và gán InputVerifier
        spinnerInputVerifier = new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                if (isCanceling) {
                    return true; // Bỏ qua xác thực khi đang hủy bỏ
                }
                String text = spinnerTextField.getText().trim();

                // Kiểm tra nếu chuỗi không phải là số nguyên dương
                if (!text.matches("^[1-9][0-9]*$")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chỉ nhập số nguyên dương!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                    spinnerTextField.setText("0"); // Đặt lại về 0 nếu không hợp lệ
                    return false;
                }

                return true; // Đầu vào hợp lệ
            }
        };

//      Gán InputVerifier cho spinnerTextField
        spinnerTextField.setInputVerifier(spinnerInputVerifier);
        jButton_HuyBo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                isCanceling = true;
            }
        });
    }

    private Sach_ThemSach(java.awt.Frame parent, boolean modal) {

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
        jLabel_ISBN = new javax.swing.JLabel();
        jLabel_TenSach = new javax.swing.JLabel();
        jLabel_DonGia = new javax.swing.JLabel();
        jLabel_TenLoaiSach = new javax.swing.JLabel();
        jLabel_TacGia = new javax.swing.JLabel();
        jLabel_NhaXuatBan = new javax.swing.JLabel();
        jLabel_NamXuatBan = new javax.swing.JLabel();
        jButton_ThemSach = new javax.swing.JButton();
        jButton_HuyBo = new javax.swing.JButton();
        jTextField_TenSach = new javax.swing.JTextField();
        jTextField_ISBN = new javax.swing.JTextField();
        jTextField_TacGia = new javax.swing.JTextField();
        jTextField_NhaXuatBan = new javax.swing.JTextField();
        jTextField_NamXuatBan = new javax.swing.JTextField();
        jButton_ThemAnh = new javax.swing.JButton();
        jTextField_DonGia = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel_AnhDaiDien = new javax.swing.JLabel();
        jComboBox_LoaiSach = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jComboBox_Kho = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jSpinner_SoLuong = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1214, 773));

        jLabel_ThemNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_ThemNhanVien.setFont(new java.awt.Font("Arial", 3, 28)); // NOI18N
        jLabel_ThemNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_ThemNhanVien.setText("Thêm sách");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setFont(new java.awt.Font("Times New Roman", 1, 20));
        jPanel2.setPreferredSize(new java.awt.Dimension(1170, 597));

        jLabel_ISBN.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_ISBN.setText("ISBN");

        jLabel_TenSach.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_TenSach.setText("Tên sách");

        jLabel_DonGia.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_DonGia.setText("Đơn giá");

        jLabel_TenLoaiSach.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_TenLoaiSach.setText("Loại sách");

        jLabel_TacGia.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_TacGia.setText("Tác giả");

        jLabel_NhaXuatBan.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_NhaXuatBan.setText("Nhà xuất bản");

        jLabel_NamXuatBan.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_NamXuatBan.setText("Năm xuất bản");

        jButton_ThemSach.setBackground(new java.awt.Color(102, 102, 0));
        jButton_ThemSach.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_ThemSach.setForeground(new java.awt.Color(255, 255, 255));
        jButton_ThemSach.setText("Thêm");
        jButton_ThemSach.setBorder(null);
        jButton_ThemSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ThemSachActionPerformed(evt);
            }
        });

        jButton_HuyBo.setBackground(new java.awt.Color(153, 0, 51));
        jButton_HuyBo.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_HuyBo.setForeground(new java.awt.Color(255, 255, 255));
        jButton_HuyBo.setText("Hủy bỏ");
        jButton_HuyBo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_HuyBoMouseClicked(evt);
            }
        });
        jButton_HuyBo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_HuyBoActionPerformed(evt);
            }
        });

        jTextField_TenSach.setBackground(new java.awt.Color(240, 240, 240));
        jTextField_TenSach.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jTextField_TenSach.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTextField_ISBN.setBackground(new java.awt.Color(240, 240, 240));
        jTextField_ISBN.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jTextField_ISBN.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTextField_TacGia.setBackground(new java.awt.Color(240, 240, 240));
        jTextField_TacGia.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jTextField_TacGia.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTextField_NhaXuatBan.setBackground(new java.awt.Color(240, 240, 240));
        jTextField_NhaXuatBan.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jTextField_NhaXuatBan.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTextField_NamXuatBan.setBackground(new java.awt.Color(240, 240, 240));
        jTextField_NamXuatBan.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jTextField_NamXuatBan.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton_ThemAnh.setBackground(new java.awt.Color(102, 102, 0));
        jButton_ThemAnh.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_ThemAnh.setForeground(new java.awt.Color(255, 255, 255));
        jButton_ThemAnh.setText("Thêm ảnh");
        jButton_ThemAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ThemAnhActionPerformed(evt);
            }
        });

        jTextField_DonGia.setBackground(new java.awt.Color(240, 240, 240));
        jTextField_DonGia.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jTextField_DonGia.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(327, 342));

        jLabel_AnhDaiDien.setBackground(new java.awt.Color(204, 255, 153));
        jLabel_AnhDaiDien.setPreferredSize(new java.awt.Dimension(327, 342));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_AnhDaiDien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_AnhDaiDien, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
        );

        jComboBox_LoaiSach.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jComboBox_LoaiSach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiểu thuyết", "Lịch sử", "Thiếu nhi", "Văn hóa xã hội", "Tâm lý, tình cảm" }));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel1.setText("Kho");

        jComboBox_Kho.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jComboBox_Kho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FAHASA Tân Định", "FAHASA Nguyễn Huệ", "FAHASA Phú Nhuận", "FAHASA Gò Vấp", "FAHASA Lạc Xuân" }));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel2.setText("Số lượng");

        jSpinner_SoLuong.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jSpinner_SoLuong.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton_ThemAnh)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_NamXuatBan)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel_TenLoaiSach)
                                .addComponent(jLabel_NhaXuatBan)
                                .addComponent(jLabel_TacGia)
                                .addComponent(jLabel_DonGia)
                                .addComponent(jLabel_TenSach)
                                .addComponent(jLabel_ISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(64, 64, 64)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField_NhaXuatBan, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_TacGia, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_DonGia, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_TenSach, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_ISBN, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_NamXuatBan)
                            .addComponent(jComboBox_Kho, 0, 490, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jComboBox_LoaiSach, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_ThemSach, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_HuyBo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_ISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_ISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_TenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_TenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_DonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_DonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel_TenLoaiSach)
                                .addComponent(jComboBox_LoaiSach, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSpinner_SoLuong))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_NhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_NhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_ThemAnh)
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_NamXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_NamXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton_ThemSach, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton_HuyBo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBox_Kho, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)))))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_ThemNhanVien)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel_ThemNhanVien)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 728, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean kiemTraISBN(String iSBN) {
//        1. ^ và $: Đảm bảo rằng toàn bộ chuỗi chỉ chứa ký tự được mô tả trong regex.
//        2. \\d{13}: Đảm bảo chuỗi chỉ chứa 13 ký tự số liên tiếp (từ 0-9).
        String regex = "^\\d{13}$";
        return iSBN != null && iSBN.matches(regex);
    }

    private boolean kiemTraTenSach(String tenSach) {
//        \\p{L}: Một chữ cái Unicode (bao gồm cả tiếng Việt có dấu như á, à, â, v.v.).
//        0-9: Một chữ số.

//        \\p{L}: Chữ cái Unicode (như a, Ă, â, é).
//        \\p{M}: Các dấu kết hợp (như dấu sắc ́, dấu huyền ̀, dấu hỏi ̉), kết hợp với chữ cái để tạo ra các ký tự như á, ề.
//0-9: Các chữ số   \\s: Khoảng trắng   .: Dấu chấm   ,: Dấu phẩy.   ': Dấu nháy đơn   \": Dấu nháy kép    -: Dấu gạch ngang   :: Dấu hai chấm  (): Dấu ngoặc đơn
        String regex = "^[\\p{L}0-9][\\p{L}\\p{M}0-9\\s.,'\"-:()]*$";
        return tenSach != null && !tenSach.trim().isEmpty() && tenSach.matches(regex);
    }

    private boolean kiemTraDonGia(String donGiaStr) {
        // Sử dụng regex để đảm bảo donGiaStr là một số hợp lệ
        
//        ^[0-9]+:Đảm bảo chuỗi bắt đầu với ít nhất một chữ số (số nguyên).
        
//        (\\.[0-9]+)?:
//        (\\.: Cho phép một dấu chấm (thập phân).
//        [0-9]+: Yêu cầu ít nhất một chữ số sau dấu chấm.
//        ?: Toàn bộ nhóm này là tùy chọn, nghĩa là phần thập phân có thể không xuất hiện.
        String regex = "^[0-9]+(\\.[0-9]{3})?$";
        if (donGiaStr == null || !donGiaStr.matches(regex)) {
            JOptionPane.showMessageDialog(this, "Đơn giá không hợp lệ!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            double donGia = Double.parseDouble(donGiaStr);
            if (donGia <= 0) {
                JOptionPane.showMessageDialog(this, "Đơn giá phải > 0!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Đơn giá phải là một chữ số hợp lệ!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean kiemTratacGia(String tacGia) {
//        \\p{L}:Khớp với bất kỳ chữ cái Unicode nào (bao gồm cả tiếng Việt như Đ, á, é, v.v.).
//        \\p{M}:Hỗ trợ các ký tự dấu kết hợp, như dấu sắc ́, dấu huyền ̀, dấu nặng ̣, v.v. Điều này giúp nhận diện đúng các ký tự tiếng Việt.
//        \\s:Khớp với khoảng trắng (dấu cách giữa các từ trong tên tác giả).
//        .:Cho phép dấu chấm trong tên (như trong "J.R.R. Tolkien").
//        ':Hỗ trợ dấu nháy đơn trong tên (như "D'Artagnan").
//        -:Cho phép dấu gạch ngang (như trong "Jean-Paul Sartre").
//        +:Cho phép lặp lại bất kỳ số lần nào (1 hoặc nhiều ký tự).
        String regex = "^[\\p{L}\\s.'-]+$";
        return tacGia != null && !tacGia.trim().isEmpty() && tacGia.matches(regex);
    }

    private boolean kiemTraNhaXB(String nhaXB) {
//    \\p{L}:Khớp với các chữ cái Unicode (bao gồm chữ cái có dấu tiếng Việt).
//    \\p{M}:Khớp với các dấu kết hợp (như dấu sắc, dấu huyền) để hỗ trợ tiếng Việt đầy đủ.
//    0-9:Khớp với các chữ số.
//    \\s:Khớp với khoảng trắng giữa các từ.
//    Dấu chấm (.): Ví dụ: "NXB Văn Học."
//    Dấu phẩy (,): Không phổ biến nhưng có thể xuất hiện.
//    Dấu nháy đơn ('): Ví dụ: "Hội Nhà Văn's Collection."
//    Dấu gạch ngang (-): Ví dụ: "Mint-Books."

        String regex = "^[\\p{L}\\p{M}0-9\\s.,'-]+$";
        return nhaXB != null && !nhaXB.trim().isEmpty() && nhaXB.matches(regex);
    }

    private boolean kiemTraNamXB(String namXBStr) {
        try {
            int namXB = Integer.parseInt(namXBStr);
            if (namXB <= 0) {
                JOptionPane.showMessageDialog(this, "Năm xuất bản phải lớn hơn 0!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return false;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Năm xuất bản không hợp lệ", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean kiemTraTatCa() throws SQLException {
        String ISBN = jTextField_ISBN.getText();
        String tenSach = jTextField_TenSach.getText();
        String donGiaStr = jTextField_DonGia.getText();
        String tacGia = jTextField_TacGia.getText();
        String nhaXB = jTextField_NhaXuatBan.getText();
        String namXBStr = jTextField_NamXuatBan.getText();

        if (ISBN.trim().length() == 0 || !kiemTraISBN(ISBN)) {
            JOptionPane.showMessageDialog(this, "ISBN phải là 13 chữ số!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            jTextField_ISBN.requestFocus();
            jTextField_ISBN.selectAll();
            return false;
        }
        if (tenSach.trim().length() == 0 || !kiemTraTenSach(tenSach)) {
            JOptionPane.showMessageDialog(this, "Tên sách không hợp lệ!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            jTextField_TenSach.requestFocus();
            jTextField_TenSach.selectAll();
            return false;
        }
        if (donGiaStr.trim().length() == 0 || !kiemTraDonGia(donGiaStr)) {
            if (donGiaStr.trim().length() == 0) {
                JOptionPane.showMessageDialog(this, "Đơn giá không hợp lệ!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
            jTextField_DonGia.requestFocus();
            jTextField_DonGia.selectAll();
            return false;
        }
        if (tacGia.trim().length() == 0 || !kiemTratacGia(tacGia)) {
            JOptionPane.showMessageDialog(this, "Tác giả không hợp lệ!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            jTextField_TacGia.requestFocus();
            jTextField_TacGia.selectAll();
            return false;
        }
        if (nhaXB.trim().length() == 0 || !kiemTraNhaXB(nhaXB)) {
            JOptionPane.showMessageDialog(this, "Tên nhà xuất bản không hợp lệ!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            jTextField_NhaXuatBan.requestFocus();
            jTextField_NhaXuatBan.selectAll();
            return false;
        }
        if (namXBStr.trim().length() == 0 || !kiemTraNamXB(namXBStr)) {
            if (namXBStr.trim().length() == 0) {
                JOptionPane.showMessageDialog(this, "Năm xuất bản không hợp lệ!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
            jTextField_NamXuatBan.requestFocus();
            jTextField_NamXuatBan.selectAll();
            return false;
        }
        if (anh == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ảnh!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        
//        System.out.println("Sua chua con lai trong kho: " + (sucChua - soLuongSach));
        if ((int) jSpinner_SoLuong.getValue() == 0) {
            JOptionPane.showMessageDialog(this, "Số lượng phải > 0!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            jSpinner_SoLuong.requestFocus(); // Đặt focus vào JSpinner
            spinnerTextField.requestFocus(); // Đặt focus vào trường văn bản bên trong JSpinner
            spinnerTextField.selectAll(); // Chọn tất cả văn bản trong trường
            return false;
        }
        String tenKho = jComboBox_Kho.getSelectedItem().toString();
        String maKho = khoHang_dao.getMaKhoTheoTenKho(tenKho);
        int sucChua = khoHang_dao.getKhoHangTheoMaKho(maKho).getSucChua();
        int soLuongSach = chiTietKhoHang_dao.getSoLuongSachTheoKho(maKho);
        int soLuongSachConLai=sucChua-soLuongSach;
        if ((int) jSpinner_SoLuong.getValue() > soLuongSachConLai) {
            JOptionPane.showMessageDialog(this, "Số lượng sách lớn hơn sức chứa trong kho!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            jSpinner_SoLuong.requestFocus(); // Đặt focus vào JSpinner
            spinnerTextField.requestFocus(); // Đặt focus vào trường văn bản bên trong JSpinner
            spinnerTextField.selectAll(); // Chọn tất cả văn bản trong trường
            return false;
        }
        return true;
    }
    private void jButton_ThemSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ThemSachActionPerformed
        // TODO add your handling code here:

        boolean kiemTra = false;
        try {
            kiemTra = kiemTraTatCa();
        } catch (SQLException ex) {
            Logger.getLogger(Sach_ThemSach.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (kiemTra) {
            String ISBN = jTextField_ISBN.getText().trim();
            String tenSach = jTextField_TenSach.getText().trim();
            String donGiaStr = jTextField_DonGia.getText().trim();
            double donGia = Double.parseDouble(donGiaStr);
            String loaiSach = jComboBox_LoaiSach.getSelectedItem().toString();
            int soLuong = (int) jSpinner_SoLuong.getValue();
            String tacGia = jTextField_TacGia.getText().trim();
            String nhaXB = jTextField_NhaXuatBan.getText().trim();
            String namXBStr = jTextField_NamXuatBan.getText().trim();
            int namXB = Integer.parseInt(namXBStr);
            String tenKho = jComboBox_Kho.getSelectedItem().toString();
            try {
                Sach sach = new Sach(ISBN, tenSach, tacGia, namXB, nhaXB, soLuong, donGia, new LoaiSach("", loaiSach), new HinhAnh(anh), "Đang bán");
                if (!sach_dao.getDSSach().contains(sach)) {
                    if (JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn thêm sách này?", "Thông báo", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        sach_dao.themSach(sach);
                        dsSach.addDataToTable(sach);
                        KhoHang kh = khoHang_dao.getKhoTheoTenKho(tenKho);
                        chiTietKhoHang_dao.themChiTietKhoHang(new ChiTietKhoHang(createMaCTKH(), soLuong, new Sach(sach.getISBN()), new KhoHang(kh.getMaKhoHang())));
                        String maPhieuNhapKho = taoTuDong_MaPhieuNhapKho();
                        phieuNhapDao.insertPhieuNhapKho(maPhieuNhapKho, Date.valueOf(LocalDate.now()), "22685411", kh.getMaKhoHang(), soLuong);
                        chiTietPhieuNhap_dao.insertChiTietPhieuNhapKho(taoTuDong_MaChiTietPhieuNhapKho(), maPhieuNhapKho, soLuong, sach.getISBN());
                        int width = 300; // Chiều rộng của mã vạch
                        int height = 100; // Chiều cao của mã vạch
                        String userHome = System.getProperty("user.home");
                        String myData = "";
//                    for (Sach s : sach_dao.getDSSach()) {
//                        myData = s.getISBN();
//                        MultiFormatWriter barcodeWriter = new MultiFormatWriter();
//                        BitMatrix bitMatrix = barcodeWriter.encode(myData, BarcodeFormat.CODE_128, width, height);
//                        Path desktopPath = FileSystems.getDefault().getPath(userHome, "../isbn/", myData + ".png");
//                        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", desktopPath);
//                    }
                        this.dispose();
                        JOptionPane.showMessageDialog(this, "Thêm sách thành công", "Thông báo", JOptionPane.OK_OPTION);
                    } else {
                        jTextField_ISBN.selectAll();
                        jTextField_ISBN.requestFocus();
                    }

                } else {
                    jTextField_ISBN.selectAll();
                    jTextField_ISBN.requestFocus();
                    JOptionPane.showMessageDialog(this, "ISBN đã tồn tại!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }

            } catch (SQLException ex) {
                Logger.getLogger(Sach_ThemSach.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_jButton_ThemSachActionPerformed


    private void jButton_ThemAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ThemAnhActionPerformed
        // TODO add your handling code here:

        JFileChooser frame_chonAnh = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("File ảnh", "png", "jpq", "jpeg", "gif");
        frame_chonAnh.setFileFilter(filter);
        frame_chonAnh.setAcceptAllFileFilterUsed(false);

        int returnValue = frame_chonAnh.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String filePath = frame_chonAnh.getSelectedFile().getPath();

            Icon icon = new ImageScale().load(filePath, jLabel_AnhDaiDien.getWidth(), jLabel_AnhDaiDien.getHeight());
            jLabel_AnhDaiDien.setIcon(icon);

            image_url = new AddImageToData();
            String fileName = image_url.duaFileVaoThuMuc(new File(filePath), "src\\ServiceImage\\Sach_IMG", "../ServiceImage/Sach_IMG/");
            System.out.println(fileName);
            anh = fileName;
        }
    }//GEN-LAST:event_jButton_ThemAnhActionPerformed

    private void jButton_HuyBoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_HuyBoMouseClicked
        // TODO add your handling code here:

        // Hiển thị JOptionPane với kích thước chữ đã được chỉnh
        int result = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn hủy, các thông tin sẽ không đươc lưu?", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            if (spinnerInputVerifier != null && spinnerTextField != null) {
                spinnerTextField.setInputVerifier(null);
            }
            // Đóng dialog
            this.dispose();
        } else {
            jTextField_ISBN.requestFocus();
        }

    }//GEN-LAST:event_jButton_HuyBoMouseClicked

    private void jButton_HuyBoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_HuyBoActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_jButton_HuyBoActionPerformed
    @Override
    public void dispose() {
        if (spinnerInputVerifier != null && spinnerTextField != null) {
            spinnerTextField.setInputVerifier(null);
        }
        super.dispose();
    }

    public String createMaCTKH() {
        List<String> dsMaCT = chiTietKhoHang_dao.getMaChiTietKhoHang();
        String lastMaCT = dsMaCT.get(dsMaCT.size() - 1);
        String prefix = lastMaCT.substring(0, 4);
        String numberPart = lastMaCT.substring(4);
        int number = Integer.parseInt(numberPart);
        number++;
        String newNumberPart;
        if (number < 100) {
            newNumberPart = String.format("%02d", number); // Giữ 2 chữ số khi number < 100
        } else {
            newNumberPart = Integer.toString(number); // Sử dụng 3 chữ số khi number >= 100
        }
        return prefix + newNumberPart;
    }
    public String taoTuDong_MaChiTietPhieuNhapKho() {
        // Lấy mã chi tiết phiếu nhập kho mới nhất từ cơ sở dữ liệu
        String lastMaChiTietPhieuNhapKho = chiTietPhieuNhap_dao.getLastMaChiTietPhieuNhapKho();

        int newNumber;
        if (lastMaChiTietPhieuNhapKho != null && lastMaChiTietPhieuNhapKho.startsWith(ma_CT_PNK)) {
            // Tách phần số ra khỏi mã và tăng lên 1
            String numberPart = lastMaChiTietPhieuNhapKho.substring(ma_CT_PNK.length());
            newNumber = Integer.parseInt(numberPart) + 1;
        } else {
            // Nếu không có mã nào trong CSDL, bắt đầu từ 1
            newNumber = 1;
        }

        // Định dạng lại mã với tiền tố và phần số đủ 5 chữ số
        return ma_CT_PNK + String.format("%05d", newNumber);
    }

    // Hàm tạo mã phiếu nhập kho tự động tăng
    public String taoTuDong_MaPhieuNhapKho() {
        // Lấy mã phiếu nhập kho cuối cùng từ cơ sở dữ liệu
        String lastMaPhieuNhapKho = phieuNhapDao.getLastMaPhieuNhapKho();

        int newNumber;
        if (lastMaPhieuNhapKho != null && lastMaPhieuNhapKho.startsWith(ma_PNK)) {
            // Tách phần số ra khỏi mã và tăng lên 1
            String numberPart = lastMaPhieuNhapKho.substring(ma_PNK.length());
            newNumber = Integer.parseInt(numberPart) + 1;
        } else {
            // Nếu không có mã nào trong CSDL, bắt đầu từ 1
            newNumber = 1;
        }

        // Định dạng lại mã với tiền tố và phần số đủ 5 chữ số
        return ma_PNK + String.format("%04d", newNumber);
    }



    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Sach_ThemSach dialog = new Sach_ThemSach(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton_ThemAnh;
    private javax.swing.JButton jButton_ThemSach;
    private javax.swing.JComboBox<String> jComboBox_Kho;
    private javax.swing.JComboBox<String> jComboBox_LoaiSach;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_AnhDaiDien;
    private javax.swing.JLabel jLabel_DonGia;
    private javax.swing.JLabel jLabel_ISBN;
    private javax.swing.JLabel jLabel_NamXuatBan;
    private javax.swing.JLabel jLabel_NhaXuatBan;
    private javax.swing.JLabel jLabel_TacGia;
    private javax.swing.JLabel jLabel_TenLoaiSach;
    private javax.swing.JLabel jLabel_TenSach;
    private javax.swing.JLabel jLabel_ThemNhanVien;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSpinner jSpinner_SoLuong;
    private javax.swing.JTextField jTextField_DonGia;
    private javax.swing.JTextField jTextField_ISBN;
    private javax.swing.JTextField jTextField_NamXuatBan;
    private javax.swing.JTextField jTextField_NhaXuatBan;
    private javax.swing.JTextField jTextField_TacGia;
    private javax.swing.JTextField jTextField_TenSach;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import entity.HinhAnh;
import entity.LoaiSach;
import entity.Sach;
import function.AddImageToData;
import function.ImageScale;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Tan Nghi
 */
public class Sach_SuaSach extends javax.swing.JDialog {

    /**
     * Creates new form NewJDialog
     */
    private Sach_QuanLySach dsSach;
    private Sach sach;
    private String anh;
    private AddImageToData image_url;
    private int soLuong;
    public Sach_SuaSach(java.awt.Frame parent, boolean modal, Sach_QuanLySach dsSach, Sach sach) {
        super(parent, modal);
        this.setUndecorated(true);
        this.dsSach = dsSach;
        initComponents();
        setLocationRelativeTo(null);
        initialData(sach);
    }
    private Sach_SuaSach(java.awt.Frame parent, boolean modal) {
        
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
        jPanel2 = new jPanelGradient();
        jLabel_ISBN = new javax.swing.JLabel();
        jLabel_TenSach = new javax.swing.JLabel();
        jLabel_DonGia = new javax.swing.JLabel();
        jLabel_TenLoaiSach = new javax.swing.JLabel();
        jLabel_TacGia = new javax.swing.JLabel();
        jLabel_NhaXuatBan = new javax.swing.JLabel();
        jLabel_NamXuatBan = new javax.swing.JLabel();
        jButton_Luu = new javax.swing.JButton();
        jButton_HuyBo = new javax.swing.JButton();
        jTextField_TenSach = new javax.swing.JTextField();
        jTextField_ISBN = new javax.swing.JTextField();
        jTextField_NhaXuatBan = new javax.swing.JTextField();
        jTextField_NamXuatBan = new javax.swing.JTextField();
        jButton_ThemAnh = new javax.swing.JButton();
        jTextField_DonGia = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel_Anh = new javax.swing.JLabel();
        jComboBox_LoaiSach = new javax.swing.JComboBox<>();
        jTextField_TacGia = new javax.swing.JTextField();
        jLabel_ThemNhanVien = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1214, 755));

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

        jButton_Luu.setBackground(new java.awt.Color(102, 102, 0));
        jButton_Luu.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_Luu.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Luu.setText("Lưu");
        jButton_Luu.setBorder(null);
        jButton_Luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LuuActionPerformed(evt);
            }
        });

        jButton_HuyBo.setBackground(new java.awt.Color(153, 0, 51));
        jButton_HuyBo.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_HuyBo.setForeground(new java.awt.Color(255, 255, 255));
        jButton_HuyBo.setText("Hủy bỏ");
        jButton_HuyBo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_HuyBoActionPerformed(evt);
            }
        });

        jTextField_TenSach.setBackground(new java.awt.Color(240, 240, 240));
        jTextField_TenSach.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jTextField_TenSach.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTextField_ISBN.setEditable(false);
        jTextField_ISBN.setBackground(new java.awt.Color(240, 240, 240));
        jTextField_ISBN.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jTextField_ISBN.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField_ISBN.setFocusable(false);

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

        jLabel_Anh.setBackground(new java.awt.Color(204, 255, 153));
        jLabel_Anh.setPreferredSize(new java.awt.Dimension(327, 342));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_Anh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_Anh, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
        );

        jComboBox_LoaiSach.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jComboBox_LoaiSach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiểu thuyết", "Lịch sử", "Thiếu nhi", "Văn hóa xã hội", "Tâm lý, tình cảm" }));

        jTextField_TacGia.setBackground(new java.awt.Color(240, 240, 240));
        jTextField_TacGia.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jTextField_TacGia.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

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
                                .addComponent(jLabel_ISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(76, 76, 76)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField_NhaXuatBan, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_TenSach, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_NamXuatBan, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_ISBN)
                            .addComponent(jTextField_TacGia, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox_LoaiSach, 0, 451, Short.MAX_VALUE)
                            .addComponent(jTextField_DonGia, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_Luu, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_HuyBo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_TenLoaiSach)
                            .addComponent(jComboBox_LoaiSach, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
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
                            .addComponent(jLabel_NamXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Luu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_HuyBo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jLabel_ThemNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_ThemNhanVien.setFont(new java.awt.Font("Arial", 3, 28)); // NOI18N
        jLabel_ThemNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_ThemNhanVien.setText("Cập nhật thông tin sách");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel_ThemNhanVien)
                .addContainerGap(877, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel_ThemNhanVien)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_ThemAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ThemAnhActionPerformed
        // TODO add your handling code here:
        JFileChooser frame_chonAnh = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("File ảnh", "png", "jpq", "jpeg", "gif");
        frame_chonAnh.setFileFilter(filter);
        frame_chonAnh.setAcceptAllFileFilterUsed(false);
        int returnValue = frame_chonAnh.showOpenDialog(null);
        if(returnValue == JFileChooser.APPROVE_OPTION){
            String filePath = frame_chonAnh.getSelectedFile().getPath();
            Icon icon = new ImageScale().load(filePath, jLabel_Anh.getWidth(), jLabel_Anh.getHeight());
            jLabel_Anh.setIcon(icon);
            image_url = new AddImageToData();
            String fileName =image_url.duaFileVaoThuMuc(new File(filePath), "src\\ServiceImage\\Sach_IMG", "/ServiceImage/Sach_IMG/");
            System.out.println(fileName);
            anh = fileName;
        }
    }//GEN-LAST:event_jButton_ThemAnhActionPerformed

    private void jButton_HuyBoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_HuyBoActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton_HuyBoActionPerformed

    private void jButton_LuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LuuActionPerformed
        try {    
            // TODO add your handling code here:
            
            // TODO add your handling code here:
            Sach sach = newData();
            dsSach.editDataToTable(sach);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(Sach_SuaSach.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton_LuuActionPerformed
    private void initialData(Sach sach){
        jTextField_ISBN.setText(sach.getISBN());
        jTextField_TenSach.setText(sach.getTenSach());
        jComboBox_LoaiSach.setSelectedItem(sach.getLoaiSach().getTenLoai());
        jTextField_TacGia.setText(sach.getTacGia());
        jTextField_NamXuatBan.setText(String.valueOf(sach.getNamXB()));
        jTextField_NhaXuatBan.setText(sach.getNhaXB());
        jTextField_DonGia.setText(String.valueOf(sach.getGiaGoc()));
        soLuong = sach.getSoLuong();
        ImageIcon image = new javax.swing.ImageIcon(getClass().getResource(sach.getAnh().getUrl()));
        Image imageFit = image.getImage().getScaledInstance(jLabel_Anh.getWidth(), jLabel_Anh.getHeight(), Image.SCALE_SMOOTH);
        jLabel_Anh.setIcon(new ImageIcon(imageFit));
        anh = sach.getAnh().getUrl();
    }
    private Sach newData(){
        String ISBN = jTextField_ISBN.getText();
        String tenSach = jTextField_TenSach.getText();
        String tacGia = jTextField_TacGia.getText();
        String nhaXB = jTextField_NhaXuatBan.getText();
        int namXB = Integer.parseInt(jTextField_NamXuatBan.getText());
        String loaiSach = jComboBox_LoaiSach.getSelectedItem().toString();
        Double donGia = Double.valueOf(jTextField_DonGia.getText());
        int sl = soLuong;
        return new Sach(ISBN, tenSach, tacGia, namXB, nhaXB, soLuong, donGia, new LoaiSach("", loaiSach), new HinhAnh(anh), "Đang bán");
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
            java.util.logging.Logger.getLogger(Sach_SuaSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sach_SuaSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sach_SuaSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sach_SuaSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                Sach_SuaSach dialog = new Sach_SuaSach(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> jComboBox_LoaiSach;
    private javax.swing.JLabel jLabel_Anh;
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
    private javax.swing.JTextField jTextField_DonGia;
    private javax.swing.JTextField jTextField_ISBN;
    private javax.swing.JTextField jTextField_NamXuatBan;
    private javax.swing.JTextField jTextField_NhaXuatBan;
    private javax.swing.JTextField jTextField_TacGia;
    private javax.swing.JTextField jTextField_TenSach;
    // End of variables declaration//GEN-END:variables
}

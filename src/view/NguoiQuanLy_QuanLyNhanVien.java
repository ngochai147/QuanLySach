/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import button.TableActionCellEditor;
import button.TableActionEvent;
import button.TableActionRender;
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entity.NhanVien;
import java.awt.Color;
import java.awt.Component;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tan Nghi
 */
public class NguoiQuanLy_QuanLyNhanVien extends javax.swing.JInternalFrame {

    private final NhanVien_DAO nhanVien_dao;
    private final DefaultTableModel model;
    private TaiKhoan_DAO taiKhoan_dao;
    /**
     * Creates new form TrangQuanLyNhanVien_GUI
     * @throws java.sql.SQLException
     */
    public NguoiQuanLy_QuanLyNhanVien() throws SQLException {
        initComponents();
        nhanVien_dao = new NhanVien_DAO();
        taiKhoan_dao = new TaiKhoan_DAO();
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        model = (DefaultTableModel) jTable_QuanLyNhanVien.getModel();
        addButtonToTable(model);
        for (NhanVien x : nhanVien_dao.getDSNhanVien()){
            if(!x.getChucVu().getChucVu().equalsIgnoreCase("Quản lý")){
                model.addRow(new Object[]{
                    x.getMaNV(), x.getHoTen(), x.getSoDienThoai(),
                    x.isGioiTinh() ? "Nữ" : "Nam", x.getChucVu().getChucVu()
                });
            }
        }
       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton_XoaNhieu = new javax.swing.JButton();
        jComboBox_TieuChi = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_QuanLyNhanVien = new javax.swing.JTable();
        jButton_XuatExcel = new javax.swing.JButton();
        jButton_LamMoi = new javax.swing.JButton();
        jButton_ThemNhanVien = new javax.swing.JButton();
        jButton_TimKiem = new javax.swing.JButton();
        jTextField_TimKiem = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 255, 153));
        setBorder(null);
        setPreferredSize(new java.awt.Dimension(1585, 700));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1585, 700));
        jPanel1.setLayout(null);

        jButton_XoaNhieu.setBackground(new java.awt.Color(153, 0, 51));
        jButton_XoaNhieu.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_XoaNhieu.setForeground(new java.awt.Color(255, 255, 255));
        jButton_XoaNhieu.setText("Xóa ");
        jButton_XoaNhieu.setBorder(null);
        jButton_XoaNhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton_XoaNhieuActionPerformed(evt);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        jPanel1.add(jButton_XoaNhieu);
        jButton_XoaNhieu.setBounds(350, 160, 110, 50);

        jComboBox_TieuChi.setBackground(new java.awt.Color(102, 102, 0));
        jComboBox_TieuChi.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jComboBox_TieuChi.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox_TieuChi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiêu chí", "Mã nhân viên", "Tên nhân viên", "Chức vụ" }));
        Color customGreen = new Color(102,102,0);
        jComboBox_TieuChi.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (isSelected) {
                    c.setBackground(customGreen);   // Màu nền khi mục được chọn
                    c.setForeground(Color.WHITE);   // Màu chữ khi mục được chọn
                } else {
                    c.setBackground(Color.white); // Màu nền cho các mục không được chọn
                    c.setForeground(customGreen);      // Màu chữ cho các mục không được chọn
                }

                return c;
            }
        });
        jComboBox_TieuChi.setBorder(null);
        jPanel1.add(jComboBox_TieuChi);
        jComboBox_TieuChi.setBounds(860, 160, 193, 52);

        jTable_QuanLyNhanVien.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jTable_QuanLyNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV", "Họ và tên nhân viên", "SDT", "Giới tính", "Chức vụ", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable_QuanLyNhanVien.setToolTipText("");
        jTable_QuanLyNhanVien.setFocusable(false);
        jTable_QuanLyNhanVien.setRowHeight(50);
        jTable_QuanLyNhanVien.setRowMargin(2);
        jTable_QuanLyNhanVien.setSelectionBackground(new java.awt.Color(153, 204, 0));
        jTable_QuanLyNhanVien.setShowHorizontalLines(true);
        jTable_QuanLyNhanVien.setShowVerticalLines(true);
        jTable_QuanLyNhanVien.getTableHeader().setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        jScrollPane1.setViewportView(jTable_QuanLyNhanVien);
        if (jTable_QuanLyNhanVien.getColumnModel().getColumnCount() > 0) {
            jTable_QuanLyNhanVien.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable_QuanLyNhanVien.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTable_QuanLyNhanVien.getColumnModel().getColumn(2).setPreferredWidth(65);
            jTable_QuanLyNhanVien.getColumnModel().getColumn(3).setPreferredWidth(35);
            jTable_QuanLyNhanVien.getColumnModel().getColumn(4).setPreferredWidth(45);
            jTable_QuanLyNhanVien.getColumnModel().getColumn(5).setPreferredWidth(70);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(25, 236, 1490, 375);

        jButton_XuatExcel.setBackground(new java.awt.Color(102, 102, 0));
        jButton_XuatExcel.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_XuatExcel.setForeground(new java.awt.Color(255, 255, 255));
        jButton_XuatExcel.setText("Xuất excel");
        jButton_XuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_XuatExcelActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_XuatExcel);
        jButton_XuatExcel.setBounds(30, 630, 140, 50);

        jButton_LamMoi.setBackground(new java.awt.Color(102, 102, 0));
        jButton_LamMoi.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_LamMoi.setForeground(new java.awt.Color(255, 255, 255));
        jButton_LamMoi.setText("Làm mới");
        jButton_LamMoi.setBorder(null);
        jButton_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton_LamMoiActionPerformed(evt);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        jPanel1.add(jButton_LamMoi);
        jButton_LamMoi.setBounds(220, 160, 114, 52);

        jButton_ThemNhanVien.setBackground(new java.awt.Color(102, 102, 0));
        jButton_ThemNhanVien.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_ThemNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        jButton_ThemNhanVien.setText("Thêm nhân viên");
        jButton_ThemNhanVien.setBorder(null);
        jButton_ThemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ThemNhanVienActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_ThemNhanVien);
        jButton_ThemNhanVien.setBounds(30, 160, 175, 52);

        jButton_TimKiem.setBackground(new java.awt.Color(102, 102, 0));
        jButton_TimKiem.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_TimKiem.setForeground(new java.awt.Color(255, 255, 255));
        jButton_TimKiem.setText("Tìm kiếm");
        jButton_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_TimKiemActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_TimKiem);
        jButton_TimKiem.setBounds(1370, 160, 140, 52);

        jTextField_TimKiem.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jTextField_TimKiem.setMaximumSize(new java.awt.Dimension(21474836, 2147483647));
        jPanel1.add(jTextField_TimKiem);
        jTextField_TimKiem.setBounds(1060, 160, 300, 53);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/label_QuanLyNhanVien.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(386, 18, 687, 118);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/anhnen.jpg"))); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(1585, 700));
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 0, 1585, 730);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1585, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 676, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_ThemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ThemNhanVienActionPerformed
        // TODO add your handling code here:
        NguoiQuanLy_ThemNV nguoiQuanLy_ThemNV = new NguoiQuanLy_ThemNV(new javax.swing.JFrame(), true, this);
        nguoiQuanLy_ThemNV.setVisible(true);
    }//GEN-LAST:event_jButton_ThemNhanVienActionPerformed

    private void jButton_XuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_XuatExcelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_XuatExcelActionPerformed

    private void jButton_XoaNhieuActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {//GEN-FIRST:event_jButton_XoaNhieuActionPerformed
        // TODO add your handling code here:
        if(jTable_QuanLyNhanVien.isEditing()){
            jTable_QuanLyNhanVien.getCellEditor().stopCellEditing();
        }
        int[] n = jTable_QuanLyNhanVien.getSelectedRows();
        for (int i = n.length - 1; i >= 0; i--) {
            String ma = model.getValueAt(n[i], 0).toString();
            if(taiKhoan_dao.xoaTaiKhoan(ma)){
                if(nhanVien_dao.xoaNhanVien(ma)){
                    model.removeRow(n[i]);
                }
            }
        }
    }//GEN-LAST:event_jButton_XoaNhieuActionPerformed

    private void jButton_LamMoiActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {//GEN-FIRST:event_jButton_LamMoiActionPerformed
        // TODO add your handling code here:
        if(jTable_QuanLyNhanVien.isEditing()){
            jTable_QuanLyNhanVien.getCellEditor().stopCellEditing();
        }
        model.setRowCount(0);
        for (NhanVien x : nhanVien_dao.getDSNhanVien()){
            if(!x.getChucVu().getChucVu().equalsIgnoreCase("Quản lý")){
                model.addRow(new Object[]{
                        x.getMaNV(), x.getHoTen(), x.getSoDienThoai(),
                        x.isGioiTinh() ? "Nữ" : "Nam", x.getChucVu().getChucVu()
                });
            }
        }

    }//GEN-LAST:event_jButton_LamMoiActionPerformed

    private void jButton_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_TimKiemActionPerformed
        // TODO add your handling code here:
        if(jTable_QuanLyNhanVien.isEditing()){
            jTable_QuanLyNhanVien.getCellEditor().stopCellEditing();
        }
        int i = -1;
        String timKiem = jTextField_TimKiem.getText();
        String tieuChi = jComboBox_TieuChi.getSelectedItem().toString();
        if(tieuChi.equalsIgnoreCase("Mã nhân viên")){
            try {
                NhanVien nv = nhanVien_dao.getNhanVienTheoMaNV(timKiem);
                i = nhanVien_dao.getDSNhanVien().indexOf(nv);
                if(i >= 0){
                    NguoiQuanLy_ThongTinChiTietNhanVien nguoiQuanLy_ThongTinChiTietNhanVien= null;
                    try {
                        nguoiQuanLy_ThongTinChiTietNhanVien = new NguoiQuanLy_ThongTinChiTietNhanVien(new javax.swing.JFrame(), true, nv);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    nguoiQuanLy_ThongTinChiTietNhanVien.setVisible(true);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Sach_QuanLySach.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(tieuChi.equalsIgnoreCase("Tên nhân viên")){
            try {
                model.setRowCount(0);
                for (NhanVien x : nhanVien_dao.getDSNhanVienTheoTenNhanVien(timKiem)){
                    model.addRow(new Object[]{
                            x.getMaNV(), x.getHoTen(), x.getSoDienThoai(),
                            x.isGioiTinh() ? "Nữ" : "Nam", x.getChucVu().getChucVu()
                    });
                }
            } catch (SQLException ex) {
                Logger.getLogger(Sach_QuanLySach.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                model.setRowCount(0);
                for (NhanVien x : nhanVien_dao.getDSNhanVienTheoChucVu(timKiem)){
                    model.addRow(new Object[]{
                            x.getMaNV(), x.getHoTen(), x.getSoDienThoai(),
                            x.isGioiTinh() ? "Nữ" : "Nam", x.getChucVu().getChucVu()
                    });
                }
            } catch (SQLException ex) {
                Logger.getLogger(Sach_QuanLySach.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton_TimKiemActionPerformed
    private void addButtonToTable(DefaultTableModel model){
        TableActionEvent event;
        event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {

                NguoiQuanLy_ChinhSuaNhanVien nguoiQuanLy_ChinhSuaNhanVien = null;
                try {
                    nguoiQuanLy_ChinhSuaNhanVien = new NguoiQuanLy_ChinhSuaNhanVien(new javax.swing.JFrame(), true, NguoiQuanLy_QuanLyNhanVien.this, getDataToNhanVien());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                nguoiQuanLy_ChinhSuaNhanVien.setVisible(true);
            }

            @Override
            public void onDelete(int row) {

            }

            @Override
            public void onView(int row) {
                NguoiQuanLy_ThongTinChiTietNhanVien nguoiQuanLy_ThongTinChiTietNhanVien= null;
                try {

                    nguoiQuanLy_ThongTinChiTietNhanVien = new NguoiQuanLy_ThongTinChiTietNhanVien(new javax.swing.JFrame(), true, getDataToNhanVien());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                nguoiQuanLy_ThongTinChiTietNhanVien.setVisible(true);
            }
        };
        jTable_QuanLyNhanVien.getColumnModel().getColumn(5).setCellRenderer(new TableActionRender(2));
        jTable_QuanLyNhanVien.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event, 2));
    }
    public NhanVien getDataToNhanVien() throws SQLException {
        int n = jTable_QuanLyNhanVien.getSelectedRow();
        String maNhanVien = model.getValueAt(n, 0).toString();
        return nhanVien_dao.getNhanVienTheoMaNV(maNhanVien);
    }
    public void addDataToTable(NhanVien x){
        model.addRow(new Object[]{
                x.getMaNV(), x.getHoTen(), x.getSoDienThoai(),
                x.isGioiTinh() ? "Nữ" : "Nam", x.getChucVu().getChucVu()
        });
    }
    public void editDataToTable(NhanVien x) throws SQLException {
        if(nhanVien_dao.capNhatNhanVien(x)){
            int n = jTable_QuanLyNhanVien.getSelectedRow();
            model.setValueAt(x.getMaNV(), n, 0);
            model.setValueAt(x.getHoTen(), n, 1);
            model.setValueAt(x.getSoDienThoai(), n, 2);
            model.setValueAt(x.isGioiTinh() ? "Nữ" : "Nam", n, 3);
            model.setValueAt(x.getChucVu().getChucVu(), n, 4);
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_LamMoi;
    private javax.swing.JButton jButton_ThemNhanVien;
    private javax.swing.JButton jButton_TimKiem;
    private javax.swing.JButton jButton_XoaNhieu;
    private javax.swing.JButton jButton_XuatExcel;
    private javax.swing.JComboBox<String> jComboBox_TieuChi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_QuanLyNhanVien;
    private javax.swing.JTextField jTextField_TimKiem;
    // End of variables declaration//GEN-END:variables
}

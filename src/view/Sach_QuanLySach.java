/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import button.TableActionCellEditor;
import button.TableActionEvent;
import button.TableActionRender;
import dao.ChiTietKhoHang_DAO;
import dao.Sach_DAO;
import entity.ChiTietKhoHang;
import entity.Sach;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Huu Thai
 */
public class Sach_QuanLySach extends javax.swing.JInternalFrame {

    private final Sach_DAO sach_dao;
    private DefaultTableModel model = null;
    private final DecimalFormat df;
    private ChiTietKhoHang_DAO chiTietKhoHangDao;

    /**
     * Creates new form TrangQLSach_GUI
     */
   
    public Sach_QuanLySach() throws SQLException {
        sach_dao = new Sach_DAO();
        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        addButtonToTable(model);
        df = new DecimalFormat("#");
        for (Sach x : sach_dao.getDSSach()){
            if(x.getTrangThai().equalsIgnoreCase("Đang bán")){

                model.addRow(new Object[]{x.getISBN(),
                        x.getTenSach(),
                        x.getLoaiSach().getTenLoai(),
                        x.getSoLuong(),
                        df.format(x.getGiaGoc()) + " VND"});
            }
        }
        chiTietKhoHangDao = new ChiTietKhoHang_DAO();
        JTableHeader header =  jTable_Sach.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 18));
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton_ThemSach = new javax.swing.JButton();
        jButton_LamMoi = new javax.swing.JButton();
        jButton_XoaNhieu = new javax.swing.JButton();
        jComboBox_TieuChi = new javax.swing.JComboBox<>();
        jTextField_TimKiem = new javax.swing.JTextField();
        jButton_TimKiem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Sach = new javax.swing.JTable();
        jButton_XuatExcel = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1600, 716));
        jPanel1.setLayout(null);

        jButton_ThemSach.setBackground(new java.awt.Color(102, 102, 0));
        jButton_ThemSach.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_ThemSach.setForeground(new java.awt.Color(255, 255, 255));
        jButton_ThemSach.setText("Thêm sách");
        jButton_ThemSach.setPreferredSize(new java.awt.Dimension(217, 60));
        jButton_ThemSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ThemSachActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_ThemSach);
        jButton_ThemSach.setBounds(18, 143, 170, 47);

        jButton_LamMoi.setBackground(new java.awt.Color(102, 102, 0));
        jButton_LamMoi.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_LamMoi.setForeground(new java.awt.Color(255, 255, 255));
        jButton_LamMoi.setText("Làm mới");
        jButton_LamMoi.setPreferredSize(new java.awt.Dimension(200, 60));
        jButton_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LamMoiActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_LamMoi);
        jButton_LamMoi.setBounds(217, 143, 147, 47);

        jButton_XoaNhieu.setBackground(new java.awt.Color(153, 0, 51));
        jButton_XoaNhieu.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_XoaNhieu.setForeground(new java.awt.Color(255, 255, 255));
        jButton_XoaNhieu.setText("Xóa");
        jButton_XoaNhieu.setPreferredSize(new java.awt.Dimension(200, 60));
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
        jButton_XoaNhieu.setBounds(382, 144, 137, 46);

        jComboBox_TieuChi.setBackground(new java.awt.Color(102, 102, 0));
        jComboBox_TieuChi.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jComboBox_TieuChi.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox_TieuChi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Mã sách", "Tác giả", "Loại sách"}));
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
        jPanel1.add(jComboBox_TieuChi);
        jComboBox_TieuChi.setBounds(920, 140, 173, 46);

        jTextField_TimKiem.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jPanel1.add(jTextField_TimKiem);
        jTextField_TimKiem.setBounds(1100, 140, 247, 46);

        jButton_TimKiem.setBackground(new java.awt.Color(102, 102, 0));
        jButton_TimKiem.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_TimKiem.setForeground(new java.awt.Color(255, 255, 255));
        jButton_TimKiem.setText("Tìm kiếm");
        jButton_TimKiem.setPreferredSize(new java.awt.Dimension(200, 60));
        jButton_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_TimKiemActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_TimKiem);
        jButton_TimKiem.setBounds(1360, 140, 153, 46);

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1500, 386));

        jTable_Sach.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        String columnNames[] = { "ISBN", "Tên sách", "Loại sách", "Số lượng (quyển)", "Đơn giá (VNĐ)", ""};
        model = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Trả về false để chặn mọi ô không thể chỉnh sửa
                return column == getColumnCount() - 1;
            }
        };
        model.setRowCount(0);
        jTable_Sach.setModel(model);
        jTable_Sach.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTable_Sach.setPreferredSize(new java.awt.Dimension(1500, 1000));
        jTable_Sach.setRowHeight(40);
        jTable_Sach.setSelectionBackground(new java.awt.Color(153, 204, 0));
        jTable_Sach.setSelectionForeground(new java.awt.Color(51, 51, 51));
        jTable_Sach.setShowGrid(true);
        jScrollPane1.setViewportView(jTable_Sach);
        if (jTable_Sach.getColumnModel().getColumnCount() > 0) {
            jTable_Sach.getColumnModel().getColumn(0).setPreferredWidth(100);
            jTable_Sach.getColumnModel().getColumn(1).setPreferredWidth(250);
            jTable_Sach.getColumnModel().getColumn(2).setPreferredWidth(10);
            jTable_Sach.getColumnModel().getColumn(3).setPreferredWidth(5);
            jTable_Sach.getColumnModel().getColumn(4).setPreferredWidth(5);
            jTable_Sach.getColumnModel().getColumn(5).setPreferredWidth(70);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(18, 208, 1500, 386);

        jButton_XuatExcel.setBackground(new java.awt.Color(102, 102, 0));
        jButton_XuatExcel.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_XuatExcel.setForeground(new java.awt.Color(255, 255, 255));
        jButton_XuatExcel.setText("Xuất excel");
        jButton_XuatExcel.setPreferredSize(new java.awt.Dimension(200, 60));
        jButton_XuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_XuatExcelActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_XuatExcel);
        jButton_XuatExcel.setBounds(18, 612, 140, 46);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/jLabel_QuanLySach.png"))); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(494, 6, 468, 106);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/anhnen.jpg"))); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(1520, 716));
        jPanel1.add(jLabel3);
        jLabel3.setBounds(0, 0, 1690, 730);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_ThemSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ThemSachActionPerformed
        // TODO add your handling code here:
        Sach_ThemSach themSach;
        themSach = new Sach_ThemSach(new javax.swing.JFrame(), true, this);
        themSach.setVisible(true);     
    }//GEN-LAST:event_jButton_ThemSachActionPerformed

    private void jButton_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LamMoiActionPerformed
        // TODO add your handling code here:
        if(jTable_Sach.isEditing()){
            jTable_Sach.getCellEditor().stopCellEditing();
        }
        model.setRowCount(0);
        try {
            for (Sach x : sach_dao.getDSSach()){
                if(x.getTrangThai().equalsIgnoreCase("Đang bán")){
                    model.addRow(new Object[]{x.getISBN(),
                            x.getTenSach(),
                            x.getLoaiSach().getTenLoai(),
                            x.getSoLuong(),
                            df.format(x.getGiaGoc()) + " VND"});
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sach_QuanLySach.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_LamMoiActionPerformed

    private void jButton_XoaNhieuActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {//GEN-FIRST:event_jButton_XoaNhieuActionPerformed
        // TODO add your handling code here:
        if(jTable_Sach.isEditing()){
            jTable_Sach.getCellEditor().stopCellEditing();
        }
        int[] n = jTable_Sach.getSelectedRows();
        for (int i = n.length - 1; i >= 0; i--) {
            String ma = model.getValueAt(n[i], 0).toString();
            Sach s = sach_dao.getSachTheoMaSach(ma);
            if(s.getSoLuong() <= 0){
                if(sach_dao.xoaSach(ma)){
                    model.removeRow(n[i]);
                }
            }else {
                JOptionPane.showMessageDialog(this, "Không thể xóa sách có số lượng lớn hơn 0");
            }
        }
    }//GEN-LAST:event_jButton_XoaNhieuActionPerformed

    private void jButton_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_TimKiemActionPerformed
        // TODO add your handling code here:
        if(jTable_Sach.isEditing()){
            jTable_Sach.getCellEditor().stopCellEditing();
        }
        int i = -1;
        String timKiem = jTextField_TimKiem.getText();
        String tieuChi = jComboBox_TieuChi.getSelectedItem().toString();
        if(tieuChi.equalsIgnoreCase("Mã sách")){
            try {
                Sach sach = sach_dao.getSachTheoMaSach(timKiem);
                i = sach_dao.getDSSach().indexOf(sach);
                if(i >= 0){
                    try {
                        jTable_Sach.setRowSelectionInterval(i, i);
                        Sach_ThongTinSach thongTinSach = null;
                        thongTinSach = new Sach_ThongTinSach(new javax.swing.JFrame(), true, sach);
                        thongTinSach.setVisible(true);
                    } catch (SQLException ex) {
                        Logger.getLogger(Sach_QuanLySach.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Sach_QuanLySach.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(tieuChi.equalsIgnoreCase("Loại sách")){
            try {
                model.setRowCount(0);
                ArrayList<Sach> dsLoaiSach = sach_dao.getDSSachTheoTenLoai(timKiem);

                for (Sach x : dsLoaiSach){
                    if(x.getTrangThai().equalsIgnoreCase("Đang bán")){
                        model.addRow(new Object[]{x.getISBN(),
                                x.getTenSach(),
                                x.getLoaiSach().getTenLoai(),
                                x.getSoLuong(),
                                df.format(x.getGiaGoc()) + " VND"});
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Sach_QuanLySach.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else {
            try {
                model.setRowCount(0);
                ArrayList<Sach> dsLoaiSach = sach_dao.getDSSachTheoTacGia(timKiem);
                for (Sach x : dsLoaiSach){
                    if(x.getTrangThai().equalsIgnoreCase("Đang bán")){
                        model.addRow(new Object[]{x.getISBN(),
                                x.getTenSach(),
                                x.getLoaiSach().getTenLoai(),
                                x.getSoLuong(),
                                df.format(x.getGiaGoc()) + " VND"});
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Sach_QuanLySach.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton_TimKiemActionPerformed

    private void jButton_XuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_XuatExcelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_XuatExcelActionPerformed


    private void addButtonToTable(DefaultTableModel model){
        TableActionEvent event;
        event = new TableActionEvent() {
            private Sach sach;
            @Override
            public void onEdit(int row) {
                try {
                    sach = getDataToBook();
                    ChinhSuaThongTinSach suaSach ;
                    suaSach = new ChinhSuaThongTinSach(new javax.swing.JFrame(), true, Sach_QuanLySach.this, sach);
                    suaSach.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Sach_QuanLySach.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            @Override
            public void onDelete(int row) {
            }

            @Override
            public void onView(int row) {
                try {
                    sach = getDataToBook();
                    Sach_ThongTinSach thongTinSach = null;
                    thongTinSach = new Sach_ThongTinSach(new javax.swing.JFrame(), true, sach);
                    thongTinSach.setVisible(true);


                } catch (SQLException ex) {
                    Logger.getLogger(Sach_QuanLySach.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };
        jTable_Sach.getColumnModel().getColumn(5).setCellRenderer(new TableActionRender(2));
        jTable_Sach.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event, 2));
    }

    public boolean addDataToTable(Sach x) throws SQLException{
        if(sach_dao.themSach(x)){
            model.addRow(new Object[]{x.getISBN(), x.getTenSach(), x.getLoaiSach().getTenLoai(), x.getSoLuong(), df.format(x.getGiaGoc()) + " VND"});
            JOptionPane.showMessageDialog(this, "Them thanh cong");
            return true;
        }else {
            JOptionPane.showMessageDialog(this, "Them that bai");
            return false;
        }

    }
    public void editDataToTable(Sach x) throws SQLException {
        if(sach_dao.capNhatSach(x)){
            int n = jTable_Sach.getSelectedRow();
            model.setValueAt(x.getISBN(), n, 0);
            model.setValueAt(x.getTenSach(), n, 1);
            model.setValueAt(x.getLoaiSach().getTenLoai(), n, 2);
            model.setValueAt(x.getSoLuong(), n, 3);
            model.setValueAt(x.getGiaGoc(), n, 4);

        }
    }
    public Sach getDataToBook() throws SQLException{
        int n = jTable_Sach.getSelectedRow();
        String maSach = model.getValueAt(n, 0).toString();
        return sach_dao.getSachTheoMaSach(maSach);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_LamMoi;
    private javax.swing.JButton jButton_ThemSach;
    private javax.swing.JButton jButton_TimKiem;
    private javax.swing.JButton jButton_XoaNhieu;
    private javax.swing.JButton jButton_XuatExcel;
    private javax.swing.JComboBox<String> jComboBox_TieuChi;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Sach;
    private javax.swing.JTextField jTextField_TimKiem;
    // End of variables declaration//GEN-END:variables
}

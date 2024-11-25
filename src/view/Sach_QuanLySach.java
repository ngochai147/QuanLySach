/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import button.TableActionCellEditor;
import button.TableActionEvent;
import button.TableActionRender;
import dao.ChiTietKhoHang_DAO;
import dao.KhoHang_DAO;
import dao.Sach_DAO;
import entity.ChiTietKhoHang;
import entity.Sach;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.Dimension;
import javax.swing.plaf.basic.BasicScrollBarUI;
/**
 *
 * @author Huu Thai
 */
public class Sach_QuanLySach extends javax.swing.JInternalFrame {

    private final Sach_DAO sach_dao;
    private DefaultTableModel model = null;
    private final DecimalFormat df = new DecimalFormat("#,###");
    private ChiTietKhoHang_DAO chiTietKhoHangDao;
    private KhoHang_DAO khoHang_dao;
    private String tieuChi;
    private boolean isTimKiemUpdated;
    private Color customGreen;

    /**
     * Creates new form TrangQLSach_GUI
     */
    public Sach_QuanLySach() throws SQLException {
        sach_dao = new Sach_DAO();
        khoHang_dao = new KhoHang_DAO();
        chiTietKhoHangDao = new ChiTietKhoHang_DAO();
        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        model = (DefaultTableModel) jTable_Sach.getModel();
        addButtonToTable(model);
        jComboBox_TimKiem.addItem("");
        model.setRowCount(0);
        for (Sach x : sach_dao.getDSSach()) {
            if (x.getTrangThai().equalsIgnoreCase("Đang bán")) {
                jComboBox_TimKiem.addItem(x.getISBN());
                model.addRow(new Object[]{x.getISBN(),
                    x.getTenSach(),
                    x.getLoaiSach().getTenLoai(),
                    x.getSoLuong(),
                    df.format(x.getGiaGoc()) + " VND"});
            }
        }
        JTableHeader header = jTable_Sach.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 18));

        jTable_Sach.setPreferredSize(new Dimension(1500, jTable_Sach.getRowCount()*40));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField_TimKiem = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton_ThemSach = new javax.swing.JButton();
        jButton_LamMoi = new javax.swing.JButton();
        jButton_XoaNhieu = new javax.swing.JButton();
        jComboBox_TieuChi = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Sach = new javax.swing.JTable();
        jComboBox_TimKiem = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jTextField_TimKiem.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N

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
                jButton_XoaNhieuActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_XoaNhieu);
        jButton_XoaNhieu.setBounds(382, 144, 137, 46);

        jComboBox_TieuChi.setBackground(new java.awt.Color(102, 102, 0));
        jComboBox_TieuChi.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jComboBox_TieuChi.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox_TieuChi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Mã sách", "Tác giả", "Loại sách"}));
        customGreen = new Color(102,102,0);
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
        jComboBox_TieuChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_TieuChiActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox_TieuChi);
        jComboBox_TieuChi.setBounds(1090, 140, 173, 50);

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
        jTable_Sach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISBN", "Tên sách", "Loại sách", "Số lượng (quyển)", "Đơn giá", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_Sach.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTable_Sach.setPreferredSize(new java.awt.Dimension(1500, 1000));
        jTable_Sach.setRowHeight(40);
        jTable_Sach.setSelectionBackground(new java.awt.Color(153, 204, 0));
        jTable_Sach.setSelectionForeground(new java.awt.Color(51, 51, 51));
        jTable_Sach.setShowGrid(true);
        jScrollPane1.setViewportView(jTable_Sach);
        if (jTable_Sach.getColumnModel().getColumnCount() > 0) {
            jTable_Sach.getColumnModel().getColumn(3).setPreferredWidth(10);
            jTable_Sach.getColumnModel().getColumn(4).setPreferredWidth(10);
        }

        // Code of sub-components and layout - not shown here
        jScrollPane1.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                thumbColor = new Color(100, 100, 100); // Màu của thumb
                trackColor = new Color(220, 220, 220); // Màu của track
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }

            // Ghi đè phương thức paintThumb để bo tròn và làm ngắn chiều dài của thumb
            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Điều chỉnh kích thước chiều dài (height) của thumb để ngắn hơn
                int adjustedHeight = Math.max(30, thumbBounds.height - 20);  // Làm cho thumb ngắn hơn nhưng không thấp hơn 30 pixels
                int adjustedWidth = thumbBounds.width;

                // Thiết lập màu và hình dạng bo tròn
                g2.setColor(thumbColor);
                g2.fillRoundRect(thumbBounds.x, thumbBounds.y, adjustedWidth, adjustedHeight, 10, 10); // Bo tròn 10 pixel

                g2.dispose();
            }
        });

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(18, 208, 1500, 386);

        jComboBox_TimKiem.setBackground(new java.awt.Color(102, 102, 0));
        jComboBox_TimKiem.setFont(new java.awt.Font("Arial", 1, 20));
        customGreen = new Color(102,102,0);
        jComboBox_TimKiem.setRenderer(new DefaultListCellRenderer() {
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
        jComboBox_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_TimKiemActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox_TimKiem);
        jComboBox_TimKiem.setBounds(1270, 140, 240, 50);

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
        if (jTable_Sach.isEditing()) {
            jTable_Sach.getCellEditor().stopCellEditing();
        }

        jComboBox_TieuChi.setSelectedIndex(0);
        getTableDataDefault();
    }//GEN-LAST:event_jButton_LamMoiActionPerformed

    private void jButton_XoaNhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_XoaNhieuActionPerformed
        // TODO add your handling code here:
        if (jTable_Sach.isEditing()) {
            jTable_Sach.getCellEditor().stopCellEditing();
        }
        int[] n = jTable_Sach.getSelectedRows();
        if (n.length < 1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng được xóa", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        } else {
            if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa", "Cảnh báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                for (int i = n.length - 1; i >= 0; i--) {
                    String ma = model.getValueAt(n[i], 0).toString();
                    Sach s = null;
                    try {
                        s = sach_dao.getSachTheoMaSach(ma);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        if (sach_dao.xoaSach(ma)) {
                            model.removeRow(n[i]);
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }else{
                jTable_Sach.clearSelection();
            }
        }

    }//GEN-LAST:event_jButton_XoaNhieuActionPerformed

    private void jComboBox_TieuChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_TieuChiActionPerformed
        // TODO add your handling code here:
        tieuChi = jComboBox_TieuChi.getSelectedItem().toString();
        isTimKiemUpdated = false;
        if (tieuChi.equalsIgnoreCase("Mã sách")) {
            jComboBox_TimKiem.removeAllItems();
            jComboBox_TimKiem.addItem("");
            try {
                for (Sach x : sach_dao.getDSSach()) {
                    if (x.getTrangThai().equalsIgnoreCase("Đang bán")) {
                        jComboBox_TimKiem.addItem(x.getISBN());
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (tieuChi.equalsIgnoreCase("Loại sách")) {
            jComboBox_TimKiem.removeAllItems();
            jComboBox_TimKiem.addItem("");
            Set<String> dsLoaiSach = new HashSet<>();
            try {
                for (Sach x : sach_dao.getDSSach()) {
                    if (x.getTrangThai().equalsIgnoreCase("Đang bán")) {
                        String loaiSach = x.getLoaiSach().getTenLoai();
                        if (!dsLoaiSach.contains(loaiSach)) {
                            dsLoaiSach.add(loaiSach);
                            jComboBox_TimKiem.addItem(loaiSach);
                        }
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            jComboBox_TimKiem.removeAllItems();
            jComboBox_TimKiem.addItem("");
            Set<String> dsTacGia = new HashSet<>();
            try {
                for (Sach x : sach_dao.getDSSach()) {
                    if (x.getTrangThai().equalsIgnoreCase("Đang bán")) {
                        String tacGia = x.getTacGia();
                        if (!dsTacGia.contains(tacGia)) {
                            dsTacGia.add(tacGia);
                            jComboBox_TimKiem.addItem(tacGia);
                        }
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }//GEN-LAST:event_jComboBox_TieuChiActionPerformed

    private void jComboBox_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_TimKiemActionPerformed
        // TODO add your handling code here:
        if (jTable_Sach.isEditing()) {
            jTable_Sach.getCellEditor().stopCellEditing();
        }
        Object selectedItem = jComboBox_TimKiem.getSelectedItem();
        if (selectedItem == null) {
            return; // Không làm gì nếu không có item nào được chọn
        }
        String timKiem = jComboBox_TimKiem.getSelectedItem().toString();
        String tieuChi = jComboBox_TieuChi.getSelectedItem().toString();

        if (tieuChi.equalsIgnoreCase("Mã sách")) {
            if (timKiem.equalsIgnoreCase("")) {
                getTableDataDefault();
            } else {
                model.setRowCount(0);
                try {
                    Sach sach = sach_dao.getSachTheoMaSach(timKiem);
                    model.addRow(new Object[]{sach.getISBN(),
                        sach.getTenSach(),
                        sach.getLoaiSach().getTenLoai(),
                        sach.getSoLuong(),
                        df.format(sach.getGiaGoc()) + " VND"});
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } else if (tieuChi.equalsIgnoreCase("Loại sách")) {
            if (timKiem.equalsIgnoreCase("")) {
                getTableDataDefault();
            } else {
                try {
                    model.setRowCount(0);
                    ArrayList<Sach> dsLoaiSach = sach_dao.getDSSachTheoTenLoai(timKiem);

                    for (Sach x : dsLoaiSach) {
                        if (x.getTrangThai().equalsIgnoreCase("Đang bán")) {
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
        } else {
            if (timKiem.equalsIgnoreCase("")) {
                getTableDataDefault();
            } else {
                try {
                    model.setRowCount(0);
                    ArrayList<Sach> dsLoaiSach = sach_dao.getDSSachTheoTacGia(timKiem);
                    for (Sach x : dsLoaiSach) {
                        if (x.getTrangThai().equalsIgnoreCase("Đang bán")) {
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
        }
    }//GEN-LAST:event_jComboBox_TimKiemActionPerformed

    private void addButtonToTable(DefaultTableModel model) {
        TableActionEvent event;
        event = new TableActionEvent() {
            private Sach sach;

            @Override
            public void onEdit(int row) {
                try {
                    sach = getDataToBook();
                    Sach_SuaSach suaSach;
                    suaSach = new Sach_SuaSach(new javax.swing.JFrame(), true, Sach_QuanLySach.this, sach);
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
                    System.out.println(sach);
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

    private void getTableDataDefault() {
        model.setRowCount(0);
        try {
            for (Sach x : sach_dao.getDSSach()) {
                if (x.getTrangThai().equalsIgnoreCase("Đang bán")) {
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

    public void addDataToTable(Sach x) throws SQLException {
        
        model.insertRow(0, new Object[]{x.getISBN(), x.getTenSach(), x.getLoaiSach().getTenLoai(), x.getSoLuong(), df.format(x.getGiaGoc()) + " VND"});
        jComboBox_TimKiem.addItem(x.getISBN());
        //
    }

    public void editDataToTable(Sach x) throws SQLException {
            int n = jTable_Sach.getSelectedRow();
            model.setValueAt(x.getISBN(), n, 0);
            model.setValueAt(x.getTenSach(), n, 1);
            model.setValueAt(x.getLoaiSach().getTenLoai(), n, 2);
            model.setValueAt(x.getSoLuong(), n, 3);
            model.setValueAt(df.format(x.getGiaGoc()) + " VND", n, 4);

    }

    public Sach getDataToBook() throws SQLException {
        int n = jTable_Sach.getSelectedRow();
        String maSach = model.getValueAt(n, 0).toString();
        return sach_dao.getSachTheoMaSach(maSach);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_LamMoi;
    private javax.swing.JButton jButton_ThemSach;
    private javax.swing.JButton jButton_XoaNhieu;
    private javax.swing.JComboBox<String> jComboBox_TieuChi;
    private javax.swing.JComboBox<String> jComboBox_TimKiem;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Sach;
    private javax.swing.JTextField jTextField_TimKiem;
    // End of variables declaration//GEN-END:variables
}

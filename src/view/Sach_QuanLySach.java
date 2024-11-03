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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
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

/**
 *
 * @author Huu Thai
 */
public class Sach_QuanLySach extends javax.swing.JInternalFrame {

    private final Sach_DAO sach_dao;
    private DefaultTableModel model = null;
    private final DecimalFormat df = new DecimalFormat("#");
    private ChiTietKhoHang_DAO chiTietKhoHangDao;
    private String tieuChi;
    private boolean isTimKiemUpdated;
    private Color customGreen;

    /**
     * Creates new form TrangQLSach_GUI
     */
    public Sach_QuanLySach() throws SQLException {
        sach_dao = new Sach_DAO();
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

        chiTietKhoHangDao = new ChiTietKhoHang_DAO();
        JTableHeader header = jTable_Sach.getTableHeader();
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

        jTextField_TimKiem = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton_ThemSach = new javax.swing.JButton();
        jButton_LamMoi = new javax.swing.JButton();
        jButton_XoaNhieu = new javax.swing.JButton();
        jComboBox_TieuChi = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Sach = new javax.swing.JTable();
        jButton_XuatExcel = new javax.swing.JButton();
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
                    if (s.getSoLuong() <= 0) {
                        try {
                            if (sach_dao.xoaSach(ma)) {
                                model.removeRow(n[i]);
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Không thể xóa sách có số lượng lớn hơn 0");
                    }
                }
            }else{
                jTable_Sach.clearSelection();
            }

        }

    }//GEN-LAST:event_jButton_XoaNhieuActionPerformed

    private void jButton_XuatExcelActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        try {
            JFileChooser jFileChooser = new JFileChooser();
            int returnValue = jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            // Kiểm tra xem người dùng có chọn tệp không
            if (returnValue == JFileChooser.APPROVE_OPTION && saveFile != null) {
                // Kiểm tra nếu tên tệp không kết thúc bằng ".xlsx", thêm vào
                if (!saveFile.getName().toLowerCase().endsWith(".xlsx")) {
                    saveFile = new File(saveFile.getAbsolutePath() + ".xlsx");
                }

                Workbook wb = new XSSFWorkbook();  // Tạo một workbook Excel mới
                Sheet sheet = wb.createSheet("DanhSachSach");  // Tạo một sheet có tên "DanhSachSach"

                // Tạo hàng tiêu đề
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("ISBN");
                headerRow.createCell(1).setCellValue("Tên sách");
                headerRow.createCell(2).setCellValue("Loại sách");
                headerRow.createCell(3).setCellValue("Tác giả");
                headerRow.createCell(4).setCellValue("Năm xuất bản");
                headerRow.createCell(5).setCellValue("Nhà xuất bản");
                headerRow.createCell(6).setCellValue("Số lượng");
                headerRow.createCell(7).setCellValue("Giá gốc");

                // Thiết lập độ rộng cho các cột
                sheet.setColumnWidth(0, 20 * 256); // Độ rộng cho cột ISBN
                sheet.setColumnWidth(1, 40 * 256); // Độ rộng cho cột Tên sách
                sheet.setColumnWidth(2, 20 * 256); // Độ rộng cho cột Loại sách
                sheet.setColumnWidth(3, 20 * 256); // Độ rộng cho cột Tác giả
                sheet.setColumnWidth(4, 15 * 256); // Độ rộng cho cột Năm xuất bản
                sheet.setColumnWidth(5, 25 * 256); // Độ rộng cho cột Nhà xuất bản
                sheet.setColumnWidth(6, 15 * 256); // Độ rộng cho cột Số lượng
                sheet.setColumnWidth(7, 15 * 256); // Độ rộng cho cột Giá gốc

                // Lấy danh sách sách
                List<Sach> dsSach = new ArrayList<>();
                int[] n = jTable_Sach.getSelectedRows();
                if (n.length == 0) {
                    // Lấy toàn bộ danh sách sách
                    dsSach = sach_dao.getDSSach();
                } else {
                    for (int j : n) {
                        String ma = model.getValueAt(j, 0).toString();
                        Sach s = null;
                        try {
                            s = sach_dao.getSachTheoMaSach(ma);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        dsSach.add(s);
                    }
                }

                // Xuất tất cả thông tin chi tiết của sách
                int rowIndex = 1;  // Bắt đầu từ hàng thứ 2 (hàng 1 là tiêu đề)
                for (Sach sach : dsSach) {
                    // Chỉ xuất sách đang bán
                    if (sach.getTrangThai().equalsIgnoreCase("Đang bán")) {
                        Row row = sheet.createRow(rowIndex++);
                        row.createCell(0).setCellValue(sach.getISBN());
                        row.createCell(1).setCellValue(sach.getTenSach());
                        row.createCell(2).setCellValue(sach.getLoaiSach().getTenLoai());
                        row.createCell(3).setCellValue(sach.getTacGia());
                        row.createCell(4).setCellValue(sach.getNamXB());
                        row.createCell(5).setCellValue(sach.getNhaXB());
                        row.createCell(6).setCellValue(sach.getSoLuong());
                        row.createCell(7).setCellValue(sach.getGiaGoc());
                    }
                }

                // Ghi dữ liệu vào tệp
                try (FileOutputStream out = new FileOutputStream(saveFile)) {
                    wb.write(out);  // Ghi nội dung workbook vào tệp
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Lỗi khi ghi file: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                } finally {
                    wb.close();  // Đóng workbook
                }

                JOptionPane.showMessageDialog(this, "Xuất file Excel thành công!");
                Desktop.getDesktop().open(saveFile);
            } else {
                System.out.println("Người dùng đã hủy chọn tệp lưu.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Lỗi IO: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

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
                    ChinhSuaThongTinSach suaSach;
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
    }

    public void editDataToTable(Sach x) throws SQLException {
        if (sach_dao.capNhatSach(x)) {
            int n = jTable_Sach.getSelectedRow();
            model.setValueAt(x.getISBN(), n, 0);
            model.setValueAt(x.getTenSach(), n, 1);
            model.setValueAt(x.getLoaiSach().getTenLoai(), n, 2);
            model.setValueAt(x.getSoLuong(), n, 3);
            model.setValueAt(df.format(x.getGiaGoc()) + " VND", n, 4);

        }
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
    private javax.swing.JButton jButton_XuatExcel;
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

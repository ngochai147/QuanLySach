/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import dao.ExportExcel_DAO;
import dao.ThongKeTonKho_DAO;
import entity.ThongKeTonKho_model;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import panel.BarChart_ThongKeSachTrongKho;
import panel.BarChart_ThongKeTonKho;

/**
 *
 * @author phamd
 */
public class NguoiQuanLy_ThongKeTonKho extends javax.swing.JInternalFrame {
    //

    /**
     * Creates new form NguoiQuanLy_ThongKeTonKho
     */
    final ThongKeTonKho_DAO thongKeTonKho_Dao = new ThongKeTonKho_DAO();
    final ExportExcel_DAO excel_DAO = new ExportExcel_DAO();
    private Color customGreen;
    

    public NguoiQuanLy_ThongKeTonKho() {
        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        loadDataTable();
        loadComboboxTenKho(jComboBox_TenKho);
        chinhSua_kichThuoc_MauSac();
    }

    private void loadDataIntoTable(JTable table, String tenKho) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        List<ThongKeTonKho_model> listSachTonKho = thongKeTonKho_Dao.getThongKeTonKho(tenKho);

        // Sử dụng Map để gộp sách theo ISBN
        Map<String, ThongKeTonKho_model> sachTonKho = new HashMap<>();

        for (ThongKeTonKho_model thongKe : listSachTonKho) {
            String isbn = thongKe.getISBN();
            if (sachTonKho.containsKey(isbn)) {
                // Nếu đã tồn tại ISBN, cộng số lượng tồn kho
                ThongKeTonKho_model existingThongKe = sachTonKho.get(isbn);
                existingThongKe.setSoLuongTonKho(existingThongKe.getSoLuongTonKho() + thongKe.getSoLuongTonKho());
            } else {
                // Nếu chưa tồn tại ISBN, thêm vào map
                sachTonKho.put(isbn, thongKe);
            }
        }

        // Chuyển các giá trị từ Map thành List để sắp xếp
        List<ThongKeTonKho_model> dsSach_giamDan = new ArrayList<>(sachTonKho.values());

        // Sắp xếp số lượng tồn kho giảm dần
        dsSach_giamDan.sort((o1, o2) -> Integer.compare(o2.getSoLuongTonKho(), o1.getSoLuongTonKho()));

        for (ThongKeTonKho_model thongKe :  dsSach_giamDan) {
            if (thongKe.getSoLuongTonKho() > 0) {
                model.addRow(new Object[]{
                        thongKe.getISBN(),
                        thongKe.getTenSach(),
                        thongKe.getTacGia(),
                        thongKe.getSoLuongTonKho(),
                        thongKe.getTrangThai()
                });
            }
        }
    }

    private void chinhSua_kichThuoc_MauSac() {
        jComboBox_TenKho.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // Loại bỏ khoảng cách thừa
                label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                return label;
            }
        });

        customGreen = new Color(102,102,0);
        // Chỉnh màu xanh cho combobox
        jComboBox_TenKho.setRenderer(new DefaultListCellRenderer() {
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
    }

    private void loadDataTable() {
        String selected_TenKho = jComboBox_TenKho.getSelectedItem() != null ? jComboBox_TenKho.getSelectedItem().toString() : "Tất cả";
        loadDataIntoTable(table_ThongKeTonKho, selected_TenKho);
    }

    private void loadComboboxTenKho(JComboBox<String> combobox) {
        List<String> tenKhoList = thongKeTonKho_Dao.getListTenKho();
        for (String tenKho : tenKhoList) {
            combobox.addItem(tenKho);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        table_ThongKeTonKho = new javax.swing.JTable();
        jComboBox_TenKho = new javax.swing.JComboBox<>();
        jButton_XuatExcelTonKho = new javax.swing.JButton();
        jButton_BieuDo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(167, 129, 17));
        setPreferredSize(new java.awt.Dimension(1479, 677));
        getContentPane().setLayout(null);

        table_ThongKeTonKho.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        table_ThongKeTonKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISBN", "Tên sách", "Tác giả", "Số lượng tồn kho", "Trạng thái"
            }
        ));
        table_ThongKeTonKho.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table_ThongKeTonKho.setFocusable(false);
        table_ThongKeTonKho.setMinimumSize(new java.awt.Dimension(20, 0));
        table_ThongKeTonKho.setRowHeight(40);
        table_ThongKeTonKho.setSelectionBackground(new java.awt.Color(153, 204, 0));
        table_ThongKeTonKho.setSelectionForeground(new java.awt.Color(51, 51, 51));
        table_ThongKeTonKho.setShowGrid(true);
        table_ThongKeTonKho.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table_ThongKeTonKho);
        JTableHeader tableHeader = table_ThongKeTonKho.getTableHeader();
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) tableHeader.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        headerRenderer.setFont(new Font("Arial", Font.BOLD, 20));

        TableColumnModel columnModel = table_ThongKeTonKho.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(25);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(75);
        columnModel.getColumn(3).setPreferredWidth(75);
        columnModel.getColumn(4).setPreferredWidth(150);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 120, 1467, 402);

        jComboBox_TenKho.setBackground(new java.awt.Color(102, 102, 0));
        jComboBox_TenKho.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jComboBox_TenKho.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox_TenKho.setMaximumRowCount(10);
        jComboBox_TenKho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        jComboBox_TenKho.setBorder(null);
        jComboBox_TenKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_TenKhoActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox_TenKho);
        jComboBox_TenKho.setBounds(1080, 60, 349, 40);

        jButton_XuatExcelTonKho.setBackground(new java.awt.Color(102, 102, 0));
        jButton_XuatExcelTonKho.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_XuatExcelTonKho.setForeground(new java.awt.Color(255, 255, 255));
        jButton_XuatExcelTonKho.setText("Xuất Excel");
        jButton_XuatExcelTonKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_XuatExcelTonKhoActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_XuatExcelTonKho);
        jButton_XuatExcelTonKho.setBounds(1280, 580, 150, 33);

        jButton_BieuDo.setBackground(new java.awt.Color(102, 102, 0));
        jButton_BieuDo.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_BieuDo.setForeground(new java.awt.Color(255, 255, 255));
        jButton_BieuDo.setText("Xem biểu đồ");
        jButton_BieuDo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton_BieuDoMousePressed(evt);
            }
        });
        getContentPane().add(jButton_BieuDo);
        jButton_BieuDo.setBounds(1040, 580, 210, 33);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/anhnen.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1610, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_TenKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_TenKhoActionPerformed
        loadDataTable();
    }//GEN-LAST:event_jComboBox_TenKhoActionPerformed

    private void jButton_XuatExcelTonKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_XuatExcelTonKhoActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Hiển thị hộp thoại để người dùng chọn vị trí lưu file
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Lấy đường dẫn file
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            // Đảm bảo đuôi file là .xlsx
            if (!filePath.endsWith(".xlsx")) {
                filePath += ".xlsx";
            }

            // Gọi phương thức DAO để xuất dữ liệu tồn kho ra Excel
            try {
                excel_DAO.exportThongKeTonKhoToExcel(filePath);
                JOptionPane.showMessageDialog(this, "Xuất file Excel thành công!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Xuất file Excel thất bại: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton_XuatExcelTonKhoActionPerformed

    private void jButton_BieuDoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_BieuDoMousePressed
        String selectedTenKho = (String) jComboBox_TenKho.getSelectedItem();
        BarChart_ThongKeTonKho barChart_ThongKeTonKho = new BarChart_ThongKeTonKho();
        BarChart_ThongKeSachTrongKho barChart_ThongKeSachTrongKho = new BarChart_ThongKeSachTrongKho();
        if("Tất cả".equals(selectedTenKho)){
            barChart_ThongKeTonKho.setVisible(true);
        } else {
            barChart_ThongKeSachTrongKho.setDataIntoBarChart(selectedTenKho);
            barChart_ThongKeSachTrongKho.setVisible(true);
        }
        
    }//GEN-LAST:event_jButton_BieuDoMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_BieuDo;
    private javax.swing.JButton jButton_XuatExcelTonKho;
    private javax.swing.JComboBox<String> jComboBox_TenKho;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_ThongKeTonKho;
    // End of variables declaration//GEN-END:variables
}

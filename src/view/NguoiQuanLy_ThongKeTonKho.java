/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import dao.ExportExcel_DAO;
import dao.ThongKeTonKho_DAO;
import entity.ThongKeTonKho_model;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import panel.BarChart_ThongKeTonKho;

/**
 *
 * @author phamd
 */
public class NguoiQuanLy_ThongKeTonKho extends javax.swing.JInternalFrame {

    /**
     * Creates new form NguoiQuanLy_ThongKeTonKho
     */
    final ThongKeTonKho_DAO thongKeTonKho_Dao = new ThongKeTonKho_DAO();
    final ExportExcel_DAO excel_DAO = new ExportExcel_DAO();
    

    public NguoiQuanLy_ThongKeTonKho() {
        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);

        loadDataTable();
        loadComboboxTenKho(jComboBox_TenKho);

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
        table_ThongKeTonKho = new JTable();
        jComboBox_TenKho = new JComboBox<>();
        jButton_XuatExcelTonKho = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(167, 129, 17));
        setPreferredSize(new java.awt.Dimension(1479, 677));

        table_ThongKeTonKho.setFont(new Font("Arial", 0, 18)); // NOI18N
        table_ThongKeTonKho.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISBN", "Tên sách", "Tác giả", "Số lượng tồn kho", "Trạng thái"
            }
        ));
        table_ThongKeTonKho.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table_ThongKeTonKho.setMinimumSize(new java.awt.Dimension(20, 0));
        table_ThongKeTonKho.setRowHeight(40);
        table_ThongKeTonKho.setSelectionBackground(new java.awt.Color(153, 204, 0));
        table_ThongKeTonKho.setSelectionForeground(new java.awt.Color(51, 51, 51));
        table_ThongKeTonKho.setShowGrid(true);
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

        jComboBox_TenKho.setBackground(new java.awt.Color(102, 102, 0));
        jComboBox_TenKho.setFont(new Font("Arial", 1, 20)); // NOI18N
        jComboBox_TenKho.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox_TenKho.setMaximumRowCount(10);
        jComboBox_TenKho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        jComboBox_TenKho.setBorder(null);
        jComboBox_TenKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_TenKhoActionPerformed(evt);
            }
        });

        jButton_XuatExcelTonKho.setBackground(new java.awt.Color(102, 102, 0));
        jButton_XuatExcelTonKho.setFont(new Font("Arial", 1, 20)); // NOI18N
        jButton_XuatExcelTonKho.setForeground(new java.awt.Color(255, 255, 255));
        jButton_XuatExcelTonKho.setText("Xuất Excel");
        jButton_XuatExcelTonKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_XuatExcelTonKhoActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(102, 102, 0));
        jButton1.setFont(new Font("Arial", 1, 20)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Xem biểu đồ");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1080, 1080, 1080)
                .addComponent(jComboBox_TenKho, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton_XuatExcelTonKho, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jComboBox_TenKho, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_XuatExcelTonKho)
                    .addComponent(jButton1))
                .addGap(75, 75, 75))
        );

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

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        BarChart_ThongKeTonKho barChart_ThongKeTonKho = new BarChart_ThongKeTonKho();
        barChart_ThongKeTonKho.setVisible(true);
    }//GEN-LAST:event_jButton1MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_XuatExcelTonKho;
    private JComboBox<String> jComboBox_TenKho;
    private javax.swing.JScrollPane jScrollPane1;
    private JTable table_ThongKeTonKho;
    // End of variables declaration//GEN-END:variables
}

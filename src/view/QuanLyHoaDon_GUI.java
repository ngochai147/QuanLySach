/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import button.TableActionCellEditor;
//import button.TableActionCellEditor_1;
import button.TableActionEvent;
import button.TableActionRender;
//import dao.ChiTietHoaDon_DAO;
//import dao.HoaDon_DAO;
//import dao.Sach_DAO;
import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.Sach_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.Sach;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import entity.HoaDon;
//import entity.Sach;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;
//import java.text.DecimalFormat;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
import javax.swing.*;
//import javax.swing.JFrame;
//import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author PC
 */
public class QuanLyHoaDon_GUI extends javax.swing.JInternalFrame {

    /**
     * Creates new form QuanLyHoaDon_GUI
     */
//    private JDesktopPane desktopPane;

    public QuanLyHoaDon_GUI() {
        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        JTableHeader header = jTable_HoaDon.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 18));
        TableActionEvent event;
        event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                 JOptionPane.showMessageDialog(null, "Simple Information Message");
            }

            @Override
            public void onDelete(int row) {
                DefaultTableModel model = (DefaultTableModel) jTable_HoaDon.getModel();
                model.removeRow(row);
            }

            @Override
            public void onView(int row) {
                ReportViewer phieuHoaDon=new ReportViewer();
                int n= jTable_HoaDon.getSelectedRow();
                String maHoaDon=dsHD.getAllHoaDon().get(n).getMaHoaDon();
                ArrayList<ChiTietHoaDon> dsCTHDtemp=new ArrayList<>();
                for(ChiTietHoaDon cthd: listCTHD){
                    if(cthd.getHoaDon().getMaHoaDon().equalsIgnoreCase(maHoaDon)){
                        dsCTHDtemp.add(cthd);
                    }
                }
                phieuHoaDon.viewHoaDon(dsCTHDtemp,maHoaDon);
            }
        };
        jTable_HoaDon.getColumnModel().getColumn(7).setCellRenderer(new TableActionRender(1));
        jTable_HoaDon.getColumnModel().getColumn(7).setCellEditor(new TableActionCellEditor(event, 1));
        DefaultTableModel model = (DefaultTableModel)jTable_HoaDon.getModel();
        TableColumn selectColumn = jTable_HoaDon.getColumnModel().getColumn(0);
        selectColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()));
        selectColumn.setCellRenderer(jTable_HoaDon.getDefaultRenderer(Boolean.class));



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
        jButton_TaoHoaDon = new javax.swing.JButton();
        jButton_LamMoi = new javax.swing.JButton();
        jButton_XoaNhieu = new javax.swing.JButton();
        jComboBox_TieuChi = new javax.swing.JComboBox<>();
        jTextField_TimKiem = new javax.swing.JTextField();
        jButton_TimKiem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_HoaDon = new javax.swing.JTable();
        jButton_XuatExcel = new javax.swing.JButton();
        jLabel_QuanLyHoaDon = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jButton_TaoHoaDon.setBackground(new java.awt.Color(102, 102, 0));
        jButton_TaoHoaDon.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_TaoHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        jButton_TaoHoaDon.setText("Tạo hóa đơn");
        jButton_TaoHoaDon.setPreferredSize(new java.awt.Dimension(200, 60));
        jButton_TaoHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_TaoHoaDonMouseClicked(evt);
            }
        });
        jButton_TaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_TaoHoaDonActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_TaoHoaDon);
        jButton_TaoHoaDon.setBounds(26, 130, 180, 46);

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
        jButton_LamMoi.setBounds(220, 130, 148, 46);

        jButton_XoaNhieu.setBackground(new java.awt.Color(153, 0, 51));
        jButton_XoaNhieu.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_XoaNhieu.setForeground(new java.awt.Color(255, 255, 255));
        jButton_XoaNhieu.setText("Xóa ");
        jButton_XoaNhieu.setPreferredSize(new java.awt.Dimension(200, 60));
        jButton_XoaNhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_XoaNhieuActionPerformed(evt);
            }
        });
//        jPanel1.add(jButton_XoaNhieu);
        jButton_XoaNhieu.setBounds(380, 130, 148, 46);

        jComboBox_TieuChi.setBackground(new java.awt.Color(102, 102, 0));
        jComboBox_TieuChi.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jComboBox_TieuChi.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox_TieuChi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiêu chí", "Mã hóa đơn", "Mã nhân viên", "Ngày tạo đơn" }));
        jPanel1.add(jComboBox_TieuChi);
        jComboBox_TieuChi.setBounds(920, 120, 170, 46);
        jComboBox_TieuChi.getAccessibleContext().setAccessibleName("");

        jTextField_TimKiem.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jPanel1.add(jTextField_TimKiem);
        jTextField_TimKiem.setBounds(1100, 120, 260, 46);

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
        jButton_TimKiem.setBounds(1370, 120, 136, 46);

        jTable_HoaDon.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jTable_HoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

                },
                new String [] {
                       "", "STT", "Mã hóa đơn", "Ngày tạo đơn", "Mã nhân viên", "Tổng số lượng", "Tổng tiền", ""
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    true,false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            },
            new String [] {
                "STT", "Mã hóa đơn", "Ngày tạo đơn", "Mã nhân viên", "Tổng số lượng sách", "Tổng tiền(VNĐ)", ""
            }
        ));
        jTable_HoaDon.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTable_HoaDon.setPreferredSize(new java.awt.Dimension(525, 520));
        jTable_HoaDon.setRowHeight(40);
        jTable_HoaDon.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jTable_HoaDon.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable_HoaDon.setShowGrid(true);
        jScrollPane1.setViewportView(jTable_HoaDon);
        int stt = 0;
        for (HoaDon x : dsHD.getAllHoaDon()) {
            stt++;
            double tongTien = 0;
            int tongSL = 0;
            for (ChiTietHoaDon cthd : listCTHD) {
                if (x.getMaHoaDon().equalsIgnoreCase(cthd.getHoaDon().getMaHoaDon())) {

                    tongSL += cthd.getSoLuong();
                    tongTien += cthd.getSoLuong() * cthd.getDonGia();
                }
            }
            DecimalFormat df = new DecimalFormat("#,###");

            // Giả sử đây là số bạn muốn định dạng
            String formattedTongTien = df.format(tongTien);
            ((DefaultTableModel) jTable_HoaDon.getModel()).addRow(new Object[]{null,stt, x.getMaHoaDon(), x.getNgayTaoDon(), x.getNhanVien().getMaNV(), tongSL, formattedTongTien});
        }
        if (jTable_HoaDon.getColumnModel().getColumnCount() >= 0) {

            jTable_HoaDon.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTable_HoaDon.getColumnModel().getColumn(2).setPreferredWidth(150);
            jTable_HoaDon.getColumnModel().getColumn(3).setPreferredWidth(150);
            jTable_HoaDon.getColumnModel().getColumn(7).setPreferredWidth(150);
        if (jTable_HoaDon.getColumnModel().getColumnCount() > 0) {
            jTable_HoaDon.getColumnModel().getColumn(6).setPreferredWidth(150);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(28, 190, 1480, 410);

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
        jButton_XuatExcel.setBounds(40, 640, 148, 46);

        jLabel_QuanLyHoaDon.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel_QuanLyHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/jLabel_QuanLyHoaDon.png"))); // NOI18N
        jPanel1.add(jLabel_QuanLyHoaDon);
        jLabel_QuanLyHoaDon.setBounds(420, 10, 1660, 110);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/anhnen.jpg"))); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(1520, 716));
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 0, 1710, 760);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1613, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 874, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LamMoiActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable_HoaDon.getModel();
        model.setRowCount(0);
        int stt = 0;
        for (HoaDon x : dsHD.getAllHoaDon()) {
            stt++;
            double tongTien = 0;
            int tongSL = 0;
            for (ChiTietHoaDon cthd : listCTHD) {
                if (x.getMaHoaDon().equalsIgnoreCase(cthd.getHoaDon().getMaHoaDon())) {

                    tongSL += cthd.getSoLuong();
                    tongTien += cthd.getSoLuong() * cthd.getDonGia();
                }
            }
            DecimalFormat df = new DecimalFormat("#,###");

            // Giả sử đây là số bạn muốn định dạng
            String formattedTongTien = df.format(tongTien);
            ((DefaultTableModel) jTable_HoaDon.getModel()).addRow(new Object[]{null,stt, x.getMaHoaDon(), x.getNgayTaoDon(), x.getNhanVien().getMaNV(), tongSL, formattedTongTien});
        }

    }//GEN-LAST:event_jButton_LamMoiActionPerformed

    private void jButton_XoaNhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_XoaNhieuActionPerformed
        // TODO add your handling code here:
        deleteSelectedRows();
    }//GEN-LAST:event_jButton_XoaNhieuActionPerformed

    private void jButton_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_TimKiemActionPerformed
        // TODO add your handling code here:
        int n=jComboBox_TieuChi.getSelectedIndex();
        String textTim=jTextField_TimKiem.getText();
        int stt = 0;
        if(textTim.trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Tìm kiếm không được rỗng", "Lỗi", JOptionPane.WARNING_MESSAGE);
            jTextField_TimKiem.requestFocus();
        }else{
            if(n==1){
                danhSachTimKiem(stt, textTim,n);
            }else if(n==2){
                danhSachTimKiem(stt, textTim,n);
            }else if(n==3){
                danhSachTimKiem(stt, textTim,n);
            }else{
                JOptionPane.showMessageDialog(this, "Vui lòng chọn tiêu chí tìm kiếm", "Lỗi", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton_TimKiemActionPerformed
    public void danhSachTimKiem(int stt, String textTim,int n){
        DefaultTableModel model = (DefaultTableModel) jTable_HoaDon.getModel();
        model.setRowCount(0);

        // Ép chuỗi thành LocalDate

                for(HoaDon hd: dsHD.getAllHoaDon()){
                    if(textTim.equalsIgnoreCase(hd.getMaHoaDon()) && n==1){
                        stt++;
                        double tongTien = 0;
                        int tongSL = 0;
                        for (ChiTietHoaDon cthd : listCTHD) {
                            if (hd.getMaHoaDon().equalsIgnoreCase(cthd.getHoaDon().getMaHoaDon())) {

                                tongSL += cthd.getSoLuong();
                                tongTien += cthd.getSoLuong() * cthd.getDonGia();
                            }
                        }
                        DecimalFormat df = new DecimalFormat("#,###");
                        // Giả sử đây là số bạn muốn định dạng
                        String formattedTongTien = df.format(tongTien);
                        ((DefaultTableModel) jTable_HoaDon.getModel()).addRow(new Object[]{null,stt, hd.getMaHoaDon(), hd.getNgayTaoDon(), hd.getNhanVien().getMaNV(), tongSL, formattedTongTien});
                    }else if(textTim.equalsIgnoreCase(hd.getNhanVien().getMaNV()) && n==2){
                         stt++;
                        double tongTien = 0;
                        int tongSL = 0;
                        for (ChiTietHoaDon cthd : listCTHD) {
                            if (hd.getMaHoaDon().equalsIgnoreCase(cthd.getHoaDon().getMaHoaDon())) {

                                tongSL += cthd.getSoLuong();
                                tongTien += cthd.getSoLuong() * cthd.getDonGia();
                            }
                        }
                        DecimalFormat df = new DecimalFormat("#,###");
                        // Giả sử đây là số bạn muốn định dạng
                        String formattedTongTien = df.format(tongTien);
                        ((DefaultTableModel) jTable_HoaDon.getModel()).addRow(new Object[]{null,stt, hd.getMaHoaDon(), hd.getNgayTaoDon(), hd.getNhanVien().getMaNV(), tongSL, formattedTongTien});
                    }else if(n==3){
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate date = LocalDate.parse(textTim, formatter);
                        if( date.isEqual(hd.getNgayTaoDon())){
                         stt++;

                        double tongTien = 0;
                        int tongSL = 0;
                        for (ChiTietHoaDon cthd : listCTHD) {
                            if (hd.getMaHoaDon().equalsIgnoreCase(cthd.getHoaDon().getMaHoaDon())) {

                                tongSL += cthd.getSoLuong();
                                tongTien += cthd.getSoLuong() * cthd.getDonGia();
                            }
                        }
                        DecimalFormat df = new DecimalFormat("#,### VND");
                        // Giả sử đây là số bạn muốn định dạng
                        String formattedTongTien = df.format(tongTien);
                        ((DefaultTableModel) jTable_HoaDon.getModel()).addRow(new Object[]{null,stt, hd.getMaHoaDon(), hd.getNgayTaoDon(), hd.getNhanVien().getMaNV(), tongSL, formattedTongTien});
                    }
                    }
                }
    }
    private void deleteSelectedRows() {
        DefaultTableModel model = (DefaultTableModel) jTable_HoaDon.getModel();
        // Tạo danh sách các hàng được chọn
        List<Integer> rowsToDelete = new ArrayList<>();

        // Duyệt qua tất cả các hàng và kiểm tra checkbox
        for (int i = 0; i < model.getRowCount(); i++) {
            Boolean isSelected = (Boolean) model.getValueAt(i, 7); // Giả sử cột checkbox là cột thứ 7 (index 6)
            if (isSelected != null && isSelected) {
                rowsToDelete.add(i);
            }
        }

        // Xóa từ hàng cuối cùng đến hàng đầu tiên để tránh lỗi chỉ số
        for (int i = rowsToDelete.size() - 1; i >= 0; i--) {
            model.removeRow(rowsToDelete.get(i));
        }
    }
    private void jButton_XuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_XuatExcelActionPerformed
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            if (saveFile != null) {
                // Check if the file name ends with ".xlsx", otherwise add it
                if (!saveFile.getName().toLowerCase().endsWith(".xlsx")) {
                    saveFile = new File(saveFile + ".xlsx");
                }

                Workbook wb = new XSSFWorkbook();  // Create a new Excel workbook
                Sheet sheet = wb.createSheet("HoaDon");  // Create a new sheet named "HoaDon"

                // Create the header row, excluding the first (checkbox) and last columns
                Row headerRow = sheet.createRow(0);
                for (int i = 2; i < jTable_HoaDon.getColumnCount(); i++) {  // Exclude first and last columns
                    Cell cell = headerRow.createCell(i - 2);  // Shift by -2 to start at cell 0
                    cell.setCellValue(jTable_HoaDon.getColumnName(i));  // Set column names in the first row
                }

                DefaultTableModel model = (DefaultTableModel) jTable_HoaDon.getModel();
                List<Integer> rowsToExport = new ArrayList<>();

                // Collect selected rows where the checkbox is checked
                for (int i = 0; i < model.getRowCount(); i++) {
                    Boolean isSelected = (Boolean) model.getValueAt(i, 0); // Assuming the checkbox column is the first one
                    if (isSelected != null && isSelected) {
                        rowsToExport.add(i);
                    }
                }

                // If any rows are selected, export only those
                if (rowsToExport.size() > 0) {
                    for (int rowIndex = 0; rowIndex < rowsToExport.size(); rowIndex++) {
                        int selectedRow = rowsToExport.get(rowIndex);
                        Row row = sheet.createRow(rowIndex + 1);  // Create a new row for each selected JTable row

                        // Exclude first and last columns when exporting
                        for (int colIndex = 2; colIndex < jTable_HoaDon.getColumnCount(); colIndex++) {
                            Cell cell = row.createCell(colIndex - 2);
                            Object value = jTable_HoaDon.getValueAt(selectedRow, colIndex);  // Get value from JTable cell
                            if (value != null) {
                                cell.setCellValue(value.toString());  // Set the cell value in Excel
                            }
                        }
                    }
                } else {  // If no rows are selected, export all rows
                    for (int i = 0; i < jTable_HoaDon.getRowCount(); i++) {
                        Row row = sheet.createRow(i + 1);  // Create a new row for each JTable row

                        // Exclude first and last columns
                        for (int j = 2; j < jTable_HoaDon.getColumnCount(); j++) {
                            Cell cell = row.createCell(j - 2);
                            Object value = jTable_HoaDon.getValueAt(i, j);  // Get value from JTable cell
                            if (value != null) {
                                cell.setCellValue(value.toString());  // Set the cell value in Excel
                            }
                        }
                    }
                }

                // Write the data to the file
                try (FileOutputStream out = new FileOutputStream(saveFile)) {
                    wb.write(out);  // Write the workbook content to the file
                }
                wb.close();  // Close the workbook

                JOptionPane.showMessageDialog(this, "Xuất file Excel thành công!");
            } else {
                System.out.println("Save file selection was canceled.");
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "File không tìm thấy: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(this, "Lỗi IO: " + ioe.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton_XuatExcelActionPerformed
//GEN-LAST:event_jButton_XuatExcelActionPerformed

    private void jButton_TaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_TaoHoaDonActionPerformed
        // TODO add your handling code here:
//        jPanel1.removeAll();
//        TrangTaoHoaDon_GUI Thd = new TrangTaoHoaDon_GUI();
//        Thd.setSize(jPanel1.getSize());
//        Thd.setVisible(true);
//        jPanel1.add(Thd);

    }//GEN-LAST:event_jButton_TaoHoaDonActionPerformed

    private void jButton_TaoHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_TaoHoaDonMouseClicked
        // TODO add your handling code here:
        jPanel1.removeAll();
        TrangTaoHoaDon_GUI trangNhapKho = new TrangTaoHoaDon_GUI();
        trangNhapKho.setSize(jPanel1.getSize());
        trangNhapKho.setVisible(true);
        jPanel1.add(trangNhapKho);

    }//GEN-LAST:event_jButton_TaoHoaDonMouseClicked
//    private HoaDon_DAO dsHD = new HoaDon_DAO();
//    private List<HoaDon> listhD = dsHD.getAllHoaDon();
//    private Sach_DAO dss = new Sach_DAO();
//    private List<Sach> sachList = dss.getAllSP();
//
//    private ChiTietHoaDon_DAO dsCTHD = new ChiTietHoaDon_DAO();
//    private List<ChiTietHoaDon> listCTHD = dsCTHD.getAllChiTietHoaDon();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_LamMoi;
    private javax.swing.JButton jButton_TaoHoaDon;
    private javax.swing.JButton jButton_TimKiem;
    private javax.swing.JButton jButton_XoaNhieu;
    private javax.swing.JButton jButton_XuatExcel;
    private javax.swing.JComboBox<String> jComboBox_TieuChi;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_QuanLyHoaDon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_HoaDon;
    private javax.swing.JTextField jTextField_TimKiem;
    // End of variables declaration//GEN-END:variables
}

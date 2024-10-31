/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import chart.ModelChart;
import dao.ThongKe_Dao;
import entity.ThongKe_model;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author phamd
 */
public class NguoiQuanLy_ThongKeDoanhThu1 extends javax.swing.JInternalFrame {

    private final Color clickColor;
    private final Color defaultColor;
    private boolean legendsAdded = false;
    private ThongKe_Dao thongKe_Dao = new ThongKe_Dao();

    /**
     * Creates new form NguoiQuanLy_ThongKeDoanhThu1
     */
    public NguoiQuanLy_ThongKeDoanhThu1() {
        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        
        clickColor = new Color(102,102,0);
        defaultColor = new Color(153,153,0);
        
        jPanelHoldChart_DoanhThu.setVisible(false);
    }

    public void setChart_DoanhThuTheoThang(Date fromDate, Date toDate){

        List<ThongKe_model> thongKe_thang = thongKe_Dao.getChartThongKeTheoThang(fromDate, toDate);
        for(int i = thongKe_thang.size()-1; i>=0; i--){
            ThongKe_model thongKe = thongKe_thang.get(i);
            chart_DoanhThu.addData(new ModelChart(thongKe.getMonth(), new double[]{thongKe.getDoanhThu(), thongKe.getLoiNhuan()}));
        }
        chart_DoanhThu.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel_Year = new javax.swing.JPanel();
        jLabel_Year = new javax.swing.JLabel();
        jPanel_Month = new javax.swing.JPanel();
        jLabel_Month = new javax.swing.JLabel();
        jPanel_Day = new javax.swing.JPanel();
        jLabel_Day = new javax.swing.JLabel();
        jButton_XuatExcel = new javax.swing.JButton();
        jButton_LamMoi = new javax.swing.JButton();
        jPanelHoldChart_DoanhThu = new javax.swing.JPanel();
        panelShadow1 = new panel.PanelShadow();
        chart_DoanhThu = new chart.CurveLineChart();
        jDateChooser_From = new com.toedter.calendar.JDateChooser();
        jDateChooser_To = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1479, 677));

        jPanel1.setPreferredSize(new java.awt.Dimension(1495, 436));

        jPanel_Year.setBackground(new java.awt.Color(153, 153, 0));
        jPanel_Year.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel_Year.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_YearMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel_YearMousePressed(evt);
            }
        });

        jLabel_Year.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_Year.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Year.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Year.setText("Thống kê theo năm");

        javax.swing.GroupLayout jPanel_YearLayout = new javax.swing.GroupLayout(jPanel_Year);
        jPanel_Year.setLayout(jPanel_YearLayout);
        jPanel_YearLayout.setHorizontalGroup(
            jPanel_YearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_YearLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_Year, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_YearLayout.setVerticalGroup(
            jPanel_YearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_YearLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_Year)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_Month.setBackground(new java.awt.Color(153, 153, 0));
        jPanel_Month.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel_Month.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_MonthMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel_MonthMousePressed(evt);
            }
        });

        jLabel_Month.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_Month.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Month.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Month.setText("Thống kê theo tháng");

        javax.swing.GroupLayout jPanel_MonthLayout = new javax.swing.GroupLayout(jPanel_Month);
        jPanel_Month.setLayout(jPanel_MonthLayout);
        jPanel_MonthLayout.setHorizontalGroup(
            jPanel_MonthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_MonthLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_Month, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_MonthLayout.setVerticalGroup(
            jPanel_MonthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_MonthLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_Month)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_Day.setBackground(new java.awt.Color(153, 153, 0));
        jPanel_Day.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel_Day.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_DayMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel_DayMousePressed(evt);
            }
        });

        jLabel_Day.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_Day.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Day.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Day.setText("Thống kê theo ngày");

        javax.swing.GroupLayout jPanel_DayLayout = new javax.swing.GroupLayout(jPanel_Day);
        jPanel_Day.setLayout(jPanel_DayLayout);
        jPanel_DayLayout.setHorizontalGroup(
            jPanel_DayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_Day, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_DayLayout.setVerticalGroup(
            jPanel_DayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_Day)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton_XuatExcel.setBackground(new java.awt.Color(102, 102, 0));
        jButton_XuatExcel.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_XuatExcel.setForeground(new java.awt.Color(255, 255, 255));
        jButton_XuatExcel.setText("Xuất Excel");
        jButton_XuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_XuatExcelActionPerformed(evt);
            }
        });

        jButton_LamMoi.setBackground(new java.awt.Color(255, 0, 0));
        jButton_LamMoi.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_LamMoi.setForeground(new java.awt.Color(255, 255, 255));
        jButton_LamMoi.setText("Làm mới");
        jButton_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LamMoiActionPerformed(evt);
            }
        });

        panelShadow1.setBackground(new java.awt.Color(62, 38, 103));
        panelShadow1.setColorGradient(new java.awt.Color(5, 5, 75));

        chart_DoanhThu.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        chart_DoanhThu.setForeground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addComponent(chart_DoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 1518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(chart_DoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanelHoldChart_DoanhThuLayout = new javax.swing.GroupLayout(jPanelHoldChart_DoanhThu);
        jPanelHoldChart_DoanhThu.setLayout(jPanelHoldChart_DoanhThuLayout);
        jPanelHoldChart_DoanhThuLayout.setHorizontalGroup(
            jPanelHoldChart_DoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHoldChart_DoanhThuLayout.createSequentialGroup()
                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelHoldChart_DoanhThuLayout.setVerticalGroup(
            jPanelHoldChart_DoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHoldChart_DoanhThuLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );

        jDateChooser_From.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Từ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N

        jDateChooser_To.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Đến", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N

        jButton1.setBackground(new java.awt.Color(102, 102, 0));
        jButton1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Thống kê");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelHoldChart_DoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel_Year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel_Month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel_Day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(jDateChooser_From, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser_To, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_XuatExcel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_LamMoi)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel_Day, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_Month, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_Year, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jDateChooser_To, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser_From, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_XuatExcel)
                            .addComponent(jButton_LamMoi)
                            .addComponent(jButton1))
                        .addGap(25, 25, 25)))
                .addComponent(jPanelHoldChart_DoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1544, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void jPanel_YearMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_YearMousePressed
        jPanel_Year.setBackground(clickColor);
        jPanel_Month.setBackground(defaultColor);
        jPanel_Day.setBackground(defaultColor);
    }//GEN-LAST:event_jPanel_YearMousePressed

    private void jPanel_MonthMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_MonthMousePressed
        jPanel_Year.setBackground(defaultColor);
        jPanel_Month.setBackground(clickColor);
        jPanel_Day.setBackground(defaultColor);
    }//GEN-LAST:event_jPanel_MonthMousePressed

    private void jPanel_DayMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_DayMousePressed
        jPanel_Year.setBackground(defaultColor);
        jPanel_Month.setBackground(defaultColor);
        jPanel_Day.setBackground(clickColor);
    }//GEN-LAST:event_jPanel_DayMousePressed

    private void jPanel_YearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_YearMouseClicked
        jDateChooser_From.setDateFormatString("yyyy");
        jDateChooser_To.setDateFormatString("yyyy");
        
        jPanelHoldChart_DoanhThu.setVisible(true);
        if (!legendsAdded) { // Kiểm tra nếu legend chưa được thêm
        chart_DoanhThu.addLegend("Doanh thu", Color.decode("#439CFB"), Color.decode("#F187FB"));
        chart_DoanhThu.addLegend("Lợi nhuận", Color.decode("#00EE6E"), Color.decode("#0C75E6"));
        legendsAdded = true; // Đánh dấu là legend đã được thêm
        }

    }//GEN-LAST:event_jPanel_YearMouseClicked

    private void jPanel_MonthMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_MonthMouseClicked
        jDateChooser_From.setDateFormatString("MM-yyyy");
        jDateChooser_To.setDateFormatString("MM-yyyy");
        
        jPanelHoldChart_DoanhThu.setVisible(true);
        if (!legendsAdded) { // Kiểm tra nếu legend chưa được thêm
        chart_DoanhThu.addLegend("Doanh thu", Color.decode("#439CFB"), Color.decode("#F187FB"));
        chart_DoanhThu.addLegend("Lợi nhuận", Color.decode("#00EE6E"), Color.decode("#0C75E6"));
        legendsAdded = true; // Đánh dấu là legend đã được thêm
        }

    }//GEN-LAST:event_jPanel_MonthMouseClicked

    private void jPanel_DayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_DayMouseClicked
        jDateChooser_From.setDateFormatString("dd-MM-yyyy");
        jDateChooser_To.setDateFormatString("dd-MM-yyyy");
        
        jPanelHoldChart_DoanhThu.setVisible(true);
        if (!legendsAdded) { // Kiểm tra nếu legend chưa được thêm
        chart_DoanhThu.addLegend("Doanh thu", Color.decode("#439CFB"), Color.decode("#F187FB"));
        chart_DoanhThu.addLegend("Lợi nhuận", Color.decode("#00EE6E"), Color.decode("#0C75E6"));
        legendsAdded = true; // Đánh dấu là legend đã được thêm
        }
    }//GEN-LAST:event_jPanel_DayMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        Date fromDate = jDateChooser_From.getDate();
        Date toDate = jDateChooser_To.getDate();
        setChart_DoanhThuTheoThang(fromDate, toDate);
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LamMoiActionPerformed
        jDateChooser_From.setDate(null);
        jDateChooser_To.setDate(null);
        chart_DoanhThu.clear();
    }//GEN-LAST:event_jButton_LamMoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private chart.CurveLineChart chart_DoanhThu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_LamMoi;
    private javax.swing.JButton jButton_XuatExcel;
    private com.toedter.calendar.JDateChooser jDateChooser_From;
    private com.toedter.calendar.JDateChooser jDateChooser_To;
    private javax.swing.JLabel jLabel_Day;
    private javax.swing.JLabel jLabel_Month;
    private javax.swing.JLabel jLabel_Year;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelHoldChart_DoanhThu;
    private javax.swing.JPanel jPanel_Day;
    private javax.swing.JPanel jPanel_Month;
    private javax.swing.JPanel jPanel_Year;
    private panel.PanelShadow panelShadow1;
    // End of variables declaration//GEN-END:variables
}

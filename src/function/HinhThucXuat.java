package function;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import view.NguoiQuanLy_ThemNV;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class HinhThucXuat extends JDialog {
    //
    private JTable jTable_HoaDon;
    private JButton jButton_XuatExcel;
    private JButton jButton_InPDF;
    public HinhThucXuat(JTable jTable) {
        this.jTable_HoaDon=jTable;
        initComponents();
    }
    private void initComponents() {
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jLabel_TieuDe = new JLabel();
        jButton_XuatExcel = new JButton();
        jButton_InPDF = new JButton();
        jButton_Huy = new JButton();
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        kGradientPanel1.setkEndColor(new Color(139, 119, 101));
        kGradientPanel1.setkStartColor(new Color(205, 175, 149));
        jLabel_TieuDe.setFont(new Font("Bahnschrift", 1, 30));
        jLabel_TieuDe.setForeground(new Color(102, 102, 0));
        jLabel_TieuDe.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel_TieuDe.setText("Hình thức");
        jButton_XuatExcel.setBackground(new Color(102, 102, 0));
        jButton_XuatExcel.setFont(new Font("Arial", 1, 20));
        jButton_XuatExcel.setForeground(new Color(255, 255, 255));
        jButton_XuatExcel.setText("Xuất Excel");
        jButton_XuatExcel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // Gọi phương thức xuất Excel tại đây
                xuatExcelActionPerformed(evt);
            }
        });

        jButton_InPDF.setBackground(new Color(0, 102, 153));
        jButton_InPDF.setFont(new Font("Arial", 1, 20));
        jButton_InPDF.setForeground(new Color(255, 255, 255));
        jButton_InPDF.setText("In PDF");
        jButton_InPDF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // Gọi phương thức in PDF tại đây
                inPdfActionPerformed(evt);
            }
        });

        jButton_Huy.setBackground(new Color(153, 0, 51));
        jButton_Huy.setFont(new Font("Arial", 1, 20));
        jButton_Huy.setForeground(new Color(255, 255, 255));
        jButton_Huy.setText("Hủy");
        jButton_Huy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                huyActionPerformed(evt);
            }
        });

        GroupLayout kGradientPanel1Layout = new GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
                kGradientPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(kGradientPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel_TieuDe, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                                .addComponent(jButton_XuatExcel)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton_InPDF, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton_Huy, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        kGradientPanel1Layout.setVerticalGroup(
                kGradientPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel_TieuDe)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                                .addGroup(kGradientPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton_XuatExcel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton_InPDF, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton_Huy, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                .addGap(100, 100, 100))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(kGradientPanel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(kGradientPanel1, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0))
        );

        pack();
    }

    private void huyActionPerformed(ActionEvent evt) {
        this.dispose();
    }

    private void inPdfActionPerformed(ActionEvent evt) {
        new InPDF().ghiDachSachHoaDon(jTable_HoaDon);
    }

    private void xuatExcelActionPerformed(ActionEvent evt) {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Chọn nơi lưu file Excel");
        int userSelection = jFileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File saveFile = jFileChooser.getSelectedFile();
            // Đảm bảo file có đuôi .xlsx
            if (!saveFile.getName().toLowerCase().endsWith(".xlsx")) {
                saveFile = new File(saveFile.getAbsolutePath() + ".xlsx");
            }
            // Tạo workbook và sheet
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("HoaDon");
                // Định dạng header
                CellStyle headerCellStyle = taoKieuTieuDe(workbook);
                // Tạo dòng tiêu đề
                Row tieuDe = sheet.createRow(0);
                for (int i = 1; i < jTable_HoaDon.getColumnCount()-1; i++) {  // Bỏ qua cột đầu và cuối
                    Cell cell = tieuDe.createCell(i - 1);
                    cell.setCellValue(jTable_HoaDon.getColumnName(i));
                    cell.setCellStyle(headerCellStyle);
                }
                // Định dạng dữ liệu trong bảng
                CellStyle dataCellStyle = taoKieuDuLieu(workbook);
                int tongTien=0;
                int tongSoLuong=0;
                // Thêm dữ liệu vào các dòng
                for (int i = 0; i < jTable_HoaDon.getRowCount(); i++) {
                    Row row = sheet.createRow(i + 1);
                    String tongTienHoaDon=(String)jTable_HoaDon.getValueAt(i,5);
                    String numberStr = tongTienHoaDon.replaceAll("[^0-9,]", "");
                    numberStr = numberStr.replace(",", "");
                    int tongTienFormat = Integer.parseInt(numberStr);
                    tongSoLuong+= (int)jTable_HoaDon.getValueAt(i,4);
                    tongTien+=tongTienFormat;
                    for (int j = 1; j < jTable_HoaDon.getColumnCount()-1; j++) {  // Bỏ qua cột đầu và cuối
                        Cell cell = row.createCell(j - 1);
                        Object value = jTable_HoaDon.getValueAt(i, j);
                        if (value != null) {
                            cell.setCellValue(value.toString());
                        }
                        cell.setCellStyle(dataCellStyle);
                    }
                }
                Row row = sheet.createRow(jTable_HoaDon.getRowCount());
                Cell cellSL = row.createCell(3);
                cellSL.setCellStyle(dataCellStyle);
                cellSL.setCellValue(tongSoLuong);
                Cell cellTongTien = row.createCell(4);
                cellTongTien.setCellStyle(dataCellStyle);
                DecimalFormat df=new DecimalFormat("#,###");
                cellTongTien.setCellValue(df.format(tongTien)+" VND");
                // Tự động điều chỉnh kích thước cột cho phù hợp với nội dung
                for (int i = 0; i < jTable_HoaDon.getColumnCount() - 2; i++) {
                    sheet.autoSizeColumn(i);
                }
                // Ghi dữ liệu vào file
                try (FileOutputStream out = new FileOutputStream(saveFile)) {
                    workbook.write(out);
                    JOptionPane.showMessageDialog(this, "Xuất file Excel thành công!");
                    Desktop.getDesktop().open(saveFile);
                }
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(this, "File không tìm thấy: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Lỗi IO: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NguoiQuanLy_ThemNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NguoiQuanLy_ThemNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NguoiQuanLy_ThemNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NguoiQuanLy_ThemNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    private CellStyle taoKieuTieuDe(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    // Phương thức tạo định dạng cho dữ liệu
    private CellStyle taoKieuDuLieu(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }
    private JButton jButton_Huy;

    private JLabel jLabel_TieuDe;
    private keeptoo.KGradientPanel kGradientPanel1;
}

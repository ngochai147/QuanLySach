/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import button.TableActionCellEditor;
import button.TableActionEvent;
import button.TableActionRender;
import dao.*;
import entity.*;

import entity.NhanVien;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
/**
 *
 * @author Thế Bảo
 */
public class ThuKho_QuanLyNhapXuatKho extends javax.swing.JInternalFrame {
    private PhieuXuat_DAO pxk_dao = new PhieuXuat_DAO();
    private NhanVien_DAO nv_dao = new NhanVien_DAO();
    private ChiTietPhieuXuatKho_DAO ctpxk_dao = new ChiTietPhieuXuatKho_DAO();
    private KhoHang_DAO khoHang = new KhoHang_DAO();
    private Report_PhieuXuatKho Report_PhieuXuatKho = new Report_PhieuXuatKho();
    ArrayList<PhieuXuatKho> listPXK = pxk_dao.getAllphieuXuatKho();
    private DefaultTableModel modelXuatNhapKho;
    private ThuKho thuKho;
    /**
     * Creates new form TrangNhapKho_GUI
     */
    public ThuKho_QuanLyNhapXuatKho(ThuKho thuKho) {
        this.thuKho = thuKho;

        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);

         // doc du lieu tu database SQL vao Jtable
        modelXuatNhapKho = new DefaultTableModel(new Object[]{"STT", "Mã phiếu", "Mã thủ kho", "Tên kho nhập", "Tên kho xuất", "Loại phiếu", "Số lượng", "Ngày lập phiếu", ""}, 0);
        DocDuLieuDatabaseVaoTable();
        tbl_QLXuatNhapKho.setModel(modelXuatNhapKho);

        cb_chonTieuChi.setSelectedIndex(0);

        ChinhMauCombobox();
        canGiua_tableHeader();
        chinhSua_table();
        chinhSua_btnView();
    }

    private void DocDuLieuDatabaseVaoTable() {
        int stt = 1;
        DateTimeFormatter dfDay = DateTimeFormatter.ofPattern("dd-MM-YYYY");
        // Đọc dữ liệu từ bảng phiếu xuất kho vào table
        ArrayList<PhieuXuatKho> listPXK = pxk_dao.getAllphieuXuatKho();
//        ArrayList<PhieuNhapKho> listPNK = pnk_dao.getAllPhieuNhapKho();
        for(PhieuXuatKho pxk : listPXK) {
            String loaiPhieu;
            loaiPhieu = "Xuất kho"; // Nếu mã bắt đầu bằng "PXK"
            KhoHang tenKhoNhap = khoHang.getKhoHangTheoMaKho(pxk.getKhoHangNhap().getMaKhoHang());
            KhoHang tenKhoXuat = khoHang.getKhoHangTheoMaKho(pxk.getKhoHangXuat().getMaKhoHang());
            modelXuatNhapKho.addRow(new Object[]{
                    stt++,pxk.getMaPhieuXuatKho(), pxk.getNhanVien().getMaNV(), tenKhoNhap.getTenKho(), tenKhoXuat.getTenKho(), loaiPhieu, pxk.getSoLuong(),dfDay.format(pxk.getNgayLap()), ""
            });
        }
    }

    private void ChinhMauCombobox() {
        // Chỉnh màu xanh cho combobox
        Color customGreen = new Color(255, 255, 255);
        cb_chonTieuChi.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (isSelected) {
                    c.setBackground(new Color(102,102,0));   // Màu nền khi mục được chọn
                    c.setForeground(customGreen);   // Màu chữ khi mục được chọn
                } else {
                    c.setBackground(customGreen); // Màu nền cho các mục không được chọn
                    c.setForeground(new Color(102,102,0));      // Màu chữ cho các mục không được chọn
                }

                return c;
            }
        });

        jcb_danhSach.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (isSelected) {
                    c.setBackground(new Color(102,102,0));   // Màu nền khi mục được chọn
                    c.setForeground(customGreen);   // Màu chữ khi mục được chọn
                } else {
                    c.setBackground(customGreen); // Màu nền cho các mục không được chọn
                    c.setForeground(new Color(102,102,0));      // Màu chữ cho các mục không được chọn
                }

                return c;
            }
        });
    }

    public void chinhSua_table() {
        //Căn giữa các giá trị cột STT trong table
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        centerRenderer.setVerticalAlignment(JLabel.CENTER );
        tbl_QLXuatNhapKho.getColumnModel().getColumn(0).setCellRenderer( centerRenderer);

        // Thiết lập kích thước font cho các phần tử trong table
        Font font = new Font("Arial", Font.PLAIN, 18);
        tbl_QLXuatNhapKho.setFont(font);

        // Chỉnh kích thước cột
        tbl_QLXuatNhapKho.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbl_QLXuatNhapKho.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbl_QLXuatNhapKho.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbl_QLXuatNhapKho.getColumnModel().getColumn(3).setPreferredWidth(150);
        tbl_QLXuatNhapKho.getColumnModel().getColumn(4).setPreferredWidth(150);
        tbl_QLXuatNhapKho.getColumnModel().getColumn(5).setPreferredWidth(100);
        tbl_QLXuatNhapKho.getColumnModel().getColumn(6).setPreferredWidth(60);
        tbl_QLXuatNhapKho.getColumnModel().getColumn(7).setPreferredWidth(150);
        tbl_QLXuatNhapKho.getColumnModel().getColumn(8).setPreferredWidth(50);
    }

    public void chinhSua_btnView() {
        // Căn giữa và chỉnh cho nút chi tiết có thể hoạt động được trong table
        TableActionEvent event;
        event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
//
//                JOptionPane.showMessageDialog(null, "Simple Information Message");
            }

            @Override
            public void onDelete(int row) {

            }

            @Override
            public void onView(int row) {
                PhieuXuatKho pxk = new PhieuXuatKho();
                int n = tbl_QLXuatNhapKho.getSelectedRow();
                String maPXK = pxk_dao.getAllphieuXuatKho().get(n).getMaPhieuXuatKho();

                ArrayList<ChiTietPhieuXuatKho> dsCTPXKtemp = new ArrayList<>();
                dsCTPXKtemp.forEach(x-> System.out.println(x));
                for(ChiTietPhieuXuatKho ctpxk : ctpxk_dao.getDSCTPXK()){
                    if(ctpxk.getPhieuXuatKho().getMaPhieuXuatKho().equalsIgnoreCase(maPXK)){
                        dsCTPXKtemp.add(ctpxk);
                    }
                }
                Report_PhieuXuatKho.ViewReport_PhieuXuatKho(dsCTPXKtemp,maPXK);
            }
        };
        tbl_QLXuatNhapKho.getColumnModel().getColumn(8).setCellRenderer(new TableActionRender(1));
        tbl_QLXuatNhapKho.getColumnModel().getColumn(8).setCellEditor(new TableActionCellEditor(event, 1));
    }

    public void canGiua_tableHeader() {
    // Căn giữa và chỉnh kích thước table header
        Font fn = new Font("Arial", Font.BOLD, 18);
        tbl_QLXuatNhapKho.getTableHeader().setFont(fn);

        // Lấy header của bảng
        JTableHeader header = tbl_QLXuatNhapKho.getTableHeader();

        // Tạo renderer để căn giữa header
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        // Thiết lập renderer cho header
        header.setDefaultRenderer(renderer);

        tbl_QLXuatNhapKho.setPreferredSize(new Dimension(600, modelXuatNhapKho.getRowCount()*40));
    }

    public void exportToExcel(JTable tbl_QLXuatNhapKho) {
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            if (saveFile != null) {
                // Check if the file name ends with ".xlsx", otherwise add it
                if (!saveFile.getName().toLowerCase().endsWith(".xlsx")) {
                    saveFile = new File(saveFile + ".xlsx");
                }

                Workbook wb;  // Create a new Excel workbook
                wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("ds_XuatKho");

                // Create the header row, but exclude the last column
                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < tbl_QLXuatNhapKho.getColumnCount() - 1; i++) {  // Exclude last column
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(tbl_QLXuatNhapKho.getColumnName(i));  // Set column names in the first row
                }

                // Loop through the JTable rows and columns, filling the Excel sheet, excluding the last column
                for (int i = 0; i < tbl_QLXuatNhapKho.getRowCount(); i++) {
                    Row row = sheet.createRow(i + 1);  // Create a new row for each JTable row
                    for (int j = 0; j < tbl_QLXuatNhapKho.getColumnCount() - 1; j++) {  // Exclude last column
                        Cell cell = row.createCell(j);
                        Object value = tbl_QLXuatNhapKho.getValueAt(i, j);  // Get value from JTable cell
                        if (value != null) {
                            cell.setCellValue(value.toString());  // Set the cell value in Excel
                        }
                    }
                }

                // Write the data to the file
                try (FileOutputStream out = new FileOutputStream(saveFile)) {
                    wb.write(out);  // Write the workbook content to the file
                }
                wb.close();  // Close the workbook

                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(saveFile);
                } else {
                    System.out.println("Desktop not supported, cannot open the file automatically.");
                }

            } else {
                System.out.println("Save file selection was canceled.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException ioe) {
            System.out.println("IO error: " + ioe.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        panel_QLXuatNhapKho = new javax.swing.JPanel();
        btn_xuatExcel = new javax.swing.JButton();
        btn_XuatKho = new javax.swing.JButton();
        cb_chonTieuChi = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_QLXuatNhapKho = new javax.swing.JTable();
        btn_lamMoi = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btn_NhapKho = new javax.swing.JButton();
        jcb_danhSach = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1612, 733));

        panel_QLXuatNhapKho.setBackground(new java.awt.Color(255, 255, 255));
        panel_QLXuatNhapKho.setPreferredSize(new java.awt.Dimension(1612, 733));
        panel_QLXuatNhapKho.setLayout(null);

        btn_xuatExcel.setBackground(new java.awt.Color(102, 102, 0));
        btn_xuatExcel.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btn_xuatExcel.setForeground(new java.awt.Color(255, 255, 255));
        btn_xuatExcel.setText("Xuất excel");
        btn_xuatExcel.setPreferredSize(new java.awt.Dimension(120, 42));
        btn_xuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xuatExcelActionPerformed(evt);
            }
        });
        panel_QLXuatNhapKho.add(btn_xuatExcel);
        btn_xuatExcel.setBounds(50, 640, 140, 40);

        btn_XuatKho.setBackground(new java.awt.Color(102, 102, 0));
        btn_XuatKho.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btn_XuatKho.setForeground(new java.awt.Color(255, 255, 255));
        btn_XuatKho.setText("Xuất kho");
        btn_XuatKho.setPreferredSize(new java.awt.Dimension(120, 42));
        btn_XuatKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btn_XuatKhoActionPerformed(evt);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        panel_QLXuatNhapKho.add(btn_XuatKho);
        btn_XuatKho.setBounds(40, 170, 120, 42);

        cb_chonTieuChi.setBackground(new java.awt.Color(102, 102, 0));
        cb_chonTieuChi.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        cb_chonTieuChi.setForeground(new java.awt.Color(255, 255, 255));
        cb_chonTieuChi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã phiếu", "Tên kho nhập", "Tên kho xuất", "Mã thủ kho", "Ngày lập phiếu" }));
        cb_chonTieuChi.setMinimumSize(new java.awt.Dimension(72, 30));
        cb_chonTieuChi.setPreferredSize(new java.awt.Dimension(300, 42));
        cb_chonTieuChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_chonTieuChiActionPerformed(evt);
            }
        });
        panel_QLXuatNhapKho.add(cb_chonTieuChi);
        cb_chonTieuChi.setBounds(1010, 170, 220, 42);

        tbl_QLXuatNhapKho.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tbl_QLXuatNhapKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã phiếu", "Mã thủ kho", "Tên kho nhập", "Tên kho xuất", "Loại phiếu", "Số lượng", "Ngày lập phiếu", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_QLXuatNhapKho.setAutoscrolls(false);
        tbl_QLXuatNhapKho.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbl_QLXuatNhapKho.setPreferredSize(new java.awt.Dimension(600, 1000));
        tbl_QLXuatNhapKho.setRowHeight(40);
        tbl_QLXuatNhapKho.setShowGrid(true);
        jScrollPane1.setViewportView(tbl_QLXuatNhapKho);

        panel_QLXuatNhapKho.add(jScrollPane1);
        jScrollPane1.setBounds(40, 230, 1470, 390);

        btn_lamMoi.setBackground(new java.awt.Color(102, 102, 0));
        btn_lamMoi.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btn_lamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btn_lamMoi.setText("Làm mới");
        btn_lamMoi.setPreferredSize(new java.awt.Dimension(120, 42));
        btn_lamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lamMoiActionPerformed(evt);
            }
        });
        panel_QLXuatNhapKho.add(btn_lamMoi);
        btn_lamMoi.setBounds(320, 170, 120, 42);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/jLabel_QuanLyXuatKho.png"))); // NOI18N
        panel_QLXuatNhapKho.add(jLabel1);
        jLabel1.setBounds(420, 20, 670, 88);

        btn_NhapKho.setBackground(new java.awt.Color(102, 102, 0));
        btn_NhapKho.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btn_NhapKho.setForeground(new java.awt.Color(255, 255, 255));
        btn_NhapKho.setText("Nhập kho");
        btn_NhapKho.setPreferredSize(new java.awt.Dimension(120, 42));
        btn_NhapKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NhapKhoActionPerformed(evt);
            }
        });
        panel_QLXuatNhapKho.add(btn_NhapKho);
        btn_NhapKho.setBounds(180, 170, 120, 42);

        jcb_danhSach.setBackground(new java.awt.Color(102, 102, 0));
        jcb_danhSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jcb_danhSach.setForeground(new java.awt.Color(255, 255, 255));
        jcb_danhSach.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcb_danhSachItemStateChanged(evt);
            }
        });
        panel_QLXuatNhapKho.add(jcb_danhSach);
        jcb_danhSach.setBounds(1250, 170, 260, 40);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/anhnen.jpg"))); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(1612, 733));
        panel_QLXuatNhapKho.add(jLabel2);
        jLabel2.setBounds(-8, 0, 1630, 733);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_QLXuatNhapKho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_QLXuatNhapKho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_xuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xuatExcelActionPerformed
        exportToExcel(tbl_QLXuatNhapKho);
    }//GEN-LAST:event_btn_xuatExcelActionPerformed

    private void btn_lamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lamMoiActionPerformed
        modelXuatNhapKho = new DefaultTableModel(new Object[]{"STT", "Mã phiếu", "Mã thủ kho", "Tên kho nhập", "Tên kho xuất", "Loại phiếu", "Số lượng", "Ngày lập phiếu", ""}, 0);
        DocDuLieuDatabaseVaoTable();
        tbl_QLXuatNhapKho.setModel(modelXuatNhapKho);

        cb_chonTieuChi.setSelectedItem("Mã phiếu");

        canGiua_tableHeader();
        chinhSua_table();
        chinhSua_btnView();
    }//GEN-LAST:event_btn_lamMoiActionPerformed

    private void btn_XuatKhoActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {//GEN-FIRST:event_btn_XuatKhoActionPerformed
            thuKho.getDesktopPanel(new ThuKho_XuatKho());
//        thuKho.getDesktopPanel(new ThuKho_XuatKho());
    }//GEN-LAST:event_btn_XuatKhoActionPerformed

    private void btn_NhapKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NhapKhoActionPerformed
        thuKho.getDesktopPanel(new ThuKho_NhapKho());
    }//GEN-LAST:event_btn_NhapKhoActionPerformed

    private void cb_chonTieuChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_chonTieuChiActionPerformed
        Object selectedItem = cb_chonTieuChi.getSelectedItem();
        if (selectedItem == null) {
            return; // Thoát nếu chưa có mục nào được chọn
        }

        try {
            String tieuChi = cb_chonTieuChi.getSelectedItem().toString();
            jcb_danhSach.setVisible(true);
            jcb_danhSach.removeAllItems();
            jcb_danhSach.addItem("");

            switch (tieuChi) {
                case "Mã thủ kho":
                    for (NhanVien nv : nv_dao.getDSNhanVien()) {
                        jcb_danhSach.addItem(nv.getMaNV());
                    }
                    break;

                case "Mã phiếu":
                    ArrayList<PhieuXuatKho> listPXK = pxk_dao.getAllphieuXuatKho();
                    for (PhieuXuatKho pxk : listPXK) {
                        jcb_danhSach.addItem(pxk.getMaPhieuXuatKho());
                    }
                    break;

                case "Tên kho nhập":
                    Set<String> uniqueMaKhoNhap = new HashSet<>(); // Using Set to store unique codes
                    for (KhoHang kh : khoHang.getDSKhoHang()) {
                        uniqueMaKhoNhap.add(kh.getTenKho()); // Add to Set, which automatically handles duplicates
                    }
                    for (String tenKho : uniqueMaKhoNhap) {
                        jcb_danhSach.addItem(tenKho); // Add unique codes to jcb_danhSach
                    }
                    break;

                case "Tên kho xuất":
                    Set<String> uniqueMaKhoXuat = new HashSet<>(); // Using Set to store unique codes
                    for (KhoHang kh : khoHang.getDSKhoHang()) {
                        uniqueMaKhoXuat.add(kh.getTenKho()); // Add to Set, which automatically handles duplicates
                    }
                    for (String tenKho : uniqueMaKhoXuat) {
                        jcb_danhSach.addItem(tenKho); // Add unique codes to jcb_danhSach
                    }
                    break;
                case "Ngày lập phiếu":
                    DateTimeFormatter dfDay = DateTimeFormatter.ofPattern("dd-MM-YYYY");
                    for (PhieuXuatKho pxk : pxk_dao.getAllphieuXuatKho()) {
                        jcb_danhSach.addItem(dfDay.format(pxk.getNgayLap()));
                    }
                    break;
                default:
                    System.out.println("No matching criteria found.");
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cb_chonTieuChiActionPerformed

    private void jcb_danhSachItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcb_danhSachItemStateChanged
        int stt = 0;
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            Object selectedItem = evt.getItem(); // Lấy mục vừa được chọn
            if (selectedItem != null && !selectedItem.toString().trim().isEmpty()) { // Kiểm tra null hoặc rỗng
//                System.out.println("Bạn đã chọn: " + selectedItem.toString());

                String textTim = selectedItem.toString();
                danhSachTimKiem(stt, textTim);

            } else {
//                System.out.println("Không có mục hợp lệ được chọn."); // Xử lý khi rỗng
            }
        }
    }//GEN-LAST:event_jcb_danhSachItemStateChanged

    public void danhSachTimKiem(int stt, String textTim) {
        String tieuChi = cb_chonTieuChi.getSelectedItem().toString();

        modelXuatNhapKho = new DefaultTableModel(new Object[]{"STT", "Mã phiếu", "Mã thủ kho", "Tên kho nhập", "Tên kho xuất", "Loại phiếu", "Số lượng", "Ngày lập phiếu", ""}, 0);
        if (tieuChi.equalsIgnoreCase("Mã phiếu")) {
            for (PhieuXuatKho pxk : listPXK) {
                if (pxk.getMaPhieuXuatKho().contains(textTim)) {
                    String loaiPhieu;
                    loaiPhieu = "Xuất kho"; // Nếu mã bắt đầu bằng "PXK"
                    KhoHang tenKhoNhap = khoHang.getKhoHangTheoMaKho(pxk.getKhoHangNhap().getMaKhoHang());
                    KhoHang tenKhoXuat = khoHang.getKhoHangTheoMaKho(pxk.getKhoHangXuat().getMaKhoHang());
                    DateTimeFormatter dfDay = DateTimeFormatter.ofPattern("dd-MM-YYYY");

                    modelXuatNhapKho.addRow(new Object[]{
                            stt++,pxk.getMaPhieuXuatKho(), pxk.getNhanVien().getMaNV(), tenKhoNhap.getTenKho(), tenKhoXuat.getTenKho(), loaiPhieu, pxk.getSoLuong(), dfDay.format(pxk.getNgayLap()), ""
                    });
                }
            }
        } else if (tieuChi.equalsIgnoreCase("Tên kho nhập")) {
            Set<String> addedPhieuXuat = new HashSet<>();
            try {
                for (KhoHang kh : khoHang.getDSKhoHang()) {
                    if (kh.getTenKho().contains(textTim)) {
                        // Lặp qua danh sách phiếu xuất kho
                        for (PhieuXuatKho pxk : listPXK) {
                            if (pxk.getKhoHangNhap().getMaKhoHang().contains(kh.getMaKhoHang())) {
                                String maPhieu = pxk.getMaPhieuXuatKho();
                                KhoHang tenKhoNhap = khoHang.getKhoHangTheoMaKho(pxk.getKhoHangNhap().getMaKhoHang());
                                KhoHang tenKhoXuat = khoHang.getKhoHangTheoMaKho(pxk.getKhoHangXuat().getMaKhoHang());
                                DateTimeFormatter dfDay = DateTimeFormatter.ofPattern("dd-MM-YYYY");

                                // Kiểm tra xem mã phiếu đã được thêm chưa
                                if (!addedPhieuXuat.contains(maPhieu)) {
                                    String loaiPhieu = "Xuất kho"; // Nếu mã bắt đầu bằng "PXK"

                                    // Thêm phiếu xuất vào mô hình
                                    modelXuatNhapKho.addRow(new Object[]{
                                            stt++,
                                            pxk.getMaPhieuXuatKho(),
                                            pxk.getNhanVien().getMaNV(),
                                            tenKhoNhap.getTenKho(),
                                            tenKhoXuat.getTenKho(),
                                            loaiPhieu,
                                            pxk.getSoLuong(),
                                            dfDay.format(pxk.getNgayLap()),
                                            ""
                                    });

                                    // Thêm mã phiếu vào Set để tránh trùng lặp
                                    addedPhieuXuat.add(maPhieu);
                                }
                            }
                        }
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (tieuChi.equalsIgnoreCase("Tên kho xuất")) {
            Set<String> addedPhieuXuat = new HashSet<>();
            try {
                for (KhoHang kh : khoHang.getDSKhoHang()) {
                    if (kh.getTenKho().contains(textTim)) {
                        // Lặp qua danh sách phiếu xuất kho
                        for (PhieuXuatKho pxk : listPXK) {
                            if (pxk.getKhoHangXuat().getMaKhoHang().contains(kh.getMaKhoHang())) {
                                String maPhieu = pxk.getMaPhieuXuatKho();
                                KhoHang tenKhoNhap = khoHang.getKhoHangTheoMaKho(pxk.getKhoHangNhap().getMaKhoHang());
                                KhoHang tenKhoXuat = khoHang.getKhoHangTheoMaKho(pxk.getKhoHangXuat().getMaKhoHang());
                                DateTimeFormatter dfDay = DateTimeFormatter.ofPattern("dd-MM-YYYY");

                                // Kiểm tra xem mã phiếu đã được thêm chưa
                                if (!addedPhieuXuat.contains(maPhieu)) {
                                    String loaiPhieu = "Xuất kho"; // Nếu mã bắt đầu bằng "PXK"

                                    // Thêm phiếu xuất vào mô hình
                                    modelXuatNhapKho.addRow(new Object[]{
                                            stt++,
                                            pxk.getMaPhieuXuatKho(),
                                            pxk.getNhanVien().getMaNV(),
                                            tenKhoNhap.getTenKho(),
                                            tenKhoXuat.getTenKho(),
                                            loaiPhieu,
                                            pxk.getSoLuong(),
                                            dfDay.format(pxk.getNgayLap()),
                                            ""
                                    });

                                    // Thêm mã phiếu vào Set để tránh trùng lặp
                                    addedPhieuXuat.add(maPhieu);
                                }
                            }
                        }
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (tieuChi.equalsIgnoreCase("Mã thủ kho")) {
            for (PhieuXuatKho pxk : listPXK) {
                if (pxk.getNhanVien().getMaNV().contains(textTim)) {
                    String loaiPhieu;
                    loaiPhieu = "Xuất kho"; // Nếu mã bắt đầu bằng "PXK"
                    KhoHang tenKhoNhap = khoHang.getKhoHangTheoMaKho(pxk.getKhoHangNhap().getMaKhoHang());
                    KhoHang tenKhoXuat = khoHang.getKhoHangTheoMaKho(pxk.getKhoHangXuat().getMaKhoHang());
                    DateTimeFormatter dfDay = DateTimeFormatter.ofPattern("dd-MM-YYYY");

                    modelXuatNhapKho.addRow(new Object[]{
                            stt++,pxk.getMaPhieuXuatKho(), pxk.getNhanVien().getMaNV(), tenKhoNhap.getTenKho(), tenKhoXuat.getTenKho(), loaiPhieu, pxk.getSoLuong(),dfDay.format(pxk.getNgayLap()), ""
                    });
                }
            }
        } else if (tieuChi.equalsIgnoreCase("Ngày lập phiếu")) {
            // Định dạng ngày thành dd-MM-yyyy
            DateTimeFormatter dfDay = DateTimeFormatter.ofPattern("dd-MM-YYYY");
            for (PhieuXuatKho pxk : listPXK) {
                if (dfDay.format(pxk.getNgayLap()).equalsIgnoreCase(textTim)) {
                    String loaiPhieu;
                    loaiPhieu = "Xuất kho"; // Nếu mã bắt đầu bằng "PXK"
                    KhoHang tenKhoNhap = khoHang.getKhoHangTheoMaKho(pxk.getKhoHangNhap().getMaKhoHang());
                    KhoHang tenKhoXuat = khoHang.getKhoHangTheoMaKho(pxk.getKhoHangXuat().getMaKhoHang());

                    modelXuatNhapKho.addRow(new Object[]{
                            stt++,pxk.getMaPhieuXuatKho(), pxk.getNhanVien().getMaNV(), tenKhoNhap.getTenKho(), tenKhoXuat.getTenKho(), loaiPhieu, pxk.getSoLuong(),dfDay.format(pxk.getNgayLap()), ""
                    });
                }
            }
        }
        tbl_QLXuatNhapKho.setModel(modelXuatNhapKho);
        canGiua_tableHeader();
        chinhSua_table();
        chinhSua_btnView();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_NhapKho;
    private javax.swing.JButton btn_XuatKho;
    private javax.swing.JButton btn_lamMoi;
    private javax.swing.JButton btn_xuatExcel;
    private javax.swing.JComboBox<String> cb_chonTieuChi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcb_danhSach;
    private javax.swing.JPanel panel_QLXuatNhapKho;
    private javax.swing.JTable tbl_QLXuatNhapKho;
    // End of variables declaration//GEN-END:variables
}


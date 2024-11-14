/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import button.TableActionCellEditor;
import button.TableActionEvent;
import button.TableActionRender;
import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.Sach_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.Sach;
import function.HinhThucXuat;
import function.PhieuHoaDon;
import org.apache.poi.ss.usermodel.*;

import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
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

/**
 *
 * @author Ngọc Hải
 */
public class QuanLyHoaDon_GUI extends javax.swing.JInternalFrame {

    private Color customGreen;

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
                PhieuHoaDon phieuHoaDon=new PhieuHoaDon();
                int n= jTable_HoaDon.getSelectedRow();
                String maHoaDon=dsHD.getAllHoaDon().get(n).getMaHoaDon();
                ArrayList<ChiTietHoaDon> dsCTHDtemp=new ArrayList<>();
                for(ChiTietHoaDon cthd: listCTHD){
                    if(cthd.getHoaDon().getMaHoaDon().equalsIgnoreCase(maHoaDon)){
                        dsCTHDtemp.add(cthd);
                    }
                }
                phieuHoaDon.viewHoaDon(dsCTHDtemp,maHoaDon);
                if (jTable_HoaDon.isEditing()) {
                    jTable_HoaDon.getCellEditor().stopCellEditing();
                }
            }
        };
        jTable_HoaDon.getColumnModel().getColumn(6).setCellRenderer(new TableActionRender(1));
        jTable_HoaDon.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor(event, 1));
        jComboBox_TimKiem.addItem("");
        for(HoaDon hd:listHD){
            jComboBox_TimKiem.addItem(hd.getMaHoaDon());
        }
        themDuLieuVaoBang();
        jTable_HoaDon.setPreferredSize(new java.awt.Dimension(525, jTable_HoaDon.getRowCount()*40));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton_TaoHoaDon = new javax.swing.JButton();
        jButton_LamMoi = new javax.swing.JButton();
        jButton_XoaNhieu = new javax.swing.JButton();
        jComboBox_TieuChi = new javax.swing.JComboBox<>();
        jButton_TimKiem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_HoaDon = new javax.swing.JTable();
        jButton_XuatExcel = new javax.swing.JButton();
        jLabel_QuanLyHoaDon = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox_TimKiem= new javax.swing.JComboBox<>();

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
        jComboBox_TieuChi.setBorder(null);
        jComboBox_TieuChi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Mã hóa đơn", "Mã nhân viên", "Ngày tạo đơn" }));
        jPanel1.add(jComboBox_TieuChi);
        jComboBox_TieuChi.setBounds(1085, 120, 170, 46);
        jComboBox_TieuChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_TieuChiActionPerformed(evt);
            }
        });

        jDateChooser_NgayTaoDon = new com.toedter.calendar.JDateChooser();
        jDateChooser_NgayTaoDon.setFont(new java.awt.Font("Arial", 1, 20));
        jPanel1.add(jDateChooser_NgayTaoDon);
        jDateChooser_NgayTaoDon.setBounds(1260, 120, 250, 46);
        jDateChooser_NgayTaoDon.setVisible(false);
        jDateChooser_NgayTaoDon.getDateEditor().addPropertyChangeListener("date", evt -> {
            if (jDateChooser_NgayTaoDon.getDate() != null) {
                jDateChooser_NgayTaoDonMouseListener(evt);
            }
        });

        jComboBox_TimKiem.setBackground(new java.awt.Color(102, 102, 0));
        jComboBox_TimKiem.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jComboBox_TimKiem.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox_TimKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
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
        jComboBox_TimKiem.setBorder(null);
        jComboBox_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_TimKiemActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox_TimKiem);
        jComboBox_TimKiem.setBounds(1260, 120, 250, 46);

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
       // jPanel1.add(jButton_TimKiem);
        jButton_TimKiem.setBounds(1370, 120, 136, 46);

        jTable_HoaDon.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jTable_HoaDon.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "STT", "Mã hóa đơn", "Ngày tạo đơn", "Mã nhân viên", "Tổng số lượng", "Tổng tiền", ""
                }

        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, true
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_HoaDon.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTable_HoaDon.setPreferredSize(new java.awt.Dimension(525, 520));
        jTable_HoaDon.setRowHeight(40);
        jTable_HoaDon.setSelectionBackground(new java.awt.Color(153, 204, 0));
        jTable_HoaDon.setSelectionForeground(new java.awt.Color(51, 51, 51)); jTable_HoaDon.setShowGrid(true);
        jScrollPane1.setViewportView(jTable_HoaDon);
        themDuLieuVaoBang();
        if (jTable_HoaDon.getColumnModel().getColumnCount() >= 0) {

            jTable_HoaDon.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTable_HoaDon.getColumnModel().getColumn(2).setPreferredWidth(150);
            jTable_HoaDon.getColumnModel().getColumn(3).setPreferredWidth(150);
            jTable_HoaDon.getColumnModel().getColumn(6).setPreferredWidth(150);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(28, 190, 1480, 410);

        jButton_XuatExcel.setBackground(new java.awt.Color(102, 102, 0));
        jButton_XuatExcel.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_XuatExcel.setForeground(new java.awt.Color(255, 255, 255));
        jButton_XuatExcel.setText("Xuất dữ liệu");
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

    private void jDateChooser_NgayTaoDonMouseListener(PropertyChangeEvent evt) {
        int soThuTu = 0;
        DateTimeFormatter formatterNgay  = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Lấy ngày từ jDateChooser và chuyển đổi sang LocalDate
        Date selectedDate = jDateChooser_NgayTaoDon.getDate();
        if (selectedDate != null) {
            LocalDate date = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            // Xóa các hàng cũ trong bảng trước khi thêm dữ liệu mới
            DefaultTableModel model = (DefaultTableModel) jTable_HoaDon.getModel();
            model.setRowCount(0);

            // Duyệt qua danh sách hóa đơn và kiểm tra ngày
            for (HoaDon hd : dsHD.getAllHoaDon()) {
                if (date.isEqual(hd.getNgayTaoDon())) {
                    soThuTu++;
                    double tongTien = 0;
                    int tongSoLuong = 0;

                    // Tính tổng số lượng và tổng tiền của các chi tiết hóa đơn
                    for (ChiTietHoaDon cthd : listCTHD) {
                        if (hd.getMaHoaDon().equalsIgnoreCase(cthd.getHoaDon().getMaHoaDon())) {
                            tongSoLuong += cthd.getSoLuong();
                            tongTien += cthd.getSoLuong() * cthd.getDonGia();
                        }
                    }

                    // Định dạng tổng tiền
                    DecimalFormat df = new DecimalFormat("#,### VND");
                    String formattedTongTien = df.format(tongTien);

                    // Thêm dòng mới vào bảng
                    model.addRow(new Object[]{
                            soThuTu,
                            hd.getMaHoaDon(),
                            formatterNgay .format(hd.getNgayTaoDon()), // Định dạng ngày theo kiểu dd-MM-yyyy
                            hd.getNhanVien().getMaNV(),
                            tongSoLuong,
                            formattedTongTien
                    });
                }
            }
        } else {
            System.out.println("Ngày không hợp lệ hoặc không được chọn.");
        }
        jTable_HoaDon.setPreferredSize(new java.awt.Dimension(525, jTable_HoaDon.getRowCount()*40));

    }


    private void jButton_TimKiemActionPerformed(ActionEvent evt) {
    }


    private void jComboBox_TieuChiActionPerformed(ActionEvent evt) {
       try {
           String tieuChi = jComboBox_TieuChi.getSelectedItem().toString();
           if (tieuChi.equalsIgnoreCase("Mã nhân viên")) {
               jDateChooser_NgayTaoDon.setVisible(false);
               jComboBox_TimKiem.setVisible(true);
               jComboBox_TimKiem.removeAllItems();
               jComboBox_TimKiem.addItem("");
               themDuLieuVaoBang();
               for (String x : dsHD.getAllMaNV()) {
                   jComboBox_TimKiem.addItem(x);
               }
           } else if (tieuChi.equalsIgnoreCase("Mã hóa đơn")) {
               jDateChooser_NgayTaoDon.setVisible(false);
               jComboBox_TimKiem.setVisible(true);
               jComboBox_TimKiem.removeAllItems();
               jComboBox_TimKiem.addItem("");
               themDuLieuVaoBang();
               for(HoaDon hd:listHD){
                   jComboBox_TimKiem.addItem(hd.getMaHoaDon());
               }
           }
           else if(tieuChi.equalsIgnoreCase("Ngày tạo đơn")){
               jComboBox_TimKiem.setVisible(false);// NOI18N
               jDateChooser_NgayTaoDon.setVisible(true);
               themDuLieuVaoBang();
           }
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    private void jButton_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LamMoiActionPerformed
        // TODO add your handling code here:

        DefaultTableModel model = (DefaultTableModel) jTable_HoaDon.getModel();
        model.setRowCount(0);

        themDuLieuVaoBang();

    }//GEN-LAST:event_jButton_LamMoiActionPerformed

    private void jButton_XoaNhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_XoaNhieuActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton_XoaNhieuActionPerformed

    private void jComboBox_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_TimKiemActionPerformed
        // TODO add your handling code here:
        int n=jComboBox_TieuChi.getSelectedIndex();

        try {
            int soThuTu = 0;
            String textTim=(String)jComboBox_TimKiem.getSelectedItem();
            try {
                if(n==0){
                    danhSachTimKiem(soThuTu, textTim,n);
                }else if(n==1){
                    danhSachTimKiem(soThuTu, textTim,n);
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //GEN-LAST:event_jButton_TimKiemActionPerformed
    public void danhSachTimKiem(int soThuTu, String textTim,int n){
        DefaultTableModel model = (DefaultTableModel) jTable_HoaDon.getModel();
        model.setRowCount(0);
        DateTimeFormatter formatterNgay =DateTimeFormatter.ofPattern("dd-MM-YYYY");
        if(textTim==null){
            return;
        }
        try {
            for(HoaDon hd: dsHD.getAllHoaDon()){
                if(textTim.equalsIgnoreCase(hd.getMaHoaDon()) && n==0){
                    soThuTu++;
                    double tongTien = 0;
                    int tongSoLuong = 0;
                    for (ChiTietHoaDon cthd : listCTHD) {
                        if (hd.getMaHoaDon().equalsIgnoreCase(cthd.getHoaDon().getMaHoaDon())) {

                            tongSoLuong += cthd.getSoLuong();
                            tongTien += cthd.getSoLuong() * cthd.getDonGia();
                        }
                    }
                    DecimalFormat df = new DecimalFormat("#,### VND");
                    // Giả sử đây là số bạn muốn định dạng
                    String formattedTongTien = df.format(tongTien);
                    ((DefaultTableModel) jTable_HoaDon.getModel()).addRow(new Object[]{soThuTu, hd.getMaHoaDon(),
                            formatterNgay .format(hd.getNgayTaoDon()) , hd.getNhanVien().getMaNV(), tongSoLuong, formattedTongTien});
                }else if(textTim.equalsIgnoreCase(hd.getNhanVien().getMaNV()) && n==1){
                    soThuTu++;
                    double tongTien = 0;
                    int tongSoLuong = 0;
                    for (ChiTietHoaDon cthd : listCTHD) {
                        if (hd.getMaHoaDon().equalsIgnoreCase(cthd.getHoaDon().getMaHoaDon())) {

                            tongSoLuong += cthd.getSoLuong();
                            tongTien += cthd.getSoLuong() * cthd.getDonGia();
                        }
                    }
                    DecimalFormat df = new DecimalFormat("#,### VND");
                    // Giả sử đây là số bạn muốn định dạng
                    String formattedTongTien = df.format(tongTien);
                    ((DefaultTableModel) jTable_HoaDon.getModel()).addRow(new Object[]{soThuTu, hd.getMaHoaDon(),
                            formatterNgay .format(hd.getNgayTaoDon()), hd.getNhanVien().getMaNV(), tongSoLuong, formattedTongTien});
                }
                jTable_HoaDon.setPreferredSize(new java.awt.Dimension(525, jTable_HoaDon.getRowCount()*40));

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void jButton_XuatExcelActionPerformed(java.awt.event.ActionEvent evt) {
        HinhThucXuat hinhThucXuat=new HinhThucXuat(jTable_HoaDon);
        hinhThucXuat.setLocationRelativeTo(null);
        hinhThucXuat.setVisible(true);
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
//GEN-LAST:event_jButton_XuatExcelActionPerformed
//GEN-LAST:event_jButton_XuatExcelActionPerformed



    private void jButton_TaoHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_TaoHoaDonMouseClicked
        // TODO add your handling code here:
        jPanel1.removeAll();
        TrangTaoHoaDon_GUI taoHoaDonGui = new TrangTaoHoaDon_GUI();
        taoHoaDonGui.setSize(jPanel1.getSize());
        taoHoaDonGui.setVisible(true);
        jPanel1.add(taoHoaDonGui);

    }//GEN-LAST:event_jButton_TaoHoaDonMouseClicked
    public  void themDuLieuVaoBang(){
        int soThuTu = 0;
        for (HoaDon x : dsHD.getAllHoaDon()) {
            soThuTu++;
            double tongTien = 0;
            int tongSoLuong = 0;
            for (ChiTietHoaDon cthd : listCTHD) {
                if (x.getMaHoaDon().equalsIgnoreCase(cthd.getHoaDon().getMaHoaDon())) {
                    tongSoLuong += cthd.getSoLuong();
                    tongTien += cthd.getSoLuong() * cthd.getDonGia();
                }
            }
            DecimalFormat df = new DecimalFormat("#,### VND");
            DateTimeFormatter formatterNgay = DateTimeFormatter.ofPattern("dd-MM-YYYY");
            // Giả sử đây là số bạn muốn định dạng
            String formattedTongTien = df.format(tongTien);
            ((DefaultTableModel) jTable_HoaDon.getModel()).addRow(new Object[]{soThuTu, x.getMaHoaDon(),formatterNgay.format(x.getNgayTaoDon()) , x.getNhanVien().getMaNV(), tongSoLuong, formattedTongTien});
        }
        jTable_HoaDon.setPreferredSize(new java.awt.Dimension(525, jTable_HoaDon.getRowCount()*40));

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private HoaDon_DAO dsHD = new HoaDon_DAO();
    private List<HoaDon> listHD = dsHD.getAllHoaDon();
    private Sach_DAO dss = new Sach_DAO();
    private List<Sach> sachList = dss.getAllSP();

    private ChiTietHoaDon_DAO dsCTHD = new ChiTietHoaDon_DAO();
    private List<ChiTietHoaDon> listCTHD = dsCTHD.getAllChiTietHoaDon();
    private com.toedter.calendar.JDateChooser jDateChooser_NgayTaoDon;
    private javax.swing.JButton jButton_LamMoi;
    private javax.swing.JButton jButton_TaoHoaDon;
    private javax.swing.JButton jButton_TimKiem;
    private javax.swing.JButton jButton_XoaNhieu;
    private javax.swing.JButton jButton_XuatExcel;
    private javax.swing.JComboBox<String> jComboBox_TieuChi;
    private javax.swing.JComboBox<String> jComboBox_TimKiem;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_QuanLyHoaDon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_HoaDon;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import dao.*;
import entity.*;
import entity.NhanVien;
import function.NhapTienKhachTra;
import function.PhieuHoaDon;

import java.awt.*;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;


/**
 *
 * @author Ngọc Hải
 */
public class NhanVien_TrangTaoHoaDon_GUI extends javax.swing.JInternalFrame {
//



    /**
     * Creates new form TrangTaoHoaDon_GUI
     */
    public NhanVien_TrangTaoHoaDon_GUI() {
        initComponents();
        SwingUtilities.invokeLater(() -> {
            this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
            ui.setNorthPane(null);


        });
        if (jTable_DonHang.getColumnModel().getColumnCount() >= 0) {

            jTable_DonHang.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTable_DonHang.getColumnModel().getColumn(1).setPreferredWidth(300);
            jTable_DonHang.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTable_DonHang.getColumnModel().getColumn(3).setPreferredWidth(50);
            jTable_DonHang.getColumnModel().getColumn(5).setPreferredWidth(150);
        }
        jTextField_LoaiSach.setEnabled(false);
        jTextField_TenSach.setEnabled(false);
        df = new DecimalFormat("#,###");
        jTable_DonHang.setPreferredSize(new java.awt.Dimension(1612, 270));
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
        jTextField_TenSach = new javax.swing.JTextField();
        jButton_XoaRong = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_DonHang = new javax.swing.JTable();
        jButton_ThemHoaDon = new javax.swing.JButton();
        jButton_HuyDonHang = new javax.swing.JButton();
        jTextField_LoaiSach = new javax.swing.JTextField();
        jLabel_LoaiSach = new javax.swing.JLabel();
        jLabel_TenSach = new javax.swing.JLabel();
        jButton_Xoa = new javax.swing.JButton();
        jButton_ThemSach = new javax.swing.JButton();
        jTextField_ISBN = new javax.swing.JTextField();
        jLabel_ISBN = new javax.swing.JLabel();
        jLabel_SoLuong = new javax.swing.JLabel();
        jTextField_SoLuong = new javax.swing.JTextField();
        jLabel_TongTien = new javax.swing.JLabel();
        jLabel_ChiTietDonHang = new javax.swing.JLabel();
        jLabel_TongTienHoaDon = new javax.swing.JLabel();
        jButton_QuetMa = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1612, 733));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1612, 733));
        jPanel1.setLayout(null);

        jTextField_TenSach.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jPanel1.add(jTextField_TenSach);
        jTextField_TenSach.setBounds(1070, 50, 430, 40);

        jButton_XoaRong.setBackground(new java.awt.Color(215, 36, 36));
        jButton_XoaRong.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_XoaRong.setForeground(new java.awt.Color(255, 255, 255));
        jButton_XoaRong.setText("Xóa rỗng");
        jButton_XoaRong.setPreferredSize(new java.awt.Dimension(200, 60));
        jButton_XoaRong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_XoaRongActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_XoaRong);
        jButton_XoaRong.setBounds(1370, 180, 140, 40);

        jTable_DonHang.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jTable_DonHang.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "STT", "Tên sách", "Loại sách", "Số lượng", "Đơn giá", "Thành tiền"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false,false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                        return canEdit [columnIndex];
            }
        }
        );
        jTable_DonHang.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTable_DonHang.setPreferredSize(new java.awt.Dimension(1612, 733));
        jTable_DonHang.setRowHeight(40);
        jTable_DonHang.setSelectionBackground(new java.awt.Color(153, 204, 0));
        jTable_DonHang.setSelectionForeground(new java.awt.Color(51, 51, 51));
        jTable_DonHang.setShowGrid(true);

        jTable_DonHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_DonHangMouseClicked(evt);
            }
        });
        JTableHeader header =  jTable_DonHang.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 18));
        jScrollPane1.setViewportView(jTable_DonHang);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(20, 270, 1490, 306);

        jButton_ThemHoaDon.setBackground(new java.awt.Color(102, 102, 0));
        jButton_ThemHoaDon.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_ThemHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        jButton_ThemHoaDon.setText("Tạo hóa đơn");
        jButton_ThemHoaDon.setPreferredSize(new java.awt.Dimension(200, 60));
        jButton_ThemHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton_ThemHoaDonActionPerformed(evt);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        jPanel1.add(jButton_ThemHoaDon);
        jButton_ThemHoaDon.setBounds(1010, 630, 160, 46);

        jButton_HuyDonHang.setBackground(new java.awt.Color(215, 36, 36));
        jButton_HuyDonHang.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_HuyDonHang.setForeground(new java.awt.Color(255, 255, 255));
        jButton_HuyDonHang.setText("Hủy đơn hàng");
        jButton_HuyDonHang.setPreferredSize(new java.awt.Dimension(200, 60));
        jButton_HuyDonHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_HuyDonHangActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_HuyDonHang);
        jButton_HuyDonHang.setBounds(1180, 630, 170, 46);

        jTextField_LoaiSach.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jPanel1.add(jTextField_LoaiSach);
        jTextField_LoaiSach.setBounds(1070, 120, 430, 40);

        jLabel_LoaiSach.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_LoaiSach.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_LoaiSach.setText("Loại sách:");
        jPanel1.add(jLabel_LoaiSach);
        jLabel_LoaiSach.setBounds(970, 110, 100, 47);

        jLabel_TenSach.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_TenSach.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_TenSach.setText("Tên sách:");
        jPanel1.add(jLabel_TenSach);
        jLabel_TenSach.setBounds(970, 50, 100, 47);

        jButton_Xoa.setBackground(new java.awt.Color(215, 36, 36));
        jButton_Xoa.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_Xoa.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Xoa.setText("Xóa");
        jButton_Xoa.setPreferredSize(new java.awt.Dimension(200, 60));
        jButton_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_XoaActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_Xoa);
        jButton_Xoa.setBounds(1360, 630, 148, 46);

        jButton_ThemSach.setBackground(new java.awt.Color(102, 102, 0));
        jButton_ThemSach.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_ThemSach.setForeground(new java.awt.Color(255, 255, 255));
        jButton_ThemSach.setText("Thêm sách");
        jButton_ThemSach.setPreferredSize(new java.awt.Dimension(200, 60));
        jButton_ThemSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ThemSachActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_ThemSach);
        jButton_ThemSach.setBounds(1220, 180, 140, 40);
        jTextField_ISBN.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jTextField_ISBN.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                laySachtuMa();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                laySachtuMa();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                laySachtuMa();
            }
        });
        jTextField_ISBN.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_ISBNFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_ISBNFocusLost(evt);
            }
        });
        jTextField_ISBN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_ISBNMouseClicked(evt);
            }
        });
        jPanel1.add(jTextField_ISBN);
        jTextField_ISBN.setBounds(250, 70, 340, 40);

        jLabel_ISBN.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_ISBN.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_ISBN.setText("ISBN:");
        jPanel1.add(jLabel_ISBN);
        jLabel_ISBN.setBounds(176, 71, 60, 47);

        jLabel_SoLuong.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_SoLuong.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_SoLuong.setText("Số lượng:");
        jPanel1.add(jLabel_SoLuong);
        jLabel_SoLuong.setBounds(139, 128, 100, 47);

        jTextField_SoLuong.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jTextField_SoLuong.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_SoLuongFocusGained(evt);
            }
        });
        jPanel1.add(jTextField_SoLuong);
        jTextField_SoLuong.setBounds(250, 130, 340, 40);

        jLabel_TongTien.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_TongTien.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_TongTien.setText("Tổng tiền:");
        jPanel1.add(jLabel_TongTien);
        jLabel_TongTien.setBounds(1150, 580, 100, 30);

        jLabel_ChiTietDonHang.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel_ChiTietDonHang.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_ChiTietDonHang.setText("Chi tiết đơn hàng");
        jPanel1.add(jLabel_ChiTietDonHang);
        jLabel_ChiTietDonHang.setBounds(50, 230, 200, 28);

        jLabel_TongTienHoaDon.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_TongTienHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_TongTienHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_TongTienHoaDon.setText("0 VND");
        jPanel1.add(jLabel_TongTienHoaDon);
        jLabel_TongTienHoaDon.setBounds(1310, 580, 190, 30);

        jButton_QuetMa.setBackground(new java.awt.Color(102, 102, 0));
        jButton_QuetMa.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_QuetMa.setForeground(new java.awt.Color(255, 255, 255));
        jButton_QuetMa.setText("Quét mã");
        jPanel1.add(jButton_QuetMa);
        jButton_QuetMa.setBounds(1090, 180, 120, 40);
        jButton_QuetMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_QuetMaActionPerformed(evt);
            }
        });
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/anhnen.jpg"))); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(1612, 733));
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 0, 1612, 733);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();

    }// </editor-fold>
    private  void jTable_DonHangMouseClicked(java.awt.event.MouseEvent evt){
        int n=jTable_DonHang.getSelectedRow();
        String tenSach=(String)jTable_DonHang.getValueAt(n,1);
        int soLuong=(int)jTable_DonHang.getValueAt(n,3);
        for(Sach s: sachDao.getAllSP()){
            if(s.getTenSach().equalsIgnoreCase(tenSach)){
                jTextField_ISBN.setText(s.getISBN());
            }
        }
        jTextField_TenSach.setText(tenSach);
        jTextField_LoaiSach.setText((String)jTable_DonHang.getValueAt(n,2));
        jTextField_SoLuong.setText(""+soLuong);
    }
    private void jButton_ThemHoaDonActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {//GEN-FIRST:event_jButton_ThemHoaDonActionPerformed

        // BasicConfigurator.configure();
        int index_hoaDon=hoaDon_dao.getAllHoaDon().size()-1;
        String maHoaDon=taoMaHoaDonTuDongTang(hoaDon_dao.getAllHoaDon().get(index_hoaDon).getMaHoaDon());
        if(dsCTHDTemp.size()==0){
            jTextField_ISBN.requestFocus();
            JOptionPane.showMessageDialog(this, "Vui lòng thêm sản phẩm vào đơn hàng");
            return;
        }else{
            ChiTietKhoHang_DAO ctkh_dao=new ChiTietKhoHang_DAO();

            NhapTienKhachTra tkt = new NhapTienKhachTra((Frame) SwingUtilities.getWindowAncestor(this),tongTienHoaDon,maHoaDon);
            tkt.setVisible(true);

            double tienKhachTra = tkt.getTienKhachTra();
            boolean kiemTra=tkt.getKiemtra();
            boolean hinhThucThanhToan=tkt.getHinhThucThanhToan();
            int soLuong=0;
            if(kiemTra){
                jTextField_ISBN.requestFocus();
                if(hinhThucThanhToan){
                    PhieuHoaDon phieuHoaDon=new PhieuHoaDon();
                    phieuHoaDon.viewReport(dsCTHDTemp,maHoaDon,tienKhachTra);

                }else if(!hinhThucThanhToan){
                    PhieuHoaDon phieuHoaDon=new PhieuHoaDon();
                    phieuHoaDon.viewReport(dsCTHDTemp,maHoaDon,tongTienHoaDon);
                }
                hoaDon_dao.create(new HoaDon(maHoaDon, LocalDate.now(),new NhanVien(DangNhap.ma)));
                for (ChiTietHoaDon cthd: dsCTHDTemp){

                    for (ChiTietKhoHang ctkh: ctkh_dao.getAllChiTietKhoHang()){
                        if (cthd.getSach().getISBN().equalsIgnoreCase(ctkh.getSach().getISBN())){
                            soLuong=ctkh.getSoLuong()-cthd.getSoLuong();
                            ctkh_dao.capNhatChiTietKhoHang(cthd.getSach().getISBN(),ctkh.getKhoHang().getMaKhoHang(),soLuong);
                        }
                    }
                    for(Sach s:dsSach){
                        if(cthd.getSach().getISBN().equalsIgnoreCase(s.getISBN())){
                            sachDao.capNhatSoLuongSachTon(s.getISBN(),s.getSoLuong()-cthd.getSoLuong());
                        }
                    }
                    chiTietHoaDonDao.create(cthd);
                }
                model= (DefaultTableModel) jTable_DonHang.getModel();
                model.setRowCount(0);
                xoaRong();
            }else{
                return;
            }

        }
    }//GEN-LAST:event_jButton_ThemHoaDonActionPerformed

    private void jButton_HuyDonHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_HuyDonHangActionPerformed
        // TODO add your handling code here:

        int thongBao=JOptionPane.showConfirmDialog(this,"Bạn chắc chắn hủy đơn hàng","Cảnh báo",JOptionPane.WARNING_MESSAGE,JOptionPane.YES_NO_OPTION);
        if(thongBao==0){
            model= (DefaultTableModel) jTable_DonHang.getModel();
            model.setRowCount(0);
            dsCTHDTemp.clear();
            jTextField_ISBN.requestFocus();
            xoaRong();
        }
    }//GEN-LAST:event_jButton_HuyDonHangActionPerformed

    private void jButton_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_XoaActionPerformed
        // TODO add your handling code here:
        model= (DefaultTableModel) jTable_DonHang.getModel();
        int n=jTable_DonHang.getSelectedRow();
        if(n<0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng xóa!!");
        }else {
            int thongBao=JOptionPane.showConfirmDialog(this,"Bạn chắc chắn muốn xóa","Cảnh báo",JOptionPane.WARNING_MESSAGE,JOptionPane.YES_NO_OPTION);
            if(thongBao==0){
                String tenSach=(String)jTable_DonHang.getValueAt(n, 1);
                String maSach="";
                for (Sach s:dsSach){
                    if(s.getTenSach().equalsIgnoreCase(tenSach))
                        maSach=s.getISBN();
                }
                String maSachCuoi = maSach;
                for (int i = n; i < dsCTHDTemp.size(); i++) {
                    String part1 = "CTHD";
                    int part2 = Integer.parseInt(dsCTHDTemp.get(i).getMaChiTietHoaDon().substring(4))- 1;
                    String maCTHD = part1 + String.format("%05d", part2);
                    dsCTHDTemp.get(i).setMaChiTietHoaDon(maCTHD);
                }
                tongTienHoaDon=tongTienHoaDon-(dsCTHDTemp.get(n).getSoLuong()*dsCTHDTemp.get(n).getDonGia());
                jLabel_TongTienHoaDon.setText(df.format(tongTienHoaDon)+" VND");
                dsCTHDTemp.removeIf(cthd-> (cthd.getSach().getISBN().equalsIgnoreCase(maSachCuoi)));
                model.removeRow(n);
                for(int i=n;i<model.getRowCount();i++){
                    jTable_DonHang.setValueAt(i+1,i,0);
                }
                JOptionPane.showMessageDialog(this, "Xóa thành công");
            }
        }
    }//GEN-LAST:event_jButton_XoaActionPerformed

    private void jButton_ThemSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ThemSachActionPerformed
        // TODO add your handling code here:
        String ISBN=jTextField_ISBN.getText();
        String tenSach=jTextField_TenSach.getText();
        String loaiSach=jTextField_LoaiSach.getText();
        boolean kiemTra=false;
        if (ISBN.trim().isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "ISBN không được rỗng", "Lỗi", JOptionPane.WARNING_MESSAGE);
            jTextField_ISBN.requestFocus();
            return;
        }else {
            for(Sach s: sachDao.getAllSP()){
                if(s.getISBN().equalsIgnoreCase(ISBN)){
                        kiemTra=true;
                }
            }
        }
        if(kiemTra){
            if(jTextField_SoLuong.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(this,
                        "Số lượng không được rỗng", "Lỗi", JOptionPane.ERROR_MESSAGE);
                jTextField_SoLuong.requestFocus();
                return;
            }else{
                try {
                    Sach sach=new Sach();
                    for (Sach s: sachDao.getAllSP()){
                        if(ISBN.equals(s.getISBN())){
                            sach=s;
                        }
                    }
                    int soLuongTon = sach.getSoLuong();
                    int soLuongMua = Integer.parseInt(jTextField_SoLuong.getText());
                    if (soLuongMua <= 0) {
                        JOptionPane.showMessageDialog(this,
                                "Số lượng phải > 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        jTextField_SoLuong.requestFocus();
                        return;
                    } else if (soLuongTon < soLuongMua) {
                        JOptionPane.showMessageDialog(this,
                                "Không đủ số lượng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        jTextField_SoLuong.requestFocus();
                        return;
                    }else {
                        model= (DefaultTableModel) jTable_DonHang.getModel();
                        boolean timKiem=timSachTrongDonHang(ISBN);
                        if(timKiem){
                            for (int i = 0; i < dsCTHDTemp.size(); i++) {
                                if (dsCTHDTemp.get(i).getSach().getISBN().equalsIgnoreCase(ISBN)) {
                                    int soLuongMoi = dsCTHDTemp.get(i).getSoLuong() + soLuongMua;
                                    if (soLuongTon < soLuongMoi) {
                                        JOptionPane.showMessageDialog(this,
                                                "Không đủ số lượng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                                        jTextField_SoLuong.requestFocus();
                                        return;
                                    }
                                    tongTienHoaDon=tongTienHoaDon-(dsCTHDTemp.get(i).getSoLuong()*dsCTHDTemp.get(i).getDonGia());

                                    // Cập nhật lại dsCTHDTemp
                                    dsCTHDTemp.get(i).setSoLuong(soLuongMoi);
                                    // Nếu sản phẩm đã tồn tại, tăng số lượng và cập nhật thành tiền
                                    double thanhTien = dsCTHDTemp.get(i).getDonGia() * soLuongMoi;
                                    tongTienHoaDon+=thanhTien;
                                    // Cập nhật vào bảng
                                    model.setValueAt(soLuongMoi, i, 3);
                                    model.setValueAt(df.format(thanhTien), i, 5);

                                    jLabel_TongTienHoaDon.setText(df.format(tongTienHoaDon)+" VND");
                                    return;
                                }
                            }
                        }else {
                            soThuTu=model.getRowCount()+1;
                            int soLuong=Integer.parseInt(jTextField_SoLuong.getText()) ;
                            double thanhTien=sach.getGiaGoc()*soLuong*LOI_NHUAN;
                            String maHoaDon=taoMaHoaDonTuDongTang(hoaDon_dao.getAllHoaDon().get(hoaDon_dao.getAllHoaDon().size()-1).getMaHoaDon());
                            int indexCTHD=chiTietHoaDonDao.getAllChiTietHoaDon().size()-1;
                            String maCTHD=taoMaChiTietHoaDonTuDong(indexCTHD);
                            model.addRow(new Object[]{
                                    soThuTu,tenSach,loaiSach,soLuong,df.format(sach.getGiaGoc()*LOI_NHUAN),df.format(thanhTien)
                            });
                            dsCTHDTemp.add(new ChiTietHoaDon(maCTHD,new HoaDon(maHoaDon),new Sach(ISBN),soLuong,sach.getGiaGoc()*LOI_NHUAN));
                            tongTienHoaDon+=thanhTien;
                            jLabel_TongTienHoaDon.setText(df.format(tongTienHoaDon)+" VND");
                        }
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this,
                            "Số lượng phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    jTextField_SoLuong.requestFocus();
                    return;
                }

            }
        }else{
            JOptionPane.showMessageDialog(this,
                    "ISBN không đúng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            jTextField_ISBN.requestFocus();
            jTextField_ISBN.selectAll();
            return;
        }
        int n=jTable_DonHang.getSelectedRow();
        if(n>6){
            jTable_DonHang.setPreferredSize(new java.awt.Dimension(1612, 270+(n-6)*40));
        }


    }//GEN-LAST:event_jButton_ThemSachActionPerformed

    private void xoaRong(){
        dsCTHDTemp.clear();
        jTextField_ISBN.setText("");
        jTextField_TenSach.setText("");
        jTextField_LoaiSach.setText("");
        jTextField_SoLuong.setText("");
        jLabel_TongTienHoaDon.setText(df.format(tongTienHoaDon=0));
    }

    private void jButton_XoaRongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_XoaRongActionPerformed
        // TODO add your handling code here:
        jTextField_ISBN.setText("");
        jTextField_TenSach.setText("");
        jTextField_LoaiSach.setText("");
        jTextField_SoLuong.setText("");
        jTextField_ISBN.requestFocus();

    }//GEN-LAST:event_jButton_XoaRongActionPerformed

    private void jTextField_ISBNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_ISBNMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_ISBNMouseClicked

    private void jTextField_ISBNFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_ISBNFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_ISBNFocusGained

    private void jTextField_ISBNFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_ISBNFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_ISBNFocusLost

    private void jTextField_SoLuongFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_SoLuongFocusGained
        // TODO add your handling code here:
            laySachtuMa();
    }


    private void jButton_QuetMaActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
        Thread scannerThread = new Thread(() -> {
            Sach_DAO dsS=new Sach_DAO();
            Webcam webcam = Webcam.getWebcams().get(0);
            if (webcam == null) {
                System.out.println("Không tìm thấy webcam.");
                return;
            }
            webcam.setViewSize(new Dimension(320, 240));
            JFrame window=taoWebcamWindow(webcam);
            window.setVisible(true);
            while (true) {
                try {
                    BufferedImage image = webcam.getImage();  // Lấy hình ảnh từ webcam
                    String barcodeText = decodeBarcode(image);
                    if (barcodeText != null) {
                        for(Sach s: dsS.getAllSP()){
                            if(barcodeText.equalsIgnoreCase(s.getISBN())){
                                jTextField_ISBN.setText(s.getISBN());
                                jTextField_TenSach.setText(s.getTenSach());
                                jTextField_LoaiSach.setText(s.getLoaiSach().getTenLoai());
                                jTextField_SoLuong.setText("1");
                                webcam.close();
                                window.setVisible(false);
                            }
                        }
                    }
                    //      jLabel_TongTienHoaDon.setText(df.format(tongTienHoaDon));
                }catch (NullPointerException  e){
                    break;
                }
            }
        });
        scannerThread.start();
    }
    private void laySachtuMa(){
        String ISBN=jTextField_ISBN.getText();
        if(jTextField_ISBN.getText()!=null && jTextField_SoLuong.isRequestFocusEnabled()){
            Sach_DAO sachDao=new Sach_DAO();
            List<Sach> dss= sachDao.getAllSP();
            for (Sach s: dss){
                if(ISBN.equals(s.getISBN())){
                    jTextField_TenSach.setText(s.getTenSach());
                    jTextField_LoaiSach.setText(s.getLoaiSach().getTenLoai());
                }
            }
        }
    }
    private String taoMaChiTietHoaDonTuDong(int index){
        String part1 = chiTietHoaDonDao.getAllChiTietHoaDon().get(index).getMaChiTietHoaDon().substring(0, 4);  // Lấy 4 ký tự đầu tiên
        int part2 = Integer.parseInt(chiTietHoaDonDao.getAllChiTietHoaDon().get(index).getMaChiTietHoaDon().substring(5))+soThuTu;
        String maCTHD = part1 + String.format("%05d", part2);
        return maCTHD;
    }
    public String taoMaHoaDonTuDongTang(String maHDCuoiCung) {
        String part1 = maHDCuoiCung.substring(0, 2);
        String part2 = maHDCuoiCung.substring(2);
        int number = Integer.parseInt(part2) + 1;
        String maHD = part1 + String.format("%04d", number);

        return maHD;
    }

    private static String decodeBarcode(BufferedImage image) {
        try {
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Map<DecodeHintType, Object> hints = new EnumMap<>(DecodeHintType.class);
            List<BarcodeFormat> formats = new ArrayList<>();
            formats.add(BarcodeFormat.CODE_128);
            formats.add(BarcodeFormat.EAN_13);
            formats.add(BarcodeFormat.UPC_A);
            hints.put(DecodeHintType.POSSIBLE_FORMATS, formats);
            Result result = new MultiFormatReader().decode(bitmap, hints);
            return result.getText();  // Trả về kết quả đã giải mã
        } catch (NotFoundException e) {
            return null;
        }
    }
    private boolean timSachTrongDonHang(String maSach){
        boolean timKiem=false;
        for(int i=0;i<dsCTHDTemp.size();i++){
            if(dsCTHDTemp.get(i).getSach().getISBN().equalsIgnoreCase(maSach)){
                timKiem=true;
                break;
            }else{
                timKiem=false;
            }
        }
        return timKiem;
    }
    private JFrame taoWebcamWindow(Webcam webcam) {
        JFrame window = new JFrame();
        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setMirrored(false);// Lật hình ảnh để dễ nhìn
        webcam.open();
        window.setLocation(600,95);
        window.add(panel);
        window.setResizable(true);
        window.pack();
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (webcam.isOpen()) {
                    webcam.close();  // Đóng webcam để giải phóng tài nguyên
                }
            }
        });
        return window;
    }//GEN-LAST:event_jTextField_SoLuongFocusGained


    private DefaultTableModel model;
    private int soThuTu;
    private DecimalFormat df;
    private  static final double LOI_NHUAN=1.4;
    private  double tongTienHoaDon=0;
    private JLabel jLabel_TongTien;
    private JLabel jLabel_TongTienHoaDon;
    private HoaDon_DAO hoaDon_dao=new HoaDon_DAO();

    private ChiTietHoaDon_DAO chiTietHoaDonDao=new ChiTietHoaDon_DAO();

    private ArrayList<ChiTietHoaDon> dsCTHDTemp = new ArrayList<>();
    private Sach_DAO sachDao=new Sach_DAO();
    private List<Sach> dsSach= sachDao.getAllSP();// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_QuetMa;
    private javax.swing.JButton jButton_HuyDonHang;
    private javax.swing.JButton jButton_ThemHoaDon;
    private javax.swing.JButton jButton_ThemSach;
    private javax.swing.JButton jButton_Xoa;
    private javax.swing.JButton jButton_XoaRong;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_ChiTietDonHang;
    private javax.swing.JLabel jLabel_ISBN;
    private javax.swing.JLabel jLabel_LoaiSach;
    private javax.swing.JLabel jLabel_SoLuong;
    private javax.swing.JLabel jLabel_TenSach;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_DonHang;
    private javax.swing.JTextField jTextField_ISBN;
    private javax.swing.JTextField jTextField_LoaiSach;
    private javax.swing.JTextField jTextField_SoLuong;
    private javax.swing.JTextField jTextField_TenSach;
    // End of variables declaration//GEN-END:variables
}

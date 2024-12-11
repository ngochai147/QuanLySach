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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
/**
 *
 * @author Thế Bảo
 */
public class ThuKho_QuanLyNhapXuatKho extends javax.swing.JInternalFrame {
    private PhieuXuat_DAO pxk_dao = new PhieuXuat_DAO();
    private PhieuNhap_DAO pnk_dao = new PhieuNhap_DAO();
    private NhanVien_DAO nv_dao = new NhanVien_DAO();
    private ChiTietPhieuXuatKho_DAO ctpxk_dao = new ChiTietPhieuXuatKho_DAO();
    private ChiTietPhieuNhapKho_DAO ctpnk_dao = new ChiTietPhieuNhapKho_DAO();
    private KhoHang_DAO khoHang = new KhoHang_DAO();
    private Report_PhieuXuatKho Report_PhieuXuatKho = new Report_PhieuXuatKho();
    private Report_PhieuNhapKho Report_PhieuNhapKho = new Report_PhieuNhapKho();
    ArrayList<PhieuXuatKho> listPXK = pxk_dao.getAllphieuXuatKho();
    ArrayList<PhieuNhapKho> listPNK = pnk_dao.getAllPhieuNhapKho();
    private DefaultTableModel modelXuatNhapKho;
    private ThuKho thuKho;
    private NguoiQuanLy nguoiQuanLy;
    private Color customGreen;
    /**
     * Creates new form TrangNhapKho_GUI
     */
    public ThuKho_QuanLyNhapXuatKho(JFrame jFrame) {
        if(jFrame instanceof ThuKho) {
            thuKho = (ThuKho) jFrame;
        }else {
            nguoiQuanLy = (NguoiQuanLy) jFrame;
        }

        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);

         // doc du lieu tu database SQL vao Jtable
        modelXuatNhapKho = new javax.swing.table.DefaultTableModel(
                new Object [][] {
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
        };

        DocDuLieuDatabaseVaoTable();
        tbl_QLXuatNhapKho.setModel(modelXuatNhapKho);

        cb_chonTieuChi.setSelectedIndex(0);

        ChinhMauCombobox();
        canGiua_tableHeader();
        chinhSua_table();
        chinhSua_btnView();
    }
    public ThuKho_QuanLyNhapXuatKho() {

        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);

         // doc du lieu tu database SQL vao Jtable
        modelXuatNhapKho = new javax.swing.table.DefaultTableModel(
                new Object [][] {
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
        };

        DocDuLieuDatabaseVaoTable();
        tbl_QLXuatNhapKho.setModel(modelXuatNhapKho);

        cb_chonTieuChi.setSelectedIndex(0);

        ChinhMauCombobox();
        
    }

    private void DocDuLieuDatabaseVaoTable() {
        int stt = 1;
        DateTimeFormatter dfDay = DateTimeFormatter.ofPattern("dd-MM-YYYY");
        // Đọc dữ liệu từ bảng phiếu xuất kho vào table
        ArrayList<Object[]> listChung = new ArrayList<>();
        ArrayList<PhieuXuatKho> listPXK = pxk_dao.getAllphieuXuatKho();
        ArrayList<PhieuNhapKho> listPNK = pnk_dao.getAllPhieuNhapKho();
        for(PhieuXuatKho pxk : listPXK) {
            String loaiPhieu;
            loaiPhieu = "Xuất kho"; // Nếu mã bắt đầu bằng "PXK"
            KhoHang tenKhoNhap = khoHang.getKhoHangTheoMaKho(pxk.getKhoHangNhap().getMaKhoHang());
            KhoHang tenKhoXuat = khoHang.getKhoHangTheoMaKho(pxk.getKhoHangXuat().getMaKhoHang());
            listChung.add(new Object[]{
                    1,pxk.getMaPhieuXuatKho(), pxk.getNhanVien().getMaNV(), tenKhoNhap.getTenKho(), tenKhoXuat.getTenKho(), loaiPhieu, pxk.getSoLuong(),dfDay.format(pxk.getNgayLap()), ""
            });
        }

        for(PhieuNhapKho pnk : listPNK) {
            String loaiPhieu;
            loaiPhieu = "Nhập kho"; // Nếu mã bắt đầu bằng "PXK"
            KhoHang tenKhoNhap = khoHang.getKhoHangTheoMaKho(pnk.getKhoHangNhap().getMaKhoHang());
            listChung.add(new Object[]{
                    1, pnk.getMaPhieuNhapKho(), pnk.getNhanVien().getMaNV(), tenKhoNhap.getTenKho(), "", loaiPhieu, pnk.getSoLuong(), dfDay.format(pnk.getNgayLap()), ""
            });
        }
        listChung.sort((o1, o2) -> Integer.compare((int) o2[6], (int) o1[6]));

        for (Object[] row : listChung) {
            row[0] = stt++; // Cập nhật giá trị stt
        }
        for (Object[] row : listChung) {
            modelXuatNhapKho.addRow(row);
        }
    }

    private void ChinhMauCombobox() {
        customGreen = new Color(102,102,0);
        // Chỉnh màu xanh cho combobox
        cb_chonTieuChi.setRenderer(new DefaultListCellRenderer() {
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

        jcb_danhSach.setRenderer(new DefaultListCellRenderer() {
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

    public void chinhSua_table() {
        //Căn giữa các giá trị cột STT trong table
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setVerticalAlignment(JLabel.CENTER);
        tbl_QLXuatNhapKho.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbl_QLXuatNhapKho.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbl_QLXuatNhapKho.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

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
//                JOptionPane.showMessageDialog(null, "Simple Information Message");
            }

            @Override
            public void onDelete(int row) {

            }

            @Override
            public void onView(int row) {
                int n = tbl_QLXuatNhapKho.getSelectedRow();
//                String maPXK = pxk_dao.getAllphieuXuatKho().get(n).getMaPhieuXuatKho();
                String loaiPhieu = tbl_QLXuatNhapKho.getValueAt(n, 5).toString();

                if (loaiPhieu.equalsIgnoreCase("Xuất kho")) {
                    // Lấy mã phiếu xuất kho từ bảng
                    String maPXK = tbl_QLXuatNhapKho.getValueAt(n, 1).toString(); // Cột 0 chứa mã phiếu xuất

                    ArrayList<ChiTietPhieuXuatKho> dsCTPXKtemp = new ArrayList<>();

                    for (ChiTietPhieuXuatKho ctpxk : ctpxk_dao.getDSCTPXK()) {
                        if (ctpxk.getPhieuXuatKho().getMaPhieuXuatKho().equalsIgnoreCase(maPXK)) {
                            Sach s = null;
                            boolean found = false;

                            for (Sach sach : new Sach_DAO().getAllSP()) {
                                if (sach.getISBN().equalsIgnoreCase(ctpxk.getSach().getISBN())) {
                                    s = sach;
                                    found = true;
                                    break;
                                }
                            }

                            if (!found) {
                                JOptionPane.showMessageDialog(null, "Không tìm thấy sách với ISBN: " + ctpxk.getSach().getISBN());
                                continue;
                            }

                            ctpxk.setSach(s);
                            dsCTPXKtemp.add(ctpxk);
                        }
                    }

                    if (dsCTPXKtemp.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Không có dữ liệu chi tiết phù hợp cho phiếu xuất kho!");
                        return;
                    }

                    // Hiển thị báo cáo phiếu xuất kho
                    Report_PhieuXuatKho.ViewReport_PhieuXuatKho(dsCTPXKtemp, maPXK);

                } else if (loaiPhieu.equalsIgnoreCase("Nhập kho")) {
                    // Lấy mã phiếu nhập kho từ bảng
                    String maPNK = tbl_QLXuatNhapKho.getValueAt(n, 1).toString(); // Cột 0 chứa mã phiếu nhập

                    ArrayList<ChiTietPhieuNhapKho> dsCTPNKtemp = new ArrayList<>();

                    for (ChiTietPhieuNhapKho ctpnk : ctpnk_dao.getAllChiTietPhieuNhap()) {
                        if (ctpnk.getPhieuNhapKho().getMaPhieuNhapKho().equalsIgnoreCase(maPNK)) {
                            Sach s = null;
                            boolean found = false;

                            for (Sach sach : new Sach_DAO().getAllSP()) {
                                if (sach.getISBN().equalsIgnoreCase(ctpnk.getSach().getISBN())) {
                                    s = sach;
                                    found = true;
                                    break;
                                }
                            }

                            if (!found) {
                                JOptionPane.showMessageDialog(null, "Không tìm thấy sách với ISBN: " + ctpnk.getSach().getISBN());
                                continue;
                            }

                            ctpnk.setSach(s);
                            dsCTPNKtemp.add(ctpnk);
                        }
                    }

                    if (dsCTPNKtemp.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Không có dữ liệu chi tiết phù hợp cho phiếu nhập kho!");
                        return;
                    }

                    // Hiển thị báo cáo phiếu nhập kho
                    Report_PhieuNhapKho.ViewReport_PhieuNhapKho(dsCTPNKtemp, maPNK);
                }
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
//        btn_xuatExcel = new javax.swing.JButton();
        btn_XuatKho = new javax.swing.JButton();
        cb_chonTieuChi = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_QLXuatNhapKho = new javax.swing.JTable();
        btn_lamMoi = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
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

        btn_XuatKho.setBackground(new java.awt.Color(102, 102, 0));
        btn_XuatKho.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btn_XuatKho.setForeground(new java.awt.Color(255, 255, 255));
        btn_XuatKho.setText("Xuất kho");
        btn_XuatKho.setPreferredSize(new java.awt.Dimension(120, 42));
        btn_XuatKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XuatKhoActionPerformed(evt);
            }
        });
        panel_QLXuatNhapKho.add(btn_XuatKho);
        btn_XuatKho.setBounds(40, 170, 120, 42);

        cb_chonTieuChi.setBackground(new java.awt.Color(102, 102, 0));
        cb_chonTieuChi.setFont(new java.awt.Font("Arial", Font.BOLD, 20)); // NOI18N
        cb_chonTieuChi.setForeground(new java.awt.Color(255, 255, 255));
        cb_chonTieuChi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã phiếu", "Loại phiếu", "Tên kho nhập", "Tên kho xuất", "Mã thủ kho", "Ngày lập phiếu" }));
        cb_chonTieuChi.setMinimumSize(new java.awt.Dimension(72, 30));
        cb_chonTieuChi.setPreferredSize(new java.awt.Dimension(300, 42));
        cb_chonTieuChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_chonTieuChiActionPerformed(evt);
            }
        });
        panel_QLXuatNhapKho.add(cb_chonTieuChi);
        cb_chonTieuChi.setBounds(1010, 170, 220, 42);

        tbl_QLXuatNhapKho.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tbl_QLXuatNhapKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_QLXuatNhapKho.setAutoscrolls(false);
        tbl_QLXuatNhapKho.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbl_QLXuatNhapKho.setName(""); // NOI18N
        tbl_QLXuatNhapKho.setPreferredSize(new java.awt.Dimension(600, 1000));
        tbl_QLXuatNhapKho.setRowHeight(40);
        tbl_QLXuatNhapKho.setSelectionBackground(new java.awt.Color(153, 204, 0));
        tbl_QLXuatNhapKho.setShowGrid(true);
        jScrollPane1.setViewportView(tbl_QLXuatNhapKho);
        panel_QLXuatNhapKho.add(jScrollPane1);
        jScrollPane1.setBounds(40, 230, 1470, 390);

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
        panel_QLXuatNhapKho.add(jScrollPane1);
        jScrollPane1.setBounds(25, 236, 1490, 375);


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
        btn_lamMoi.setBounds(180, 170, 120, 42);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ql_kho.png"))); // NOI18N
        panel_QLXuatNhapKho.add(jLabel1);
        jLabel1.setBounds(10, 20, 1560, 75);

        jcb_danhSach.setBackground(new java.awt.Color(102, 102, 0));
        jcb_danhSach.setFont(new java.awt.Font("Arial", Font.BOLD, 18)); // NOI18N
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
            .addComponent(panel_QLXuatNhapKho, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_lamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lamMoiActionPerformed
        modelXuatNhapKho = new DefaultTableModel(new Object[]{"STT", "Mã phiếu", "Mã thủ kho", "Tên kho nhập", "Tên kho xuất", "Loại phiếu", "Số lượng", "Ngày lập phiếu", ""}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Chỉ cho phép chỉnh sửa cột cuối cùng
                return column == getColumnCount() - 1;
            }
        };
        DocDuLieuDatabaseVaoTable();
        tbl_QLXuatNhapKho.setModel(modelXuatNhapKho);

        cb_chonTieuChi.setSelectedItem("Mã phiếu");

        canGiua_tableHeader();
        chinhSua_table();
        chinhSua_btnView();
    }

    private void btn_XuatKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XuatKhoActionPerformed
        try {
            if(thuKho != null)
                thuKho.getDesktopPanel(new ThuKho_XuatKho());
            else if(nguoiQuanLy != null)
                nguoiQuanLy.getDesktopPanel(new ThuKho_XuatKho());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        thuKho.getDesktopPanel(new ThuKho_XuatKho());
    }//GEN-LAST:event_btn_XuatKhoActionPerformed

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
                    ArrayList<PhieuNhapKho> listPNK = pnk_dao.getAllPhieuNhapKho();
                    for (PhieuXuatKho pxk : listPXK) {
                        jcb_danhSach.addItem(pxk.getMaPhieuXuatKho());
                    }
                    for (PhieuNhapKho pnk : listPNK) {
                        jcb_danhSach.addItem(pnk.getMaPhieuNhapKho());
                    }
                    break;
                case "Loại phiếu":
                    ArrayList<String> loaiPhieu = new ArrayList<>();
                    loaiPhieu.add("Xuất kho");
                    loaiPhieu.add("Nhập kho");
                    for (String loai : loaiPhieu) {
                        jcb_danhSach.addItem(loai);
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
                    HashSet<LocalDate> ngayLapSet = new HashSet<>();

                    for (PhieuXuatKho pxk : pxk_dao.getAllphieuXuatKho()) {
//                        jcb_danhSach.addItem(dfDay.format(pxk.getNgayLap()));
                        ngayLapSet.add(pxk.getNgayLap());
                    }
                    for (PhieuNhapKho pnk : pnk_dao.getAllPhieuNhapKho()) {
//                        jcb_danhSach.addItem(dfDay.format(pnk.getNgayLap()));
                        ngayLapSet.add(pnk.getNgayLap());
                    }

                    ArrayList<LocalDate> ngayLapChung = new ArrayList<>(ngayLapSet);
                    ngayLapChung.sort((date1, date2) -> date2.compareTo(date1));

                    for (LocalDate ngay : ngayLapChung) {
                        jcb_danhSach.addItem(dfDay.format(ngay));
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
        int stt = 1;
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            Object selectedItem = evt.getItem(); // Lấy mục vừa được chọn
            if (selectedItem != null && !selectedItem.toString().trim().isEmpty()) { // Kiểm tra null hoặc rỗng
//                System.out.println("Bạn đã chọn: " + selectedItem.toString());

                String textTim = selectedItem.toString();
                danhSachTimKiem(stt, textTim);

            }
        }
    }//GEN-LAST:event_jcb_danhSachItemStateChanged

    public void danhSachTimKiem(int stt, String textTim) {
        String tieuChi = cb_chonTieuChi.getSelectedItem().toString();

        modelXuatNhapKho = new DefaultTableModel(new Object[]{"STT", "Mã phiếu", "Mã thủ kho", "Tên kho nhập", "Tên kho xuất", "Loại phiếu", "Số lượng", "Ngày lập phiếu", ""}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Chỉ cho phép chỉnh sửa cột cuối cùng
                return column == getColumnCount() - 1;
            }
        };
        if (tieuChi.equalsIgnoreCase("Mã phiếu")) {
            for (PhieuXuatKho pxk : listPXK) {
                if (pxk.getMaPhieuXuatKho().contains(textTim)) {
                    String loaiPhieu;
                    loaiPhieu = "Xuất kho";
                    KhoHang tenKhoNhap = khoHang.getKhoHangTheoMaKho(pxk.getKhoHangNhap().getMaKhoHang());
                    KhoHang tenKhoXuat = khoHang.getKhoHangTheoMaKho(pxk.getKhoHangXuat().getMaKhoHang());
                    DateTimeFormatter dfDay = DateTimeFormatter.ofPattern("dd-MM-YYYY");

                    modelXuatNhapKho.addRow(new Object[]{
                            stt++,pxk.getMaPhieuXuatKho(), pxk.getNhanVien().getMaNV(), tenKhoNhap.getTenKho(), tenKhoXuat.getTenKho(), loaiPhieu, pxk.getSoLuong(), dfDay.format(pxk.getNgayLap()), ""
                    });
                }
            }

            for (PhieuNhapKho pnk : listPNK) {
                if (pnk.getMaPhieuNhapKho().contains(textTim)) {
                    String loaiPhieu;
                    loaiPhieu = "Nhập kho";
                    KhoHang tenKhoNhap = khoHang.getKhoHangTheoMaKho(pnk.getKhoHangNhap().getMaKhoHang());
                    DateTimeFormatter dfDay = DateTimeFormatter.ofPattern("dd-MM-YYYY");

                    modelXuatNhapKho.addRow(new Object[]{
                            stt++,pnk.getMaPhieuNhapKho(), pnk.getNhanVien().getMaNV(), tenKhoNhap.getTenKho(), "", loaiPhieu, pnk.getSoLuong(), dfDay.format(pnk.getNgayLap()), ""
                    });
                }
            }
        } else if (tieuChi.equalsIgnoreCase("Loại phiếu")) {
            if (textTim.equalsIgnoreCase("Xuất kho")) {
                for (PhieuXuatKho pxk : listPXK) {
                        String loaiPhieu;
                        loaiPhieu = "Xuất kho"; // Nếu mã bắt đầu bằng "PXK"
                        KhoHang tenKhoNhap = khoHang.getKhoHangTheoMaKho(pxk.getKhoHangNhap().getMaKhoHang());
                        KhoHang tenKhoXuat = khoHang.getKhoHangTheoMaKho(pxk.getKhoHangXuat().getMaKhoHang());
                        DateTimeFormatter dfDay = DateTimeFormatter.ofPattern("dd-MM-YYYY");

                        modelXuatNhapKho.addRow(new Object[]{
                                stt++, pxk.getMaPhieuXuatKho(), pxk.getNhanVien().getMaNV(), tenKhoNhap.getTenKho(), tenKhoXuat.getTenKho(), loaiPhieu, pxk.getSoLuong(), dfDay.format(pxk.getNgayLap()), ""
                        });
                }
            } else {
                for (PhieuNhapKho pnk : listPNK) {
                        String loaiPhieu;
                        loaiPhieu = "Nhập kho"; // Nếu mã bắt đầu bằng "PNK"
                        KhoHang tenKhoNhap = khoHang.getKhoHangTheoMaKho(pnk.getKhoHangNhap().getMaKhoHang());
                        DateTimeFormatter dfDay = DateTimeFormatter.ofPattern("dd-MM-YYYY");

                        modelXuatNhapKho.addRow(new Object[]{
                                stt++, pnk.getMaPhieuNhapKho(), pnk.getNhanVien().getMaNV(), tenKhoNhap.getTenKho(), "", loaiPhieu, pnk.getSoLuong(), dfDay.format(pnk.getNgayLap()), ""
                        });
                }
            }
        } else if (tieuChi.equalsIgnoreCase("Tên kho nhập")) {
            Set<String> addedPhieu = new HashSet<>(); // Lưu trữ mã phiếu để loại bỏ trùng lặp
            ArrayList<Object[]> listChung = new ArrayList<>();

            try {
                for (KhoHang kh : khoHang.getDSKhoHang()) {
                    if (kh.getTenKho().contains(textTim)) {
                        // Lặp qua danh sách phiếu xuất kho
                        for (PhieuXuatKho pxk : listPXK) {
                            if (pxk.getKhoHangNhap().getMaKhoHang().contains(kh.getMaKhoHang())) {
                                String maPhieu = pxk.getMaPhieuXuatKho();
                                if (!addedPhieu.contains(maPhieu)) {
                                    // Thêm phiếu xuất kho vào danh sách chung
                                    listChung.add(new Object[]{
                                            stt++,
                                            pxk.getMaPhieuXuatKho(),
                                            pxk.getNhanVien().getMaNV(),
                                            khoHang.getKhoHangTheoMaKho(pxk.getKhoHangNhap().getMaKhoHang()).getTenKho(),
                                            khoHang.getKhoHangTheoMaKho(pxk.getKhoHangXuat().getMaKhoHang()).getTenKho(),
                                            "Xuất kho",
                                            pxk.getSoLuong(),
                                            pxk.getNgayLap(),
                                            ""
                                    });
                                    addedPhieu.add(maPhieu);
                                }
                            }
                        }

                        // Lặp qua danh sách phiếu nhập kho
                        for (PhieuNhapKho pnk : listPNK) {
                            if (pnk.getKhoHangNhap().getMaKhoHang().contains(kh.getMaKhoHang())) {
                                String maPhieu = pnk.getMaPhieuNhapKho();
                                if (!addedPhieu.contains(maPhieu)) {
                                    // Thêm phiếu nhập kho vào danh sách chung
                                    listChung.add(new Object[]{
                                            stt++,
                                            pnk.getMaPhieuNhapKho(),
                                            pnk.getNhanVien().getMaNV(),
                                            khoHang.getKhoHangTheoMaKho(pnk.getKhoHangNhap().getMaKhoHang()).getTenKho(),
                                            "",
                                            "Nhập kho",
                                            pnk.getSoLuong(),
                                            pnk.getNgayLap(),
                                            ""
                                    });
                                    addedPhieu.add(maPhieu);
                                }
                            }
                        }
                    }
                }

                // Sắp xếp danh sách chung theo ngày lập (mới nhất -> cũ nhất)
                listChung.sort((o1, o2) -> ((LocalDate) o2[7]).compareTo((LocalDate) o1[7])); // Cột 7 chứa ngày lập

                // Thêm các phiếu đã gộp vào model để hiển thị
                for (Object[] phieu : listChung) {
                    modelXuatNhapKho.addRow(phieu);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (tieuChi.equalsIgnoreCase("Tên kho xuất")) {
            Set<String> addedPhieuXuat = new HashSet<>();
            ArrayList<Object[]> danhSachPhieu = new ArrayList<>(); // Danh sách lưu các dòng dữ liệu

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
                                    String loaiPhieu = "Xuất kho"; // Loại phiếu là "Xuất kho"

                                    // Lưu thông tin phiếu vào danh sách
                                    danhSachPhieu.add(new Object[]{
                                            stt++,
                                            pxk.getMaPhieuXuatKho(),
                                            pxk.getNhanVien().getMaNV(),
                                            tenKhoNhap.getTenKho(),
                                            tenKhoXuat.getTenKho(),
                                            loaiPhieu,
                                            pxk.getSoLuong(),
                                            pxk.getNgayLap(), // Lưu thô để sắp xếp
                                            ""
                                    });

                                    // Thêm mã phiếu vào Set để tránh trùng lặp
                                    addedPhieuXuat.add(maPhieu);
                                }
                            }
                        }
                    }
                }

                // Sắp xếp danh sách phiếu theo ngày lập (mới nhất -> cũ nhất)
                danhSachPhieu.sort((o1, o2) -> ((LocalDate) o2[7]).compareTo((LocalDate) o1[7])); // Sắp xếp dựa vào cột ngày lập

                // Thêm các dòng đã sắp xếp vào model
                for (Object[] phieu : danhSachPhieu) {
                    phieu[7] = DateTimeFormatter.ofPattern("dd-MM-YYYY").format((LocalDate) phieu[7]); // Định dạng lại ngày lập trước khi hiển thị
                    modelXuatNhapKho.addRow(phieu);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (tieuChi.equalsIgnoreCase("Mã thủ kho")) {
            Set<String> addedPhieu = new HashSet<>();
            ArrayList<Object[]> danhSachPhieu = new ArrayList<>(); // Danh sách lưu các dòng dữ liệu

            for (PhieuXuatKho pxk : listPXK) {
                // Lọc phiếu xuất kho theo mã nhân viên
                if (pxk.getNhanVien().getMaNV().contains(textTim)) {
                    String loaiPhieu = "Xuất kho"; // Loại phiếu là "Xuất kho"
                    KhoHang tenKhoNhap = khoHang.getKhoHangTheoMaKho(pxk.getKhoHangNhap().getMaKhoHang());
                    KhoHang tenKhoXuat = khoHang.getKhoHangTheoMaKho(pxk.getKhoHangXuat().getMaKhoHang());

                    // Kiểm tra mã phiếu đã được thêm chưa
                    if (!addedPhieu.contains(pxk.getMaPhieuXuatKho())) {
                        // Lưu thông tin phiếu xuất kho vào danh sách
                        danhSachPhieu.add(new Object[]{
                                stt++,
                                pxk.getMaPhieuXuatKho(),
                                pxk.getNhanVien().getMaNV(),
                                tenKhoNhap.getTenKho(),
                                tenKhoXuat.getTenKho(),
                                loaiPhieu,
                                pxk.getSoLuong(),
                                pxk.getNgayLap(), // Lưu thô để sắp xếp
                                ""
                        });

                        addedPhieu.add(pxk.getMaPhieuXuatKho()); // Thêm mã phiếu để tránh trùng lặp
                    }
                }
            }

            for (PhieuNhapKho pnk : listPNK) {
                // Lọc phiếu nhập kho theo mã nhân viên
                if (pnk.getNhanVien().getMaNV().contains(textTim)) {
                    String loaiPhieu = "Nhập kho"; // Loại phiếu là "Nhập kho"
                    KhoHang tenKhoNhap = khoHang.getKhoHangTheoMaKho(pnk.getKhoHangNhap().getMaKhoHang());

                    // Kiểm tra mã phiếu đã được thêm chưa
                    if (!addedPhieu.contains(pnk.getMaPhieuNhapKho())) {
                        // Lưu thông tin phiếu nhập kho vào danh sách
                        danhSachPhieu.add(new Object[]{
                                stt++,
                                pnk.getMaPhieuNhapKho(),
                                pnk.getNhanVien().getMaNV(),
                                tenKhoNhap.getTenKho(),
                                "",
                                loaiPhieu,
                                pnk.getSoLuong(),
                                pnk.getNgayLap(), // Lưu thô để sắp xếp
                                ""
                        });

                        addedPhieu.add(pnk.getMaPhieuNhapKho()); // Thêm mã phiếu để tránh trùng lặp
                    }
                }
            }

            // Sắp xếp danh sách phiếu theo ngày lập (mới nhất -> cũ nhất)
            danhSachPhieu.sort((o1, o2) -> ((LocalDate) o2[7]).compareTo((LocalDate) o1[7])); // Sắp xếp dựa vào cột ngày lập

            // Thêm các dòng đã sắp xếp vào model
            for (Object[] phieu : danhSachPhieu) {
                phieu[7] = DateTimeFormatter.ofPattern("dd-MM-YYYY").format((LocalDate) phieu[7]); // Định dạng lại ngày lập trước khi hiển thị
                modelXuatNhapKho.addRow(phieu);
            }

        } else if (tieuChi.equalsIgnoreCase("Ngày lập phiếu")) {
            DateTimeFormatter dfDay = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            Set<String> addedPhieu = new HashSet<>(); // Để tránh trùng lặp mã phiếu
            ArrayList<Object[]> danhSachPhieu = new ArrayList<>(); // Lưu dữ liệu phiếu xuất và nhập

            // Duyệt qua danh sách phiếu xuất kho
            for (PhieuXuatKho pxk : listPXK) {
                // Kiểm tra ngày lập của phiếu xuất kho
                if (dfDay.format(pxk.getNgayLap()).equalsIgnoreCase(textTim)) {
                    String loaiPhieu = "Xuất kho"; // Định nghĩa loại phiếu
                    KhoHang tenKhoNhap = khoHang.getKhoHangTheoMaKho(pxk.getKhoHangNhap().getMaKhoHang());
                    KhoHang tenKhoXuat = khoHang.getKhoHangTheoMaKho(pxk.getKhoHangXuat().getMaKhoHang());

                    // Kiểm tra mã phiếu để tránh trùng lặp
                    if (!addedPhieu.contains(pxk.getMaPhieuXuatKho())) {
                        danhSachPhieu.add(new Object[]{
                                stt++,
                                pxk.getMaPhieuXuatKho(),
                                pxk.getNhanVien().getMaNV(),
                                tenKhoNhap.getTenKho(),
                                tenKhoXuat.getTenKho(),
                                loaiPhieu,
                                pxk.getSoLuong(),
                                pxk.getNgayLap(), // Lưu thô để sắp xếp
                                ""
                        });
                        addedPhieu.add(pxk.getMaPhieuXuatKho()); // Thêm mã phiếu vào Set
                    }
                }
            }

            // Duyệt qua danh sách phiếu nhập kho
            for (PhieuNhapKho pnk : listPNK) {
                // Kiểm tra ngày lập của phiếu nhập kho
                if (dfDay.format(pnk.getNgayLap()).equalsIgnoreCase(textTim)) {
                    String loaiPhieu = "Nhập kho"; // Định nghĩa loại phiếu
                    KhoHang tenKhoNhap = khoHang.getKhoHangTheoMaKho(pnk.getKhoHangNhap().getMaKhoHang());

                    // Kiểm tra mã phiếu để tránh trùng lặp
                    if (!addedPhieu.contains(pnk.getMaPhieuNhapKho())) {
                        danhSachPhieu.add(new Object[]{
                                stt++,
                                pnk.getMaPhieuNhapKho(),
                                pnk.getNhanVien().getMaNV(),
                                tenKhoNhap.getTenKho(),
                                "",
                                loaiPhieu,
                                pnk.getSoLuong(),
                                pnk.getNgayLap(), // Lưu thô để sắp xếp
                                ""
                        });
                        addedPhieu.add(pnk.getMaPhieuNhapKho()); // Thêm mã phiếu vào Set
                    }
                }
            }

            // Sắp xếp danh sách phiếu theo ngày lập (mới nhất -> cũ nhất)
            danhSachPhieu.sort((o1, o2) -> ((LocalDate) o2[7]).compareTo((LocalDate) o1[7])); // Dựa vào cột ngày lập

            // Thêm các dòng đã sắp xếp vào model
            for (Object[] phieu : danhSachPhieu) {
                phieu[7] = dfDay.format((LocalDate) phieu[7]); // Định dạng lại ngày trước khi hiển thị
                modelXuatNhapKho.addRow(phieu);
            }

        }
        tbl_QLXuatNhapKho.setModel(modelXuatNhapKho);
        canGiua_tableHeader();
        chinhSua_table();
        chinhSua_btnView();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_XuatKho;
    private javax.swing.JButton btn_lamMoi;
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


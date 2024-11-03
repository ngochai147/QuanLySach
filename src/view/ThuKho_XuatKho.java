/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import dao.*;
import entity.*;

import java.awt.*;
import java.awt.event.*;

import java.sql.SQLException;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


/**
 *
 * @author Thế Bảo
 */
public class ThuKho_XuatKho extends javax.swing.JInternalFrame {
    private ArrayList<ChiTietPhieuXuatKho> dsCTPXK = new ArrayList<>();
    ChiTietPhieuXuatKho_DAO ctpx_dao = new ChiTietPhieuXuatKho_DAO();

    private Sach_DAO sach_dao = new Sach_DAO();
    private KhoHang_DAO khoHang_dao = new KhoHang_DAO();
    ChiTietKhoHang_DAO ctkh_dao = new ChiTietKhoHang_DAO();
    private PhieuXuat_DAO px_dao = new PhieuXuat_DAO();

    private static final String ma_CT_PXK = "CTPXK";
    private static final String ma_PXK = "PXK";
    private static final String ma_CTK = "CTKH";

    private boolean isUpdatingDate = false; // Cờ kiểm soát việc xóa ngày để tránh vòng lặp vô hạn
    /**
     * Creates new form TrangNhapKho
     */
    public ThuKho_XuatKho() throws SQLException {
        initComponents();
    
    // Sử dụng SwingUtilities.invokeLater để đảm bảo các thay đổi giao diện được thực hiện trên EDT
        SwingUtilities.invokeLater(() -> {
            this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
            ui.setNorthPane(null);
        });

        canGiua_tableHeader();
        chinhSua_table();
        nhapThongTin();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean isNgayLapValid = kiemTra_NgayLap();

                // Mở khóa jcb_chonSach và tf_soLuong nếu ngày lập hợp lệ
                jcb_chonSach.setEnabled(isNgayLapValid);
                tf_soLuong.setEnabled(isNgayLapValid);
                String tenKhoNhap = jcb_khoNhap.getSelectedItem().toString();
                try {
                    String ma_KN = khoHang_dao.getMaKhoTheoTenKho(tenKhoNhap);
                    hienThi_Sach(ma_KN);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    // Hàm kiểm tra nếu ngày lập phiếu lớn hơn hoặc bằng ngày hiện tại
    private boolean kiemTraNgayLapPhieu(Date ngayLapPhieu, boolean showMessage) {
        if (ngayLapPhieu == null) return false;

        // Lấy phần ngày của ngayHienTai
        Calendar calHienTai = Calendar.getInstance();
        calHienTai.set(Calendar.HOUR_OF_DAY, 0);
        calHienTai.set(Calendar.MINUTE, 0);
        calHienTai.set(Calendar.SECOND, 0);
        calHienTai.set(Calendar.MILLISECOND, 0);
        Date ngayHienTai = calHienTai.getTime();

        // Lấy phần ngày của ngayLapPhieu
        Calendar calNgayLapPhieu = Calendar.getInstance();
        calNgayLapPhieu.setTime(ngayLapPhieu);
        calNgayLapPhieu.set(Calendar.HOUR_OF_DAY, 0);
        calNgayLapPhieu.set(Calendar.MINUTE, 0);
        calNgayLapPhieu.set(Calendar.SECOND, 0);
        calNgayLapPhieu.set(Calendar.MILLISECOND, 0);
        Date ngayLapPhieuDateOnly = calNgayLapPhieu.getTime();

        // Kiểm tra ngày lập phiếu có phải là bằng hoặc sau ngày hiện tại
        boolean isValid = !ngayLapPhieuDateOnly.before(ngayHienTai);

        // Hiển thị thông báo và xử lý giao diện nếu cần
        if (!isValid && showMessage) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày lập bằng hoặc sau ngày hiện tại.");
            isUpdatingDate = true; // Đặt cờ để tránh vòng lặp khi xóa ngày
            jdc_ngayLapPX.setDate(null); // Xóa ngày lập nếu không hợp lệ
            isUpdatingDate = false; // Đặt lại cờ sau khi xóa
        }

        return isValid;
    }

    // Sử dụng trong các hàm khác
    private boolean kiemTra_NgayLap() {
        if (isUpdatingDate) {
            return true; // Bỏ qua kiểm tra khi đang cập nhật
        }

        Date selectedDate = jdc_ngayLapPX.getDate();
        return kiemTraNgayLapPhieu(selectedDate, true);
    }

    public void hienThi_Sach(String maKhoNhap) {
        try {
            jcb_chonSach.removeAllItems();
            List<Sach> ds_sach = sach_dao.getDanhSachSachTheoMaKhoHang(maKhoNhap);
            for (Sach s : ds_sach) {
                System.out.println(s);
                jcb_chonSach.addItem(s.getISBN());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Hàm điều hướng focus giữa các text field và JDateChooser
    public void nhapThongTin() {

        jcb_chonSach.setEnabled(false);
        tf_soLuong.setEnabled(false);

        jcb_khoXuat.addItem("");
        jcb_khoNhap.addItem("");
        try {
            KhoHang_DAO dao = new KhoHang_DAO();
            List<KhoHang> danhSachTenKho = dao.getDSKhoHang();

            for (KhoHang tenKho : danhSachTenKho) {

                jcb_khoNhap.addItem(tenKho.getTenKho());
                jcb_khoXuat.addItem(tenKho.getTenKho());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean kiemTraSoLuong(String isbn, String soLuongStr) {
        // Kiểm tra số lượng
        int soLuong;
        try {
            soLuong = Integer.parseInt(soLuongStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ. Vui lòng nhập một số nguyên.");
            return false; // Không phải là số nguyên
        }

        // Kiểm tra số lượng âm hoặc bằng 0
        if (soLuong <= 0) {
            JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0.");
            return false; // Số lượng âm hoặc bằng 0
        }
        return true; // Dữ liệu hợp lệ
    }

    public void chinhSua_table() {
        //Căn giữa các giá trị cột STT trong table
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        centerRenderer.setVerticalAlignment(JLabel.CENTER );
        tbl_phieuXuatKho.getColumnModel().getColumn(0).setCellRenderer( centerRenderer);

        // Thiết lập kích thước font cho các phần tử trong table
        Font font = new Font("Arial", Font.PLAIN, 18);
        tbl_phieuXuatKho.setFont(font);

        // Chỉnh kích thước cột
        tbl_phieuXuatKho.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbl_phieuXuatKho.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbl_phieuXuatKho.getColumnModel().getColumn(2).setPreferredWidth(250);
        tbl_phieuXuatKho.getColumnModel().getColumn(3).setPreferredWidth(150);
        tbl_phieuXuatKho.getColumnModel().getColumn(4).setPreferredWidth(100);
        tbl_phieuXuatKho.getColumnModel().getColumn(5).setPreferredWidth(100);
        tbl_phieuXuatKho.getColumnModel().getColumn(6).setPreferredWidth(100);
        tbl_phieuXuatKho.getColumnModel().getColumn(7).setPreferredWidth(100);
    }
    
    public void canGiua_tableHeader() {
    // Căn giữa và chỉnh kích thước table header
        Font fn = new Font("Arial", Font.BOLD, 18);
        tbl_phieuXuatKho.getTableHeader().setFont(fn);
        
        // Lấy header của bảng
        JTableHeader header = tbl_phieuXuatKho.getTableHeader();
        
        // Tạo renderer để căn giữa header
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        // Thiết lập renderer cho header
        header.setDefaultRenderer(renderer);
    }

    public String taoTuDong_MaPhieuXuatKho() {
        // Lấy mã phiếu nhập kho cuối cùng từ cơ sở dữ liệu
        String lastMaPhieuXuatKho = px_dao.getLastMaPhieuXuatKho();

        int newNumber;
        if (lastMaPhieuXuatKho != null && lastMaPhieuXuatKho.startsWith(ma_PXK)) {
            // Tách phần số ra khỏi mã và tăng lên 1
            String numberPart = lastMaPhieuXuatKho.substring(ma_PXK.length());
            newNumber = Integer.parseInt(numberPart) + 1;
        } else {
            // Nếu không có mã nào trong CSDL, bắt đầu từ 1
            newNumber = 1;
        }

        return ma_PXK + String.format("%04d", newNumber);
    }

    public String taoTuDong_MaChiTietPhieuXuatKho() {
        // Lấy mã chi tiết phiếu nhập kho mới nhất từ cơ sở dữ liệu
        String lastMaChiTietPhieuXuatKho = ctpx_dao.getLastMaChiTietPhieuXuatKho();

        int newNumber;
        if (lastMaChiTietPhieuXuatKho != null && lastMaChiTietPhieuXuatKho.startsWith(ma_CT_PXK)) {
            // Tách phần số ra khỏi mã và tăng lên 1
            String numberPart = lastMaChiTietPhieuXuatKho.substring(ma_CT_PXK.length());
            newNumber = Integer.parseInt(numberPart) + 1;
        } else {
            // Nếu không có mã nào trong CSDL, bắt đầu từ 1
            newNumber = 1;
        }

        // Định dạng lại mã với tiền tố và phần số đủ 5 chữ số
        return ma_CT_PXK + String.format("%05d", newNumber);
    }

    public String taoTuDong_MaChiTietKhoHang() {
        // Lấy mã chi tiết kho hàng cuối cùng từ cơ sở dữ liệu
        String lastMaChiTietKhoHang = ctkh_dao.getLastMaChiTietKhoHang();

        int newNumber;
        if (lastMaChiTietKhoHang != null && lastMaChiTietKhoHang.startsWith(ma_CTK)) {
            // Tách phần số ra khỏi mã và tăng lên 1
            String numberPart = lastMaChiTietKhoHang.substring(ma_CTK.length());
            newNumber = Integer.parseInt(numberPart) + 1;
        } else {
            // Nếu không có mã nào trong CSDL, bắt đầu từ 1
            newNumber = 1;
        }

        // Định dạng lại mã với tiền tố và phần số đủ 2 chữ số
        return ma_CTK + String.format("%02d", newNumber);
    }

    public void themChiTietPhieuXuatKho(DefaultTableModel model, String maPhieuXuatKho) throws SQLException {
        dsCTPXK.clear();

        for (int i = 0; i < model.getRowCount(); i++) {
            // Tạo mã chi tiết phiếu nhập kho tự động
            String maChiTietPhieuXuatKho = taoTuDong_MaChiTietPhieuXuatKho();

            // Lấy ISBN và số lượng từ bảng
            String isbn = model.getValueAt(i, 1).toString();
            int soLuong = Integer.parseInt(model.getValueAt(i, 5).toString());

            // Gọi hàm insert chi tiết phiếu nhập kho vào cơ sở dữ liệu
            boolean isInserted = ctpx_dao.insertChiTietPhieuXuatKho(maChiTietPhieuXuatKho, maPhieuXuatKho, soLuong, isbn);

            // Lấy thông tin sách từ ISBN
            Sach sach = sach_dao.getSachTheoMaSach(isbn); // Giả sử bạn có hàm lấy thông tin sách từ ISBN
            if (sach == null) {
                System.out.println("Không tìm thấy sách với ISBN: " + isbn);
                sach = new Sach(isbn, "Tên sách mặc định", "Loại sách mặc định", 0, "", 0, 0.0, null, null, ""); // Khởi tạo sách mặc định
            }

            // Tạo chi tiết phiếu nhập kho
            ChiTietPhieuXuatKho ctpxk = new ChiTietPhieuXuatKho(maChiTietPhieuXuatKho, new PhieuNhapKho(maPhieuXuatKho), soLuong, sach);
            dsCTPXK.add(ctpxk);

//            if (!isInserted) {
//                System.out.println("Không thể thêm chi tiết phiếu nhập kho cho ISBN: " + isbn);
//            } else {
//                System.out.println("Thêm chi tiết phiếu nhập kho thành công cho ISBN: " + isbn);
//            }
        }
    }

    public void themChiTietKhoHang(DefaultTableModel model, String maKhoHangNhap, String maKhoHangXuat) throws SQLException {
        for (int i = 0; i < model.getRowCount(); i++) {
            // Lấy ISBN và số lượng từ bảng
            String isbn = model.getValueAt(i, 1).toString();
            Sach sach = new Sach(isbn, "","",0,"",0,0.0, null,null,"");
//            System.out.println("Sach: " + sach.getISBN());
            int soLuong = Integer.parseInt(model.getValueAt(i, 5).toString());

            // Kiểm tra xem sách đã tồn tại trong kho nhập chưa
            ChiTietKhoHang sach_tonTai = ctkh_dao.kiemTraTonTaiISBNTrongKho(isbn, maKhoHangXuat);

            if (sach_tonTai != null) {
                // Nếu sách đã tồn tại trong kho nhập, cập nhật số lượng
                int soLuongMoi = sach_tonTai.getSoLuong() + soLuong;
                sach_tonTai.setSoLuong(soLuongMoi);
                boolean isUpdated = ctkh_dao.capNhatChiTietKhoHang(sach_tonTai.getSach().getISBN(), sach_tonTai.getKhoHang().getMaKhoHang(), soLuongMoi);

                if (!isUpdated) {
                    System.out.println("Không thể cập nhật số lượng cho ISBN: " + isbn);
                } else {
                    System.out.println("Cập nhật số lượng thành công cho ISBN: " + isbn);
                }
            } else {
                // Nếu sách chưa tồn tại, thêm chi tiết kho hàng mới
                String maChiTietKhoHang = taoTuDong_MaChiTietKhoHang();

                ChiTietKhoHang CTKH_moi = new ChiTietKhoHang(maChiTietKhoHang, soLuong, new Sach(isbn), new KhoHang(maKhoHangXuat));
                boolean isInserted = ctkh_dao.themChiTietKhoHang(CTKH_moi);

                if (!isInserted) {
                    System.out.println("Không thể thêm chi tiết kho hàng cho ISBN: " + isbn);
                } else {
                    System.out.println("Thêm chi tiết kho hàng thành công cho ISBN: " + isbn);
                }
            }
        }
    }

    public int layTongSoLuong() {
        int tongSoLuong = 0;
        DefaultTableModel model = (DefaultTableModel) tbl_phieuXuatKho.getModel();

        // Duyệt qua từng hàng và lấy giá trị từ cột số lượng (cột 5, index 4)
        for (int i = 0; i < model.getRowCount(); i++) {
            int soLuong = Integer.parseInt(model.getValueAt(i, 5).toString());
            tongSoLuong += soLuong;
        }

        return tongSoLuong;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jdc_ngayLapPX = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tf_soLuong = new javax.swing.JTextField();
        btn_them = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        btn_taoPX = new javax.swing.JButton();
        jcb_khoXuat = new javax.swing.JComboBox<>();
        jcb_chonSach = new javax.swing.JComboBox<>();
        jcb_khoNhap = new javax.swing.JComboBox<>();
        btn_huy = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_phieuXuatKho = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1612, 733));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1612, 733));
        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Thông tin");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(70, 90, 140, 36);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Từ kho");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(110, 210, 80, 30);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("(*)");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(190, 150, 30, 30);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Đến kho");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(100, 270, 80, 40);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("(*)");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(190, 210, 24, 30);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Ngày lập phiếu");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(40, 150, 150, 30);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("(*)");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(190, 270, 30, 40);

        jdc_ngayLapPX.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jPanel1.add(jdc_ngayLapPX);
        jdc_ngayLapPX.setBounds(250, 150, 340, 40);
        Locale locale = new Locale("vi", "VN");
        jdc_ngayLapPX.setLocale(locale);

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/jLabel_TaoPhieuXuatKho.png"))); // NOI18N
        jPanel1.add(jLabel16);
        jLabel16.setBounds(340, 10, 740, 84);

        jLabel9.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/book-stack.png"))); // NOI18N
        jLabel9.setText("Nhập sách");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(900, 90, 190, 36);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("(*)");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(1070, 160, 24, 20);

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("ISBN");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(1000, 160, 60, 30);

        jLabel12.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Số lượng");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(970, 214, 90, 30);

        jLabel13.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("(*)");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(1070, 224, 24, 20);

        tf_soLuong.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tf_soLuong.setPreferredSize(new java.awt.Dimension(64, 40));
        tf_soLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_soLuongActionPerformed(evt);
            }
        });
        jPanel1.add(tf_soLuong);
        tf_soLuong.setBounds(1110, 210, 340, 40);

        btn_them.setBackground(new java.awt.Color(102, 102, 0));
        btn_them.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btn_them.setForeground(new java.awt.Color(255, 255, 255));
        btn_them.setText("Thêm");
        btn_them.setPreferredSize(new java.awt.Dimension(120, 42));
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });
        jPanel1.add(btn_them);
        btn_them.setBounds(1190, 270, 120, 42);

        btn_xoa.setBackground(new java.awt.Color(153, 0, 51));
        btn_xoa.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btn_xoa.setForeground(new java.awt.Color(255, 255, 255));
        btn_xoa.setText("Xóa");
        btn_xoa.setPreferredSize(new java.awt.Dimension(120, 42));
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });
        jPanel1.add(btn_xoa);
        btn_xoa.setBounds(1330, 270, 120, 42);

        jLabel14.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Chi tiết xuất kho");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(30, 360, 240, 36);

        btn_taoPX.setBackground(new java.awt.Color(102, 102, 0));
        btn_taoPX.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btn_taoPX.setForeground(new java.awt.Color(255, 255, 255));
        btn_taoPX.setText("Tạo phiếu xuất");
        btn_taoPX.setPreferredSize(new java.awt.Dimension(120, 42));
        btn_taoPX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_taoPXActionPerformed(evt);
            }
        });
        jPanel1.add(btn_taoPX);
        btn_taoPX.setBounds(1170, 630, 170, 50);

        jcb_khoXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb_khoXuatActionPerformed(evt);
            }
        });
        jPanel1.add(jcb_khoXuat);
        jcb_khoXuat.setBounds(250, 270, 340, 40);

        jPanel1.add(jcb_chonSach);
        jcb_chonSach.setBounds(1110, 150, 340, 40);

        jcb_khoNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb_khoNhapActionPerformed(evt);
            }
        });
        jPanel1.add(jcb_khoNhap);
        jcb_khoNhap.setBounds(252, 210, 340, 40);

        btn_huy.setBackground(new java.awt.Color(153, 0, 51));
        btn_huy.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btn_huy.setForeground(new java.awt.Color(255, 255, 255));
        btn_huy.setText("Hủy");
        btn_huy.setPreferredSize(new java.awt.Dimension(120, 42));
        btn_huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huyActionPerformed(evt);
            }
        });
        jPanel1.add(btn_huy);
        btn_huy.setBounds(1360, 630, 90, 50);

        tbl_phieuXuatKho.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tbl_phieuXuatKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ISBN", "Tên sách", "Loại sách", "Giá gốc", "Số lượng", "Thành tiền", ""
            }
        ));
        tbl_phieuXuatKho.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbl_phieuXuatKho.setRowHeight(40);
        tbl_phieuXuatKho.setShowGrid(true);
        jScrollPane1.setViewportView(tbl_phieuXuatKho);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(30, 400, 1500, 210);

        jLabel15.setBackground(new java.awt.Color(102, 102, 0));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/anhnen.jpg"))); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(1612, 733));
        jPanel1.add(jLabel15);
        jLabel15.setBounds(0, 0, 1600, 700);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_soLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_soLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_soLuongActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        jcb_chonSach.setSelectedItem(0);
        tf_soLuong.setText("");
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void btn_huyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huyActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbl_phieuXuatKho.getModel();
        model.setRowCount(0);

        // Bắt đầu cập nhật ngày lập
        isUpdatingDate = true; // Đặt cờ để tránh thông báo lỗi
        jdc_ngayLapPX.setDate(null); // Xóa ngày lập
        isUpdatingDate = false; // Đặt lại cờ sau khi xóa

        // Xóa giá trị trong các TextField
        jcb_khoXuat.setSelectedItem(0);
        jcb_khoNhap.setSelectedItem(0);
        jcb_chonSach.setSelectedItem(0);
        tf_soLuong.setText("");
    }//GEN-LAST:event_btn_huyActionPerformed

    private void btn_taoPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_taoPXActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbl_phieuXuatKho.getModel();

        String tenKhoXuat = jcb_khoXuat.getSelectedItem().toString();
        String tenKhoNhap = jcb_khoNhap.getSelectedItem().toString();
        Date ngayLapPX = jdc_ngayLapPX.getDate();

    //         Kiểm tra và chuyển đổi ngày
            java.sql.Date sqlDate = null;
            if (ngayLapPX != null) {
                sqlDate = new java.sql.Date(ngayLapPX.getTime()); // Chuyển sang java.sql.Date
//                System.out.println("Ngày dạng SQL: " + sqlDate);
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày lập phiếu nhập kho.");
                return; // Thoát khỏi phương thức nếu ngày chưa được chọn
            }
            try {
                String ma_PXK = taoTuDong_MaPhieuXuatKho();
                String maKX = khoHang_dao.getMaKhoTheoTenKho(tenKhoXuat);
                String maKN = khoHang_dao.getMaKhoTheoTenKho(tenKhoNhap);
                int tong_SL = layTongSoLuong();
//                System.out.println("Tổng số lượng: " + ma_PXK);

                boolean isInserted = px_dao.insertPhieuXuatKho(ma_PXK, sqlDate, "22690761", maKX, maKN, tong_SL);

                if (isInserted) {
                    System.out.println("Thêm phiếu xuất kho thành công!");
                } else {
                    System.out.println("Thêm phiếu xuất kho thất bại!");
                }
                try {
                    themChiTietPhieuXuatKho(model, ma_PXK);
                    themChiTietKhoHang(model, maKN, maKX);
                } catch (SQLException ex) {
                    Logger.getLogger(ThuKho_XuatKho.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Xóa hết thông tin trên màn hình sau khi lưu
                model.setRowCount(0);

                // Xóa rỗng các trường nhập liệu
                isUpdatingDate = true; // Đặt cờ để tránh thông báo lỗi
                jdc_ngayLapPX.setDate(null); // Xóa ngày lập
                isUpdatingDate = false; // Đặt lại cờ sau khi xóa

                // Xóa giá trị trong các TextField
                jcb_khoXuat.setSelectedItem(0);
                jcb_khoNhap.setSelectedItem(0);
                jcb_chonSach.setSelectedItem(0);
                tf_soLuong.setText("");

                // In ra PDF, truyền dsCTPXK vào
                if (dsCTPXK != null) {
                    Report_PhieuXuatKho phieuXuatKho = new Report_PhieuXuatKho();
                    phieuXuatKho.ViewReport_PhieuXuatKho(dsCTPXK, ma_PXK);
                } else {
                    System.out.println("Danh sách chi tiết phiếu nhập kho rỗng, không thể in báo cáo PDF.");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }//GEN-LAST:event_btn_taoPXActionPerformed

    private void jcb_khoXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb_khoXuatActionPerformed
        String maKhoNhap = (String) jcb_khoNhap.getSelectedItem();
        String maKhoXuat = (String) jcb_khoXuat.getSelectedItem();

        // Kiểm tra nếu kho xuất trùng với kho nhập
        if (maKhoNhap != null && maKhoNhap.equals(maKhoXuat)) {
            JOptionPane.showMessageDialog(this, "Kho xuất không được trùng với kho nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            // Đặt lại giá trị jcb_khoXuat nếu cần
            jcb_khoXuat.setSelectedIndex(-1);
        }
    }//GEN-LAST:event_jcb_khoXuatActionPerformed

    private void jcb_khoNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb_khoNhapActionPerformed
        // Lấy mã kho được chọn
        String maKhoHang = (String) jcb_khoNhap.getSelectedItem();
        
        if (maKhoHang != null && !maKhoHang.isEmpty()) {
            try {
                // Gọi phương thức để lấy danh sách sách từ mã kho hàng
                List<Sach> danhSachSach = sach_dao.getDanhSachSachTheoMaKhoHang(maKhoHang);

                // Xóa các mục cũ trong jcb_chonSach và cập nhật mục mới
                jcb_chonSach.removeAllItems();
                for (Sach sach : danhSachSach) {
                    jcb_chonSach.addItem(sach.getISBN() + " - " + sach.getTenSach()); // Hiển thị ISBN và tên sách
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi cập nhật danh sách sách!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {
        String isbn = jcb_chonSach.getSelectedItem().toString();
        String sl = tf_soLuong.getText();

        if (!kiemTraSoLuong(isbn, sl)) {
            return; // Nếu dữ liệu không hợp lệ, dừng hàm
        }

        int soLuong = Integer.parseInt(sl);

        // Kiểm tra model
        DefaultTableModel model = (DefaultTableModel) tbl_phieuXuatKho.getModel();

        try {
            // Lấy thông tin sách từ mã ISBN
            Sach sach = sach_dao.getSachTheoMaSach(isbn);

            if (sach != null) {
                String tenSach = sach.getTenSach();
                String loaiSach = sach.getLoaiSach().getTenLoai();
                double giaGoc = sach.getGiaGoc();

                // Kiểm tra xem ISBN đã có trong bảng chưa
                boolean isbnTonTai = false;
                for (int i = 0; i < model.getRowCount(); i++) {
                    String isbnTrongBang = model.getValueAt(i, 1).toString(); // Lấy giá trị ISBN từ cột 1
                    if (isbnTrongBang.equals(isbn)) {
                        // ISBN đã tồn tại, cập nhật số lượng
                        int soLuongCu = Integer.parseInt(model.getValueAt(i, 5).toString()); // Cột 6 là số lượng
                        int soLuongMoiCapNhat = soLuongCu + soLuong;
                        model.setValueAt(soLuongMoiCapNhat, i, 5); // Cập nhật số lượng
                        model.setValueAt(soLuongMoiCapNhat * giaGoc, i, 6); // Cập nhật tổng tiền (cột 7)
                        isbnTonTai = true;
                        break; // Ngừng tìm kiếm sau khi đã cập nhật
                    }
                }

                // Nếu ISBN chưa tồn tại, thêm dòng mới
                if (!isbnTonTai) {
                    // Cập nhật STT
                    int stt = model.getRowCount() + 1;

                    // Thêm hàng mới vào bảng
                    model.addRow(new Object[]{stt, isbn, tenSach, loaiSach, giaGoc, soLuong, giaGoc * soLuong, ""});
                }

                // Xóa các text fields sau khi thêm thành công
                jcb_chonSach.setSelectedItem(0);
                tf_soLuong.setText("");

                JOptionPane.showMessageDialog(null, "Thêm sách thành công.");
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy sách với ISBN: " + isbn);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm sách: " + e.getMessage());
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_huy;
    private javax.swing.JButton btn_taoPX;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcb_chonSach;
    private javax.swing.JComboBox<String> jcb_khoNhap;
    private javax.swing.JComboBox<String> jcb_khoXuat;
    private com.toedter.calendar.JDateChooser jdc_ngayLapPX;
    private javax.swing.JTable tbl_phieuXuatKho;
    private javax.swing.JTextField tf_soLuong;
    // End of variables declaration//GEN-END:variables

    // Phương thức để loại bỏ dấu trong chuỗi truyền vào
    private String removeAccents(String text) {
        return java.text.Normalizer.normalize(text, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .replaceAll("[đĐ]", "d"); // Thay thế ký tự 'đ' và 'Đ' thành 'd'
    }
}

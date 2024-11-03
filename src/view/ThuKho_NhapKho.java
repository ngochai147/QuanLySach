/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import dao.*;
import entity.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thế Bảo
 */
public class ThuKho_NhapKho extends JInternalFrame {
    private ArrayList<ChiTietPhieuNhapKho> dsCTPNK = new ArrayList<>();
    KhoHang_DAO khoHang_dao = new KhoHang_DAO();
    ChiTietKhoHang_DAO ctkh_dao = new ChiTietKhoHang_DAO();
    Sach_DAO sach_dao = new Sach_DAO();
    PhieuNhap_DAO pn_dao = new PhieuNhap_DAO();
    ChiTietPhieuNhap_DAO ctpn_dao = new ChiTietPhieuNhap_DAO();

    private static final String ma_CT_PNK = "CTPNK";
    private static final String ma_PNK = "PNK";
    private static final String ma_CTK = "CTKH";

    private boolean isUpdatingDate = false; // Cờ kiểm soát việc xóa ngày để tránh vòng lặp
    /**
     * Creates new form ThuKho_NhapKho
     */
    public ThuKho_NhapKho() {
        initComponents();


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
            jdc_ngayLapPN.setDate(null); // Xóa ngày lập nếu không hợp lệ
            isUpdatingDate = false; // Đặt lại cờ sau khi xóa
        }

        return isValid;
    }

    // Sử dụng trong các hàm khác
    private boolean kiemTra_NgayLap() {
        if (isUpdatingDate) {
            return true; // Bỏ qua kiểm tra khi đang cập nhật
        }

        Date selectedDate = jdc_ngayLapPN.getDate();
        return kiemTraNgayLapPhieu(selectedDate, true);
    }

    // Hàm điều hướng focus giữa các text field và JDateChooser
    public void nhapThongTin() {
        // Vô hiệu hóa các trường ISBN và Số Lượng lúc khởi tạo
        Sach_DAO sach = new Sach_DAO();
        try {
            List<Sach> danhSachSach = sach.getDSSach();

            for (Sach s : danhSachSach) {
                jcb_chonSach.addItem(s.getISBN());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        jcb_chonSach.setEnabled(false);
        tf_soLuong.setEnabled(false);

        try {
            KhoHang_DAO dao = new KhoHang_DAO();
            List<KhoHang> danhSachTenKho = dao.getDSKhoHang();

            for (KhoHang tenKho : danhSachTenKho) {
                jcb_khoNhap.addItem(tenKho.getTenKho());
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
        tbl_phieuNhapKho.getColumnModel().getColumn(0).setCellRenderer( centerRenderer);

        // Thiết lập kích thước font cho các phần tử trong table
        Font font = new Font("Arial", Font.PLAIN, 18);
        tbl_phieuNhapKho.setFont(font);

        // Chỉnh kích thước cột
        tbl_phieuNhapKho.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbl_phieuNhapKho.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbl_phieuNhapKho.getColumnModel().getColumn(2).setPreferredWidth(250);
        tbl_phieuNhapKho.getColumnModel().getColumn(3).setPreferredWidth(150);
        tbl_phieuNhapKho.getColumnModel().getColumn(4).setPreferredWidth(100);
        tbl_phieuNhapKho.getColumnModel().getColumn(5).setPreferredWidth(100);
        tbl_phieuNhapKho.getColumnModel().getColumn(6).setPreferredWidth(100);
        tbl_phieuNhapKho.getColumnModel().getColumn(7).setPreferredWidth(100);
    }

    public void canGiua_tableHeader() {
        // Căn giữa và chỉnh kích thước table header
        Font fn = new Font("Arial", Font.BOLD, 18);
        tbl_phieuNhapKho.getTableHeader().setFont(fn);

        // Lấy header của bảng
        JTableHeader header = tbl_phieuNhapKho.getTableHeader();

        // Tạo renderer để căn giữa header
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        // Thiết lập renderer cho header
        header.setDefaultRenderer(renderer);
    }

    public String taoTuDong_MaChiTietPhieuNhapKho() {
        // Lấy mã chi tiết phiếu nhập kho mới nhất từ cơ sở dữ liệu
        String lastMaChiTietPhieuNhapKho = ctpn_dao.getLastMaChiTietPhieuNhapKho();

        int newNumber;
        if (lastMaChiTietPhieuNhapKho != null && lastMaChiTietPhieuNhapKho.startsWith(ma_CT_PNK)) {
            // Tách phần số ra khỏi mã và tăng lên 1
            String numberPart = lastMaChiTietPhieuNhapKho.substring(ma_CT_PNK.length());
            newNumber = Integer.parseInt(numberPart) + 1;
        } else {
            // Nếu không có mã nào trong CSDL, bắt đầu từ 1
            newNumber = 1;
        }

        // Định dạng lại mã với tiền tố và phần số đủ 5 chữ số
        return ma_CT_PNK + String.format("%05d", newNumber);
    }

    // Hàm tạo mã phiếu nhập kho tự động tăng
    public String taoTuDong_MaPhieuNhapKho() {
        // Lấy mã phiếu nhập kho cuối cùng từ cơ sở dữ liệu
        String lastMaPhieuNhapKho = pn_dao.getLastMaPhieuNhapKho();

        int newNumber;
        if (lastMaPhieuNhapKho != null && lastMaPhieuNhapKho.startsWith(ma_PNK)) {
            // Tách phần số ra khỏi mã và tăng lên 1
            String numberPart = lastMaPhieuNhapKho.substring(ma_PNK.length());
            newNumber = Integer.parseInt(numberPart) + 1;
        } else {
            // Nếu không có mã nào trong CSDL, bắt đầu từ 1
            newNumber = 1;
        }

        // Định dạng lại mã với tiền tố và phần số đủ 5 chữ số
        return ma_PNK + String.format("%04d", newNumber);
    }

    // Hàm tạo mã chi tiết kho hàng tự động tăng
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

    // Hàm để thêm chi tiết phiếu nhập kho từ JTable
    public void themChiTietPhieuNhapKho(DefaultTableModel model, String maPhieuNhapKho) throws SQLException {
        dsCTPNK.clear();

        for (int i = 0; i < model.getRowCount(); i++) {
            // Tạo mã chi tiết phiếu nhập kho tự động
            String maChiTietPhieuNhapKho = taoTuDong_MaChiTietPhieuNhapKho();

            // Lấy ISBN và số lượng từ bảng
            String isbn = model.getValueAt(i, 1).toString();
            int soLuong = Integer.parseInt(model.getValueAt(i, 5).toString());

            // Gọi hàm insert chi tiết phiếu nhập kho vào cơ sở dữ liệu
            boolean isInserted = ctpn_dao.insertChiTietPhieuNhapKho(maChiTietPhieuNhapKho, maPhieuNhapKho, soLuong, isbn);

            // Lấy thông tin sách từ ISBN
            Sach sach = sach_dao.getSachTheoMaSach(isbn); // Giả sử bạn có hàm lấy thông tin sách từ ISBN
            if (sach == null) {
                System.out.println("Không tìm thấy sách với ISBN: " + isbn);
                sach = new Sach(isbn, "Tên sách mặc định", "Loại sách mặc định", 0, "", 0, 0.0, null, null, ""); // Khởi tạo sách mặc định
            }

            // Tạo chi tiết phiếu nhập kho
            ChiTietPhieuNhapKho ctpnk = new ChiTietPhieuNhapKho(maChiTietPhieuNhapKho, new PhieuNhapKho(maPhieuNhapKho), soLuong, sach);
            dsCTPNK.add(ctpnk);

            if (!isInserted) {
                System.out.println("Không thể thêm chi tiết phiếu nhập kho cho ISBN: " + isbn);
            } else {
                System.out.println("Thêm chi tiết phiếu nhập kho thành công cho ISBN: " + isbn);
            }
        }
    }

    // Hàm để thêm chi tiết kho hàng từ JTable
    public void themChiTietKhoHang(DefaultTableModel model, String maKhoHang) throws SQLException {
        for (int i = 0; i < model.getRowCount(); i++) {
            // Tạo mã chi tiết phiếu nhập kho tự động
            String maChiTietKhoHang = taoTuDong_MaChiTietKhoHang();

            // Lấy ISBN và số lượng từ bảng
            String isbn = model.getValueAt(i, 1).toString();
            Sach sach = new Sach(isbn, "","",0,"",0,0.0, null,null,"");
//            System.out.println("Sach: " + sach.getISBN());
            int soLuong = Integer.parseInt(model.getValueAt(i, 5).toString());

            KhoHang khoHang = new KhoHang(maKhoHang, "", 0, "");
//            System.out.println("Kho hàng: " + khoHang.getMaKhoHang());
            // Gọi hàm insert chi tiết phiếu nhập kho vào cơ sở dữ liệu
            ChiTietKhoHang ctkh = new ChiTietKhoHang(maChiTietKhoHang, soLuong, new Sach(sach.getISBN()), new KhoHang(khoHang.getMaKhoHang()));
            boolean isInserted = ctkh_dao.themChiTietKhoHang(ctkh);

            if (!isInserted) {
                System.out.println("Không thể thêm chi tiết kho hàng cho ISBN: " + isbn);
            } else {
                System.out.println("Thêm chi tiết kho hàng thành công cho ISBN: " + isbn);
            }
        }
    }

    public void capNhatSoLuongTuBangNhapKho(DefaultTableModel model) {
        for (int i = 0; i < model.getRowCount(); i++) {
            // Lấy ISBN và số lượng từ bảng
            String isbn = model.getValueAt(i, 1).toString(); // ISBN là cột 1
            int soLuong = Integer.parseInt(model.getValueAt(i, 5).toString()); // Số lượng là cột 5

            // Cập nhật số lượng sách trong cơ sở dữ liệu
            boolean isUpdated = sach_dao.capNhatSoLuongSach(isbn, soLuong);

            if (!isUpdated) {
                System.out.println("Không thể cập nhật số lượng cho sách với ISBN: " + isbn);
            } else {
                System.out.println("Cập nhật số lượng thành công cho sách với ISBN: " + isbn);
            }
        }
    }

    public int layTongSoLuong() {
        int tongSoLuong = 0;
        DefaultTableModel model = (DefaultTableModel) tbl_phieuNhapKho.getModel();

        // Duyệt qua từng hàng và lấy giá trị từ cột số lượng (cột 5, index 4)
        for (int i = 0; i < model.getRowCount(); i++) {
            int soLuong = Integer.parseInt(model.getValueAt(i, 5).toString());
            tongSoLuong += soLuong;
        }

        return tongSoLuong;
    }

    public void tao_PhieuNhapKho_tuThemSach(String isbn, java.sql.Date ngayLap, int soLuong, String tenKho) throws SQLException {
        // Thêm sách mới vào bảng
        Sach sach = sach_dao.getSachTheoMaSach(isbn);

        DefaultTableModel model = (DefaultTableModel) tbl_phieuNhapKho.getModel();
        model.addRow(new Object[]{"", sach.getISBN(),"", "", sach.getGiaGoc(), soLuong, "", ""});
        String ma_PNK = taoTuDong_MaPhieuNhapKho();
        String maKH = khoHang_dao.getMaKhoTheoTenKho(tenKho);
        int tong_SL = layTongSoLuong();
        boolean isInserted = pn_dao.insertPhieuNhapKho(ma_PNK, ngayLap, "22690761", maKH, tong_SL);

        if (isInserted) {
            System.out.println("Thêm phiếu nhập kho thành công!");
        } else {
            System.out.println("Thêm phiếu nhập kho thất bại!");
        }

        try {
            themChiTietPhieuNhapKho(model, ma_PNK);
        } catch (SQLException ex) {
            Logger.getLogger(ThuKho_NhapKho.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            themChiTietKhoHang(model, maKH);
            themChiTietPhieuNhapKho(model, ma_PNK);
        } catch (SQLException ex) {
            Logger.getLogger(ThuKho_NhapKho.class.getName()).log(Level.SEVERE, null, ex);
        }

        capNhatSoLuongTuBangNhapKho(model);
        model.setRowCount(0);

        // In ra PDF, truyền dsCTPNK vào
        if (dsCTPNK != null) {
            Report_PhieuNhapKho phieuNhapKho = new Report_PhieuNhapKho();
            phieuNhapKho.ViewReport_PhieuNhapKho(dsCTPNK, ma_PNK);
        } else {
            System.out.println("Danh sách chi tiết phiếu nhập kho rỗng, không thể in báo cáo PDF.");
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

        jPanel_NhapKho = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btn_them = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btn_xoa = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_phieuNhapKho = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jdc_ngayLapPN = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        tf_soLuong = new javax.swing.JTextField();
        btn_taoPN = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btn_huy = new javax.swing.JButton();
        jcb_khoNhap = new javax.swing.JComboBox<>();
        jcb_chonSach = new javax.swing.JComboBox<>();
        background = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1612, 733));

        jPanel_NhapKho.setPreferredSize(new java.awt.Dimension(1612, 733));
        jPanel_NhapKho.setLayout(null);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/jLabel_TaoPhieuNhapKho.png"))); // NOI18N
        jPanel_NhapKho.add(jLabel1);
        jLabel1.setBounds(0, 0, 1580, 130);

        jLabel9.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/book-stack.png"))); // NOI18N
        jLabel9.setText("Nhập sách");
        jPanel_NhapKho.add(jLabel9);
        jLabel9.setBounds(900, 150, 190, 36);

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
        jPanel_NhapKho.add(btn_them);
        btn_them.setBounds(1190, 310, 120, 42);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("(*)");
        jPanel_NhapKho.add(jLabel11);
        jLabel11.setBounds(1070, 200, 24, 20);

        jLabel13.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("(*)");
        jPanel_NhapKho.add(jLabel13);
        jLabel13.setBounds(1070, 260, 24, 20);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("(*)");
        jPanel_NhapKho.add(jLabel4);
        jLabel4.setBounds(200, 190, 30, 30);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("(*)");
        jPanel_NhapKho.add(jLabel8);
        jLabel8.setBounds(200, 250, 30, 40);

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
        jPanel_NhapKho.add(btn_xoa);
        btn_xoa.setBounds(1330, 310, 120, 42);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Ngày lập phiếu");
        jPanel_NhapKho.add(jLabel7);
        jLabel7.setBounds(50, 190, 150, 30);

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("ISBN");
        jPanel_NhapKho.add(jLabel10);
        jLabel10.setBounds(1010, 190, 60, 40);

        tbl_phieuNhapKho.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tbl_phieuNhapKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ISBN", "Tên sách", "Loại sách", "Giá gốc", "Số lượng", "Thành tiền", ""
            }
        ));
        tbl_phieuNhapKho.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbl_phieuNhapKho.setRowHeight(40);
        tbl_phieuNhapKho.setShowGrid(true);
        jScrollPane1.setViewportView(tbl_phieuNhapKho);

        jPanel_NhapKho.add(jScrollPane1);
        jScrollPane1.setBounds(30, 400, 1500, 210);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Thông tin");
        jPanel_NhapKho.add(jLabel2);
        jLabel2.setBounds(90, 140, 140, 36);

        jdc_ngayLapPN.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jPanel_NhapKho.add(jdc_ngayLapPN);
        jdc_ngayLapPN.setBounds(260, 190, 340, 40);
        Locale locale = new Locale("vi", "VN");
        jdc_ngayLapPN.setLocale(locale);

        jLabel12.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Số lượng");
        jPanel_NhapKho.add(jLabel12);
        jLabel12.setBounds(970, 250, 90, 40);

        tf_soLuong.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tf_soLuong.setPreferredSize(new java.awt.Dimension(64, 40));
        tf_soLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_soLuongActionPerformed(evt);
            }
        });
        jPanel_NhapKho.add(tf_soLuong);
        tf_soLuong.setBounds(1110, 250, 340, 40);

        btn_taoPN.setBackground(new java.awt.Color(102, 102, 0));
        btn_taoPN.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btn_taoPN.setForeground(new java.awt.Color(255, 255, 255));
        btn_taoPN.setText("Tạo phiếu nhập");
        btn_taoPN.setPreferredSize(new java.awt.Dimension(120, 42));
        btn_taoPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_taoPNActionPerformed(evt);
            }
        });
        jPanel_NhapKho.add(btn_taoPN);
        btn_taoPN.setBounds(1160, 630, 180, 50);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Đến kho");
        jPanel_NhapKho.add(jLabel5);
        jLabel5.setBounds(110, 250, 80, 40);

        jLabel14.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Chi tiết nhập kho");
        jPanel_NhapKho.add(jLabel14);
        jLabel14.setBounds(30, 360, 260, 36);

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
        jPanel_NhapKho.add(btn_huy);
        btn_huy.setBounds(1360, 630, 90, 50);

        jcb_khoNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb_khoNhapActionPerformed(evt);
            }
        });
        jPanel_NhapKho.add(jcb_khoNhap);
        jcb_khoNhap.setBounds(260, 250, 340, 40);

        jcb_chonSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb_chonSachActionPerformed(evt);
            }
        });
        jPanel_NhapKho.add(jcb_chonSach);
        jcb_chonSach.setBounds(1110, 190, 340, 40);

        background.setBackground(new java.awt.Color(102, 102, 0));
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/anhnen.jpg"))); // NOI18N
        background.setPreferredSize(new java.awt.Dimension(1612, 733));
        jPanel_NhapKho.add(background);
        background.setBounds(0, 0, 1612, 733);

        jPanel_NhapKho.setLayout(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_NhapKho, javax.swing.GroupLayout.DEFAULT_SIZE, 2479, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_NhapKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_soLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_soLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_soLuongActionPerformed

    private void btn_taoPNActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) tbl_phieuNhapKho.getModel();

        String tenKho = jcb_khoNhap.getSelectedItem().toString();
        Date ngayLapPN = jdc_ngayLapPN.getDate();

        // Kiểm tra và chuyển đổi ngày
        java.sql.Date sqlDate = null;
        if (ngayLapPN != null) {
            sqlDate = new java.sql.Date(ngayLapPN.getTime()); // Chuyển sang java.sql.Date
//            System.out.println("Ngày dạng SQL: " + sqlDate);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày lập phiếu nhập kho.");
            return; // Thoát khỏi phương thức nếu ngày chưa được chọn
        }

        try {
            String ma_PNK = taoTuDong_MaPhieuNhapKho();
            String maKH = null;
            maKH = khoHang_dao.getMaKhoTheoTenKho(tenKho);
            int tong_SL = layTongSoLuong();

//            System.out.println("Mã phiếu nhập kho: " + ma_PNK);

            boolean isInserted = pn_dao.insertPhieuNhapKho(ma_PNK, sqlDate, "22690761", maKH, tong_SL);

//            if (isInserted) {
//                System.out.println("Thêm phiếu nhập kho thành công!");
//            } else {
//                System.out.println("Thêm phiếu nhập kho thất bại!");
//            }
            try {
                themChiTietKhoHang(model, maKH);
                themChiTietPhieuNhapKho(model, ma_PNK);
            } catch (SQLException ex) {
                Logger.getLogger(ThuKho_NhapKho.class.getName()).log(Level.SEVERE, null, ex);
            }
            capNhatSoLuongTuBangNhapKho(model);

            // In ra PDF, truyền dsCTPNK vào
            if (dsCTPNK != null) {
                Report_PhieuNhapKho phieuNhapKho = new Report_PhieuNhapKho();
                phieuNhapKho.ViewReport_PhieuNhapKho(dsCTPNK, ma_PNK);
            } else {
                System.out.println("Danh sách chi tiết phiếu nhập kho rỗng, không thể in báo cáo PDF.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Xóa hết thông tin trên màn hình sau khi lưu
        model.setRowCount(0);

        // Xóa rỗng các trường nhập liệu
        isUpdatingDate = true; // Đặt cờ để tránh thông báo lỗi
        jdc_ngayLapPN.setDate(null); // Xóa ngày lập
        isUpdatingDate = false; // Đặt lại cờ sau khi xóa

        // Xóa giá trị trong các TextField
        jcb_khoNhap.setSelectedIndex(0);
        jcb_chonSach.setSelectedIndex(0);
        tf_soLuong.setText("");
//      JOptionPane.showMessageDialog(null, "Đã lưu phiếu nhập kho.");
    }

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        jcb_chonSach.setSelectedIndex(0);
        tf_soLuong.setText("");
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void jcb_khoNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb_khoNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcb_khoNhapActionPerformed

    private void jcb_chonSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb_chonSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcb_chonSachActionPerformed

    private void btn_huyActionPerformed(java.awt.event.ActionEvent evt) {                                        
        DefaultTableModel model = (DefaultTableModel) tbl_phieuNhapKho.getModel();
        model.setRowCount(0);

        // Bắt đầu cập nhật ngày lập
        isUpdatingDate = true; // Đặt cờ để tránh thông báo lỗi
        jdc_ngayLapPN.setDate(null); // Xóa ngày lập
        isUpdatingDate = false; // Đặt lại cờ sau khi xóa

        // Xóa giá trị trong các TextField
        jcb_khoNhap.setSelectedIndex(0);
        jcb_chonSach.setSelectedIndex(0);
        tf_soLuong.setText("");
    }

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {
        String isbn = jcb_chonSach.getSelectedItem().toString();
        String sl = tf_soLuong.getText();

        if (!kiemTraSoLuong(isbn, sl)) {
            return; // Nếu dữ liệu không hợp lệ, dừng hàm
        }

        int soLuong = Integer.parseInt(sl);

        // Kiểm tra model
        DefaultTableModel model = (DefaultTableModel) tbl_phieuNhapKho.getModel();

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
    private javax.swing.JLabel background;
    private javax.swing.JButton btn_huy;
    private javax.swing.JButton btn_taoPN;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel_NhapKho;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcb_chonSach;
    private javax.swing.JComboBox<String> jcb_khoNhap;
    private com.toedter.calendar.JDateChooser jdc_ngayLapPN;
    private javax.swing.JTable tbl_phieuNhapKho;
    private javax.swing.JTextField tf_soLuong;
    // End of variables declaration//GEN-END:variables
}

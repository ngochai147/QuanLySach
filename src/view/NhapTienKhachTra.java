package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class NhapTienKhachTra extends JDialog {
    private JTextField amountField;
    private JButton confirmButton;
    private JButton cancelButton;
    private double tongTienHoaDon;
    private  String maHoaDon;
    private double tienKhachTra;
    private boolean kiemTra;
    private boolean hinhThucThanhToan=true;
    public NhapTienKhachTra(Frame parent, double tongTienHoaDon, String maHoaDon) {
        super(parent, "Nhập Số Tiền Khách Đưa", true);
        initComponents();
        this.tongTienHoaDon=tongTienHoaDon;
        this.maHoaDon=maHoaDon;
        pack();
        setLocationRelativeTo(parent);

    }
    private void initComponents() {

        kGradientPanel1 = new keeptoo.KGradientPanel();
        jLabel_TieuDe = new JLabel();
        jLabel_TienKhachDua = new JLabel();
        jTextField_TienKhachDua = new JTextField();
        jlabel_HinhThuc = new JLabel();
        jButton_Ok = new JButton();
        jButton_Huy = new JButton();
        jComboBox_TieuChi = new JComboBox<>();

        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        kGradientPanel1.setkEndColor(new Color(139, 119, 101));
        kGradientPanel1.setkStartColor(new Color(205, 175, 149));

        jLabel_TieuDe.setFont(new Font("Bahnschrift", 1, 30)); // NOI18N
        jLabel_TieuDe.setForeground(new Color(102, 102, 0));
        jLabel_TieuDe.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel_TieuDe.setText("Thanh toán");

        jLabel_TienKhachDua.setFont(new Font("Arial", 1, 18)); // NOI18N
        jLabel_TienKhachDua.setText("Tiền khách đưa:");

        jTextField_TienKhachDua.setFont(new Font("Arial", 0, 18)); // NOI18N

        jlabel_HinhThuc.setFont(new Font("Arial", 1, 18)); // NOI18N
        jlabel_HinhThuc.setText("Hình thức:");

        jButton_Ok.setBackground(new Color(102, 102, 0));
        jButton_Ok.setFont(new Font("Arial", 1, 20)); // NOI18N
        jButton_Ok.setForeground(new Color(255, 255, 255));
        jButton_Ok.setText("OK");
        jButton_Ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    jButton_OkActionPerformed(evt);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        jButton_Huy.setBackground(new Color(153, 0, 51));
        jButton_Huy.setFont(new Font("Arial", 1, 20)); // NOI18N
        jButton_Huy.setForeground(new Color(255, 255, 255));
        jButton_Huy.setText("Hủy");
        jButton_Huy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton_HuyActionPerformed(evt);
            }
        });

        jComboBox_TieuChi.setFont(new Font("Arial", 1, 20)); // NOI18N
        jComboBox_TieuChi.setModel(new DefaultComboBoxModel<>(new String[] {"Tiền mặt","Thanh toán qua mã QR"}));
        Color customGreen = new Color(102,102,0);
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
        jComboBox_TieuChi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jComboBox_TieuChiMouseClicked(evt);
            }


        });
        GroupLayout kGradientPanel1Layout = new GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
                kGradientPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton_Ok)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_Huy, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48))
                        .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(kGradientPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel_TieuDe, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                                .addGroup(kGradientPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jlabel_HinhThuc, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel_TienKhachDua))
                                                .addGap(18, 18, 18)
                                                .addGroup(kGradientPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jTextField_TienKhachDua, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                                                        .addComponent(jComboBox_TieuChi, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(0, 11, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        kGradientPanel1Layout.setVerticalGroup(
                kGradientPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel_TieuDe)
                                .addGap(31, 31, 31)
                                .addGroup(kGradientPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jlabel_HinhThuc, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBox_TieuChi, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(kGradientPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField_TienKhachDua, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel_TienKhachDua, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(kGradientPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton_Huy, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton_Ok, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(kGradientPanel1, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0))
        );

        pack();
    }
    private void jButton_HuyActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
        kiemTra=false;
        this.dispose();
    }
    private void jComboBox_TieuChiMouseClicked(ActionEvent evt) {
        int hinhThuc=jComboBox_TieuChi.getSelectedIndex();

        if(hinhThuc==0){
            hinhThucThanhToan=true;
            jTextField_TienKhachDua.setEnabled(true);
        }else{
            jTextField_TienKhachDua.setEnabled(false);
            new VietQRGen(tongTienHoaDon,maHoaDon);
            hinhThucThanhToan=false;
        }

    }
    private void jButton_OkActionPerformed(ActionEvent evt) throws SQLException {
        kiemTra=true;
        try {
            int hinhThuc=jComboBox_TieuChi.getSelectedIndex();
            if(hinhThuc==0){
               tienKhachTra = Double.parseDouble(jTextField_TienKhachDua.getText());
                if (tienKhachTra < tongTienHoaDon) {
                    JOptionPane.showMessageDialog(NhapTienKhachTra.this,
                            "Số tiền khách đưa phải lớn hơn hoặc bằng tổng tiền hóa đơn!",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    dispose();
                }
            }else if(hinhThuc==1){
                hinhThucThanhToan=false;
                this.dispose();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(NhapTienKhachTra.this,
                    "Vui lòng nhập số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        // TODO add your handling code here:


    }// </editor-fold>

    public double getTienKhachTra() {
        return tienKhachTra;
    }
    public boolean getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }
    public boolean getKiemtra(){
        return kiemTra;
    }
    private JButton jButton_Huy;
    private JButton jButton_Ok;
    private JComboBox<String> jComboBox_TieuChi;
    private JLabel jLabel_TienKhachDua;
    private JLabel jLabel_TieuDe;
    private JTextField jTextField_TienKhachDua;
    private JLabel jlabel_HinhThuc;
    private keeptoo.KGradientPanel kGradientPanel1;
}

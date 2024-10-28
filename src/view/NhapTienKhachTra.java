package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NhapTienKhachTra extends JDialog {
    private JTextField amountField;
    private JButton confirmButton;
    private JButton cancelButton;
    private double tienKhachTra;

    public NhapTienKhachTra(Frame parent, double tongTienHoaDon) {
        super(parent, "Nhập Số Tiền Khách Đưa", true);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel("Số tiền khách đưa:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 5, 10);
        add(label, gbc);

        amountField = new JTextField(15);
        gbc.gridx = 1;
        add(amountField, gbc);

        confirmButton = new JButton("Xác Nhận");
        cancelButton = new JButton("Hủy");

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 5);
        add(confirmButton, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(10, 5, 10, 10);
        add(cancelButton, gbc);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tienKhachTra = Double.parseDouble(amountField.getText());
                    if (tienKhachTra < tongTienHoaDon) {
                        JOptionPane.showMessageDialog(NhapTienKhachTra.this,
                                "Số tiền khách đưa phải lớn hơn hoặc bằng tổng tiền hóa đơn!",
                                "Lỗi", JOptionPane.ERROR_MESSAGE);
                    } else {
                        dispose();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(NhapTienKhachTra.this,
                            "Vui lòng nhập số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(e -> dispose());

        pack();
        setLocationRelativeTo(parent);
    }

    public double getTienKhachTra() {
        return tienKhachTra;
    }
}

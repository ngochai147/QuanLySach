/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import dao.ExportExcel_DAO;
import dao.ThongKe_Dao;
import entity.ThongKe_model;
import panel.Panel_DoanhThuTheoNam;
import panel.Panel_DoanhThuThongKeTheoNgay;
import panel.panel_DoanhThuTheoThang;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author phamd
 */
public class NguoiQuanLy_ThongKeDoanhThu1 extends javax.swing.JInternalFrame {

    private String selectedPeriod = "";

    final Panel_DoanhThuThongKeTheoNgay chart_Day = new Panel_DoanhThuThongKeTheoNgay();
    final panel_DoanhThuTheoThang chart_Month = new panel_DoanhThuTheoThang();
    final Panel_DoanhThuTheoNam chart_Year = new Panel_DoanhThuTheoNam();

    private final Color clickColor;
    private final Color defaultColor;

    private ThongKe_Dao thongKe_Dao = new ThongKe_Dao();
    ExportExcel_DAO exportExcel_DAO = new ExportExcel_DAO();

    /**
     * Creates new form NguoiQuanLy_ThongKeDoanhThu1
     */
    public NguoiQuanLy_ThongKeDoanhThu1() {
        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);

        clickColor = new Color(102, 102, 0);
        defaultColor = new Color(153, 153, 0);

    }

    public void Restart_JdesktopPane() {
        jDateChooser_From.setDate(null);
        jDateChooser_To.setDate(null);
        jDesktopPaneHoldPanelChart.removeAll();
        jDesktopPaneHoldPanelChart.repaint();
        jDesktopPaneHoldPanelChart.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new JPanel();
        jPanel_Year = new JPanel();
        jLabel_Year = new javax.swing.JLabel();
        jPanel_Month = new JPanel();
        jLabel_Month = new javax.swing.JLabel();
        jPanel_Day = new JPanel();
        jLabel_Day = new javax.swing.JLabel();
        jButton_XuatExcel = new javax.swing.JButton();
        jButton_LamMoi = new javax.swing.JButton();
        jDateChooser_From = new com.toedter.calendar.JDateChooser();
        jDateChooser_To = new com.toedter.calendar.JDateChooser();
        jButton_ThongKe = new javax.swing.JButton();
        jDesktopPaneHoldPanelChart = new javax.swing.JDesktopPane();
        jDateChooser_From.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                jDateChooser_From.getDateEditor().setEnabled(false);
            }
        });
        jDateChooser_From.getDateEditor().addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Date selectedDate = jDateChooser_From.getDate();
                Date currentDate = new Date();

                // Kiểm tra nếu ngày đã chọn vượt quá ngày hiện tại
                if (selectedDate != null && selectedDate.after(currentDate)) {
                    JOptionPane.showMessageDialog(null, "Ngày chọn không được vượt quá ngày hiện tại.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);

                    // Đặt lại ngày về ngày hiện tại
                    jDateChooser_From.setDate(currentDate);
                }
            }
        });
        jDateChooser_To.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                jDateChooser_To.getDateEditor().setEnabled(false);
            }
        });

        jDateChooser_To.getDateEditor().addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Date selectedDate = jDateChooser_To.getDate();
                Date currentDate = new Date();

                // Kiểm tra nếu ngày đã chọn vượt quá ngày hiện tại
                if (selectedDate != null && selectedDate.after(currentDate)) {
                    JOptionPane.showMessageDialog(null, "Ngày chọn không được vượt quá ngày hiện tại.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);

                    // Đặt lại ngày về ngày hiện tại
                    jDateChooser_To.setDate(currentDate);
                }
            }
        });
        setPreferredSize(new java.awt.Dimension(1479, 677));

        jPanel1.setPreferredSize(new java.awt.Dimension(1495, 436));

        jPanel_Year.setBackground(new Color(153, 153, 0));
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
        jLabel_Year.setForeground(new Color(255, 255, 255));
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

        jPanel_Month.setBackground(new Color(153, 153, 0));
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
        jLabel_Month.setForeground(new Color(255, 255, 255));
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

        jPanel_Day.setBackground(new Color(153, 153, 0));
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
        jLabel_Day.setForeground(new Color(255, 255, 255));
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

        jButton_XuatExcel.setBackground(new Color(102, 102, 0));
        jButton_XuatExcel.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_XuatExcel.setForeground(new Color(255, 255, 255));
        jButton_XuatExcel.setText("Xuất Excel");
        jButton_XuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_XuatExcelActionPerformed(evt);
            }
        });

        jButton_LamMoi.setBackground(new Color(255, 0, 0));
        jButton_LamMoi.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_LamMoi.setForeground(new Color(255, 255, 255));
        jButton_LamMoi.setText("Làm mới");
        jButton_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LamMoiActionPerformed(evt);
            }
        });

        jDateChooser_From.setBorder(BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new Color(0, 0, 0), 1, true), "Từ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N

        jDateChooser_To.setBorder(BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new Color(0, 0, 0), 1, true), "Đến", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N

        jButton_ThongKe.setBackground(new Color(102, 102, 0));
        jButton_ThongKe.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton_ThongKe.setForeground(new Color(255, 255, 255));
        jButton_ThongKe.setText("Thống kê");
        jButton_ThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_ThongKeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jDesktopPaneHoldPanelChartLayout = new javax.swing.GroupLayout(jDesktopPaneHoldPanelChart);
        jDesktopPaneHoldPanelChart.setLayout(jDesktopPaneHoldPanelChartLayout);
        jDesktopPaneHoldPanelChartLayout.setHorizontalGroup(
                jDesktopPaneHoldPanelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1536, Short.MAX_VALUE)
        );
        jDesktopPaneHoldPanelChartLayout.setVerticalGroup(
                jDesktopPaneHoldPanelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 545, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jPanel_Year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(jPanel_Month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(jPanel_Day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(216, 216, 216)
                                                .addComponent(jDateChooser_From, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jDateChooser_To, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(29, 29, 29)
                                                .addComponent(jButton_ThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton_XuatExcel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton_LamMoi)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jDesktopPaneHoldPanelChart))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jPanel_Day, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel_Month, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel_Year, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jDateChooser_From, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jDateChooser_To, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jButton_ThongKe)
                                                        .addComponent(jButton_XuatExcel)
                                                        .addComponent(jButton_LamMoi))))
                                .addGap(0, 544, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 122, Short.MAX_VALUE)
                                        .addComponent(jDesktopPaneHoldPanelChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1560, Short.MAX_VALUE)
                                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>

    private void jButton_XuatExcelActionPerformed(java.awt.event.ActionEvent evt) {
        Date fromDate = jDateChooser_From.getDate();
        Date toDate = jDateChooser_To.getDate();

        // Kiểm tra ngày bắt đầu và ngày kết thúc
        if (fromDate == null || toDate == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn cả hai ngày.");
            return;
        }
        if (fromDate.after(toDate)) {
            JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc.");
            return;
        }

        // Đường dẫn xuất file Excel
        String filePath_Thang = "D:\\ThongKeDoanhThuTheoThang.xlsx";
        String filePath_Ngay = "D:\\ThongKeDoanhThuTheoNgay.xlsx";
        String filePath_Nam = "D:\\ThongKeDoanhThuTheoNam.xlsx";

        // Xuất dữ liệu theo khoảng thời gian đã chọn
        if (selectedPeriod.equals("day")) {
            exportExcel_DAO.exportExcel_Ngay(filePath_Ngay, fromDate, toDate); // Gọi phương thức với tham số
            JOptionPane.showMessageDialog(null, "Xuất file Excel thành công!");
        } else if (selectedPeriod.equals("month")) {
            exportExcel_DAO.exportExcel_Thang(filePath_Thang, fromDate, toDate); // Gọi phương thức với tham số
            JOptionPane.showMessageDialog(null, "Xuất file Excel thành công!");
        } else {
            exportExcel_DAO.exportExcel_Nam(filePath_Nam, fromDate, toDate);
            JOptionPane.showMessageDialog(null, "Xuất file Excel thành công!");
        }
    }

    private void jPanel_YearMousePressed(java.awt.event.MouseEvent evt) {
        jPanel_Year.setBackground(clickColor);
        jPanel_Month.setBackground(defaultColor);
        jPanel_Day.setBackground(defaultColor);
        Restart_JdesktopPane();
        selectedPeriod = "year";
    }

    private void jPanel_MonthMousePressed(java.awt.event.MouseEvent evt) {
        jPanel_Year.setBackground(defaultColor);
        jPanel_Month.setBackground(clickColor);
        jPanel_Day.setBackground(defaultColor);
        Restart_JdesktopPane();
        selectedPeriod = "month";
    }

    private void jPanel_DayMousePressed(java.awt.event.MouseEvent evt) {
        jPanel_Year.setBackground(defaultColor);
        jPanel_Month.setBackground(defaultColor);
        jPanel_Day.setBackground(clickColor);
        Restart_JdesktopPane();
        selectedPeriod = "day";
    }

    private void jPanel_YearMouseClicked(java.awt.event.MouseEvent evt) {
        jDateChooser_From.setDateFormatString("yyyy");
        jDateChooser_To.setDateFormatString("yyyy");
    }

    private void jPanel_MonthMouseClicked(java.awt.event.MouseEvent evt) {
        jDateChooser_From.setDateFormatString("MM-yyyy");
        jDateChooser_To.setDateFormatString("MM-yyyy");
    }

    private void jPanel_DayMouseClicked(java.awt.event.MouseEvent evt) {
        jDateChooser_From.setDateFormatString("dd-MM-yyyy");
        jDateChooser_To.setDateFormatString("dd-MM-yyyy");
    }

    private void jButton_ThongKeMouseClicked(java.awt.event.MouseEvent evt) {
        Date fromDate = jDateChooser_From.getDate();
        Date toDate = jDateChooser_To.getDate();

        // Kiểm tra ngày hợp lệ
        if (fromDate == null || toDate == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn từ ngày và đến ngày hợp lệ.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (fromDate.after(toDate)) {
            JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (selectedPeriod == null || selectedPeriod.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn loại thống kê (ngày, tháng, hoặc năm).");
            return;
        }

// Lấy dữ liệu thống kê
        List<ThongKe_model> thongKe_Thang = thongKe_Dao.getChartThongKeTheoThang(fromDate, toDate);
        List<ThongKe_model> thongKe_Ngay = thongKe_Dao.getChartThongKeTheoNgay(fromDate, toDate);
        List<ThongKe_model> thongKe_Nam = thongKe_Dao.getChartThongKeTheoNam(fromDate, toDate);

// Kiểm tra dữ liệu có đủ hay không

        if (thongKe_Thang.size() >= 3 || thongKe_Ngay.size() >= 3 || thongKe_Nam.size() >= 3) {

            switch (selectedPeriod) {
                case "day":
                    chart_Day.setSize(jDesktopPaneHoldPanelChart.getSize());
                    chart_Day.setVisible(true);
                    jDesktopPaneHoldPanelChart.add(chart_Day);
                    chart_Day.setChart_DoanhThuTheoNgay(fromDate, toDate);
                    break;
                case "month":
                    chart_Month.setSize(jDesktopPaneHoldPanelChart.getSize());
                    chart_Month.setVisible(true);
                    jDesktopPaneHoldPanelChart.add(chart_Month);
                    chart_Month.setChart_DoanhThuTheoThang(fromDate, toDate);
                    break;
                case "year":
                    chart_Year.setSize(jDesktopPaneHoldPanelChart.getSize());
                    chart_Year.setVisible(true);
                    jDesktopPaneHoldPanelChart.add(chart_Year);
                    chart_Year.setChart_DoanhThuTheoNam(fromDate, toDate);
                    break;
                default:
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu cho khoảng thời gian đã chọn.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void jButton_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {

        jDateChooser_From.setDate(null);
        jDateChooser_To.setDate(null);

        switch (selectedPeriod) {
            case "day":
                chart_Day.clearChart();
                break;
            case "month":
                chart_Month.clearChart();
                break;
            case "year":
                chart_Year.clearChart();
                break;
        }
        Restart_JdesktopPane();

    }


    // Variables declaration - do not modify
    private javax.swing.JButton jButton_LamMoi;
    private javax.swing.JButton jButton_ThongKe;
    private javax.swing.JButton jButton_XuatExcel;
    private com.toedter.calendar.JDateChooser jDateChooser_From;
    private com.toedter.calendar.JDateChooser jDateChooser_To;
    private javax.swing.JDesktopPane jDesktopPaneHoldPanelChart;
    private javax.swing.JLabel jLabel_Day;
    private javax.swing.JLabel jLabel_Month;
    private javax.swing.JLabel jLabel_Year;
    private JPanel jPanel1;
    private JPanel jPanel_Day;
    private JPanel jPanel_Month;
    private JPanel jPanel_Year;
    // End of variables declaration
}

package function;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;

import java.awt.*;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Ngoc Hai
 * */
public class InPDF {
    //

    DecimalFormat formatter = new DecimalFormat("###,###,###");
    SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY HH:mm");
    Document document = new Document();
    FileOutputStream file;
    JFrame jf = new JFrame();
    FileDialog fd = new FileDialog(jf, "Xuất pdf", FileDialog.SAVE);
    Font fontData;
    Font fontTitle;
    Font fontHeader;

    public InPDF() {
        try {
            fontData = new Font(BaseFont.createFont("Dependency/Roboto-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 11, Font.NORMAL);
            fontTitle = new Font(BaseFont.createFont("Dependency/Roboto-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 25, Font.BOLD);
            fontHeader = new Font(BaseFont.createFont("Dependency/Roboto-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 11, Font.BOLD);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(InPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void chooseURL(String url) {
        try {
            document.close();
            document = new Document();
            file = new FileOutputStream(url);
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Khong tim thay duong dan file " + url);
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Khong goi duoc document !");
        }
    }

    public void setTitle(String title) {
        try {
            Paragraph pdfTitle = new Paragraph(new Phrase(title, fontTitle));
            pdfTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(pdfTitle);
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
    }

    private String getFile(String name) {
        fd.pack();
        fd.setSize(800, 600);
        fd.validate();
        Rectangle rect = jf.getContentPane().getBounds();
        double width = fd.getBounds().getWidth();
        double height = fd.getBounds().getHeight();
        double x = rect.getCenterX() - (width / 2);
        double y = rect.getCenterY() - (height / 2);
        Point leftCorner = new Point();
        leftCorner.setLocation(x, y);
        fd.setLocation(leftCorner);
        fd.setFile(name + ".pdf");
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("null")) {
            return null;
        }
        return url;
    }

    public void ghiDachSachHoaDon(JTable jTable) {
        String url = "";
        try {
            fd.setTitle("In phiếu nhập");
            fd.setLocationRelativeTo(null);
            url = getFile("DanhSachHoaDon");
            if (url == null) {
                return;
            }
            file = new FileOutputStream(url);
            document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();


            // Set title
            setTitle("DANH SÁCH HÓA ĐƠN");
            // Tạo bảng cho chi tiết phiếu nhập
            PdfPTable pdfTable = new PdfPTable(5);
            pdfTable.setWidths(new float[]{25f, 20f, 20f, 20f, 25f});
            pdfTable.setWidthPercentage(100); // Đặt bảng chiếm 100% chiều rộng
            pdfTable.setSpacingBefore(10f);
            BaseColor headerColor = new BaseColor(192, 192, 192);

            // Header của bảng
            PdfPCell headerCell;
            headerCell = new PdfPCell(new Phrase("Mã hóa đơn", fontHeader));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER); // Căn giữa
            headerCell.setBackgroundColor(headerColor);
            headerCell.setPadding(5);
            pdfTable.addCell(headerCell);

            headerCell = new PdfPCell(new Phrase("Ngày tạo đơn", fontHeader));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setPadding(5);
            headerCell.setBackgroundColor(headerColor);
            pdfTable.addCell(headerCell);

            headerCell = new PdfPCell(new Phrase("Mã nhân viên", fontHeader));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setPadding(5);
            headerCell.setBackgroundColor(headerColor);
            pdfTable.addCell(headerCell);

            headerCell = new PdfPCell(new Phrase("Số lượng", fontHeader));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setBackgroundColor(headerColor);
            headerCell.setPadding(5);
            pdfTable.addCell(headerCell);

            headerCell = new PdfPCell(new Phrase("Tổng tiền", fontHeader));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setBackgroundColor(headerColor);
            headerCell.setPadding(5);
            pdfTable.addCell(headerCell);

            int tongTien=0;
            int tongSoLuong=0;

            for (int i=0;i<jTable.getRowCount();i++) {

                        // Mã hóa đơn
                        PdfPCell cell = new PdfPCell(new Phrase((String)jTable.getValueAt(i,1), fontData));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER); // Căn giữa
                        cell.setPadding(4);
                        pdfTable.addCell(cell);

                        // Ngày tạo đơn
                        cell = new PdfPCell(new Phrase((String)jTable.getValueAt(i,2), fontData));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER); // Căn trái
                        cell.setPadding(4);
                        pdfTable.addCell(cell);

                        //Mã nhân viên
                        cell = new PdfPCell(new Phrase((String)jTable.getValueAt(i,3), fontData));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setPadding(4);
                        pdfTable.addCell(cell);


                        // Số lượng
                        cell = new PdfPCell(new Phrase(formatter.format((int)jTable.getValueAt(i,4)), fontData));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER); // Căn phải
                        cell.setPadding(4);
                        pdfTable.addCell(cell);

                        // Tổng tiền
                        cell = new PdfPCell(new Phrase((String)jTable.getValueAt(i,5), fontData));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell.setPadding(4);
                        pdfTable.addCell(cell);

                        String tongTienHoaDon=(String)jTable.getValueAt(i,5);
                        String numberStr = tongTienHoaDon.replaceAll("[^0-9,]", "");
                        numberStr = numberStr.replace(",", "");
                        int tongTienFormat = Integer.parseInt(numberStr);

                        tongTien+=tongTienFormat;

                         tongSoLuong+=(int)jTable.getValueAt(i,4);
            }

            PdfPCell cell = new PdfPCell(new Phrase("Tổng kết", fontHeader));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER); // Căn giữa
            cell.setPadding(4);
            pdfTable.addCell(cell);

            cell = new PdfPCell(new Phrase("", fontData));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER); // Căn giữa
            cell.setPadding(4);
            pdfTable.addCell(cell);

            cell = new PdfPCell(new Phrase("", fontData));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER); // Căn giữa
            cell.setPadding(4);
            pdfTable.addCell(cell);

            cell = new PdfPCell(new Phrase(""+tongSoLuong, fontHeader));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER); // Căn giữa
            cell.setPadding(4);
            pdfTable.addCell(cell);

            DecimalFormat df=new DecimalFormat("#,###");

            cell = new PdfPCell(new Phrase(df.format(tongTien)+" VND", fontHeader));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setPadding(4);
            pdfTable.addCell(cell);


            document.add(pdfTable); // Thêm bảng vào tài liệu
            // Kết thúc tài liệu
            document.close();
            JOptionPane.showMessageDialog(null, "Ghi file thành công " + url);
            openFile(url);

        } catch (DocumentException | FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi ghi file " + url);
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException e) {
            System.out.println(e);
        }
    }


}
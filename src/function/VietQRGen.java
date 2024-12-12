/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package function;

import entity.HoaDon;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class VietQRGen {
    //

    private  byte[] decodeDataURI(String dataURI) {
        // Tách phần base64
        String base64Image = dataURI.split(",")[1]; // Phần sau dấu phẩy là dữ liệu base64
        return Base64.getDecoder().decode(base64Image);
    }
    private  void displayImage(byte[] imageBytes) {
        JDialog jDialog = new JDialog();
        jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jDialog.setSize(400, 500); // Kích thước khung

        // Tạo JLabel để chứa ảnh

        try {
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));

            int newWidth = jDialog.getWidth();
            int newHeight = (int) ((double) img.getHeight() / img.getWidth() * newWidth);
            Image scaledImg = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledImg);


            JLabel label= new JLabel(icon);
            label.setHorizontalAlignment(JLabel.CENTER); // Căn giữa
            label.setVerticalAlignment(JLabel.CENTER);
            jDialog.getContentPane().add(label, BorderLayout.CENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Căn giữa khung trên màn hình
        jDialog.setTitle("QR code");
        jDialog.setModal(true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setVisible(true);
    }
    private  void saveImage(byte[] imageBytes) {
        // Hiển thị ảnh ngay mà không lưu
        displayImage(imageBytes);
    }

    public VietQRGen(double tongTien,String maHoaDon) {
        String url = "https://api.vietqr.io/v2/generate";

        // Tạo đối tượng RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Tạo yêu cầu header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-client-id", "50ccaeeb-630f-4fa1-9a3b-fee332349e7f"); // Thay thế bằng Client ID của bạn
        headers.set("x-api-key", "0101a28b-dcbd-40ca-aa48-623ac8672683"); // Thay thế bằng API key của bạn

        // Tạo dữ liệu request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("accountNo", "28659007"); // Số tài khoản ngân hàng
        requestBody.put("accountName", "Fahasa Bookstore"); // Tên tài khoản
        requestBody.put("acqId", 970416); // Mã định danh ngân hàng
        requestBody.put("amount", tongTien); // Số tiền chuyển
        requestBody.put("addInfo", "Thanh toán " + maHoaDon); // Nội dung chuyển tiền
        requestBody.put("template", "print"); // Mẫu VietQR trả về

        // Đóng gói yêu cầu
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            // Gửi POST request và nhận response
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            // In ra kết quả
            if (response.getStatusCode() == HttpStatus.OK) {
                // Sử dụng Jackson để phân tích cú pháp JSON
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.getBody());

                String code = jsonNode.path("code").asText();
                JsonNode data = jsonNode.path("data");

                if ("00".equals(code)) {
                    String qrDataURL = data.path("qrDataURL").asText(); // Lấy Data URI
                    byte[] imageBytes = decodeDataURI(qrDataURL); // Giải mã dữ liệu
                    saveImage(imageBytes); // Hiển thị ảnh mà không lưu

                } else {
                    System.out.println("Error: " + jsonNode.path("desc").asText());
                }
            } else {
                System.out.println("Error: " + response.getStatusCode());
                System.out.println("Response Body: " + response.getBody());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

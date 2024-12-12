/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package function;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author Huu Thai
 */
public class AddImageToData {
//
    public String chuanHoaTenFile(String fileName) {
        String[] chuoi_cat = fileName.split("\\s");
        String fileName_1 = "";
        for (String x : chuoi_cat) {
            fileName_1 = fileName_1.concat(x);
        }
        return fileName_1;
    }

    public String duaFileVaoThuMuc(File oldPath, String folder, String url) {
        //Lấy tên file để chuẩn hóa
        String fileName = oldPath.getName();
        //Chuẩn hóa fileName
        fileName = chuanHoaTenFile(fileName);

        //Thư mục lưu file ảnh trong project
        File directory = new File(folder);

        //Đường dẫn trực tiếp của file
        String newPathFile = directory.getAbsolutePath() + "\\" + fileName;
        String hinhAnhPath = null;
        try {
            System.out.println(newPathFile);
            // Đường dẫn mới
            File newPath = new File(newPathFile);

            // Sử dụng Files.copy để copy file thay vì rename
            Files.copy(oldPath.toPath(), newPath.toPath(), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Copy file thành công!");
            hinhAnhPath = url + fileName;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return hinhAnhPath;
    }
}


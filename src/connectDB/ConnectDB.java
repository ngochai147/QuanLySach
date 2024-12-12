///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package connectDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Huu Thai
 */
//
public class ConnectDB {
    private static Connection con = null;
    private static ConnectDB instance = new ConnectDB();

    private ConnectDB() {
        connect();  // Mở kết nối ban đầu khi khởi tạo instance
    }

    // Phương thức mở kết nối
    private void connect() {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=QL_NhaSachFahasa;encrypt=true;trustServerCertificate=true;";
        String user = "sa";
        String password = "sapassword";
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Kết nối thành công tới cơ sở dữ liệu");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Đóng kết nối nếu không sử dụng nữa
    public void disconnect() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Kết nối đã được đóng");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static ConnectDB getInstance() {
        return instance;
    }

    public static Connection getConnection() {
        try {
            // Kiểm tra nếu kết nối bị đóng hoặc chưa khởi tạo
            if (con == null || con.isClosed()) {  
                instance.connect(); // Mở lại kết nối nếu cần
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}

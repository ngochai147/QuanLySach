/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package dao;
import connectDB.ConnectDB;

import entity.KhoHang;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
public class KhoHang_DAO {
    private final List<KhoHang> dsKhoHang;
    public KhoHang_DAO() {
        dsKhoHang = new ArrayList<>();
    }
    public KhoHang getKhoTheoTenKho(String tk) throws SQLException {
        Connection con = ConnectDB.getConnection();
        KhoHang khoHang = null;
        String sql = "select *\n"
                + "from KhoHang\n"
                + "where tenKho = ?";
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
        }
        statement.setString(1, tk);
        ResultSet rs = statement.executeQuery();
        while(rs.next()) {
            String maKho = rs.getString(1);
            String tenKho = rs.getString(2);
            int sucChua = rs.getInt(3);
            String diaChi = rs.getString(4);

            khoHang = new KhoHang(maKho, tenKho, sucChua, diaChi);
        }
        statement.close();
        return khoHang;
    }
}

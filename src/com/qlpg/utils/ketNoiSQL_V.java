package com.qlpg.utils;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
public class ketNoiSQL_V {
//public static void main(String[] args) {
//        try {
//            // Test the JDBC connection
//            String sql = "SELECT 1";
//            PreparedStatement preparedStatement = JdbcHelper.getStmt(sql);
//            // Lấy kết nối từ PreparedStatement
//            Connection connection = preparedStatement.getConnection();
//            if (connection != null) {
//                System.out.println("JDBC Connection successful!");
//                // Thực hiện các thao tác với kết nối ở đây
//                // Ví dụ: Hiển thị thông tin cơ sở dữ liệu
//                DatabaseMetaData metaData = connection.getMetaData();
//                System.out.println("Connected to: " + metaData.getDatabaseProductName());
//
//                // Đóng PreparedStatement sau khi sử dụng
//                preparedStatement.close();
//                // Đóng kết nối sau khi sử dụng
//                connection.close();
//            } else {
//                System.err.println("JDBC Connection failed!");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public static void main(String[] args) {
        // Thực hiện truy vấn để lấy dữ liệu từ bảng (ví dụ: SELECT * FROM TableName)
        String sqlQuery = "SELECT * FROM NguoiDung"; // Thay TableName bằng tên bảng thực tế của bạn
        try {
            // Thực hiện truy vấn
            ResultSet resultSet = JdbcHelper.query(sqlQuery);
            // Xuất kết quả
            while (resultSet.next()) {
                // Đọc dữ liệu từ các cột trong mỗi hàng
                String column1 = resultSet.getString("UserID"); // Thay "Column1" bằng tên cột thực tế
                String column2 = resultSet.getString("Password"); // Thay "Column2" bằng tên cột thực tế
                String column3 = resultSet.getString("VaiTro"); // Thay "Column2" bằng tên cột thực tế
                // ...
                // In ra dữ liệu của mỗi hàng
                System.out.println("UserID: " + column1 + ", Password: " + column2 + ", VaiTro: " + column3);
            }
            // Đóng kết nối ResultSet
            resultSet.getStatement().getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


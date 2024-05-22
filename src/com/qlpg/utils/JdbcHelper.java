/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlpg.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcHelper {
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String url = "jdbc:sqlserver://localhost:1433;database=qqq;encrypt=false";
    private static String user = "sa";
    private static String pass = "songlong";
     static {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
        // hàm getStmt dùng để phục vụ cho 3 hàm còn lại  Update, value, query
    public static PreparedStatement getStmt(String sql, Object... args) throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "ERROR CONNECTION");
        }
        if (connection == null) {
            System.err.print("Cannot connection to the Database Server MSSQL!");
        }
        PreparedStatement pstmt = null;
        // nếu bắt đầu với "{" thì ta sẽ gọi Procduce
        if (sql.trim().startsWith("{")) {
            pstmt = connection.prepareCall(sql);
        } else {
            // ngược lại sẽ là câu lệnh bình thường
            pstmt = connection.prepareStatement(sql);
        }
        // bây giờ đưa các giá trị vào bên trong dấu chấm hỏi
        // thì nó sẽ xem có bao nhiêu dấu chấm hỏi, thì sẽ có bấy nhiêu tham số tương ứng với dấu chấm hỏi
        for (int i = 0; i < args.length; i++) {
            pstmt.setObject(i + 1, args[i]);
        }
        // String sql = "SQL...,?,?,?,..."
        // PreparedStatement stmt = JDBCHelper.getStmt(SQL,a1,a2,a3)
        return pstmt;
    }
        // dùng để thao tác với dự liệu, dùng getStmt để lấy tham số đã truyền để thực hiện lệnh
    public static void update(String sql, Object... args) {
        try {
            PreparedStatement stmt = JdbcHelper.getStmt(sql, args);
            try {
                stmt.executeUpdate();
            } finally {
                stmt.getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // lấy duy nhất một giá trị thôi, ví dụ như select count(*), hãy lấy điểm cao nhất thì chỉ cần trả về 1 giá trị
    public static Object value(String sql, Object... args) {
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            if (rs.next()) {
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    // hàm query để truy xuất dữ liệu có thể có đối số hoặc không, nhưng không được thao tác với dữ liệu
    public static ResultSet query(String sql, Object... args) {
        try {
            PreparedStatement stmt = JdbcHelper.getStmt(sql, args);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    
}

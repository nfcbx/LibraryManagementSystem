package com.jdbc.records;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class user_borrow {
    public static void user_borrow_books(int user_id, int book_id, int days) {
        //时间相加
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyLocalizedPattern("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String nowDate = simpleDateFormat.format(date);
        System.out.println(nowDate);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        date = calendar.getTime();
        String addDate = simpleDateFormat.format(date);
        System.out.println(addDate);

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql:///library_management_system?useSSL=false&characterEncoding=utf8", "root", "/*-w123l/*-");
            String sql = "INSERT INTO library_management_system.records (user_id, book_id, borrow_time, return_time) VALUES (?, ?, ?, ?);";
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1, user_id);
            stmt.setObject(2, book_id);
            stmt.setObject(3, nowDate);
            stmt.setObject(4, addDate);

            stmt.executeUpdate();

            System.out.println("借书成功！");

        } catch (SQLException e) {
            System.out.println("借书失败！");
        }

    }
}

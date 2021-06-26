package com.jdbc.records;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import static com.tools.IsOrNot.isInteger;

public class get_records {
    public static Vector<Record> user_get_records(int user_id) {
        Vector<Record> user_records = new Vector<Record>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String sql = "SELECT records.`book_id`, books.`book_name`, books.`book_author`, books.`publishing_house`, DATE_FORMAT(records.`borrow_time`, '%Y-%m-%d'), DATE_FORMAT(records.`return_time`, '%Y-%m-%d') FROM records INNER JOIN books ON records.`book_id`= books.`book_id` WHERE user_id = ?;";
        try {
            connection = DriverManager.getConnection("jdbc:mysql:///library_management_system?useSSL=false&characterEncoding=utf8", "root", "/*-w123l/*-");
            statement = connection.prepareStatement(sql);
            statement.setObject(1, user_id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int book_id = Integer.parseInt(resultSet.getString(1));
                String book_name = resultSet.getString(2);
                String book_author = resultSet.getString(3);
                String publishing_house = resultSet.getString(4);
                String borrow_time = resultSet.getString(5);
                String return_time = resultSet.getString(6);
                boolean is_overdue = true;

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
                simpleDateFormat.applyLocalizedPattern("yyyy-MM-dd");
                Calendar calendar = Calendar.getInstance();
                Date now_date = calendar.getTime();
                Date return_date = simpleDateFormat.parse(return_time);

                if (now_date.before(return_date)) {
                    is_overdue = false;
                }

                System.out.println("*******************");
                Record record = new Record(book_id, book_name, book_author, publishing_house, borrow_time, return_time, is_overdue);
                record.user_toString();
                user_records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return user_records;
    }

    public static Vector<Record> user_search_records(int user_id, String info) {
        Vector<Record> user_records = new Vector<Record>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        if (isInteger(info)) {
            try {
                String sql = "SELECT records.`book_id`, books.`book_name`, books.`book_author`, books.`publishing_house`, DATE_FORMAT(records.`borrow_time`, '%Y-%m-%d'), DATE_FORMAT(records.`return_time`, '%Y-%m-%d')\n" +
                        "FROM records INNER JOIN books ON records.`book_id`= books.`book_id`\n" +
                        "WHERE user_id = ? AND (CAST(records.`book_id` AS CHAR) LIKE ? OR books.`book_name` LIKE ?) ;";
                connection = DriverManager.getConnection("jdbc:mysql:///library_management_system?useSSL=false&characterEncoding=utf8", "root", "/*-w123l/*-");
                statement = connection.prepareStatement(sql);
                statement.setObject(1, user_id);
                statement.setObject(2, "%" + info + "%");
                statement.setObject(3, "%" + info + "%");
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int book_id = Integer.parseInt(resultSet.getString(1));
                    String book_name = resultSet.getString(2);
                    String book_author = resultSet.getString(3);
                    String publishing_house = resultSet.getString(4);
                    String borrow_time = resultSet.getString(5);
                    String return_time = resultSet.getString(6);
                    boolean is_overdue = true;

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
                    simpleDateFormat.applyLocalizedPattern("yyyy-MM-dd");
                    Calendar calendar = Calendar.getInstance();
                    Date now_date = calendar.getTime();
                    Date return_date = simpleDateFormat.parse(return_time);

                    if (now_date.before(return_date)) {
                        is_overdue = false;
                    }

                    Record record = new Record(book_id, book_name, book_author, publishing_house, borrow_time, return_time, is_overdue);
                    record.user_toString();
                    user_records.add(record);
                }
            } catch (SQLException | ParseException throwables) {
                throwables.printStackTrace();
            }
        } else {
            try {
                String sql = "SELECT records.`book_id`, books.`book_name`, books.`book_author`, books.`publishing_house`, DATE_FORMAT(records.`borrow_time`, '%Y-%m-%d'), DATE_FORMAT(records.`return_time`, '%Y-%m-%d')\n" +
                        "FROM records INNER JOIN books ON records.`book_id`= books.`book_id`\n" +
                        "WHERE user_id = ? AND books.`book_name` LIKE ?;";
                connection = DriverManager.getConnection("jdbc:mysql:///library_management_system?useSSL=false&characterEncoding=utf8", "root", "/*-w123l/*-");
                statement = connection.prepareStatement(sql);
                statement.setObject(1, user_id);
                statement.setObject(2, "%" + info + "%");
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int book_id = Integer.parseInt(resultSet.getString(1));
                    String book_name = resultSet.getString(2);
                    String book_author = resultSet.getString(3);
                    String publishing_house = resultSet.getString(4);
                    String borrow_time = resultSet.getString(5);
                    String return_time = resultSet.getString(6);
                    boolean is_overdue = true;

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
                    simpleDateFormat.applyLocalizedPattern("yyyy-MM-dd");
                    Calendar calendar = Calendar.getInstance();
                    Date now_date = calendar.getTime();
                    Date return_date = simpleDateFormat.parse(return_time);

                    if (now_date.before(return_date)) {
                        is_overdue = false;
                    }

                    Record record = new Record(book_id, book_name, book_author, publishing_house, borrow_time, return_time, is_overdue);
                    record.user_toString();
                    user_records.add(record);
                }
            } catch (SQLException | ParseException throwables) {
                throwables.printStackTrace();
            }
        }
        return user_records;
    }
}

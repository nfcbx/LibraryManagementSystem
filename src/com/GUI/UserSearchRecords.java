package com.GUI;

import com.jdbc.records.Record;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import static com.jdbc.records.get_records.user_get_records;
import static com.jdbc.records.get_records.user_search_records;

public class UserSearchRecords {
    private JPanel user_search_records_panel;
    private JTable user_search_records_table;

    private String info;

    public static void user_search_records_GUI() {
        JFrame frame = new JFrame("UserSearchRecords");
        frame.setContentPane(new UserSearchRecords().user_search_records_panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(750, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("cache.txt"));
            info = bufferedReader.readLine();
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        String[] records_tableHeader = {"图书编号", "图书名", "作者名", "出版社", "借书时间", "应当归还时间", "是否逾期"};
        Vector<Record> records = user_search_records(UserBorrowBooks.id, info);
        int records_numRows = records.size();
        Object[][] records_data = new Object[records_numRows][7];
        for (int i = 0; i < records_numRows; i++) {
            records_data[i][0] = records.get(i).book_id;
            records_data[i][1] = records.get(i).book_name;
            records_data[i][2] = records.get(i).book_author;
            records_data[i][3] = records.get(i).publishing_house;
            records_data[i][4] = records.get(i).borrow_time;
            records_data[i][5] = records.get(i).return_time;
            if (records.get(i).is_overdue)
                records_data[i][6] = "已逾期";
            else records_data[i][6] = "未逾期";
        }
        DefaultTableModel record_default_table_model = new DefaultTableModel(records_data, records_tableHeader);
        user_search_records_table = new JTable(record_default_table_model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        user_search_records_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //单选
        JTableHeader records_tab_header = user_search_records_table.getTableHeader();                    //获取表头
        records_tab_header.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        records_tab_header.setPreferredSize(new Dimension(records_tab_header.getWidth(), 30));    //修改表头的高度
        user_search_records_table.setFont(new Font("微软雅黑", Font.PLAIN, 13));

    }
}

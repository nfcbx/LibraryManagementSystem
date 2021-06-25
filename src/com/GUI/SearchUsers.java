package com.GUI;

import com.Sout;
import com.jdbc.users.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Vector;

import static com.jdbc.managers.manager_get.manager_get_users_by;

public class SearchUsers {
    private JPanel search_panel;
    private JTable search_table;
    private DefaultTableModel search_book_default_table_model;
    private JScrollPane search_pane;

    public static void search_users_GUI() {
        JFrame frame = new JFrame("SearchUsers");
        frame.setContentPane(new SearchUsers().search_panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(750, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createUIComponents() {

        Vector<User> users = manager_get_users_by(UserManage.info);
        System.out.println("************************");
        Sout.soutVectorUsrs(users);
        System.out.println("************************");

        int numRows = users.size();
        Object[] tableHeader = new Object[]{"序号", "学工号", "姓名", "密码"};

        Object[][] data = new Object[numRows][5];
        for (int i = 0; i < numRows; i++) {
            data[i][0] = i + 1;
            data[i][1] = users.get(i).user_id;
            data[i][2] = users.get(i).user_name;
            data[i][3] = users.get(i).user_password;
        }

        search_book_default_table_model = new DefaultTableModel(data, tableHeader);
        search_table = new JTable(search_book_default_table_model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

//        setTableColumnSize(search_table, 0, 50, 50, 50);
//        setTableColumnSize(search_table, 1, 150, 150, 150);
//        setTableColumnSize(search_table, 2, 200, 200, 200);
//        setTableColumnSize(search_table, 3, 150, 150, 150);
//        setTableColumnSize(search_table, 4, 200, 200, 200);

        search_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //单选
        JTableHeader tab_header = search_table.getTableHeader();                    //获取表头
        tab_header.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));    //修改表头的高度
        search_table.setFont(new Font("微软雅黑", Font.PLAIN, 13));

    }
}

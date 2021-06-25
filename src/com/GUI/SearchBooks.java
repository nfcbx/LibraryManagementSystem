package com.GUI;

import com.Sout;
import com.jdbc.books.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Vector;

import static com.GUI.setColumnSize.setTableColumnSize;
import static com.jdbc.managers.manager_get.manager_get_books_by;

public class SearchBooks {
    private JPanel search_books_GUI;
    private JScrollPane search_books_pane;
    private JTable search_table;
    DefaultTableModel search_book_default_table_model;

    public static void search_books_GUI() {
        JFrame frame = new JFrame("SearchBooks");
        frame.setContentPane(new SearchBooks().search_books_GUI);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(750, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createUIComponents() {

        Vector<Book> books = manager_get_books_by(BookManage.info);
        Sout.soutVectorBooks(books);

        int numRows = books.size();
        Object[] tableHeader = new Object[]{"序号", "图书编号", "图书名", "作者", "出版社"};
        Object[][] data = new Object[numRows][5];
        for (int i = 0; i < numRows; i++) {
            data[i][0] = i + 1;
            data[i][1] = books.get(i).book_id;
            data[i][2] = books.get(i).book_name;
            data[i][3] = books.get(i).book_author;
            data[i][4] = books.get(i).publishing_house;
        }

        search_book_default_table_model = new DefaultTableModel(data, tableHeader);
        search_table = new JTable(search_book_default_table_model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        setTableColumnSize(search_table, 0, 50, 50, 50);
        setTableColumnSize(search_table, 1, 150, 150, 150);
        setTableColumnSize(search_table, 2, 200, 200, 200);
        setTableColumnSize(search_table, 3, 150, 150, 150);
        setTableColumnSize(search_table, 4, 200, 200, 200);

        search_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //单选
        JTableHeader tab_header = search_table.getTableHeader();                    //获取表头
        tab_header.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));    //修改表头的高度
        search_table.setFont(new Font("微软雅黑", Font.PLAIN, 13));

    }
}

package com.GUI;

import com.jdbc.books.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Vector;

import static com.GUI.setColumnSize.setTableColumnSize;
import static com.jdbc.managers.manager_get.manager_get_books;

public class UserBorrowBooks {
    String[] tableHeader = {"序号", "图书编号", "图书名", "作者", "出版社"};
    Object[] objects = new Object[]{"一天", "两天", "三天", "四天", "五天", "六天", "七天", "八天", "九天", "十天",
            "十一天", "十二天", "十三天", "十四天", "十五天"};
    private JPanel UserBorrowsBooks_GUI;
    private JTable books_table;
    private JTable records_table;
    private JTextField search_books_textField;
    private JButton search_book_button;
    private JTextField search_records_textField;
    private JButton search_records_button;
    private JComboBox borrow_book_comboBox;
    private JButton borrow_book_button;
    private JButton return_book_button;
    private JPanel books_panel;
    private JPanel serach_books_panel;
    private JPanel borrow_book_panel;
    private JScrollPane book_panel;
    private JLabel search_books_panel;
    private JPanel search_records_panel;
    private JLabel search_records_label;
    private JPanel records_panel;
    private JPanel return_book_panel;
    private JScrollPane return_book_pane;
    private DefaultTableModel book_default_table_model;

    public static void main(String[] args) {
        JFrame frame = new JFrame("UserBorrowBooks");
        frame.setContentPane(new UserBorrowBooks().UserBorrowsBooks_GUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here


        //book_table 的实现
        Vector<Book> books = manager_get_books();
        int numRows = books.size();
        Object[][] data = new Object[numRows][5];
        for (int i = 0; i < numRows; i++) {
            data[i][0] = i + 1;
            data[i][1] = books.get(i).book_id;
            data[i][2] = books.get(i).book_name;
            data[i][3] = books.get(i).book_author;
            data[i][4] = books.get(i).publishing_house;
        }

        book_default_table_model = new DefaultTableModel(data, tableHeader);
        books_table = new JTable(book_default_table_model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        setTableColumnSize(books_table, 0, 35, 35, 35);
//        setTableColumnSize(books_table, 1, 150, 150, 150);
//        setTableColumnSize(books_table, 2, 200, 200, 200);
//        setTableColumnSize(books_table, 3, 150, 150, 150);
//        setTableColumnSize(books_table, 4, 200, 200, 200);

        books_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //单选
        JTableHeader tab_header = books_table.getTableHeader();                    //获取表头
        tab_header.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));    //修改表头的高度
        books_table.setFont(new Font("微软雅黑", Font.PLAIN, 13));


        //combobox 的实现

        borrow_book_comboBox = new JComboBox(objects) {

        };


    }
}

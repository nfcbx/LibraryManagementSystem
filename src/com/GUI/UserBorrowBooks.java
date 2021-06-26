package com.GUI;

import com.jdbc.books.Book;
import com.jdbc.records.Record;
import com.jdbc.records.user_borrow;
import com.jdbc.records.user_return;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import static com.GUI.SearchBooks.search_books_GUI;
import static com.GUI.UserSearchRecords.user_search_records_GUI;
import static com.tools.setColumnSize.setTableColumnSize;
import static com.jdbc.managers.manager_get.manager_get_books;
import static com.jdbc.records.get_records.user_get_records;

public class UserBorrowBooks {

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
    private DefaultTableModel record_default_table_model;
    public static int id = -1;
    private int borrow_book_id = -1;
    private int return_book_id = -1;
    private String search_books;
    private String search_records;

    public UserBorrowBooks() {
        borrow_book_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (borrow_book_id == -1) {
                    JOptionPane.showMessageDialog(null, "请选择图书！", "提示", JOptionPane.WARNING_MESSAGE);

                } else {
                    user_borrow.user_borrow_books(id, borrow_book_id, borrow_book_comboBox.getSelectedIndex() + 1);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
                    simpleDateFormat.applyLocalizedPattern("yyyy-MM-dd");
                    Calendar calendar = Calendar.getInstance();
                    Date date = calendar.getTime();
                    String nowDate = simpleDateFormat.format(date);
                    calendar.add(Calendar.DAY_OF_MONTH, borrow_book_comboBox.getSelectedIndex() + 1);
                    date = calendar.getTime();
                    String addDate = simpleDateFormat.format(date);

                    Object[] data = new Object[]{borrow_book_id,
                            book_default_table_model.getValueAt(books_table.getSelectedRow(), 2),
                            book_default_table_model.getValueAt(books_table.getSelectedRow(), 3),
                            book_default_table_model.getValueAt(books_table.getSelectedRow(), 4),
                            nowDate, addDate, "未逾期"
                    };
                    record_default_table_model.addRow(data);
                }
            }
        });

        books_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                borrow_book_id = (int) book_default_table_model.getValueAt(books_table.getSelectedRow(), 1);
            }
        });

        return_book_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (return_book_id == -1) {
                    JOptionPane.showMessageDialog(null, "请选择图书！", "提示", JOptionPane.WARNING_MESSAGE);

                } else {
                    user_return.user_return_book(id, return_book_id);
                    record_default_table_model.removeRow(records_table.getSelectedRow());
                }
            }
        });

        records_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                return_book_id = (int) record_default_table_model.getValueAt(records_table.getSelectedRow(), 0);
            }
        });
        search_books_textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                search_books = search_books_textField.getText();
            }
        });
        search_book_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("cache.txt"));
                    bufferedWriter.write(search_books);
                    bufferedWriter.close();
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                search_books_GUI();
            }
        });
        search_records_textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                search_records = search_records_textField.getText();
            }
        });
        search_records_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("cache.txt"));
                    bufferedWriter.write(search_records);
                    bufferedWriter.close();
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                user_search_records_GUI();
            }
        });
    }

    public static void user_borrow_books_GUI() {
        JFrame frame = new JFrame("UserBorrowBooks");
        frame.setContentPane(new UserBorrowBooks().UserBorrowsBooks_GUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        id = Login.u_id;

        //book_table 的实现
        String[] book_tableHeader = {"序号", "图书编号", "图书名", "作者", "出版社"};
        Vector<Book> books = manager_get_books();
        int books_numRows = books.size();
        Object[][] books_data = new Object[books_numRows][5];
        for (int i = 0; i < books_numRows; i++) {
            books_data[i][0] = i + 1;
            books_data[i][1] = books.get(i).book_id;
            books_data[i][2] = books.get(i).book_name;
            books_data[i][3] = books.get(i).book_author;
            books_data[i][4] = books.get(i).publishing_house;
        }

        book_default_table_model = new DefaultTableModel(books_data, book_tableHeader);
        books_table = new JTable(book_default_table_model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        setTableColumnSize(books_table, 0, 35, 35, 35);

        books_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //单选
        JTableHeader books_tab_header = books_table.getTableHeader();                    //获取表头
        books_tab_header.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        books_tab_header.setPreferredSize(new Dimension(books_tab_header.getWidth(), 30));    //修改表头的高度
        books_table.setFont(new Font("微软雅黑", Font.PLAIN, 13));


        //combobox 的实现
        Object[] objects = new Object[]{"一天", "两天", "三天", "四天", "五天", "六天", "七天", "八天", "九天", "十天",
                "十一天", "十二天", "十三天", "十四天", "十五天"};
        borrow_book_comboBox = new JComboBox(objects) {

        };


        //records_table 的实现
        String[] records_tableHeader = {"图书编号", "图书名", "作者名", "出版社", "借书时间", "应当归还时间", "是否逾期"};
        Vector<Record> records = user_get_records(id);
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
        record_default_table_model = new DefaultTableModel(records_data, records_tableHeader);
        records_table = new JTable(record_default_table_model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        books_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //单选
        JTableHeader records_tab_header = books_table.getTableHeader();                    //获取表头
        records_tab_header.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        records_tab_header.setPreferredSize(new Dimension(records_tab_header.getWidth(), 30));    //修改表头的高度
        books_table.setFont(new Font("微软雅黑", Font.PLAIN, 13));

    }
}

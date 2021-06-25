package com.GUI;

import com.jdbc.books.Book;
import com.jdbc.managers.manager_update_books;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import static com.GUI.setColumnSize.setTableColumnSize;
import static com.IsOrNot.isInteger;
import static com.jdbc.managers.manager_get.manager_get_books;

public class BookManage {
    Vector<Book> books;
    String[] tableHeader = {"序号", "图书编号", "图书名", "作者", "出版社"};
    private int selectedRow;

    private DefaultTableModel book_default_table_model;

    private JButton add_user_button;
    private JButton change_button;
    private JButton delete_button;
    private JTextField search_textField;
    private JButton search_button;
    private JTable books_table;
    private JTextField book_id_textField;
    private JTextField book_name_textField;
    private JTextField book_author_textField;
    private JTextField book_pulish_textField;
    private JPanel operate_panel;
    private JPanel info_panel;
    private JScrollPane table_pane;
    private JLabel book_id_label;
    private JLabel book_name_label;
    private JLabel book_author_label;
    private JLabel book_publish_label;
    private JPanel search_panel;
    private JPanel books_manage_GUI;
    private JLabel search_label;

    private int book_id;
    private String book_name;
    private String book_author;
    private String publishing_house;
    private String search_String;
    public static String info;


    public BookManage() {
        add_user_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isInteger(book_id_textField.getText())) {
                    book_id = Integer.parseInt(book_id_textField.getText());
                    book_name = book_name_textField.getText();
                    book_author = book_author_textField.getText();
                    publishing_house = book_pulish_textField.getText();
                    if (manager_update_books.manager_add_books(book_id, book_name, book_author, publishing_house)) {
                        Vector<Book> bookVector = manager_get_books();
                        Object[] data = new Object[]{bookVector.size(), book_id, book_name, book_author, publishing_house};
                        book_default_table_model.addRow(data);
                        for (int i = 1; i <= book_default_table_model.getRowCount(); i++) {
                            book_default_table_model.setValueAt(i, i - 1, 0);
                        }
                        JOptionPane.showMessageDialog(null, "添加成功！", "提示", JOptionPane.WARNING_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(null, "添加失败！", "提示", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "图书编号为整数！", "提示", JOptionPane.WARNING_MESSAGE);

                }
            }
        });

        books_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                selectedRow = books_table.getSelectedRow();
                book_id_textField.setText(String.valueOf(book_default_table_model.getValueAt(selectedRow, 1)));
                book_name_textField.setText(String.valueOf(book_default_table_model.getValueAt(selectedRow, 2)));
                book_author_textField.setText(String.valueOf(book_default_table_model.getValueAt(selectedRow, 3)));
                book_pulish_textField.setText(String.valueOf(book_default_table_model.getValueAt(selectedRow, 4)));

                book_id = Integer.parseInt(book_id_textField.getText());
                book_name = book_name_textField.getText();
                book_author = book_author_textField.getText();
                publishing_house = book_pulish_textField.getText();
                System.out.println(book_id + "\t" + book_name + "\t" + book_author + "\t" + publishing_house);
            }
        });
        delete_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedRow = books_table.getSelectedRow();
                if ("".equals(book_id_textField.getText()) || "".equals(book_name_textField.getText()) || "".equals(book_author_textField.getText()) || "".equals(book_pulish_textField.getText())) {
                    JOptionPane.showMessageDialog(null, "请先选择图书！", "提示", JOptionPane.WARNING_MESSAGE);
                } else {
                    book_id = Integer.parseInt(book_id_textField.getText());
                    manager_update_books.manager_delete_books(book_id);
                    book_default_table_model.removeRow(selectedRow);
                    for (int i = 1; i <= book_default_table_model.getRowCount(); i++) {
                        book_default_table_model.setValueAt(i, i - 1, 0);
                    }
                    JOptionPane.showMessageDialog(null, "删除成功！", "提示", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        //todo 修改失败  ID名有冲突时
        change_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedRow = books_table.getSelectedRow();
                int old_book_id = (int) book_default_table_model.getValueAt(selectedRow, 1);
                if ("".equals(book_id_textField.getText()) || "".equals(book_name_textField.getText()) || "".equals(book_author_textField.getText()) || "".equals(book_pulish_textField.getText())) {
                    JOptionPane.showMessageDialog(null, "请先选择图书！", "提示", JOptionPane.WARNING_MESSAGE);
                } else {

                    if (manager_update_books.manager_change_books(book_id, old_book_id, book_name, book_author, publishing_house)) {
                        JOptionPane.showMessageDialog(null, "修改成功！", "提示", JOptionPane.WARNING_MESSAGE);
                        book_id = Integer.parseInt(book_id_textField.getText());
                        book_name = book_name_textField.getText();
                        book_author = book_author_textField.getText();
                        publishing_house = book_pulish_textField.getText();
                        book_default_table_model.setValueAt(book_id, selectedRow, 1);
                        book_default_table_model.setValueAt(book_name, selectedRow, 2);
                        book_default_table_model.setValueAt(book_author, selectedRow, 3);
                        book_default_table_model.setValueAt(publishing_house, selectedRow, 4);
                        for (int i = 1; i <= book_default_table_model.getRowCount(); i++) {
                            book_default_table_model.setValueAt(i, i - 1, 0);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "修改失败！", "提示", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        search_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                info = search_String;
                SearchBooks.search_books_GUI();

            }
        });
        search_textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                search_String = search_textField.getText();
            }
        });
    }

    public static void books_manage_GUI() {
        JFrame frame = new JFrame("BookManage");
        frame.setContentPane(new BookManage().books_manage_GUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private void createUIComponents() {
        books = manager_get_books();
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

        setTableColumnSize(books_table, 0, 50, 50, 50);
        setTableColumnSize(books_table, 1, 150, 150, 150);
        setTableColumnSize(books_table, 2, 200, 200, 200);
        setTableColumnSize(books_table, 3, 150, 150, 150);
        setTableColumnSize(books_table, 4, 200, 200, 200);

        books_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //单选
        JTableHeader tab_header = books_table.getTableHeader();                    //获取表头
        tab_header.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));    //修改表头的高度
        books_table.setFont(new Font("微软雅黑", Font.PLAIN, 13));

    }
}

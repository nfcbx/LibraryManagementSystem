package com.GUI;

import com.jdbc.users.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import static com.GUI.SearchUsers.search_users_GUI;
import static com.jdbc.managers.manager_get.manager_get_users;
import static com.jdbc.managers.manager_update_users.manager_change_users;
import static com.jdbc.managers.manager_update_users.manager_delete_users;
import static com.jdbc.users.user_register.user_register;

public class UserManage {
    Vector<User> user;
    String[] tableHeader = {"序号", "学工号", "姓名", "密码"};
    private int selectedRow;

    private JPanel UserManage;
    private JTextField search_textField;
    private JButton search_button;
    private JButton add_user_button;
    private JButton change_button;
    private JButton delete_button;
    private JTable user_table;
    private JPanel search_panel;
    private JScrollPane user_table_pane;
    private JPanel operate_panel;
    private JTextField user_id_textField;
    private JTextField user_name_textField;
    private JLabel user_id_label;
    private JLabel user_name_label;
    private JLabel password_label;
    private JTextField user_password_textField;
    private JPanel user_panel;
    private JLabel search_label;
    private DefaultTableModel user_default_table_model;

    public static String info;


    public UserManage() {
        user_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
//                user = manager_get_users();
                selectedRow = user_table.getSelectedRow();
                System.out.println(user);
                user_id_textField.setText(String.valueOf(user_default_table_model.getValueAt(selectedRow, 1)));
                user_name_textField.setText(String.valueOf(user_default_table_model.getValueAt(selectedRow, 2)));
                user_password_textField.setText(String.valueOf(user_default_table_model.getValueAt(selectedRow, 3)));
            }
        });
        change_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: 多次添加后会修改失败
                if (manager_change_users(user.get(selectedRow).user_index, Integer.parseInt(user_id_textField.getText()), user_name_textField.getText(), user_password_textField.getText())) {
                    JOptionPane.showMessageDialog(null, "修改成功！", "提示", JOptionPane.WARNING_MESSAGE);
                    user_default_table_model.setValueAt(user_id_textField.getText(), selectedRow, 1);
                    user_default_table_model.setValueAt(user_name_textField.getText(), selectedRow, 2);
                    user_default_table_model.setValueAt(user_password_textField.getText(), selectedRow, 3);
                } else {
                    JOptionPane.showMessageDialog(null, "修改失败！", "提示", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        add_user_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (user_register(Integer.parseInt(user_id_textField.getText()), user_name_textField.getText(), user_password_textField.getText())) {
                    JOptionPane.showMessageDialog(null, "添加成功！", "提示", JOptionPane.WARNING_MESSAGE);
                    Vector<User> user = manager_get_users();
                    int numRows = user.size();
                    Object[] data = new Object[]{numRows, user_id_textField.getText(), user_name_textField.getText(), user_password_textField.getText()};
                    user_default_table_model.addRow(data);
                    for (int i = 1; i <= user_default_table_model.getRowCount(); i++) {
                        user_default_table_model.setValueAt(i, i - 1, 0);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "添加失败！", "提示", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        delete_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(user_id_textField.getText());
                if (manager_delete_users(Integer.parseInt(user_id_textField.getText()))) {
                    JOptionPane.showMessageDialog(null, "删除成功！", "提示", JOptionPane.WARNING_MESSAGE);
                    user_default_table_model.removeRow(selectedRow);
                    for (int i = 1; i <= user_default_table_model.getRowCount(); i++) {
                        user_default_table_model.setValueAt(i, i - 1, 0);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "删除失败！", "提示", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        search_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search_users_GUI();
            }
        });
        search_textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                info = search_textField.getText();
            }
        });
    }

    public static void users_manager_GUI() {
        JFrame frame = new JFrame("用户管理");
        frame.setContentPane(new UserManage().UserManage);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 750);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createUIComponents() {

        user = manager_get_users();
        int numRows = user.size();
        Object[][] data = new Object[numRows][4];
        for (int i = 0; i < numRows; i++) {
            data[i][0] = i + 1;
            data[i][1] = user.get(i).user_id;
            data[i][2] = user.get(i).user_name;
            data[i][3] = user.get(i).user_password;
        }

        user_default_table_model = new DefaultTableModel(data, tableHeader);
        user_table = new JTable(user_default_table_model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        user_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //单选
        JTableHeader tab_header = user_table.getTableHeader();                    //获取表头
        tab_header.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));    //修改表头的高度
        user_table.setFont(new Font("微软雅黑", Font.PLAIN, 13));

//        user_table.setRowSelectionInterval(0, 0);  //设置选中第index0行到第index1行


    }
}


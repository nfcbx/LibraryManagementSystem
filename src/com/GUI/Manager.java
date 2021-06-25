package com.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manager {
    static JFrame frame = new JFrame("管理界面");
    private JButton user_manage_button;
    private JButton book_manage_button;
    private JPanel users_manage;
    private JPanel books_manage;
    private JPanel Manager;
    private JLabel text;

    public Manager() {
        user_manage_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserManage.users_manager_GUI();
            }
        });


        book_manage_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookManage.books_manage_GUI();
            }
        });
    }

    public static void manager_GUI() {
        frame.setContentPane(new Manager().Manager);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 580);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

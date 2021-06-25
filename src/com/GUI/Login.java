package com.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.IsOrNot.isInteger;
import static com.jdbc.managers.manager_login.manager_login;
import static com.jdbc.users.user_login.user_login;

public class Login {
    static JFrame frame = new JFrame("欢迎使用图书管理系统");
    private JPanel login_panel;
    private JButton login_in;
    private JLabel login;
    private JButton sign_up;
    private JCheckBox manger_select;
    private JPanel login_header_panel;
    private JPanel login_text_panel;
    private JPanel login_button_panel;
    private JPanel username_panel;
    private JPanel password_panel;
    private JTextField user_id_textField;
    private JPasswordField user_passwordField;
    private JLabel username_label;
    private JLabel password_label;

    public Login() {
        sign_up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register.register_GUI();
            }
        });
        login_in.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (manger_select.isSelected()) {
                    if ("".equals(user_id_textField.getText()) || "".equals(String.valueOf(user_passwordField.getPassword()))) {
                        JOptionPane.showMessageDialog(null, "请将用户名密码补全完整", "提示", JOptionPane.WARNING_MESSAGE);
                    } else {
                        if (isInteger(String.valueOf(user_id_textField.getText()))) {
                            int id = Integer.parseInt(user_id_textField.getText());
                            String password = String.valueOf(user_passwordField.getPassword());
                            if (manager_login(id, password)) {
                                System.out.println("管理员登录成功");
                                frame.dispose();
                                Manager.manager_GUI();
                            } else {
                                JOptionPane.showMessageDialog(null, "用户名或密码不正确", "提示", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "用户名或密码不正确", "提示", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                } else {
                    if ("".equals(user_id_textField.getText()) || "".equals(String.valueOf(user_passwordField.getPassword()))) {
                        JOptionPane.showMessageDialog(null, "请将用户名密码补全完整", "提示", JOptionPane.WARNING_MESSAGE);
                    } else {
                        if (isInteger(String.valueOf(user_id_textField.getText()))) {
                            int id = Integer.parseInt(user_id_textField.getText());
                            String password = String.valueOf(user_passwordField.getPassword());
                            if (user_login(id, password)) {
                                System.out.println("用户登录成功");
                            } else {
                                JOptionPane.showMessageDialog(null, "用户名或密码不正确", "提示", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "用户名或密码不正确", "提示", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }

            }
        });
    }

    public static void Login_GUI() {
        frame.setContentPane(new Login().login_panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 250);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

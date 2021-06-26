package com.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.tools.IsOrNot.isInteger;
import static com.jdbc.users.user_register.user_register;

public class Register {
    int user_id;
    String user_name, user_password;

    static JFrame frame;
    private JPanel register_panel;
    private JLabel register_label;
    private JPanel register_header;
    private JPanel register_button_panel;
    private JButton register_button;
    private JPanel login_text_panel;
    private JPanel username_panel;
    private JLabel password_label;
    private JPasswordField user_passwordField;
    private JPanel user_id_panel;
    private JLabel user_id_label;
    private JTextField user_id_textField;
    private JTextField user_name_TextField;
    private JPanel password_panel;
    private JLabel username_label;

    public Register() {
        register_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if ("".equals(user_name_TextField.getText()) || "".equals(String.valueOf(user_passwordField.getPassword())) || "".equals(user_id_textField.getText())) {
                    JOptionPane.showMessageDialog(null, "请补全上述字段！", "提示", JOptionPane.WARNING_MESSAGE);

                } else {
                    if (!isInteger(user_id_textField.getText()))
                        JOptionPane.showMessageDialog(null, "学工号必须为整数！！", "提示", JOptionPane.WARNING_MESSAGE);
                    else {
                        user_id = Integer.parseInt(user_id_textField.getText());
                        System.out.println(user_id);
                    }
                    user_name = user_name_TextField.getText();
                    user_password = String.valueOf(user_passwordField.getPassword());
                    System.out.println(user_name);
                    System.out.println(user_password);
                    System.out.println("点击了注册按钮");

                    if(user_register(user_id, user_name, user_password)) {
                        JOptionPane.showMessageDialog(null, "注册成功", "提示", JOptionPane.WARNING_MESSAGE);
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "注册失败！", "提示", JOptionPane.WARNING_MESSAGE);
                    }
                }

            }
        });
    }

    public static void register_GUI() {
        frame = new JFrame("注册");
        frame.setContentPane(new Register().register_panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 250);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

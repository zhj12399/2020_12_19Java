package com.ZHJ.Login;
/**
 * @author ZHJ
 * @date 2020/11/26
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import com.ZHJ.Things.People;
import com.ZHJ.dbPeople.*;

public class Register extends JFrame {
    public Register() {
        JFrame MYFrame = new JFrame();
        MYFrame.setBounds(200, 200, 300, 200);
        MYFrame.setTitle("注册界面");
        Container c = MYFrame.getContentPane();
        c.setBackground(new Color(200, 200, 255));
        MYFrame.setResizable(false);
        MYFrame.setLayout(null);

        JLabel LL = new JLabel("欢迎注册校园互助软件！");
        LL.setBounds(40, 20, 150, 20);
        MYFrame.add(LL);

        JLabel L1 = new JLabel("请输入您的用户名：");
        L1.setBounds(40, 50, 160, 20);
        MYFrame.add(L1);

        JTextField T1 = new JTextField(50);
        T1.setBounds(180, 50, 100, 20);
        MYFrame.add(T1);

        JLabel L2 = new JLabel("请输入您的密码：");
        L2.setBounds(40, 80, 160, 20);
        MYFrame.add(L2);

        JPasswordField T2 = new JPasswordField(50);
        T2.setEchoChar('*');
        T2.setBounds(180, 80, 100, 20);
        MYFrame.add(T2);

        JLabel L3 = new JLabel("请再次输入您的密码：");
        L3.setBounds(40, 110, 160, 20);
        MYFrame.add(L3);

        JPasswordField T3 = new JPasswordField(50);
        T3.setEchoChar('*');
        T3.setBounds(180, 110, 100, 20);
        MYFrame.add(T3);

        JButton Btn1 = new JButton("确定");
        Btn1.setBounds(60, 140, 60, 30);
        Btn1.addActionListener((ActionEvent e) -> {
            String name = T1.getText();
            char[] ch2 = T2.getPassword();
            String password2 = new String(ch2);
            char[] ch3 = T3.getPassword();
            String password3 = new String(ch3);
            PeopleRepository PeopleRepository = null;
            try {
                PeopleRepository = new DBPeopleManager();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (!PeopleRepository.NameExist(name)) {
                if (password2.equals(password3)) {
                    PeopleRepository = null;
                    try {
                        PeopleRepository = new DBPeopleManager();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    if(PeopleRepository.AddPeople(new People(name,password2)))
                    {
                        JOptionPane.showMessageDialog(null, "注册成功！");
                        MYFrame.setVisible(false);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "您输入的密码不一致！");
                    T2.setText("");
                    T3.setText("");
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "您输入的用户名已被注册！");
                T1.setText("");
                T2.setText("");
                T3.setText("");
            }
        });
        MYFrame.add(Btn1);

        JButton Btn2 = new JButton("清空");
        Btn2.setBounds(120, 140, 60, 30);
        Btn2.addActionListener((ActionEvent e) -> {
            T1.setText("");
            T2.setText("");
            T3.setText("");
        });
        MYFrame.add(Btn2);

        JButton Btn3 = new JButton("取消");
        Btn3.setBounds(200, 140, 60, 30);
        Btn3.addActionListener((ActionEvent e) -> {
            MYFrame.setVisible(false);
            Login login = new Login();
        });
        MYFrame.add(Btn3);

        MYFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        MYFrame.setVisible(true);
    }

}

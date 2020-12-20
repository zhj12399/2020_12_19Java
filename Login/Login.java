package com.ZHJ.Login;
/**
 * @author ZHJ
 * @date 2020/11/26
 */
import com.ZHJ.Home.Home;
import com.ZHJ.dbPeople.DBPeopleManager;
import com.ZHJ.dbPeople.PeopleRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class Login extends JFrame {
    public Login() {
        JFrame MYFrame = new JFrame();
        MYFrame.setBounds(200, 200, 300, 200);
        MYFrame.setTitle("登陆界面");
        Container c = MYFrame.getContentPane();
        c.setBackground(new Color(200, 200, 255));
        MYFrame.setLayout(null);
        MYFrame.setResizable(false);

        JLabel LL = new JLabel("欢迎来到校园互助软件！");
        LL.setBounds(40, 20, 150, 20);
        MYFrame.add(LL);

        JLabel L1 = new JLabel("用户名：");
        L1.setBounds(40, 50, 55, 20);
        MYFrame.add(L1);

        JTextField T1 = new JTextField(50);
        T1.setBounds(100, 50, 100, 20);
        MYFrame.add(T1);

        JLabel L2 = new JLabel("密码：");
        L2.setBounds(40, 80, 55, 20);
        MYFrame.add(L2);

        JPasswordField T2 = new JPasswordField(50);
        T2.setEchoChar('*');
        T2.setBounds(100, 80, 100, 20);
        MYFrame.add(T2);

        JButton Btn1 = new JButton("确定");
        Btn1.setBounds(40, 120, 80, 30);
        Btn1.addActionListener((ActionEvent e) -> {
            String name = T1.getText();
            char []ch = T2.getPassword();
            String password = new String(ch);
            PeopleRepository repository = null;
            try {
                repository = new DBPeopleManager();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (repository.NameExist(name)) {
                try {
                    repository = new DBPeopleManager();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                if (repository.PassWordCorrect(name, password)) {
                    JOptionPane.showMessageDialog(null, "登录成功");
                    MYFrame.setVisible(false);
                    Home home=new Home(name);
                }
                else {
                    JOptionPane.showMessageDialog(null, "密码错误");
                    T2.setText("");
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "请您注册账号");
                T1.setText("");
                T2.setText("");
            }
        });
        MYFrame.add(Btn1);

        JButton Btn2 = new JButton("清空");
        Btn2.setBounds(120, 120, 80, 30);
        Btn2.addActionListener((ActionEvent e) -> {
            T1.setText("");
            T2.setText("");
        });
        MYFrame.add(Btn2);

        MYFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        MYFrame.setVisible(true);
    }
}

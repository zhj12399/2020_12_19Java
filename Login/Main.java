package com.ZHJ.Login;

import com.ZHJ.Home.Home;
import com.ZHJ.dbPeople.DBPeopleManager;
import com.ZHJ.dbPeople.PeopleRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

/**
 * @auther ZHJ
 * @date 2020/12/5
 */
public class Main extends JFrame {
    public Main(){
        JFrame MYFrame = new JFrame();
        MYFrame.setBounds(200, 200, 300, 200);
        MYFrame.setTitle("主界面");
        Container c = MYFrame.getContentPane();
        c.setBackground(new Color(200, 200, 255));
        MYFrame.setLayout(null);
        MYFrame.setResizable(false);

        JLabel LL = new JLabel("欢迎来到校园互助软件！");
        LL.setBounds(80, 20, 150, 20);
        MYFrame.add(LL);

        JButton Btn1 = new JButton("登陆");
        Btn1.setBounds(40, 120, 100, 30);
        Btn1.addActionListener((ActionEvent e) -> {
            Login login=new Login();
        });
        MYFrame.add(Btn1);

        JButton Btn3 = new JButton("注册");
        Btn3.setBounds(160, 120, 100, 30);
        Btn3.addActionListener((ActionEvent e) -> {
            Register register=new Register();
        });
        MYFrame.add(Btn3);

        MYFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MYFrame.setVisible(true);
    }
    public static void main(String []args){
        Main main=new Main();
    }

}

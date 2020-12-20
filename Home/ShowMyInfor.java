package com.ZHJ.Home;
/**
 * @author ZHJ
 * @date 2020/12/1
 */

import com.ZHJ.Login.Login;
import com.ZHJ.Things.People;
import com.ZHJ.dbMenu.MenuRepository;
import com.ZHJ.dbMenu.dbMenuManager;
import com.ZHJ.dbPeople.DBPeopleManager;
import com.ZHJ.dbPeople.PeopleRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class ShowMyInfor extends JFrame {
    public ShowMyInfor(String name) {
        JFrame MYFrame = new JFrame();
        MYFrame.setBounds(200, 200, 350, 200);
        MYFrame.setTitle("个人信息");
        Container c = MYFrame.getContentPane();
        c.setBackground(new Color(200, 200, 255));
        MYFrame.setResizable(false);
        MYFrame.setLayout(null);

        PeopleRepository peoplerepository = null;
        try {
            peoplerepository = new DBPeopleManager();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        People people = peoplerepository.FindPeople(name);
        JLabel L1 = new JLabel("账号：");
        L1.setBounds(45, 15, 50, 20);
        MYFrame.add(L1);

        JLabel L2 = new JLabel(people.GetName());
        L2.setBounds(85, 15, 100, 20);
        MYFrame.add(L2);

        JLabel L3 = new JLabel("余额：");
        L3.setBounds(45, 50, 50, 20);
        MYFrame.add(L3);

        JLabel L4 = new JLabel(String.valueOf(people.GetProperty()));
        L4.setBounds(85, 50, 100, 20);
        MYFrame.add(L4);

        JButton jb1 = new JButton("我的发布");
        jb1.setBounds(45, 90, 80, 30);
        jb1.addActionListener((ActionEvent e) -> {
            MYFrame.setVisible(false);
            ShowMyMenus showMyMenus = new ShowMyMenus(name);
        });
        MYFrame.add(jb1);

        JButton jb2 = new JButton("我的接单");
        jb2.setBounds(45, 125, 80, 30);
        jb2.addActionListener((ActionEvent e) -> {
            MYFrame.setVisible(false);
            ShowMyReceive showMyReceive = new ShowMyReceive(name);
        });
        MYFrame.add(jb2);

        JButton jb3 = new JButton("余额充值");
        jb3.setBounds(180, 50, 80, 30);
        jb3.addActionListener((ActionEvent e) -> {
            MYFrame.setVisible(false);
            Bonus bonus = new Bonus(name);
        });
        MYFrame.add(jb3);

        JButton jb4 = new JButton("注销账户");
        jb4.setBounds(180, 90, 80, 30);
        jb4.addActionListener((ActionEvent e) -> {
            MYFrame.setVisible(false);
            PeopleRepository peopleRepository = null;
            try {
                peopleRepository = new DBPeopleManager();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (peopleRepository.DeletePeople(name)) {
                JOptionPane.showMessageDialog(null, "成功注销账户\n感谢使用！\n您的余额请联系管理员获得\n谢谢使用!");
                Login login = new Login();
            }
        });
        MYFrame.add(jb4);

        JButton jb5 = new JButton("返回主界面");
        jb5.setBounds(200, 140, 100, 30);
        jb5.addActionListener((ActionEvent e) -> {
            MYFrame.setVisible(false);
            Home home = new Home(name);
        });
        MYFrame.add(jb5);

        MYFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        MYFrame.setVisible(true);
    }
}

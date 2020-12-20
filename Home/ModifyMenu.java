package com.ZHJ.Home;
/**
 * @author ZHJ
 * @date 2020/12/3
 */

import com.ZHJ.Login.Login;
import com.ZHJ.Things.Menu;
import com.ZHJ.dbMenu.MenuRepository;
import com.ZHJ.dbMenu.dbMenuManager;
import com.ZHJ.dbPeople.DBPeopleManager;
import com.ZHJ.dbPeople.PeopleRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class ModifyMenu extends JFrame {
    public ModifyMenu(String name, Menu OldMenu, String From) {
        JFrame MYFrame = new JFrame();
        MYFrame.setBounds(200, 200, 450, 420);
        MYFrame.setTitle("修改信息");
        Container c = MYFrame.getContentPane();
        c.setBackground(new Color(200, 200, 255));
        MYFrame.setResizable(false);
        MYFrame.setLayout(null);

        JLabel LL = new JLabel("修改您的求助信息");
        LL.setBounds(140, 20, 150, 20);
        MYFrame.add(LL);

        JLabel L1 = new JLabel("请输入您的求助内容：");
        L1.setBounds(40, 50, 160, 20);
        MYFrame.add(L1);

        JTextField T1 = new JTextField(50);
        T1.setText(OldMenu.GetText());
        T1.setBounds(40, 80, 330, 230);
        MYFrame.add(T1);

        JLabel L2 = new JLabel("请输入您的悬赏金额：");
        L2.setBounds(40, 320, 160, 20);
        MYFrame.add(L2);

        JTextField T2 = new JTextField(50);
        T2.setText(String.valueOf(OldMenu.GetMoney()));
        T2.setBounds(180, 320, 100, 20);
        MYFrame.add(T2);

        JButton Btn1 = new JButton("确定");
        Btn1.setBounds(40, 350, 80, 30);

        Btn1.addActionListener((ActionEvent e) -> {
            MYFrame.setVisible(false);
            String text = T1.getText();
            String bonusstring = T2.getText();
            try {
                int bonus = Integer.valueOf(bonusstring);
                if (text.length() > 0) {
                    MenuRepository menurepository = null;
                    try {
                        menurepository = new dbMenuManager();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    Menu NewMenu = new Menu(text, name, bonus);
                    if (menurepository.ModifyMenu(OldMenu, NewMenu)) {
                        JOptionPane.showMessageDialog(null, "成功修改求助!");
                        if (From.equals("Home")) {//从home来的
                            Home home = new Home(name);
                        }
                        else if (From.equals("ShowMyMenus")) {
                            ShowMyMenus showMyMenus = new ShowMyMenus(name);
                        }
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "请勿发布空信息!");
                }
            } catch (Exception ee) {
                JOptionPane.showMessageDialog(null, "请输入整数金额!");
            }
        });
        MYFrame.add(Btn1);

        JButton Btn2 = new JButton("清空");
        Btn2.setBounds(120, 350, 80, 30);
        Btn2.addActionListener((ActionEvent e) -> {
            T1.setText("");
            T2.setText("");
        });
        MYFrame.add(Btn2);

        JButton Btn3 = new JButton("取消");
        Btn3.setBounds(220, 350, 80, 30);
        Btn3.addActionListener((ActionEvent e) -> {
            MYFrame.setVisible(false);
            if (From.equals("Home")) {//从home来的
                Home home = new Home(name);
            }
            else if (From.equals("ShowMyMenus")) {
                ShowMyMenus showMyMenus = new ShowMyMenus(name);
            }
        });
        MYFrame.add(Btn3);

        MYFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        MYFrame.setVisible(true);
    }
}

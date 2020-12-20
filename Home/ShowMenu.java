package com.ZHJ.Home;
/**
 * @author ZHJ
 * @date 2020/12/1
 */

import com.ZHJ.Login.Login;
import com.ZHJ.Things.Menu;
import com.ZHJ.Things.People;
import com.ZHJ.dbMenu.MenuRepository;
import com.ZHJ.dbMenu.dbMenuManager;
import com.ZHJ.dbPeople.DBPeopleManager;
import com.ZHJ.dbPeople.PeopleRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class ShowMenu extends JFrame {
    private boolean FlagSamePeople = true;

    public ShowMenu(String name, int ID, String From) {
        JFrame MYFrame = new JFrame();
        MYFrame.setBounds(200, 200, 400, 370);
        MYFrame.setTitle("内容展示");
        Container c = MYFrame.getContentPane();
        c.setBackground(new Color(200, 200, 255));
        MYFrame.setResizable(false);
        MYFrame.setLayout(null);

        MenuRepository menuRepository = null;
        try {
            menuRepository = new dbMenuManager();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        List<Menu> list = menuRepository.check();
        Menu menu = new Menu();
        menu = list.get(ID);
        if (menu.GetName().equals(name)) {
            FlagSamePeople = true;//名字相同说明是自己在查询自己的信息
        }
        else {
            FlagSamePeople = false;
        }

        final Menu menufinal = menu;

        JLabel L1 = new JLabel("编号：");
        L1.setBounds(45, 15, 50, 20);
        MYFrame.add(L1);

        JLabel L2 = new JLabel(String.valueOf(ID + 1));
        L2.setBounds(85, 15, 100, 20);
        MYFrame.add(L2);

        JLabel L3 = new JLabel("内容：");
        L3.setBounds(45, 60, 50, 20);
        MYFrame.add(L3);

        JLabel L4 = new JLabel("<html>" + menu.GetText() + "</html>");
        L4.setBounds(85, 60, 250, 150);
        MYFrame.add(L4);

        JLabel L5 = new JLabel("金额：");
        L5.setBounds(45, 210, 50, 20);
        MYFrame.add(L5);

        JLabel L6 = new JLabel(String.valueOf(menu.GetMoney()));
        L6.setBounds(85, 210, 100, 20);
        MYFrame.add(L6);

        JLabel L9 = new JLabel("发布人：");
        L9.setBounds(33, 37, 60, 20);
        MYFrame.add(L9);

        JLabel L7 = new JLabel("接单状态：");
        L7.setBounds(20, 240, 80, 20);
        MYFrame.add(L7);

        JLabel L8 = new JLabel();
        if (FlagSamePeople) {//是自己查询自己发布的信息
            JLabel L10 = new JLabel("我");
            L10.setBounds(85, 37, 100, 20);
            MYFrame.add(L10);

            if (menu.GetFlag()) {//已经被接单了，就不能修改信息，只能有完成订单的操作
                L8.setText("已接单");
                JLabel L11 = new JLabel("接单人：" + menu.GetNameWant());
                L11.setBounds(33, 270, 150, 20);
                MYFrame.add(L11);

                JButton jb1 = new JButton("完成订单");
                jb1.setBounds(60, 300, 80, 30);
                jb1.addActionListener((ActionEvent e) -> {//此时是完成订单的操作，需要资金转移操作
                    MenuRepository menuRepository1 = null;
                    try {
                        menuRepository1 = new dbMenuManager();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    menuRepository1.DeleteMenu(menufinal);

                    PeopleRepository peopleRepository1 = null;
                    try {
                        peopleRepository1 = new DBPeopleManager();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    People peopleFa = peopleRepository1.FindPeople(menufinal.GetName());
                    PeopleRepository peopleRepository2 = null;
                    try {
                        peopleRepository2 = new DBPeopleManager();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    People peopleWant = peopleRepository2.FindPeople(menufinal.GetNameWant());
                    PeopleRepository peopleRepository3 = null;
                    try {
                        peopleRepository3 = new DBPeopleManager();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    peopleRepository3.ModifyMoney(peopleWant, menufinal.GetMoney(), "+");
                    PeopleRepository peopleRepository4 = null;
                    try {
                        peopleRepository4 = new DBPeopleManager();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    peopleRepository4.ModifyMoney(peopleFa, menufinal.GetMoney(), "-");

                    MYFrame.setVisible(false);
                    JOptionPane.showMessageDialog(null, "订单完成！");
                    if (From.equals("Home")) {
                        Home home = new Home(name);
                    }
                    else if (From.equals("ShowMyMenus")) {
                        ShowMyMenus showMyMenus = new ShowMyMenus(name);
                    }
                });
                MYFrame.add(jb1);
            }
            else {//自己查询自己订单，且未被别人下单的时候才可以修改信息
                L8.setText("未接单");
                JButton jb1 = new JButton("修改信息");
                jb1.setBounds(40, 260, 80, 30);
                jb1.addActionListener((ActionEvent e) -> {
                    MYFrame.setVisible(false);
                    ModifyMenu modifyMenu = new ModifyMenu(name, menufinal, From);
                });
                MYFrame.add(jb1);

                JButton jb2 = new JButton("删除信息");
                jb2.setBounds(140, 260, 80, 30);
                jb2.addActionListener((ActionEvent e) -> {
                    MenuRepository menuRepository1 = null;
                    try {
                        menuRepository1 = new dbMenuManager();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    menuRepository1.DeleteMenu(menufinal);
                    MYFrame.setVisible(false);
                    JOptionPane.showMessageDialog(null, "成功删除信息！");
                    if (From.equals("Home")) {
                        Home home = new Home(name);
                    }
                    else if (From.equals("ShowMyMenus")) {
                        ShowMyMenus showMyMenus = new ShowMyMenus(name);
                    }
                });
                MYFrame.add(jb2);
            }
        }
        else {//在查看别人的单子
            JLabel L10 = new JLabel(menu.GetName());
            L10.setBounds(85, 37, 100, 20);
            MYFrame.add(L10);
            if (menu.GetFlag()) {//别人的单子已经被自己接了
                L8.setText("已接单");
                JLabel L11 = new JLabel("接单人：我");
                L11.setBounds(33, 270, 150, 20);
                MYFrame.add(L11);
            }
            else {
                L8.setText("未接单");

                JButton jb1 = new JButton("接单");
                jb1.setBounds(60, 300, 80, 30);
                jb1.addActionListener((ActionEvent e) -> {
                    MenuRepository menuRepository1 = null;
                    try {
                        menuRepository1 = new dbMenuManager();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    menuRepository1.AcceptMenu(menufinal,name);

                    MYFrame.setVisible(false);
                    JOptionPane.showMessageDialog(null, "成功接单！");
                    if (From.equals("Home")) {
                        Home home = new Home(name);
                    }
                });
                MYFrame.add(jb1);
            }
        }
        L8.setBounds(85, 240, 100, 20);
        MYFrame.add(L8);

        JButton jb3 = new JButton("返回");
        jb3.setBounds(260, 300, 80, 30);
        jb3.addActionListener((ActionEvent e) -> {
            MYFrame.setVisible(false);
            if (From.equals("Home")) {
                Home home = new Home(name);
            }
            else if (From.equals("ShowMyMenus")) {
                ShowMyMenus showMyMenus = new ShowMyMenus(name);
            }
            else if (From.equals("ShowMyReceive")) {
                ShowMyReceive showMyReceive = new ShowMyReceive(name);
            }
        });
        MYFrame.add(jb3);

        MYFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        MYFrame.setVisible(true);
    }
}

package com.ZHJ.Home;
/**
 * @author ZHJ
 * @date 2020/11/29
 */

import com.ZHJ.Login.Login;
import com.ZHJ.Login.Register;
import com.ZHJ.Things.Menu;
import com.ZHJ.dbMenu.MenuRepository;
import com.ZHJ.dbMenu.dbMenuManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class Home extends JFrame {
    public Home(String name) {
        JFrame MYFrame = new JFrame();
        MYFrame.setBounds(200, 200, 430, 420);
        MYFrame.setTitle("信息面板");
        MYFrame.setResizable(false);
        MYFrame.setLocationRelativeTo(null);
        MYFrame.setLayout(null);//采用坐标布局

        MenuRepository repository = null;
        try {
            repository = new dbMenuManager();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        List<Menu> list = repository.check();

        String[] rowName = {"编号", "内容", "金额", "发布人"};
        int DataNum = 0;
        for (int i = 0; i < list.size(); i++) {//仅展示未被接单的单子
            Menu menu = list.get(i);
            if (!menu.GetFlag()) {
                DataNum++;
            }
        }
        Object[][] data = new Object[DataNum][4];
        for (int i = 0, j = 0; i < list.size(); i++) {
            Menu menu = list.get(i);
            if (!menu.GetFlag()) {
                Object[] s = {i + 1, menu.GetText(), menu.GetMoney(), menu.GetName()};
                data[j++] = s;
            }
        }
        final JTable table = new JTable(data, rowName);
        JScrollPane JSP = new JScrollPane(table);//这一步不能省去，否则显示不出列名
        JSP.setBounds(20, 10, 400, 200);
        MYFrame.add(JSP);

        JLabel L1 = new JLabel("您的ID：" + name);
        L1.setBounds(20, 220, 120, 20);
        MYFrame.add(L1);

        JButton jb1 = new JButton("查看信息");
        jb1.setBounds(170, 220, 100, 30);
        jb1.addActionListener((ActionEvent e) -> {
            int row = table.getSelectedRow();//选中第几行
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "您没有选中信息");
                return;
            }
            int ID = (int) data[row][0];
            MYFrame.setVisible(false);
            ShowMenu showMenu = new ShowMenu(name, ID - 1, "Home");
        });
        MYFrame.add(jb1);

        JButton jb2 = new JButton("发布求助");
        jb2.setBounds(45, 280, 100, 30);
        jb2.addActionListener((ActionEvent e) -> {
            MYFrame.setVisible(false);
            UpdateMenu updateMenu = new UpdateMenu(name, "Home");
        });
        MYFrame.add(jb2);

        JButton jb3 = new JButton("我的发布");
        jb3.setBounds(170, 280, 100, 30);
        jb3.addActionListener((ActionEvent e) -> {
            MYFrame.setVisible(false);
            ShowMyMenus showMyMenus = new ShowMyMenus(name);
        });
        MYFrame.add(jb3);

        JButton jbFresh = new JButton("刷新");
        jbFresh.setBounds(270, 220, 100, 30);
        jbFresh.addActionListener((ActionEvent e) -> {
            MYFrame.setVisible(false);
            Home home = new Home(name);
        });

        MYFrame.add(jbFresh);
        JButton jb4 = new JButton("我的接单");
        jb4.setBounds(170, 320, 100, 30);
        jb4.addActionListener((ActionEvent e) -> {
            MYFrame.setVisible(false);
            ShowMyReceive showMyReceive = new ShowMyReceive(name);
        });
        MYFrame.add(jb4);

        JButton jb5 = new JButton("我的信息");
        jb5.setBounds(280, 280, 100, 30);
        jb5.addActionListener((ActionEvent e) -> {
            ShowMyInfor showMyInfor = new ShowMyInfor(name);
            MYFrame.setVisible(false);
        });
        MYFrame.add(jb5);

        JButton jb6 = new JButton("退出登陆");
        jb6.setBounds(300, 340, 100, 30);
        jb6.addActionListener((ActionEvent e) -> {
            MYFrame.setVisible(false);
            JOptionPane.showMessageDialog(null, "感谢使用！\n欢迎下次再见！");
            Login login = new Login();
        });
        MYFrame.add(jb6);

        MYFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        MYFrame.setVisible(true);
    }
}

package com.ZHJ.Home;

/**
 * @auther ZHJ
 * @date 2020/12/4
 */

import com.ZHJ.Things.Menu;
import com.ZHJ.dbMenu.MenuRepository;
import com.ZHJ.dbMenu.dbMenuManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class ShowMyReceive extends JFrame {
    public ShowMyReceive(String name) {
        JFrame MYFrame = new JFrame();
        MYFrame.setBounds(200, 200, 430, 340);
        MYFrame.setTitle("我的接单");
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

        String[] rowName = {"编号", "内容", "金额", "发单人"};
        int DataNum = 0;
        for (int i = 0; i < list.size(); i++) {//仅展示自己接的单子
            Menu menu = list.get(i);
            if (name.equals(menu.GetNameWant()) && menu.GetFlag()) {
                DataNum++;
            }
        }
        Object[][] data = new Object[DataNum][4];
        for (int i = 0, j = 0; i < list.size(); i++) {
            Menu menu = list.get(i);
            if (name.equals(menu.GetNameWant())) {
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
            ShowMenu showMenu = new ShowMenu(name, ID - 1, "ShowMyReceive");
        });
        MYFrame.add(jb1);

        JButton jbFresh = new JButton("刷新");
        jbFresh.setBounds(270, 220, 100, 30);
        jbFresh.addActionListener((ActionEvent e) -> {
            MYFrame.setVisible(false);
            ShowMyReceive showMyReceive = new ShowMyReceive(name);
        });
        MYFrame.add(jbFresh);

        JButton jb5 = new JButton("返回主界面");
        jb5.setBounds(270, 260, 100, 30);
        jb5.addActionListener((ActionEvent e) -> {
            MYFrame.setVisible(false);
            Home home = new Home(name);
        });
        MYFrame.add(jb5);

        MYFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        MYFrame.setVisible(true);
    }

}

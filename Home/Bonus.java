package com.ZHJ.Home;
/**
 * @author ZHJ
 * @date 2020/12/3
 */
import com.ZHJ.Login.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Bonus extends JFrame {
    public Bonus(String name){
        JFrame MYFrame = new JFrame();
        MYFrame.setBounds(200, 200, 460, 450);
        MYFrame.setTitle("账号充值");
        MYFrame.setResizable(false);
        MYFrame.setLocationRelativeTo(null);
        MYFrame.setLayout(null);//采用坐标布局

        JLabel L1 = new JLabel("<html>感谢您的使用，尚不支持充值功能<br>" +
                "欢迎您通过打赏功能支持作者<br>" +
                "作者姓名：张昊杰<br>" +
                "学校：BIT</html>");
        L1.setBounds(20, 5, 300, 60);
        MYFrame.add(L1);

        JLabel P1=new JLabel(new ImageIcon("./files/ZFB.JPG"));
        P1.setBounds(20,70,200,300);
        MYFrame.add(P1);

        JLabel P2=new JLabel(new ImageIcon("./files/WX.JPG"));
        P2.setBounds(240,70,200,271);
        MYFrame.add(P2);

        JLabel L2 = new JLabel("<html>账号："+name+"<br>请联系管理员为您充值</html>");
        L2.setBounds(20, 360, 300, 60);
        MYFrame.add(L2);

        JButton jb1 = new JButton("返回主界面");
        jb1.setBounds(320, 380, 100, 30);
        jb1.addActionListener((ActionEvent e) -> {
            MYFrame.setVisible(false);
            Home home =new Home(name);
        });
        MYFrame.add(jb1);

        MYFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        MYFrame.setVisible(true);
    }
}

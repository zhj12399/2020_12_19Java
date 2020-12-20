package com.ZHJ.dbMenu;
/**
 * @author ZHJ
 * @date 2020/11/29
 */

import com.ZHJ.Things.Menu;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dbMenuManager implements MenuRepository, Closeable {
    private Connection connection = null;
    private final String connectionStr = "jdbc:sqlite:./files/Menu.db";

    public dbMenuManager() throws SQLException {
        connection = DriverManager.getConnection(connectionStr);
    }

    @Override
    public boolean AddMenu(Menu menu) {
        try {//增加菜单
            Statement statement = connection.createStatement();
            String sql = "insert into Menu(Text,People,Money,Flag)" +
                    "values('" + menu.GetText() + "','" +
                    menu.GetName() + "','" + menu.GetMoney() +
                    "','" + 0 + "')";
            int rowAffected = statement.executeUpdate(sql);
            if (rowAffected > 0) {
                return true;
            }
            else {

                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean ExistMenu(Menu menu) {
        try {//判断菜单是否有重合的
            String sql = "select * from Menu";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (menu.GetName().equals(rs.getString("People")) &&
                        menu.GetText().equals(rs.getString("Text"))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return false;
    }

    @Override
    public boolean DeleteMenu(Menu menu) {
        try {//删除菜单
            Statement statement = connection.createStatement();
            String sql = "delete from Menu where Text='" +
                    menu.GetText() + "'and People='" + menu.GetName() + "'";
            int rowAffected = statement.executeUpdate(sql);
            if (rowAffected > 0) {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return false;
    }

    @Override
    public boolean ModifyMenu(Menu OldMenu, Menu NewMenu) {
        try {//更新菜单内容
            Statement statement = connection.createStatement();
            String sql = "update Menu set Text='" + NewMenu.GetText() +
                    "', Money=" + NewMenu.GetMoney() +
                    " where Text='" + OldMenu.GetText() + "' and People='" + OldMenu.GetName() + "'";
            int rowAffected = statement.executeUpdate(sql);
            if (rowAffected > 0) {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return false;
    }

    @Override
    public List<Menu> check() {//查看
        String sql = "select * from Menu";
        List<Menu> list = new ArrayList<Menu>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {//
                Menu menu = new Menu();
                menu.SetText(rs.getString("Text"));
                menu.SetName(rs.getString("People"));
                menu.SetMoney(rs.getInt("Money"));
                menu.SetFlag(rs.getBoolean("Flag"));
                menu.SetNameWant(rs.getString("PeopleWant"));
                list.add(menu);//把类对象放到集合里
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return list;
    }

    @Override
    public boolean AcceptMenu(Menu menu, String name) {
        try {//被别人接单了
            Statement statement = connection.createStatement();
            String sql = "update Menu set PeopleWant='" + name + "', Flag=1" +
                    " where Text='" + menu.GetText() + "' and People='" + menu.GetName() + "'";
            int rowAffected = statement.executeUpdate(sql);
            if (rowAffected > 0) {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return false;
    }

    @Override
    public void close() throws IOException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}

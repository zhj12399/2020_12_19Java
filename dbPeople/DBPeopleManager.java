package com.ZHJ.dbPeople;
/**
 * @author ZHJ
 * @date 2020/11/27
 */

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;

import com.ZHJ.Things.*;

public class DBPeopleManager implements PeopleRepository, Closeable {
    private Connection connection = null;
    private final String connectionStr = "jdbc:sqlite:./files/People.db";

    public DBPeopleManager() throws SQLException {
        connection = DriverManager.getConnection(connectionStr);
    }

    @Override
    public boolean NameExist(String name) {
        try {//判断是否有重名的
            String sql = "select * from People";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (name.equals(rs.getString("Name"))) {
                    return true;
                }
            }
            return false;
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
    public boolean PassWordCorrect(String name, String password) {
        try {//判定账户的密码是否正确
            String sql = "select * from People";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (name.equals(rs.getString("Name"))) {
                    return password.equals(rs.getString("Password"));
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
    public boolean AddPeople(People people) {
        try {//注册新用户
            Statement statement = connection.createStatement();
            String sql = "insert into People(name,password,property)" +
                    "values('" + people.GetName() + "','" +
                    people.GetPassWord() + "','" + people.GetProperty() + "')";
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
    public People FindPeople(String name) {//根据name查找整个people的数据
        People people = new People();
        people.SetName(name);
        try {
            String sql = "select * from People";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (name.equals(rs.getString("Name"))) {
                    people.SetProperty(rs.getInt("Property"));
                    return people;
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
        return people;
    }

    @Override
    public boolean DeletePeople(String name) {
        try {//注销账户
            String sql = "delete from People " +
                    " where Name=" + "'" + name + "'";
            Statement statement = connection.createStatement();
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
    public boolean ModifyMoney(People people, int num, String op) {//op为判断增加还是减少金额
        int money = people.GetProperty();
        if (op.equals("+")) {
            money += num;
        }
        else if (op.equals("-")) {
            money -= num;
        }
        try {
            Statement statement = connection.createStatement();
            String sql = "update People set Property='" + money +
                    "' where Name='" + people.GetName() + "'";
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

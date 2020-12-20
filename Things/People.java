package com.ZHJ.Things;
/**
 * @author ZHJ
 * @date 2020/11/27
 */
public class People {
    private String Name = "";
    private String Password = "";
    private int Property = 0;

    public People() {
    }

    public People(String name, String password) {
        Name = name;
        Password = password;
        Property = 0;
    }

    @Override
    public String toString() {
        return "People [Name=" + Name + ", Password=" + Password + ", Property=" + Property + "]";
    }

    public String GetName() {
        return Name;
    }

    public String GetPassWord() {
        return Password;
    }

    public int GetProperty() {
        return Property;
    }

    public void SetName(String name) {
        Name = name;
    }

    public void SetPassword(String password) {
        Password = password;
    }

    public void SetProperty(int property) {
        Property = property;
    }
}

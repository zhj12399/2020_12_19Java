package com.ZHJ.Things;
/**
 * @author ZHJ
 * @date 2020/11/28
 */
public class Menu {
    private String Text = "";
    private String Name = "";
    private int Money = 0;
    private boolean Flag = false;
    private String NameWant = "";

    public Menu() {
    }

    public Menu(String text, String name) {
        Text = text;
        Name = name;
    }

    public Menu(String text, String name, int money) {
        Text = text;
        Name = name;
        Money = money;
    }

    public void SetText(String text) {
        Text = text;
    }

    public void SetName(String name) {
        Name = name;
    }

    public void SetMoney(int money) {
        Money = money;
    }

    public void SetFlag(boolean flag) {
        Flag = flag;
    }

    public void SetNameWant(String nameWant) {
        NameWant = nameWant;
    }

    public String GetText() {
        return Text;
    }

    public String GetName() {
        return Name;
    }

    public int GetMoney() {
        return Money;
    }

    public boolean GetFlag() {
        return Flag;
    }

    public String GetNameWant() {
        return NameWant;
    }
}

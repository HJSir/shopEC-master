package com.example.ec.main.personal.team;

/**
 * Created by jian
 */

public class TreeItem {
    private int plies;
    private int icon = 1;
    private String name;
    private String level;
    private String money;

    public TreeItem(String name, String level, String money, int plies) {
        this.plies = plies;
        this.name = name;
        this.level = level;
        this.money = money;
    }

    public int getPlies() {
        return plies;
    }

    public void setPlies(int plies) {
        this.plies = plies;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}

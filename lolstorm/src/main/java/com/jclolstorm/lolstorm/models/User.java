package com.jclolstorm.lolstorm.models;

public class User {

    private String name;
    private String region;
    private int iconID;

    public User(String name, String region, int iconID) {
        this.name = name;
        this.region = region;
        this.iconID = iconID;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public int getIconID() {
        return iconID;
    }
}

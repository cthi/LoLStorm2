package com.jclolstorm.lolstorm.models;

import org.parceler.Parcel;

@Parcel
public class User {

    String name;
    String region;
    int iconID;
    long level;
    long userID;

    public User() {
    }

    public User(String name, String region, int iconID) {
        this.name = name;
        this.region = region;
        this.iconID = iconID;
    }

    public User(String name, String region, int iconID, long level, long userID) {
        this.name = name;
        this.region = region;
        this.iconID = iconID;
        this.userID = userID;
        this.level = level;
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

    public long getUserID() {
        return userID;
    }

    public long getLevel() {
        return level;
    }
}

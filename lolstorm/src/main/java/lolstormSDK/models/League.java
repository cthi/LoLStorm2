package lolstormSDK.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class League {

    ArrayList<LeagueEntry> entries;
    String name;
    String queue;
    String tier;

    public ArrayList<LeagueEntry> getEntries() {
        return entries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQueue() {
        return queue;
    }

    public String getTier() {
        return tier;
    }
}

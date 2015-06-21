package lolstormSDK.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Game {

    int championId;
    long createDate;
    ArrayList<Player> fellowPlayers;
    int spell1;
    int spell2;
    RawStats stats;
    String subType;
    int teamId;

    public int getChampionId() {
        return championId;
    }

    public long getCreateDate() {
        return createDate;
    }

    public ArrayList<Player> getFellowPlayers() {
        return fellowPlayers;
    }

    public int getSpell1() {
        return spell1;
    }

    public int getSpell2() {
        return spell2;
    }

    public RawStats getStats() {
        return stats;
    }

    public String getSubType() {
        return subType;
    }

    public int getTeamId() {
        return teamId;
    }

}

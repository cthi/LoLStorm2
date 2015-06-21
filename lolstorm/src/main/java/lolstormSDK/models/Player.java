package lolstormSDK.models;

import org.parceler.Parcel;

@Parcel
public class Player {
    int profileIcon;
    String summonerName;
    int championId;
    long summonerId;
    int teamId;
    boolean isWinner;
    long level;

    public int getProfileIcon() {
        return profileIcon;
    }

    public void setProfileIcon(int profileIcon) {
        this.profileIcon = profileIcon;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public int getChampionId() {
        return championId;
    }

    public long getSummonerId() {
        return summonerId;
    }

    public int getTeamId() {
        return teamId;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setIsWinner(boolean winner) {
        isWinner = winner;
    }
}

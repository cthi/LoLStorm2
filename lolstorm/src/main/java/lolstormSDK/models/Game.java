package lolstormSDK.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Game {

    @Override
    public String toString() {
        return "Game [championId=" + championId + ", createDate=" + createDate
                + ", fellowPlayers="
                + fellowPlayers + ", gameId=" + gameId + ", gameMode="
                + gameMode + ", gameType=" + gameType + ", invalid=" + invalid
                + ", level=" + level + ", mapId=" + mapId + ", spell1="
                + spell1 + ", spell2=" + spell2 + ", stats=" + stats
                + ", subType=" + subType + ", teamId=" + teamId + "]";
    }
     int championId;
     long createDate;
     ArrayList<Player> fellowPlayers;
     long gameId;
     String gameMode;
     String gameType;
     boolean invalid;
     int level;
     int mapId;
     int spell1;
     int spell2;
     RawStats stats;
     String subType;
     int teamId;

    public int getChampionId() {
        return championId;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public ArrayList<Player> getFellowPlayers() {
        return fellowPlayers;
    }

    public void setFellowPlayers(ArrayList<Player> fellowPlayers) {
        this.fellowPlayers = fellowPlayers;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public int getSpell1() {
        return spell1;
    }

    public void setSpell1(int spell1) {
        this.spell1 = spell1;
    }

    public int getSpell2() {
        return spell2;
    }

    public void setSpell2(int spell2) {
        this.spell2 = spell2;
    }

    public RawStats getStats() {
        return stats;
    }

    public void setStats(RawStats stats) {
        this.stats = stats;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

}

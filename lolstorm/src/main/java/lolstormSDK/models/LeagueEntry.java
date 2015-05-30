package lolstormSDK.models;

import org.parceler.Parcel;

import java.util.Comparator;

@Parcel
public class LeagueEntry implements Comparable<LeagueEntry> {

     String division;
     boolean isFreshBlood;
     boolean isHotStreak;
     boolean isInactive;
     boolean isVeteran;
     int leaguePoints;
     MiniSeries miniSeries;
     String playerOrTeamId;
     String playerOrTeamName;
     int wins;

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public boolean isIsFreshBlood() {
        return isFreshBlood;
    }

    public void setIsFreshBlood(boolean isFreshBlood) {
        this.isFreshBlood = isFreshBlood;
    }

    public boolean isIsInactive() {
        return isInactive;
    }

    public void setIsInactive(boolean isInactive) {
        this.isInactive = isInactive;
    }

    public boolean isIsVeteran() {
        return isVeteran;
    }

    public void setIsVeteran(boolean isVeteran) {
        this.isVeteran = isVeteran;
    }

    public boolean isFreshBlood() {
        return isFreshBlood;
    }

    public void setFreshBlood(boolean isFreshBlood) {
        this.isFreshBlood = isFreshBlood;
    }

    public boolean isHotStreak() {
        return isHotStreak;
    }

    public void setHotStreak(boolean isHotStreak) {
        this.isHotStreak = isHotStreak;
    }

    public boolean isInactive() {
        return isInactive;
    }

    public void setInactive(boolean isInactive) {
        this.isInactive = isInactive;
    }

    public boolean isVeteran() {
        return isVeteran;
    }

    public void setVeteran(boolean isVeteran) {
        this.isVeteran = isVeteran;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public MiniSeries getMiniSeries() {
        return miniSeries;
    }

    public void setMiniSeries(MiniSeries miniSeries) {
        this.miniSeries = miniSeries;
    }

    public String getPlayerOrTeamId() {
        return playerOrTeamId;
    }

    public void setPlayerOrTeamId(String playerOrTeamId) {
        this.playerOrTeamId = playerOrTeamId;
    }

    public String getPlayerOrTeamName() {
        return playerOrTeamName;
    }

    public void setPlayerOrTeamName(String playerOrTeamName) {
        this.playerOrTeamName = playerOrTeamName;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    @Override
    public String toString() {
        return "LeagueItem{" + "division=" + division + ", isFreshBlood=" + isFreshBlood + ", isHotStreak=" + isHotStreak + ", isInactive=" + isInactive + ", isVeteran=" + isVeteran + ", leaguePoints=" + leaguePoints + ", miniSeries=" + miniSeries + ", playerOrTeamId=" + playerOrTeamId + ", playerOrTeamName=" + playerOrTeamName + ", wins=" + wins + '}';
    }

    @Override
    public int compareTo(LeagueEntry leagueEntry) {
        MiniSeries ams = getMiniSeries();
        MiniSeries bms = leagueEntry.getMiniSeries();
        if (bms != null && ams == null) {
            return 1;
        }

        if (ams != null && bms == null) {
            return -1;
        }

        if (ams != null) {
            if (ams.getProgress().replace("N", "").length() == bms.getProgress().replace("N", "").length()) {
                if ((ams.getProgress().length() - ams.getProgress().replace("L", "").length()) >
                        (bms.getProgress().length() - bms.getProgress().replace("L", "").length())) {
                    return -1;
                } else {
                    return 1;
                }
            }

            if (ams.getProgress().replace("N", "").length() > bms.getProgress().replace("N", "").length()) {
                return -1;
            } else {
                return 1;
            }
        }

        if (leagueEntry.getLeaguePoints() - getLeaguePoints() == 0) {
            return leagueEntry.getWins() - getWins();
        }

        return leagueEntry.getLeaguePoints() - getLeaguePoints();
    }
}

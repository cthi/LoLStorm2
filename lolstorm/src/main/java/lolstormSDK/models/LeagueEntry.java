package lolstormSDK.models;

import org.parceler.Parcel;

@Parcel
public class LeagueEntry implements Comparable<LeagueEntry> {

    String division;
    int leaguePoints;
    MiniSeries miniSeries;
    String playerOrTeamId;
    String playerOrTeamName;
    int wins;

    public String getDivision() {
        return division;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public MiniSeries getMiniSeries() {
        return miniSeries;
    }

    public String getPlayerOrTeamId() {
        return playerOrTeamId;
    }

    public String getPlayerOrTeamName() {
        return playerOrTeamName;
    }

    public int getWins() {
        return wins;
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
            if (ams.getProgress().replace("N", "").length() == bms.getProgress().replace("N", "")
                    .length()) {
                if ((ams.getProgress().length() - ams.getProgress().replace("L", "").length()) >
                        (bms.getProgress().length() - bms.getProgress().replace("L", "").length()
                        )) {
                    return -1;
                } else {
                    return 1;
                }
            }

            if (ams.getProgress().replace("N", "").length() > bms.getProgress().replace("N", "")
                    .length()) {
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

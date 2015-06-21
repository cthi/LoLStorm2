package lolstormSDK.models;

import org.parceler.Parcel;

@Parcel
public class AggregatedStats {

    int mostChampionKillsPerSession;
    int totalAssists;
    int totalChampionKills;
    int totalDamageDealt;
    int totalDamageTaken;
    int totalDeathsPerSession;
    int totalDoubleKills;
    int totalGoldEarned;
    int totalMagicDamageDealt;
    int totalMinionKills;
    int totalNeutralMinionsKilled;
    int totalPentaKills;
    int totalPhysicalDamageDealt;
    int totalQuadraKills;
    int totalSessionsLost;
    int totalSessionsPlayed;
    int totalSessionsWon;
    int totalTripleKills;
    int totalTurretsKilled;
    int totalUnrealKills;

    public int getTotalDeathsPerSession() {
        return totalDeathsPerSession;
    }

    public int getMostChampionKillsPerSession() {
        return mostChampionKillsPerSession;
    }

    public int getTotalAssists() {
        return totalAssists;
    }

    public int getTotalChampionKills() {
        return totalChampionKills;
    }

    public int getTotalDamageDealt() {
        return totalDamageDealt;
    }

    public int getTotalDamageTaken() {
        return totalDamageTaken;
    }

    public int getTotalDoubleKills() {
        return totalDoubleKills;
    }

    public int getTotalGoldEarned() {
        return totalGoldEarned;
    }

    public int getTotalMagicDamageDealt() {
        return totalMagicDamageDealt;
    }

    public int getTotalMinionKills() {
        return totalMinionKills;
    }

    public int getTotalNeutralMinionsKilled() {
        return totalNeutralMinionsKilled;
    }

    public int getTotalPentaKills() {
        return totalPentaKills;
    }

    public int getTotalPhysicalDamageDealt() {
        return totalPhysicalDamageDealt;
    }

    public int getTotalQuadraKills() {
        return totalQuadraKills;
    }

    public int getTotalSessionsLost() {
        return totalSessionsLost;
    }

    public int getTotalSessionsPlayed() {
        return totalSessionsPlayed;
    }

    public int getTotalSessionsWon() {
        return totalSessionsWon;
    }

    public int getTotalTripleKills() {
        return totalTripleKills;
    }

    public int getTotalTurretsKilled() {
        return totalTurretsKilled;
    }

    public int getTotalUnrealKills() {
        return totalUnrealKills;
    }
}

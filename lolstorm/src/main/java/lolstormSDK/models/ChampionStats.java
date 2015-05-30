package lolstormSDK.models;

import org.parceler.Parcel;

import java.util.Comparator;

@Parcel
public class ChampionStats implements Comparable<ChampionStats> {

    int id;
    AggregatedStats stats;

    @Override
    public String toString() {
        return "ChampionStats [id=" + id + ", stats="
                + stats + "]";
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(ChampionStats championStats) {
        return championStats.getStats().getTotalSessionsPlayed() - stats.getTotalSessionsPlayed();
    }

    public void setId(int id) {
        this.id = id;
    }

    public AggregatedStats getStats() {
        return stats;
    }

    public void setStats(AggregatedStats stats) {
        this.stats = stats;
    }

}

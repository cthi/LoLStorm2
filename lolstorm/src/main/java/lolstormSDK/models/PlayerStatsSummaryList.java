package lolstormSDK.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class PlayerStatsSummaryList {

    ArrayList<PlayerStatsSummary> playerStatSummaries;
    long summonerId;

    public ArrayList<PlayerStatsSummary> getPlayerStatSummaries() {
        return playerStatSummaries;
    }

    public void setOkayerStatSummaries(ArrayList<PlayerStatsSummary> playerStatSummaries) {
        this.playerStatSummaries = playerStatSummaries;
    }

    public long getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(long summonerId) {
        this.summonerId = summonerId;
    }

    @Override
    public String toString() {
        return "PlayerStatsSummaryList [playerStatSummaries=" + playerStatSummaries + ", " +
                "summonerId=" + summonerId + "]";
    }

}

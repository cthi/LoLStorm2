package lolstormSDK.models;

import org.parceler.Parcel;

@Parcel
public class PlayerStatsSummary {
	
	 AggregatedStats aggregatedStats;
	 int losses;
	 long modifyDate;
	 long modifyDateStr;
	 String playerStatSummaryType;
	 int wins;
	
	public AggregatedStats getAggregatedStats() {
		return aggregatedStats;
	}
	public void setAggregatedStats(AggregatedStats aggregatedStats) {
		this.aggregatedStats = aggregatedStats;
	}
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
	public long getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(long modifyDate) {
		this.modifyDate = modifyDate;
	}
	public long getModifyDateStr() {
		return modifyDateStr;
	}
	public void setModifyDateStr(long modifyDateStr) {
		this.modifyDateStr = modifyDateStr;
	}
	public String getPlayerStatSummaryType() {
		return playerStatSummaryType;
	}
	public void setPlayerStatSummaryType(String playerStatSummaryType) {
		this.playerStatSummaryType = playerStatSummaryType;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	@Override
	public String toString() {
		return "PlayerStatsSummary [aggregatedStats=" + aggregatedStats
				+ ", losses=" + losses + ", modifyDate=" + modifyDate
				+ ", modifyDateStr=" + modifyDateStr
				+ ", playerStatSummaryType=" + playerStatSummaryType
				+ ", wins=" + wins + "]";
	}

}

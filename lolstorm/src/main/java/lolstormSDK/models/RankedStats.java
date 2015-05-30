package lolstormSDK.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class RankedStats {

     ArrayList<ChampionStats> champions;
     long modifyDate;
     long summonerId;

    public ArrayList<ChampionStats> getChampions() {
        return champions;
    }

    public void setChampions(ArrayList<ChampionStats> champions) {
        this.champions = champions;
    }

    public long getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(long modifyDate) {
        this.modifyDate = modifyDate;
    }

    public long getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(long summonerId) {
        this.summonerId = summonerId;
    }

    @Override
    public String toString() {
        return "RankedStats [champions=" + champions + ", modifyDate="
                + modifyDate + ", summonerId=" + summonerId + "]";
    }

}

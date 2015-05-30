package lolstormSDK.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class RecentGames {

     ArrayList<Game> games;
     long summonerId;

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public long getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(long summonerId) {
        this.summonerId = summonerId;
    }

    @Override
    public String toString() {
        return "RecentGames [games=" + games + ", summonerId=" + summonerId
                + "]";
    }

}

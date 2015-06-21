package lolstormSDK.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class RankedStats {

    ArrayList<ChampionStats> champions;

    public ArrayList<ChampionStats> getChampions() {
        return champions;
    }
}

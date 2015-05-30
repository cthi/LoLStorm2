package lolstormSDK.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class SummonerNameList {

     ArrayList<SummonerName> summoners;

    public ArrayList<SummonerName> getSummoners() {
        return summoners;
    }

    public void setSummoners(ArrayList<SummonerName> summoners) {
        this.summoners = summoners;
    }

    @Override
    public String toString() {
        return "SummonerNameList [summoners=" + summoners + "]";
    }

}

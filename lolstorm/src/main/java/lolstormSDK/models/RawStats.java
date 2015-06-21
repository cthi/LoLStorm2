package lolstormSDK.models;

import org.parceler.Parcel;

@Parcel
public class RawStats {

    int assists;
    int championsKilled;
    int item0;
    int item1;
    int item2;
    int item3;
    int item4;
    int item5;
    int item6;
    int level;

    int numDeaths;

    boolean win;

    public int getAssists() {
        return assists;
    }

    public int getChampionsKilled() {
        return championsKilled;
    }

    public int getItem0() {
        return item0;
    }

    public int getItem1() {
        return item1;
    }

    public int getItem2() {
        return item2;
    }

    public int getItem3() {
        return item3;
    }

    public int getItem4() {
        return item4;
    }

    public int getItem5() {
        return item5;
    }

    public int getItem6() {
        return item6;
    }

    public int getLevel() {
        return level;
    }

    public int getNumDeaths() {
        return numDeaths;
    }

    public boolean getWin() {
        return win;
    }
}

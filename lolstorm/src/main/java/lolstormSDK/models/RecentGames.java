package lolstormSDK.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class RecentGames {

    ArrayList<Game> games;

    public ArrayList<Game> getGames() {
        return games;
    }

}

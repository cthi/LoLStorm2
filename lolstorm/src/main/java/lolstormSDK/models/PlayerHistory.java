package lolstormSDK.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class PlayerHistory {
    ArrayList<MatchSummary> matches;

    public ArrayList<MatchSummary> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<MatchSummary> matches) {
        this.matches = matches;
    }
}

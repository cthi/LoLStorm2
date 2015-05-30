package lolstormSDK.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class TeamStatSummary {

     TeamId teamId;
     ArrayList<TeamStatDetail> teamStatDetails;

    public TeamId getTeamId() {
        return teamId;
    }

    public void setTeamId(TeamId teamId) {
        this.teamId = teamId;
    }

    public ArrayList<TeamStatDetail> getTeamStatDetails() {
        return teamStatDetails;
    }

    public void setTeamStatDetails(ArrayList<TeamStatDetail> teamStatDetails) {
        this.teamStatDetails = teamStatDetails;
    }

    @Override
    public String toString() {
        return "TeamStatSummary [teamId=" + teamId + ", teamStatDetails="
                + teamStatDetails + "]";
    }

}

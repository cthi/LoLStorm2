package lolstormSDK.models;

import org.parceler.Parcel;

@Parcel
public class Summoner {

    long id;
    String name;
    int profileIconId;
    long revisionDate;
    long summonerLevel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public long getSummonerLevel() {
        return summonerLevel;
    }

    @Override
    public String toString() {
        return "Summoner [id=" + id + ", name=" + name + ", profileIconId=" + profileIconId + ", " +
                "revisionDate=" + revisionDate + ", summonerLevel=" + summonerLevel + "]";
    }

}

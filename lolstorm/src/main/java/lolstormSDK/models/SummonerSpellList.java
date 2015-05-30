package lolstormSDK.models;

import org.parceler.Parcel;

import java.util.Map;

@Parcel
public class SummonerSpellList {

     Map<String, SummonerSpell> data;
     String type;
     String version;

    public Map<String, SummonerSpell> getData() {
        return data;
    }

    public void setData(Map<String, SummonerSpell> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

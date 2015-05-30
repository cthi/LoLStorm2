package lolstormSDK.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class SpellVars {

     ArrayList<Double> coeff;
     String dyn;
     String key;
     String link;
     String ranksWith;

    public ArrayList<Double> getCoeff() {
        return coeff;
    }

    public void setCoeff(ArrayList<Double> coeff) {
        this.coeff = coeff;
    }

    public String getDyn() {
        return dyn;
    }

    public void setDyn(String dyn) {
        this.dyn = dyn;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRanksWith() {
        return ranksWith;
    }

    public void setRanksWith(String ranksWith) {
        this.ranksWith = ranksWith;
    }

}

package lolstormSDK.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class RunePages {

     ArrayList<RunePage> pages;
     long summonerId;

    public ArrayList<RunePage> getPages() {
        return pages;
    }

    public void setPages(ArrayList<RunePage> pages) {
        this.pages = pages;
    }

    public long getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(long summonerId) {
        this.summonerId = summonerId;
    }

    @Override
    public String toString() {
        return "RunePages [pages=" + pages + ", summonerId=" + summonerId + "]";
    }

}

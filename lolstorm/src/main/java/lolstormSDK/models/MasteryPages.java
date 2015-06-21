package lolstormSDK.models;

import org.parceler.Parcel;

import java.util.HashSet;

@Parcel
public class MasteryPages {

    HashSet<MasteryPage> pages;
    long summonerId;

    public HashSet<MasteryPage> getPages() {
        return pages;
    }

    public void setPages(HashSet<MasteryPage> pages) {
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
        return "MasteryPages [pages=" + pages + ", summonerId=" + summonerId + "]";
    }

}

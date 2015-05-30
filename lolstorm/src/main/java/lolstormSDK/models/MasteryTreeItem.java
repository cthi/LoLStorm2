package lolstormSDK.models;

import org.parceler.Parcel;

@Parcel
public class MasteryTreeItem {

     int masteryId;
     String prereq;

    public int getMasteryId() {
        return masteryId;
    }

    public void setMasteryId(int masteryId) {
        this.masteryId = masteryId;
    }

    public String getPrereq() {
        return prereq;
    }

    public void setPrereq(String prereq) {
        this.prereq = prereq;
    }
}

package lolstormSDK.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class MasteryTree {

     ArrayList<MasteryTreeList> Defense;
     ArrayList<MasteryTreeList> Offense;
     ArrayList<MasteryTreeList> Utility;

    public ArrayList<MasteryTreeList> getDefense() {
        return Defense;
    }

    public void setDefense(ArrayList<MasteryTreeList> Defense) {
        this.Defense = Defense;
    }

    public ArrayList<MasteryTreeList> getOffense() {
        return Offense;
    }

    public void setOffense(ArrayList<MasteryTreeList> Offense) {
        this.Offense = Offense;
    }

    public ArrayList<MasteryTreeList> getUtility() {
        return Utility;
    }

    public void setUtility(ArrayList<MasteryTreeList> Utility) {
        this.Utility = Utility;
    }
}

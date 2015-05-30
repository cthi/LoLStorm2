package lolstormSDK.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class MasteryTreeList {

     ArrayList<MasteryTreeItem> masteryTreeItems;

    public ArrayList<MasteryTreeItem> getMasteryTreeItems() {
        return masteryTreeItems;
    }

    public void setMasteryTreeItems(ArrayList<MasteryTreeItem> masteryTreeItems) {
        this.masteryTreeItems = masteryTreeItems;
    }
}

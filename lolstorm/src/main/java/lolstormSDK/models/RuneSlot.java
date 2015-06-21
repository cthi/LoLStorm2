package lolstormSDK.models;

import org.parceler.Parcel;

@Parcel
public class RuneSlot {

    int runeId;
    int runeSlotId;

    public int getRuneId() {
        return runeId;
    }

    public void setRuneId(int runeId) {
        this.runeId = runeId;
    }

    public int getRuneSlotId() {
        return runeSlotId;
    }

    public void setRuneSlotId(int runeSlotId) {
        this.runeSlotId = runeSlotId;
    }

    @Override
    public String toString() {
        return "RuneSlot{" + "runeId=" + runeId + ", runeSlotId=" + runeSlotId + '}';
    }

}

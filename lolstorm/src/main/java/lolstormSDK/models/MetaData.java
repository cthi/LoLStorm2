package lolstormSDK.models;

import org.parceler.Parcel;

@Parcel
public class MetaData {

     String tier;
     String type;
     boolean isRune;

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getIsRune() {
        return isRune;
    }

    public void setIsRune(boolean isRune) {
        this.isRune = isRune;
    }
}

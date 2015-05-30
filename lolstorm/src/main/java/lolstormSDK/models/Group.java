package lolstormSDK.models;

import org.parceler.Parcel;

@Parcel
public class Group {

     String MaxGroupOwnable;
     String key;

    public String getMaxGroupOwnable() {
        return MaxGroupOwnable;
    }

    public void setMaxGroupOwnable(String MaxGroupOwnable) {
        this.MaxGroupOwnable = MaxGroupOwnable;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}

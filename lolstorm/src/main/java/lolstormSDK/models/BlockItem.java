package lolstormSDK.models;

import org.parceler.Parcel;

@Parcel
public class BlockItem {

     int count;
     int id;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

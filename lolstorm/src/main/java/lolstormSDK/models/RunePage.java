package lolstormSDK.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class RunePage {

     boolean current;
     long id;
     String name;
     ArrayList<RuneSlot> slots;

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<RuneSlot> getSlots() {
        return slots;
    }

    public void setSlots(ArrayList<RuneSlot> slots) {
        this.slots = slots;
    }

    @Override
    public String toString() {
        return "RunePage [current=" + current + ", id=" + id + ", name=" + name
                + ", slots=" + slots + "]";
    }

}

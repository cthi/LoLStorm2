package lolstormSDK.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class ChampionSpell {

    ArrayList<Integer> cost;
    ArrayList<Double> cooldown;
    String costType;
    String key;
    String name;
    String sanitizedDescription;

    public ArrayList<Integer> getCost() {
        return cost;
    }

    public ArrayList<Double> getCooldown() {
        return cooldown;
    }

    public String getCostType() {
        return costType;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getSanitizedDescription() {
        return sanitizedDescription;
    }
}

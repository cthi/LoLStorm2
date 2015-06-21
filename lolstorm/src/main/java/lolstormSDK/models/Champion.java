package lolstormSDK.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Champion {

    ArrayList<ChampionSpell> spells;
    String name;
    String title;
    long id;

    public Champion() {
    }

    public Champion(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public ArrayList<ChampionSpell> getSpells() {
        return spells;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public long getId() {
        return id;
    }

}

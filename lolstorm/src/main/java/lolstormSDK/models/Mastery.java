package lolstormSDK.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Mastery {

    ArrayList<String> description;
    int id;
    Image image;
    String name;
    String prereq;
    int ranks;
    ArrayList<String> sanitizedDescription;

    public ArrayList<String> getDescription() {
        return description;
    }

    public void setDescription(ArrayList<String> description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrereq() {
        return prereq;
    }

    public void setPrereq(String prereq) {
        this.prereq = prereq;
    }

    public int getRanks() {
        return ranks;
    }

    public void setRanks(int ranks) {
        this.ranks = ranks;
    }

    public ArrayList<String> getSanitizedDescription() {
        return sanitizedDescription;
    }

    public void setSanitizedDescription(ArrayList<String> sanitizedDescription) {
        this.sanitizedDescription = sanitizedDescription;
    }
}

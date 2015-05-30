package lolstormSDK.models;

import org.parceler.Parcel;

import java.util.Map;

@Parcel
public class RuneList {

     BasicData basic;
     Map<String, Rune> data;
     String type;
     String version;

    public BasicData getBasic() {
        return basic;
    }

    public void setBasic(BasicData basic) {
        this.basic = basic;
    }

    public Map<String, Rune> getData() {
        return data;
    }

    public void setData(Map<String, Rune> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

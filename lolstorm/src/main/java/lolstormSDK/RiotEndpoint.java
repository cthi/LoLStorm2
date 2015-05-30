package lolstormSDK;

import retrofit.Endpoint;

public class RiotEndpoint implements Endpoint {
    private static final String BASE1 = "https://";
    private static final String BASE2 = ".api.pvp.net";

    private String url;
    private String region;

    private static RiotEndpoint endpoint;

    public static RiotEndpoint getInstance() {
        if (null == endpoint) {
            endpoint = new RiotEndpoint();
            endpoint.setRegion("na");
        }

        return endpoint;
    }
    public void setRegion(String region) {
        url = BASE1 + region + BASE2;
        this.region = region;
    }

    public String getRegion() {
        return region;
    }

    @Override
    public String getName() {
        return "default";
    }

    @Override
    public String getUrl() {
        if (url == null) throw new IllegalStateException("Error.");
        return url;
    }
}
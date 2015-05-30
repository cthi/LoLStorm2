package lolstormSDK;

import retrofit.RestAdapter;

public class RiotApiImpl {

    private static RiotApi api;

    protected RiotApiImpl(){
    }

    public static RiotApi getInstance(RiotEndpoint endpoint){
        if (api == null){
            RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
            api = restAdapter.create(RiotApi.class);
        }
        return api;
    }
}

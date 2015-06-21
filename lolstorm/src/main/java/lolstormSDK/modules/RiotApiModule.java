package lolstormSDK.modules;

import com.jclolstorm.lolstorm.BuildConfig;

import java.util.List;
import java.util.Map;

import lolstormSDK.RiotApi;
import lolstormSDK.RiotApiImpl;
import lolstormSDK.RiotEndpoint;
import lolstormSDK.models.League;
import lolstormSDK.models.RankedStats;
import lolstormSDK.models.RecentGames;
import lolstormSDK.models.Summoner;
import rx.Observable;


public class RiotApiModule {

    private static final String API_KEY = BuildConfig.API_KEY;

    public static Observable<Map<String,Summoner>> getSummonerIDFromName(String summonerName) {
        return getSummonersByName(summonerName);
    }

    private static RiotApi getApi() {
        RiotEndpoint endpoint = RiotEndpoint.getInstance();
        return RiotApiImpl.getInstance(endpoint);
    }

    private static String getRegion() {
        return RiotEndpoint.getInstance().getRegion();
    }

    public static Observable<RecentGames> getSummonerRecentGames(long summonerID) {
        return getApi().getSummonerRecentGames(getRegion(), summonerID, API_KEY);
    }

    public static Observable<Map<String, List<League>>> getSummonerLeagues(long summonerID) {
        return getApi().getSummonerLeagues(getRegion(), summonerID, API_KEY);
    }

    public static Observable<RankedStats> getSummonerRankedStats(long summonerID) {
        return getApi().getSummonerRankedStats(getRegion(), summonerID, API_KEY);
    }

    public static Observable<Map<String,Summoner>> getSummonersByName(String summonerName) {
        return getApi().getSummonersByName(getRegion(), summonerName, API_KEY);
    }

    public static Observable<Map<String,Summoner>> getSummonersById(String summonerIDs) {
        return getApi().getSummonersById(getRegion(), summonerIDs, API_KEY);
    }
}
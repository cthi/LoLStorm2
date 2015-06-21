package lolstormSDK;

import java.util.List;
import java.util.Map;

import lolstormSDK.models.League;
import lolstormSDK.models.RankedStats;
import lolstormSDK.models.RecentGames;
import lolstormSDK.models.Summoner;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface RiotApi {

    @GET("/api/lol/{region}/v1.3/game/by-summoner/{summonerId}/recent")
    Observable<RecentGames> getSummonerRecentGames(@Path("region") String region, @Path("summonerId") long id, @Query("api_key") String apikey);

    @GET("/api/lol/{region}/v2.5/league/by-summoner/{summonerIds}")
    Observable<Map<String, List<League>>> getSummonerLeagues(@Path("region") String region, @Path("summonerIds") long id, @Query("api_key") String apikey);

    @GET("/api/lol/{region}/v1.3/stats/by-summoner/{summonerId}/ranked")
    Observable<RankedStats> getSummonerRankedStats(@Path("region") String regions, @Path("summonerId") long id, @Query("api_key") String apikey);

    @GET("/api/lol/{region}/v1.4/summoner/by-name/{summonerNames}")
    Observable<Map<String,Summoner>> getSummonersByName(@Path("region") String regions, @Path("summonerNames") String summonerNames, @Query("api_key") String apikey);

    @GET("/api/lol/{region}/v1.4/summoner/{summonerIds}")
    Observable<Map<String,Summoner>> getSummonersById(@Path("region") String regions, @Path("summonerIds") String ids, @Query("api_key") String apikey);
}
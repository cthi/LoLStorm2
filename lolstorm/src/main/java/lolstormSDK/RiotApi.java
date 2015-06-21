/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Christopher C. Thi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
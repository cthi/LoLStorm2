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
        return RiotEndpoint.getInstance().getRegionAsString();
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
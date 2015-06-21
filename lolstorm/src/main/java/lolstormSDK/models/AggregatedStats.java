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

package lolstormSDK.models;

import org.parceler.Parcel;

@Parcel
public class AggregatedStats {

    int mostChampionKillsPerSession;
    int totalAssists;
    int totalChampionKills;
    int totalDamageDealt;
    int totalDamageTaken;
    int totalDeathsPerSession;
    int totalDoubleKills;
    int totalGoldEarned;
    int totalMagicDamageDealt;
    int totalMinionKills;
    int totalNeutralMinionsKilled;
    int totalPentaKills;
    int totalPhysicalDamageDealt;
    int totalQuadraKills;
    int totalSessionsLost;
    int totalSessionsPlayed;
    int totalSessionsWon;
    int totalTripleKills;
    int totalTurretsKilled;
    int totalUnrealKills;

    public int getTotalDeathsPerSession() {
        return totalDeathsPerSession;
    }

    public int getMostChampionKillsPerSession() {
        return mostChampionKillsPerSession;
    }

    public int getTotalAssists() {
        return totalAssists;
    }

    public int getTotalChampionKills() {
        return totalChampionKills;
    }

    public int getTotalDamageDealt() {
        return totalDamageDealt;
    }

    public int getTotalDamageTaken() {
        return totalDamageTaken;
    }

    public int getTotalDoubleKills() {
        return totalDoubleKills;
    }

    public int getTotalGoldEarned() {
        return totalGoldEarned;
    }

    public int getTotalMagicDamageDealt() {
        return totalMagicDamageDealt;
    }

    public int getTotalMinionKills() {
        return totalMinionKills;
    }

    public int getTotalNeutralMinionsKilled() {
        return totalNeutralMinionsKilled;
    }

    public int getTotalPentaKills() {
        return totalPentaKills;
    }

    public int getTotalPhysicalDamageDealt() {
        return totalPhysicalDamageDealt;
    }

    public int getTotalQuadraKills() {
        return totalQuadraKills;
    }

    public int getTotalSessionsLost() {
        return totalSessionsLost;
    }

    public int getTotalSessionsPlayed() {
        return totalSessionsPlayed;
    }

    public int getTotalSessionsWon() {
        return totalSessionsWon;
    }

    public int getTotalTripleKills() {
        return totalTripleKills;
    }

    public int getTotalTurretsKilled() {
        return totalTurretsKilled;
    }

    public int getTotalUnrealKills() {
        return totalUnrealKills;
    }
}

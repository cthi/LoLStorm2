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
public class LeagueEntry implements Comparable<LeagueEntry> {

    String division;
    int leaguePoints;
    MiniSeries miniSeries;
    String playerOrTeamId;
    String playerOrTeamName;
    int wins;

    public String getDivision() {
        return division;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public MiniSeries getMiniSeries() {
        return miniSeries;
    }

    public String getPlayerOrTeamId() {
        return playerOrTeamId;
    }

    public String getPlayerOrTeamName() {
        return playerOrTeamName;
    }

    public int getWins() {
        return wins;
    }

    @Override
    public int compareTo(LeagueEntry leagueEntry) {
        MiniSeries ams = getMiniSeries();
        MiniSeries bms = leagueEntry.getMiniSeries();
        if (bms != null && ams == null) {
            return 1;
        }

        if (ams != null && bms == null) {
            return -1;
        }

        if (ams != null) {
            if (ams.getProgress().replace("N", "").length() == bms.getProgress().replace("N", "")
                    .length()) {
                if ((ams.getProgress().length() - ams.getProgress().replace("L", "").length()) >
                        (bms.getProgress().length() - bms.getProgress().replace("L", "").length()
                        )) {
                    return -1;
                } else {
                    return 1;
                }
            }

            if (ams.getProgress().replace("N", "").length() > bms.getProgress().replace("N", "")
                    .length()) {
                return -1;
            } else {
                return 1;
            }
        }

        if (leagueEntry.getLeaguePoints() - getLeaguePoints() == 0) {
            return leagueEntry.getWins() - getWins();
        }

        return leagueEntry.getLeaguePoints() - getLeaguePoints();
    }
}

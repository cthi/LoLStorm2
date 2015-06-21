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

package com.jclolstorm.lolstorm.presenters;

import com.jclolstorm.lolstorm.models.User;
import com.jclolstorm.lolstorm.views.SummonerChampionsView;

import java.util.Collections;
import java.util.List;

import lolstormSDK.models.ChampionStats;
import lolstormSDK.models.RankedStats;
import lolstormSDK.modules.RiotApiModule;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class SummonerChampionsPresenter {

    private SummonerChampionsView mView;

    private List<ChampionStats> mStatList;
    private ChampionStats mHeaderStat;

    public SummonerChampionsPresenter() {
    }

    public void setView(SummonerChampionsView view) {
        this.mView = view;
    }

    public void setUser(User user) {
        if (null == mStatList || null == mHeaderStat) {
            getRankedStats(user.getUserID());
        } else {
            updateView();
        }
    }

    private void updateView() {
        mView.populateAdapter(mStatList);
        mView.setHeaderData(mHeaderStat);
        mView.hideLoading();
    }

    private void getRankedStats(long userID) {
        RiotApiModule.getSummonerRankedStats(userID).observeOn(AndroidSchedulers.mainThread())
                .retry(3).subscribe(new Subscriber<RankedStats>() {
            @Override
            public void onCompleted() {
                updateView();
            }

            @Override
            public void onError(Throwable e) {
                mView.showNoDataView();
            }


            @Override
            public void onNext(RankedStats rankedStats) {

                mStatList = rankedStats.getChampions();
                Collections.sort(mStatList, ChampionStats::compareTo);

                mHeaderStat = mStatList.remove(0);
            }
        });
    }
}

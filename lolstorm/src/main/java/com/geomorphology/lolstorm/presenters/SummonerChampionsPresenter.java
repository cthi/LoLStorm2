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

package com.geomorphology.lolstorm.presenters;

import com.geomorphology.lolstorm.R;
import com.geomorphology.lolstorm.domain.interactors.NetworkConnectionInteractor;
import com.geomorphology.lolstorm.models.User;
import com.geomorphology.lolstorm.views.SummonerChampionsView;

import java.util.Collections;
import java.util.List;

import lolstormSDK.RiotErrors;
import lolstormSDK.models.ChampionStats;
import lolstormSDK.models.RankedStats;
import lolstormSDK.modules.RiotApiModule;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class SummonerChampionsPresenter {

    private NetworkConnectionInteractor mInteractor;
    private SummonerChampionsView mView;

    private List<ChampionStats> mStatList;
    private ChampionStats mHeaderStat;

    public SummonerChampionsPresenter(NetworkConnectionInteractor interactor) {
        this.mInteractor = interactor;
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
        if (!mInteractor.hasNetworkConnection()) {
            mView.showErrorView(R.string.error_internet_connection);
            return;
        }
        RiotApiModule.getSummonerRankedStats(userID).observeOn(AndroidSchedulers.mainThread())
                .retry(3).subscribe(new Subscriber<RankedStats>() {
            @Override
            public void onCompleted() {
                updateView();
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof RiotErrors.RiotConnectionException) {
                    mView.showErrorView(R.string.error_internet_connection);
                } else if (e instanceof RiotErrors.RiotDataNotFoundException) {
                    mView.showErrorView(R.string.error_data_not_found);
                } else if (e instanceof RiotErrors.RiotServerFailureException) {
                    mView.showErrorView(R.string.error_server_failure);
                } else if (e instanceof RiotErrors.RiotApiLimitException) {
                    mView.showErrorView(R.string.error_server_failure);
                } else if (e instanceof RiotErrors.RiotGenericFailureException) {
                    mView.showErrorView(R.string.error_app_update);
                } else {
                    mView.showErrorView(R.string.error_app_unknown);
                }
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

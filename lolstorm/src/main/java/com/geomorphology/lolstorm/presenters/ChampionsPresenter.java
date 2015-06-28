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

import com.geomorphology.lolstorm.views.ChampionsView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import lolstormSDK.GameConstants;
import lolstormSDK.models.Champion;

public class ChampionsPresenter {

    private ChampionsView mView;

    public ChampionsPresenter() {
    }

    public void setView(ChampionsView view) {
        this.mView = view;
        getListOfChampions();
    }

    private void getListOfChampions() {
        List<Champion> championList = new ArrayList<>();

        for (Map.Entry<String, String> entry : GameConstants.CHAMPION_NAME_MAP.entrySet()) {
            championList.add(new Champion(entry.getValue(), Long.parseLong(entry.getKey())));
        }

        Collections.sort(championList, (lhs, rhs) -> lhs.getName().compareTo(rhs.getName()));
        mView.populate(championList);
    }
}

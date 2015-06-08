package com.jclolstorm.lolstorm.ui.widgets;


import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.jclolstorm.lolstorm.R;

public class ColoredProgressBar extends ProgressBar {
    public ColoredProgressBar(Context context) {
        super(context);
        init();
    }

    public ColoredProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ColoredProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.green_primary), PorterDuff.Mode.SRC_IN);
    }
}
package com.jclolstorm.lolstorm.ui.widgets.headers;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jclolstorm.lolstorm.R;
import com.jclolstorm.lolstorm.models.User;
import com.jclolstorm.lolstorm.utils.ResourceUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class NavViewHeader extends RelativeLayout {

    @InjectView(R.id.header_drawer_summoner_image)
    ImageView mUserImage;
    @InjectView(R.id.header_drawer_summoner_name)
    TextView mUserName;

    public NavViewHeader(Context context) {
        super(context, null);
        init();
    }

    public NavViewHeader(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init();
    }

    public NavViewHeader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.header_drawer, this);
        ButterKnife.inject(this);
    }

    public void setUser(User user) {
        Glide.with(getContext()).load(ResourceUtils.numberedDrawableFromID(user.getIconID(),
                getContext())).into(mUserImage);
        mUserName.setText(user.getName());
    }
}

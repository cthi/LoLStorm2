package com.jclolstorm.lolstorm.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jclolstorm.lolstorm.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AboutFragment extends Fragment {

    @InjectView(R.id.about_open_source_libs)
    TextView mOpenSourceLibs;

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_fragment, container, false);

        getActivity().setTitle(getString(R.string.about_title));

        ButterKnife.inject(this, view);

        String[] libs = getResources().getStringArray(R.array.open_source_libs);
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < libs.length; i++) {
            builder.append(libs[i] + "\n");
        }

        mOpenSourceLibs.setText(builder.toString());

        return view;
    }
}

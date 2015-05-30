package com.jclolstorm.lolstorm.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public abstract class BaseHeaderRecyclerViewAdapter<T> extends RecyclerView.Adapter {
    protected static final int HEADER = 0;
    protected static final int ITEM = 1;

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER;
        } else {
            return ITEM;
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View view) {
            super(view);
        }
    }

    public abstract void populate(List<T> list);
}

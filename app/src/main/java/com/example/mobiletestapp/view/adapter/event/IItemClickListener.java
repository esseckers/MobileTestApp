package com.example.mobiletestapp.view.adapter.event;

import android.view.View;

public interface IItemClickListener<D> {
    void onItemClick(D data);
    void onItemClick(D data, View... views);
}

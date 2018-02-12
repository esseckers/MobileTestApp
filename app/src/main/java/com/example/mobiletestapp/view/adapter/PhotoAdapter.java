package com.example.mobiletestapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobiletestapp.model.ShotModel;
import com.example.mobiletestapp.view.adapter.holder.PhotoHolder;

public class PhotoAdapter extends AbstractAdapter<ShotModel, PhotoHolder> {

    public PhotoAdapter(int resource) {
        super(resource);
    }

    @Override
    public PhotoHolder onInitViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new PhotoHolder(view);
    }
}

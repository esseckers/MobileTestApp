package com.example.mobiletestapp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.mobiletestapp.view.adapter.event.IItemClickListener;
import com.example.mobiletestapp.view.adapter.holder.AbstractHolder;

import java.util.List;

public abstract class AbstractAdapter<D, T extends AbstractHolder<D>> extends RecyclerView.Adapter<T> {

    protected int resource;
    protected List<D> data;
    protected LayoutInflater inflater;
    private IItemClickListener<D> clickListener;

    public AbstractAdapter(int resource) {
        this.resource = resource;
    }

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        T holder = onInitViewHolder(parent);
        holder.setOnItemClick(clickListener);
        return holder;
    }


    protected T onInitViewHolder(ViewGroup parent) {
        return null;
    }


    @Override
    public void onBindViewHolder(T holder, int position) {
        holder.setData(data.get(position), position);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setData(List<D> data) {
        if (this.data != null) {
            this.data.clear();
        }
        this.data = data;
        notifyDataSetChanged();
    }

    public void clearData() {
        if (data != null) data.clear();
        notifyDataSetChanged();
    }

    public void setClickListener(IItemClickListener<D> clickListener) {
        this.clickListener = clickListener;
    }
}

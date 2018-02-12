package com.example.mobiletestapp.view.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.mobiletestapp.view.adapter.event.IItemClickListener;

import butterknife.ButterKnife;


public abstract class AbstractHolder<D> extends RecyclerView.ViewHolder {

    protected D model;
    protected IItemClickListener<D> clickListener;

    public AbstractHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    protected abstract void bind(int position);

    public void setData(D model, int position) {
        this.model = model;
        bind(position);
    }

    public void setOnItemClick(final IItemClickListener<D> clickListener) {
        this.clickListener = clickListener;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onItemClick(model);
                }
            }
        });
    }
}

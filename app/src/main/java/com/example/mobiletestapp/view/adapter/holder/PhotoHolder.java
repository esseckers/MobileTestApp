package com.example.mobiletestapp.view.adapter.holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mobiletestapp.R;
import com.example.mobiletestapp.model.ShotModel;
import com.example.mobiletestapp.utils.Utils;
import com.example.mobiletestapp.view.adapter.event.IItemClickListener;

import butterknife.BindView;

public class PhotoHolder extends AbstractHolder<ShotModel> {

    @BindView(R.id.photo)
    protected ImageView photo;
    @BindView(R.id.title)
    protected TextView title;
    @BindView(R.id.description)
    protected TextView description;

    @BindView(R.id.group)
    protected LinearLayout group;

    public PhotoHolder(View itemView) {
        super(itemView);
        ViewGroup.LayoutParams params = itemView.getLayoutParams();
        params.height = Utils.halfScreenHeight(itemView.getContext());
        itemView.setLayoutParams(params);
    }

    @Override
    protected void bind(int position) {
        Utils.loadImage(itemView.getContext(), photo, model.getImages().getImage());
        title.setText(model.getTitle());
        description.setText(model.getDescription());
    }

    @Override
    public void setOnItemClick(final IItemClickListener<ShotModel> clickListener) {
        itemView.setOnClickListener(view -> {
            if (clickListener != null) {
                clickListener.onItemClick(model, photo, group);
            }
        });
    }
}

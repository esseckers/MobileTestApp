package com.example.mobiletestapp.view.activtiy;

import android.support.v4.view.ViewCompat;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mobiletestapp.R;
import com.example.mobiletestapp.annataion.Layout;
import com.example.mobiletestapp.model.ShotModel;
import com.example.mobiletestapp.utils.Utils;

import butterknife.BindView;
import butterknife.OnClick;

@Layout(layoutRes = R.layout.activity_detail)
public class DetailActivity extends AbstractActivity {


    @BindView(R.id.photo_detail)
    protected ImageView photoDetail;
    @BindView(R.id.title)
    protected TextView title;
    @BindView(R.id.description)
    protected TextView description;

    @BindView(R.id.group)
    protected LinearLayout group;

    @Override
    protected void bindView() {
        Window window = getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        ShotModel model = null;
        if (getIntent() != null && getIntent().getExtras() != null) {
            model = (ShotModel) getIntent()
                    .getExtras().getSerializable(ShotModel.class.getSimpleName());
        }
        if (model != null) {
            Utils.loadImage(this, photoDetail, model.getImages().getImage());
            title.setText(model.getTitle());
            description.setText(model.getDescription());
        }
        ViewCompat.setTransitionName(photoDetail, Utils.IMAGE_TRANSITION_NAME);
        ViewCompat.setTransitionName(group, Utils.GROUP_TRANSITION_NAME);
    }

    @OnClick(R.id.back)
    void back() {
        this.onBackPressed();
    }
}

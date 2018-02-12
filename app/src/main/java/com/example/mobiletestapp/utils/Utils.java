package com.example.mobiletestapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mobiletestapp.model.ShotModel;
import com.example.mobiletestapp.view.activtiy.DetailActivity;

public class Utils {
    public static final String IMAGE_TRANSITION_NAME = "image_transition_name";
    public static final String GROUP_TRANSITION_NAME = "title_transition_name";

    public static void loadImage(final Context context, ImageView view, String url) {
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);
    }

    public static void startDetailScreen(Activity activity, ShotModel model, View... view) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                new Pair<>(view[0], Utils.IMAGE_TRANSITION_NAME),
                new Pair<>(view[1], Utils.GROUP_TRANSITION_NAME));

        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(ShotModel.class.getSimpleName(), model);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    public static int halfScreenHeight(Context context) {
        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            return (size.y / 2) - (actionBarHeight / 2);
        }
        return -1;
    }
}

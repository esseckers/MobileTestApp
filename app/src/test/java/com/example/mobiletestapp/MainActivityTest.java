package com.example.mobiletestapp;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mobiletestapp.view.activtiy.MainActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
@Config(packageName = "com.jetrube.mobiletestapp", manifest = Config.NONE)
public class MainActivityTest {

    MainActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void testProgressView() throws Exception {
        ProgressBar bar = activity.findViewById(R.id.progress);
        SwipeRefreshLayout swipe = activity.findViewById(R.id.swipe);
        activity.hideProgress();
        Assert.assertEquals("Progress view gone", bar.getVisibility(), View.GONE);
        Assert.assertEquals("SwipeRefreshLayout view gone", swipe.getVisibility(), View.VISIBLE);
        activity.showProgress();
        Assert.assertEquals("Progress view visible", bar.getVisibility(), View.VISIBLE);
        Assert.assertEquals("SwipeRefreshLayout view visible", swipe.getVisibility(), View.GONE);
    }


}
package com.example.mobiletestapp.view.activtiy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.mobiletestapp.annataion.Layout;
import com.example.mobiletestapp.presentor.AbstractPresenterImpl;
import com.example.mobiletestapp.presentor.contract.IBaseView;

import butterknife.ButterKnife;

public abstract class AbstractActivity<V extends AbstractPresenterImpl> extends AppCompatActivity implements IBaseView {

    private V screenPresenter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(getLayoutRes());

        ButterKnife.bind(this);
        screenPresenter = createPresenter();
    }

    protected V createPresenter() {
        return null;
    }

    protected V getPresenter() {
        return screenPresenter;
    }

    protected void bindView() {

    }

    /**
     * set layout resource for current activity (for setContentView method)
     *
     * @return layout resource
     */
    private int getLayoutRes() {
        Layout layout = this.getClass().getAnnotation(Layout.class);
        return layout != null ? layout.layoutRes() : 0;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (screenPresenter != null) {
            screenPresenter.start();
        }
        bindView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (screenPresenter != null) {
            screenPresenter.stop();
        }
    }
}

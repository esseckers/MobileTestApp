package com.example.mobiletestapp.presentor;

import android.support.annotation.CallSuper;
import android.util.Log;

import com.example.mobiletestapp.network.event.IDataCallback;
import com.example.mobiletestapp.presentor.contract.IBasePresenter;
import com.example.mobiletestapp.presentor.contract.IBaseView;
import com.example.mobiletestapp.utils.RxUtils;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

public abstract class AbstractPresenterImpl<T extends IBaseView> implements IBasePresenter {

    private static final String TAG = AbstractPresenterImpl.class.getSimpleName();
    protected T view;
    private CompositeDisposable compositeDisposable;

    public AbstractPresenterImpl(T iBaseViewContact) {
        this.view = iBaseViewContact;
    }

    @Override
    public void start() {
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void stop() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    protected <D> void execute(Single<D> single, IDataCallback<D> callback) {
        RxUtils.executeSingle(single, compositeDisposable, callback);
    }

    protected class SimpleDataCallback<D> implements IDataCallback<D> {

        @Override
        public void onStart() {
            view.showProgress();
        }

        @CallSuper
        @Override
        public void onResponse(D d) {
            view.hideProgress();
        }

        @CallSuper
        @Override
        public void onFail(Throwable throwable) {
            view.hideProgress();
            Log.e(TAG, throwable.getMessage(), throwable);
        }
    }
}

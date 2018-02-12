package com.example.mobiletestapp.utils;

import com.example.mobiletestapp.network.event.IDataCallback;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class RxUtils {

    /**
     * Create from {@link Observable} from function
     *
     * @param func Callable<T>
     */
    public static <DATA> Single<DATA> makeSingle(Callable<DATA> func) {
        return Single.fromCallable(func);
    }

    /**
     * Method for starting to perform asynchronous tasks
     *
     * @param observable          @see {@link Observable}
     * @param compositeDisposable @see {@link CompositeDisposable}
     * @param callback            @see {@link IDataCallback}
     */
    public static <DATA> void executeSingle(Single<DATA> observable,
                                            CompositeDisposable compositeDisposable,
                                            IDataCallback<DATA> callback) {
        observable.compose(applySchedulers())
                .doOnSubscribe(disposable -> {
                    callback.onStart();
                    compositeDisposable.add(disposable);
                }).subscribe(callback::onResponse, callback::onFail);
    }

    private static <T> SingleTransformer<T, T> applySchedulers() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

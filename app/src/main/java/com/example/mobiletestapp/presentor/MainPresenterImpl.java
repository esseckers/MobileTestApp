package com.example.mobiletestapp.presentor;

import com.example.mobiletestapp.App;
import com.example.mobiletestapp.R;
import com.example.mobiletestapp.database.DataBaseGateway;
import com.example.mobiletestapp.model.Images;
import com.example.mobiletestapp.model.ShotModel;
import com.example.mobiletestapp.network.NetworkManager;
import com.example.mobiletestapp.presentor.contract.IShotsContract;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class MainPresenterImpl extends AbstractPresenterImpl<IShotsContract.View> implements IShotsContract.Presenter {

    public MainPresenterImpl(IShotsContract.View iBaseViewContact) {
        super(iBaseViewContact);
    }

    @Override
    public void getShots() {
        execute(request(), new SimpleDataCallback<List<ShotModel>>() {

            @Override
            public void onResponse(List<ShotModel> models) {
                super.onResponse(models);
                view.showShots(models);
            }

            @Override
            public void onFail(Throwable throwable) {
                super.onFail(throwable);
                view.onFailLoadShots();
            }
        });
    }

    private List<ShotModel> stub() {
        List<ShotModel> models = new ArrayList<>();
        models.add(new ShotModel(new Images("https://pp.userapi.com/c10367/u52156020/-6/y_5a6fcb0a.jpg"),
                App.getInstance().getResources().getString(R.string.lorem_ipsum), App.getInstance().getResources().getString(R.string.lorem_ipsum)));
        models.add(new ShotModel(new Images("http://www.androhub.com/wp-content/uploads/2015/09/staggeredrecyclerview_banner.jpg"),
                App.getInstance().getResources().getString(R.string.lorem_ipsum), App.getInstance().getResources().getString(R.string.lorem_ipsum)));
        models.add(new ShotModel(new Images("https://pp.userapi.com/c10367/u52156020/-6/y_5a6fcb0a.jpg"),
                App.getInstance().getResources().getString(R.string.lorem_ipsum), App.getInstance().getResources().getString(R.string.lorem_ipsum)));
        models.add(new ShotModel(new Images("https://i.stack.imgur.com/EYFqg.png"),
                App.getInstance().getResources().getString(R.string.lorem_ipsum), App.getInstance().getResources().getString(R.string.lorem_ipsum)));
        models.add(new ShotModel(new Images("http://www.androhub.com/wp-content/uploads/2015/09/staggeredrecyclerview_banner.jpg"),
                App.getInstance().getResources().getString(R.string.lorem_ipsum), App.getInstance().getResources().getString(R.string.lorem_ipsum)));
        models.add(new ShotModel(new Images("http://blog.nkdroidsolutions.com/wp-content/uploads/2016/02/device-2016-02-18-200435-1.png"),
                App.getInstance().getResources().getString(R.string.lorem_ipsum), App.getInstance().getResources().getString(R.string.lorem_ipsum)));
        return models;
    }

    private Single<List<ShotModel>> request() {
        return NetworkManager.getInstance().fetchShots().map(models -> {
            if (models != null && !models.isEmpty()) {
                DataBaseGateway.getInstance().createOrUpdateList(ShotModel.class, models);
            } else {
                return stub();
            }
            return models;
        });
    }

    @Override
    public void login() {
        view.onLogin();
    }
}

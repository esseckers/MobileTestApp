package com.example.mobiletestapp.presentor.contract;


import com.example.mobiletestapp.model.ShotModel;

import java.util.List;

public interface IShotsContract {

    interface View extends IBaseView {

        void showShots(List<ShotModel> models);

        void onLogin();

        void onFailLoadShots();
    }

    interface Presenter extends IBasePresenter {
        void getShots();

        void login();
    }
}

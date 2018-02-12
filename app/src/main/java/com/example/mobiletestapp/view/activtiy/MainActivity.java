package com.example.mobiletestapp.view.activtiy;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.agilie.dribbblesdk.service.auth.AuthCredentials;
import com.agilie.dribbblesdk.service.auth.DribbbleAuthHelper;
import com.agilie.dribbblesdk.service.auth.DribbbleConstants;
import com.google.api.client.auth.oauth2.Credential;
import com.example.mobiletestapp.Preference;
import com.example.mobiletestapp.R;
import com.example.mobiletestapp.annataion.Layout;
import com.example.mobiletestapp.model.ShotModel;
import com.example.mobiletestapp.presentor.MainPresenterImpl;
import com.example.mobiletestapp.presentor.contract.IShotsContract;
import com.example.mobiletestapp.utils.Utils;
import com.example.mobiletestapp.view.adapter.PhotoAdapter;
import com.example.mobiletestapp.view.adapter.event.SimpleItemClickListener;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Layout(layoutRes = R.layout.activity_main)
public class MainActivity extends AbstractActivity<MainPresenterImpl> implements IShotsContract.View {

    private static final String DRIBBBLE_CLIENT_ID = "7be2cb78c44516402bae7b1e76a7c92fd1a9e8d332ef6ca0607b7d6def72b2b3";
    private static final String DRIBBBLE_CLIENT_SECRET = "328df594a9827bb4bbdcaf111ee28368125d51b98487be71b148b7e95d4d8f4a";
    private static final String DRIBBBLE_CLIENT_REDIRECT_URL = "https://dribbble.com/esseckers";

    @BindView(R.id.gallery)
    RecyclerView gallery;

    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;

    @BindView(R.id.progress)
    ProgressBar progress;

    @BindView(R.id.splash_view)
    FrameLayout splash;


    @Override
    protected MainPresenterImpl createPresenter() {
        return new MainPresenterImpl(this);
    }

    @Override
    protected void bindView() {
        if (Preference.getToken().isEmpty()) {
            splash.setVisibility(View.VISIBLE);
        } else {
            splash.setVisibility(View.GONE);
            getPresenter().getShots();
        }
        swipe.setOnRefreshListener(() -> getPresenter().getShots());
    }

    @Override
    public void showShots(List<ShotModel> shotModels) {
        swipe.setRefreshing(false);
        PhotoAdapter adapter = new PhotoAdapter(R.layout.item_photo);
        adapter.setClickListener(new SimpleItemClickListener<ShotModel>() {
            @Override
            public void onItemClick(ShotModel data, View... views) {
                Utils.startDetailScreen(MainActivity.this, data, views);
            }
        });

        gallery.setLayoutManager(new LinearLayoutManager(this));
        gallery.setAdapter(adapter);
        adapter.setData(shotModels);
    }

    @Override
    public void onLogin() {
        final AuthCredentials credentials = AuthCredentials.newBuilder(
                DRIBBBLE_CLIENT_ID,
                DRIBBBLE_CLIENT_SECRET,
                "",
                DRIBBBLE_CLIENT_REDIRECT_URL)
                .setScope(Arrays.asList(
                        DribbbleConstants.SCOPE_PUBLIC,
                        DribbbleConstants.SCOPE_COMMENT))
                .build();


        DribbbleAuthHelper.startOauthDialog(this, credentials, new DribbbleAuthHelper.AuthListener() {

            @Override
            public void onSuccess(final Credential credential) {
                String accessToken = credential.getAccessToken();
                if (accessToken != null) {
                    Preference.saveToken(accessToken);
                }
                runOnUiThread(() -> {
                    splash.setVisibility(View.GONE);
                    getPresenter().getShots();
                });
            }

            @Override
            public void onError(final Exception ex) {
                runOnUiThread(() -> Toast.makeText(MainActivity.this, ex.getMessage(),
                        Toast.LENGTH_LONG).show());
            }
        });
    }

    @Override
    public void onFailLoadShots() {
        Toast.makeText(MainActivity.this, "Fail to load shots, check internet connection",
                Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.login)
    void loginClick() {
        getPresenter().login();
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
        swipe.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        swipe.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
    }
}

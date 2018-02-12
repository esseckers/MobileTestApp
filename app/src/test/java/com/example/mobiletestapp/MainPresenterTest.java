package com.example.mobiletestapp;

import com.example.mobiletestapp.presentor.MainPresenterImpl;
import com.example.mobiletestapp.presentor.contract.IShotsContract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    IShotsContract.View view;

    private MainPresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new MainPresenterImpl(view);
    }

    @Test
    public void testGetShotsFail() throws Exception {
        presenter.getShots();
        Mockito.verify(view).showProgress();
        Mockito.verify(view).onFailLoadShots();
        Mockito.verify(view).hideProgress();
    }

    @Test
    public void testGetShotsSuccess() throws Exception {
        presenter.getShots();
        Mockito.verify(view).showProgress();
        Mockito.verify(view).showShots(null);
        Mockito.verify(view).hideProgress();
    }

    @Test
    public void testLogin() throws Exception {
        Mockito.verify(view).onLogin();
    }
}
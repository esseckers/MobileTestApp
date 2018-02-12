package com.example.mobiletestapp.model;

import android.os.Bundle;

import java.io.Serializable;

public abstract class AbstractModel implements Serializable {

    protected AbstractModel() {
    }

    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putSerializable(getBundleKey(), this);
        return b;
    }

    protected abstract String getBundleKey();
}

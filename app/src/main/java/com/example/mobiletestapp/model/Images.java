package com.example.mobiletestapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Images extends AbstractModel {
    @JsonProperty("hidpi")
    private String image;

    public String getImage() {
        return image;
    }

    public Images(String image) {
        this.image = image;
    }

    @Override
    protected String getBundleKey() {
        return Images.class.getSimpleName();
    }
}

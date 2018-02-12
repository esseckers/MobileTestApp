package com.example.mobiletestapp.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.example.mobiletestapp.database.DataBaseUtils;

@DatabaseTable(tableName = DataBaseUtils.SHOT)
public class ShotModel extends AbstractModel {

    @DatabaseField(columnName = DataBaseUtils.IMAGES, dataType = DataType.SERIALIZABLE)
    private Images images;
    @DatabaseField(columnName = DataBaseUtils.TITLE, dataType = DataType.STRING)
    private String title;
    @DatabaseField(columnName = DataBaseUtils.DESCRIPTION, dataType = DataType.STRING)
    private String description;

    public ShotModel(Images images, String title, String description) {
        this.images = images;
        this.title = title;
        this.description = description;
    }

    public Images getImages() {
        return images;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    protected String getBundleKey() {
        return ShotModel.class.getSimpleName();
    }
}

package br.com.interaje.productmanager.models;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by rayquaza on 15/04/16.
 */
public class Category extends SugarRecord implements Serializable {

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ID = "_id";
    public static final String TALBE_NAME = "category";

    private Long mId;
    private String name;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }
}

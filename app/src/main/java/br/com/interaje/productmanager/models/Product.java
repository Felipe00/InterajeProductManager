package br.com.interaje.productmanager.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.io.Serializable;


/**
 * Created by rayquaza on 15/04/16.
 */
public class Product extends SugarRecord implements Serializable {

    public static final String TABLE_NAME = "product";
    public static final String COLUMN_ID = "_id";
    public final static String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_PHOTO = "photo";
    public static final String COLUMN_CATEGORY_ID = "category_id";

    @Expose
    @SerializedName("id")
    private Long mId;
    private String name;
    private Double price;
    private byte[] photo;
    //private Long idCategory;
    private Category category;

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Retornando o ID do SUGAR ORM
     * @return
     */
    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

}

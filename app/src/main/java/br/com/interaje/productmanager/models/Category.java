package br.com.interaje.productmanager.models;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by rayquaza on 15/04/16.
 */
public class Category extends SugarRecord implements Serializable {

    private Long id;
    private String name;
    //private String something;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

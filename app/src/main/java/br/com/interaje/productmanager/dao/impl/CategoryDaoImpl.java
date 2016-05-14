package br.com.interaje.productmanager.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

import br.com.interaje.productmanager.dao.CategoryDAO;
import br.com.interaje.productmanager.databases.Database;
import br.com.interaje.productmanager.models.Category;

/**
 * Created by rayquaza on 11/05/16.
 */
public class CategoryDaoImpl implements CategoryDAO {

    @Override
    public Long save(Category category, Database database) {
        ContentValues cv = new ContentValues();
        database.open();
        Long id = 0L;

        try {
            if (category != null) {
                cv.put(Category.COLUMN_NAME, category.getName());
                cv.put(Category.COLUMN_ID, category.getmId());
                id = database.get().insert(Category.TALBE_NAME, null, cv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.close();
        }

        return id;
    }

    @Override
    public void update(Category category, Database database) {

    }

    @Override
    public List<Category> listAll(Database database) {
        return null;
    }

    @Override
    public void delete(Long id, Database database) {

    }

    @Override
    public Category findById(Long id, Database database) {
        Cursor cursor = null;
        database.open();
        try {
            cursor = database.get().rawQuery("select * from product", null);
            cursor.moveToFirst();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Category entity = new Category();
        if (cursor != null && !cursor.isClosed()) {
            entity.setmId(cursor.getLong(cursor.getColumnIndex(Category.COLUMN_ID)));
            entity.setName(cursor.getString(cursor.getColumnIndex(Category.COLUMN_NAME)));
        }

        if (cursor != null) {
            cursor.close();
        }
        database.close();

        return entity;
    }
}

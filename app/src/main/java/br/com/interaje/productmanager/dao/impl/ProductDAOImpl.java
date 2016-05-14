package br.com.interaje.productmanager.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.interaje.productmanager.dao.ProductDAO;
import br.com.interaje.productmanager.databases.Database;
import br.com.interaje.productmanager.databases.ProductManagerDatabaseHelper;
import br.com.interaje.productmanager.models.Category;
import br.com.interaje.productmanager.models.Product;

/**
 * Created by rayquaza on 06/05/16.
 */
public class ProductDAOImpl implements ProductDAO {

    private static final String TAG = "PRODUCT_DAO_IMPL";

    @Override
    public void save(Product product, Database database) {
        ContentValues cv = new ContentValues();
        database.open();
        Long id = 0L;
        try {
            if (product != null) {
                cv.put(Product.COLUMN_NAME, product.getName());
                cv.put(Product.COLUMN_PRICE, product.getPrice());
                cv.put(Product.COLUMN_PHOTO, product.getPhoto());
                cv.put(Product.COLUMN_CATEGORY_ID, product.getCategory().getmId());
                id = database.get().insert(Product.TABLE_NAME, null, cv);
            }
        } catch (Exception e) {
            e.getCause();
            // Tratar o erro
        } finally {
            database.close();
            // TODO salvar photo como blob. Primeiro coloca no inputstream
            Log.d(TAG, ">> " + id);
        }
    }

    @Override
    public void update(Product product, Database database) {
        ContentValues cv = new ContentValues();
        database.open();
        try {
            if (product != null) {
                cv.put(Product.COLUMN_ID, product.getmId());
                cv.put(Product.COLUMN_NAME, product.getName());
                cv.put(Product.COLUMN_PRICE, product.getPrice());
                cv.put(Product.COLUMN_CATEGORY_ID, product.getCategory().getmId());

                String idProduct = String.valueOf(product.getmId());
                database.get().update(
                        Product.TABLE_NAME, cv, "id", new String[]{idProduct});
            }
        } catch (Exception e) {
            e.getCause();
        } finally {
            database.close();
        }
    }

    @Override
    public List<Product> listAll(Database database) {
        Cursor cursor = null;
        List<Product> listEntity = null;
        Product entity;

        database.open();

        try {
            cursor = database.get().rawQuery("select * from product", null);
            cursor.moveToFirst();
        } catch (Exception e) {
            e.getCause();
        }

        if (cursor != null && !cursor.isClosed()) {
            listEntity = new ArrayList<Product>();

            while (cursor.moveToNext()) {
                entity = new Product();
                entity.setId(cursor.getLong(cursor.getColumnIndex(Product.COLUMN_ID)));
                entity.setName(cursor.getString(cursor.getColumnIndex(Product.COLUMN_NAME)));
                entity.setPrice(cursor.getDouble(cursor.getColumnIndex(Product.COLUMN_PRICE)));
                Category category = new Category();
                entity.setCategory(category);
                entity.getCategory().setmId(cursor.getLong(cursor.getColumnIndexOrThrow(Product.COLUMN_CATEGORY_ID)));

                // entity.setPhoto(cursor.getBlob(cursor.getColumnIndex(Product.COLUMN_PHOTO)));

                listEntity.add(entity);
            }
            cursor.close();
        }

        database.close();
        return listEntity;
    }

    @Override
    public void delete(Long id, Database database) {
        int rows = database.get().delete(Product.TABLE_NAME, "id = ?", new String[]{String.valueOf(id)});

        if (rows > 0) {
            // Deletou com sucesso;
        }
    }
}

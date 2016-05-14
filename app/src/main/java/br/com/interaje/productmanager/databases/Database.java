package br.com.interaje.productmanager.databases;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by rayquaza on 06/05/16.
 */
public class Database {

    private SQLiteDatabase sqld;
    private ProductManagerDatabaseHelper productDatabaseManager;

    public Database(ProductManagerDatabaseHelper databaseManager) {
        this.productDatabaseManager = databaseManager;
    }

    public void open() {
        if (productDatabaseManager != null) {
            sqld = productDatabaseManager.getWritableDatabase();
        }
    }

    public SQLiteDatabase get() {
        if (sqld != null && sqld.isOpen()) {
            return sqld;
        }
        return null;
    }

    public void close() {
        if (productDatabaseManager != null) {
            productDatabaseManager.close();
        }
    }

}

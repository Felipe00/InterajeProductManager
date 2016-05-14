package br.com.interaje.productmanager.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rayquaza on 06/05/16.
 */
public class ProductManagerDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "prodManDB.db";
    public static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_PRODUCT = "create table product"
            + "("
            + "_id" + " integer primary key autoincrement, "
            + "name" + " text not null, "
            + "price" + " real, "
            + "photo" + " blob, "
            + "category_id" + " integer "
            + ");";

    public static final String CREATE_TABLE_CATEGORY = "create table category"
            +"(" +
            "_id" + " integer primary key autoincrement, " +
            "name" + " text not null" +
            ");";

    public ProductManagerDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        /**
         * Context
         * Nome do banco
         * Fábrica de cursors
         * Versão do banco
         */
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PRODUCT);
        db.execSQL(CREATE_TABLE_CATEGORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Atualiza o banco.
    }
}


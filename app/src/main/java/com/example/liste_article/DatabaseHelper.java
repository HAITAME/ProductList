package com.example.liste_article;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "Produits";
    public static final String COLUMN_LIBELLE = "libelle";
    public static final String COLUMN_PU = "pu";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_LIBELLE + " TEXT, "
                + COLUMN_PU + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }

    public List<Produit> getAllProduits() {
        List<Produit> ProduitList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String libelle = cursor.getString(cursor.getColumnIndex(COLUMN_LIBELLE));
                String pu = cursor.getString(cursor.getColumnIndex(COLUMN_PU));
                Produit produit = new Produit(libelle, pu);
                ProduitList.add(produit);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return ProduitList;
    }

    public long insertProduit(Produit produit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("libelle", produit.getLibelle());
        values.put("pu", produit.getPu());

        long rowId = db.insert("produits", null, values);
        db.close();
        return rowId;
    }
}

package com.example.imerge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public DBManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();

    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String namaBarang, int jumlah, double harga, String kategori, byte[] gambar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NamaBarang, namaBarang);
        contentValues.put(DatabaseHelper.jumlah, jumlah);
        contentValues.put(DatabaseHelper.harga, harga);
        contentValues.put(DatabaseHelper.kategori, kategori);
        contentValues.put(DatabaseHelper.gambar, gambar);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }


    public Cursor fetch() {
        String[] columns = {
                DatabaseHelper._ID,
                DatabaseHelper.NamaBarang,
                DatabaseHelper.jumlah,
                DatabaseHelper.harga,
                DatabaseHelper.kategori,
                DatabaseHelper.gambar
        };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long id, String namaBarang, int jumlah, double harga, String kategori, byte[] gambar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NamaBarang, namaBarang);
        contentValues.put(DatabaseHelper.jumlah, jumlah);
        contentValues.put(DatabaseHelper.harga, harga);
        contentValues.put(DatabaseHelper.kategori, kategori);
        contentValues.put(DatabaseHelper.gambar, gambar);
        return database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = ?", new String[]{String.valueOf(id)});
    }

    public void delete(long id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + " = ?", new String[]{String.valueOf(id)});
    }
}


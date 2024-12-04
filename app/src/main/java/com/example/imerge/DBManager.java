package com.example.imerge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public DBManager(Context context) {
        dbHelper = new DatabaseHelper(context);
        Log.d("DBManager", "DatabaseHelper initialized.");
    }

    public void open() {
        if (database == null) {
            database = dbHelper.getWritableDatabase();
            if (database != null) {
                Log.d("DBManager", "Database opened successfully.");
            } else {
                Log.e("DBManager", "Failed to open database.");
            }
        }
    }

    public void close() {
        dbHelper.close();
    }



    public void insert(String namaBarang, int jumlah, double harga, String kategori, byte[] gambar) {
        ContentValues values = new ContentValues();
        values.put("nama_barang", namaBarang);
        values.put("jumlah", jumlah);
        values.put("harga", harga);
        values.put("kategori", kategori);
        values.put("gambarBLOB", gambar);

        database.insert("storage", null, values);
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
    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<>();
        if (database == null) {
            Log.e("DBManager", "Database is null! Ensure it's opened before accessing.");
            return itemList;
        }
        String[] columns = {"_id", "nama_barang", "jumlah", "harga", "kategori", "gambarBLOB"};
        Cursor cursor = database.query("storage", columns, null, null, null, null, null);
        if (cursor != null) {
            // Get column indices for each column to check if they exist
            int idIndex = cursor.getColumnIndex("_id");
            int namaBarangIndex = cursor.getColumnIndex("nama_barang");
            int jumlahIndex = cursor.getColumnIndex("jumlah");
            int hargaIndex = cursor.getColumnIndex("harga");
            int kategoriIndex = cursor.getColumnIndex("kategori");
            int gambarIndex = cursor.getColumnIndex("gambarBLOB");

            Log.d("DBManager", "Column indices: _id=" + idIndex + ", nama_barang=" + namaBarangIndex + ", jumlah=" + jumlahIndex +
                    ", harga=" + hargaIndex + ", kategori=" + kategoriIndex + ", gambarBLOB=" + gambarIndex);

            if (idIndex != -1) {
                cursor.moveToFirst();
                do {
                    int id = cursor.getInt(idIndex);
                    String namaBarang = cursor.getString(namaBarangIndex);
                    int jumlah = cursor.getInt(jumlahIndex);
                    double harga = cursor.getDouble(hargaIndex);
                    String kategori = cursor.getString(kategoriIndex);
                    String gambar = cursor.getString(gambarIndex);
                    // Add item to the list
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return itemList;
    }


}



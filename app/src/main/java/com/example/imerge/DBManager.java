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
import java.util.Arrays;
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
        } else {
            String[] columns = {"_id", "nama_barang", "jumlah", "harga", "kategori", "gambarBLOB"};
            Cursor cursor = database.query("storage", columns, null, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(0);
                        String namaBarang = cursor.getString(1);
                        int jumlah = cursor.getInt(2);
                        double harga = cursor.getDouble(3);
                        String kategori = cursor.getString(4);

                        // Correct handling of BLOB column
                        byte[] gambarBytes = cursor.getBlob(5); // Get BLOB as byte array
                        String gambar = Arrays.toString(gambarBytes); // If you need to store it as a string (e.g., for debugging)

                        Item item = new Item(id, namaBarang, jumlah, harga, kategori, gambar);
                        itemList.add(item);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        }
        return itemList;
    }



}



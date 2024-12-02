package com.example.imerge;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "storage";

    // Nama kolom dalam tabel
    public static final String _ID = "_id";
    public static final String NamaBarang = "nama_barang";
    public static final String jumlah = "jumlah";

    public static final String harga = "harga";

    public static final String kategori = "kategori";

    public static final String gambar = "gambar";

    // Nama Database
    static final String DB_NAME = "IMERGE.DB";

    // Versi Database
    static final int DB_VERSION = 1;

    // Membuat query tabel
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NamaBarang + " TEXT NOT NULL, "
            + jumlah + " INTEGER NOT NULL, "
            + harga + " REAL, "
            + kategori + " TEXT NOT NULL, "
            + gambar +"BLOB);";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}


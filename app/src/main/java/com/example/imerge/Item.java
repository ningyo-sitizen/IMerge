package com.example.imerge;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Item {
    private int id;
    private String namaBarang;
    private int jumlah;
    private double harga;
    private String kategori;
    private byte[] gambar;

    public Item(int id, String namaBarang, int jumlah, double harga, String kategori, byte[] gambar) {
        this.id = id;
        this.namaBarang = namaBarang;
        this.jumlah = jumlah;
        this.harga = harga;
        this.kategori = kategori;
        this.gambar = gambar;
    }

    public int getId() {
        return id;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public double getHarga() {
        return harga;
    }

    public String getKategori() {
        return kategori;
    }

    public byte[] getGambar() {
        return gambar;
    }

    public Bitmap getBitmapGambar() {
        return BitmapFactory.decodeByteArray(gambar, 0, gambar.length);
    }
}

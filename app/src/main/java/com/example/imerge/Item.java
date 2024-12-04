// Item.java
package com.example.imerge;

public class Item {
    private int id;
    private String namaBarang;
    private int jumlah;
    private double harga;
    private String kategori;
    private String gambar;

    public Item(int id, String namaBarang, int jumlah, double harga, String kategori, String gambar) {
        this.id = id;
        this.namaBarang = namaBarang;
        this.jumlah = jumlah;
        this.harga = harga;
        this.kategori = kategori;
        this.gambar = gambar;
    }

    // Getters and Setters for the fields
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

    public String getGambar() {
        return gambar;
    }
}

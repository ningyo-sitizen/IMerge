package com.example.imerge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fabMain, fabAdd, fabRemove, fabLog;
    private DBManager dbManager;
    private boolean isFabOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storage);

        fabMain = findViewById(R.id.fab_main);
        fabAdd = findViewById(R.id.fab_add);
        fabRemove = findViewById(R.id.fab_remove);
        fabLog = findViewById(R.id.fab_log);

        dbManager = new DBManager(this);
        dbManager.open();

        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFabOpen) {
                    closeFabMenu();
                } else {
                    openFabMenu();
                }
            }
        });

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String namaBarang = "Sample Item";
//                int jumlah = 10;
//                double harga = 99.99;
//                String kategori = "Sample Category";
//                byte[] gambar = null;
//
//                // Tambahkan data ke database
//                dbManager.insert(namaBarang, jumlah, harga, kategori, gambar);
//
//                Toast.makeText(MainActivity.this, "You clicked add button", Toast.LENGTH_LONG).show();
//                closeFabMenu();

                Intent intent = new Intent(MainActivity.this, add.class);
                startActivity(intent);
            }
        });

        fabRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You clicked remove button", Toast.LENGTH_LONG).show();
                closeFabMenu();
            }
        });

        fabLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You clicked remove button", Toast.LENGTH_LONG).show();
                closeFabMenu();
            }
        });
    }

    private void openFabMenu() {
        isFabOpen = true;

        fabAdd.setVisibility(View.VISIBLE);
        TranslateAnimation translateAdd = new TranslateAnimation(0, 0, fabMain.getHeight(), 0);
        translateAdd.setDuration(300);
        fabAdd.startAnimation(translateAdd);

        fabRemove.setVisibility(View.VISIBLE);
        TranslateAnimation translateRemove = new TranslateAnimation(0, 0, fabMain.getHeight() * 2, 0);
        translateRemove.setDuration(300);
        fabRemove.startAnimation(translateRemove);

        fabLog.setVisibility(View.VISIBLE);
        TranslateAnimation translateLog = new TranslateAnimation(0, 0, fabMain.getHeight() * 3, 0);
        translateLog.setDuration(300);
        fabLog.startAnimation(translateLog);
    }

    private void closeFabMenu() {
        isFabOpen = false;

        TranslateAnimation translateAdd = new TranslateAnimation(0, 0, 0, fabMain.getHeight());
        translateAdd.setDuration(300);
        fabAdd.startAnimation(translateAdd);
        fabAdd.setVisibility(View.GONE);

        TranslateAnimation translateRemove = new TranslateAnimation(0, 0, 0, fabMain.getHeight() * 2);
        translateRemove.setDuration(300);
        fabRemove.startAnimation(translateRemove);
        fabRemove.setVisibility(View.GONE);

        TranslateAnimation translateLog = new TranslateAnimation(0, 0, 0, fabMain.getHeight() * 3);
        translateLog.setDuration(300);
        fabLog.startAnimation(translateLog);
        fabLog.setVisibility(View.GONE);
    }


}


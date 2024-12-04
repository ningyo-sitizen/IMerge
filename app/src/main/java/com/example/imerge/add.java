package com.example.imerge;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class add extends AppCompatActivity {
    EditText namabarang;
    EditText jumlahbarang;
    EditText hargabarang;
    Button Button_add;

    private ImageView selectedImage;
    private byte[] imageByteArray;
    private static final int PICK_IMAGE_REQUEST = 1;

    private DBManager dbManager;
    private String selectedItem;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        Spinner spinner = findViewById(R.id.spinner1);
        dbManager = new DBManager(this);
        dbManager.open();

        String[] categories = {"elektronik", "makanan", "baju"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = parent.getItemAtPosition(position).toString(); // Menyimpan nilai item yang dipilih
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedItem = null; // Reset jika tidak ada yang dipilih
            }
        });

        Button_add = findViewById(R.id.button_add);
        namabarang = findViewById(R.id.nama_barangin);
        jumlahbarang = findViewById(R.id.jumlah_barangIN);
        hargabarang = findViewById(R.id.harga_barangIN);
        Button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaBarang = namabarang.getText().toString();
                int jumlah = Integer.parseInt(jumlahbarang.getText().toString());
                double harga = Double.parseDouble(hargabarang.getText().toString());
                String kategori = selectedItem;

                dbManager.insert(namaBarang, jumlah, harga, kategori, imageByteArray);
                Toast.makeText(add.this, "Data berhasil disimpan!", Toast.LENGTH_SHORT).show();
            }
        });


        selectedImage = findViewById(R.id.selectedImage);
        Button buttonSelectImage = findViewById(R.id.button_select_image);

        buttonSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), PICK_IMAGE_REQUEST);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            try {
                Uri imageUri = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                selectedImage.setImageBitmap(bitmap);

                // Konversi gambar ke byte array
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                imageByteArray = stream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Gagal memuat gambar", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

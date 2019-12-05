package com.apahayo.tubespabpaim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DaftarActivity extends AppCompatActivity {
    private EditText nama;
    private Button daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        nama = findViewById(R.id.namaET);
        daftar = findViewById(R.id.daftarBtn);


    }

    public void daftarAkun(View view) {

        Intent intent = new Intent(DaftarActivity.this,WelcomeActivity.class);
        startActivity(intent);

    }
}

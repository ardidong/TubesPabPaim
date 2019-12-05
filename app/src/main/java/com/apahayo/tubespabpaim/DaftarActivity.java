package com.apahayo.tubespabpaim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DaftarActivity extends AppCompatActivity {

    private DatabaseReference database;
    private EditText nama;
    private Button daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        nama = findViewById(R.id.namaET);
        daftar = findViewById(R.id.daftarBtn);

        database = FirebaseDatabase.getInstance().getReference();


    }

    public void daftarAkun(View view) {

        if(!isEmpty(nama.getText().toString())){
            database.child("user").push().setValue(nama.getText().toString());

            Intent intent = new Intent(DaftarActivity.this,WelcomeActivity.class);
            startActivity(intent);
        }else{
            Snackbar.make(findViewById(R.id.daftarBtn), "Data barang tidak boleh kosong", Snackbar.LENGTH_LONG).show();
        }

        InputMethodManager imm = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(
                nama.getWindowToken(), 0);

    }

    private boolean isEmpty(String s) {
        // Cek apakah ada fields yang kosong, sebelum disubmit
        return TextUtils.isEmpty(s);
    }
}

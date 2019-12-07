package com.apahayo.tubespabpaim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HasilActivity extends AppCompatActivity {

    private Button btnOK;
    private TextView tvIndikasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        btnOK = findViewById(R.id.btn_ok);
        tvIndikasi = findViewById(R.id.indikasi);

        Intent intent = getIntent();
        String indikasi = intent.getStringExtra("indikasi");

        //tvIndikasi.setText(indikasi);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HasilActivity.this, NavBotActivity.class));
                finish();
            }
        });



    }
}

package com.apahayo.tubespabpaim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class HalamanUtama extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_utama);

        RecyclerView mRecyclerView = findViewById(R.id.listKegiatan);

        AktifitasAdapter listAdapter = new AktifitasAdapter(HalamanUtama.this);
        mRecyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HalamanUtama.this);
        mRecyclerView.setLayoutManager(layoutManager);

    }
}

package com.apahayo.tubespabpaim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.apahayo.tubespabpaim.Adapter.AktifitasAdapter;
import com.apahayo.tubespabpaim.Adapter.GejalaAdapter;
import com.apahayo.tubespabpaim.Model.Gejala;
import com.apahayo.tubespabpaim.Model.Mood;

import java.util.ArrayList;

public class PilihGejalaActivity extends AppCompatActivity {

    private ArrayList<Gejala> listGejala;
    RecyclerView mRecyclerView;
    GejalaAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_gejala);

        mRecyclerView = findViewById(R.id.gejalaRV);


        listGejala = new ArrayList<>();
        listAdapter = new GejalaAdapter(PilihGejalaActivity.this, listGejala);
        mRecyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PilihGejalaActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);

        initialize();

    }

    private void initialize() {
        String[] gejala = getResources().getStringArray(R.array.gejala);
        listGejala.clear();

        for (int i = 0; i < gejala.length; i++) {
            Gejala mGejala = new Gejala();
            mGejala.setPertanyaan(gejala[i]);
            listGejala.add(mGejala);
        }

        listAdapter.notifyDataSetChanged();
    }
}

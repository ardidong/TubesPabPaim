package com.apahayo.tubespabpaim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.apahayo.tubespabpaim.Adapter.AktifitasAdapter;
import com.apahayo.tubespabpaim.Model.Mood;

import java.util.ArrayList;

public class HalamanUtama extends AppCompatActivity {

    private ArrayList<Mood> moodList;
    private AktifitasAdapter aktifitasAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_utama);

        recyclerView = findViewById(R.id.listKegiatan);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HalamanUtama.this);
        recyclerView.setLayoutManager(layoutManager);

        moodList = new ArrayList<>();
        aktifitasAdapter= new AktifitasAdapter(HalamanUtama.this,moodList);
        recyclerView.setAdapter(aktifitasAdapter);

        initialize();

    }


    public void initialize(){
        String[] mood = getResources().getStringArray(R.array.kegiatan);

        for (int i = 0; i < mood.length; i++) {
            moodList.add(new Mood(mood[i]));
        }

        aktifitasAdapter.notifyDataSetChanged();

    }
}

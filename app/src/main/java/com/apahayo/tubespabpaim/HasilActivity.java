package com.apahayo.tubespabpaim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.apahayo.tubespabpaim.Adapter.SaranAdapter;
import com.apahayo.tubespabpaim.Adapter.SolusiAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class HasilActivity extends AppCompatActivity {

    private String kode;
    private RecyclerView mRecyclerView;
    private SolusiAdapter sAdapter;
    private ArrayList<String> listSolusi;
    private TextView penjelasan;
    private Button saranOK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);
        saranOK = findViewById(R.id.btn_ok_saran);
        penjelasan = findViewById(R.id.penjalasan_solusi_tv);

        saranOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HasilActivity.this,SaranActivity.class);
                startActivity(intent);
                finish();
            }
        });

        kode = getIntent().getStringExtra("KODE");

        mRecyclerView = findViewById(R.id.solusi_rv);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        listSolusi = new ArrayList<>();
        sAdapter = new SolusiAdapter(HasilActivity.this, listSolusi);
        mRecyclerView.setAdapter(sAdapter);

        initialize();

        mRecyclerView.setItemViewCacheSize(listSolusi.size());
    }

    public void initialize() {
        String[] solusiList;


        switch (kode) {
            case "C01":
                penjelasan.setText(getResources().getString(R.string.penjelasan_C01));
                solusiList = getResources().getStringArray(R.array.solusi_C01);
                break;
            case "C02":
                penjelasan.setText(getResources().getString(R.string.penjelasan_C02));
                solusiList = getResources().getStringArray(R.array.solusi_C02);
                break;
            case "C03":
                penjelasan.setText(getResources().getString(R.string.penjelasan_C03));
                solusiList = getResources().getStringArray(R.array.solusi_C03);
                break;
            case "C04":
                penjelasan.setText(getResources().getString(R.string.penjelasan_C04));
                solusiList = getResources().getStringArray(R.array.solusi_C04);
                break;
            case "C05":
                penjelasan.setText(getResources().getString(R.string.penjelasan_C05));
                solusiList = getResources().getStringArray(R.array.solusi_C05);
                break;
            case "C06":
                penjelasan.setText(getResources().getString(R.string.penjelasan_C06));
                solusiList = getResources().getStringArray(R.array.solusi_C06);
                break;
            case "C07":
                penjelasan.setText(getResources().getString(R.string.penjelasan_C07));
                solusiList = getResources().getStringArray(R.array.solusi_C07);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + kode);
        }

        listSolusi.addAll(Arrays.asList(solusiList));

        sAdapter.notifyDataSetChanged();
    }
}

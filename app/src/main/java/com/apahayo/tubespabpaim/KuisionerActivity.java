package com.apahayo.tubespabpaim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.apahayo.tubespabpaim.Adapter.PertanyaanAdapter;
import com.apahayo.tubespabpaim.Model.Pertanyaan;

import java.util.ArrayList;
import java.util.Collections;

public class KuisionerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Pertanyaan> mPertanyaanData;
    private ArrayList<Integer> mJumlah;
    private PertanyaanAdapter mAdapter;
    private ProgressBar mProgressBar;
    private TextView mTerjawab;
    private int progres;
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuisioner);

        mTerjawab = findViewById(R.id.tv_terjawab);
        mProgressBar = findViewById(R.id.pb_kuisioner);
        mRecyclerView = findViewById(R.id.recyclerView);
        submit = findViewById(R.id.submit);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        PagerSnapHelper helper = new PagerSnapHelper();
        helper.attachToRecyclerView(mRecyclerView);

        mPertanyaanData = new ArrayList<>();
        mAdapter = new PertanyaanAdapter(mPertanyaanData, this);
        mRecyclerView.setAdapter(mAdapter);


        mAdapter.setOnItemClickListener(new PertanyaanAdapter.OnItemClickListener() {
            @Override
            public void onChoiceClick(final int position) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mJumlah.get(position) == 0) {
                            mJumlah.set(position, 1);
                        }

                        progres = Collections.frequency(mJumlah, 1);
                        mProgressBar.setProgress(progres);
                        mTerjawab.setText("Terjawab " + progres + " dari " + mPertanyaanData.size());

                        if (position < mJumlah.size() - 1) {
                            if (mJumlah.get(position + 1) == 0) {
                                mRecyclerView.smoothScrollToPosition(position + 1);
                            }
                        }

                        if (progres == mJumlah.size()) {
                            submit.setBackgroundResource(R.drawable.buttonsolusi);
                            submit.setText("Selesai");
                            submit.setTextColor(Color.parseColor("#FFFFFF"));
                        }

                    }
                }, 300);
            }
        });

        initializeData();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (progres == mPertanyaanData.size()) {
                    startActivity(new Intent(KuisionerActivity.this, HasilActivity.class));
                    finish();
                } else {
                    for (int i = 0; i < mJumlah.size(); i++) {
                        if (mJumlah.get(i) == 0) {
                            mRecyclerView.smoothScrollToPosition(i);
                            break;
                        }
                    }
                }
            }
        });

        mRecyclerView.setItemViewCacheSize(mPertanyaanData.size());
    }

    private void initializeData() {
        String[] pertanyaanList = getResources().getStringArray(R.array.pertanyaan);
        mPertanyaanData.clear();


        for (int i = 0; i < pertanyaanList.length; i++) {
            mPertanyaanData.add(new Pertanyaan(pertanyaanList[i]));
        }

        mProgressBar.setMax(mPertanyaanData.size());
        mTerjawab.setText("Terjawab " + 0 + " dari " + mPertanyaanData.size());

        mJumlah = new ArrayList<>(Collections.nCopies(mPertanyaanData.size(), 0));
        mAdapter.notifyDataSetChanged();

    }

}

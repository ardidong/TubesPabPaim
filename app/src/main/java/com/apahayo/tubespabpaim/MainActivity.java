package com.apahayo.tubespabpaim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.apahayo.tubespabpaim.Model.Pertanyaan;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Pertanyaan> mPertanyaanData;
    private ArrayList<Integer> mJumlah;
    private PertanyaanAdapter mAdapter;
    private ProgressBar mProgressBar;
    private TextView mTerjawab;
    private int progres;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTerjawab = findViewById(R.id.tv_terjawab);
        mProgressBar = findViewById(R.id.pb_kuisioner);
        mRecyclerView = findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        PagerSnapHelper helper = new PagerSnapHelper() ;
        helper.attachToRecyclerView(mRecyclerView);

        mPertanyaanData = new ArrayList<>();
        mAdapter  = new PertanyaanAdapter(mPertanyaanData, this);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new PertanyaanAdapter.OnItemClickListener() {
            @Override
            public void onChoiceClick(final int position) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(mJumlah.get(position)==0){
                            mJumlah.set(position,1);
                        }

                        progres = Collections.frequency(mJumlah,1);
                        mProgressBar.setProgress(progres);
                        mTerjawab.setText("Terjawab "+progres+" dari "+mPertanyaanData.size());

                        if(position<mJumlah.size()-1){
                            if(mJumlah.get(position+1)==0 ){
                                mRecyclerView.smoothScrollToPosition(position+1);
                            }
                        }
                    }
                }, 300);
            }
        });

        initializeData();
    }

    private void initializeData() {
        String[] pertanyaanList = getResources().getStringArray(R.array.pertanyaan);
        mPertanyaanData.clear();


        for (int i=0;i<pertanyaanList.length;i++){
            mPertanyaanData.add(new Pertanyaan(pertanyaanList[i]));
        }

        mProgressBar.setMax(mPertanyaanData.size());
        mTerjawab.setText("Terjawab "+0+" dari "+mPertanyaanData.size());

        mJumlah = new ArrayList<Integer>(Collections.nCopies(mPertanyaanData.size(),0));
        mAdapter.notifyDataSetChanged();
    }
}

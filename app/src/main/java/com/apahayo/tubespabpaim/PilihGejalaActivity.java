package com.apahayo.tubespabpaim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.apahayo.tubespabpaim.Adapter.AktifitasAdapter;
import com.apahayo.tubespabpaim.Adapter.GejalaAdapter;
import com.apahayo.tubespabpaim.Adapter.PertanyaanAdapter;
import com.apahayo.tubespabpaim.Model.Gejala;
import com.apahayo.tubespabpaim.Model.Mood;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;

public class PilihGejalaActivity extends AppCompatActivity {

    public static final String HASIL_GEJALA = "HASIL_GEJALA";
    private ArrayList<Gejala> listGejala;
    private ArrayList<Boolean> mPilihan;
    private ArrayList<String> hasilGejala;
    RecyclerView mRecyclerView;
    GejalaAdapter listAdapter;
    private TextView titleGejala;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_gejala);

        mRecyclerView = findViewById(R.id.gejalaRV);
        titleGejala = findViewById(R.id.titleGejala);
        submit = findViewById(R.id.btn_submit_gejala);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prosesGejala();
            }
        });

        listGejala = new ArrayList<>();
        listAdapter = new GejalaAdapter(PilihGejalaActivity.this, listGejala);
        mRecyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PilihGejalaActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);


        listAdapter.setCustomItemClickListener(new GejalaAdapter.CustomItemClickListener() {
            @Override
            public void onChoiceClick(int position, boolean answer) {
                if(answer){
                    mPilihan.set(position,answer);
                }else {
                    mPilihan.set(position,answer);
                }

                int x = Collections.frequency(mPilihan, true);
                Log.d("__DEBUGJumlah",Integer.toString(x));

            }
        });

        initialize();
        mRecyclerView.setItemViewCacheSize(listGejala.size());
    }

    private void initialize() {
        String[] gejala = getResources().getStringArray(R.array.gejala);
        listGejala.clear();

        for (String s : gejala) {
            Gejala mGejala = new Gejala();
            mGejala.setPertanyaan(s);
            listGejala.add(mGejala);
        }

        mPilihan = new ArrayList<>(Collections.nCopies(20, false));
        listAdapter.notifyDataSetChanged();
    }

    public void prosesGejala(){
        hasilGejala = new ArrayList<>();
        String hasil = "";
        boolean kena = false;

        if(mPilihan.get(0) && mPilihan.get(2) && mPilihan.get(7)){
            //C01
            hasilGejala.add("C01");
            kena = true;
        }
        if(mPilihan.get(1) && mPilihan.get(6) && mPilihan.get(18)){
            //C02
            hasilGejala.add("C02");
            kena = true;
        }
        if(mPilihan.get(3) && mPilihan.get(5)){
            //C03
            hasilGejala.add("C03");
            kena = true;
        }
        if(mPilihan.get(8) && mPilihan.get(9) && mPilihan.get(4)){
            //C04
            hasilGejala.add("C04");
            kena = true;
        }
        if(mPilihan.get(10) && mPilihan.get(12) && mPilihan.get(17) && mPilihan.get(19)){
            //C05
            hasilGejala.add("C05");
            kena = true;
        }
        if(mPilihan.get(15) && mPilihan.get(16) && mPilihan.get(14)){
            //C06
            hasilGejala.add("C06");
            kena = true;
        }
        if(mPilihan.get(13) && mPilihan.get(11)){
            //C02
            hasilGejala.add("C07");
            kena = true;
        }

        int frequency = Collections.frequency(mPilihan, true);
        if(frequency<=5 && kena == false){
            hasilGejala.add("normal");
        }

        unggahDiagnosis();

        Intent intent = new Intent(this, SaranActivity.class);
        intent.putStringArrayListExtra(HASIL_GEJALA,hasilGejala);
        startActivity(intent);
        finish();


        Log.d("___DEBUGHasilDiagnosis",hasil);
    }

    public void unggahDiagnosis(){
        GoogleSignInAccount acct;
        String uid;
        acct = GoogleSignIn.getLastSignedInAccount(PilihGejalaActivity.this);

        if (acct != null) {
            uid = acct.getId();
        } else {
            uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

        DatabaseReference aktifitasDB = FirebaseDatabase.getInstance().getReference().child("solusi");
        aktifitasDB.child(uid).removeValue();
        for(String saran : hasilGejala){
            aktifitasDB.child(uid).child(saran).setValue(true);
        }
    }
}

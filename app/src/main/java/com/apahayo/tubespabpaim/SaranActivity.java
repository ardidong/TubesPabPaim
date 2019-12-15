package com.apahayo.tubespabpaim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.apahayo.tubespabpaim.Adapter.AktifitasAdapter;
import com.apahayo.tubespabpaim.Adapter.SaranAdapter;
import com.apahayo.tubespabpaim.Model.Mood;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class SaranActivity extends AppCompatActivity {

    private GoogleSignInAccount acct;
    private ArrayList<Mood> lists;
    private String uid;
    private Button kembaliBtn;
    private Button saranBtn;
    private SaranAdapter saranAdapter;
    private DatabaseReference dbRef;
    public ArrayList<String> hasilGejala;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saran);
        lists = new ArrayList<>();
        //hasilGejala = getIntent().getStringArrayListExtra(PilihGejalaActivity.HASIL_GEJALA);

        mRecyclerView = findViewById(R.id.saran_RV);

        kembaliBtn = findViewById(R.id.kembali_btn);
        kembaliBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SaranActivity.this,NavBotActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        hasilGejala = new ArrayList<>();


        getSarans();

        //mRecyclerView.setItemViewCacheSize(hasilGejala.size());

    }

    public void getSarans(){
        GoogleSignInAccount acct;
        String uid;
        acct = GoogleSignIn.getLastSignedInAccount(SaranActivity.this);

        if (acct != null) {
            uid = acct.getId();
        } else {
            uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

        DatabaseReference aktifitasDB = FirebaseDatabase.getInstance().getReference().child("solusi");
        aktifitasDB.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> sarans = new ArrayList<>();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    String saran = ds.getKey();
                    sarans.add(saran);
                }

                saranAdapter = new SaranAdapter(SaranActivity.this,sarans);
                mRecyclerView.setAdapter(saranAdapter);
                mRecyclerView.setItemViewCacheSize(sarans.size());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       // saranAdapter.notifyDataSetChanged();
    }

    public void check(){
        acct = GoogleSignIn.getLastSignedInAccount(SaranActivity.this);

        if (acct != null) {
            uid = acct.getId();
        } else if (acct == null) {
            uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

        Calendar to = Calendar.getInstance();
        Calendar from = Calendar.getInstance();
        to.add(Calendar.DAY_OF_YEAR, -3);

        SimpleDateFormat formatting = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String ends = formatting.format(from.getTime()) + " 23:59:59";
        String starts = formatting.format(to.getTime()) ;


        DatabaseReference aktifitasDB = FirebaseDatabase.getInstance().getReference().child("mood");
        aktifitasDB.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot moodDataSnapshot : dataSnapshot.getChildren()) {
                    Mood mood = moodDataSnapshot.getValue(Mood.class);
                    mood.setKey(moodDataSnapshot.getKey());
                    Log.d("__DEBUG",mood.getJudul());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        double rerata = 0;
        for (int i = 0; i < lists.size(); i++) {
            rerata += lists.get(i).getValue();
        }

        rerata = rerata / lists.size();

        if (rerata < 2) {
            Intent intent = new Intent(this, PilihGejalaActivity.class);
            startActivity(intent);
        }
    }

}

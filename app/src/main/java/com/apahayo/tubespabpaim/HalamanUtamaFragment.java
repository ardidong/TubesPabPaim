package com.apahayo.tubespabpaim;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apahayo.tubespabpaim.Model.Mood;
import com.apahayo.tubespabpaim.Model.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HalamanUtamaFragment extends Fragment {

    private ArrayList<Mood> moodList;
    private String uid,uidGoogle;
    private GoogleSignInAccount acct;
    private AktifitasAdapter aktifitasAdapter;
    private DatabaseReference database;
    private RecyclerView recyclerView;
    private TextView name;


    public HalamanUtamaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_halaman_utama, container, false);
        name = view.findViewById(R.id.namaUserTV);
        database = FirebaseDatabase.getInstance().getReference();



        acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (acct != null) {
            final String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            uidGoogle = personId;


            String uid2 = FirebaseAuth.getInstance().getCurrentUser().getUid();
            DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("users").child(uid2);
            db.child("nama").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String nama = dataSnapshot.getValue(String.class);
                    name.setText(personName);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }else if (acct == null){
            uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
            db.child("nama").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String nama = dataSnapshot.getValue(String.class);
                    name.setText(nama);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


            RecyclerView mRecyclerView = view.findViewById(R.id.timelineKegiatan);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(layoutManager);


            moodList = new ArrayList<>();
            aktifitasAdapter = new AktifitasAdapter(getActivity(), moodList);
            mRecyclerView.setAdapter(aktifitasAdapter);
            mRecyclerView.setItemViewCacheSize(moodList.size());


            initialize();

            return view;


    }

    public void initialize(){
        //String[] mood = getResources().getStringArray(R.array.kegiatan);
        moodList.clear();
        if (acct != null) {
            DatabaseReference aktifitasDB2 = FirebaseDatabase.getInstance().getReference().child("mood");
            aktifitasDB2.child(uidGoogle).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot moodDataSnapshot : dataSnapshot.getChildren()) {
                        Mood mood = moodDataSnapshot.getValue(Mood.class);
                        mood.setKey(moodDataSnapshot.getKey());
                        moodList.add(mood);

                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });




        }else if (acct == null){
            DatabaseReference aktifitasDB = FirebaseDatabase.getInstance().getReference().child("mood");
            aktifitasDB.child(uid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot moodDataSnapshot : dataSnapshot.getChildren()) {
                        Mood mood = moodDataSnapshot.getValue(Mood.class);
                        mood.setKey(moodDataSnapshot.getKey());
                        moodList.add(mood);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }

        aktifitasAdapter.notifyDataSetChanged();



    }


}

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HalamanUtamaFragment extends Fragment {

    private ArrayList<Mood> moodList;
    private AktifitasAdapter aktifitasAdapter;
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

        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
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



        RecyclerView mRecyclerView = view.findViewById(R.id.timelineKegiatan);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);


        moodList = new ArrayList<>();
        aktifitasAdapter = new AktifitasAdapter(getActivity(),moodList);
        mRecyclerView.setAdapter(aktifitasAdapter);
        mRecyclerView.setItemViewCacheSize(moodList.size());



        initialize();

        return view;

    }

    public void initialize(){
        String[] mood = getResources().getStringArray(R.array.kegiatan);
        moodList.clear();

        for (int i = 0; i < mood.length; i++) {
            moodList.add(new Mood(mood[i]));
        }
        moodList.get(0).setValue(1);
        moodList.get(1).setValue(2);
        moodList.get(2).setValue(3);
        moodList.get(3).setValue(4);
        moodList.get(4).setValue(5);

        aktifitasAdapter.notifyDataSetChanged();

    }


}

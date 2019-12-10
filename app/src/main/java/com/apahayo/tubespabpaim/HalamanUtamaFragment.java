package com.apahayo.tubespabpaim;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apahayo.tubespabpaim.Model.Mood;

import java.util.ArrayList;


public class HalamanUtamaFragment extends Fragment {

    private ArrayList<Mood> moodList;
    private AktifitasAdapter aktifitasAdapter;
    private RecyclerView recyclerView;

    public HalamanUtamaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_halaman_utama, container, false);

        RecyclerView mRecyclerView = view.findViewById(R.id.timelineKegiatan);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);


        moodList = new ArrayList<>();
        aktifitasAdapter = new AktifitasAdapter(getActivity(),moodList);
        mRecyclerView.setAdapter(aktifitasAdapter);



        initialize();

        return view;

    }

    public void initialize(){
        String[] mood = getResources().getStringArray(R.array.kegiatan);

        for (int i = 0; i < mood.length; i++) {
            moodList.add(new Mood(mood[i]));
        }

        aktifitasAdapter.notifyDataSetChanged();

    }


}

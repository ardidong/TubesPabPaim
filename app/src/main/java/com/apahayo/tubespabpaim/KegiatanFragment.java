package com.apahayo.tubespabpaim;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apahayo.tubespabpaim.Model.Mood;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class KegiatanFragment extends Fragment {


    public KegiatanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_aktifitas, container, false);
        RecyclerView mRecyclerView = view.findViewById(R.id.listAktifitas);


        ArrayList<Mood> mood = new ArrayList<>();
        AktifitasAdapter listAdapter = new AktifitasAdapter(getActivity(),mood);
        mRecyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        return view;

    }

}

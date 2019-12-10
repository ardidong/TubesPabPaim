package com.apahayo.tubespabpaim;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.apahayo.tubespabpaim.Model.Mood;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilUserFragment extends Fragment {

    private DatabaseReference dbRefrence;
    private int value = 0;
    private Mood currentMood;
    private ImageView emotImageView;
    private Button btnLogout;
    FirebaseAuth mAuth;
    FirebaseUser user;



    public ProfilUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profil_user, container, false);




        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();


        btnLogout = view.findViewById(R.id.logoutBtn);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().finish();

            }
        });
        return view;
    }

}

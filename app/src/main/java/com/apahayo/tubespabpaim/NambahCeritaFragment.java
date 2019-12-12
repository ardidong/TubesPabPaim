package com.apahayo.tubespabpaim;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.apahayo.tubespabpaim.Model.Mood;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class NambahCeritaFragment extends Fragment {
    private Button btnLogout;
    private EditText namaKegiatan;
    private RadioGroup emotGroup;
    private int value = 0;
    private EditText detailKegiatan;
    private DatabaseReference dbRefrence;
    private ImageView emotImageView;
    FirebaseAuth mAuth;
    FirebaseUser user;


    public NambahCeritaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_nambah_cerita, container, false);

        namaKegiatan = view.findViewById(R.id.namakegiatanET);
        detailKegiatan = view.findViewById(R.id.detailkegiatanET);
        emotImageView = view.findViewById(R.id.emotImageView);
        emotGroup = view.findViewById(R.id.emotGroup2);

        emotGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.sedih1:
                        value = 1;
                        break;
                    case R.id.sedih2:
                        value = 2;
                        break;
                    case R.id.netral1:
                        value = 3;
                        break;
                    case R.id.senang1:
                        value = 4;
                        break;
                    case R.id.senang2:
                        value = 5;
                        break;
                }
            }
        });


        Button ceritaBt = view.findViewById(R.id.ceritaBtn);
        ceritaBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cekInput()) {
                    String nama = namaKegiatan.getText().toString();
                    String detail = detailKegiatan.getText().toString();
                    Intent intent;
                    Date waktu = new Date();

                    Mood mood = new Mood(waktu.toString(), nama, value, detail);

                    if (value == 4 || value == 5 || value==3) {
                        intent = new Intent(getContext(), TambahQuoteSaranActivity.class);
                    } else {
                        intent = new Intent(getContext(), SaranActivity.class);
                    }
                    intent.putExtra("data", mood);
                    startActivity(intent);
                } else {
                    showAlert();
                }
            }
        });


        return view;
    }

    private boolean cekInput() {
        return namaKegiatan.length() != 0 && detailKegiatan.length() != 0 && value != 0;
    }

    public void showAlert(){
        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(getContext());
        myAlertBuilder.setMessage("Pastikan semua telah diisi ya!");

        myAlertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

            }
        });

        myAlertBuilder.show();
    }

}

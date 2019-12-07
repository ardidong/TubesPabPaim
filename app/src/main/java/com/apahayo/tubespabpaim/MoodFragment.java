package com.apahayo.tubespabpaim;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.apahayo.tubespabpaim.Model.Mood;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoodFragment extends Fragment {

    private int value = 0;
    private Mood currentMood;
    private ImageView emotImageView;
    private Button btnLogout;
    FirebaseAuth mAuth;
    FirebaseUser user;


    public MoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mood, container, false);
        final Button btnUpdate = view.findViewById(R.id.btnUpdate);
        emotImageView = view.findViewById(R.id.emotImageView);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value = 0;
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.mood_dialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                Button btnUpdateEmotion = dialog.findViewById(R.id.btn_update_emotion);
                RadioGroup emotGroup = dialog.findViewById(R.id.emotGroup);

                emotGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int id) {
                        switch (id) {
                            case R.id.sedihSekali:
                                value = 1;
                                break;
                            case R.id.sedih:
                                value = 2;
                                break;
                            case R.id.netral:
                                value = 3;
                                break;
                            case R.id.senang:
                                value = 4;
                                break;
                            case R.id.senangSekali:
                                value = 5;
                                break;
                        }

                    }
                });

                btnUpdateEmotion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (value != 0) {
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                            Date date = new Date();
                            Log.d("__DEBUG_DATE", formatter.format(date));
                            Log.d("__DEBUG_DATE", timeFormat.format(date));
                            Log.d("__DEBUG_DATE", date.toString());

                            Mood mood = new Mood(date, value);
                            updateMood(mood);
                        } else {
                            Log.d("__DEBUG_DATE", "No Input");
                        }
                        dialog.dismiss();
                    }
                });


            }
        });


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

    private void updateMood(Mood mood) {
        currentMood = mood;
        switch (currentMood.getValue()) {
            case 1:
                emotImageView.setImageResource(R.drawable.ic_clicked_sedih_sekali);
                break;
            case 2:
                emotImageView.setImageResource(R.drawable.ic_clicked_sedih);
                break;
            case 3:
                emotImageView.setImageResource(R.drawable.ic_clicked_netral);
                break;
            case 4:
                emotImageView.setImageResource(R.drawable.ic_clicked_senang);
                break;
            case 5:
                emotImageView.setImageResource(R.drawable.ic_clicked_senang_sekali);
        }
    }

}

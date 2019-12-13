package com.apahayo.tubespabpaim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.apahayo.tubespabpaim.Model.Mood;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditMoodActivity extends AppCompatActivity {
    private EditText judul,Kegiatan;
    private Button edit;
    private RadioGroup emot;
    private TextView tanggal;
    private Mood mood;
    private int value;
    private GoogleSignInAccount acct;
    private AktifitasAdapter aktifitasAdapter;
    private DatabaseReference database;
    private RadioButton sedihsekali,sedih,netral,senang,senangsekali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_mood);

        judul = findViewById(R.id.namakegiatanET2);
        Kegiatan = findViewById(R.id.detailkegiatanET2);
        tanggal = findViewById(R.id.tanggalTV);
        emot = findViewById(R.id.emotGroup3);
        sedihsekali = findViewById(R.id.sedihh1);
        sedih = findViewById(R.id.sedihh2);
        netral = findViewById(R.id.netrall1);
        senang = findViewById(R.id.senangg1);
        senangsekali = findViewById(R.id.senangg2);
        edit = findViewById(R.id.editBtn);
        acct = GoogleSignIn.getLastSignedInAccount(EditMoodActivity.this);
        database = FirebaseDatabase.getInstance().getReference();




        mood = (Mood) getIntent().getSerializableExtra("MOOD_DATA");

        value = mood.getValue();

        judul.setText(mood.getJudul());
        Kegiatan.setText(mood.getDeskripsi());
        tanggal.setText(mood.getWaktu());

        switch (value) {
            case 1:
                sedihsekali.setChecked(true);
                break;
            case 2:
                sedih.setChecked(true);
                break;
            case 3:
                netral.setChecked(true);
                break;
            case 4:
                senang.setChecked(true);
                break;
            case 5:
                senangsekali.setChecked(true);
                break;



        }


        emot.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.sedihh1:
                        value = 1;
                        break;
                    case R.id.sedihh2:
                        value = 2;
                        break;
                    case R.id.netrall1:
                        value = 3;
                        break;
                    case R.id.senangg1:
                        value = 4;
                        break;
                    case R.id.senangg2:
                        value = 5;
                        break;
                }
            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(EditMoodActivity.this);
                myAlertBuilder.setMessage("Apakah kamu yakin ingin menghapus?");

                myAlertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        editKegiatan();

                    }

                });

                myAlertBuilder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                myAlertBuilder.show();
            }
        });
    }

    private void editKegiatan(){

        if (acct != null) {

            String personId = acct.getId();

            String tanggal2 = tanggal.getText().toString();
            String judul2 = judul.getText().toString();
            String kegiatan2 = Kegiatan.getText().toString();

            mood.setJudul(judul.getText().toString());
            mood.setDeskripsi(Kegiatan.getText().toString());
            mood.setValue(value);

            Log.d("__DEBUGKEY",mood.getKey());
            database.child("mood").child(personId).child(mood.getKey()).setValue(mood).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(EditMoodActivity.this);
                    myAlertBuilder.setMessage("Data berhasil dihapus !");

                    myAlertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {

                            Intent intent= new Intent(EditMoodActivity.this,NavBotActivity.class);
                            startActivity(intent);
                            finish();

                        }
                    });

                    myAlertBuilder.show();

                }
            });
        }else {

            String uid2 = FirebaseAuth.getInstance().getCurrentUser().getUid();
            mood.setJudul(judul.getText().toString());
            mood.setDeskripsi(Kegiatan.getText().toString());
            mood.setValue(value);
            database.child("mood").child(uid2).child(mood.getKey()).setValue(mood).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(EditMoodActivity.this);
                    myAlertBuilder.setMessage("Data berhasil dihapus !");

                    myAlertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {

                            Intent intent= new Intent(EditMoodActivity.this,NavBotActivity.class);
                            startActivity(intent);

                        }
                    });

                    myAlertBuilder.show();
                }
            });
        }
    }


}

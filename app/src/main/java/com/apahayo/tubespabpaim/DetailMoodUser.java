package com.apahayo.tubespabpaim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.apahayo.tubespabpaim.Model.Mood;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailMoodUser extends AppCompatActivity {

    private ArrayList<Mood> moodList;
    private TextView tanggal,jam,kegiatan,detail,quote;
    private String uidGoogle;
    private ImageView emot;
    private Mood mood;
    private Button buttonDelete;
    private GoogleSignInAccount acct;
    private AktifitasAdapter aktifitasAdapter;
    private DatabaseReference database;
    private RecyclerView recyclerView;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mood_user);

        tanggal = findViewById(R.id.tanggalET);
        jam = findViewById(R.id.jamET);
        kegiatan = findViewById(R.id.judulKegiatanTV);
        detail = findViewById(R.id.detailKegiatanTV);
        quote = findViewById(R.id.quoteTV);
        emot = findViewById(R.id.emotIV);
        database = FirebaseDatabase.getInstance().getReference();
        buttonDelete = findViewById(R.id.deleteBtn);



        mood = (Mood) getIntent().getSerializableExtra("MOOD_DATA");

        tanggal.setText(mood.getWaktu());
        kegiatan.setText(mood.getJudul());
        detail.setText(mood.getDeskripsi());
        quote.setText(mood.getQuote());
        acct = GoogleSignIn.getLastSignedInAccount(DetailMoodUser.this);

        switch (mood.getValue()) {
            case 1:
                emot.setImageResource(R.drawable.ic_mood_sedihsekali);
                break;
            case 2:
                emot.setImageResource(R.drawable.ic_mood_sedih);
                break;
            case 3:
                emot.setImageResource(R.drawable.ic_mood_netral);
                break;
            case 4:
                emot.setImageResource(R.drawable.ic_mood_senang);
                break;
            case 5:
                emot.setImageResource(R.drawable.ic_mood_senangsekali);
                break;
        }

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(DetailMoodUser.this);
                myAlertBuilder.setMessage("Apakah kamu yakin ingin menghapus?");

                myAlertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        deleteKegiatan();

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

    public void deleteKegiatan() {




        if (acct != null) {

            String personId = acct.getId();
            Log.d("__DEBUGKEY",mood.getKey());
            database.child("mood").child(personId).child(mood.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(DetailMoodUser.this);
                    myAlertBuilder.setMessage("Data berhasil dihapus !");

                    myAlertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {

                            Intent intent= new Intent(DetailMoodUser.this,NavBotActivity.class);
                            startActivity(intent);
                            finish();

                        }
                    });

                    myAlertBuilder.show();

                }
            });
        }else {

            String uid2 = FirebaseAuth.getInstance().getCurrentUser().getUid();
            database.child("mood").child(uid2).child(mood.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(DetailMoodUser.this);
                    myAlertBuilder.setMessage("Data berhasil dihapus !");

                    myAlertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {

                            Intent intent= new Intent(DetailMoodUser.this,NavBotActivity.class);
                            startActivity(intent);

                        }
                    });

                    myAlertBuilder.show();
                }
            });
        }
    }
}

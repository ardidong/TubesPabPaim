package com.apahayo.tubespabpaim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.Random;

public class TampilkanQuoteActivity extends AppCompatActivity {

    private TextView tampilQuoteTV;
    private TextView milikmuTV;
    private Button okQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilkan_quote);

        tampilQuoteTV = findViewById(R.id.showQuote);
        milikmuTV = findViewById(R.id.milikmu_tv);
        okQuote = findViewById(R.id.btn_ok_tampil_quote);

        okQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TampilkanQuoteActivity.this,NavBotActivity.class);
                startActivity(intent);
                finish();
            }
        });


        GoogleSignInAccount acct;
        String uid;
        acct = GoogleSignIn.getLastSignedInAccount(TampilkanQuoteActivity.this);

        if (acct != null) {
            uid = acct.getId();
        } else {
            uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

        DatabaseReference aktifitasDB = FirebaseDatabase.getInstance().getReference().child("mood");
        aktifitasDB.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Mood> moodList = new ArrayList<>();
                for (DataSnapshot moodDataSnapshot : dataSnapshot.getChildren()) {
                    Mood mood = moodDataSnapshot.getValue(Mood.class);
                    mood.setKey(moodDataSnapshot.getKey());
                    moodList.add(mood);
                }

                Random rand = new Random();

                if (moodList.size() != 0) {
                    int x = rand.nextInt(2) + 1;
                    int n = rand.nextInt(moodList.size());
                    String empty = "";

                    if (x == 1 &&  moodList.get(n).getValue()==3 && !moodList.get(n).getQuote().isEmpty()) {
                        tampilQuoteTV.setText(moodList.get(n).getQuote());
                        milikmuTV.setText("Wah kebetulan quote diatas buatanmu loh. Tetap semangat ya dan jangan lupa untuk terus menciptakan quote saat kamu senang!");
                    } else {
                        String quoteList[] = getResources().getStringArray(R.array.quotes);
                        int y = rand.nextInt(quoteList.length);
                        tampilQuoteTV.setText(quoteList[y]);
                    }

                } else {
                    String quoteList[] = getResources().getStringArray(R.array.quotes);
                    int y = rand.nextInt(quoteList.length);
                    tampilQuoteTV.setText(quoteList[y]);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

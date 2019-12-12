package com.apahayo.tubespabpaim;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.apahayo.tubespabpaim.Model.Mood;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahQuoteSaranActivity extends AppCompatActivity {

    private TextView kataTV;
    private EditText quoteET;
    private Button submitBtn;
    private Mood mood;
    private DatabaseReference dbRefrence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_quote_saran);

        kataTV = findViewById(R.id.kata_quote_tv);
        quoteET = findViewById(R.id.quote_ET);
        submitBtn = findViewById(R.id.btn_submit);

        mood = (Mood) getIntent().getSerializableExtra("data");

        if (mood.getValue() == 4) {
            kataTV.setText(getResources().getString(R.string.kataSenang));
        } else if (mood.getValue() == 5) {
            kataTV.setText(getResources().getString(R.string.kataSenangSekali));
        }

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(
                        quoteET.getWindowToken(), 0);

                if(quoteET.length()!=0){
                    String quote = quoteET.getText().toString();
                    mood.setQuote(quote);
                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    dbRefrence = FirebaseDatabase.getInstance().getReference().child("mood");
                    dbRefrence.child(uid).push().setValue(mood, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                            if (databaseError != null) {
                                Snackbar.make(findViewById(R.id.btn_submit), "Data Gagal ditambahkan", Snackbar.LENGTH_LONG).show();
                            } else {
                                startActivity(new Intent(TambahQuoteSaranActivity.this, NavBotActivity.class));
                                finish();
                            }
                        }
                    });
                }else{
                    showAlert();
                }


            }
        });


    }

    public void showAlert(){
        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(this);
        myAlertBuilder.setMessage("Pastikan kolom masukan diisi ya!");

        myAlertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

            }
        });

        myAlertBuilder.show();
    }

}

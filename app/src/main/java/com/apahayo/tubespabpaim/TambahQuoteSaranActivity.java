package com.apahayo.tubespabpaim;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.apahayo.tubespabpaim.Model.Mood;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class TambahQuoteSaranActivity extends AppCompatActivity {

    private TextView kataTV;
    private EditText quoteET;
    private GoogleSignInAccount acct;
    private NotificationManager mNotifyManager;
    private PendingIntent contentIntent;
    private Button submitBtn;
    private static final int NOTIFICATION_ID = 0;
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
        createNotificationChannel();

        if (mood.getValue() == 3){
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

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,8);
                calendar.set(Calendar.MINUTE,0);
                calendar.set(Calendar.SECOND,0);


                Intent intent = new Intent(TambahQuoteSaranActivity.this, ReceiverSenang.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        TambahQuoteSaranActivity.this,0,intent,0);
                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);








                if (quoteET.length() != 0) {
                    String quote = quoteET.getText().toString();
                    mood.setQuote(quote);

                    acct = GoogleSignIn.getLastSignedInAccount(TambahQuoteSaranActivity.this);
                    if (acct != null) {
                        String personName = acct.getDisplayName();
                        String personEmail = acct.getEmail();
                        String personId = acct.getId();
                        Uri personPhoto = acct.getPhotoUrl();
                        dbRefrence = FirebaseDatabase.getInstance().getReference().child("mood");
                        dbRefrence.child(personId).push().setValue(mood, new DatabaseReference.CompletionListener() {
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

                    } else if (acct == null) {
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
                    }
                } else {
                    showAlert();
                }


            }
        });




    }

    public void showAlert() {
        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(this);
        myAlertBuilder.setMessage("Pastikan kolom masukan diisi ya!");

        myAlertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

            }
        });

        myAlertBuilder.show();
    }
    public void createNotificationChannel() {
        mNotifyManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("channel1",
                    "Mascot Notification", NotificationManager
                    .IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Mascot");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

    private NotificationCompat.Builder getNotificationBuilder() {
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, "channel1");
        if (mood.getValue() == 3) {

            notifyBuilder.setContentTitle("Wah sepertinya kamu akhir akhir ini senang sekali ya, bagaimana dengan hari ini?")
                    .setSmallIcon(R.drawable.icon_launcher)
                    .setContentIntent(contentIntent);
        }
        return notifyBuilder;
    }

}

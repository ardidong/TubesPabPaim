package com.apahayo.tubespabpaim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.apahayo.tubespabpaim.Adapter.SaranAdapter;
import com.apahayo.tubespabpaim.Adapter.SolusiAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class HasilActivity extends AppCompatActivity {

    private String kode;
    private RecyclerView mRecyclerView;
    private ImageView gambarsolusi;
    private SolusiAdapter sAdapter;
    private ArrayList<String> listSolusi;
    private NotificationManager mNotifyManager;
    private TextView penjelasan;
    private Button saranOK,saranBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);
        saranOK = findViewById(R.id.btn_ok_saran);
        gambarsolusi = findViewById(R.id.solusi_img);
        penjelasan = findViewById(R.id.penjalasan_solusi_tv);
        createNotificationChannel();

        saranOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,7);
                calendar.set(Calendar.MINUTE,0);
                calendar.set(Calendar.SECOND,0);


                Intent intent2 = new Intent(HasilActivity.this, ReceiverSedih.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        HasilActivity.this,1,intent2,PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);




                Intent intent = new Intent(HasilActivity.this,SaranActivity.class);
                startActivity(intent);
            }
        });

        kode = getIntent().getStringExtra("KODE");

        mRecyclerView = findViewById(R.id.solusi_rv);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        listSolusi = new ArrayList<>();
        sAdapter = new SolusiAdapter(HasilActivity.this, listSolusi);
        mRecyclerView.setAdapter(sAdapter);

        initialize();

        mRecyclerView.setItemViewCacheSize(listSolusi.size());
    }

    public void initialize() {
        String[] solusiList;


        switch (kode) {
            case "C01":
                gambarsolusi.setImageResource(R.drawable.ragupadadirisendiri);
                penjelasan.setText(getResources().getString(R.string.penjelasan_C01));
                solusiList = getResources().getStringArray(R.array.solusi_C01);
                break;
            case "C02":
                gambarsolusi.setImageResource(R.drawable.mencemaskanyangbelumterjadi);
                penjelasan.setText(getResources().getString(R.string.penjelasan_C02));
                solusiList = getResources().getStringArray(R.array.solusi_C02);
                break;
            case "C03":
                gambarsolusi.setImageResource(R.drawable.percayadiri);
                penjelasan.setText(getResources().getString(R.string.penjelasan_C03));
                solusiList = getResources().getStringArray(R.array.solusi_C03);
                break;
            case "C04":
                gambarsolusi.setImageResource(R.drawable.kecewadirisendiri);
                penjelasan.setText(getResources().getString(R.string.penjelasan_C04));
                solusiList = getResources().getStringArray(R.array.solusi_C04);
                break;
            case "C05":
                gambarsolusi.setImageResource(R.drawable.psikis);
                penjelasan.setText(getResources().getString(R.string.penjelasan_C05));
                solusiList = getResources().getStringArray(R.array.solusi_C05);
                break;
            case "C06":
                gambarsolusi.setImageResource(R.drawable.depresi);
                penjelasan.setText(getResources().getString(R.string.penjelasan_C06));
                solusiList = getResources().getStringArray(R.array.solusi_C06);
                break;
            case "C07":
                gambarsolusi.setImageResource(R.drawable.sulitsenang);
                penjelasan.setText(getResources().getString(R.string.penjelasan_C07));
                solusiList = getResources().getStringArray(R.array.solusi_C07);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + kode);
        }

        listSolusi.addAll(Arrays.asList(solusiList));

        sAdapter.notifyDataSetChanged();
    }

    public void createNotificationChannel() {
        mNotifyManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("channel2",
                    "Mascot Notification", NotificationManager
                    .IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Mascot");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

}

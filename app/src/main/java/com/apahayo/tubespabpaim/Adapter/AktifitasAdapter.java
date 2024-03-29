package com.apahayo.tubespabpaim.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.apahayo.tubespabpaim.DetailMoodUser;
import com.apahayo.tubespabpaim.Model.Aktifitas;
import com.apahayo.tubespabpaim.Model.Mood;
import com.apahayo.tubespabpaim.R;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AktifitasAdapter extends RecyclerView.Adapter {
    private ArrayList<Mood> moodList;
    private final Activity activity;

    public AktifitasAdapter(Activity activity, ArrayList<Mood> moodList) {
        this.moodList = moodList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.activity_isi_list
                , parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Mood currentMood = moodList.get(getItemCount() - position - 1);
        ((ListViewHolder) holder).bindView(currentMood);

        try {
            ((ListViewHolder) holder).setTimeDiff(currentMood);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ((ListViewHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, DetailMoodUser.class);
                intent.putExtra("MOOD_DATA",currentMood);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moodList.size();
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView kegiatanTV;
        private TextView waktuTV;
        private ImageView moodImage;
        private CardView cardView;

        public ListViewHolder(View itemView) {
            super(itemView);
            kegiatanTV = itemView.findViewById(R.id.kegiatanTV);
            waktuTV = itemView.findViewById(R.id.waktuTV);
            moodImage = itemView.findViewById(R.id.moodImage);
            cardView = itemView.findViewById(R.id.aktifitas_card);
        }

        public void bindView(Mood currentMood) {
            kegiatanTV.setText(currentMood.getJudul());

            switch (currentMood.getValue()) {
                case 1:
                    cardView.setCardBackgroundColor(Color.parseColor("#89ADEC"));
                    break;
                case 2:
                    cardView.setCardBackgroundColor(Color.parseColor("#777777"));
                    break;
                case 3:
                    cardView.setCardBackgroundColor(Color.parseColor("#F38240"));
                    break;
            }

            switch (currentMood.getValue()) {
                case 1:
                    moodImage.setImageResource(R.drawable.ic_mood_sedihsekali);
                    break;
                case 2:
                    moodImage.setImageResource(R.drawable.ic_mood_netral);
                    break;
                case 3:
                    moodImage.setImageResource(R.drawable.ic_mood_senangsekali);
                    break;
            }

        }

        public void setTimeDiff(Mood mood) throws ParseException {
            DateFormat formatter = new SimpleDateFormat("uuuu-MM-dd HH:mm:ss.SSS", Locale.US);
            Date waktu;

            String www = mood.getWaktu();

            waktu = formatter.parse(www);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Date now = formatter.parse(timestamp.toString());
            long diff = now.getTime() - waktu.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);
            Log.d("__DEBUGSatu",diffSeconds+" "+diffMinutes);

            if(diffDays>=1){

                waktuTV.setText(diffDays+" hari yang lalu");
            }else if(diffHours>=1){
                waktuTV.setText(diffHours + " jam yang lalu");

            }else if(diffMinutes>=1){
                waktuTV.setText(diffMinutes+" menit yang lalu");
            }else{
                waktuTV.setText(diffSeconds + " detik yang lalu");
            }


        }

        public void onClick(View view) {

        }

    }
}

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
import com.apahayo.tubespabpaim.HasilActivity;
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

public class SaranAdapter extends RecyclerView.Adapter {
    private ArrayList<String> saranList;
    private final Activity activity;

    public SaranAdapter(Activity activity, ArrayList<String> saranList) {
        this.saranList = saranList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.saran_item
                , parent, false);
        return new SaranViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final String currentSaran= saranList.get(position);
        ((SaranViewHolder) holder).bindView(currentSaran);
        ((SaranViewHolder)holder).saranButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, HasilActivity.class);
                intent.putExtra("KODE",currentSaran);
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return saranList.size();
    }

    private class SaranViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private  TextView saranButton;

        public SaranViewHolder(View itemView) {
            super(itemView);
            saranButton = itemView.findViewById(R.id.button_saran);
        }

        public void bindView(String currentSaran) {

            switch (currentSaran) {
                case "C01":
                    saranButton.setText("Solusi 1");
                    break;
                case "C02":
                    saranButton.setText("Solusi 2");
                    break;
                case "C03":
                    saranButton.setText("Solusi 3");
                    break;
                case "C04":
                    saranButton.setText("Solusi 4");
                    break;
                case "C05":
                    saranButton.setText("Solusi 5");
                    break;
                case "C06":
                    saranButton.setText("Solusi 6");
                    break;
                case "C07":
                    saranButton.setText("Solusi 7");
                    break;
                case "mulai" :
                    saranButton.setText("Saran Untukmu");
                    break;
                default:
                    saranButton.setText("Saran Untukmu");
                    break;
            }

        }

        public void onClick(View view) {

        }

    }
}

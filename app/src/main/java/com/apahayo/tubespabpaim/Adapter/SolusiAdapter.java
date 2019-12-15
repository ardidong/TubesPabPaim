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

public class SolusiAdapter extends RecyclerView.Adapter {
    private ArrayList<String> solusiList;
    private final Activity activity;

    public SolusiAdapter(Activity activity, ArrayList<String> solusiList) {
        this.solusiList = solusiList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.solusi_item
                , parent, false);
        return new SolusiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final String currentSolusi= solusiList.get(position);
        ((SolusiViewHolder) holder).bindView(currentSolusi);
    }

    @Override
    public int getItemCount() {
        return solusiList.size();
    }

    private class SolusiViewHolder extends RecyclerView.ViewHolder  {
        private TextView solusiTV;

        public SolusiViewHolder(View itemView) {
            super(itemView);
            solusiTV = itemView.findViewById(R.id.solusi_tv);

        }

        public void bindView(String currentSolusi) {
            solusiTV.setText(currentSolusi);

        }



        public void onClick(View view) {

        }

    }
}

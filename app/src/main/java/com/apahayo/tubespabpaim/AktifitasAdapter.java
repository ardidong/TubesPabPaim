package com.apahayo.tubespabpaim;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apahayo.tubespabpaim.Model.Aktifitas;
import com.apahayo.tubespabpaim.Model.Mood;

import java.util.ArrayList;

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
        ,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Mood currentMood = moodList.get(position);
        ((ListViewHolder)holder).bindView(currentMood);
    }

    @Override
    public int getItemCount() {
        return Aktifitas.Aktifitas.length;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView kegiatanTV;
        private TextView waktuTV;
        private ImageView moodImage;

        public ListViewHolder(View itemView){
            super(itemView);
            kegiatanTV = itemView.findViewById(R.id.kegiatanTV);
            waktuTV = itemView.findViewById(R.id.waktuTV);
            moodImage = itemView.findViewById(R.id.moodImage);
        }

        public void bindView(Mood currentMood)  {
            kegiatanTV.setText(currentMood.getJudul());
            waktuTV.setText("20menitlalu");
            moodImage.setImageResource(R.drawable.ic_clicked_netral);

        }

        public void onClick(View view){

        }

    }
}

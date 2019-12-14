package com.apahayo.tubespabpaim.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apahayo.tubespabpaim.Model.Gejala;
import com.apahayo.tubespabpaim.R;

import java.util.ArrayList;

public class GejalaAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<Gejala> gejalaList;

    public GejalaAdapter(Context context, ArrayList<Gejala> gejalaList) {
        this.context = context;
        this.gejalaList = gejalaList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_pilih_gejala_list
                , parent, false);
        return new PertanyaanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Gejala currentGejala = gejalaList.get(position);
        ((PertanyaanViewHolder)holder).bindTo(currentGejala);

    }

    @Override
    public int getItemCount() {
        return gejalaList.size();
    }

    private class PertanyaanViewHolder extends RecyclerView.ViewHolder {

        private CheckBox gejalaCB;


        public PertanyaanViewHolder(@NonNull View itemView) {
            super(itemView);
            gejalaCB = itemView.findViewById(R.id.checkbox_gejala);
        }

        public void bindTo(Gejala mGejala){
            gejalaCB.setText(mGejala.getPertanyaan());

        }
    }
}

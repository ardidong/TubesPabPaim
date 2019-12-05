package com.apahayo.tubespabpaim;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apahayo.tubespabpaim.Model.Aktifitas;

public class AktifitasAdapter extends RecyclerView.Adapter {
    private final Activity activity;

    public AktifitasAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_aktifitas_list
        ,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((ListViewHolder)holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return Aktifitas.Aktifitas.length;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private CheckBox mCheckBox;
        private Button mButton;

        public ListViewHolder(View itemView){
            super(itemView);
            mCheckBox = itemView.findViewById(R.id.aktifitasCB);
            mButton = itemView.findViewById(R.id.buttonDetail);
            itemView.setOnClickListener(this);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.startActivity(new Intent(activity,DetailAktifitas.class));

                }
            });

        }

        public void bindView(int position)  {
            mCheckBox.setText(Aktifitas.Aktifitas[position]);
        }

        public void onClick(View view){

        }

    }
}

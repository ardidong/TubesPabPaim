package com.apahayo.tubespabpaim.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apahayo.tubespabpaim.Model.Gejala;
import com.apahayo.tubespabpaim.R;

import java.util.ArrayList;

public class GejalaAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<Gejala> gejalaList;
    private CustomItemClickListener mListener;

    public interface CustomItemClickListener {
        void onChoiceClick(int position, boolean answer);
    }

    public void setCustomItemClickListener(CustomItemClickListener listener) {
        mListener = listener;
    }

    public GejalaAdapter(Context context, ArrayList<Gejala> gejalaList) {
        this.context = context;
        this.gejalaList = gejalaList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_pilih_gejala_list
                , parent, false);
        GejalaAdapter.GejalaViewHolder gh = new GejalaAdapter.GejalaViewHolder(view,mListener);
        return gh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Gejala currentGejala = gejalaList.get(position);
        ((GejalaViewHolder)holder).bindTo(currentGejala);

    }

    @Override
    public int getItemCount() {
        return gejalaList.size();
    }

    private class GejalaViewHolder extends RecyclerView.ViewHolder {

        private CheckBox gejalaCB;


        public GejalaViewHolder(@NonNull View itemView, final CustomItemClickListener listener) {
            super(itemView);
            gejalaCB = itemView.findViewById(R.id.checkbox_gejala);

            gejalaCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onChoiceClick(position,isChecked);
                        }
                    }
                }
            });
        }

        public void bindTo(Gejala mGejala){
            gejalaCB.setText(mGejala.getPertanyaan());

        }
    }
}

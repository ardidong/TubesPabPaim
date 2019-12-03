package com.apahayo.tubespabpaim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apahayo.tubespabpaim.Model.Pertanyaan;

import java.util.ArrayList;

public class PertanyaanAdapter extends RecyclerView.Adapter<PertanyaanAdapter.ViewHolder> {
    private ArrayList<Pertanyaan> mPertanyaan;
    private Context context;
    private OnItemClickListener mListener;
    private RadioGroup mRadioGroup;




    public interface OnItemClickListener {
        void onChoiceClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public PertanyaanAdapter(ArrayList<Pertanyaan> mPertanyaan, Context context) {
        this.mPertanyaan = mPertanyaan;
        this.context = context;

    }

    @NonNull
    @Override
    public PertanyaanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.kuisioner_list, parent, false);
        PertanyaanAdapter.ViewHolder vh = new PertanyaanAdapter.ViewHolder(v, mListener);
        mRadioGroup.clearCheck();
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PertanyaanAdapter.ViewHolder holder, int position) {
        Pertanyaan currentPertanyaan = mPertanyaan.get(position);
        holder.bindTo(currentPertanyaan);

    }

    @Override
    public int getItemCount() {
        return mPertanyaan.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mJudulPertanyaan;






        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mJudulPertanyaan = itemView.findViewById(R.id.tv_pertanyaan);
            mRadioGroup = itemView.findViewById(R.id.radioPilihan);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onChoiceClick(position);
                            mRadioGroup.clearCheck();
                        }


                    }

                }
            });

            mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onChoiceClick(position);
                        }
                    }
                }

            }

            );


        }

        void bindTo(Pertanyaan currentPertanyaan) {
            mJudulPertanyaan.setText(currentPertanyaan.getSoal());


        }

    }
}

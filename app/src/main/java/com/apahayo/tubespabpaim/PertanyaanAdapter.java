package com.apahayo.tubespabpaim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
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
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PertanyaanAdapter.ViewHolder holder, int position) {
        Pertanyaan currentPertanyaan = mPertanyaan.get(position);
        holder.bindTo(currentPertanyaan);
        holder.setOptions(currentPertanyaan, position);
    }

    @Override
    public int getItemCount() {
        return mPertanyaan.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mJudulPertanyaan;
        private RadioButton radioYa;
        private RadioButton radioTidak;
        private RadioGroup mRadioGroup;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mJudulPertanyaan = itemView.findViewById(R.id.tv_pertanyaan);
            mRadioGroup = itemView.findViewById(R.id.radioPilihan);
            radioYa = itemView.findViewById(R.id.radioYa);
            radioTidak = itemView.findViewById(R.id.radioTidak);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onChoiceClick(position);
                        }
                    }
                }
            });

            radioYa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onChoiceClick(position);
                        }
                    }
                }
            });

            radioTidak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onChoiceClick(position);
                        }
                    }
                }
            });

        }

        void bindTo(Pertanyaan currentPertanyaan) {
            mJudulPertanyaan.setText(currentPertanyaan.getSoal());
        }

        public void setOptions(final Pertanyaan pertanyaan, int position) {
            mRadioGroup.setTag(position);
            if (pertanyaan.isAnswered) {
                mRadioGroup.check(pertanyaan.checkedId);
            } else {
                mRadioGroup.check(-1);
            }
            mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int pos = (int) group.getTag();
                    Pertanyaan que = mPertanyaan.get(pos);
                    que.isAnswered = true;
                    que.checkedId = checkedId;
                }
            });

        }
    }
}

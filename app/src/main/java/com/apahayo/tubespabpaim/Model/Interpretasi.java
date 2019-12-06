package com.apahayo.tubespabpaim.Model;

import java.util.ArrayList;
import java.util.List;

public class Interpretasi {
    private int neurosis;
    private int psikotik;
    private int ptsd;
    private ArrayList<Pertanyaan> pertanyaans;
    private String indikasi;

    public Interpretasi(ArrayList<Pertanyaan> pertanyaans) {
        this.pertanyaans = pertanyaans;
    }

    public int getNeurosis() {
        return neurosis;
    }

    public void setNeurosis(int neurosis) {
        this.neurosis = neurosis;
    }

    public int getPsikotik() {
        return psikotik;
    }

    public void setPsikotik(int psikotik) {
        this.psikotik = psikotik;
    }

    public int getPtsd() {
        return ptsd;
    }

    public void setPtsd(int ptsd) {
        this.ptsd = ptsd;
    }

    public String getIndikasi() {
        return indikasi;
    }

    public void setIndikasi(String indikasi) {
        this.indikasi = indikasi;
    }

    public ArrayList<Pertanyaan> getPertanyaans() {
        return pertanyaans;
    }

    public void setPertanyaans(ArrayList<Pertanyaan> pertanyaans) {
        this.pertanyaans = pertanyaans;
    }

    public void hitungIndikasi(){


        List<Pertanyaan> neurosisList = pertanyaans.subList(0,19);
        List<Pertanyaan> psikotikList = pertanyaans.subList(21,23);
        List<Pertanyaan> ptsdList = pertanyaans.subList(24,29);

        for(int i=0;i<neurosisList.size();i++){
            if (neurosisList.get(i).isAnswer()){
                neurosis++;
            }
        }

        for(int i=0;i<psikotikList.size();i++){
            if (psikotikList.get(i).isAnswer()){
                psikotik++;
            }
        }

        for(int i=0;i<ptsdList.size();i++){
            if (ptsdList.get(i).isAnswer()){
                ptsd++;
            }
        }

        if(neurosis<=5){
            this.indikasi="Normal";
        }else if(neurosis<=12){
            this.indikasi="Mulai Stress";
        }else if(neurosis<=20){
            this.indikasi = "cukup menganggu";
        }
        if(psikotik>0){
            this.indikasi = "gangguan mental cukup berarti";
        }
        if(ptsd>0){
            this.indikasi = "ptsd";
        }
    }


}

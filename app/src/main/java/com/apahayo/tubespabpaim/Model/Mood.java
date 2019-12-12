package com.apahayo.tubespabpaim.Model;

import java.io.Serializable;
import java.util.Date;

public class Mood implements Serializable {
    private String key;
    private String waktu;
    private String judul;
    private String deskripsi;
    private String quote;
    private int value;

    public Mood() {

    }

    public Mood(String judul) {
        this.judul = judul;
    }

    public Mood(String waktu, String judul, int value,String deskripsi) {
        this.waktu = waktu;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

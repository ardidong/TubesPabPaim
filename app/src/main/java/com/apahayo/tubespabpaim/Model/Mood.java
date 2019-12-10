package com.apahayo.tubespabpaim.Model;

import java.util.Date;

public class Mood {
    private String tanggal;
    private String waktu;
    private String judul;
    private String deskripsi;
    private int value;

    public Mood() {
    }
    public Mood(String judul) {
        this.judul = judul;
    }

    public Mood(String tanggal, String waktu, String judul, int value) {
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.judul = judul;
        this.value = value;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

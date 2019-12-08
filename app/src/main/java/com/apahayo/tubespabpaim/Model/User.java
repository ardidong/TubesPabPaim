package com.apahayo.tubespabpaim.Model;

public class User {

    private String email;
    private String nama;
    private int jumlahInterpretasi;

    public User(String email, String nama) {
        this.email = email;
        this.nama = nama;
        this.jumlahInterpretasi = 0;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getJumlahInterpretasi() {
        return jumlahInterpretasi;
    }

    public void setJumlahInterpretasi(int jumlahInterpretasi) {
        this.jumlahInterpretasi = jumlahInterpretasi;
    }
}

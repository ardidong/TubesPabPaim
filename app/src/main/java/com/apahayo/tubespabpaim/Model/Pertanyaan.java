package com.apahayo.tubespabpaim.Model;

public class Pertanyaan {

    public long id;
    public int checkedId = -1;
    public boolean isAnswered;
    public boolean answer;
    private String Soal;


    public Pertanyaan(String soal) {
        Soal = soal;
    }

    public String getSoal() {
        return Soal;
    }

    public void setSoal(String soal) {
        Soal = soal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCheckedId() {
        return checkedId;
    }

    public void setCheckedId(int checkedId) {
        this.checkedId = checkedId;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}

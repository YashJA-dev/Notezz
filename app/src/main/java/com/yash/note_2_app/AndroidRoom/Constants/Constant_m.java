package com.yash.note_2_app.AndroidRoom.Constants;

public class Constant_m {
    String title;
    String Date;
    String note;
    String time;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Constant_m(String title, String date,String time, String note) {
        this.title = title;
        this.time=time;
        Date = date;
        this.note = note;
    }
}

package com.yash.note_2_app.AndroidRoom.Database.Tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "mainTable")
public class Main_Table {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "note")
    String note;
    @ColumnInfo(name = "date")
    String date;
    @ColumnInfo(name = "time")
    String time;
    @ColumnInfo(name = "title")
    String title;

    public Main_Table(String note, String date, String time, String title,int id) {
        this.note = note;
        this.date = date;
        this.time = time;
        this.title = title;
        this.id=id;
    }
    @Ignore
    public Main_Table(String note, String date, String time, String title) {
        this.note = note;
        this.date = date;
        this.time = time;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

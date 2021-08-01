package com.example.webinarmeeting.Model;

public class Data {
    String title;
    String description;
    String date;
    String time;
    String id;

    String mdate;

    public Data(String title, String description, String date, String time, String id, String mdate) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.id = id;
        this.mdate = mdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public Data(){

    }
}

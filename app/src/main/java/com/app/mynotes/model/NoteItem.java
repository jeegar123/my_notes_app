package com.app.mynotes.model;

public class NoteItem {
    private String data;
    private String date;

    public NoteItem(String data, String date) {
        this.data = data;
        this.date = date;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

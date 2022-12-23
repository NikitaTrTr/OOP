package ru.nsu.ntatarinov;


import java.util.Date;

public class Record {

    public Date timestamp;
    public String title;
    public String text;

    public Record(String title, String text) {
        this.timestamp = new Date();
        this.title = title;
        this.text = text;
    }

    @Override
    public boolean equals(Object obj) {
        return this.title.equals(((Record) obj).title);
    }

    @Override
    public int hashCode() {
        return this.title.hashCode();
    }

    @Override
    public String toString() {
        return timestamp.toString() + "\n" + title + "\n" + text;
    }
}

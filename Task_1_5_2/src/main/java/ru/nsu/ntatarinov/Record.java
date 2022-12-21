package ru.nsu.ntatarinov;

import static java.time.format.DateTimeFormatter.ofLocalizedDateTime;
import java.time.format.FormatStyle;

import java.time.LocalDateTime;

public class Record {

    public LocalDateTime timestamp;
    public String title;
    public String text;

    public Record(String title, String text) {
        this.timestamp = LocalDateTime.now();
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
        return timestamp.format(ofLocalizedDateTime(FormatStyle.MEDIUM)) + " " + title + " " + text;
    }
}

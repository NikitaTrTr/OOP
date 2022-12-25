package ru.nsu.ntatarinov;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Notebook {

    public final List<Record> records;

    public Notebook() {
        this.records = new ArrayList<>();
    }

    public void addRecord(String title, String text) {
        records.add(new Record(title, text));
    }
    public void addRecord(Record record){
        records.add(record);
    }

    public void removeRecord(String title) {
        records.remove(new Record(title, ""));
    }

    public void showRecords() {
        for (Record record : records) {
            System.out.println(record);
        }
    }

    public void showRecords(Date date1, Date date2, String[] keywords) {
        records.stream()
            .filter((Record r) -> r.timestamp.after(date1))
            .filter((Record r) -> r.timestamp.before(date2))
            .filter((Record r) -> containsKeywords(keywords, r.title))
            .forEach(System.out::println);
    }

    private boolean containsKeywords(String[] keywords, String title) {
        if (keywords.length == 0) {
            return true;
        }
        for (String keyword : keywords) {
            if (title.toUpperCase().contains(keyword.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}
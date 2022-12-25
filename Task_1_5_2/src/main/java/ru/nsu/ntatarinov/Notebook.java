package ru.nsu.ntatarinov;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Notebook class.
 */
public class Notebook {

    public final List<Record> records;

    public Notebook() {
        this.records = new ArrayList<>();
    }

    public void addRecord(String title, String text) {
        records.add(new Record(title, text));
    }

    public void addRecord(Record record) {
        records.add(record);
    }

    public void removeRecord(String title) {
        records.remove(new Record(title, ""));
    }

    /**
     * Print all records in standard output.
     */
    public void showRecords() {
        for (Record record : records) {
            System.out.println(record);
        }
    }

    /**
     * Print all records in standard output, that were created between date1 and date2 and contain
     * keywords in title.
     *
     * @param date1    minimal date
     * @param date2    maximal date
     * @param keywords keywords to search in titles of records
     */
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
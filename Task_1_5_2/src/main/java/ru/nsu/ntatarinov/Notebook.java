package ru.nsu.ntatarinov;

import java.util.ArrayList;
import java.util.List;

public class Notebook {
    public List<Record> records;
    public Notebook(){
        this.records = new ArrayList<>();
    }
    public void addRecord(String title, String text){
        records.add(new Record(title, text));
    }
    public void removeRecord(String title){
        records.remove(new Record(title, ""));
    }
    public void showRecords(){
        for (Record record: records){
            System.out.println(record);
        }
    }
}

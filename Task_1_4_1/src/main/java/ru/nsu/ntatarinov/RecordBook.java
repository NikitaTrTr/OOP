package ru.nsu.ntatarinov;

import java.util.ArrayList;
import java.util.List;

public class RecordBook {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String faculty;
    private final List<Record> records;
    private int numberOfSemester;

    RecordBook(int id, String firstName, String lastName, String faculty, int semester){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.faculty = faculty;
        this.numberOfSemester = semester;
        this.records = new ArrayList<>();
    }
    public boolean equals(Object o){
        if (!(o instanceof RecordBook)) return false;
        RecordBook other = (RecordBook) o;
        return other.id == this.id;
    }

    public String toString() {
        return String.format("ID: %d\nName: %s\nFaculty: %s\nYear of Education: %d\n",
            id, firstName+lastName, faculty, (numberOfSemester-1)/2+1);
    }
    public void changeNumOfSemester(int n){
        this.numberOfSemester = n;
    }
    public void addRecord(Record rec){
        records.add(rec);
    }
    public void addRecord(String disciplineName, String teacherName, int mark, int semester){
        records.add(new Record(disciplineName, teacherName, mark, semester));
    }
    public List<Record> getAllRecords(){
        return records;
    }
    public double averageMark(){
        return 0;
    }
    public boolean ableToGetRedDiploma(){
        return true;
    }
    public boolean hasIncreaseStipend(){
        return true;
    }


}
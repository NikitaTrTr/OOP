package ru.nsu.ntatarinov;

import java.util.ArrayList;
import java.util.List;

/**
 * Record Book class.
 */
public class RecordBook {

    private final int id;
    private final String firstName;
    private final String lastName;
    private final String faculty;
    private final List<Record> records;
    private int numberOfSemester;

    /**
     * creates Record book.
     *
     * @param id ID number of student
     * @param firstName first name of student
     * @param lastName last name of student
     * @param faculty faculty
     * @param semester number of current semester
     */
    public RecordBook(int id, String firstName, String lastName, String faculty, int semester) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.faculty = faculty;
        this.numberOfSemester = semester;
        this.records = new ArrayList<>();
    }

    public void changeNumOfSemester(int n) {
        this.numberOfSemester = n;
    }

    public void addRecord(Record rec) {
        records.add(rec);
    }

    public void addRecord(String disciplineName, String teacherName, int mark, int semester) {
        records.add(new Record(disciplineName, teacherName, mark, semester));
    }

    public List<Record> getAllRecords() {
        return records;
    }

    /**
     * get average of marks for whole period of studying.
     *
     * @return average of marks
     */
    public float averageMark() {
        float sum = 0F;
        for (Record record : records) {
            sum += ((float) record.mark) / ((float) records.size());
        }
        return sum;
    }

    /**
     * get average of marks in particular semester.
     *
     * @param numberOfSemester number of semester
     * @return average of marks
     */
    public float averageMark(int numberOfSemester) {
        float sum = 0F;
        int k = 0;
        for (Record record : records) {
            if (record.semester == numberOfSemester) {
                sum += (float) record.mark;
                k += 1;
            }
        }
        return sum / (float) k;
    }

    /**
     * does student satisfy requirements of getting red diploma.
     *
     * @return true or false answer
     */
    public boolean ableToGetRedDiploma() {
        if (this.averageMark() < 4.75) {
            return false;
        }
        for (Record record : records) {
            if (record.mark == 3) {
                return false;
            }
        }
        if (records.get(records.size() - 1).mark != 5) {
            return false;
        }
        return true;
    }

    public boolean hasIncreasedStipend() {
        return averageMark(this.numberOfSemester - 1) == 5.0;
    }

    /**
     * converts to string data from record book.
     *
     * @return string
     */
    public String toString() {
        return String.format("ID: %d\nName: %s\nFaculty: %s\nYear of Education: %d\n",
            id, firstName +" "+ lastName, faculty, (numberOfSemester - 1) / 2 + 1);
    }

}
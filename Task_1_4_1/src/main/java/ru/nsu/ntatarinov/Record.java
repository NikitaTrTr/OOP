package ru.nsu.ntatarinov;

/**
 * Record class.
 */
public class Record {

    public String disciplineName;
    public String teacherName;
    public int mark;
    public int semester;

    /**
     * creates record.
     *
     * @param disciplineName name of discipline
     * @param teacherName name of teacher
     * @param mark mark
     * @param semester number of semester
     */
    public Record(String disciplineName, String teacherName, int mark, int semester) {
        this.disciplineName = disciplineName;
        this.teacherName = teacherName;
        this.mark = mark;
        this.semester = semester;
    }

    public String toString() {
        return String.format("Discipline: %s, teacher: %s, mark: %d, semester: %d\n",
            disciplineName, teacherName, mark, semester);
    }
}

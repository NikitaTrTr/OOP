package ru.nsu.ntatarinov;

public class Record {
    public String disciplineName;
    public String teacherName;
    public int mark;
    public int semester;
    Record(String disciplineName, String teacherName, int mark, int semester){
        this.disciplineName = disciplineName;
        this.teacherName = teacherName;
        this.mark = mark;
        this.semester = semester;
    }
    public String toString(){
        return String.format("Discipline: %s, teacher: %s, mark: %d, semester: %d\n",
            disciplineName, teacherName, mark, semester);
    }
}

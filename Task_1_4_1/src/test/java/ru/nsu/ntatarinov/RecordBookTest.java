package ru.nsu.ntatarinov;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Different tests for RecordBook.
 */
public class RecordBookTest {

    @Test
    void simpleTest() {
        RecordBook myBook = new RecordBook(210614, "Nikita", "Tatarinov", "FIT", 3);
        myBook.addRecord("Calculus", "Vaskevich", 4, 1);
        myBook.addRecord("Discrete math", "Vlasov", 5, 1);
        myBook.addRecord("Imperative programming", "Nesterenko", 4, 1);
        Record myRec = new Record("Declarative programming", "Vlasov", 5, 1);
        myBook.addRecord(myRec);
        myBook.addRecord("Digital platforms", "IRTEGOV", 5, 2);
        myBook.addRecord("Calculus", "Rudometova", 5, 2);

        assertTrue(myBook.averageMark() - 4.66 < 0.01);
        assertFalse(myBook.ableToGetRedDiploma());
        assertTrue(myBook.hasIncreasedStipend());
        assertEquals(5.0, myBook.averageMark(2));
        assertEquals("ID: 210614\nName: Nikita Tatarinov\nFaculty: FIT\nYear of Education: 2\n", myBook.toString());
    }
    @Test
    void anotherTest() {
        RecordBook myBook = new RecordBook(210614, "Nikita", "Tatarinov", "FIT", 3);
        Record myRec = new Record("Declarative programming", "Vlasov", 5, 1);
        Record myRec2 = new Record("Digital platforms", "IRTEGOV", 5, 2);
        myBook.addRecord(myRec);
        myBook.addRecord(myRec2);
        List<Record> records = new ArrayList<>();
        records.add(myRec);
        records.add(myRec2);
        assertArrayEquals(records.toArray(), myBook.getAllRecords().toArray());
        assertEquals(1, myRec.semester);
        assertEquals("Vlasov", myRec.teacherName);
        assertEquals(5, myRec.mark);
        assertEquals("Declarative programming", myRec.disciplineName);
        assertEquals("Discipline: Declarative programming, teacher: Vlasov, mark: 5, semester: 1\n", myRec.toString());
    }

}

package ru.nsu.ntatarinov;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
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
    }

}

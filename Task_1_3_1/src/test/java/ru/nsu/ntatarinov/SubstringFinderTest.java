package ru.nsu.ntatarinov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Different test for SubstringFinder class.
 */
public class SubstringFinderTest {

    @Test
    void simpleTest() throws IOException {
        String pattern = "kjhfqp";
        long[] ans = {9L, 53L, 59L, 109L};
        List<Long> ansLong = new ArrayList<>();
        for (int i = 0; i < ans.length; i++) {
            ansLong.add(ans[i]);
        }
        assertEquals(ansLong,
            SubstringFinder.findSubstringEntries("./src/test/resources/test.txt", pattern));
    }

    @Test
    void nullPointerExceptionTest() {
        assertThrows(FileNotFoundException.class,
            () -> SubstringFinder.findSubstringEntries("./src/test/resources/test1.txt",
                "pattern"));
    }

    @Test
    void nullPointerExceptionTest2() {
        InputStream input = null;
        assertThrows(NullPointerException.class,
            () -> SubstringFinder.findSubstringEntries(input,
                "pattern"));
    }
}

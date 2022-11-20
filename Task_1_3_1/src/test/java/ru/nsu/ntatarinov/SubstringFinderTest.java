package ru.nsu.ntatarinov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Different test for SubstringFinder class.
 */
public class SubstringFinderTest {

    @Test
    void simpleTest() throws IOException {
        String pattern = "kjhfqp";
        long[] ans = {9L, 53L, 59L, 109L};
        ArrayList<Long> ansLong = new ArrayList<>();
        for (int i = 0; i < ans.length; i++) {
            ansLong.add(ans[i]);
        }
        assertEquals(ansLong,
            SubstringFinder.findSubstringEntries("./src/test/resources/test.txt", pattern));
    }

    @Test
    void nullPntrExcptnTest() {
        assertThrows(NullPointerException.class,
            () -> SubstringFinder.findSubstringEntries("./src/test/resources/test1.txt",
                "pattern"));
    }
}

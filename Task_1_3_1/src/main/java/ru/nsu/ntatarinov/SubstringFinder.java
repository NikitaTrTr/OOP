package ru.nsu.ntatarinov;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * class for finding substrings in text.
 */
public class SubstringFinder {

    /**
     * finds indices of entries of substring pattern in 'input' stream.
     *
     * @param fileName name of file for find substring
     * @param pattern  substring for search
     * @return list of indices
     * @throws IOException IOException
     */
    public static List<Long> findSubstringEntries(String fileName, String pattern)
        throws IOException {
        InputStream input = new FileInputStream(fileName);
        return findEntries(input, ((pattern + '&').getBytes()));
    }

    /**
     * finds indices of entries of substring pattern in 'input' stream.
     *
     * @param input   stream in which you want to find a pattern
     * @param pattern substring for search
     * @return list of indices
     * @throws IOException IOException
     */
    public static List<Long> findSubstringEntries(InputStream input, String pattern)
        throws IOException {
        if (input == null) {
            throw new NullPointerException();
        }
        return findEntries(input, pattern.getBytes());
    }

    /**
     * finds entries using Z function.
     *
     * @param textStream stream in which you want to find a pattern
     * @param pattern    substring for search
     * @return list of indices
     * @throws IOException IOException
     */
    private static List<Long> findEntries(InputStream textStream, byte[] pattern)
        throws IOException {
        List<Long> entries = new ArrayList<>();
        InputStream input = new ByteArrayInputStream(pattern);
        long left = 0L;
        long right = 0L;
        long index = 0L;
        int zfuncValue;
        int len = pattern.length;
        int[] patZfunc = new int[len];
        List<Integer> buffer = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            buffer.add(input.read());
        }
        input.close();
        input = textStream;
        while (true) {
            zfuncValue =
                (right > index) ? (int) Long.min(patZfunc[(int) (index - left)], right - index)
                    : 0;
            while (zfuncValue < len && pattern[zfuncValue] == buffer.get(zfuncValue)) {
                zfuncValue += 1;
            }
            if (index + (long) zfuncValue > right) {
                left = index;
                right = index + (long) zfuncValue;
            }
            buffer.remove(0);
            if (input.available() > 0) {
                buffer.add(input.read());
            } else {
                input.close();
                break;
            }
            if (index < len) {
                patZfunc[(int) index] = zfuncValue;
            }
            if (zfuncValue == len - 1 & index >= len) {
                entries.add(index - len);
            }
            index += 1;
        }
        return entries;
    }
}

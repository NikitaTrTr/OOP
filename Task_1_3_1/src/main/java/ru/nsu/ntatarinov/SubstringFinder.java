package ru.nsu.ntatarinov;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SubstringFinder {

    public static ArrayList<Long> findSubstringEntries(String fileName, String pattern)
        throws IOException {
        InputStream input = null;
        try {
            input = new FileInputStream(fileName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Integer[] zfunc = findZfuncForPattern(pattern.toCharArray());
        return findEntries(zfunc, input, pattern.toCharArray());
    }

    private static Integer[] findZfuncForPattern(char[] pattern) {
        int left = 0;
        int right = 0;
        int len = pattern.length;
        Integer[] zfunc = new Integer[len];
        zfunc[0] = len;
        for (int i = 1; i < len; i++) {
            zfunc[i] = (right > i) ? Integer.min(zfunc[i - left], right - i) : 0;
            while (i + zfunc[i] < len && pattern[zfunc[i]] == pattern[i + zfunc[i]]) {
                zfunc[i] += 1;
            }
            if (i + zfunc[i] > right) {
                left = i;
                right = i + zfunc[i];
            }
        }
        return zfunc;
    }

    private static ArrayList<Long> findEntries(Integer[] zfunc, InputStream input, char[] pattern)
        throws IOException {
        ArrayList<Long> entries = new ArrayList<>();
        long left = 0L;
        long right = 0L;
        long index = 0L;
        int zfuncValue;
        int len = pattern.length;
        ArrayList<Integer> buffer = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            buffer.add(input.read());
        }
        while (true) {
            zfuncValue =
                (right > index) ? (int) Long.min((long) zfunc[(int) (index - left)], right - index)
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
            if (zfuncValue == len) {
                entries.add(index);
            }

            index += 1;
        }
        return entries;
    }
}

package ru.nsu.ntatarinov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import org.junit.jupiter.api.Test;

/**
 * some simple tests for Tree collection.
 */
public class TreeTests {

    @Test
    void sizeAndAddTest() {
        Tree<String> treeA = new Tree<>("A");
        treeA.add("B");
        treeA.add("C");

        assertEquals(treeA.size(), 2);
    }

    @Test
    void sonsValuesTest() {
        Tree<String> treeA = new Tree<>("A");
        treeA.add("B");
        treeA.add("C");
        ArrayList<String> list = new ArrayList<>();
        list.add("B");
        list.add("C");

        assertEquals(treeA.sonsValues(), list);
    }

    @Test
    void emptyTreeTest() {
        Tree<String> treeA = new Tree<>();
        assertEquals(treeA.value(), null);
        treeA.add("A");

        assertEquals(treeA.value(), "A");
    }

    @Test
    void addToSubtreeTest() {
        Tree<String> treeA = new Tree<>("A");
        Tree<String> treeB = treeA.add("B");
        treeB.add("C");
        treeA.add(treeB, "D");
        treeA.add(treeA, "G");
        ArrayList<String> list = new ArrayList<>();
        list.add("C");
        list.add("D");
        Tree<String> treeX = new Tree<>("X");
        assertEquals(treeB.sonsValues(), list);
        assertEquals(treeA.add(treeX, "M"), null);
    }

    @Test
    void getNodeByValueTest() {
        Tree<String> treeA = new Tree<>("A");
        treeA.add("B");
        Tree<String> treeC = treeA.add("C");
        Tree<String> treeD = treeC.add("D");

        assertEquals(treeA.getNodeByValue("D"), treeD);
        assertEquals(treeA.getNodeByValue("G"), null);
    }

    @Test
    void getParentTest() {
        Tree<String> treeA = new Tree<>("A");
        Tree<String> treeB = treeA.add("B");

        assertEquals(treeB.getParent(), treeA);

    }


    @Test
    void bfsIteratorTest() {
        Tree<String> treeA = new Tree<>("A");
        treeA.add("B");
        treeA.add("C");
        Tree<String> treeD = treeA.add("D");
        Tree<String> treeE = treeD.add("E");
        treeD.add("F");
        treeD.add("G");
        treeE.add("H");
        treeE.add("K");
        String[] array = {"A", "B", "C", "D", "E", "F", "G", "H", "K"};
        Iterator<String> iter = treeA.bfsIterator();
        for (String s : array) {
            assertEquals(iter.hasNext(), true);
            assertEquals(iter.next(), s);
        }
    }

    @Test
    void dfsIteratorTest() {
        Tree<String> treeA = new Tree<>("A");
        treeA.add("B");
        treeA.add("C");
        Tree<String> treeD = treeA.add("D");
        Tree<String> treeE = treeD.add("E");
        treeD.add("F");
        treeD.add("G");
        treeE.add("H");
        treeE.add("K");
        String[] array = {"A", "B", "C", "D", "E", "H", "K", "F", "G"};
        Iterator<String> iter = treeA.dfsIterator();
        for (String s : array) {
            assertEquals(iter.hasNext(), true);
            assertEquals(iter.next(), s);
        }
    }

    @Test
    void removeTest() {
        Tree<String> treeA = new Tree<>("A");
        Tree<String> treeB = treeA.add("B");
        Tree<String> treeC = treeB.add("C");
        treeA.remove(treeC);
        assertEquals(treeB.size(), 0);
        treeA.remove(treeB);
        assertEquals(treeA.size(), 0);
    }


}

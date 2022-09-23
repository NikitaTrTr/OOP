package ru.nsu.ntatarinov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Different test for stack class.
 */
public class StackTest {

    @Test
    void exampleTest() {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> pushedStack = new Stack<>();

        pushedStack.push(4);
        pushedStack.push(8);

        stack.push(2);
        stack.push(7);

        stack.pushStack(pushedStack);
        stack.popStack(2);

        assertEquals(2, stack.count());
        assertEquals(7, stack.pop());
    }


    @Test
    void countTest() {
        Stack<Integer> stack = new Stack<Integer>();
        assertEquals(0, stack.count());
        stack.push(2);
        assertEquals(1, stack.count());
        stack.push(7);
        assertEquals(2, stack.count());
        stack.push(4);
        assertEquals(3, stack.count());
    }

    @Test
    void increaseCapacityTest() {
        Stack<Integer> stack = new Stack<Integer>();
        Integer[] array = {2, 7, 4, 8, 5, 3, 9};
        for (int i = 0; i < array.length; i++) {
            stack.push(array[i]);
        }
        assertEquals(7, stack.count());
    }

    @Test
    void popMethodTest() {
        Stack<Integer> stack = new Stack<Integer>();
        Integer[] array = {2, 7, 4, 8, 5, 3, 9};
        for (int i = 0; i < array.length; i++) {
            stack.push(array[i]);
        }
        for (int i = 0; i < array.length; i++) {
            assertEquals(array[array.length - i - 1], stack.pop());
        }
    }

    @Test
    void pushStackMethodTest() {
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> pushedStack = new Stack<Integer>();
        Integer[] array = {2, 7, 4, 8, 5, 3, 9};
        for (int i = 0; i < array.length; i++) {
            stack.push(array[i]);
            pushedStack.push(array[i]);
        }
        stack.pushStack(pushedStack);

        assertEquals(9, stack.pop());
        assertEquals(3, stack.pop());
    }

    @Test
    void popStackMethodTest() {
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> popStack = new Stack<Integer>();
        Integer[] array = {2, 7, 4, 8, 5, 3, 9};
        for (int i = 0; i < array.length; i++) {
            stack.push(array[i]);
        }
        popStack = stack.popStack(4);

        assertEquals(4, popStack.count());
        assertEquals(9, popStack.pop());
        assertEquals(3, popStack.pop());
    }


}

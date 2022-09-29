package ru.nsu.ntatarinov;

import java.util.EmptyStackException;

/**
 * Java implementation of basic stack.
 *
 * @param <T> type of elements
 */
public class Stack<T> {

    private int capacity;
    private int currentSize;
    private T[] stack;

    /**
     * Creates object of Stack class.
     */
    @SuppressWarnings("unchecked")
    public Stack() {
        this.capacity = 5;
        this.currentSize = 0;
        this.stack = (T[]) new Object[capacity];
    }

    /**
     * Add element to top of Stack.
     *
     * @param element element to add
     */
    @SuppressWarnings("unchecked")
    public void push(T element) {
        if (element == null) {
            throw new NullPointerException("Can't push null pointed object");
        }
        if (currentSize == capacity) {
            this.capacity *= 2;
            T[] biggerStack = (T[]) new Object[capacity];
            System.arraycopy(stack, 0, biggerStack, 0, currentSize);
            this.stack = biggerStack;
        }
        this.stack[currentSize++] = element;
    }

    /**
     * Pops top element.
     *
     * @return element from the top of Stack
     */
    @SuppressWarnings("unchecked")
    public T pop() {
        if (currentSize == 0) {
            throw new EmptyStackException();
        }
        if (currentSize <= capacity / 2) {
            this.capacity = currentSize;
            T[] lessStack = (T[]) new Object[capacity];
            System.arraycopy(stack, 0, lessStack, 0, currentSize);
            this.stack = lessStack;
        }
        this.currentSize--;
        return this.stack[currentSize];
    }

    /**
     * Pushes all elements from oldStack to current Stack.
     *
     * @param oldStack stack from which you want to add elements
     */
    public void pushStack(Stack<T> oldStack) {
        if (oldStack == null) {
            throw new NullPointerException("Can't push null pointed object");
        }
        for (int i = 0; i < oldStack.count(); i++) {
            push(oldStack.stack[i]);
        }
    }

    /**
     * Pop num elements and returns them as new stack.
     *
     * @param num number of elements you want to pop
     * @return new Stack with num elements
     */
    @SuppressWarnings("unchecked")
    public Stack<T> popStack(int num) {
        if (currentSize < num) {
            throw new EmptyStackException();
        }
        this.currentSize -= num;
        Stack<T> newStack = new Stack<>();
        for (int i = 0; i < num; i++) {
            newStack.push(this.stack[this.currentSize + i]);
        }
        if (currentSize <= capacity / 2) {
            this.capacity = currentSize;
            T[] lessStack = (T[]) new Object[capacity];
            System.arraycopy(stack, 0, lessStack, 0, currentSize);
            this.stack = lessStack;
        }
        return newStack;
    }

    /**
     * Return number of elements in Stack.
     *
     * @return number of elements in Stack
     */
    public int count() {
        return this.currentSize;
    }
}

package ru.nsu.ntatarinov;

/**
 * Java implementation of basic stack.
 *
 * @param <T> type of elements
 */
public class Stack<T> {

    private int capacity;
    private int curSize;
    private T[] stack;

    /**
     * Creates object of Stack class.
     */
    public Stack() {
        this.capacity = 5;
        this.curSize = 0;
        this.stack = (T[]) new Object[capacity];
    }

    /**
     * Add element to top of Stack.
     *
     * @param elem element to add
     */
    public void push(T elem) {
        if (curSize == capacity) {
            this.capacity *= 2;
            T[] biggerStack = (T[]) new Object[capacity];
            System.arraycopy(stack, 0, biggerStack, 0, curSize);
            this.stack = biggerStack;
        }
        this.stack[curSize++] = elem;
    }

    /**
     * Pops top element.
     *
     * @return element from the top of Stack
     */
    public T pop() {
        return this.stack[--curSize];
    }

    /**
     * Pushes all elements from oldStack to current Stack.
     *
     * @param oldStack stack from which you want to add elements
     */
    public void pushStack(Stack<T> oldStack) {
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
    public Stack<T> popStack(int num) {
        this.curSize -= num;
        Stack<T> newStack = new Stack<>();
        for (int i = 0; i < num; i++) {
            newStack.push(this.stack[this.curSize + i]);
        }
        return newStack;
    }

    /**
     * Return number of elements in Stack.
     *
     * @return number of elements in Stack
     */
    public int count() {
        return this.curSize;
    }
}

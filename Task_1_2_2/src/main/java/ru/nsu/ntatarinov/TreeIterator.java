package ru.nsu.ntatarinov;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import ru.nsu.ntatarinov.Tree.TypeOfIterator;

/**
 * Iterator class for Tree collection.
 *
 * @param <E> type of objects in nodes of tree
 */
public class TreeIterator<E> implements Iterator<E> {

    /**
     * list of nodes in tree.
     */
    private final ArrayList<Tree<E>> nodes;
    /**
     * pointer for next and hasNext method.
     */

    Tree.TypeOfIterator type;
    private final int numOfOperations;
    private final Tree<E> root;

    /**
     * constructs an Iterator, fills nodes list.
     *
     * @param tree Iterated tree
     * @param type of traversal
     */
    public TreeIterator(Tree<E> tree, Tree.TypeOfIterator type) {
        this.nodes = new ArrayList<>();
        this.type = type;
        nodes.add(tree);
        this.root = tree;
        this.numOfOperations = tree.numOfOperations;
    }

    /**
     * checks if next element exist.
     *
     * @return true/false if next element exists or not
     */
    @Override
    public boolean hasNext() {
        if (root.numOfOperations > this.numOfOperations) {
            throw new ConcurrentModificationException();
        }
        return nodes.size() != 0;
    }

    /**
     * returns next element.
     *
     * @return next element
     */
    @Override
    public E next() {
        if (root.numOfOperations > this.numOfOperations) {
            throw new ConcurrentModificationException();
        }
        if (type == Tree.TypeOfIterator.DFS) {
            Tree<E> currentNode = nodes.get(0);
            nodes.remove(0);
            nodes.addAll(0, currentNode.getSons());
            return currentNode.value();
        } else {
            Tree<E> currentNode = nodes.get(0);
            nodes.remove(0);
            nodes.addAll(currentNode.getSons());
            return currentNode.value();
        }
    }
}

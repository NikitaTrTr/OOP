package ru.nsu.ntatarinov;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Iterator class for Tree collection.
 *
 * @param <E> type of objects in nodes of tree
 */
public class TreeIterator<E> implements Iterator {

    /**
     * list of all nodes in tree.
     */
    private final ArrayList<E> nodes;
    /**
     * pointer for next and hasNext method.
     */
    int pointer;

    /**
     * constructs an Iterator, fills nodes list.
     *
     * @param tree Iterated tree
     * @param type of traversal
     */
    public TreeIterator(Tree<E> tree, String type) {
        nodes = new ArrayList<>();
        pointer = 0;
        if (type.equals("DFS")) {
            dfs(tree);
        } else {
            bfs(tree);
        }
    }

    /**
     * fills nodes list by dfs traversal.
     *
     * @param tree iterated tree
     */
    private void dfs(Tree<E> tree) {
        nodes.add(tree.value());
        for (int i = 0; i < tree.getSons().size(); i++) {
            dfs(tree.getSons().get(i));
        }
    }

    /**
     * fills nodes list by bfs traversal.
     *
     * @param tree iterated tree
     */
    private void bfs(Tree<E> tree) {
        Tree<E> currentNode;
        ArrayList<Tree<E>> queue = new ArrayList<>();
        queue.add(tree);
        while (!queue.isEmpty()) {
            currentNode = queue.get(0);
            nodes.add(currentNode.value());
            queue.remove(0);
            queue.addAll(currentNode.getSons());
        }
    }

    /**
     * checks if next element exist.
     *
     * @return true/false if next element exists or not
     */
    @Override
    public boolean hasNext() {
        return pointer <= nodes.size() - 1;
    }
    /**
     * returns next element.
     *
     * @return next element
     */

    @Override
    public E next() {
        return nodes.get(pointer++);
    }
}

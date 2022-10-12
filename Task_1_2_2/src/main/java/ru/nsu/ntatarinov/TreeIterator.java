package ru.nsu.ntatarinov;

import java.util.ArrayList;
import java.util.Iterator;

public class TreeIterator<E> implements Iterator {

    private ArrayList<E> nodes;
    int pointer;

    public TreeIterator(Tree<E> tree, String type) {
        nodes = new ArrayList<>();
        pointer = 0;
        if (type.equals("DFS")) {
            dfs(tree);
        } else {
            bfs(tree);
        }
    }

    private void dfs(Tree<E> tree) {
        nodes.add(tree.value());
        for (int i = 0; i < tree.getSons().size(); i++) {
            dfs(tree.getSons().get(i));
        }
    }

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

    @Override
    public boolean hasNext() {
        return pointer <= nodes.size() - 1;
    }

    @Override
    public E next() {
        return nodes.get(pointer++);
    }
}

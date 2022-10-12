package ru.nsu.ntatarinov;


import java.util.ArrayList;
import java.util.Iterator;

public class Tree<E> implements Iterable<E> {

    private E value;
    private Tree<E> parent;
    private final ArrayList<Tree<E>> sons;

    public Tree() { //создает пустое дерево
        this.value = null;
        this.sons = new ArrayList<>();
        this.parent = null;
    }

    public Tree(E value) {
        this.value = value;
        this.sons = new ArrayList<>();
        this.parent = null;
    }

    public Tree<E> add(E value) {
        if (this.value == null) {
            this.value = value;
            return this;
        } else {
            Tree<E> newSon = new Tree<>(value);
            newSon.parent = this;
            sons.add(newSon);
            return newSon;
        }
    }

    public Tree<E> add(Tree<E> tree, E value) {
        Tree<E> addedNode = null;
        Tree<E> son;
        if (tree == this) {
            return this.add(value);
        } else {
            for (Tree<E> etree : sons) {
                son = etree;
                addedNode = son.add(tree, value);
                if (addedNode != null) {
                    break;
                }
            }
            return addedNode;
        }
    }

    public boolean remove(Tree<E> tree) {
        if (sons.contains(tree)) {
            sons.remove(tree);
            return true;
        } else {
            for (Tree<E> etree : sons) {
                if (etree.remove(tree)) {
                    return true;
                }
            }
            return false;
        }
    }

    public Tree<E> getNodeByValue(E value) {
        if (this.sonsValues().contains(value)) {
            int ind = this.sonsValues().indexOf(value);
            return sons.get(ind);
        } else {
            for (Tree<E> son : sons) {
                Tree<E> node = son.getNodeByValue(value);
                if (node != null) {
                    return node;
                }
            }
            return null;
        }
    }

    public int size() {
        return sons.size();
    }

    public Tree<E> getParent() {
        return this.parent;
    }

    public E value() {
        return value;
    }

    public ArrayList<E> sonsValues() {
        ArrayList<E> values = new ArrayList<>();
        for (Tree<E> son : sons) {
            values.add(son.value());
        }
        return values;
    }

    public ArrayList<Tree<E>> getSons() {
        return this.sons;
    }

    private String typeOfIterator;

    public Iterator<E> dfsIterator() {
        this.typeOfIterator = "DFS";
        return this.iterator();
    }

    public Iterator<E> bfsIterator() {
        this.typeOfIterator = "BFS";
        return this.iterator();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Iterator<E> iterator() {
        return (Iterator<E>) new TreeIterator<>(this, typeOfIterator);
    }
}

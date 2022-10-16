package ru.nsu.ntatarinov;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * My tree collection implementation.
 *
 * @param <E> type of elements tree contains
 */
public class Tree<E> implements Iterable<E> {

    /**
     * value stored in node of tree.
     */
    private E value;
    /**
     * link to parent node.
     */
    private Tree<E> parent;
    public int numOfOperations;
    /**
     * array of son nodes.
     */
    private final ArrayList<Tree<E>> sons;

    /**
     * typeOfIterator = "BFS"/"DFS". Used for Iterator method
     */
    public enum TypeOfIterator {DFS, BFS}

    /**
     * builds an empty tree with null value.
     */
    public Tree() {
        this.value = null;
        this.sons = new ArrayList<>();
        this.parent = null;
        this.numOfOperations = 0;
    }

    /**
     * build a tree with values equal to value argument.
     *
     * @param value value in node
     */
    public Tree(E value) {
        this.value = value;
        this.sons = new ArrayList<>();
        this.parent = null;
        this.numOfOperations = 0;
    }

    /**
     * Add node to sons with value equal value. if current tree doesn't contain any value add method
     * stores value to this.value
     *
     * @param value value you want to save in new node
     * @return link to a created subtree
     */
    public Tree<E> add(E value) {
        numOfOperations++;
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

    /**
     * add node with value equal value to subtree equal tree.
     *
     * @param tree  subtree in which you want to add a node
     * @param value a value of a new node
     * @return null if subtree equal to tree wasn't found, else link to added node
     */
    public Tree<E> add(Tree<E> tree, E value) {
        numOfOperations++;
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

    /**
     * removes a subtree equal to tree. sons of this subtree are left untouched and removed by
     * garbage collector
     *
     * @param tree link to tree you want to remove
     * @return true if removing is successful and false if such subtree wasn't found
     */
    public boolean remove(Tree<E> tree) {
        numOfOperations++;
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

    /**
     * method to get a node with value equal value.
     *
     * @param value value of node, which you want to receive
     * @return link to a found node or null if node wasn't found
     */
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

    /**
     * returns size of tree.
     *
     * @return size of tree
     */
    public int size() {
        return sons.size();
    }

    /**
     * returns a parent of current subtree, else null if current node is a root.
     *
     * @return parent of a node
     */
    public Tree<E> getParent() {
        return this.parent;
    }

    /**
     * value stored in value field.
     *
     * @return value stored in value field
     */
    public E value() {
        return value;
    }

    /**
     * list of values of son nodes.
     *
     * @return list of values of son nodes
     */
    public ArrayList<E> sonsValues() {
        ArrayList<E> values = new ArrayList<>();
        for (Tree<E> son : sons) {
            values.add(son.value());
        }
        return values;
    }

    /**
     * list of son nodes.
     *
     * @return list of son nodes
     */
    public ArrayList<Tree<E>> getSons() {
        return this.sons;
    }

    /**
     * returns an iterator with the "type" traversal type.
     *
     * @return iterator with the "type" traversal type
     */
    public Iterator<E> iterator(TypeOfIterator type) {
        return (Iterator<E>) new TreeIterator<>(this, type);
    }

    /**
     * dfs iterator for tree.
     *
     * @return Iterator object
     */
    @Override
    public Iterator<E> iterator() {
        return (Iterator<E>) new TreeIterator<>(this, TypeOfIterator.DFS);
    }
}

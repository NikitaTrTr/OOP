package ru.nsu.ntatarinov;



public class TestRemoveMethod {

    public static void main(String[] args) {

        Tree<String> tree = new Tree<>("A");
        tree.add("A");
        Tree<String> treeB = tree.add("B");
        Tree<String> treeC = treeB.add("C");
        treeC.add("D");
        Tree<String> treeE = treeC.add("E");

        System.out.println(treeC.sonsValues());
        System.out.println(tree.remove(treeE));
        System.out.println(treeC.sonsValues());
    }
}

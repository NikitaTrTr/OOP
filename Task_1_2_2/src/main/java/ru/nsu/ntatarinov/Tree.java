package ru.nsu.ntatarinov;


import java.util.ArrayList;

public class Tree<E> {
    private E value;
    private final ArrayList<Tree<E>> sons;
    public Tree(){ //создает пустое дерево
        this.value = null;
        this.sons =  new ArrayList<>();
    }

    public Tree(E value){// создает пустое дерево с инициализированным корнем
        this.value = value;
        this.sons =  new ArrayList<>();
    }

    public Tree<E> add(E node){//добавляет ноду в сыновья, если корень проинициализирован, иначе заполняет нодой корень
        if (value == null){
            this.value = node;
            return this;
        }
        else{
            Tree<E> newSon = new Tree<>(node);
            sons.add(newSon);
            return newSon;
        }
    }
    public Tree<E> add(Tree<E> tree, E node){//добавляет ноду в поддерево tree
        Tree<E> addedNode = null;
        Tree<E> son;
        if (tree==this){
            return this.add(node);
        }
        else{
            for (Tree<E> eTree : sons) {
                son = eTree;
                addedNode = son.add(tree, node);
                if (addedNode != null) {
                    break;
                }
            }
            return addedNode;
        }
    }
    public boolean remove(Tree<E> tree){//удаляет поддерево tree
        if (sons.contains(tree)){
            sons.remove(tree);
            return true;
        }
        else {
            for (Tree<E> eTree : sons){
                if (eTree.remove(tree)){
                    return true;
                }
            }
            return false;
        }
    }
    public int size(){
        return sons.size();
    }
    public E value(){
        return value;
    }
    public ArrayList<E> sonsValues(){// список корневых значений всех сыновей
        ArrayList<E> values = new ArrayList<>();
        for (Tree<E> son : sons) {
            values.add(son.value());
        }
        return values;
    }
}

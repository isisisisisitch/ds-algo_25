package ca.bytetube._07_avl;


import ca.bytetube._07_avl.printer.BinaryTrees;

public class Main {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.add(7);
        tree.add(4);
        tree.add(9);
        tree.add(5);
        tree.add(13);
        tree.add(12);
        tree.add(14);
        tree.add(10);

        BinaryTrees.println(tree);

    }
}

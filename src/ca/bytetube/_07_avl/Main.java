package ca.bytetube._07_avl;


import ca.bytetube._07_avl.printer.BinaryTrees;

public class Main {
    public static void main(String[] args) {
//        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
//        tree.add(7);
//        tree.add(4);
//        tree.add(9);
//        tree.add(5);
//        tree.add(13);
//        tree.add(12);
//        tree.add(14);
//        tree.add(10);
//
//        BinaryTrees.println(tree);

        AVLTree<Integer> tree2 = new AVLTree<>();
        tree2.add(11);
        tree2.add(6);
        tree2.add(15);
        tree2.add(4);
        tree2.add(8);
        //tree2.add(14);
        tree2.add(16);
        tree2.add(9);
       // tree2.add(12);

        BinaryTrees.println(tree2);
        tree2.remove(16);
        System.out.println("after remove:");
        BinaryTrees.println(tree2);

    }
}

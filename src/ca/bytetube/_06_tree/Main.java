package ca.bytetube._06_tree;

import ca.bytetube._06_tree.BinaryTree.Node;
import ca.bytetube._06_tree.file.Files;
import ca.bytetube._06_tree.printer.BinaryTrees;
import org.junit.Test;

public class Main {
    public static void main(String[] args) {
        test0();


    }

    @Test
    public void test2() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(7);
        bst.add(4);
        bst.add(9);
        bst.add(2);
        bst.add(5);
        bst.add(8);
        bst.add(11);
        bst.add(1);
        bst.add(3);
        bst.add(12);

        BinaryTrees.print(bst);
        System.out.println();

        bst.remove(7);
        BinaryTrees.print(bst);
    }

    @Test
    public void test1() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(7);
        bst.add(4);
        bst.add(9);
        bst.add(2);
        bst.add(5);
        bst.add(8);
        bst.add(11);
        bst.add(1);
        bst.add(3);
        bst.add(12);


        String s = BinaryTrees.printString(bst);
        Files.writeToFile("/Users/dalluo/Desktop/test.txt","\n",true);
        Files.writeToFile("/Users/dalluo/Desktop/test.txt",s,true);
        Files.writeToFile("/Users/dalluo/Desktop/test.txt","\n",true);


    }


    public static void test0() {
        Node root = new Node<>(7);
        root.left = new Node(4);
        root.right = new Node(9);
        root.left.left = new Node(2);
        root.left.right = new Node(5);
        root.right.left = new Node(8);
        root.right.right = new Node(11);
        root.left.left.left = new Node(1);
        root.left.left.right = new Node(3);
        root.right.right.left = new Node(10);
        root.right.right.right = new Node(12);

        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        System.out.println(binaryTree.height(root));


    }

}

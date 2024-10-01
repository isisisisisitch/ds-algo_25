package ca.bytetube._07_avl;

import java.util.Comparator;

public class BinarySearchTree<E> extends BinaryTree<E> {
    public BinarySearchTree() {
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    private Comparator<E> comparator;

    public void afterAdd(Node<E> node) {

    }

    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }

    public void add(E element) {
        if (element == null) throw new IllegalArgumentException("element can not be null!");
        //1.添加第一个节点
        if (root == null) {
//            root = new Node<>(element, null);
            root = createNode(element, null);
            size++;
            afterAdd(root);
            return;
        }

        //2.添加的不是第一个节点
        Node<E> node = root;
        int cmp = 0;
        Node<E> parent = root;
        while (node != null) {
            cmp = compare(element, node.element);
            parent = node;
            if (cmp > 0) node = node.right;
            else if (cmp < 0) node = node.left;
            else {
                node.element = element;
                return;
            }
        }

        //3.出了loop一定找到了插入点，则把待插入的节点加入到指定位置上
//        Node<E> newNode = new Node<>(element, parent);
        Node<E> newNode = createNode(element, parent);
        if (cmp > 0) parent.right = newNode;
        else parent.left = newNode;

        size++;
        afterAdd(newNode);

    }

    public void remove(E element) {
        Node<E> node = node(element);
        remove(node);
    }

    private void remove(Node<E> node) {
        //3.删除degree = 2；
        if (node.hasTwoChildren()) {
            Node<E> s = successor(node);
            node.element = s.element;
            node = s;
        }

        //2.删除degree = 1；
        Node<E> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) {
            replacement.parent = node.parent;
            if (node == root) root = replacement;
            else {
                if (node == node.parent.left) node.parent.left = replacement;
                else node.parent.right = replacement;
            }

        }

        //1.删除degree = 0；
        else if (node.parent == null) root = null;
        else {
            if (node == node.parent.left) node.parent.left = null;
            else node.parent.right = null;
        }


    }


    private Node<E> node(E element) {
        Node<E> node = root;
        int cmp = 0;

        while (node != null) {
            cmp = compare(element, node.element);

            if (cmp > 0) node = node.right;
            else if (cmp < 0) node = node.left;
            else {
                node.element = element;
                return node;
            }
        }

        return null;
    }


    public boolean contains(E element) {
        return false;
    }

    private int compare(E e1, E e2) {
        if (comparator != null) return comparator.compare(e1, e2);
        return ((Comparable<E>) e1).compareTo(e2);
    }
}

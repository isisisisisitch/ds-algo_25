package ca.bytetube._07_avl;


import java.util.Comparator;

public class AVLTree<E> extends BinarySearchTree<E> {
    public AVLTree() {
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode) node).balanceFactor()) <= 1;
    }

    @Override
    public void afterAdd(Node<E> node) {

        while ((node = node.parent) != null) {
            //tree is balanced
            if (isBalanced(node)) {
                calculateHeight(node);
            } else {  //not balanced
                rebalance2(node);
                break;
            }
        }

    }

    @Override
    public void afterRemove(Node<E> node) {

        while ((node = node.parent) != null) {
           //tree is balanced
            if (isBalanced(node)) {
                calculateHeight(node);
            } else {  //not balanced
                rebalance2(node);
                // break; 不能加break，因为我们需要把所有可能失衡的祖先节点都调平

            }
        }
    }


    private void calculateHeight(Node<E> node) {
        ((AVLNode) node).calculateHeight();
    }


    private void rebalance2(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) {//L
            if (node.isLeftChild()) {//L
                rotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
            } else {//R
                rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
            }

        } else {//R
            if (node.isLeftChild()) {//L
                rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
            } else {//R
                rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
            }

        }
    }

    private void rotate(Node<E> r,//用来指定原来子树的root，用r的parent作为d的parent
                        Node<E> a, Node<E> b, Node<E> c,
                        Node<E> d, Node<E> e, Node<E> f, Node<E> g) {

        //d是新子树的根结点
        d.parent = r.parent;
        if (r.isLeftChild()) r.parent.left = d;
        else if (r.isRightChild()) r.parent.right = d;
        else root = d;

        //a--b--c
        b.left = a;
        if (a != null) a.parent = b;
        b.right = c;
        if (c != null) c.parent = b;
        calculateHeight(b);

        //e--f--g
        f.left = e;
        if (e != null) e.parent = f;
        f.right = g;
        if (g != null) g.parent = f;
        calculateHeight(f);

        //b--d--f
        d.left = b;
        b.parent = d;
        d.right = f;
        f.parent = d;
        calculateHeight(d);

    }

    private void rebalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) {//L
            if (node.isLeftChild()) {//L
                rightRotation(grand);
            } else {//R
                leftRotation(parent);
                rightRotation(grand);
            }

        } else {//R
            if (node.isLeftChild()) {//L
                rightRotation(parent);
                leftRotation(grand);
            } else {//R
                leftRotation(grand);
            }

        }
    }

    private void rightRotation(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;

        // update parent,height
        afterRotation(grand, parent, child);

    }


    private void leftRotation(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;

        // update parent,height
        afterRotation(grand, parent, child);
    }

    private void afterRotation(Node<E> grand, Node<E> parent, Node<E> child) {
        parent.parent = grand.parent;
        if (grand.isLeftChild()) grand.parent.left = parent;
        else if (grand.isRightChild()) grand.parent.right = parent;
            //grand is root
        else root = parent;

        if (child != null) child.parent = grand;
        grand.parent = parent;

        calculateHeight(grand);
        calculateHeight(parent);
    }


    private static class AVLNode<E> extends Node<E> {
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        public int balanceFactor() {
            int leftHeight = this.left == null ? 0 : ((AVLNode<E>) this.left).height;
            int rightHeight = this.right == null ? 0 : ((AVLNode<E>) this.right).height;
            return leftHeight - rightHeight;
        }

        private void calculateHeight() {
            int leftHeight = this.left == null ? 0 : ((AVLNode<E>) this.left).height;
            int rightHeight = this.right == null ? 0 : ((AVLNode<E>) this.right).height;
            this.height = Math.max(leftHeight, rightHeight) + 1;
        }

        private Node<E> tallerChild() {
            int leftHeight = this.left == null ? 0 : ((AVLNode<E>) this.left).height;
            int rightHeight = this.right == null ? 0 : ((AVLNode<E>) this.right).height;
            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;
            return this == this.parent.left ? left : right;
        }

    }


}

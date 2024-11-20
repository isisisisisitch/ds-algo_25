package ca.bytetube._06_tree;


import ca.bytetube._06_tree.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<E> implements BinaryTreeInfo {
    protected Node<E> root;
    protected int size;


    public static abstract class Visitor<E> {
        boolean stop;

        public abstract boolean visit(E element);

    }

    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        root = null;
    }

    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    public void preOrderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        preOrderTraversal0(root, visitor);
    }


    public void preOrderTraversal0(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) return;
        //System.out.print(node.element + " ");
        visitor.stop = visitor.visit(node.element);
        preOrderTraversal0(node.left, visitor);
        preOrderTraversal0(node.right, visitor);
    }

    //有右先压右，有左再压左
    public void preOrderTraversal(Node<E> node) {
        if (node == null) return;
        Stack<Node<E>> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            Node<E> pop = stack.pop();
            System.out.print(pop.element + " ");
            if (pop.right != null) stack.push(pop.right);
            if (pop.left != null) stack.push(pop.left);
        }


    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    public void inOrderTraversal0(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) return;

        inOrderTraversal0(node.left, visitor);
        //System.out.print(node.element + " ");
        visitor.stop = visitor.visit(node.element);
        inOrderTraversal0(node.right, visitor);
    }

    //有左一直压左，否则弹出栈顶元素判断当前节点是否有右，如果有右再压右
    public void inOrderTraversal(Node<E> node) {
        if (node != null) {
            Stack<Node<E>> stack = new Stack<>();
            while (!stack.isEmpty() || node != null) {
                if (node != null) {
                    stack.push(node);
                    node = node.left;
                } else {
                    Node<E> pop = stack.pop();
                    System.out.print(pop.element + " ");
                    node = pop.right;

                }

            }

        }


    }

    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    public void postOrderTraversal0(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) return;

        postOrderTraversal0(node.left, visitor);

        postOrderTraversal0(node.right, visitor);

        //System.out.print(node.element + " ");
        visitor.stop = visitor.visit(node.element);
    }

    public void postOrderTraversal(Node<E> node) {
        if (node == null) return;
        Stack<Node<E>> stack = new Stack<>();
        Stack<Node<E>> helpStack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node<E> pop = stack.pop();
            helpStack.push(pop);
            if (pop.left != null) stack.push(pop.left);
            if (pop.right != null) stack.push(pop.right);
        }

        while (!helpStack.isEmpty()) System.out.print(helpStack.pop().element + " ");
    }

    public void levelOrderTraversal() {
        levelOrderTraversal(root);
    }

    public void levelOrderTraversal(Node<E> node) {
        if (node == null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            System.out.print(poll.element + " ");
            if (poll.left != null) queue.offer(poll.left);
            if (poll.right != null) queue.offer(poll.right);
        }
    }


    public int height() {
        return height(root);
    }

    public int height0(Node<E> node) {
        if (node == null) return 0;
        return Math.max(height0(node.left), height0(node.right)) + 1;

    }

    public int height(Node<E> node) {

        if (node == null) return 0;
        Queue<Node<E>> queue = new LinkedList<>();

        queue.offer(node);
        int height = 0;
        int levelSize = 1;
        while (!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            levelSize--;
            if (poll.left != null) queue.offer(poll.left);
            if (poll.right != null) queue.offer(poll.right);

            if (levelSize == 0) {//意味着当前层遍历结束，即将遍历下一层 码值
                height++;
                levelSize = queue.size();
            }
        }
        return height;

    }

    public boolean isComplete() {
        return isComplete(root);
    }


    public boolean isComplete(Node<E> head) {
        if (head == null) return false;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        boolean isLeaf = false;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (isLeaf && !node.isLeafNode()) return false;
            //1.If node.left!=null && node.right!=null,add node.left and node.right to the queue
            if (node.hasTwoChildren()) {
                queue.offer(node.left);
                queue.offer(node.right);
            }

            //2.If node.left==null && node.right!=null，return false
            else if (node.left == null && node.right != null) return false;

                //3.If node.left!=null && node.right==null,Then the nodes traversed later should all be leaf nodes
            else if (node.left != null && node.right == null) {
                queue.offer(node.left);
                isLeaf = true;

            }
            //4.node.left==null && node.right==null Then the nodes traversed later should all be leaf nodes
            else {
                isLeaf = true;
            }

        }
        return true;
    }

    public Node<E> predecessor(Node<E> node) {
        if (node == null) return null;
        //1.node.left != null
        Node<E> left = node.left;
        if (left != null) {
            while (left.right != null) left = left.right;
            return left;
        }

        //2.node.left == null && node.parent != null
        while (node.parent != null && node == node.parent.left) node = node.parent;
        return node.parent;
    }

    public Node<E> successor(Node<E> node) {
        if (node == null) return null;
        //1.node.right != null
        Node<E> right = node.right;
        if (right != null) {
            while (right.left != null) right = right.left;
            return right;
        }

        //2.node.right == null && node.parent != null
        while (node.parent != null && node == node.parent.right) node = node.parent;
        return node.parent;
    }


    public static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;


        public Node() {
        }

        public Node(E element) {
            this.element = element;

        }

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }


        protected boolean hasTwoChildren() {
            return this.left != null && this.right != null;
        }

        protected boolean isLeafNode() {
            return this.left == null && this.right == null;
        }

        @Override
        public String toString() {
            return "element=" + element;
        }
    }


    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>) node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.element.toString();
        }

        return myNode.element + "_p(" + parentString + ")";
    }

}

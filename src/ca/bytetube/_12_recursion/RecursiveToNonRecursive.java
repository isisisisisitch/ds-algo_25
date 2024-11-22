package ca.bytetube._12_recursion;

import java.util.Stack;

public class RecursiveToNonRecursive {
    public static void main(String[] args) {
        log1(4);
    }

    static void log(int n) {
        if (n < 1) return;
        log(n - 1);
        int v = n + 10;
        System.out.println(v);
    }

    static void log1(int n) {
        Stack<Frame> stack = new Stack<>();
        //模拟系统压栈
        while (n > 0) {
            stack.push(new Frame(n, n + 10));
            n--;
        }

        //模拟系统退栈
        while (!stack.isEmpty()) {
            Frame frame = stack.pop();
            System.out.println(frame.v);
        }

    }

    static void log2(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.println(n + 10);
        }
    }

    private static class Frame {
        int n;
        int v;

        Frame(int n, int v) {
            this.n = n;
            this.v = v;
        }
    }

}

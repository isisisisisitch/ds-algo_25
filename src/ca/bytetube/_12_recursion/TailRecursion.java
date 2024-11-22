package ca.bytetube._12_recursion;

public class TailRecursion {
    public static void main(String[] args) {
        System.out.println(factorial0(5));
        System.out.println(factorial(5));
    }

    public static int factorial0(int n) {
        if (n <= 1) return n;
        return n * factorial0(n - 1);
    }

    public static int factorial(int n) {
        if (n <= 1) return n;
        return factorial(n, 1);
    }

    public static int factorial(int n, int res) {
        if (n <= 1) return res;
        return factorial(n - 1, res * n);
    }


    public void test1() {
        int a = 10;
        int b = a + 10;
        test2();
        //int c = a + b;
    }

    public void test2() {
        int x = 100;
        int y = 200;
    }
}

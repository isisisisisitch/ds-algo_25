package ca.bytetube._01_complexity;

public class Main {
    public static void main(String[] args) {

    }


    public static void test1(int n) { //6
        if (n > 10) {
            System.out.println("n > 10");
        } else if (n > 5) {
            System.out.println("n > 5");
        } else if (n > 2) {
            System.out.println("n > 2");
        } else {
            System.out.println("n< 2");
        }

        //1
        //1 + 4 + 4 + 4 = 13 ~ O(1)
        for (int i = 0; i < 4; i++) {
            System.out.println("test1");
        }

    }

    public static void test2(int n) {
        //1 + n + n*1 + n = 3n + 1 ~O(n)
        for (int i = 0; i < n; i++) {
            System.out.println("test2");
        }
    }

    public static void test3(int n) {
        //1 +n+  n*(3n + 1) + n = 3n ^ 2 + 3n + 1 = O(n^2)
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {
                System.out.println("test3");
            }

        }
    }

    /**
     *n = 16
     * i = 1
     *
     * i = 2  = 2 ^ 1
     * i = 4 = 2^ 2
     * i = 8 = 2^ 3
     * i = 16=2^4
     *
     */
    public static void test4(int n) {
        //1 + log2n + log2n(3n + 1) + log2n = (3n+3)log2n + 1 = O(nlogn)
        for (int i = 1; i < n; i *= 2) {
            //3n + 1
            for (int j = 0; j < n; j++) {
                System.out.println("test4");
            }
        }
    }


}

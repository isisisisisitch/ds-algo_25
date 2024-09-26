package ca.bytetube._01_complexity;

public class Fib {
    public static void main(String[] args) {
        System.out.println(fib(4));
    }

    //递归过程中，数据共享的方法：
    //1.容器作为递归方法的参数进行传递
    //2.把容器改为成员变量
    public static int fib(int n) {
        if (n <= 1) return n;

        int first = 0;
        int second = 1;
        for (int i = 0; i < n - 1; i++) {
            int sum = first + second;
            first = second;
            second = sum;
        }

        return second;

    }


    public static int fib1(int n) {
        if (n <= 1) return n;
        //在arr中记录了fib(n)所对应的值
        int[] arr = new int[n + 1];

        arr[1] = 1;
        arr[2] = 1;


        return fib(n, arr);
    }

    private static int fib(int n, int[] arr) {

        if (arr[n] == 0) {
            arr[n] = fib(n - 1, arr) + fib(n - 2, arr);
        }

        return arr[n];
    }


    /**
     * F(0) = 0, F(1) = 1
     * F(n) = F(n - 1) + F(n - 2), for n > 1.
     * <p>
     * n = 5
     * fib(5) = fib(4) + fib(3)
     * fib(4) = fib(3) + fib(2)
     * fib(3) = fib(2) + fib(1)
     * fib(2) = fib(1) + fib(0) =1 + 0 = 1
     */
    public static int fib0(int n) {
        if (n <= 1) return n;

        return fib0(n - 1) + fib0(n - 2);
    }

}

package ca.bytetube._12_recursion;

public class NumsOfCows {
    public int numsOfCows0(int n) {
        if (n <= 4) return n;
        return numsOfCows0(n - 1) + numsOfCows0(n - 3);
    }

    public int numsOfCows(int n) {
        if (n <= 1) return n;
        int[] arr = new int[n + 1];

        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 3;
        arr[4] = 4;

        return numsOfCows(n, arr);

    }

    private int numsOfCows(int n, int[] arr) {
        if (arr[n] == 0) {
            arr[n] = numsOfCows(n - 1, arr) + numsOfCows(n - 3, arr);
        }


        return arr[n];

    }
}

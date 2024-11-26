package ca.bytetube._14_divideandconquer;

public class LeftSum {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 5};
        System.out.println(leftSum(arr));

    }


    public static int leftSum(int[] arr) {

        return leftSum(arr, 0, arr.length - 1);
    }

    private static int leftSum(int[] arr, int l, int r) {
        if (l == r) return 0;
        int mid = (l + r) >> 1;
        return leftSum(arr, l, mid) + leftSum(arr, mid + 1, r) + merge(arr, l, mid, r);

    }

    private static int merge(int[] arr, int l, int mid, int r) {
        int p1 = l, p2 = mid + 1;
        int[] help = new int[r - l + 1];
        int i = 0;
        int sum = 0;
        while (p1 <= mid && p2 <= r) {
            sum += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) help[i++] = arr[p1++];
        while (p2 <= r) help[i++] = arr[p2++];

        for (int j = 0; j < help.length; j++) {
            arr[l + j] = help[j];
        }

        return sum;

    }

}

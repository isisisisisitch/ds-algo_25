package ca.bytetube._14_divideandconquer;


public class GetMaxValueFromArray {

    public static void main(String[] args) {
        int[] arr = {10, 3, 6, -6, 100, 32, 15, 79};
        System.out.println(getMaxValue(arr));
    }
    //[ )
    public static int getMaxValue(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("arr is null or empty");
        if (arr.length == 1) return arr[0];
        return getMaxValue(arr, 0, arr.length - 1);
    }

    private static int getMaxValue(int[] arr,int left,int right) {

        if (left == right) return arr[left];
        int mid = (left + right) >> 1;
        int leftMaxValue = getMaxValue(arr, left, mid);
        int rightMaxValue = getMaxValue(arr, mid + 1, right);
        return Math.max(leftMaxValue, rightMaxValue);
    }

}

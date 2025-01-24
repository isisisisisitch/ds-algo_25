package ca.bytetube._19_sort;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {2, 4, 8, 8, 8, 12, 14};
       // System.out.println(indexOf(arr, 3));
        System.out.println(search(arr, 8));
    }

    //查找某个元素在有序数组中的插入位置
    public static int search(int[] array, int value) {
        if (array == null || array.length == 0) return -1;
        int begin = 0;
        int end = array.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;

            if (array[mid] > value) end = mid;
            else begin = mid + 1;
        }

        return begin;
    }
    //查找某个元素在有序数组中的下标
    public static int indexOf(int[] array, int value) {
        if (array == null || array.length == 0) return -1;
        int begin = 0;
        int end = array.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (array[mid] == value) return mid;
            else if (array[mid] > value) end = mid;
            else begin = mid + 1;


        }

        return -1;
    }


}

package ca.bytetube._19_sort.uncmp;

import java.util.Arrays;

public class CountingSort {
    public static void main(String[] args) {
        Integer[] array = {7, -3, 5, 8, 6, 7, 4, 5};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void sort(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr.length == 1) return;
        //1.从原数组中找到最小值，最大值
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) max = arr[i];
            if (min > arr[i]) min = arr[i];
        }
        //2.1创建counts数组用来统计原数组中每个元素出现的次数
        int[] counts = new int[max - min + 1];

        for (int i = 0; i < arr.length; i++) counts[arr[i] - min]++;

        //2.2统计出现次数的累积和
        for (int i = 1; i < counts.length; i++) counts[i] += counts[i - 1];

        //3.从右向左遍历原数组，从counts中将每个遍历元素放入到新数组中
        int[] newArr = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            newArr[--counts[arr[i] - min]] = arr[i];
        }
        //4.再将newArr中的数据放回到arr中
        for (int i = 0; i < newArr.length; i++) {
            arr[i] = newArr[i];
        }

    }


    public static void sort1(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr.length == 1) return;
        //1.从原数组中找到最大值
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) max = arr[i];
        }

        //2.创建counts数组用来统计原数组中每个元素出现的次数
        int[] counts = new int[max + 1];
        for (int i = 0; i < arr.length; i++) counts[arr[i]]++;

        //3.利用counts数组中非零值的下标出现的次数，对原数组排序
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i]-- > 0) {
                arr[index++] = i;
            }

        }


    }

}

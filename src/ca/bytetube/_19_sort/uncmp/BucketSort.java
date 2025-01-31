package ca.bytetube._19_sort.uncmp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BucketSort {
    public static void main(String[] args) {
        Double[] arr = {0.34, 0.47, 0.29, 0.84, 0.45, 0.38, 0.35, 0.76};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(Double[] arr) {
        if (arr == null || arr.length == 0 || arr.length == 1) return;
        // 1.创建一定数量的桶（桶可以用数组或者链表）
        List<Double>[] buckets = new List[arr.length];
        // 2.按照一定的规则，将数组中元素尽可能均匀地分配到不同的桶中
        for (int i = 0; i < arr.length; i++) {
            //规则：元素值*桶的数量
            int bucketIndex = (int) (arr[i] * buckets.length);
            List<Double> bucket = buckets[bucketIndex];
            if (bucket == null) {
                bucket = new ArrayList<>();
                buckets[bucketIndex] = bucket;
            }
            bucket.add(arr[i]);
        }
        int index = 0;
        // 3.分别对每个桶进行单独的排序
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] == null) continue;
            buckets[i].sort(null);
            // 4.将所有的非空桶合并成有序数组
            for (Double d : buckets[i]) {
                arr[index++] = d;
            }
        }


    }
}

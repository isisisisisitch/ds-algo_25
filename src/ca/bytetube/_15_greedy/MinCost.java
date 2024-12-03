package ca.bytetube._15_greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinCost {

    public static void main(String[] args) {
        System.out.println(minCost(new int[]{1, 2, 6, 4, 3, 7, 1, 8}));
    }

    public static int minCost(int[] arr) {
        int cost = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        for (int data : arr) queue.add(data);
        while (queue.size() > 1) {
            int subCost = queue.poll() + queue.poll();
            cost += subCost;
            queue.add(subCost);
        }
        return cost;
    }

    public static int minCost1(int[] arr) {
        Arrays.sort(arr);
        int cost = 0;

        for (int i = arr.length - 1; i > 0; i--) {
            int subCost = sum(arr, i);
            cost += subCost;
        }

        return cost;
    }


    public static int sum(int[] arr, int len) {
        int res = 0;
        for (int i = len; i >= 0; i--) {
            res += arr[i];
        }

        return res;
    }
}

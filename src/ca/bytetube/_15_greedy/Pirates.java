package ca.bytetube._15_greedy;

import java.util.Arrays;

public class Pirates {
    public static void main(String[] args) {
        System.out.println(pirates(30,new int[]{3, 5, 4, 10, 7, 14, 2, 11}));
    }


    public static int pirates(int W, int[] weights) {
        Arrays.sort(weights);
        int count = 0;
        int weight = 0;
        for (int i = 0; i < weights.length; i++) {
            int newWeight = weights[i] + weight;
            if (newWeight <= W) {
                weight = newWeight;
                count++;
            }
        }


        return count;

    }
}

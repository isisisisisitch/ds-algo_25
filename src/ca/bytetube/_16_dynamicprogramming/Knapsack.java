package ca.bytetube._16_dynamicprogramming;

public class Knapsack {
    public static void main(String[] args) {
        int[] values = {6, 3, 5, 4, 6};
        int[] weights = {2, 2, 6, 5, 4};
        int W = 10;
        System.out.println(knapsack(values, weights, W));
    }


    public static int knapsack(int[] values, int[] weights, int W) {
        int[] dp = new int[W + 1];
        for (int i = 1; i <= values.length; i++) {
            for (int j = W; j >= weights[i - 1]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + values[i - 1]);
            }
        }

        return dp[W];
    }

    public static int knapsack2(int[] values, int[] weights, int W) {
        int[] dp = new int[W + 1];
        for (int i = 1; i <= values.length; i++) {
            for (int j = W; j >= 1; j--) {
                if (weights[i - 1] > j) dp[j] = dp[j];
                else dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + values[i - 1]);
            }
        }

        return dp[W];
    }


    public static int knapsack1(int[] values, int[] weights, int W) {
        int[][] dp = new int[values.length + 1][W + 1];
        for (int i = 1; i <= values.length; i++) {
            for (int j = 1; j <= W; j++) {
                if (weights[i - 1] > j) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
            }
        }

        return dp[values.length][W];
    }
}

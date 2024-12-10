package ca.bytetube._16_dynamicprogramming;

public class FindAimInArray {
    public static void main(String[] args) {
        System.out.println(findAimInArray(new int[]{2, 3, 5}, 9));
    }

    public static boolean findAimInArray(int[] arr, int aim) {
        boolean[][] dp = new boolean[arr.length + 1][aim + 1];//false
        for (int i = 0; i < dp.length; i++) dp[i][aim] = true;
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = aim - 1; j >= 0; j--) {
//                if (dp[i + 1][j] == true) {
//                    dp[i][j] = true;
//                }

                dp[i][j] = dp[i + 1][j];
                if (j + arr[i] <= aim )  dp[i][j] = dp[i + 1][j] || dp[i + 1][j + arr[i]];
            }
        }

        return dp[0][0];
    }


    public static boolean findAimInArray1(int[] arr, int aim) {
        return findAimInArray1(arr, aim, 0, 0);
    }

    private static boolean findAimInArray1(int[] arr, int aim, int index, int sum) {

        if (sum == aim) return true;
        if (index == arr.length) return false;

        return findAimInArray1(arr, aim, index + 1, sum + arr[index]) ||
                findAimInArray1(arr, aim, index + 1, sum);

    }
}

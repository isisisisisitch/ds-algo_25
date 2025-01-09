package ca.bytetube._16_dynamicprogramming;

public class LongestCommonSubstring {
    public static void main(String[] args) {
        String s1 = "ABCBA";
        String s2 = "BABCA";
        System.out.println(longestCommonSubstring(s1, s2));
    }

    public static int longestCommonSubstring(String s1, String s2) {
        char[] nums1 = s1.toCharArray();
        char[] nums2 = s2.toCharArray();

        char[] rowsNums = nums1, colsNums = nums2;
        if (nums1.length < nums2.length) {
            colsNums = nums1;
            rowsNums = nums2;
        }

        int[] dp = new int[colsNums.length + 1];
        int max = 0;
        for (int i = 1; i <= rowsNums.length; i++) {
            int cur = 0;
            for (int j = 1; j <= colsNums.length; j++) {
                int leftTop = cur;
                cur = dp[j];
                if (rowsNums[i - 1] == colsNums[j - 1]) {
                    dp[j] = leftTop + 1;
                    max = Math.max(max, dp[j]);
                } else {
                    dp[j] = 0;
                }
            }
        }


        return max;
    }

    public static int longestCommonSubstring1(String s1, String s2) {
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();

        int[][] dp = new int[ch1.length + 1][ch2.length + 1];
        int max = 0;
        for (int i = 1; i <= ch1.length; i++) {
            for (int j = 1; j <= ch2.length; j++) {
                if (ch1[i - 1] != ch2[j - 1]) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }


        return max;
    }
}

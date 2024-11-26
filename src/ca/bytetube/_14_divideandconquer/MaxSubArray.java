package ca.bytetube._14_divideandconquer;

/**
 * https://leetcode.com/problems/maximum-subarray/
 *
 * @author dall.
 */
public class MaxSubArray {

    public int maxSubArray(int[] nums) {
        return maxSubArray(nums, 0, nums.length);
    }

    private int maxSubArray(int[] nums, int start, int end) {
        if(end - start < 2) return nums[start];
        int mid = start + (end - start) / 2;
        int leftSum = 0;
        int leftMax = Integer.MIN_VALUE;
        for (int i = mid - 1; i >= start; i--) {
            leftSum += nums[i];
            leftMax = Math.max(leftMax, leftSum);
        }

        int rightSum = 0;
        int rightMax = Integer.MIN_VALUE;
        for (int i = mid; i < end; i++) {
            rightSum += nums[i];
            rightMax = Math.max(rightMax, rightSum);
        }

        return Math.max(Math.max(maxSubArray(nums, start, mid), maxSubArray(nums, mid, end)), (leftMax + rightMax));

    }


    public int maxSubArray2(int[] nums) {
        int max = nums[0];
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                max = Math.max(max, sum);
            }

        }
        return max;
    }

    public int maxSubArray1(int[] nums) {
        int max = nums[0];
        for (int start = 0; start < nums.length; start++) {
            for (int end = start; end < nums.length; end++) {
                int sum = 0;
                for (int i = start; i <= end; i++) {
                    sum += nums[i];
                }
                max = Math.max(max, sum);
            }

        }
        return max;
    }
}

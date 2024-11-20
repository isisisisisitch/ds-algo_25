package ca.bytetube._00_leetcode._04_queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/sliding-window-maximum/
 *
 * @author dal
 */

//Monotonic Queue
public class MaxSlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();

        //初始化必须是0
        int w = 0;

        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.removeLast();
            }
            deque.offer(i);

            if (i >= k - 1) {//合法窗口形成
                while (deque.peekFirst() < w) {
                    deque.removeFirst();
                }
                res[w++] = nums[deque.peekFirst()];
            }
        }

        return res;
    }

//    public int[] maxSlidingWindow(int[] nums, int k) {
//
//        Deque<Integer> deque = new LinkedList<>();
//        int[] maxes = new int[nums.length - k + 1];
//
//
//        return maxes;
//    }

}

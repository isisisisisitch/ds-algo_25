package ca.bytetube._00_leetcode._01_array;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays/description/
 *
 * @author dal
 */
public class IntersectionOfTwoArrays {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (int ele : nums1) {
            set1.add(ele);
        }

        Set<Integer> set2 = new HashSet<>();
        for (int ele : nums2) {
            set2.add(ele);
        }

        Set<Integer> resSet = new HashSet<>();
        for (Integer ele : set1) {
            if (set2.contains(ele)) {
                resSet.add(ele);
            }
        }

        int[] arr = new int[resSet.size()];
        int i = 0;
        for (Integer ele : resSet) {
            arr[i++] = ele;
        }


        return arr;
    }
}

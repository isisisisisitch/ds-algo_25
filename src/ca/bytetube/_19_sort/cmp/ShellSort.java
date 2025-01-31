package ca.bytetube._19_sort.cmp;

import java.util.ArrayList;
import java.util.List;

public class ShellSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        List<Integer> sequence = shellSequence();
        for (Integer step : sequence) {
            sort(step);
        }
    }

    /**
     * 分成step列进行排序
     */
    protected void sort(int step) {
        for (int col = 0; col < step; col++) {
            for (int begin = col + step; begin < array.length; begin += step) {
                int cur = begin;
                while (cur > col && cmp(cur, cur - step) < 0) {
                    swap(cur, cur - step);
                    cur -= step;
                }
            }

        }
    }


    private List<Integer> shellSequence() {
        List<Integer> sequence = new ArrayList<Integer>();
        int len = array.length;//16
        while ((len = len >> 1) > 0) {
            sequence.add(len);
        }
        return sequence;
    }


}

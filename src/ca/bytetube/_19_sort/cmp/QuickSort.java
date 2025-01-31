package ca.bytetube._19_sort.cmp;

public class QuickSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        sort(0, array.length);
    }

    private void sort(int begin, int end) {
        if (end - begin <= 1) return;
        int pivotIndex = pivotIndex(begin, end);
        sort(begin, pivotIndex);
        sort(pivotIndex + 1, end);


    }

    //1.找到pivotIndex
    //2.把数组中小于等于pivot的值放在pivot左侧，大于pivot的值放在pivot右侧
    private int pivotIndex(int begin, int end) {
        //0.从数组中随机的选择一个元素作为分界点（和begin位置交换位置）
        swap(begin, begin + (int) (Math.random() * (end - begin)));
        //1.备份起点
        T pivot = array[begin];
        end--;
        while (begin < end) {
            //2.1 end从右向左
            while (begin < end) {
                if (cmp(pivot, array[end]) < 0) end--;
                else {
                    array[begin++] = array[end];
                    break;
                }
            }
            //2.2 begin从左向右
            while (begin < end) {
                if (cmp(pivot, array[begin]) >= 0) begin++;
                else {
                    array[end--] = array[begin];
                    break;
                }
            }
        }
        //3.将之前备份的begin放到pivotIndex位置上
        array[begin] = pivot;

        return begin;
    }
}

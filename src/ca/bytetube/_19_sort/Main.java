package ca.bytetube._19_sort;

import ca.bytetube._19_sort.cmp.MergeSort;
import ca.bytetube._19_sort.cmp.QuickSort;
import ca.bytetube._19_sort.cmp.ShellSort;
import ca.bytetube._19_sort.cmp.Sort;
import ca.bytetube._19_sort.utils.Asserts;
import ca.bytetube._19_sort.utils.Integers;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Integer[] array = Integers.random(100000, 1, 20000000);
        Sort<Integer> sort = new ShellSort<>();
        System.out.println( Arrays.toString(array));
        sort.sort(array);
        System.out.println( Arrays.toString(array));

       // System.out.println( Arrays.toString(array));
//        Sort<Integer> sort = new MergeSort<>();
//        sort.sort(array);
       // System.out.println( Arrays.toString(array));
       // Integer[] array = {3819, 12969, 11544, 1994, 2364, 3291};
        testSorts(array,

               // new BubbleSort3<>(),
               // new HeapSort<>(),
//                new InsertionSort<>(),
//                new InsertionSort2<>(),
//                new InsertionSort3<>()
//                new InsertionSort(),
//                new InsertionSort2(),
//                new InsertionSort3()
                new MergeSort(),
                new QuickSort(),
                new ShellSort()

        );

       // System.out.println( Arrays.toString(array));
//        Integer[] array = Integers.random(10, 1, 200);
//        System.out.println( Arrays.toString(array));
//        Sort<Integer> sort = new QuickSort<>();
//        sort.sort(array);
//        System.out.println( Arrays.toString(array));
    }

    static void testSorts(Integer[] array, Sort... sorts) {
        for (Sort sort : sorts) {
            Integer[] newArray = Integers.copy(array);
            sort.sort(newArray);
            Asserts.test(Integers.isAscOrder(newArray));
        }

       // Arrays.sort(sorts);

        for (Sort sort : sorts) {
            System.out.println(sort);
        }

    }
}

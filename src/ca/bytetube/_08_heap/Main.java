package ca.bytetube._08_heap;

import ca.bytetube._08_heap.printer.BinaryTrees;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = {70, 30, 34, 73, 60, 68, 43, 25, 72, 78, 90, 57};
        topK(arr, 4);
    }

    public static void topK(Integer[] data, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < data.length; i++) {
            int val = data[i];
            if (minHeap.size() < k) {
                minHeap.add(val);
            } else if (val > minHeap.peek()) {
                //minHeap.replace(data[i]);
                minHeap.poll();
                minHeap.add(val);
            }
        }

        Iterator<Integer> iterator = minHeap.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

//        BinaryHeap<Integer> heap = new BinaryHeap<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return  o1 - o2;
//            }
//        });
//
//        for (int i = 0; i < data.length; i++) {
//            if (heap.size < k) {
//                heap.add(data[i]);
//            } else if (data[i] < heap.get()) {
//                heap.replace(data[i]);
//            }
//        }
//        BinaryTrees.println(heap);

    }

    public static void test2() {
        Integer[] arr = {70, 30, 34, 73, 60, 68, 43, 25, 72, 78, 90, 57};
        BinaryHeap<Integer> heap = new BinaryHeap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        }, arr);
        BinaryTrees.println(heap);

    }

    public static void test1() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(70);
        heap.add(30);
        heap.add(34);
        heap.add(73);
        heap.add(60);
        heap.add(68);
        heap.add(43);
        BinaryTrees.println(heap);
        System.out.println("=====================================");
        while (heap.size > 1) {
            heap.remove();
            BinaryTrees.println(heap);
            System.out.println("=====================================");

        }


    }
}

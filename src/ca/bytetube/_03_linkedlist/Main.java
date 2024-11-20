package ca.bytetube._03_linkedlist;

import ca.bytetube._03_linkedlist.doubly.DoublyCircularLinkedList;
import ca.bytetube._03_linkedlist.doubly.LinkedList;
import ca.bytetube._03_linkedlist.singly.SingleCircularLinkedList;


public class Main {
    public static void main(String[] args) {
        DoublyCircularLinkedList<Integer> linkedList = new DoublyCircularLinkedList<>();
        for (int i = 1; i <= 8; i++) {
            linkedList.add(i);
        }

        //reset
        linkedList.reset();
        while (linkedList.size() > 1) {
            next(linkedList, 2);
            System.out.print(linkedList.remove()+" ");
        }


//        DoublyCircularLinkedList<Integer> list = new DoublyCircularLinkedList<>();
//        for (int i = 11; i < 15; i++) {
//            list.add(i);
//        }
//       // list.add(1,100);
//        while (list.size() > 0){
//            list.remove(0);
//        }
////        list.remove(0);
//        System.out.println();
//        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
//        for (int i = 0; i < 5; i++) {
//            list.add(i + 10);
//        }
//        list.add(0, 100);
//
//        list.remove(0);
//        System.out.println();
//
//        System.out.println(list.indexOf(14));
//        LinkedList<Integer> linkedList = new LinkedList<>();
//        linkedList.add(11);
//        linkedList.add(22);
//        linkedList.add(null);
//        linkedList.add(33);
//        linkedList.add(44);
//
//        System.out.println(linkedList.indexOf(null));

//       while (linkedList.size() > 0){
//           linkedList.remove(0);
//       }


    }


    public static void next(DoublyCircularLinkedList<Integer> linkedList, int count) {
        for (int i = 0; i < count; i++) {
            linkedList.next();
        }
    }

    public static void test(List<String> list) {
        list.clear();

    }


}

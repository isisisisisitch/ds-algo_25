package ca.bytetube._02_dynamicarray;


public class Main {
    public static void main(String[] args) {
//        ArrayList<Integer> arrayList = new ArrayList<>(20);
//
//        for (int i = 0; i < 20; i++) {
//            arrayList.add(i);
//        }
//
//        System.out.println(arrayList);
//        arrayList.add(20);
//        System.out.println(arrayList);
//
//        ArrayList<String> arrayList2 = new ArrayList<>(20);
        ArrayList<Integer> arrayList = new ArrayList<>(100);
        for (int i = 1; i < 11; i++) {
            arrayList.add(i + 10);
        }
        System.out.println(arrayList.size());
        arrayList.add(2,null);
        System.out.println(arrayList.get(2)+" ");

    }
}

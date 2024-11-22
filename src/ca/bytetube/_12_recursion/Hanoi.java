package ca.bytetube._12_recursion;

import java.util.LinkedHashSet;

public class Hanoi {
    public static void main(String[] args) {
        hanoi0(3, "A", "C", "B");
        System.out.println("===================================");
        LinkedHashSet<MoveInfo> moveInfos = hanoi(3, "A", "C", "B");
        System.out.println(moveInfos);
    }

    public static void hanoi0(int n, String from, String to, String help) {
        if (n == 1) System.out.println("move " + 1 + " from " + from + " to " + to);

        else { //When n> 1, it can be divided into 3 major steps

            //① Move n – 1 plates from A to B
            hanoi0(n - 1, from, help, to);
            //② Move the plate n from A to C
            System.out.println("move " + n + " from " + from + " to " + to);
            //③ Move n – 1 plates from B to C
            hanoi0(n - 1, help, to, from);
        }


    }

    public static LinkedHashSet<MoveInfo> hanoi(int n, String from, String to, String help) {

        LinkedHashSet<MoveInfo> set = new LinkedHashSet<>();
        hanoi(n, from, to, help, set);
        return set;


    }

    private static void hanoi(int n, String from, String to, String help, LinkedHashSet<MoveInfo> set) {
        if (n == 1) set.add(new MoveInfo(1, from, to));

        else { //When n> 1, it can be divided into 3 major steps

            //① Move n – 1 plates from A to B
            hanoi(n - 1, from, help, to, set);
            //② Move the plate n from A to C
            // System.out.println("move " + n + " from " + from + " to " + to);
            set.add(new MoveInfo(n, from, to));
            //③ Move n – 1 plates from B to C
            hanoi(n - 1, help, to, from, set);
        }

    }

    private static class MoveInfo {
        int index;
        String from;
        String to;

        public MoveInfo(int index, String from, String to) {
            this.index = index;
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "move " + index + " from " + from + " to " + to + "\n";
        }
    }
}

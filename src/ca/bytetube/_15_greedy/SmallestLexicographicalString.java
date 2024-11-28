package ca.bytetube._15_greedy;

import java.util.Arrays;
import java.util.Comparator;

public class SmallestLexicographicalString {
    public static void main(String[] args) {
       String[] strings = {"abc", "bc", "ac", "ca", "ab"};
       // String[] strings = {"b","ba"};
        System.out.println(smallestLexicalString(strings));
    }

    public static String smallestLexicalString(String[] strs) {
        Arrays.sort(strs, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });

        StringBuilder str = new StringBuilder();
        for (String s : strs) {
            str.append(s);
        }


        return str.toString();
    }
}

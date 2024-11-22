package ca.bytetube._12_recursion;

public class PrintAllSubString {

    public static void main(String[] args) {
        printAllSubString("abc");
    }

    public static void printAllSubString(String s) {
        String str = "";
        printAllSubString(s, 0, str);
    }

    private static void printAllSubString(String s, int index, String str) {
        if (index == s.length()) {
            System.out.println(str);
            return;
        }

        printAllSubString(s, index + 1, str);
        printAllSubString(s, index + 1, str + s.charAt(index));
    }
}

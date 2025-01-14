package ca.bytetube._17_sequence;

public class BruteForce {
    public static void main(String[] args) {
        String text = "Hello World!";
        String pattern = "or";
        System.out.println(indexOf(text, pattern));
    }


    public static int indexOf(String text, String pattern) {
        int pi = 0;
        int ti = 0;
        int patLen = pattern.length();
        int textLen = text.length();
        char[] textChar = text.toCharArray();
        char[] patternChar = pattern.toCharArray();
        while (pi < patLen && ti <= textLen - patLen) {
            //match successfully
            //pi++
            //ti++
            if (textChar[ti] == patternChar[pi]) {
                pi++;
                ti++;
            } else {
                //match unsuccessfully
                //pi = 0
                //ti –= pi – 1
                ti -= pi - 1;
                pi = 0;
            }

        }

        return pi == patLen ? ti - pi : -1;

    }
}

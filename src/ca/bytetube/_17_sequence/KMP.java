package ca.bytetube._17_sequence;

public class KMP {
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
        int[] next = next(pattern);
        while (pi < patLen && ti <= textLen - patLen) {
            //match successfully
            //pi++
            //ti++
            if (pi < 0 || textChar[ti] == patternChar[pi]) {
                pi++;
                ti++;
            } else {
                //match unsuccessfully
                //pi = 0
                //ti –= pi – 1
                pi = next[pi];
            }

        }

        return pi == patLen ? ti - pi : -1;

    }

    private static int[] next(String pattern) {
        int[] next = new int[pattern.length()];
        char[] charArray = pattern.toCharArray();
        int i = 0;
        int n = next[i] = -1;
        while (i < charArray.length - 1) {
            if (n < 0 || charArray[i] == charArray[n]) next[++i] = ++n;
            else n = next[n];//这里的next[n]相当于k
        }
        return next;
    }
}

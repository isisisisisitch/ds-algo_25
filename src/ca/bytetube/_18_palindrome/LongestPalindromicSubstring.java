package ca.bytetube._18_palindrome;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/description/
 *
 * @author dal
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("abbaba"));
    }


    //manacher
    public String longestPalindrome(String s) {
        if (s == null) return null;
        char[] oldChars = s.toCharArray();
        if (oldChars.length <= 1) return s;
        int maxLen = 0;//最长回文串的长度
        int index = 0;
        char[] cs = preprocess(oldChars);
        int[] m = new int[cs.length];
        int c = 0;
        int r = 0;

        for (int i = 2; i < m.length - 2; i++) {
            //1.2.3
            if (i < r) {
                int li = (c << 1) - i;
//                if (i + m[li] <= r) {//1.2
//                    m[i] = m[li];
//                } else {//3
//                    m[i] = r - i;
//                }
                m[i] = i + m[li] <= r ? m[i] = m[li] : r - i;
            }

            //4.2.3 以i为对称中心向左右两边扩展
            while (cs[i + m[i] + 1] == cs[i - m[i] - 1]) m[i]++;

            //4.2.3 更新c，r
            if (i + m[i] > r) {
                c = i;
                r = i + m[i];
            }

            //4.2.3 更新最大回文字串的长度和开始索引
            if (m[i] > maxLen) {
                maxLen = m[i];
                index = i;
            }

        }

        int begin = (index - maxLen) >> 1;

        return new String(oldChars, begin, maxLen);
    }


    private char[] preprocess(char[] oldChars) {

        char[] cs = new char[(oldChars.length << 1) + 3];
        cs[0] = '^';
        cs[1] = '#';
        cs[cs.length - 1] = '$';
        for (int i = 0; i < oldChars.length; i++) {
            int index = (i + 1) << 1;
            cs[index] = oldChars[i];
            cs[index + 1] = '#';

        }

        return cs;


    }


    //扩展中心法的优化：以连续相同的字串作为扩展中心
    public String longestPalindrome3(String s) {
        if (s == null) return null;
        char[] cs = s.toCharArray();
        if (cs.length <= 1) return s;
        int begin = 0;//最长回文串的开始索引
        int maxLen = 1;//最长回文串的长度
        int i = 0;
        while (i < cs.length) {
            int l = i - 1;
            //找到右边第一个不等于cs[i]的位置，记为r
            int r = i;
            while (++r < cs.length && cs[r] == cs[i]) ;
            //r作为下一次的i
            i = r;
            //从l向左，r向右扩展，进而得到更大的回文串
            while (l >= 0 && r < cs.length && cs[l] == cs[r]) {
                l--;
                r++;
            }
            //扩展结束，cs[l + 1, r)区间就是最大回文子串
            int len = r - ++l;
            if (len > maxLen) {
                maxLen = len;
                begin = l;
            }
        }

        return new String(cs, begin, maxLen);
    }

    //扩展中心法：分别以i位置字符和i位置右间隙作为扩展中心
    public String longestPalindrome2(String s) {
        if (s == null) return null;
        char[] cs = s.toCharArray();
        if (cs.length <= 1) return s;
        int begin = 0;//最长回文串的开始索引
        int maxLen = 1;//最长回文串的长度
        for (int i = cs.length - 2; i >= 1; i--) {
            //以字符为中心向左右扩展
            int len1 = palindromeLength(cs, i - 1, i + 1);

            //以字符的右间隙为中心向左右扩展
            int len2 = palindromeLength(cs, i, i + 1);

            len1 = Math.max(len1, len2);
            if (len1 > maxLen) {
                maxLen = len1;
                begin = i - ((maxLen - 1) >> 1);
            }
        }
        //处理0号位置右间隙  以0号位字符右间隙为中心向左右扩展
        if (cs[0] == cs[1] && maxLen < 2) {
            maxLen = 2;
            begin = 0;
        }


        return new String(cs, begin, maxLen);
    }


    private int palindromeLength(char[] cs, int l, int r) {
        while (l >= 0 && r <= cs.length - 1 && cs[l] == cs[r]) {
            l--;
            r++;
        }

        return r - l - 1;
    }


    public String longestPalindrome1(String s) {
        if (s == null) return null;
        char[] cs = s.toCharArray();
        if (cs.length <= 1) return s;
        int begin = 0;//最长回文串的开始索引
        int maxLen = 1;//最长回文串的长度
        boolean[][] dp = new boolean[cs.length][cs.length];

        for (int i = cs.length - 1; i >= 0; i--) {
            for (int j = i; j <= cs.length - 1; j++) {
                int len = j - i + 1;
//                if (len <= 2) {
//                    dp[i][j] = cs[i] == cs[j];
//                }else {
//                    dp[i][j] = dp[i + 1][j - 1] && cs[i] == cs[j];
//                }
                dp[i][j] = (cs[i] == cs[j]) && (len <= 2 || dp[i + 1][j - 1]);

                if (dp[i][j] && len > maxLen) {
                    maxLen = len;
                    begin = i;
                }

            }
        }
        return new String(cs, begin, maxLen);
    }
}

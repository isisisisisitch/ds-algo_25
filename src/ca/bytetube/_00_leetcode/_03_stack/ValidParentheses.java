package ca.bytetube._00_leetcode._03_stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-parentheses/description/
 *
 * @author dal
 */
public class ValidParentheses {

     static HashMap<Character, Character> map = new HashMap<>();

    static {
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        map.put('<', '>');
        map.put('#', '#');
    }


    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //1.When you meet the left character, push the left character into the stack
            if (map.containsKey(c)) {
                stack.push(c);
            } else { //2. When you meet the right character

                //If the stack is empty, the brackets are invalid
                if (stack.isEmpty()) return false;
                //If the stack is not empty, pop the top character of the stack to match the right character
                char left = stack.pop();
                // If the left and right characters do not match, the brackets ar invalid
                if (c != map.get(left)) return false;

                // If the left and right characters match, continue scanning the nex character
            }
        }

        return stack.isEmpty();
    }


    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //1.When you meet the left character, push the left character into the stack
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else { //2. When you meet the right character

                //If the stack is empty, the brackets are invalid
                if (stack.isEmpty()) return false;
                //If the stack is not empty, pop the top character of the stack to match the right character
                char left = stack.pop();
                // If the left and right characters do not match, the brackets ar invalid
                if (left == '(' && c != ')') return false;
                if (left == '[' && c != ']') return false;
                if (left == '{' && c != '}') return false;
                // If the left and right characters match, continue scanning the nex character
            }
        }

        return stack.isEmpty();
    }


    public boolean isValid1(String s) {

        while (s.contains("()") || s.contains("[]") || s.contains("{}")) {
            s = s.replace("()", "");
            s = s.replace("[]", "");
            s = s.replace("{}", "");
        }


        return s.isEmpty();
    }
}

package ca.bytetube._04_stack;

public class Main {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
//            stack.add(3,100);
        }

        System.out.println();
        while (!stack.isEmpty()){
            System.out.print( stack.pop() + " ");
        }
    }

}

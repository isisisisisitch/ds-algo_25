package ca.bytetube._15_greedy;

import java.util.Arrays;

public class ChangeCoins {
    public static void main(String[] args) {
        System.out.println("total:"+ changeCoins(new int[]{1,10,5,25},7));
    }

//    public static int changeCoins(int[] coins, int money) {
//        Arrays.sort(coins);
//        int total = 0;
//        int index = coins.length - 1;
//       while (money > 0) {
//            if (money < coins[index]) {
//                index--;
//                continue;
//            }
//            money = money - coins[index];
//            System.out.print(coins[index]+" ");
//            total++;
//
//        }
//
//
//        return total;
//    }


    public static int changeCoins(int[] coins, int money) {
        Arrays.sort(coins);
        int total = 0;
        for (int i = coins.length - 1; i >= 0; i--) {
            if (money < coins[i]) continue;
            money = money - coins[i];
            System.out.print(coins[i]+" ");
            total++;
            i++;
        }


        return total;
    }
}

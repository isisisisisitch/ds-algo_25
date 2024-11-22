package ca.bytetube._13_backtracking;

/**
 * https://leetcode.com/problems/n-queens-ii/
 *
 * @author dal
 */
public class NQueens {
    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        nQueens.totalNQueens(5);
    }


    int[] cols;// cols[4] = 5; 表示在第5行第6列放入一个皇后
    int ways;

    public int totalNQueens(int n) {
        cols = new int[n];
        place(0);
        return ways;
    }


    private void place(int row) {
        if (row == cols.length) {
            ways++;
            showQueen();
            return;
        }

        for (int col = 0; col < cols.length; col++) {
            if (isValid(row, col)) {
                cols[row] = col;
                place(row + 1);
            }
        }
    }

    private boolean isValid(int row, int col) {
        for (int i = 0; i < row; i++) {
            //列
            if (cols[i] == col) return false;
            //对角线
            if (Math.abs(col - cols[i]) == row - i) return false;
        }

        return true;
    }


    private void showQueen() {
        for (int row = 0; row < cols.length; row++) {
            for (int col = 0; col < cols.length; col++) {
                if (cols[row] == col) System.out.print("Q ");
                else System.out.print(". ");
            }
            System.out.println();
        }

        System.out.println("======================================");
    }
}

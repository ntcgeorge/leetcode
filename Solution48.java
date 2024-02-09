import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.List;

/**
 * https://leetcode.cn/problems/rotate-image/description/
 * risk-level: medium
 */
class Solution48 {

    public List<Integer> spiralOrder(int[][] matrix) {
        int row;
        int col;
        boolean[][] marked;
        row = matrix.length;
        col = matrix[0].length;
        List<Integer> ans = new ArrayList<Integer>();
        marked = new boolean[row][col];
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                marked[i][j] = false;
        int cnt = 0;
        int x = 0;
        int y = 0;
        int dx = 1;
        int dy = 0;
        while (cnt < row * col) {
            ans.add(matrix[y][x]);
            marked[y][x] = true;
            cnt++;
            if (dx != 0) {
                if (!check(matrix, x + dx, y + dy) || marked[y + dy][x + dx]) {
                    dy = dx;
                    dx = 0;
                }
            } else if (dy != 0) {
                if (!check(matrix, x + dx, y + dy) || marked[y + dy][x + dx]) {
                    dx = -1 * dy;
                    dy = 0;
                }
            }
            // update the momentum
            x = x + dx;
            y = y + dy;
        }
        return ans;
    }

    private boolean check(int[][] matrix, int x, int y) {
        int col = matrix[0].length;
        int row = matrix.length;
        return x < col && y < row && x >= 0 && y >= 0;
    }

    public static void main(String[] args) {
        int row = StdIn.readInt();
        int col = StdIn.readInt();
        int[][] matrix = new int[row][col];
        while (!StdIn.isEmpty()) {
            for (int i = 0; i < row; i++)
                for (int j = 0; j < col; j++) {
                    matrix[i][j] = StdIn.readInt();
                }
        }
        SpatialMatrix sp = new SpatialMatrix();
        List<Integer> ans = sp.spiralOrder(matrix);
        System.out.println(ans);

    }
}

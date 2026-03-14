package demoMultiDynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 */
public class MinimumPathSum64 {

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        dp[0][0] = grid[0][0];
        // 第一行的只能是一路往右走得到的
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        // 第一列的只能是一路往下走得到的
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 当前最小路径和取决于上方和左方的最小值，再加上自身
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        // 补充：如果要输出最优路径：
        List<Integer> list = new ArrayList<>(m + n - 1);
        int i = m - 1;
        int j = n - 1;
        // 先反向记录路径
        while (true) {
            list.add(grid[i][j]);
            if (i == 0 && j == 0) {
                break;
            }

            if (i == 0) {
                // 第一行了，就往左
                j--;
            } else if (j == 0) {
                // 第一列了，就往上
                i--;
            } else {
                if (dp[i][j - 1] < dp[i - 1][j]) {
                    j--;
                } else {
                    i--;
                }
            }
        }
        Collections.reverse(list);
        System.out.println("最优解为: " + list);

        return dp[m - 1][n - 1];
    }

}

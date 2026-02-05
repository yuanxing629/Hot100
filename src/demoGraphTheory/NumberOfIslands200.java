package demoGraphTheory;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 */
public class NumberOfIslands200 {
    public int numIslands(char[][] grid) {
        int ans = 1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') { // 找到一个新的岛，就去进行dfs
                    dfs(grid, i, j);
                    ans++;
                }
            }
        }
        return ans;
    }


    public void dfs(char[][] grid, int i, int j) {
        // 出界或者遇到非'1'元素，就不再往下递归
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }

        // 插旗：避免来回横跳无限递归
        grid[i][j] = '2';
        // 向左右上下移动
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
    }
}

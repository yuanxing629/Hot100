package demoGraphTheory;

import java.util.ArrayList;
import java.util.List;


public class RottingOranges994 {

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 四个方向

    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int fresh = 0;

        List<int[]> q = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    fresh++; // 统计新鲜橘子的个数
                } else if (grid[i][j] == 2) {
                    q.add(new int[]{i, j}); // 存储初始时腐烂的橘子
                }
            }
        }

        int ans = 0;
        while (fresh > 0 && !q.isEmpty()) {
            ans++; // 经过了1分钟
            List<int[]> tmp = q;
            q = new ArrayList<>();
            for (int[] pos : tmp) { // 已经腐烂的橘子
                for (int[] d : DIRECTIONS) { // 四个方向
                    int i = pos[0] + d[0];
                    int j = pos[1] + d[1];
                    if (0 <= i && i < m && 0 <= j && j < n && grid[i][j] == 1) { // 新鲜橘子
                        fresh--;
                        grid[i][j] = 2; // 橘子腐烂
                        q.add(new int[]{i, j});
                    }
                }
            }
        }

        return fresh > 0 ? -1 : ans;
    }
}

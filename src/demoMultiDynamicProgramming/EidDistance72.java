package demoMultiDynamicProgramming;

/**
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 */
public class EidDistance72 {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }

        // dp[i][j] 表示 word1 中的前 i 个字母 与 word2 中的 前 j 个字母的编辑距离
        int[][] dp = new int[m + 1][n + 1];
        // 初始化
        for (int i = 0; i < m + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < n + 1; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                int left = dp[i - 1][j] + 1;
                int down = dp[i][j - 1] + 1;
                int left_down = dp[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down++;
                }
                dp[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return dp[m][n];
    }
}

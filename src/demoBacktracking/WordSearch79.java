package demoBacktracking;

/**
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 */
public class WordSearch79 {

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        // 遍历整个网络，寻找单词的起点
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果从当前位置 (i,j) 能够成功匹配整个单词，直接返回 true
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        // 遍历完了整个网格，都没有找到
        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j, int index) {
        // 所有字符都已匹配成功
        if (index == word.length()) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(index)) {
            return false;
        }

        // 都到这里，说明当前字符已经匹配成功
        // 保存当前字符，并将其标记为特殊字符'*' ，表示当前路径已经访问过该位置
        char temp = board[i][j];
        board[i][j] = '*';

        // 在 上下左右 四个方向递归，寻找下一个字符
        // 只要有一个返回 true ，整体就是 true
        boolean found = dfs(board, word, i + 1, j, index + 1) ||
                dfs(board, word, i - 1, j, index + 1) ||
                dfs(board, word, i, j - 1, index + 1) ||
                dfs(board, word, i, j + 1, index + 1);

        // 核心：回溯
        // 无论后续路径是否能找到完整单词，当前层递归结束后，必须恢复现场
        // 这样才不会影响其他方向的搜索路径使用这个格子
        board[i][j] = temp;
        return found;
    }
}

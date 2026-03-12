package demoBacktracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 */
public class NQueens51 {

    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        // 初始化空棋盘
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        backtrack(board, 0);
        return res;
    }

    private void backtrack(char[][] board, int row) {
        // 如果走到了最后一行之外 (row == n) ，说明找到了一种方案
        if (row == board.length) {
            res.add(buildBoard(board));
            return;
        }

        int n = board.length;
        // 尝试在当前行的每一列(col)放置皇后
        for (int col = 0; col < n; col++) {
            // 这个位置不合法，直接跳过，看下一列
            if (!isValid(board, row, col)) {
                continue;
            }

            board[row][col] = 'Q';
            backtrack(board, row + 1); // 递归，进入下一行
            board[row][col] = '.'; // 回溯：把皇后拿走，以便尝试当前行的下一列
        }
    }

    // 检查 board[row][col] 放置皇后是否合法
    private boolean isValid(char[][] board, int row, int col) {
        int n = board.length;

        // 检查正上方
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // 检查左上方（主对角线）
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; --i, --j) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // 检查右上方（副对角线）
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; --i, ++j) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // 都没有冲突，合法
        return true;
    }

    // 辅助方法：将 char[][] 的棋盘转化为 List<String> 格式加入结果集
    private List<String> buildBoard(char[][] board) {
        List<String> list = new ArrayList<>();
        for (char[] row : board) {
            list.add(new String(row));
        }
        return list;
    }
}

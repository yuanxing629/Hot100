package demoMatrix;

import java.util.HashMap;
import java.util.Map;

public class SetMatrixZeroes73 {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[] rows = new int[m];
        int[] columns = new int[n];

        // 记录元素0所在的行索引和列索引打标记
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = 1;
                    columns[j] = 1;
                }
            }
        }

        // 检查第i行和第j列的标记，只要有一个为1，则将元素置0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rows[i] == 1 || columns[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}

package demoMatrix;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int i = 0, j = 0;
        int m = matrix.length, n = matrix[0].length;
        int up = 1, down = m - 1;
        int left = 0, right = n - 1;
        while (res.size() < m * n) {
            while (res.size() < m * n && j <= right) {
                res.add(matrix[i][j++]);
            }
            j = right; // j越界了，让它回到右边界
            right--; // 右边界缩短
            i++; // 下移
            while (res.size() < m * n && i <= down) {
                res.add(matrix[i++][j]);
            }
            i = down; // i越界了，让它回到下边界
            down--; // 下边界缩短
            j--; // 左移
            while (res.size() < m * n && j >= left) {
                res.add(matrix[i][j--]);
            }
            j = left; // j越界了，让它回到左边界
            left++; //左边界缩短
            i--; // 上移
            while (res.size() < m * n && i >= up) {
                res.add(matrix[i--][j]);
            }
            i = up; // i越界了，让它回到上边界
            up++; // 下边界缩短
            j++; // 右移，开启下一次旋转
        }
        return res;
    }

    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int i = 0, j = 0;
        int m = matrix.length, n = matrix[0].length;
        int up = 1, down = m - 1;
        int left = 0, right = n - 1;
        while (res.size() < m * n) {
            while (j <= right) {
                if (res.size() >= m * n) {
                    return res;
                }
                res.add(matrix[i][j++]);
            }
            j = right; // j越界了，让它回到右边界
            right--; // 右边界缩短
            i++; // 下移
            while (i <= down) {
                if (res.size() >= m * n) {
                    return res;
                }
                res.add(matrix[i++][j]);
            }
            i = down; // i越界了，让它回到下边界
            down--; // 下边界缩短
            j--; // 左移
            while (j >= left) {
                if (res.size() >= m * n) {
                    return res;
                }
                res.add(matrix[i][j--]);
            }
            j = left; // j越界了，让它回到左边界
            left++; //左边界缩短
            i--; // 上移
            while (i >= up) {
                if (res.size() >= m * n) {
                    return res;
                }
                res.add(matrix[i--][j]);
            }
            i = up; // i越界了，让它回到上边界
            up++; // 下边界缩短
            j++; // 右移，开启下一次旋转
        }
        return res;
    }
}

package demoBinSearch;

public class SearchA2dMatrix74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int i = m - 1, j = 0;
        while (i >= 0 && j < n) {
            int num = matrix[i][j];
            if (num == target) {
                return true;
            } else if (num > target) {
                i--;
            } else {
                j++;
            }
        }
        return false;
    }

    public boolean searchMatrix2(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int low = 0, high = m * n - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            int num = matrix[mid / n][mid % n];
            if (target == num) {
                return true;
            } else if (target > num) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

}

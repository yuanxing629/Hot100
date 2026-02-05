package demoRegularArrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals56 {
    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }

        // 根据数组的start进行排序
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);

        List<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals) {
            int start = interval[0], end = interval[1];
            if (merged.isEmpty() || merged.getLast()[1] < start) { // 如果是第一个数组或者已排序数组的最后一个end < 当前数组的start
                merged.add(new int[]{start, end}); // 就加入
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], end); // 否则更新已排序数组的最后一个end
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};

        int[][] res = merge(intervals);
        for (int[] arr : res) {
            System.out.print("[" + arr[0] + ", " + arr[1] + "], ");
        }
    }
}

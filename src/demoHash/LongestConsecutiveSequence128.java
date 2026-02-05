package demoHash;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class LongestConsecutiveSequence128 {
    public int longestConsecutive(int[] nums) {
        int n = nums.length;
        if (n == 0 || n == 1) {
            return n;
        }

        Set<Integer> set = new LinkedHashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int res = 0;

        for (int num : set) {
            if (!set.contains(num - 1)) {
                int currNum = num;
                int currLen = 1;

                while (set.contains(currNum + 1)) {
                    currNum += 1;
                    currLen += 1;
                }

                res = Math.max(res, currLen);
            }
        }
        return res;
    }
}

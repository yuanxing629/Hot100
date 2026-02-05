package demoRegularArrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ProductOfArrayExceptSelf238 {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        int[] prefix = new int[n];
        int[] suffix = new int[n];
        int[] answer = new int[n];

        prefix[0] = 1;
        for (int i = 1; i < n; i++) {
            prefix[i] = nums[i - 1] * prefix[i - 1];
        }

        suffix[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = nums[i + 1] * suffix[i + 1];
        }

        for (int i = 0; i < n; i++) {
            answer[i] = prefix[i] * suffix[i];
        }

        return answer;
    }

    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        int beforeSum = 1;
        int afterSum = 1;
        for (int i = 0, j = n - 1; i < n; i++, j--) {
            ans[i] *= beforeSum;
            ans[j] *= afterSum;
            beforeSum *= nums[i];
            afterSum *= nums[j];
        }
        return ans;
    }
}

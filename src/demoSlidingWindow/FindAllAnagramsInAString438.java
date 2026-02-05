package demoSlidingWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllAnagramsInAString438 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int n = s.length(), m = p.length();
        if (n < m) {
            return res;
        }
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for (int i = 0; i < m; i++) {
            sCount[s.charAt(i) - 'a']++;
            pCount[p.charAt(i) - 'a']++;
        }
        if (Arrays.equals(sCount, pCount)) {
            res.add(0);
        }
        for (int i = 0; i < n - m; i++) {
            sCount[s.charAt(i) - 'a']--;
            sCount[s.charAt(i + m) - 'a']++;
            if (Arrays.equals(sCount, pCount)) {
                res.add(i + 1);
            }
        }
        return res;
    }

    public List<Integer> findAnagrams2(String s, String p) {
        int n = s.length(), m = p.length();
        if (n < m) {
            return new ArrayList<Integer>();
        }

        List<Integer> res = new ArrayList<>();
        int[] count = new int[26];
        for (int i = 0; i < m; i++) {
            count[s.charAt(i) - 'a']++;
            count[p.charAt(i) - 'a']--;
        }

        int diff = 0;
        for (int j = 0; j < 26; j++) {
            if (count[j] != 0) {
                ++diff;
            }
        }

        if (diff == 0) {
            res.add(0);
        }

        for (int i = 0; i < n - m; i++) {
            if (count[s.charAt(i) - 'a'] == 1) {
                --diff;
            } else if (count[s.charAt(i) - 'a'] == 0) {
                ++diff;
            }
            --count[s.charAt(i) - 'a'];

            if (count[s.charAt(i + m) - 'a'] == 1) {
                --diff;
            } else if (count[s.charAt(i + m) - 'a'] == 0) {
                ++diff;
            }
            --count[s.charAt(i + m) - 'a'];

            if (diff == 0) {
                res.add(i + 1);
            }
        }
        return res;
    }
}

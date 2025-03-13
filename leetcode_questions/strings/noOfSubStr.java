// Leetcode Q: 1358. Number of Substrings Containing All Three Characters

import java.util.*;

public class noOfSubStr {
    public int numberOfSubstrings(String s) {
        int left = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        int ans = 0;

        for(int right = 0; right<s.length(); right++) {
            char ch = s.charAt(right);
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            while(map.size() == 3) {
                ans += s.length() - right;
                ch = s.charAt(left++);
                map.put(ch, map.get(ch) - 1);
                if(map.get(ch) == 0) map.remove(ch);
            }
        }

        return ans;
    }
}

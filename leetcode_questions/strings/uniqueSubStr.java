import java.util.*;

public class uniqueSubStr {
    public static int lengthOfLongestSubstring(String s) {
        if(s.length() == 0 || s.length() == 1) {
            return s.length();
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int maxRes = 0;
        int res = 0;
        int last = -1;
        for(int i=0; i<s.length(); i++) {
            char curr = s.charAt(i);
            if(!map.containsKey(curr)) {
                res++;
            } else {
                int temp = Math.max(map.get(curr), last);
                res = i - temp;
                last = temp;
            }
            map.put(curr, i);
            maxRes = Math.max(maxRes, res);
        }

        return maxRes;
    }
}

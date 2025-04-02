// Leetcode Q: 763. Partition Labels

import java.util.*;

public class partition {
    public List<Integer> partitionLabels(String s) {
        List<Integer> ans = new ArrayList<>();
        int idx = 0;
        while(idx < s.length()) {
            int start = idx;
            int end = s.lastIndexOf(s.charAt(start));
            if(start == end) {
                ans.add(1);
                idx++;
                continue;
            }

            for(int i=start+1; i<=end; i++) {
                int currIdx = s.lastIndexOf(s.charAt(i));
                if(currIdx > end) end = currIdx;
            }
            ans.add(end - start + 1);
            idx = end+1;
        }

        return ans;
    }
}

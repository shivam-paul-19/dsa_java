// Leetcode Q: 2780. Minimum Index of a Valid Split

import java.util.*;

public class validSplit {
    public int minimumIndex(List<Integer> nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int dom = 0;
        int len = nums.size();

        for(int n: nums) {
            int currCount = map.getOrDefault(n, 0) + 1;
            map.put(n, currCount);

            if(currCount > len/2) dom = n;
        }

        int domCount = map.get(dom);
        int currDomCount = 0;
        for(int i=0; i<len; i++) {
            if(nums.get(i) == dom) {
                currDomCount++;
                if(domCount > currDomCount && (i+1)/currDomCount < 2 && (len-(i+1))/(domCount - currDomCount) < 2) {
                    return i;
                }
            }
        }

        return -1;
    }
}

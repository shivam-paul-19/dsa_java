// Leetcode Q: 217. Contains Duplicate

import java.util.*;

public class containsDuplicateI {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int n: nums) {
            if(set.contains(n)) {
                return true;
            }
            set.add(n);
        }
        return false;
    }
}
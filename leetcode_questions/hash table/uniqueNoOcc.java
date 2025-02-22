// Leetcode Q: 1207. Unique Number of Occurrences

import java.util.*;

public class uniqueNoOcc {
    public static boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int e: arr) {
            map.put(e, map.getOrDefault(e, 0) + 1);
        }
        HashSet<Integer> set = new HashSet<>(map.values());
        if(map.size() == set.size()) {
            return true;
        }
        return false;
    }
}

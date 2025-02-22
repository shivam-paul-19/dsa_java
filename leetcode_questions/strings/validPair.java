// LeetCode Q: 3438. Find Valid Pair of Adjacent Digits in String

import java.util.*;

public class validPair {
    public static String findValidPair(String s) {
        Queue<Integer> q = new LinkedList<>();
        HashSet<Integer> set = new HashSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            int key = c - '0';
            if(map.containsKey(key)) {   
                map.put(key, map.get(key) + 1);
            } else {
                q.add(key);
                map.put(key, 1);
            }
        }

        while(!q.isEmpty()) {
            int key = q.remove();
            if(key == map.get(key)) {
                set.add(key);
            }
        }

        System.out.println(set);
        if(set.size() == 1) {
            return "";
        }

        for(int i=0; i<s.length()-1; i++) {
            String s1 = s.substring(i, i+2);
            if((set.contains(s1.charAt(0) - '0') && set.contains(s1.charAt(1) - '0')) && ((s1.charAt(0) - '0') != (s1.charAt(1) - '0'))) {
                return s1;
            }
        }

        return "";
    }
}

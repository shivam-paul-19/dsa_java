// Leetcode Q: 1790. Check if One String Swap Can Make Strings Equal

import java.util.*;

public class swapEq {
    public static boolean areAlmostEqual(String s1, String s2) {
        HashSet<Character> set1 = new HashSet<>();
        HashSet<Character> set2 = new HashSet<>();
        if(s1.equals(s2)) {
            return true;
        }
        
        int areDiff = 0;
        for(int i=0; i<s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i)) {
                areDiff++;
                set1.add(s1.charAt(i));
                set2.add(s2.charAt(i));
            }
            if(areDiff>2) {
                return false;
            }
        }

        if(areDiff<2 || !set1.equals(set2)) {
            return false;
        }

        return true;
    }
}


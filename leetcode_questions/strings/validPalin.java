// Leetcode Q: 125. Valid Palindrome

import java.util.*;

public class validPalin {
    public static boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isAlphabetic(c) || Character.isDigit(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }

        StringBuilder r_sb = new StringBuilder(sb);
        sb.reverse();

        String s1 = sb.toString();
        String s2 = r_sb.toString();

        return s1.equals(s2);
    }
}

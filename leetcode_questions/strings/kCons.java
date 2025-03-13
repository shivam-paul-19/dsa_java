// Leetcode Q: 3306. Count of Substrings Containing Every Vowel and K Consonants II

import java.util.*;

public class kCons {
    private long countOfSubstringsUtil(String word, int k) {
        long result = 0;
        int left = 0;
        int cons = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for(int right = 0; right<word.length(); right++) {
            char c = word.charAt(right);
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                map.put(c, map.getOrDefault(c, 0) + 1);
            } else {
                cons++;
            }

            while(map.size() == 5 && cons>=k) {
                result += word.length() - right;
                c = word.charAt(left);
                if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    map.put(c, map.get(c)-1);
                    if(map.get(c) == 0) map.remove(c);
                } else {
                    cons--;
                }

                left++;
            }
        }

        return result;
    }

    public long countOfSubstrings(String word, int k) {
        return countOfSubstringsUtil(word, k) - countOfSubstringsUtil(word, k+1);
    }
}

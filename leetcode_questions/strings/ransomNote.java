// Leetcode Q: 383. Ransom Note

import java.util.*;

public class ransomNote {
    public static boolean canConstruct(String ransomNote, String magazine) {
        ArrayList<Character> list = new ArrayList<>();
        for(int i=0; i<magazine.length(); i++) {
            char c = magazine.charAt(i);
            list.add(c);
        }

        for(int i=0; i<ransomNote.length(); i++) {
            Character c = ransomNote.charAt(i);
            if(!list.remove(c)) {
                return false;
            }
        }
        
        return true;
    }
}

import java.util.*;

public class closeStr {
    public static boolean closeStrings(String word1, String word2) {
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        HashSet<Character> set1 = new HashSet<>();
        HashSet<Character> set2 = new HashSet<>();

        for(int i=0; i<word1.length(); i++) {
            char c = word1.charAt(i);
            map1.put(c, map1.getOrDefault(c, 0) + 1);
            set1.add(c);
        }

        for(int i=0; i<word2.length(); i++) {
            char c = word2.charAt(i);
            map2.put(c, map2.getOrDefault(c, 0) + 1);
            set2.add(c);
        }

        if(!set1.equals(set2)) {
            return false;
        }
        
        ArrayList<Integer> a1 = new ArrayList<>(map1.values());
        ArrayList<Integer> a2 = new ArrayList<>(map2.values());
        Collections.sort(a1);
        Collections.sort(a2);

        if(!a1.equals(a2)) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(closeStrings("bcca", "abc"));
    }
}

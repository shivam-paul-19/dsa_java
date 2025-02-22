// Leetcode Q: 49. Group Anagrams

import java.util.*;

public class grpAnagram {
    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<List<Character>, List<String>> map = new HashMap<>();
        List<Character> list = new ArrayList<>();
        List<List<String>> finaList = new ArrayList<>();

        for(int i=0; i<strs.length; i++) {
            String str = strs[i];
            for(int c=0; c<str.length(); c++) {
                list.add(str.charAt(c));
            }
            Collections.sort(list);
            if(map.containsKey(list)) {
                map.get(list).add(new String(str));
            } else {
                map.put(new ArrayList<>(list), new ArrayList<>());
                map.get(list).add(new String(str));
            }
            list.clear();
        }

        for(List<Character> charList: map.keySet()) {
            finaList.add(map.get(charList));
        }

        return finaList;
    }
}

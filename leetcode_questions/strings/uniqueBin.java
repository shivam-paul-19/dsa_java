// Leetcode Q: 1980. Find Unique Binary String

import java.util.*;

public class uniqueBin {
    public static void findDifferent(int n, StringBuilder sb, char c, HashSet<String> set, ArrayList<String> list) {
        sb.append(c);
        if(sb.length() == n) {
            if(!set.contains(sb.toString())) {
                list.add(sb.toString());
            }
            return;
        }

        findDifferent(n, sb, '0', set, list);
        if(list.size() == 1) {
            return;
        }
        sb.deleteCharAt(sb.length()-1);
        findDifferent(n, sb, '1', set, list);
        if(list.size() == 1) {
            return;
        }
        sb.deleteCharAt(sb.length()-1);
    }

    public static String findDifferentBinaryString(String[] nums) {
        int n = nums[0].length();
        HashSet<String> set = new HashSet<>();
        for(int i=0; i<nums.length; i++) {
            set.add(nums[i]);
        }

        ArrayList<String> list = new ArrayList<>();
        findDifferent(n, new StringBuilder(), '0', set, list);
        if(list.size() == 0) {
            findDifferent(n, new StringBuilder(), '1', set, list);
        }

        System.out.println(list);
        return list.get(0);
    }

    public static void main(String[] args) {
        String[] arr = {"111"};
        findDifferentBinaryString(arr);
    }
}

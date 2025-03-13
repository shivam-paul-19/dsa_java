// Leetcode Q: 349. Intersection of Two Arrays

import java.util.*;

public class intersection {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        
        for(int n: nums1) {
            set1.add(n);
        }
        for(int n: nums2) {
            set2.add(n);
        }

        set1.retainAll(set2);
        int[] arr = new int[set1.size()];
        int i=0;
        for(int n: set1) {
            arr[i] = n;
            i++;
        }

        return arr;
    }
    public static void main(String[] args) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();

        set1.add(1);
        set1.add(2);
        set2.add(2);
        set2.add(3);

        set1.retainAll(set2);
        System.out.println(set1);
    }
}

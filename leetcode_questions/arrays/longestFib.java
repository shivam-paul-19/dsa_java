// Lettcode Q: 873. Length of Longest Fibonacci Subsequence

import java.util.*;

public class longestFib {
    public static int lenLongestFibSubseq(int[] arr) {
        if(arr.length < 3) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<arr.length; i++) {
            map.put(arr[i], i);
        }
        
        int max = 0;
        for(int i=0; i<arr.length; i++) {
            for(int j=i+1; j<arr.length; j++) {
                int len = 2;
                int p = arr[i];
                int q = arr[j];
                while(map.containsKey(p+q)) {
                    int next = map.get(p+q);
                    p = q;
                    q = arr[next];
                    len++;
                }   
                max = Math.max(max, len); 
            }
        }
        System.out.println(max);
        return max;
    }
}
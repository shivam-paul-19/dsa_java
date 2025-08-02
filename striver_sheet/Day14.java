// Day 14: Stack and Queues Part II

import java.util.*;

public class Day14 {
    // Not in Leetcode: next smallest element
    public int[] nextSmallerElements(int[] arr) {
        // A stack will be used here
        Stack<Integer> s = new Stack<>();
        
        int[] ans = new int[arr.length];
        int n = ans.length;
        
        // traverse the array backward
        for(int i=n-1; i>=0; i--) {
            int curr = arr[i];  // get the current element
            
            while(!s.isEmpty() && s.peek() > curr) {
                s.pop();    // keep until there are larger elements
            }

            if(s.isEmpty()) ans[i] = -1;    // if no element is smaller
            s.push(arr[i]);     // push the curr element
        }

        return ans;
    }
}

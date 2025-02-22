// Leetcode Q: 167. Two Sum II - Input Array Is Sorted

import java.util.*;

public class twoSumII {
    public static int[] twoSum(int[] numbers, int target) {
        int s = 0;
        int e = numbers.length-1;
        int[] res = new int[2];
        while(s<e) {
            int sum = numbers[s] + numbers[e];
            if(sum==target) {
                res[0] = ++s;
                res[1] = ++e;
                break;
            } else if(sum > target) {
                e--;
            } else {
                s++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {-1, 0};
        System.out.println(Arrays.toString(twoSum(arr, -1)));
    }
}

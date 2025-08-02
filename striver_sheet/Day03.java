// Day 3: Array part-III

import java.util.*;

public class Day03 {
    // Leetcode Q: 74. Search a 2D Matrix
    public boolean searchMatrix(int[][] matrix, int target) {
        // one thing given to us is that the matrix is sorted in ascending order
        // both right to left and top to bottom
        // so we can search by choosing the direction instead of linear search

        int j = matrix[0].length-1;
        int i = 0;

        // starting from upper right corner
        while(i < matrix.length && j >= 0) {
            // if the target is found
            if(target == matrix[i][j]) return true;

            // if target is greater than element, we will move leftwards to find smaller element
            if(target < matrix[i][j]) j--;

            // or if target is smaller than element, we will go downward to find larger element
            else i++;
        }

        // if target isn't found
        return false;
    }

    // Leetcode Q: 50. pow(x, n)
    public double myPow(double x, int n) {
        // we will follow a recursive approach to reduce time complexity
        
        // base case
        if(n == 0) return 1.0;    // anything to the power 0 is 1

        // recursive calls
        double y = myPow(x, n/2);

        // explanation of this:
        // for even powers -> 6^4 => 6^2 * 6^2 and so on
        // for odd powers -> 6^5 => 6^2 * 6^2 * 6

        if(n%2 == 0) return y*y;
        else {
            if(n > 0) return y*y*x;
            else return (y*y)/x;    // in case the power gets negative
        }
    }

    // Leetcode Q: 169. majority element
    public int majorityElement(int[] nums) {
        // we will use a hashmap to track occurence
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int n: nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        int n = nums.length;
        for(int k: map.keySet()) {
            // if any number comes more than n/2 times then return that
            if(map.get(k) > n/2) return k;
        }

        return -1;
    }

    // Leetcode Q: 229. Majority element II
    public List<Integer> majorityElementII(int[] nums) {
        // we will use a hashmap to track occurence
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int n: nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        for(int k: map.keySet()) {
            // if any elements comes more than n/3 times then add it to the list
            if(map.get(k) > n/3) list.add(k);
        }

        return list;
    }

    // Leetcode Q: 62. Unique Paths
    private int uniquePathsutil(int row, int col, int m, int n, int[][] dp) {
        // base cases
        if(row == m-1 || col == n-1) return 1;  // robot has reached the goal
        if(row >= m || col >= n) return 0;    // robot has crossed the boundaries of the box

        // if it is calculted already then return the value
        if(dp[row][col] != -1) return dp[row][col];

        // at each point, robot has two choices, either go down or go right
        int right = uniquePathsutil(row, col+1, m, n, dp);
        int down = uniquePathsutil(row+1, col, m, n, dp);

        return dp[row][col] = right + down;    // add both choices
    }

    public int uniquePaths(int m, int n) {
        // a helper recursive function will be used here, also we will use dynamic programming to reduce operations
        int[][] dp = new int[m][n];
        for(int[] row: dp) {
            Arrays.fill(row, -1);
        }
        dp[m-1][n-1] = 1;   // destinaion will have 1 way only

        return uniquePathsutil(0, 0, m, n, dp);
    }

    // Leetcode Q: 493. Reverse Pairs

    // we will use merge sort here
    private void merge(int[] nums, int start, int mid, int end) {
        List<Integer> list = new ArrayList<>();     // the sorted elements will be here

        // the two pointers
        int i = start;
        int j = mid+1;

        while(i<=mid && j<=end) {
            if(nums[i] <= nums[j]) {
                list.add(nums[i++]);
            } else {
                list.add(nums[j++]);
            }
        }

        while(i<=mid) list.add(nums[i++]);
        while(j<=end) list.add(nums[j++]);

        for(i=start; i<=end; i++) {
            nums[i] = list.get(i - start);
        }
    }

    // merge sort, but this sorting fn will return an int, which is the answer
    private int mergeSort(int[] nums, int start, int end) {
        // base case
        int cnt = 0;

        if(end <= start) return cnt;
        int mid = (start + end)/2;

        // on the left half
        cnt += mergeSort(nums, start, mid);
        // on the right half
        cnt += mergeSort(nums, mid+1, end);
        // count fn
        cnt += count(nums, start, mid, end);
        // the merge fn
        merge(nums, start, mid, end);

        return cnt;
    }

    private int count(int[] nums, int start, int mid, int end) {
        int j = mid + 1;
        int cnt = 0;
        for(int i=start; i<=mid; i++) {
            while(j<=end && nums[i] > (long)nums[j]*2L) j++;
            cnt += (j - mid - 1);
        }

        return cnt;
    }

    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length-1);
    }
}

// Day 1: Arrays

import java.util.*;

public class Day1 {

    // Leetcode Q: 73. Set Matrix Zeros
    public void setZeroes(int[][] matrix) {
        // we can use two hashSets here, one to store row, and other for storing col
        HashSet<Integer> row = new HashSet<>();
        HashSet<Integer> col = new HashSet<>();

        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                // when we get a 0, we will store the row and col in the sets
                if(matrix[i][j] == 0) {
                    row.add(i);
                    col.add(j);
                }
            }
        }

        // modifying the matrix
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                // if this row or col is in any set then this element has to changed into 0
                if(row.contains(i) || col.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
    
    // Leetcode Q: 118. Pascal's Triangle
    private List<Integer> generateRows(int row) {
        List<Integer> list = new ArrayList<>();
        int ans = 1;
        list.add(ans);  // first element is always 1

        // making the row
        for(int i=1; i<row-1; i++) {
            ans *= row-i+1;
            ans /= i;
            list.add(ans);
        }

        if(row>1) list.add(1);    // last element is also always 1 but if the row isn't the first
        return list;
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();

        for(int i=1; i<=numRows; i++) {
            // generate each row
            ans.add(generateRows(i));
        }

        return ans;
    }

    // Leetcode Q: 31. Next Permutation
    public void nextPermutation(int[] nums) {
        // since we need the lexicographically next permutation 
        // or any other words, a number which can be made by these exact same elements and comes exatly after that

        // travese the array in reverse to get the breakpoint
        // breakpoint: the last idx where nums[i]<nums[i+1]
        int idx = -1;
        for(int i=nums.length-1; i>0; i--) {
            if(nums[i-1]<nums[i]) {
                idx = i-1;
                break;
            }
        }

        if(idx == -1) {
            Arrays.sort(nums);    // in this case it was the greatest possible number
            return;
        }

        // else
        // find the index which is greater than nums[idx] but also smallest in range [idx, nums.length]
        int swap = nums[idx+1];
        for(int i=nums.length-1; i>idx; i--) {
            if(nums[i] > nums[idx]) {
                swap = i;
                break;
            }
        }

        // swap them
        int temp = nums[swap];
        nums[swap] = nums[idx];
        nums[idx] = temp;

        // sort the rest
        Arrays.sort(nums, idx+1, nums.length);
    }

    // Leetcode Q: 53. Maximum Subarray (using Kadane's algo)
    public int maxSubArray(int[] nums) {
        // kadane's algo in simple terms -> make sume 0 if it get's -ve
        int sum = 0;
        int maxSum = 0;
        int maxElement = Integer.MIN_VALUE; 

        // traverse the array
        for(int el: nums) {
            sum += el;
            if(sum < 0) sum = 0;    // sum gets -ve => make it 0
            maxElement = Math.max(maxElement, el);
            maxSum = Math.max(sum, maxSum);
        }

        if(maxSum == 0) return maxElement;  // if it is still 0, then return the maximum element
        return maxSum;
    }

    // Leetcode Q: 75. Sort Colors 
    public void sortColors(int[] nums) {
        // we will use count sort as the elements are only 0,1 or 2
        int[] count = new int[3];

        // count each element
        for(int el: nums) {
            count[el]++;
        }

        int idx = 0;
        for(int i=0; i<3; i++) {
            // insert the numbers back
            for(int j=0; j<count[i]; j++) {
                nums[idx++] = i;
            }
        }
    }

    // Leetcode Q: 121. Best Time to Buy and Sell Stock
    public int maxProfit(int[] prices) {
        int min = prices[0];
        int maxProfit = 0;
        
        // for profit to be maximise: the buying price should be lowest and selling price should be highest
        // but the occurence of buying should be before selling
        for(int i=1; i<prices.length; i++) {
            if(min > prices[i]) min = prices[i];    // update the min and move on
            else {
                int profit = prices[i] - min;
                maxProfit = Math.max(profit, maxProfit);    // track the maximum profit
            }
        }

        return maxProfit;
    }
}
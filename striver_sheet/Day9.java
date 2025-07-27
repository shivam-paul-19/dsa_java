// Day 9: Recursion

import java.util.*;

public class Day9 {
    // Not in Leetcode: Subset sum equals to target

    // helper recursive function
    private boolean isSubsetSumUtil(int idx, int[] arr, int target, int sum) {
        // base cases
        if(sum == target) {
            return true;
        } 
        if(idx == arr.length) {
            return false;
        }

        // if we pick the idx element
        boolean pick = isSubsetSumUtil(idx + 1, arr, target, sum + arr[idx]);

        // if we not pick the idx element
        boolean not_pick = isSubsetSumUtil(idx + 1, arr, target, sum);

        // any one case should be true
        return pick || not_pick;
    }

    public boolean isSubsetSum(int[] arr, int target) {
        // we will make a recursive function of testing every combination that matched the target
        return isSubsetSumUtil(0, arr, target, 0);
    }

    // Leetcode Q: 90. Subsets II

    // helper recusive function
    private void subsetsWithDupUtil(int[] nums, int idx, List<Integer> list, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(list)); // add the list to it

        for(int i=idx; i<nums.length; i++) {
            if(i != idx && nums[i] == nums[i-1]) continue;  // duplicacy have to be avoided
            list.add(nums[i]);
            subsetsWithDupUtil(nums, i + 1, list, ans);
            list.removeLast();
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // as this needs need to generate subsets then we will use recursion
        // but first sort the array
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        subsetsWithDupUtil(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    // Leetcode Q: 39. Combination Sum
    private void combinationSumUtil(int idx, int[] candidates, int target, List<Integer> list, List<List<Integer>> ans) {
        if(idx == candidates.length) {
            // if target becomes 0, then add the list
            if(target == 0) {
                ans.add(new ArrayList<>(list));
            }
            return;     // return anyway
        }
        
        if(candidates[idx] <= target) {
            // include it
            list.add(candidates[idx]);
            combinationSumUtil(idx, candidates, target - candidates[idx], list, ans);   // reduce target
            list.removeLast();
        }
        
        // not include
        combinationSumUtil(idx + 1, candidates, target, list, ans);
    }
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // again here we will need to have a recursive function
        List<List<Integer>> ans = new ArrayList<>();
        combinationSumUtil(0, candidates, target, new ArrayList<>(), ans);
        return ans;
    }
    
    // Leetcode Q: 40. Combination Sum II
    private void combinationSum2Util(int idx, int[] candidates, int target, List<Integer> list, List<List<Integer>> ans) {
        if(target == 0) {
            // iƒ target becomes 0 then add it to the list and return
            ans.add(new ArrayList<>(list));
            return;
        }

        for(int i=idx; i<candidates.length; i++) {
            if(i != idx && candidates[i] == candidates[i-1]) continue;  // no duplicacy
            if(candidates[i] > target) break;   // no more combinations can be made
            list.add(candidates[i]);
            combinationSum2Util(i+1, candidates, target - candidates[i], list, ans);
            list.removeLast();
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // again here we will need to have a recursive function
        // first let us sort the array
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        combinationSum2Util(0, candidates, target, new ArrayList<>(), ans);
        return ans;
    }

    // Leetcode Q: 131. Palindrome Partitioning
    // fn to check if palidrome
    private boolean isPalindrome(String s, int start, int end) {
        while(start < end) {
            if(s.charAt(start) != s.charAt(end)) return false;
            start++;
            end--;
        }

        return true;
    }
    
    // helper recursive function
    private void partitionUtil(String s, int idx, List<String> list, List<List<String>> ans) {
        if(idx == s.length()) {
            // if idx has reached this point then add the list of lists
            ans.add(new ArrayList<>(list));
            return;
        }

        for(int i=idx; i<s.length(); i++) {
            if(isPalindrome(s, idx, i)) {
                // if it is palidrome then add it to the list
                list.add(s.substring(idx, i+1));
                partitionUtil(s, i+1, list, ans);
                list.removeLast();
            }
        }
    }

    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        partitionUtil(s, 0, new ArrayList<>(), ans);
        return ans;
    }

    // Leetcode Q: 60. Permutation Sequence
    public String getPermutation(int n, int k) {
        // this can't be implemented in recursion because that will take the time complexity to O(n!)
        // so we will use loop here

        // first calculate the factorial of n-1 and make a list of numbers up to n
        List<Integer> list = new ArrayList<>();
        int fact = 1;

        for(int i=1; i<n; i++) {
            fact *= i;
            list.add(i);
        }
        list.add(n);

        k--;    // we are following 0 based indexing
        StringBuilder sb = new StringBuilder("");
        // run the loop
        while(true) {
            sb.append(list.remove(k / fact));
            if(list.size() == 0) {
                // if no number is left
                break;
            }

            // reduce them for next iterations
            k %= fact;              
            fact /= list.size();
        }

        return sb.toString();
    }
}
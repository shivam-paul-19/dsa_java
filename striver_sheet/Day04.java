// Day 4: Arrays part-IV

import java.util.*;

public class Day04 {
    // leetcode Q: 1. Two sum
    public int[] twoSum(int[] nums, int target) {
        // we can use a hashmap to make it an optimised approach
        // at every point, we will check that was there any (target - nums[i]) element present before it
        // this will make the time complexity to O(n) in average case

        HashMap<Integer, Integer> map = new HashMap<>();
        int[] ans = {-1, -1};
        for(int i=0; i<nums.length; i++) {
            int n = target - nums[i];
            if(map.containsKey(n)) {
                ans[0] = map.get(n);    // get the index
                ans[1] = i;
                break;  // we have got the answer so terminate the loop
            } else {
                map.put(nums[i], i);    // put the element and the index in map
            }
        }

        return ans;
    }

    // Leetcode Q: 18. Four sum
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> ans = new HashSet<>();
        List<Integer> list = new ArrayList<>();

        Arrays.sort(nums);
        int n = nums.length;

        // we will fix the two point i and j in each iteration
        for(int i=0; i<n-3; i++) {
            if(i>0 && nums[i] == nums[i-1]) continue;   // we don't want duplicate pairs
            for(int j=i+1; j<n-2; j++) {
                if(j != i+1 && nums[j] == nums[j-1]) continue;   // we don't want duplicate pairs
                int k = j+1;
                int l = n-1;

                // we will find the ideal k and l to make a valid set of 4
                while(k<l) {
                    long sum = (long)nums[i] + nums[j] + nums[k] + nums[l];
                    if(sum == target) {
                        // we got the target, add them in the list
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        list.add(nums[l]);
                        Collections.sort(list);
                        ans.add(new ArrayList<>(list));
                        list.clear();

                        k++;
                        l--;
                    } else if(sum < target) {
                        // we need to move the k pointer to make the sum larger
                        k++;
                    } else {
                        // we need to move the l pointer to make the sum smaller
                        l--;
                    }
                }
            }
        }

        return new ArrayList<>(ans);
    }

    // Leetcode Q: 128. Longest Consecutive Sequence
    public int longestConsecutive(int[] nums) {
        // egde case 
        if(nums.length == 0) return 0;

        // we will make set or list to store each element
        Set<Integer> set = new HashSet<>();          
        int maxCount = 0;

        for(int n: nums) {
            set.add(n);
        }

        for(int n: set) {
            if(!set.contains(n-1)) {
                int count = 1;   // in any case the answer will be atleast one
                while(set.contains(n+count)) {
                    count++;
                }

                maxCount = Math.max(maxCount, count);   // get the maximum sequence
            }
        }

        return maxCount;
    }

    // Leetcode Q: 3. Longest Substring Without Repeating Characters
    public int lengthOfLongestSubstring(String s) {
        // edge case
        if(s.length() == 0 || s.length() == 1) return s.length();

        // we will use a hashmap to track the indices of each character
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLen = 0;
        int last = -1;  // idx of last repeated char
        int len = 0;

        for(int i=0; i<s.length(); i++) {
            if(map.containsKey(s.charAt(i))) {
                int temp = map.get(s.charAt(i));
                temp = Math.max(temp, last);
                len = i - temp;     // length of sequence from last repeated character
                last = temp;
            } else {
                len++;
            }
            maxLen = Math.max(maxLen, len);     // get the largest sequence
            map.put(s.charAt(i), i);            // update or add the element with its new index
        }

        return maxLen;
    }

    // not in leetcode: largest subarray with sum K
    public int longestSubarray(int[] nums, int k) {
        int maxLen = 0;
        int sum = 0;
        // we will use a prefix sum hashMap in this (prefix sum -> left most idx)
        HashMap<Integer, Integer> prefix = new HashMap<>(); 
        prefix.put(0, -1);

        for(int i=0; i<nums.length; i++) {
            sum += nums[i];
            if(prefix.containsKey(sum - k)) {
                int len = i - prefix.get(sum - k);
                maxLen = Math.max(maxLen, len);
            } else {
                prefix.put(sum, i);     // add the sum and index in it
            }
        }

        return maxLen;
    }

    // not in leetcode: Count subarrays with given xor K
    public int subarraysWithXorK(int[] nums, int k) {
        // we will use a hashMap to store subarray XORs and their freq
        HashMap<Integer, Integer> freq = new HashMap<>();
        int sumXor = 0;
        int count = 0;

        for(int i=0; i<nums.length; i++) {
            sumXor ^= nums[i];
            if(sumXor == k) count++;

            count += freq.getOrDefault(sumXor^k, 0);    // add the frequency
            
            // increase the freq of sum-xor
            freq.put(sumXor, freq.getOrDefault(sumXor, 0) + 1);
        }

        return count;
    }
}

// Day 2: Array part-II

import java.util.*;

public class Day2 {
    // Leetcode Q: 48. Rotate Image
    public void rotate(int[][] matrix) {
        // we have to rotate the matrix in clockwise
        // there is a two step to process to do it: tranpose it -> reverse the rows

        // Transpose it
        for(int i=0; i<matrix.length; i++) {
            for(int j=1; j<matrix[0].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // reverse the rows
        for(int[] row: matrix) {
            for(int i=0; i<row.length/2; i++) {
                int temp = row[i];
                row[i] = row[row.length-i-1];
                row[row.length-i-1] = temp;
            }
        }

        // It is now rotated in place!
    }

    // Leetcode Q: 56. Merge Intervals
    public int[][] merge(int[][] intervals) {
        // first sort the 2D array on the basis of starting time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        // make a list to store intervals
        List<List<Integer>> ansList = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(intervals[0][0]);
        list.add(intervals[0][1]);
        ansList.add(new ArrayList<>(list));     // first interval is added
        list.clear();   // clear this list

        // traverse through the intervals
        for(int i=1; i<intervals.length; i++) {
            int n = intervals[i][0];
            int m = ansList.getLast().get(1);

            // if the starting of this interval is after end of previous, add it
            if(n > m) {
                list.add(intervals[i][0]);
                list.add(intervals[i][1]);
                ansList.add(new ArrayList<>(list));
                list.clear();
            } else if(m < intervals[i][1]) {
                // if ending time of the current interval is after previous, include it
                list.add(ansList.getLast().get(0));
                list.add(intervals[i][1]);
                ansList.removeLast();
                ansList.add(new ArrayList<>(list));
                list.clear();
            }
        }

        // put it into an array
        int[][] ans = new int[ansList.size()][2];
        for(int i=0; i<ans.length; i++) {
            ans[i][0] = ansList.get(i).get(0);
            ans[i][1] = ansList.get(i).get(1);
        }

        return ans;
    }

    // Leetcode Q: 88. Merge Sorted Array
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // make an extra array for storing elements of num1
        int[] newArr = new int[m];
        for(int i=0; i<m; i++) {
            newArr[i] = nums1[i];
        }

        // merge them with two pointer
        int i=0;
        int j=0;
        int idx = 0;
        while(i<m && j<n) {
            // the smaller number will be placed and its pointer will be incremented
            if(newArr[i] <= nums2[j]) {
                nums1[idx++] = newArr[i++];
            } else {
                nums1[idx++] = nums2[j++];
            }
        }

        // if some elements are left in any of the arrays
        while(i<m) nums1[idx++] = newArr[i++];
        while(j<n) nums1[idx++] = nums2[j++];

        // nums1 has now sorted merged elements of both arrays!
    }

    // Leetocode Q: 287. Find the Duplicate Number
    public int findDuplicate(int[] nums) {
        // we will use a hashSet to store elements
        HashSet<Integer> set = new HashSet<>();

        for(int n: nums) {
            if(set.contains(n)) {
                // if set has this element then we got the duplicate element
                return n;
            }
            set.add(n);
        }

        return -1;
    }

    // not in leetocode
    public int[] findMissingRepeatingNumbers(int[] nums) {
        // we can use a frequence array as we know the range
        int[] freq = new int[nums.length+1];
        for(int i=0; i<nums.length; i++) {
            freq[nums[i]]++;
        }

        int missing = -1;
        int two = -1;

        // now see which is double and which is missing
        for(int i=1; i<=nums.length; i++) {
            if(freq[i] == 2) two = i;
            if(freq[i] == 0) missing = i;

            if(missing != -1 && two != -1) break;
        }

        return new int[]{two, missing};
    }

    // not in leetcode

    // merge function for the merge sort (here the modification will occur)
    private long merge(int[] nums, int start, int mid, int end) {
        long count = 0;
        List<Integer> list = new ArrayList<>();     // the sorted elements will be here

        // the two pointers
        int i = start;
        int j = mid+1;

        while(i<=mid && j<=end) {
            if(nums[i] <= nums[j]) {
                list.add(nums[i++]);
            } else {
                count += (mid - i + 1);     // this is the modification
                list.add(nums[j++]);
            }
        }

        while(i<=mid) list.add(nums[i++]);
        while(j<=end) list.add(nums[j++]);

        for(i=start; i<=end; i++) {
            nums[i] = list.get(i - start);
        }

        return count;
    }

    // merge sort, but this sorting fn will return an int, which is the answer
    private long mergeSort(int[] nums, int start, int end) {
        long count = 0;

        // base case
        if(end <= start) return count;

        int mid = (start + end)/2;

        // on the left half
        count += mergeSort(nums, start, mid);
        // on the right half
        count += mergeSort(nums, mid+1, end);
        // the merge fn
        count += merge(nums, start, mid, end);

        return count;
    }

    public long numberOfInversions(int[] nums) {
        // we will just implement merge sort with a slight modification
        return mergeSort(nums, 0, nums.length-1);
    }
}

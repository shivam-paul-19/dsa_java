// Day 11: Binary search

import java.util.Arrays;

public class Day11 {
    // Not in leetcode: Find nth root of a number
    public int NthRoot(int N, int M) {
        // the answer will always range between 1 and M, in any case
        // so we will use binary search
        int low = 1;
        int high = M;

        while(low <= high) {
            int mid = (low + high)/2;
            int power = (int)Math.pow(mid, N);

            if(power == M) {
                // if root found then return it
                return mid; 
            } else if(power > M) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }

        return -1;  // if no integer root found
    }

    // not in leetcode: Matrix median
    private int upperBound(int n, int[] arr) {
        int low = 0;
        int high = n-1;

        while(low <= high) {
            int mid = (low+high)/2;

            if(arr[mid] >= n) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }

        return low+1;
    }

    private int countLessEq(int n, int[][] matrix) {
        int cnt = 0;
        for(int[] row: matrix) {
            // as given that every row is sorted
            cnt += upperBound(n, row);
        }
        return cnt;
    }

    public int findMedian(int[][] matrix) {
        // get the max and min of the matrix
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int[] row: matrix) {
            for(int r: row) {
                min = Math.min(r, min);
                max = Math.max(r, max);
            }
        }

        int threshold = (matrix.length*matrix[0].length)/2;
        // so now the median lies between this max and min for sure
        // time to perform binary search
        while(min <= max) {
            int mid = (min + max)/2;
            // get the number of el less than equal to mid

            int freq = countLessEq(mid, matrix);

            // we need the first element whose freq of less than eq element will be more than threshold
            if(freq > threshold) {
                max = mid-1;
            } else {
                min = mid+1;
            }
        }

        return min;
    }

    // Leetcode Q: 540. Single Element in a Sorted Array
    public int singleNonDuplicate(int[] nums) {
        // egde case
        if(nums.length == 1) return nums[0];

        // we know its a sorted array so it is best to apply binary search
        int low = 0;
        int high = nums.length-1;

        while(low <= high) {
            int mid = (low+high)/2;

            // if it is the first element
            if(mid == 0) {
                if(nums[mid] != nums[mid+1]) return nums[mid];
                else break;
            }
            // if it is the last element
            if(mid == nums.length-1 && nums[mid] != nums[mid-1]) {
                if(nums[mid] != nums[mid-1]) return nums[mid]; 
                else break;
            }

            // if it is somewhere in middle and we found it
            if(nums[mid] != nums[mid-1] && nums[mid] != nums[mid+1]) {
                return nums[mid];
            }

            // so we haven't found the number yet
            // let's say mid is odd index
            if(mid%2!=0) {
                // if nums[mid] != nums[mid+1] then it means, all elements before it are in pair, so go in right
                if(nums[mid] != nums[mid+1]) low = mid+1;
                else high = mid-1;
            } else {
                // and if mid is even then the case will be opposite
                if(nums[mid] != nums[mid+1]) high = mid-1;
                else low = mid+1;
            }
        }

        return -1;  // we found nothing
    }

    // Leetcode Q: 33. Search in Rotated Sorted Array
    public int search(int[] nums, int target) {
        // edge case 
        if(nums.length == 1) {
            return (target == nums[0])? 0:-1;
        }

        // our first task is to find the pivot point where the array is rotated
        int n = nums.length;
        int pivot = 0;
        for(int i=1; i<n; i++) {
            if(nums[i] < nums[i-1]) {
                pivot = i;
                break;
            }
        }

        // now as we got a pivot point, the array is divided into two parts
        // we have to decide where the target might be
        int low = 0;
        int high = n-1;
        if(target > nums[n-1]) {
            // in this case, the target is in the first half
            low = 0;
            high = pivot-1;
        } else {
            // in this case, the target is in the second half
            low = pivot;
            high = n-1;
        }

        // now perform binary search
        while(low <= high) {
            int mid = (low+high)/2;

            // target found
            if(nums[mid] == target) return mid;
            // mid is less
            else if(nums[mid] < target) low = mid + 1;
            // mis is greater
            else high = mid - 1;
        }

        // we found nothing
        return -1;
    }

    // Leetcode Q: 4. Median of Two Sorted Arrays
    private int[] merge(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] res = new int[len1+len2];

        int i=0;
        int j=0;
        int k=0;
        while(i<len1 && j<len2) {
            if(nums1[i] < nums2[j]) {
                res[k++] = nums1[i++];
            } else {
                res[k++] = nums2[j++];
            }
        }

        while(i<len1) {
            res[k++] = nums1[i++];
        }
        while(j<len2) {
            res[k++] = nums2[j++];
        }

        return res;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // as we can see both arrays sorted, so just merge them like we do in merge sort
        int[] merged = merge(nums1, nums2);
        int n = merged.length;

        double ans = 0.0;
        // edge case
        if(n==0) return ans;

        if(n%2==0) {
            // in this case we will have two median
            int mid1 = n/2;
            int mid2 = mid1-1;

            // average them
            ans = (merged[mid1] + merged[mid2])/2.0;
        } else {
            ans = (double)merged[n/2];
        }

        return ans;
    }

    // Not in leetcode: Kth element of 2 sorted arrays
    public int kthElement(int[] a, int[] b, int k) {
        // as again both arrays are sorted, so let's reuse the function of merge
        int[] merged = merge(a, b);

        // just return the element now
        return merged[k-1];
    }

    // Not in Leetcode: Book allocation problem

    // this helper fn will tell us how many students can get books
    private int allocateBooks(int[] nums, int maxPages) {
        int pages = 0;
        int students = 1;   // atleast one student will get books

        for(int i=0; i<nums.length; i++) {
            if(nums[i] > maxPages) {
                // in this case, it is not possible to allocate all books
                return Integer.MAX_VALUE;
            }

            // if the pages go beyond max range, then increase the students
            if(pages + nums[i] > maxPages) {
                pages = nums[i];
                students++;
            } else {
                pages += nums[i];
            }
        }

        return students;
    }

    public int findPages(int[] nums, int m) {
        // we can allocate atleast the lowest paged books
        // or we can allocate atmost all the books to one
        // as we know the range, it will take us to the binary search

        // get minimun and maximum
        int high = 0;
        int low = Integer.MAX_VALUE;
        for(int n: nums) {
            high += n;
            low = Math.min(low, n);
        }

        // apply binary search
        while(low <= high) {
            int mid = (low+high)/2;

            if(allocateBooks(nums, mid) >= m) {
                // if can allocate more than or eq to m students, then increase the number
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }

    // Not in Leetcode: Aggresive cows

    // this helper fn will tell us how many cows we can place
    private int placeCows(int[] nums, int minDistance) {
        int cows = 1;
        int last = nums[0];     // place first cow at 0th index

        for(int i=1; i<nums.length; i++) {
            if(nums[i] - last >= minDistance) {
                // in this case we can place another cow
                last = nums[i];
                cows++;
            }
        }

        return cows;
    }

    public int aggressiveCows(int[] nums, int k) {
        // first of all let us sort the array
        Arrays.sort(nums);

        // so the minimun distance can be 1 and the maximum can be nums[n-1] - nums[0]
        // now we have a range so we will use binary search here

        int low = 1;
        int high = nums[nums.length-1] - nums[0];

        while(low <= high) {
            int mid = (low + high)/2;

            if(placeCows(nums, mid) >= k) {
                // we can place more than or equal to k cows, so we will try higher number
                low = mid + 1;
            } else {
                // we can't place k cows, time to lower the number
                high = mid - 1;
            }
        }

        return high;    // we will return the higher
    }
}
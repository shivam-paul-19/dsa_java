// Leetcode Q: 540. Single Element in a Sorted Array

public class singleNonDupl {
    public static int singleNonDuplicate(int[] nums) {
        if(nums.length == 1) {
            return nums[0];
        }
        int s = 0;
        int e = nums.length - 1;
        while(s<=e) {
            int mid = s + (e - s)/2;
            if(mid == 0) {
                if(nums[mid] != nums[mid+1]) {
                    return nums[mid];
                }
            } else if(mid == nums.length-1) {
                if(nums[mid] != nums[mid-1]) {
                    return nums[mid];
                }
            } else {
                if(nums[mid] != nums[mid-1] && nums[mid] != nums[mid+1]) {
                    return nums[mid];
                }
            }

            if(mid%2==0) {
                if(nums[mid] == nums[mid-1]) {
                    e = mid-1;
                } else {
                    s = mid+1;
                }
            } else {
                if(nums[mid] == nums[mid-1]) {
                    s = mid+1;
                } else {
                    e = mid-1;
                }
            }

        }

        return -1;
    }
    
    public static void main(String[] args) {
        int[] arr = {1,1,2,3,3,4,4,8,8};
        System.out.println(singleNonDuplicate(arr));
    }
}
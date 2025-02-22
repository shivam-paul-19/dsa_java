// Leetcode Q: 896. Monotonic Array

public class monotonic {
    public static boolean isMonotonic(int[] nums) {
        if(nums.length <= 2) {
            return true;
        }
        
        boolean isMono = true;
        for(int i=0; i<nums.length-1; i++) {
            if(nums[i] > nums[i+1]) {
                isMono = false;
                break;
            }
        }

        if(!isMono) {
            isMono = true;
            for(int i=0; i<nums.length-1; i++) {
                if(nums[i] < nums[i+1]) {
                    isMono = false;
                    break;
                }
            }
        }

        return isMono;
    }

    public static void main(String[] args) {
        int[] arr = {11,11,9,4,3,3,3,1,-1,-1,3,3,3,5,5,5};
        System.out.println(isMonotonic(arr));
    }
}

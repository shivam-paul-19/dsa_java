// Leetcode Q: 3151. Special array

public class specialArr {
    public static boolean isArraySpecial(int[] nums) {
        if(nums.length == 1) {
            return true;
        }

        for(int i=0; i<nums.length-1; i++) {
            if(nums[i]%2 == nums[i+1]%2) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        System.out.println(isArraySpecial(arr));
    }
}

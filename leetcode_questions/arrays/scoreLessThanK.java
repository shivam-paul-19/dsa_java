// Leetocde Q: 2302. Count Subarrays With Score Less Than K

public class scoreLessThanK {
    public long countSubarrays(int[] nums, long k) {
        long sum = 0L;
        long count = 0L;
        int left = 0;

        for(int right=0; right<nums.length; right++) {
            sum += nums[right];
            int size = right - left + 1;
            while(left <= right && sum*size >= k) {
                sum -= nums[left];
                size--;
                left++;
            }

            count += size;
        }

        return count;
    }
}

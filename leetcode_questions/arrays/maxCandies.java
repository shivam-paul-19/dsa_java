// Leetcode Q: 2226. Maximum Candies Allocated to K Children

public class maxCandies {
    public static boolean canAlloc(int n, int[] candies, long k) {
        if(n == 0) return true;

        long alloc = 0;
        for(int c: candies) {
            alloc += c/n;
        }
        
        return alloc >= k;
    }

    public static int maximumCandies(int[] candies, long k) {
        int sum = 0;
        int max = candies[0];

        for(int n: candies) {
            max = Math.max(n, max);
            sum += n;
        }

        if(sum < k) return 0;

        int least = 1;
        int most = max;
        int mid = 0;

        while(least < most) {
            mid = least + (most-least)/2;
            if(canAlloc(mid, candies, k)) {
                least = mid+1;
            } else {
                most = mid-1;
            }
        }

        return mid;
    }
}

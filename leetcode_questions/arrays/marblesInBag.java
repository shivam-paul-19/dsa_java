// Leetcode Q: 2551. Put Marbles in Bags

import java.util.Arrays;

public class marblesInBag {
    public long putMarbles(int[] weights, int k) {
        int[] pairs = new int[weights.length-1];
        for(int i=0; i<pairs.length; i++) {
            pairs[i] = weights[i] + weights[i+1];
        }

        Arrays.sort(pairs);
        long ans = 0l;

        for(int i=0; i<k-1; i++) {
            ans += pairs[pairs.length - i - 1] - pairs[i];
        }

        return ans;
    }
}

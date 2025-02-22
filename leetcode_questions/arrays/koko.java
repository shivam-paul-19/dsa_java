// Leetcode Q: 875. Koko Eating Bananas

import java.util.*;

public class koko {
    public static long calculateHours(int k, int[] piles) {
        long h=0;
        for(int p: piles) {
            int div = p/k;
            h += (p%k==0) ? div : div+1;
        }

        return h;
    }

    public static int minEatingSpeed(int[] piles, int h) {
        Arrays.sort(piles);
        if(piles.length == h) {
            return piles[piles.length-1];
        }

        int low = 1;
        int high = piles[piles.length-1];

        while(low<=high) {
            int mid = low + (high - low) / 2;
            if(calculateHours(mid, piles) > h) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }

        return low;
    }

    public static void main(String[] args) {
        int[] piles = {805306368,805306368,805306368};
        System.out.println(minEatingSpeed(piles, 1000000000));
    }
}

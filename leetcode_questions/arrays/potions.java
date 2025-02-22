//Leetcode Q: 2300. Successful Pairs of Spells and Potions

import java.util.*;

public class potions {
    public static int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int n = potions.length;
        int[] res = new int[spells.length];
        for(int s=0; s<spells.length; s++) {
            int low=0;
            int high=n-1;
            while(low<=high) {
                int mid = low + (high - low) / 2;
                if(success > (long)spells[s]*potions[mid]) {
                    low = mid+1;
                } else {
                    high = mid-1;
                }
            }

            res[s] = n-low;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] spells = {3,1,2};
        int[] potions = {8,5,8};
        long success = 16;
        System.out.println(Arrays.toString(successfulPairs(spells, potions, success)));
    }
}

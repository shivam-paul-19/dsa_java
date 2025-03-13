// Leetcode Q: 3356. Zero Array Transformation II

public class zeroTransformII {
    public int minZeroArray(int[] nums, int[][] queries) {
        int d = 0;
        int decrement = 0;
        int[] newArr = new int[nums.length + 1];

        for(int i=0; i<nums.length; i++) {
            while(decrement + newArr[i] < nums[i]) {
                if(d == queries.length) return -1;

                int l = queries[d][0];
                int r = queries[d][1];
                int val = queries[d][2];
                d++;

                if(r < i) continue;

                newArr[Math.max(l, i)] += val;
                newArr[r] -= val;
            }

            decrement += newArr[i];
        }

        return d;
    }
}

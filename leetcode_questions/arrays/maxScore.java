// LeetCode Q: 2818. Apply Operations to Maximize Score

import java.util.*;

public class maxScore {
    final int MOD = 1000000007;
    private long power(long base, long exponent) {
        long res = 1;
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                res = (res * base) % MOD;
            }

            base = (base * base) % MOD;
            exponent /= 2;
        }

        return res;
    }

    public int maximumScore(List<Integer> nums, int k) {
        int[] primeScore = new int[nums.size()];

        Arrays.fill(primeScore, 0);
        for(int n=0; n<nums.size(); n++) {
            int el = nums.get(n);
            for(int i=2; i<=Math.sqrt(el); i++) {
                if(el%i == 0) primeScore[n]++;
                while(el%i == 0) {
                    el /= i;
                }
            }

            if(el>=2) primeScore[n]++;
        }

        int[] nextDominant = new int[nums.size()];
        int[] prevDominant = new int[nums.size()];
        Arrays.fill(nextDominant, nums.size());
        Arrays.fill(prevDominant, -1);
        Stack<Integer> s = new Stack<>();

        for(int i=0; i<nums.size(); i++) {
            while(!s.isEmpty() && primeScore[s.peek()] < primeScore[i]) {
                nextDominant[s.pop()] = i;
            }

            if(!s.isEmpty()) prevDominant[i] = s.peek();
            s.push(i);
        }

        long[] subArr = new long[nums.size()];
        for(int i=0; i<nums.size(); i++) {
            subArr[i] = ((long)nextDominant[i] - i)*(i - prevDominant[i]);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[0] == b[0]) return Integer.compare(a[1], b[1]);
            return Integer.compare(b[0], a[0]);
        });

        for(int i=0; i<nums.size(); i++) {
            pq.add(new int[] {nums.get(i), i});
        }

        long score = 1;
        while(k > 0) {
            int[] top = pq.poll();
            int n = top[0];
            int idx = top[1];

            long op = Math.min((long)k, subArr[idx]);
            score = (score * power(n, op)) % MOD;
            k -= op;
        }

        return (int)score;
    }
}

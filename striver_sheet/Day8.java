// Day 8: Greedy algorithm

import java.util.*;

public class Day8 {
    // Not in Leetcode: N meetings in one room
    public int maxMeetings(int[] start, int[] end) {
        // lets make a 2D array of starting and ending time of the meetings
        int n = start.length;
        int[][] meetings = new int[n][2];
        for(int i=0; i<n; i++) {
            meetings[i][0] = start[i];
            meetings[i][1] = end[i];
        }

        // sort the array according to ending time in ascending order
        Arrays.sort(meetings, (a, b) -> a[1] - b[1]);
        
        int res = 1;
        int last = meetings[0][1];  // ending time of last meeting 
        for(int i=1; i<n; i++) {
            if(meetings[i][0] > last) {
                // if the next meeting start after end of the last meeting then the current meeting can happen
                res++;
                last = meetings[i][1];
            }
        }

        return res;
    }

    // Not in leetcode: Minimum number of platforms required for a railway
    public int findPlatform(int[] Arrival, int[] Departure) {
        // again make a 2D array of combining them both
        // arr[i][0] -> time, arr[i][1] -> arriaval/departure
        int n = Arrival.length;
        int[][] timing = new int[n*2][2];

        for(int i=0; i<n*2; i++) {
            if(i<n) {
                timing[i][0] = Arrival[i];
                timing[i][1] = 0;   // 0 -> arrival
                continue;
            }
            timing[i][0] = Departure[i-n];
            timing[i][1] = 1;   // 1 -> departure
        }

        // sort this in ascending order in terms of time
        Arrays.sort(timing, (a, b) -> a[0] - b[0]);
        int p = 0;      // number of platforms
        int maxP = 0;   // max platforms

        for(int i=0; i<n*2; i++) {
            if(timing[i][1] == 0) {
                // there's an arrival, we need platform
                p++;
            } else {
                // if there's a departure, one platform is free
                p--;
            }

            // track the peak
            maxP = Math.max(maxP, p);
        }

        return maxP;
    }

    // Not in Leetcode: Job sequencing Problem
    public int[] JobScheduling(int[][] Jobs) {
        int n = Jobs.length;
        // sort the jobs in descending order in terms of profit
        Arrays.sort(Jobs, (a, b) -> b[2] - a[2]);
        // make a array to track time slots
        int[] time = new int[Jobs[n-1][1] + 1];   // length of the the last deadline
        Arrays.fill(time, -1);
        int maxProfit = 0;  // maximum profit earned
        int j = 0;          // number of jobs done

        for(int i=0; i<n; i++) {
            if(j == time.length) break;     // all the time slots are full

            int profit = Jobs[i][2];
            int t = Jobs[i][1];
            if(time[t] == -1) {
                time[t] = 1;    // occupied
                maxProfit += profit;
                j++;
            } else {
                // find the idle day before t
                while(t >= 0 && time[t] != -1) {
                    t--;
                }
                if(t != 0) {
                    // if the t had reached 0 then no day is left
                    time[t] = 1;    // occupied
                    maxProfit += profit;
                    j++;
                }
            }
        }

        return new int[]{j, maxProfit};
    }

    public double fractionalKnapsack(int[] val, int[] wt, long cap) {
        // we will make n by 3, 2D matrix
        // matrix[i][0] -> val, matrix[i][1] -> wt, matrix[i][2] -> val/wt (value per 1 unit of wt) 
        int n = val.length;
        double[][] matrix = new double[n][3];

        for(int i=0; i<n; i++) {
            matrix[i][0] = val[i];
            matrix[i][1] = wt[i];
            matrix[i][2] = (double)val[i]/wt[i];
        }

        // now sort the matrix in descending order in terms of val per 1 unit wt
        Arrays.sort(matrix, (a, b) -> Double.compare(b[2], a[2]));
        double profit = 0;

        // we will add items till the cap becomes 0
        int i = 0;
        while(i < n && cap > 0) {
            if(cap >= matrix[i][1]) {
                profit += matrix[i][0];
                cap -= matrix[i][1];
            } else {
                profit += matrix[i][2]*cap;
                cap = 0;    // capacity over
            }
            i++;
        }

        return profit;
    }

    // Not in leetcode: Minimum coins
    public int MinimumCoins(int[] coins, int amount) {
        // we will sort the coins
        Arrays.sort(coins);

        // we will try to use coins with larger denomination
        int i = coins.length - 1;
        int c = 0;
        while(i >= 0 && amount > 0) {
            if(amount >= coins[i]) {
                amount -= coins[i];
                c++;
            } else {
                i--;
            }
        }

        if(amount == 0) return c;
        else return -1;
    }

    // Leetcode Q: 455. Assign Cookies
    public int findContentChildren(int[] g, int[] s) {
        // greedy approach should be taken, sort the both arrays
        // assign the less sized cookie to the child with less greed factor
        Arrays.sort(g);
        Arrays.sort(s);

        int res = 0;
        // iterate over s array
        int child = 0;  // child pointer
        for(int i=0; i<s.length; i++) {
            if(child >= g.length) break;    // if no child is left

            if(g[child] <= s[i]) {
                // if the condition matches, move the child pointer to the next child and increase the res
                res++; child++;
            }
        }

        return res;
    }
}

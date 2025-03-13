// Dynamic programming patterns
/*
 * 1. Fibonacci / Climbing stairs
 * 2. 0-1 knapsack
 * 3. Unbounded knapsack
 * 4. Longest common subsequence 
 * 5. Kadane's Algorithm (subarray sum)
 * 6. Catalan Number
 * 7. DP on grif (2D array)
 */

public class Dynamic_prog {
    // Fibonacci using normal recursion
    public static int fibonacci(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;

        return fibonacci(n-1) + fibonacci(n-2);
    }

    // Fibonacci using memoization
    public static int fibonacci(int n, int[] dp) {
        if(n == 0) return 0;
        if(n == 1) return 1;

        if(dp[n] != 0) return dp[n];

        return fibonacci(n-1) + fibonacci(n-2);
    }

    // Fiboacci using Tabulation
    public static int fibonacci_tab(int n) {
        if(n == 0) return 0;

        int[] dp = new int[n+1];
        dp[1] = 1;

        for(int i=2; i<=n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    // climbing stairs using tabulation (same as fibonacci)
    public static int climbingStairs(int n) {
        if(n == 0) return 0;

        int[] dp = new int[n+1];
        dp[1] = 1;

        for(int i=2; i<=n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    // 0-1 Knapsack recurision
    public static int knapsack(int[] val, int[] wt, int w, int n) {
        if(n == 0 || w == 0) {
            return 0;
        }

        if(wt[n-1] <= w) {
            int ans1 = knapsack(val, wt, w - wt[n-1], n-1);     // include
            int ans2 = knapsack(val, wt, w, n-1);       // exclude

            return Math.max(ans1, ans2);
        } else {
            return knapsack(val, wt, w, n-1);
        }
    }

    // 0-1 knapsack memoisation
    public static int knapsack_memo(int[] val, int[] wt, int w, int n, int[][] dp) {
        if(n == 0 || w == 0) {
            return 0;
        }

        if(dp[w][n] != -1) {
            return dp[w][n];
        }

        if(wt[n-1] <= w) {
            int ans1 = knapsack_memo(val, wt, w - wt[n-1], n-1, dp);     // include
            int ans2 = knapsack_memo(val, wt, w, n-1, dp);       // exclude

            return Math.max(ans1, ans2);
        } else {
            return knapsack_memo(val, wt, w, n-1, dp);
        }
    }

    // 0-1 knapsack tabulation
    public static int knapsack_tab(int[] val, int[] wt, int w, int n) {
        int[][] dp = new int[w+1][n+1];

        for(int i=1; i<=w; i++) {
            for(int j=1; j<=n; j++) {
                int v = val[i-1];
                int curr_w = wt[i-1];

                if(curr_w <= j) {
                    int ans1 = v + dp[i-1][j-curr_w];   // include
                    int ans2 = dp[i-1][j];      // exclude

                    dp[i][j] = Math.max(ans1, ans2);
                } else {
                    dp[i][j] = dp[i-1][j]; 
                }
            }
        }

        return dp[w][n];
    }
}

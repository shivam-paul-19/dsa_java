// leetcode Q: 1143. Longest Common Subsequence

public class lcs {
    public static int longestCommonSubsequence(String text1, String text2) {
        int row = text1.length()+1;
        int cols = text2.length()+1;
        int[][] dp = new int[row][cols];

        for(int i=1; i<row; i++) {
            for(int j=1; j<cols; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[row-1][cols-1];
    }
}

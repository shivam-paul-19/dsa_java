// Leetcode Q: 1092. Shortest Common Supersequence 

public class scs {
    public static String shortestCommonSupersequence(String str1, String str2) {
        int row = str1.length()+1;
        int cols = str2.length()+1;
        int[][] dp = new int[row][cols];

        for(int i=0; i<row; i++) {
            for(int j=0; j<cols; j++) {
                if (i == 0) dp[i][j] = j;
                if (j == 0) dp[i][j] = i; 
            }
        }

        for(int i=1; i<row; i++) {
            for(int j=1; j<cols; j++) {
                if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int i=row-1, j=cols-1;
        while(i>0 && j>0) {
            if(str1.charAt(i-1) == str2.charAt(j-1)) {
                sb.append(str1.charAt(i-1));
                i--;
                j--;
            } else if(dp[i-1][j] < dp[i][j-1]) {
                sb.append(str1.charAt(i-1));
                i--;
            } else {
                sb.append(str2.charAt(j-1));
                j--;
            }
        }

        while(i>0) {
            sb.append(str1.charAt(i-1));
            i--;
        }
        while(j>0) {
            sb.append(str2.charAt(j-1));
            j--;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String s = shortestCommonSupersequence("", "cab");
        System.out.println(s);
    }
}
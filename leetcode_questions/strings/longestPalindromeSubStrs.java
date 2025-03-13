// Leetcode Q: 5. Longest Palindromic Substring

public class longestPalindromeSubStrs {
    public static boolean isPalidrome(String s) {
        int left = 0;
        int right = s.length()-1;

        while(left<right) {
            if(s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }

        return true;
    }

    public static String longestPalindrome(String s) {
        String res = new String("");

        for(int left=0; left<s.length(); left++) {
            for(int right=left+1; right<=s.length(); right++) {
                if (isPalidrome(s.substring(left, right)) && res.length() < right - left) {
                    res = s.substring(left, right);
                }
            }
        }

        return res;
    }
}

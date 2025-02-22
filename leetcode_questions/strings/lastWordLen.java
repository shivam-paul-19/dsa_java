// Leetcode Q: 58. Length of Last Word

public class lastWordLen {
    public static int lengthOfLastWord(String s) {
        int len = 0;
        s = s.trim();
        for(int i=s.length()-1; i>=0; i--) {
            if(s.charAt(i) == ' ') {
                break;
            }
            len++;
        }

        return len;
    }
}

// Leetcode Q: 38. Count and Say

public class countNsay {
    public static String countAndSay(int n) {
        if(n == 1) return "1";
        if(n == 2) return "11";

        String prev = countAndSay(n-1);
        String res = "";
        int idx = 0;
        while(idx < prev.length()) {
            int c = 1;
            char ch = prev.charAt(idx++);
            while(idx < prev.length() && ch == prev.charAt(idx)) {
                c++;
                idx++;
            }
            res += (Integer.toString(c) + ch);
        }

        return res;
    }
}
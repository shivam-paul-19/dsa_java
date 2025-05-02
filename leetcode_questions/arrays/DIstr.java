// Leetcode Q: 942. DI String Match

public class DIstr {
    public int[] diStringMatch(String s) {
        int[] ans = new int[s.length() + 1];

        int i=0;
        int n=0;
        while(i<s.length()) {
            if(s.charAt(i) == 'I') {
                ans[i] = n++;
            } else {
                ans[i] = -1;
            }
            i++;
        }

        ans[ans.length-1] = n++;
        i = ans.length-1;
        while(i>=0) {
            if(ans[i] == -1) {
                ans[i] = n++;
            }
            i--;
        }

        return ans;
    }
}
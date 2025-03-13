// Leetcode Q: 3208. Alternating Groups II

public class alternateWheel {
    public static int numberOfAlternatingGroups(int[] colors, int k) {
        int res = 0;
        int currAlt = 1;
        int last = colors[0];
        for(int i=1; i<colors.length+k-1; i++) {
            int currCol = colors[i%colors.length];
            if(currCol == last) {
                currAlt = 1;
                last = colors[i%colors.length];
                continue;
            }
            currAlt++;
            if(currAlt >= k) res++;
            last = colors[i%colors.length];
        }
        
        return res;
    }
}

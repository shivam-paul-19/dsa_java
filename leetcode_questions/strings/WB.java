// Leetcode Question: 2379. Minimum Recolors to Get K Consecutive Black Blocks

public class WB {
    public static int minimumRecolors(String blocks, int k) {
        if(k > blocks.length()) return 0;
            
        int white = 0;
        
        int i=0;
        for(i=0; i<k && i<blocks.length(); i++) {
            char c = blocks.charAt(i);
            if (c == 'W') white++;
        }
        
        if (i==blocks.length()) return white;
        int minBlack = white;
        while(i<blocks.length()) {
            char lastC = blocks.charAt(i);
            char firstC = blocks.charAt(i-k);
            if(firstC == 'W') white--;
            if(lastC == 'W') white++;
            minBlack = Math.min(minBlack, white);
            i++;
        }

        return minBlack;
    }
}

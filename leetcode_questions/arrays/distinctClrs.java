// 3160. Find the Number of Distinct Colors Among the Balls

import java.util.*;

public class distinctClrs {
    public int[] queryResults(int limit, int[][] queries) {
        int[] ans = new int[queries.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> colorMap = new HashMap<>();
        for(int i=0; i<queries.length; i++) {
            int ball = queries[i][0];
            int color = queries[i][1];
            if(map.containsKey(ball)) {
                int prevClr = map.get(ball);
                if(prevClr != color) {
                    if(colorMap.get(prevClr) == 1) {
                        colorMap.remove(prevClr);
                    } else {
                        colorMap.put(color, colorMap.get(prevClr) - 1);
                    }
                }
            }
            
            colorMap.put(color, colorMap.getOrDefault(color, 0) + 1);
            map.put(ball, color);
            ans[i] = colorMap.keySet().size();
        }
        return ans;
    }
} 
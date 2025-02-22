// Leetcode Q: 841. Keys and Rooms

import java.util.*;

public class keyNrooms {
    private static void dfs(List<List<Integer>> rooms, HashSet<Integer> isVis, int curr) {
        isVis.add(curr);
        System.out.println(curr);
        List<Integer> l = rooms.get(curr);
        for(int j=0; j<l.size(); j++) {
            if(!isVis.contains(l.get(j))) {
                dfs(rooms, isVis, l.get(j));
            }
        }  
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        HashSet<Integer> isVis = new HashSet<>();
        dfs(rooms, isVis, 0);
        return rooms.size() == isVis.size();
    }
}

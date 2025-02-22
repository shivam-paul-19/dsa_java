// 56. Merge Intervals

import java.util.*;

public class mergeInt {
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (arr1, arr2) -> arr1[0] - arr2[0]);
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> l = new ArrayList<>();
        l.add(intervals[0][0]);
        l.add(intervals[0][1]);
        list.add(new ArrayList<>(l));
        l.clear();
        for(int i=1; i<intervals.length; i++) {
            int m = list.getLast().get(1);
            int n = intervals[i][0];
            if(n>m) {
                l.add(intervals[i][0]);
                l.add(intervals[i][1]);
                list.add(new ArrayList<>(l));
                l.clear();
            } else {
                if(m < intervals[i][1]) {
                    l.add(list.getLast().get(0));
                    l.add(intervals[i][1]);
                    list.removeLast();
                    list.add(new ArrayList<>(l));
                    l.clear();
                }
            }
        }
        int[][] res = new int[list.size()][2];
        for(int i=0; i<res.length; i++) {
            int[] temp = new int[2];
            temp[0] = list.get(i).get(0);
            temp[1] = list.get(i).get(1);
            res[i] = temp;
        }
        return res;
    }
}

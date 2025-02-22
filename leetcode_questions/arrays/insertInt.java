import java.util.*;

public class insertInt {
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals.length == 0) {
            int[][] res = {newInterval};
            return res;
        }

        ArrayList<ArrayList<Integer>> ansList = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();

        int i=0;
        while(i<intervals.length && intervals[i][1] < newInterval[0]) {
            list.add(intervals[i][0]);
            list.add(intervals[i][1]);
            ansList.add(new ArrayList<>(list));
            list.clear();
            i++;
        }
        if(i == intervals.length) {
            list.add(newInterval[0]);
            list.add(newInterval[1]);
            ansList.add(new ArrayList<>(list));
        } else {
            list.add(intervals[i][0]);
            while(i<intervals.length && intervals[i][0] < newInterval[1]) {
                i++;
            }
    
            if(i == intervals.length) {
                list.add(Math.max(intervals[i-1][1], newInterval[1]));
            } else {
                if(intervals[i][0] == newInterval[1]) {
                    list.add(intervals[i][1]);
                    i++;
                } else {
                    list.add(newInterval[1]);
                }
            }
            ansList.add(new ArrayList<>(list));
            System.out.println(ansList);
            System.out.println(i);
            list.clear();
        }
        System.out.println(ansList);
        System.out.println(i);

        while(i<intervals.length) {
            list.add(intervals[i][0]);
            list.add(intervals[i][1]);
            ansList.add(new ArrayList<>(list));
            list.clear();
            i++;
        }
        
        System.out.println(ansList);
        System.out.println(i);

        int[][] res = new int[ansList.size()][2];
        for(int j=0; j<res.length; j++) {
            int[] temp = new int[2];
            temp[0] = ansList.get(j).get(0);
            temp[1] = ansList.get(j).get(1);
            res[j] = temp;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 5}};
        int[] newInterval = {6, 8};

        insert(intervals, newInterval);
    }
}

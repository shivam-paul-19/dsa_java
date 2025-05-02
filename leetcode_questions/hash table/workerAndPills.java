// Leetcode Q: 2071. Maximum Number of Tasks You Can Assign

import java.util.*;

public class workerAndPills {
    private boolean check(int[] tasks, int[] workers, int pills, int strength, int mid) {
        TreeMap<Integer, Integer> tMap = new TreeMap<>();
        int p = pills;

        for(int i=workers.length-mid; i<workers.length; i++) {
            tMap.put(workers[i], tMap.getOrDefault(workers[i], 0) + 1);
        }

        for(int i=mid-1; i>=0; i--) {
            int task = tasks[i];
            int key = tMap.lastKey();

            if(key >= task) {
                tMap.put(key, tMap.get(key) - 1);
                if(tMap.get(key) == 0) tMap.remove(key);
            } else {
                if(p == 0) return false;
                if(tMap.ceilingKey(task - strength) == null) return false;

                key = tMap.ceilingKey(task - strength);
                tMap.put(key, tMap.get(key) - 1);
                if(tMap.get(key) == 0) tMap.remove(key);
                p--;
            }
        }

        return true;
    }

    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        Arrays.sort(workers);
        Arrays.sort(tasks);

        int left = 1;
        int right = Math.min(workers.length, tasks.length);
        int ans = 0;

        while(left <= right) {
            int mid = (left + right)/2;
            if(check(tasks, workers, pills, strength, mid)) {
                left = mid + 1;
                ans = mid;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }
}
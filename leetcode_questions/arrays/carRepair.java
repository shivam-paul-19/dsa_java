// leetcode Q: 2594. Minimum Time to Repair Cars

import java.util.*;

public class carRepair {
    private static boolean canRepair(long minutes, int cars, int[] ranks) {
        long c = 0;

        for (int i = 0; i < ranks.length; i++) {
            c += (long) Math.sqrt(minutes / ranks[i]);
        }

        return c >= cars;
    }

    public static long repairCars(int[] ranks, int cars) {
        int max = Arrays.stream(ranks).max().getAsInt();

        long start = 0;
        long end = (long)max * cars * cars;
        if (end < 0) end = Long.MAX_VALUE;

        while (start <= end) {
            long mid = start + (end - start) / 2;
            if (canRepair(mid, cars, ranks)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }
}

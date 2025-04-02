// Leetcode Q: 3169. Count Days Without Meetings

import java.util.Arrays;

public class noMeeting {
    public int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));
        int ans = meetings[0][0] - 1;
        int day = meetings[0][1];
        for(int i=1; i<meetings.length; i++) {
            if(day < meetings[i][0]) ans += meetings[i][0] - day - 1;
            day = Math.max(day, meetings[i][1]);
        }
        
        if(days > meetings[meetings.length-1][1]) ans += days - meetings[meetings.length-1][1];
        return ans;
    }

    private boolean checkCuts(int[][] rectangles, int dimension) {
        Arrays.sort(rectangles, (a, b) -> Integer.compare(a[dimension], b[dimension]));
        int gap = 0;
        int further = rectangles[0][dimension + 2];

        for(int i=1; i<rectangles.length; i++) {
            int[] rec = rectangles[i];

            if(further <= rec[dimension]) {
                gap++;
            }

            further = Math.max(further, rec[dimension + 2]);
        }

        return gap >= 2;
    }

    public boolean checkValidCuts(int n, int[][] rectangles) {
       return checkCuts(rectangles, 0) || checkCuts(rectangles, 1);
    }
}

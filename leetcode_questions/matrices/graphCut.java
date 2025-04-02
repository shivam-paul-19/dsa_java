import java.util.*;

public class graphCut {
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

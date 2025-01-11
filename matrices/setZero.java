// Leetcode Q: 73. Set Matrix Zeroes

import java.util.*;

public class setZero {
    public static void setZeroes(int[][] matrix) {
        HashSet<Integer> rows = new HashSet<>();
        HashSet<Integer> cols = new HashSet<>();

        for(int r=0; r<matrix.length; r++) {
            for(int c=0; c<matrix[0].length; c++) {
                if(matrix[r][c] == 0) {
                    rows.add(r);
                    cols.add(c);
                }
            }
        }

        for(int r=0; r<matrix.length; r++) {
            for(int c=0; c<matrix[0].length; c++) {
                if(rows.contains(r) || cols.contains(c)) {
                    matrix[r][c] = 0;
                }
            }
        }
    }
}

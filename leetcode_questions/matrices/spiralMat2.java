// Leetcode Q: 59. Spiral Matrix II

import java.util.*;

public class spiralMat2 {
    public  static int[][] generateMatrix(int n) {
        int[][] resMat = new int[n][n];
        int rowSt = 0;
        int colSt = 0;
        int rowEnd = n-1;
        int colsEnd = n-1;
        int num = 1;
        boolean[][] visMat = new boolean[n][n];
        while(rowSt<=rowEnd && colSt<=colsEnd) {
            for(int i=colSt; i<=colsEnd; i++) {
                if(!visMat[colSt][i]) {
                    resMat[colSt][i] = num++;
                    visMat[colSt][i] = true;
                }
            }

            for(int i=rowSt+1; i<=rowEnd; i++) {
                if(!visMat[i][colsEnd]) {
                    resMat[i][colsEnd] = num++;
                    visMat[i][colsEnd] = true;
                }
            }
            
            for(int i=colsEnd-1; i>=colSt; i--) {
                if(!visMat[rowEnd][i]) {
                    resMat[rowEnd][i] = num++;
                    visMat[rowEnd][i] = true;
                }
            }
            
            for(int i=rowEnd-1; i>=rowSt+1; i--) {
                if(!visMat[i][colSt]) {
                    resMat[i][colSt] = num++;
                    visMat[i][colSt] = true;
                }
            }
            rowSt++;
            rowEnd--;
            colSt++;
            colsEnd--;
        }
        return resMat;
    }

    public static void main(String[] args) {
        for(int[] row: generateMatrix(5)) {
            System.out.println(Arrays.toString(row));
        }
    }
}

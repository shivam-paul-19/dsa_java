// Leetcode Q: 54. Spiral matrix

import java.util.*;

public class spiralMat {
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int rowSt = 0;
        int colSt = 0;
        int rowEnd = matrix.length-1;
        int colsEnd = matrix[0].length-1;
        boolean[][] newMat = new boolean[matrix.length][matrix[0].length];
        while(rowSt<=rowEnd && colSt<=colsEnd) {
            for(int i=colSt; i<=colsEnd; i++) {
                if(!newMat[colSt][i]) {
                    list.add(matrix[colSt][i]);
                    newMat[colSt][i] = true;
                }
            }

            for(int i=rowSt+1; i<=rowEnd; i++) {
                if(!newMat[i][colsEnd]) {
                    list.add(matrix[i][colsEnd]);
                    newMat[i][colsEnd] = true;
                }
            }
            
            for(int i=colsEnd-1; i>=colSt; i--) {
                if(!newMat[rowEnd][i]) {
                    list.add(matrix[rowEnd][i]);
                    newMat[rowEnd][i] = true;
                }
            }
            
            for(int i=rowEnd-1; i>=rowSt+1; i--) {
                if(!newMat[i][colSt]) {
                    list.add(matrix[i][colSt]);
                    newMat[i][colSt] = true;
                }
            }
            rowSt++;
            rowEnd--;
            colSt++;
            colsEnd--;
        }
        return list;
    }
}

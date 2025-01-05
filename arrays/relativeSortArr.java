// lletcode Q: 1122. Relative Sort Array

import java.util.*;

public class relativeSortArr {
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<arr1.length; i++) {
            map.put(arr1[i], map.getOrDefault(arr1[i], 0) + 1);
        }

        int i=0;
        int limit = 0;
        for(int e=0; e<arr2.length; e++) {
            int c = map.get(arr2[e]);
            int k=0;
            while(k<c) {
                arr1[i++] = arr2[e];
                k++;
            }
            map.remove(arr2[e]);
            limit = i;
        }

        int[] temp = new int[arr1.length - limit];
        i = 0;
        for(int e: map.keySet()) {
            int count = map.get(e);
            for(int j=0; j<count; j++) { 
                temp[i++] = e;
            }
        }

        Arrays.sort(temp);

        for(int j=limit; j<arr1.length; j++) {
            arr1[j] = temp[j-limit];
        }
        
        return arr1;
    }
}

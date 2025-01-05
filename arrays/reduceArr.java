// 1338. Reduce Array Size to The Half

import java.util.*;

public class reduceArr {
    public static int minSetSize(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        int count = 1;
        Arrays.sort(arr);
        for(int i=1; i<arr.length; i++) {
            if(arr[i] == arr[i-1]) {
                count++;
            } else {
                list.add(count);
                count = 1;
            }
        }

        list.add(count);
        int[] temp = new int[list.size()];

        for(int i=0; i<temp.length; i++) {
            temp[i] = list.get(i);
        }

        Arrays.sort(temp);
        int target = arr.length/2;
        int total = arr.length;
        int res = 0;
        for(int i=temp.length-1; i>=0; i--) {
            total -= temp[i];
            res++;
            if(total <= target) {
                return res;
            }
        }
        return res;
    }
}

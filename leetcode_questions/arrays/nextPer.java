import java.util.*;

public class nextPer {
    public static void reverse(int[] arr, int si, int ei) {
        for(int i=si; i<(si+ei)/2; i++) {
            int temp = arr[i];
            arr[i] = arr[ei-i];
            arr[ei-i] = temp;
        }
    }

    // public void nextPermutation(int[] nums) {
        
    // }
    
    public static void main(String[] args) {
        int[] num = {1,2,3,5,6,7};
        System.out.println(Arrays.toString(num));
        reverse(num, 0, num.length-1);
        System.out.println(Arrays.toString(num));
    }
}
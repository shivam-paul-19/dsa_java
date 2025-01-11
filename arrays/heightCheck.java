import java.util.*;

public class heightCheck {
    public static int heightChecker(int[] heights) {
        int[] expected = new int[heights.length];
        expected = Arrays.copyOfRange(heights, 0, heights.length);
        Arrays.sort(expected);
        int res = 0;
        for(int i=0; i<heights.length; i++) {
            if(expected[i] != heights[i]) {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {5,1,2,3,4};
        System.out.println(heightChecker(arr));
    }
}

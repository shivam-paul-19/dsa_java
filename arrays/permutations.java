import java.util.*;
import java.util.stream.IntStream;

public class permutations {
    static List<List<Integer>> per = new ArrayList<>();
    public static List<List<Integer>> permute(int[] nums) {
        per.clear();
        permutation(nums, new ArrayList<Integer>());
        return per;
    }

    public static void permutation(int[] nums, ArrayList<Integer> result) {
        if(nums.length == 0) {
            per.add(new ArrayList<>(result));
            return;
        }

        for(int i=0; i<nums.length; i++) {
            result.add(nums[i]);
            int[] newArr = new int[nums.length-1];
            newArr = IntStream.concat(Arrays.stream(Arrays.copyOfRange(nums, 0, i)), Arrays.stream(Arrays.copyOfRange(nums, i+1, nums.length))).toArray();
            permutation(newArr, result);
            result.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] a = {1,2,3};
        int[] a2 = {1};
        System.out.println(permute(a));
    }
}

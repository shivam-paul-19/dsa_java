// Leetcode Q: 922. Sort Array By Parity II

public class solveByParity {
    public int[] sortArrayByParityII(int[] nums) {
        int[] ansArr = new int[nums.length];
        int oddIdx = 1;
        int evenIdx = 0;

        for(int n: nums) {
            if(n%2!=0) {
                ansArr[oddIdx] = n;
                oddIdx += 2;
            } else {
                ansArr[evenIdx] = n;
                evenIdx += 2;
            }
        }

        return ansArr;
    }
}

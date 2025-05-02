// Leetcode Q: 2145. Count the Hidden Sequences

public class hiddenSeq {
    public static int numberOfArrays(int[] differences, int lower, int upper) {
        int minPoint = 0;
        int maxPoint = 0;

        int sum = 0;
        for(int diff: differences) {
            sum += diff;
            minPoint = Math.min(minPoint, sum);
            maxPoint = Math.max(maxPoint, sum);
            if(maxPoint - minPoint > upper - lower) return 0;
        }
        
        return (upper - lower) - (maxPoint - minPoint) + 1;
    }
}
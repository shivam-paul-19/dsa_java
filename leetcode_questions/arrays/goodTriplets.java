// Leetocode Q: 1534. Count Good Triplets

public class goodTriplets {
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int count = 0;
        for(int i=0; i<arr.length-2; i++) {
            for(int j=i+1; j<arr.length-1; j++) {
                for(int k=j+1; k<arr.length; k++) {
                    int a_ = Math.abs(arr[i] - arr[j]);
                    int b_ = Math.abs(arr[j] - arr[k]);
                    int c_ = Math.abs(arr[i] - arr[k]);
                    if(a_ <= a && b_ <= b && c_ <= c) count++;
                }
            }
        }

        return count;
    }
}
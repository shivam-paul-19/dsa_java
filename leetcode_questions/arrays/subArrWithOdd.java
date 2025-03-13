// Leetcode Q: 1524. Number of Sub-arrays With Odd Sum

public class subArrWithOdd {
    public static int numOfSubarrays(int[] arr) {
        final int mod = (int)Math.pow(10, 9) + 7;
        int odd = 0;
        int even = 0;
        long n = 0;
        int[] prefixArr = new int[arr.length];
        prefixArr[0] = arr[0];
        for(int i=1; i<arr.length; i++) {
            prefixArr[i] = arr[i] + prefixArr[i-1];
        }
        
        for(int i=0; i<prefixArr.length; i++) {
            if(prefixArr[i]%2 == 0) {
                even++;
                n += odd;
                n %= mod;
            } else {
                odd++;
                n += (even+1);
                n %= mod;
            }
        }
        
        return (int)n;
    }
}

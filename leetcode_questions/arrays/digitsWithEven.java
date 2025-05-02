// lleetcode Q: 1295. Find Numbers with Even Number of Digits

public class digitsWithEven {
    private boolean hasEvenDigits(int n) {
        if(n <= 9) return false;
        else if(n <= 999 && n >= 100) return false;
        else if(n <= 99999 && n >= 10000) return false;

        return true;
    }

    public int findNumbers(int[] nums) {
        int count = 0;
        for(int n: nums) {
            if(hasEvenDigits(n)) count++;
        }

        return count;
    }
}
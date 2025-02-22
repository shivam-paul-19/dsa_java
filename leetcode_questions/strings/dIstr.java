// Leetcode Q: 2375. Construct Smallest Number From DI String

public class dIstr {
    public static int fillDs(int[] arr, int idx, int currVal) {
        while(idx >= 0 && arr[idx] == 0) {
            arr[idx--] = currVal++;
        }

        return currVal;
    }

    public static String smallestNumber(String pattern) {
        int[] answer = new int[pattern.length()+1];
        boolean fillD = false;
        int curr = 1;
        for(int i=0; i<pattern.length(); i++) {
            char c = pattern.charAt(i);
            if(c == 'I') {
                answer[i] = curr++;
                if(fillD) {
                    curr = fillDs(answer, i-1, curr);
                }
                fillD = false;
            } else {
                fillD = true;
            }
        }

        if(fillD) {
            fillDs(answer, answer.length-1, curr);
        } else {
            answer[answer.length-1] = curr++;
        }

        StringBuilder sb = new StringBuilder("");
        for(int i=0; i<answer.length; i++) {
            sb.append(answer[i]);
        }

        return sb.toString();
    }
}

// Leetcode Q: 8. String to Integer (atoi)

import java.util.*;

class str2int {
    public static int myAtoi(String s) {
        s = s.trim();
        if(s.length() == 0) {
            return 0;
        }
        boolean isNeg = false;
        int i=0;
        long a = 0;
        if(s.charAt(i) == '-') {
            isNeg = true;
            i++;
        } else if(s.charAt(i) == '+') {
            i++;
        }

        while(i<s.length()) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                a = a*10 + ((int)c - 48);
                if(a>Integer.MAX_VALUE) {
                    return isNeg? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
            } else {
                break;
            }
            i++;
        }

        return isNeg? -(int)a : (int)a;
    }
}
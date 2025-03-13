// Leetcode Q: 264. Ugly Number II

import java.util.*;

public class uglyNoII {
    public static int nthUglyNumber(int n) {
        int counter = 1;
        ArrayList<Integer> list = new ArrayList<>();
        int two_pt = 0;
        int three_pt = 0;
        int five_pt = 0;
        list.add(1);
        while(counter<n) {
            int two = list.get(two_pt)*2;
            int three = list.get(three_pt)*3;
            int five = list.get(five_pt)*5;

            int next = Math.min(two, Math.min(three, five));
            list.add(next);
            if(next == two) two_pt++;
            if(next == three) three_pt++;
            if(next == five) five_pt++;
            counter++;
        }
        System.out.println(list);
        return list.get(list.size()-1);
    }
}

// Leetcode Q: 2570. Merge Two 2D Arrays by Summing Values

import java.util.*;

public class mergeArr {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        List<List<Integer>> resList = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        int i=0;
        int j=0;
        while(i<nums1.length && j<nums2.length) {
            int n1 = nums1[i][0];
            int n2 = nums2[j][0];

            if(n1 == n2) {
                list.add(n1);
                list.add(nums1[i][1] + nums2[j][1]);
                resList.add(new ArrayList<>(list));
                list.clear();
                i++;
                j++;
            } else if(n1 > n2) {
                list.add(n2);
                list.add(nums2[j][1]);
                resList.add(new ArrayList<>(list));
                list.clear();;
                j++;
            } else {
                list.add(n1);
                list.add(nums1[i][1]);
                resList.add(new ArrayList<>(list));
                list.clear();
                i++;
            }
        }

        while(i<nums1.length) {
            list.add(nums1[i][0]);
            list.add(nums1[i][1]);
            resList.add(new ArrayList<>(list));
            list.clear();
            i++;
        }

        while(j<nums2.length) {
            list.add(nums2[j][0]);
            list.add(nums2[j][1]);
            resList.add(new ArrayList<>(list));
            list.clear();;
            j++;
        }

        int[][] res = new int[resList.size()][2];
        for(i=0; i<resList.size(); i++) {
            res[i][0] = resList.get(i).get(0);
            res[i][1] = resList.get(i).get(1);
        }

        return res;
    }
}

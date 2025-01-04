import java.util.*;

public class main {
    //^ For linked list
    // public static class ListNode {
    //     int val;
    //     ListNode next;
    //     ListNode() {}
    //     ListNode(int val) { this.val = val; }
    //     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    // }

    // public static ListNode makeLL(int[] arr) {
    //     if(arr.length == 0) {
    //         return null;
    //     }
    //     ListNode head = new ListNode();
    //     ListNode tail;
    //     ListNode temp = tail = head;
    //     for(int i=0; i<arr.length; i++) {
    //         temp.val = arr[i];
    //         temp.next = new ListNode();
    //         tail = temp;
    //         temp = temp.next;
    //     }
    //     tail.next = null;
    //     return head;
    // }

    // public static void printLL(ListNode head) {
    //     ListNode temp = head;
    //     while(temp != null) {
    //         System.out.print(temp.val + " -> ");
    //         temp = temp.next;
    //     }
    //     System.out.println("Null");
    // }

    public static int[] twoSum(int[] nums, int target) {
        HashMap <Integer, Integer> map = new HashMap<>();
        int newArr[] = new int[nums.length];
        newArr = nums;
        int res[] = new int[2];
        int s = 0, e = nums.length-1;
        Arrays.sort(nums);
        while(e > s) {
            if(nums[s] + nums[e] == target) {
                res[0] = map.get(nums[s]);
                res[1] = map.get(nums[e]);
                break;
            } else if(nums[s] + nums[e] > target) {
                e--;
            } else {
                s++;
            }
        }
        return res;
    }

    public static int[] singleNumber(int[] nums) {
        if(nums.length == 2) {
            return nums;
        }

        int[] res = new int[2];
        int flag = 0;

        Arrays.sort(nums);

        if(nums[0] != nums[1]) {
            res[flag++] = nums[0];
        }

        for(int i=1; i<nums.length-1; i++) {
            if(nums[i-1] != nums[i] && nums[i] != nums[i+1]) {
                res[flag++] = nums[i];
            }
        }

        if(flag != 2) {
            res[flag++] = nums[nums.length-1];
        }

        return res;
    }

    public static int maxProfit(int[] prices) {
        int min = prices[0];
        int maxProfit = 0;
        for(int i=1; i<prices.length; i++) {
            if(prices[i] < min) {
                min = prices[i];
            } else {
                int profit = prices[i] - min;
                maxProfit = (profit > maxProfit) ? profit : maxProfit;
            }
        }

        return maxProfit;
    }

    public static boolean isUgly(int n) {
        while(n > 1) {
            if(n%5 == 0) {
                n /= 5;
            } else if (n%3 == 0) {
                n /= 3;
            } else if (n%2 == 0) {
                n /= 2;
            } else {
                return false;
            }
        }

        return true;
    }

    public static int nthUglyNumber(int n) {
        int i=1, j=1;
        while(j<=n) {
            if(isUgly(i)) {
                j++;
            }
            i++;
        }

        return --i;
    }

    public static void main(String[] args) {
        int[] arr = {7,6,4,3,1};
        System.out.println(nthUglyNumber(1352));
    }
}

// 912. Sort an Array
// 1338. Reduce Array Size to The Half
// 1833. Maximum Ice Cream Bars
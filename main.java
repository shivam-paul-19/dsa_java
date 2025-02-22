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



    public static boolean isExists(char[][] board, int i, int j, int idx, String word) {
        if(i<0 || j<0 || i>=board.length || j>=board[0].length || board[i][j] == '*' || board[i][j] != word.charAt(idx)) {
            return false;
        }

        if(word.length() - 1 == idx) {
            return true;
        }

        char t = board[i][j];
        board[i][j] = '*';
        boolean res = isExists(board, i+1, j, idx+1, word) || isExists(board, i-1, j, idx+1, word) || isExists(board, i, j+1, idx+1, word) || isExists(board, i, j-1, idx+1, word);
        board[i][j] = t;
        return res;
    }

    public static boolean exist(char[][] board, String word) {
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                if(word.charAt(0) == board[i][j] && isExists(board, i, j, 0, word)) {
                    return true;
                } 
            }
        }

        return false;
    }

    public static int maxAdjacentDistance(int[] nums) {
        int max = Integer.MIN_VALUE;
        for(int i=1; i<nums.length; i++) {
            max = Math.max(max, Math.abs((nums[i]-nums[i-1])));
        }

        max = Math.max(max, Math.abs((nums[0]-nums[nums.length-1])));
        return max;
    }

    public static long minCost(int[] arr, int[] brr, long k) {
        if(Arrays.equals(arr, brr)) {
            return 0;
        }

        long cost = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<brr.length; i++) {
            list.add(brr[i]);
        }

        for(int i=0; i<arr.length; i++) {
            int min = Integer.MAX_VALUE;
            int minIdx = 0;
            for(int j=0; j<list.size(); j++) {
                if(min > Math.abs(arr[i]-list.get(j))) {
                    min = Math.abs(arr[i]-list.get(j));
                    minIdx = j;
                }
            }

            map.put(i, minIdx);
            list.remove(minIdx);
            cost += min;
        }

        for(int key: map.keySet()) {
            if(key != map.get(key)) {
                cost += k;
                break;
            }
        }

        return cost;
    }

    public static int removeDuplicates(int[] nums) {
        int[] newNums = new int[nums.length];
        HashMap<Integer, Integer> map = new HashMap<>();

        newNums = Arrays.copyOf(nums, nums.length);
        Arrays.fill(nums, 0);
        int j=0;
        for(int i=0; i<newNums.length; i++) {
            if(map.containsKey(newNums[i]) && (map.get(newNums[i]) == 2)) {
                continue;
            }
            nums[j++] = newNums[i];
            map.put(newNums[i], map.getOrDefault(newNums[i], 0) + 1);
        }

        return j;
    }

    public static boolean validTriangle(int a, int b, int c) {
        boolean isValid = ((a+b)>c) && ((c+b)>a) && ((a+c)>b);
        return isValid;
    }

    public static int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        int a=0, b=0, c=0;
        for(int i=nums.length; i>=2; i--) {
            if(validTriangle(nums[i], nums[i-1], nums[i-2])) {
                a = nums[i];
                b = nums[i-1];
                c = nums[i-2];
                break;
            }
        }

        return a+b+c;
    }

    public static void main(String[] args) {
        System.out.println();
    }
}

// 3151. Special Array I
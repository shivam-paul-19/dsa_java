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

    public static int[] closestPrimes(int left, int right) {
        int[] res = new int[2];
        boolean[] prime = new boolean[right+1];

        Arrays.fill(prime, true);

        for(int i=2; i*i<=right; i++) {
            if(prime[i]) {
                for(int p=i*i; p<=right; p += i) {
                    prime[p] = false;
                }
            }
        }

        prime[1] = false;
        int leftPr = -1;
        int rightPr = -1;
        int currLeft = -1;
        // int currRight = -1;
        for(int i=left; i<=right; i++) {
            if (prime[i] && leftPr == -1) {
                leftPr = i;
            } else if (prime[i] && rightPr == -1) {
                rightPr = i;
                currLeft = i;
                if(rightPr - leftPr == 2) break;
            } else if (prime[i]) {
                System.out.println(currLeft);
                System.out.println(">>" + i);
                if(rightPr - leftPr > i - currLeft) {
                    leftPr = currLeft;
                    rightPr = i;
                    if(rightPr - leftPr == 2) {
                        System.out.println("here");
                        break;
                    }
                } 
                currLeft = i;
                
            }
        }

        res[0] = leftPr;
        res[1] = rightPr;
        if(rightPr == -1) {
            res[0] = -1;
        }

        return res;
    }

    public static int totalNumbers(int[] digits) {
        HashSet<Integer> set = new HashSet<>();

        for(int i=0; i<digits.length; i++) {
            if(digits[i] == 0) continue;

            for(int j=0; j<digits.length; j++) {
                if(i == j) continue;

                for(int k=0; k<digits.length; k++) {
                    if(i == k || j == k) continue;

                    int num = digits[i]*100 + digits[j]*10 + digits[k];
                    if(num%2 == 0) set.add(num);
                }
            }
        }

        return set.size();
    }

    static class Spreadsheet {
        HashMap<String, Integer> cells;
        public Spreadsheet(int rows) {
            this.cells = new HashMap<>();
        }
        
        public void setCell(String cell, int value) {
            cells.put(cell, value);
        }
        
        public void resetCell(String cell) {
            if(cells.containsKey(cell)) {
                cells.remove(cell);
            }
        }
        
        public int getValue(String formula) {
            int plus = formula.indexOf("+");
            String first = formula.substring(1, plus);
            String second = formula.substring(plus+1);

            int firstNum = 0;
            int secondNum = 0;

            if(Character.isDigit(first.charAt(0))) {
                firstNum = Integer.parseInt(first);
            } else {
                firstNum = cells.getOrDefault(first, 0);
            }

            if(Character.isDigit(second.charAt(0))) {
                secondNum = Integer.parseInt(second);
            } else {
                secondNum = cells.getOrDefault(second, 0);
            }

            System.out.println(firstNum + " " + secondNum);
            return firstNum + secondNum;
        }
    }

    private static boolean isSymmetric(String n) {
        int l = n.length();
        int first = Integer.parseInt(n.substring(0, l/2));
        int second = Integer.parseInt(n.substring(l/2, l));

        if(l == 2) return first == second;

        int firstSum = first/10 + (first%10);
        int secondSum = second/10 + (second%10); 
        return firstSum == secondSum;
    }

    public static int countSymmetricIntegers(int low, int high) {
        int count = 0;
        for(int i=low; i<=high; i++) {
            if(i<=9 || (i>=100 && i<999) || i == 10000) continue;
            if(isSymmetric(Integer.toString(i))) count++;
        }

        return count;
    }

    public int countLargestGroup(int n) {
        int[] sums = new int[36];
        int maxValue = 0;
        for(int i=1; i<=n; i++) {
            int num = i;
            int sum = 0;
            while(num != 0) {
                sum += num%10;
                num /= 10;
            }
            sums[sum-1]++;
            maxValue = Math.max(maxValue, sums[sum-1]);
        }

        int count = 0;
        for(int i=0; i<sums.length; i++) {
            if(sums[i] == maxValue) count++;
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(5/2);
    }
}

// 3151. Special Array I
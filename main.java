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

    public static void main(String[] args) {
        char[][] word = {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
        System.out.println(exist(word, "ABCCED"));
    }
}

// 912. Sort an Array
// 1338. Reduce Array Size to The Half
// 1833. Maximum Ice Cream Bars
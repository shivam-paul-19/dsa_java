// 237. Delete Node in a Linked List

public class deleteNode {
    public static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}

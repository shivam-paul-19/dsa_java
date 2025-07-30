// Day 13: Stack and Queues

import java.util.*;
import java.util.LinkedList;

public class Day13 {
    // Not in Leetcode: Implement Stack using Arrays
    class ArrayStack {
        // make an array
        int[] arr;
        int pointer;

        public ArrayStack() {
            // as we don't have any parameter in constructor about size, so we are cosidering it as 10
            this.arr = new int[10];
            this.pointer = -1;  // initially the stack is empty
        }
        
        public void push(int x) {
            if(this.pointer == 9) {
                return;     // stack is full
            }

            this.arr[++this.pointer] = x;
        }

        public int pop() {
            if(this.pointer == -1) {
                return -1;
            }

            return this.arr[this.pointer--];
        }

        public int top() {
            if(this.pointer == -1) {
                return -1;
            }

            // we won't decrement the pointer as we aren't removing the element
            return this.arr[this.pointer];
        }

        public boolean isEmpty() {
            return this.pointer == -1;
        }
    }

    // Not in Leetcode: Implement Queue using Arrays
    class ArrayQueue {
        // make an array, and start and end pointer
        int[] arr;
        int front;
        int end;
        int size;

        public ArrayQueue() {
            // as we don't have any parameter in constructor about size, so we are cosidering it as 10
            this.arr = new int[10];
            this.front = -1;
            this.end = -1;
            this.size = 0;
        }

        public void push(int x) {
            if(size == 10) {
                return;     // queue is full
            }

            if(this.front == -1 && this.end == -1) {
                this.front++;
                this.end++;
                this.arr[this.end] = x;
                this.size++;
                return;
            }

            this.end = (this.end+1)%10;
            this.arr[this.end] = x;
            this.size++;
        }

        public int pop() {
            if(this.isEmpty()) {
                return -1;
            }

            int el = this.arr[this.front];
            this.front = (this.front + 1)%10;
            this.size--;
            return el;
        }

        public int peek() {
            if(this.isEmpty()) {
                return -1;
            }

            return this.arr[this.front];
        }

        public boolean isEmpty() {
            return this.size == 0;
        }
    }

    // Leetcode Q: 225. Implement Stack using Queues
    class MyStack {
        // first initialise a Queue
        Queue<Integer> q;

        public MyStack() {
            this.q = new LinkedList<>();
        }
        
        public void push(int x) {
            // first of all add the element in Queue
            this.q.offer(x);

            // now get all the elements (before added one) from the Queue and push them back
            for(int i=0; i<this.q.size()-1; i++) {
                int front = this.q.poll();
                this.q.offer(front);
            }

            // now the newly added element is at the front
        }
        
        public int pop() {
            return q.poll();
        }
        
        public int top() {
            return q.peek();
        }
        
        public boolean empty() {
            // if Queue is empty then stack is too
            return q.isEmpty();
        }
    }

    // Leetcode Q: 232. Implement Queue using Stacks
    class MyQueue {
        // first initialize two Stacks
        Stack<Integer> s1;
        Stack<Integer> s2;

        public MyQueue() {
            this.s1 = new Stack<>();
            this.s2 = new Stack<>();
        }
        
        public void push(int x) {
            // while adding, first we will push all the element in s1 to s2
            while(!s1.isEmpty()) {
                s2.push(s1.pop());
            }

            // now push the new element
            s1.push(x);

            // now push the elements again to s1
            while(!s2.isEmpty()) {
                s1.push(s2.pop());
            }

            // now the new element is in the bottom of the stack so it implies FIFO property now
        }
        
        public int pop() {
            return s1.pop();
        }
        
        public int peek() {
            return s1.peek();
        }
        
        public boolean empty() {
            // if stack is empty so the Queue too
            return s1.isEmpty();
        }
    }

    // Leetcode Q: valid parenthesis
    public boolean isValid(String par) {
        // for this we need a stack
        Stack<Character> s = new Stack<>();

        // traverse the parenthis string
        for(int i=0; i<par.length(); i++) {
            char c = par.charAt(i);

            // if it is an opening bracket then push it into the stack
            if(c == '(' || c == '[' || c == '{') {
                s.push(c);
            } else {
                if(s.isEmpty()) return false;   // no brackets left, so invalid
                char curr = s.pop();

                if((c == ')' && curr != '(') || (c == '}' && curr != '{') || (c == ']' && curr != '[')) {
                    // if pair is mismatched then also it is invalid
                    return false;
                }
            }
        }

        // if still something is left in stack then it is invalid, otheriwse valid
        return s.isEmpty();
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // as we have two arrays, first we have to store the indices of each element in nums2
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums2.length; i++) {
            map.put(nums2[i], i);
        }

        // now we will need a stack
        Stack<Integer> s = new Stack<>();
        int[] num2_ans = new int[nums2.length];

        for(int i=nums2.length-1; i>=0; i--) {
            int curr = nums2[i];    // get the current element

            while(!s.isEmpty() && curr > s.peek()) {
                // remove the element lower than curr
                s.pop();
            }

            if(!s.isEmpty()) {
                num2_ans[i] = s.peek(); // this is next greater element
            } else {
                num2_ans[i] = -1;   // no greater element found
            }

            // push this curr element too
            s.push(curr);
        }

        // now make it for nums1
        int[] ans = new int[nums1.length];
        for(int i=0; i<nums1.length; i++) {
            ans[i] = num2_ans[map.get(nums1[i])];
        }

        return ans;
    }

    // Not in leetcode: sort a stack
    private void placeRight(Stack<Integer> st, int n) {
        // base case, where we push the element
        if(st.isEmpty() || n > st.peek()) {
            st.push(n);
            return;
        }

        int top = st.pop();     // get the top element
        placeRight(st, n);      // recursive call
        st.push(top);           // push the top back, backtracking
    }

    public void sortStack(Stack<Integer> st) {
        // we have to sort a stack in descending order
        // so we will put each element in it's right place

        // first of all empty the stack into a list
        List<Integer> list = new ArrayList<>();

        while(!st.isEmpty()) {
            list.add(st.pop());
        }

        // now place each element in it's right place
        for(int i=0; i<list.size(); i++) {
            placeRight(st, list.get(i));
        }
    }
}
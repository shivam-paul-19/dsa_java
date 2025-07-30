// Day 12: Heaps

import java.util.*;

public class Day12 {
    // not in Leetcode: implement max heap
    class MaxHeap {
        // initialise a list
        List<Integer> list;

        public void initializeHeap() {
            this.list = new ArrayList<>();
        }

        public void insert(int key) {
            // add the element in the list
            list.add(key);

            // swap the nodes with it's parents
            int n = list.size()-1;
            int par = (n-1)/2;

            while(list.get(par) < list.get(n)) {
                // swap
                int temp = list.get(par);
                list.set(par, list.get(n));
                list.set(n, temp);

                n = par;
                par = (n-1)/2;
            }
        }

        public void changeKey(int index, int newVal) {
            if(this.heapSize() <= index) return;

            list.set(index, newVal);
            // now heapify it
            heapify(0);
        }

        public void extractMax() {
            if(this.isEmpty()) return;

            // swap the first and last element 
            int temp = list.getLast();
            list.set(list.size()-1, list.getFirst());
            list.set(0, temp);

            // delete the last element (largest one)
            // now heapify it
            heapify(0);
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }

        public int getMax() {
            if(this.isEmpty()) {
                return -1;
            }

            return list.get(0);
        }

        public int heapSize() {
            return list.size();
        }

        // helper heapify function
        private void heapify(int idx) {
            int left = 2*idx+1;
            int right = 2*idx+2;
            int max = idx;

            if(left < list.size() && list.get(left) > list.get(max)) {
                max = left;
            }

            if(right < list.size() && list.get(right) > list.get(max)) {
                max = right;
            }

            if(max != idx) {
                // swap 
                int temp = list.get(max);
                list.set(max, list.get(idx));
                list.set(idx, temp);

                heapify(max);
            }
        }
    }

    // not in Leetcode: Maximum Sum Combination

    // for this again we will need a triplet class to store the indices of two numbers, and the sum
    private class Triplet {
        int sum;
        int first;
        int second;

        Triplet(int sum, int first, int second) {
            this.sum = sum;
            this.first = first;
            this.second = second;
        }
    }

    public int[] maxSumCombinations(int[] nums1, int[] nums2, int k) {
        int[] ans = new int[k];

        // a priority queue on the basis of sum (descending order)
        PriorityQueue<Triplet> pq = new PriorityQueue<>((a, b) -> b.sum - a.sum);

        // sort both the arrays
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        // put the first pair in the pq, as it is bound to have highest sum
        pq.offer(new Triplet(nums1[0] + nums2[0], 0, 0));

        int i=0;
        while(i < k) {
            // get the highest sum
            int currSum = pq.peek().sum;
            int firstIdx = pq.peek().first;
            int secondIdx = pq.peek().second;
            pq.poll();  // as this is used so pop it

            // add the current sum in the array
            ans[i] = currSum;

            // add the next pairs in the queue
            pq.offer(new Triplet(nums1[firstIdx+1] + nums2[secondIdx], firstIdx+1, secondIdx));
            pq.offer(new Triplet(nums1[firstIdx] + nums2[secondIdx+1], firstIdx, secondIdx+1));

            i++;
        }

        return ans;
    }

    // Leetcode Q: 215. Kth Largest Element in an Array
    public int findKthLargest(int[] nums, int k) {
        // we will use a max heap, or a priority queue but in the reverse fashion
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        // put the numbers in the pq
        for(int n: nums) {
            pq.offer(n);
        }        

        // get the first k-1 elements 
        int i = 1;
        while(i<k) {
            pq.poll();
            i++;
        }

        // now we will get the kth largest element 
        return pq.poll();
    }

    // Leetcode Q: 295. Find Median from Data Stream
    class MedianFinder {
        // for this we will have two heap, one min and one max
        PriorityQueue<Integer> minHeap; 
        PriorityQueue<Integer> maxHeap; 

        public MedianFinder() {
            // initialise them 
            this.minHeap = new PriorityQueue<>();
            this.maxHeap = new PriorityQueue<>((a, b) -> b - a);
        }
        
        public void addNum(int num) {
            if(maxHeap.size() == 0 || maxHeap.peek() > num) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }

            // keep the size difference max 1
            while(Math.abs(minHeap.size() - maxHeap.size()) > 1) {
                if(minHeap.size() > maxHeap.size()) {
                    maxHeap.offer(minHeap.poll());
                } else {
                    minHeap.offer(maxHeap.poll());
                }
            }
        }
        
        public double findMedian() {
            double ans = 0.0;
            if(this.maxHeap == this.minHeap) {
                // the elements are even so get the average of middle two
                int a = minHeap.peek();
                int b = maxHeap.peek();
                ans = (double)(a+b)/2;
            } else {
                // peek where the size is more
                ans = (double)((maxHeap.size() > minHeap.size())? maxHeap.peek() : minHeap.peek());
            }

            return ans;
        }
    }

    // not in leetcode: Merge K sorted arrays
    public List<Integer> mergeKSortedArrays(int[][] arr, int k) {
        // just push all of them in a priority Queue
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int[] row: arr) {
            for(int n: row) {
                pq.offer(n);
            }
        }   

        // now put them in a list
        List<Integer> ans = new ArrayList<>();
        while(!pq.isEmpty()) {
            ans.add(pq.poll());
        }

        return ans;
    }

    // Leetcode Q: 347. Top K Frequent Elements

    // for this we have to maintain a pair class
    private class Pair {
        int el;
        int freq;

        Pair(int el, int freq) {
            this.el = el;
            this.freq = freq;
        }
    }
    
    public int[] topKFrequent(int[] nums, int k) {
        // make a hashmap for elements and thier freq
        HashMap<Integer, Integer> freq = new HashMap<>();

        for(int n: nums) {
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }

        // make a priority queue of Pairs in descending order and put all map data in that
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> b.freq - a.freq);

        for(int key: freq.keySet()) {
            pq.offer(new Pair(key, freq.get(key)));
        }

        // make an array to store answer
        int[] ans = new int[k];
        int i=0;
        while(i<k) {
            // get the key or element
            int el = pq.poll().el;
            ans[i++] = el;
        }

        return ans;
    }
}

import java.util.*;

public class heaps {
    public static class MinHeap {
        ArrayList<Integer> arr = new ArrayList<>();
        public void insertHeap(int data) {
            arr.add(data);

            int x = arr.size()-1;
            int parIdx = (x-1)/2;

            while(arr.get(x) < arr.get(parIdx)) {
                int temp = arr.get(x);
                arr.set(x, arr.get(parIdx));
                arr.set(parIdx, temp);

                x = parIdx;
                parIdx = (x-1)/2;
            }
        }

        public int peek() {
            return arr.get(0);
        }

        public void printall() {
            System.out.println(arr);
        }

        public void heapify(int idx) {
            int left = (2*idx)+1;
            int right = (2*idx)+2;
            int minIdx = idx;

            if(left < arr.size() && arr.get(minIdx) > arr.get(left)) {
                minIdx = left;
            }

            if(right < arr.size() && arr.get(minIdx) > arr.get(right)) {
                minIdx = right;
            }

            if(minIdx != idx) {
                int temp = arr.get(idx);
                arr.set(idx, arr.get(minIdx));
                arr.set(minIdx, temp);

                heapify(minIdx);
            }
        }

        public int remove() {
            int data = arr.get(0);
            int temp = data;
            arr.set(0, arr.get(arr.size()-1));
            arr.set(arr.size()-1, temp);

            arr.remove(arr.size()-1);

            heapify(0);

            return data;
        }

        public boolean isEmpty() {
            return arr.size() == 0;
        }
    }

    public static void heapSort(int arr[]) {
        int n = arr.length;
        for(int i=n/2; i>=0; i--) {
            heapify(i);
        }

        for(int i=n-1; i>=0; i--) {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;

            heapify(i);
        }
    }

    // nearest k cars
    public static class Points implements Comparator<Points> {
        int x;
        int y;
        int dis;
        int idx;

        public Points(int x, int y, int dis, int idx) {
            this.x = x;
            this.y = y;
            this.dis = dis;
            this.idx = idx;
        }

        @Override
        public int CompareTo(Points p2) {
            return this.dis = p2.dis;
        }
    }

    public static void nearestKPoints(int arr[][], int k) {
        PriorityQueue<Points> pts = new PriorityQueue<>();

        for(int i=0; i<arr.length; i++) {
            int dis = arr[i][0]*arr[i][0] + arr[i][1]*arr[i][1];
            pts.add(new Points(arr[i][0], arr[i][1], dis, i));
        }

        for(int i=0; i<k; i++) {
            System.out.println("Car " + pts.remove());
        }
    }

}

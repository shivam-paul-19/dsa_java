import java.util.*;

public class kWorkers {
    public static long totalCost(int[] costs, int k, int candidates) {
        PriorityQueue<Integer> pq1 = new PriorityQueue<>();
        PriorityQueue<Integer> pq2 = new PriorityQueue<>();
        for(int i=0; i<candidates; i++) {
            pq1.add(costs[i]);
            pq2.add(costs[costs.length-i-1]);
        }
        
        long totalCost = 0;

        int startIdx = candidates-1;
        int endIdx = costs.length-candidates;

        for(int i=0; i<k; i++) {
            int start = (pq1.isEmpty())? Integer.MAX_VALUE : pq1.peek();
            int end = (pq2.isEmpty())? Integer.MAX_VALUE : pq2.peek();

            if(end<start) {
                totalCost += pq2.remove();
                if(endIdx>startIdx+1) {
                    pq2.add(costs[--endIdx]);
                }
            } else {
                totalCost += pq1.remove();
                if(startIdx<endIdx-1) {
                    pq1.add(costs[++startIdx]);
                }
            }
        }

        return totalCost;
    }

    public static void main(String[] args) {
        int[] costs = {1,2,4,1};
        System.out.println(totalCost(costs, 3, 3));
    }
}

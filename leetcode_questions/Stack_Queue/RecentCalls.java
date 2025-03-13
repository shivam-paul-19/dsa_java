// LeetCode Q: 933. Number of Recent Calls

import java.util.*;

public class RecentCalls {
    class RecentCounter {
        Queue<Integer> q;
        public RecentCounter() {
            this.q = new LinkedList<>();
        }
        
        public int ping(int t) {
            q.add(t);
            while(!q.isEmpty() && t-q.peek() > 3000) {
                q.remove();
            }

            return q.size();
        }
    }
}

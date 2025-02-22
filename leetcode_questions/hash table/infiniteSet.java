import java.util.*;

public class infiniteSet {
    public static class SmallestInfiniteSet {
        int smallest;
        HashSet<Integer> removed = new HashSet<>();

        public SmallestInfiniteSet() {
            this.smallest = 1;
        }
        
        public int popSmallest() {
            int pop = smallest;
            removed.add(smallest);
            while(true) {
                smallest++;
                if(!removed.contains(smallest)) {
                    break;
                }
            }

            return smallest;
        }
        
        public void addBack(int num) {
            if(removed.contains(num)) {
                removed.remove(num);
                smallest = Math.min(num, smallest);
            }
        }
    }

    public static void main(String[] args) {
        
    }
}

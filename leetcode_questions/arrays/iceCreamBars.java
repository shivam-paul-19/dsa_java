import java.util.*;

public class iceCreamBars {
    public static int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int spent = 0;
        int bars = 0;
        if(costs[0] > coins) {
            return bars;
        }
        for(int i=0; i<costs.length; i++) {
            if(spent + costs[i] > coins) {
                return bars;
            } else {
                spent += costs[i];
                bars++;
            }
        }

        return bars;
    }
}

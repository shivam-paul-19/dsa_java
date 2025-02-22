// Leetcode Q; 1352. Product of the Last K Numbers

import java.util.*;

public class lastKProd {
    class ProductOfNumbers {
        ArrayList<Integer> list = new ArrayList<>();
        public ProductOfNumbers() {
            list = new ArrayList<>();
        }
        
        public void add(int num) {
            this.list.add(num);
        }
        
        public int getProduct(int k) {
            int product = 1;
            for(int i=list.size()-1; i>list.size()-k-1; i++) {
                if(list.get(i) == 0) return 0;
                product *= list.get(i);
            }

            return product;
        }
    }
}

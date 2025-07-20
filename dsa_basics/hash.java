import java.util.LinkedList;

public class hash {
    
    public static class HashMap<K, V> {
        // Node class
        private class Node {
            K key;
            V value;

            // constructor of Node class
            Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        // array of LinkedList
        LinkedList<Node>[] bucket;

        @SuppressWarnings("unchecked")  // just to remove warnings
        HashMap() {
            // initialise the array of size 10
            this.bucket = new LinkedList[10];
            for(int i=0; i<10; i++) {
                this.bucket[i] = new LinkedList<>();
            }
        }

        private int getHash(K key) {
            int bi = key.hashCode();
            return bi%10;
        }

        private int searchInLL(int bi, K key) {
            LinkedList<Node> curr = bucket[bi];
            for(int i=0; i<curr.size(); i++) {
                if(curr.get(i).key == key) return i;
            }

            return -1;
        }

        public void put(K key, V value) {
            int bi = getHash(key);  // getting bucket index
            int di = searchInLL(bi, key);   // get the data index

            if(di == -1) {
                // key is not there, add new Node
                bucket[bi].add(new Node(key, value));
            } else {
                // key is already there, modify it
                bucket[bi].get(di).value = value;
            }
        }

        public boolean containsKey(K key) {
            int bi = getHash(key);  // getting bucket index
            int di = searchInLL(bi, key);   // get the data index

            // if di is -1 then Key isn't there, so false should be return
            // otherwise true will be return
            return di != -1;
        }

        public V get(K key) {
            int bi = getHash(key);  // getting bucket index
            int di = searchInLL(bi, key);   // get the data index

            if(di == -1) {
                // key isn't there
                return null;
            } else {
                // key found, return the value
                return bucket[bi].get(di).value;
            }
        }

        @Override
        public String toString() {
            String res = "{";
            for(int i=0; i<10; i++) {
                for(int j=0; j<bucket[i].size(); j++) {
                    res += (bucket[i].get(j).key + ": " + bucket[i].get(j).value + ", ");
                }
            }

            res += "\b\b}";
            return res;
        }

        public V remove(K key) {
            int bi = getHash(key);  // getting bucket index
            int di = searchInLL(bi, key);   // get the data index

            if(di == -1) {
                // key isn't there
                return null;
            } else {
                // key found, return the value and then remove it
                return bucket[bi].remove(di).value;
            }
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("India", 140);
        map.put("china", 135);
        map.put("USA", 35);
        map.put("India", 145);

        System.out.println(map);
        System.out.println(map.get("India"));
        System.out.println(map.remove("china"));
        System.out.println(map);
    }
}

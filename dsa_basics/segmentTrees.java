import java.util.Arrays;

public class segmentTrees {
    static int[] tree;

    public static void init(int[] arr, int n) {
        tree = new int[n*4];
        buildST(arr, 0, 0, n-1);
    }

    public static int buildST(int[] arr, int i, int start, int end) {
        if(start == end) {
            tree[i] = arr[start];
            return tree[i];
        }

        int mid = start + (end - start)/2;
        int left = buildST(arr, 2*i+1, start, mid);
        int right = buildST(arr, 2*i+2, mid+1, end);
        return tree[i] = left + right;
    }

    public static int getSumUtil(int i, int si, int sj, int qi, int qj) {
        if(si >= qj || sj <= qi) return 0;
        if(si >= qi && sj <= qj) return tree[i];
        else {
            int mid = si + (sj - si)/2;
            int left = getSumUtil(2*i+1, si, mid, qi, qj);
            int right = getSumUtil(2*i+2, mid+1, sj, qi, qj);
            return left + right;
        }
    }

    public static int getSum(int[] arr, int start, int end) {
        int n = arr.length;
        return getSumUtil(0, 0, n-1, start, end);
    }

    public static void updateUtil(int i, int idx, int start, int end, int diff) {
        if(idx <= start || idx >= end) return;

        tree[i] += diff;
        if(start != end) {
            int mid = start + (end - start)/2;
            updateUtil(2*i+1, idx, start, mid, diff);
            updateUtil(2*i+2, idx, mid+1, end, diff);
        }
    }

    public static void update(int[] arr, int i, int newVal) {
        int diff = newVal - arr[i];
        int n = arr.length;
        arr[i] = newVal;

        updateUtil(0, i, 0, n-1, diff);
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8};
        init(arr, arr.length);
        System.out.println(Arrays.toString(tree));
        System.out.println(getSum(arr, 2, 5));
    }
}
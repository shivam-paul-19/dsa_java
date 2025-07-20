import java.util.Arrays;

public class Array {

    //^ traversing
    public static void display(int[] arr) {
        for(int i=0; i<arr.length; i++) {
            System.out.println(arr[i]);
        }

        // OR

        for(int el: arr) {
            System.out.println(el);
        }
    }

    //^ searching

    //! Linear search
    //? time complexity: O(n)
    public static int linearSearch(int[] arr, int key) {
        for(int i=0; i<arr.length; i++) {
            if(arr[i] == key) {
                // if there's a match then return the index
                return i;
            }
        }
        
        // not found
        return -1;
    }
    
    //! Binary search
    //? time complexity: O(log n)
    public static int binarySearch(int[] arr, int key) {
        // the array should be sorted first
        Arrays.sort(arr);
        
        int start = 0;
        int end = arr.length - 1;
        
        while(start <= end) {
            int mid = start + (end - start)/2;
            if(arr[mid] == key) {
                // if there's a match then return the index
                return mid;
            } else if(arr[mid] < key) {
                // the element must be in the second half then
                start = mid+1;
            } else {
                // the element must be in first half then
                end = mid-1;
            }
        }

        // not found
        return -1;
    }

    //^ Sorting

    //! Bubble sort
    public static int[] bubbleSort(int[] arr) {
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr.length-1-i; j++) {
                if(arr[j] > arr[j+1]) {
                    // swap
                    int t = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = t;
                }
            }
        }

        return arr;
    }

    //! Selection sort
    public static int[] selectionSort(int[] arr) {
        for(int i=0; i<arr.length; i++) {
            int min = i;
            for(int j=i+1; j<arr.length; j++) {
                min = (arr[min] > arr[j]) ? j : min;
            }

            if(min != i) {
                // swap
                int t = arr[min];
                arr[min] = arr[i];
                arr[i] = t;
            }
        }

        return arr;
    }

    //! insertion sort
    public static int[] insertionSort(int[] arr) {
        for(int i=1; i<arr.length; i++) {
            int p = i-1;
            int c = arr[i];
            while(p>=0 && arr[p]>c) {
                arr[p+1] = arr[p];
                p--;
            }

            arr[p+1] = c;
        }

        return arr;
    }

    //! Quick sort
    public static int partition(int[] arr, int si, int ei) {
        int pivot = arr[ei];
        int i = si-1;
        for(int j=si; j<ei; j++) {
            if(pivot >= arr[j]) {
                int temp = arr[j];
                arr[j] = arr[++i];
                arr[i] = temp;
            }
        }

        int temp = pivot;
        arr[ei] = arr[++i];
        arr[i] = temp;

        return i;
    }

    public static void quickSortUtil(int[] arr, int si, int ei) {
        if(si >= ei) {
            return;
        }

        int pivIdx = partition(arr, si, ei);
        quickSortUtil(arr, pivIdx+1, ei);
        quickSortUtil(arr, si, pivIdx-1);
    }

    public static void quickSort(int[] arr) {
        quickSortUtil(arr, 0, arr.length-1);
    }

    //! Merge sort
    public static void merge(int[] arr, int si, int m, int ei) {
        int[] temp = new int[ei-si+1];
        int k = 0, i = si, j = m+1;
        while(i<=m && j<=ei) {
            if(arr[i] <= arr[j]) {
                temp[k++] = arr[i++]; 
            } else {
                temp[k++] = arr[j++];
            }
        }

        while(i<=m) {
            temp[k++] = arr[i++];
        }

        while(j<=ei) {
            temp[k++] = arr[j++];
        }

        for(i=si, k=0; k<temp.length; i++, k++) {
            arr[i] = temp[k];
        }
    }

    public static void mergeSortUtil(int[] arr, int si, int ei) {
        if(si>=ei) {
            return;
        }

        int mid = (si+ei)/2;
        mergeSortUtil(arr, si, mid);
        mergeSortUtil(arr, mid+1, ei);

        merge(arr, si, mid, ei);
    }

    //! Count sort
    // usually for small ranged arrays
    public static void countSort(int[] arr) {
        // get the maximum value to get the range of count array
        int range = Arrays.stream(arr).max().getAsInt();
        int[] count = new int[range+1];

        // modify count array
        for(int i=0; i<arr.length; i++) {
            count[arr[i]]++;
        }

        // modify the original array with the help of count array
        int idx = 0;
        for(int j=0; j<count.length; j++) {
            for(int i=0; i<count[j]; i++) {
                arr[idx++] = j;
            }
        }
    }

    //^ reversing the array
    public static void reverse(int[] arr) {
        int len = arr.length;
        for(int i=0; i<len/2; i++) {
            int temp = arr[i];
            arr[i] = arr[len - i - 1];
            arr[len - i - 1] = temp;
        }
    }

    //^ kadane's algorithm
    // for maximum subarray sum
    public static int kadanesAlgo(int[] arr) {
        int curr = 0;
        int maxSum = Integer.MIN_VALUE;

        for(int e: arr) {
            // add element to curr
            curr += e;

            // if the sum gets -ve, reset it
            if(curr < 0) curr = 0;

            // check if it is getting maximum or not
            maxSum = Math.max(maxSum, curr);
        }

        return maxSum;
    }

    public static void main(String[] args) {
       
    }
}

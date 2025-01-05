import java.util.*;

public class sorting {
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

    // Quick sort
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

    //Merge sort
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

    public static int[] mergeSort(int[] nums) {
        mergeSortUtil(nums, 0, nums.length-1);
        return nums;
    }
}
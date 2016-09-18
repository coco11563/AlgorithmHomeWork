package Elementary_Sorts;

/**
 * Created by coco1 on 2016/9/18.
 * o(N) = (N-1) + (N-2) + ... + 1 + N = (N)^2 / 2 + N
 * (N)^2/2 times compares and N times exchanges
 * Quadratic times even if input is sorted!
 * This is a STABLE SORT method!
 */
public class SelectionSort {
    public static void slectionSort(Comparable[] a) {
        int N = a.length;
        for (int i  = 0 ; i < N ; i ++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j] , a[min])) {
                    min = j;
                }
            }
            exch(a , i , min);
        }
    }
    public static boolean less(Comparable a, Comparable b) {
        if (a.compareTo(b) > 0) return true;
        else {
            return false;
        }
    }
    public static void exch(Comparable[] a, int i, int min) {
        Comparable swap = a[i];
        a[i] = a[min];
        a[min] = swap;
    }
}

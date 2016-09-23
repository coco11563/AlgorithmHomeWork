package Elementary_Sorts;

/**
 * 从0开始遍历
 *
 * 把每次选择的数和已排序的对比找到位置交换（插入）
 *
 * 直到最后一个数（排序）
 *
 * Created by coco1 on 2016/9/18.
 *
 * Sort a random order seque will use (1/4)*N^2 compares and (1/4)*N^2 times exchanges
 *
 * Insertion Sort do depands on the initial order of the data
 *
 * BEST CASE : IF the array is ascending order, insertion sort make N-1 compares and 0 exchanges
 *
 * WORST CASE : IF the array is descending order, insertion sort make ~1/2*N^2 times compares and ~1/2*N^2 times exchanges
 *
 */
public class InsertionSort {
    public static void insertionSort(Comparable[] a) {
        int N = a.length;
        for (int i = 0 ;i < N; i++) {
            for (int j = i ; j > 0 ; j--) {
                if (less(a[j], a[j-1])) {
                    exch(a, j , j -1);
                } else {
                    break;
                }
            }
        }
    }

    public static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo ;i < hi; i++) {
            for (int j = i ; j > 0 ; j--) {
                if (less(a[j], a[j-1])) {
                    exch(a, j , j -1);
                } else {
                    break;
                }
            }
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

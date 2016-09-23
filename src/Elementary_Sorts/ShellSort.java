package Elementary_Sorts;

/**
 * Created by coco1 on 2016/9/18.
 *
 * 希尔排序(Shell Sort)是插入排序的一种。
 *
 * 也称缩小增量排序，是直接插入排序算法的一种更高效的改进版本。
 *
 * 希尔排序是非稳定排序算法。
 *
 * 该方法因DL．Shell于1959年提出而得名。
 *
 * Insertion Sort is inefficient because elements really move only one position at time even we've know
 *
 * that they have a long way to go
 *
 * In Shell Sort we'll move entries serval positions at a time
 *
 * Instead Insertion Sort, which going one back every time we come with a new item, we go h back.
 *
 * because H-sort J-sort K-sort when we try to 1-sort the array is mostly in order
 *
 * After J-sort , a H-sort array is still sorted by H-sort <---that's why shell sort gain it's efficient!
 *
 * How to select H、J、K:
 *
 * power of 2 : bad
 *
 * power of 2 - 1 : maybe
 *
 * 3x + 1 : easy to compute
 *
 * 1 5 19 41 109 209 505 929 2161 3905 ... ：Nobody know this is the best!
 *
 * O(N) = N^(3/2)  <------Nobody know an accurate model for describing the number of compares taken by shell sort!
 */
public class ShellSort {
    public static void shellSort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h<N/3) {h = 3 * h + 1;}//cacu the h
        while (h >= 1) {
            for (int i = h ; i < N ; i ++) {
                for (int j = i ; j >= h && less(a[j] , a[j - h]) ; j -=h) {
                    exch(a, j , j -h);
                }
            }
            h = h / 3;
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

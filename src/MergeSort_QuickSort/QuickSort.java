package MergeSort_QuickSort;

import Elementary_Sorts.InsertionSort;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by coco1 on 2016/9/26.
 *
 * Quick Sort : A not stable sorting algorithm
 *
 *              partitioning : constant extra space
 *              Depth of recursion : logarithmic extra space(highly probability) <--- can guarantee logarithmic depth by recurring on smaller sub array before large sub array
 *              Insertion sort :　for tiny sub array (< 10 item), we can use Insertion sort to make it faster
 *              Sample : Median of three random item, pick the median one
 *
 * Quick Sort is sort for primitive types
 *
 * Quick Sort is twice(less) faster than Merge Sort
 *
 * BASIC PLAN:
 *
 * *1 Shuffle the array
 *
 * *2 Partition, so that, for some j
 *
 * -entry a[j] is in place
 *
 * -no larger entry to the left of j
 *
 * -no smaller entry to the right of j
 *
 * *3 Sort each piece recursively
 *
 * Best Case : Number of compares is N*logN
 *
 * Worst case : Number of compares is 1/2 * N^2 <----if you shuffle before sort, this may not happened!
 *
 * Average case : C(N) = (N + 1) + (C(0) + C(N-1)) / N + (C(1) + C(N-2)) / N + ... + (C(N-1) + C(0)) / N
 *                          ^                        ^
 *                          |                        |
 *                          |                        |
 *                     partitioning         partitioning probability
 *
 * C(N) = 2 (N + 1)(1/3 + 1/4 + ... + 1/(N+1)) ~ 1.39N*logN
 *
 * 39% more compares than Merge Sort
 *
 * But faster than Merge Sort because of less data movement
 *
 * Partition in-place : Use an extra array makes partition easier (and stable), but not worth the cost
 *
 * Terminal the loop : Testing whether the pointers cross is a bit trickier than it might seen
 *
 * Stay in bounds : The(j == lo) test is redundant (Why??) but (i == hi) test is not.
 *
 * Preserving randomness : Shuffle is needed for performance guarantee
 *
 * Equal keys : When duplicates are present, it is (counter-intuitively) better to stop on keys equal to the partitioning item's key
 *
 */
public class QuickSort {
    private static final int CUTOFF = 10;
    public int partition(Comparable[] array, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (true) {
            while(less(array[++i], array[lo])) { //find object in left which is bigger than array[lo]
                if (i == hi) break;
            }
            while (less(array[lo], array[--j])) { // find object in right which is smaller than array[lo]
                if (j == lo) break;
            }
            if (i >= j) break;
            exch(array, i, j); // switch the bigger left and smaller right
        }
        exch(array, lo, j); // exchange the key and j
        return j; // return the j to show the part
    }
    public void sort(Comparable[] array, int lo, int hi) {
        if (hi <= lo + CUTOFF - 1) {                         // **first improve to make it faster**
            InsertionSort.insertionSort(array, lo, hi + 1);
            return;
        }
        if (lo >= hi) return; // 边际条件

        int m = medianof3(array, lo, lo + (hi + 1) / 2, hi); // **second improve to make it faster**
        exch(array, lo, m);                                  // **second improve to make it faster**

        int j = partition(array, lo, hi); // get the partition key
        sort(array, lo, j - 1); // sort the left by j
        sort(array, j + 1, hi); // sort the right by j
    }

    private int medianof3(Comparable[] array, int lo, int i, int hi) {
        Comparable a = array[lo];
        Comparable b = array[i];
        Comparable c = array[hi];
        return less(a,b)?(less(a,c)?((less(b,c)?i:hi)):lo):(less(c,b)?i:(less(a,c)?lo:hi));
    }

    public void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void main(String args[]) {
        Comparable[] a = {1,2,3,4,5,6,7,8,9,0,11,26,33,89,32,46,21,98,93,70,31};
        QuickSort qs = new QuickSort();
        qs.sort(a);
        for (Comparable s: a
             ) {
            StdOut.println(s.toString());
        }

    }
    /*
   return true ---> a >= b
    */
    private static boolean less(Comparable a, Comparable b) {return a.compareTo(b) > 0;}

    private static void exch(Comparable[] a, int i, int min) {
        Comparable swap = a[i];
        a[i] = a[min];
        a[min] = swap;
    }
}


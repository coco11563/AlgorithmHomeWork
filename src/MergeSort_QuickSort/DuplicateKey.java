package MergeSort_QuickSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by coco1 on 2016/9/26.
 *
 * use 3 parts of quick sort
 *
 *  - 1. entry between lt and gt equal to partition item v
 *
 *  - 2. No larger entry to left of lt
 *
 *  - 3. No smaller entry to right of gt
 *
 *  sorting lower bound - :if n distinct keys and i th one occurs xi times ,any compare-based sorting algorithm must use
 *
 *  at least  linear when only a constant distinct keys
 */
public class DuplicateKey {
    public void QPartition(Comparable[] array, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo ,gt = hi;
        Comparable v = array[lo];
        int i = lo;
        while (i <= gt) {
            int cmp = array[i].compareTo(v);
            if (cmp < 0)  exch(array, lt++, i++);
            else if (cmp > 0)  exch(array, i, gt--);
            else i++;
        }
        QPartition(array, lo, lt - 1);
        QPartition(array, gt + 1, hi);
    }

    public void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        QPartition(a, 0, a.length - 1);
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
    public static void main(String args[]) {
        Comparable[] a = {1,7,5,2,6,3,4,5,6,9,1,7,8,9,0,1,1,2,6,3,3,8,9,3,2,4,6,2,1,9,8,9,3,7,0,3,1};
        DuplicateKey qs = new DuplicateKey();
        qs.sort(a);
        for (Comparable s: a
                ) {
            StdOut.println(s.toString());
        }

    }
}

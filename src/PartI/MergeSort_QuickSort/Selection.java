package MergeSort_QuickSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by coco1 on 2016/9/26.
 *
 * return the top X item
 *
 * Given a array of N items, finds k th largest item
 *
 * ----*----*----*----*----*----*----*----*----*----*----*----*----*----*
 *
 * Quick-Select:
 *
 * -1. entry a[j] is in place
 *
 * -2. no larger entry to the left of j
 *
 * -3. no smaller entry to the right of j
 *
 * -4. repeat in one sub array depending on j, when j equals to X
 *
 * ----*----*----*----*----*----*----*----*----*----*----*----*----*----*
 */
public class Selection {
    public static QuickSort qs = new QuickSort();
    public static Comparable select(Comparable[] a,int k) {
        if (k > a.length - 1) {
            throw new IllegalArgumentException();
        }
        StdRandom.shuffle(a);   // <----- important for performance
        int lo = 0;
        int hi = a.length - 1;
        while (lo < hi) {
            int j = qs.partition(a, lo, hi);
            if (j > k) hi = j - 1; // k在j的左边
            else if (j < k) lo = j + 1;// k在j的右边
            else return a[k];
        }
        return a[k];
    }

    public static void main(String args[]) {
        Comparable[] key = {1,2,3,4,5,6,8,7,5,3,4,6,8};
        Comparable ret = select(key,1);
        StdOut.print(ret);
    }
}

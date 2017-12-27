package Elementary_Sorts;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by coco1 on 2016/9/18.
 */
public class Experiment {
    public static void main(String args[]) {
        Double[] a = new Double[10];
        for (int i = 0 ; i < 10 ; i ++) {
            a[i] = StdRandom.uniform();
        }
        Insertion.sort(a);
        for (int i = 0 ; i < 10 ; i ++) {
            StdOut.println(a[i]);
        }
    }
}

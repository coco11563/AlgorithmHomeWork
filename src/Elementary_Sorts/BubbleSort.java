package Elementary_Sorts;

import MergeSort_QuickSort.Number;

/**
 * Created by coco1 on 2016/9/23.
 */
public class BubbleSort {
    public static void BubbleSort(Comparable[] a) {
        int N = a.length;
        for (int i = N - 1 ; i > 0 ; i --) {
            for (int j = 0 ; j < i ; j ++) {
                if (less(a[j], a[j + 1])) {
                    exch(a, j, j + 1);
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
    public static void main(String args[]) {

        Number[] li = new Number[5];
        li[0] = new Number(1);
        li[1] = new Number(5);
        li[2] = new Number(4);
        li[3] = new Number(9);
        li[4] = new Number(3);
        BubbleSort(li);
        for (Number n : li) {
            System.out.println(n.num);
        }

    }
}

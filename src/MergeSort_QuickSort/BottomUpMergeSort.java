package MergeSort_QuickSort;

/**
 * Created by coco1 on 2016/9/23.
 *
 * Bottom to the top method
 *
 * 自底向上方法（非递归的归并排序）
 *
 * merge first two --> sec two --> first four --> etc
 *
 * two for loop:
 *
 * 1.for sz = 1 --> n ; sz = sz * 2(lgN time)
 *
 * 2. for lo --> N - sz  ; lo = sz + sz
 *
 * 另一个维度上的希尔排序
 *
 */
public class BottomUpMergeSort {
    private static Comparable[] aux;

    private static void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz + sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz)
                Merge.merge(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz + 1, N - 1));
        }
    }

    public static void main(String args[]) {

        Number[] li = new Number[5];
        li[0] = new Number(1);
        li[1] = new Number(5);
        li[2] = new Number(4);
        li[3] = new Number(9);
        li[4] = new Number(3);
        sort(li);
        for (Number n : li) {
            System.out.println(n.num);
        }

    }

}

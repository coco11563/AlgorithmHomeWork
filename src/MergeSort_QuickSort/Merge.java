package MergeSort_QuickSort;

/**
 * Created by coco1 on 2016/9/23.
 *
 * 1.2自顶向下的归并方法
 *
 * is a divide and conquer method
 *
 * divide as two half --> solving the two half  ---> conquer them together
 *
 * the reverse sort is same as in auditory order sort
 *
 * Merge sort uses at most NlgN compares and 6NlgN array accesses to sort any array of size N
 *
 * prof:
 *
 * C(N) <= C(N/2) + C(N/2)    +     N for N > 1 , with C(1) = 0
 *           ^        ^             ^
 *           |        |             |
 *        left half right half    merge
 *           |        |             |
 *           v        v             v
 * A(N) <= A(N/2) + A(N/2)    +    6N for N > 1 , with A(1) = 0
 */
public class Merge {
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        aux = a.clone(); //Copy不是什么好东西
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid + 1, hi);
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) //i > mid即i走到终点
                a[k] = aux[j++]; //剩下的用j处剩下的补全
            else if (j > hi) //j > hi 即j走到终点
                a[k] = aux[i++]; //剩下的用i处剩下的补全
            else if (less(aux[i], aux[j])) //继承了comparable接口的对比数列
                a[k] = aux[j++]; //小的插入
            else
                a[k] = aux[i++]; //小的插入
        }
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if(lo >= hi) return; //边际条件
        int mid = lo + (hi - lo) / 2; //实时计算lo以完成递归
        sort(a, aux, lo, mid); //sort lo to mid
        sort(a, aux, mid + 1, hi); //sort mid to high
        merge(a, aux, lo, mid, hi); //merge sort ---> 一开始走到这步时就是两两merge
    }
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }
    public static boolean isSorted(Comparable[] a, int lo, int hi) {
        while (lo < hi) {
            if (less(a[lo] , a[lo + 1])) {
                return false;
            }
            lo += 1;
        }
        return true;
    }
    /*
    return true ---> a >= b
     */
    public static boolean less(Comparable a, Comparable b) {return a.compareTo(b) > 0;}

    public static void main(String args[]) {

        Number[] li = new Number[5];
        li[0] = new Number(1);
        li[1] = new Number(5);
        li[2] = new Number(4);
        li[3] = new Number(9);
        li[4] = new Number(3);
        sort(li, new Number[5], 0, 4);
        for (Number n : li) {
            System.out.println(n.num);
        }

    }

}

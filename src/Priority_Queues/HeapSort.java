package Priority_Queues;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * Created by coco1 on 2016/10/8.
 *
 * Create max-heap with N all keys
 *
 * Repeatedly return maximum key
 */
public class HeapSort<T extends Comparable<T>> {
    private int N ;
    private T[] stor;

    /**
     * 初始化的构造函数
     *
     * @param a
     */
    public HeapSort(T[] a) {
        stor = a;
        N = a.length;
        for (int i = N/2 ; i >= 1; i --) {
            sink(stor, i, N);
        }
        while (N > 1) {
            exchange(a, 1, N);
            sink(a, 1, --N);
        }
    }
    /**
     * 在堆的初始化时使用的函数
     *
     * @param a 下沉数列
     *
     * @param K 下沉起始点
     *
     * @param N 总数
     */
    private void sink(T[] a, int K, int N) {
        while (2 * K <= N) {
            int j = 2 * K;
            if (j < N && !Bigger(a[j - 1], a[j])) j = j + 1;
            if (Bigger(a[K - 1], a[j - 1])) break;
            exchange(a, K, j);
            K = j;
        }
    }

    private boolean Bigger(T a, T b) {
        if (a.compareTo(b) > 0) return true;
        else {
            return false;
        }
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private void exchange(T[] a, int i, int min) {
        T swap = a[i - 1];
        a[i - 1] = a[min - 1];
        a[min - 1] = swap;
    }
    public static void main(String[] args) {
        Comparable[] a = {1,2,3,4,5,6,7,8,9,0,11,26,33,89,32,46,21,98,93,70,31};
        StdRandom.shuffle(a);
        HeapSort hs = new HeapSort(a);
        for (int i = 0 ; i < a.length ; i ++) {
            StdOut.print(a[i] + ",");
        }
    }
}

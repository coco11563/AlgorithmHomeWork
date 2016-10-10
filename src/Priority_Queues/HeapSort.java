package Priority_Queues;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by coco1 on 2016/10/8.
 *
 * Create max-heap with N all keys
 *
 * Repeatedly return maximum key
 *
 * Proposition. Heap construction uses <= 2N compares and exchanges
 *
 * Proposition. HeapSort uses <= 2NlgN compares and exchanges
 *
 * ***Significance*** In-Place sorting algorithm with NLgN worst-case
 *   - MergeSort : no, Linear extra space.          <=== in-place merge possible, not practical
 *   - QuickSort : no, quadratic time in worst case.<=== N lgN worst-cast quickSort possible, Not Practical!
 *   - HeapSort : yes!
 *
 *    First thing is the inner loop is longer than Quicksorts.
 *    Like Mergesort there is more things to do in the inner loop.
 *    There is that compare are the two children bigger, then compare.
 *    So there are two compares that get done at N lg N times.
 *    And then there is some that array index arithmetic.
 *    The other thing that is probably more significant on modern machines is.
 *    That the references to memory are all over the place when it's a huge array,
 *    so it's not a good algorithm for a situation where there's
 *    caching which is almost everywhere nowadays. It doesn't have a local
 *    reference, like Quicksort does. It's always refers to something that's
 *    nearby something else that I just referred to. So if a big block of things
 *    comes into memory, there's no more extra costs, whereas Heapsort is
 *    going to look far away from the current place as it goes down the tree
 *    and that makes it slower in a lot of situations. And on the other thing
 *    is it's not stable, sometimes people choose to use Mergesort in practice
 *    because of the stability but Heapsort is not stable for the usual reason
 *    that it does long distance exchanges that might bring items that
 *    have equal keys back out of order. So that's our full summary
 *    of sorting algorithms to and
 *    completes our treatment of sorting algorithms with Heapsort.
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
        for (int i = N/2 ; i >= 1; i --) {  //<=2N
            sink(stor, i, N);
        }
        while (N > 1) {                     //<=2NlgN
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

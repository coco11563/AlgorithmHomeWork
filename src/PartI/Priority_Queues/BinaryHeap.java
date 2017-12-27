package Priority_Queues;

/**
 * Created by coco1 on 2016/10/8.
 *
 * Binary Heap in generic tech
 */
public class BinaryHeap<Key extends Comparable<Key>> {
    private Key[] a;
    private int N;
    public BinaryHeap(){
        a = (Key[])new Object[100];
        N = 0;
    }
    private void sink(int K) {
        while (2 * K <= N) {
            int j = 2 * K;
            if (j < N && less(a[j], a[j + 1])) j ++;
            if (!less(a[K], a[j])) break;
            exch(a, K, j);
            K = j;
        }
    }

    /**
     * DelMax : Exch the root with node at END, then sink it down;
     *
     * Cost : At Most 2lgN Compares;
     *
     * @return Max
     */
    public Key DelMax() {
        Key max = a[0];
        exch(a, 0, N--);
        sink(0);
        a[N + 1] = null;
        return max;
    }

    public Key Max() {
        return a[0];
    }

    public void insert(Key in) {
        a[++N] = in;
        swim(N);
    }

    private void swim(int k) {
        while (k > 1 && less(a[k/2], a[k])) {
            exch(a, k, k / 2);
            k = k / 2;
        }
    }

    public static boolean less(Comparable a, Comparable b) {
        if (a.compareTo(b) < 0) return true;
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

    public static void exch(Comparable[] a, int i, int min) {
        Comparable swap = a[i];
        a[i] = a[min];
        a[min] = swap;
    }
}

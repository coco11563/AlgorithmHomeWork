package Priority_Queues;

import java.util.Iterator;

/**
 * Created by coco1 on 2016/10/8.
 *
 * 有很多BUG没有去解决因为这周时间不是很充裕
 *
 * 大致实现了两种简单优先队列
 *
 */
public class SimplePriorityQueue <T extends Comparable<T>> {
    public enum id {
        UNORDER,ORDER;
    }
    private T[] t;
    final String type;
    private int N;
    public SimplePriorityQueue(id id) {
        switch (id) {
            case UNORDER:
                type = "unorder";
                break;
            case ORDER:
                type = "order";
                break;
            default:
                type = "unorder";
                break;
        }
        t = (T[])new Object[10];
        N = 0;
    }
    public boolean isEmpty() {
        return N == 0;
    }
    public T max() {
        T max;
        if (type.compareTo("order") == 0) {
            max = t[N - 1];
        } else {
            max = t[0];
            for (int i = 0 ; i < N ; i ++) {
                if (!less(max, t[i])) max = t[i];
            }
        }
        return max;
    }
    public void insert(T v) {
        if (type.compareTo("order") == 0) {
            int i ;
            T temp;
            T insert = v;
            if (N + 1 > 10) {
                throw new IndexOutOfBoundsException();
            }
            for (i = 0 ; i < N; i ++) {
                if (less(v,t[i])) break;
            }
            for (int j = i ; j < N; j ++) {
                temp = t[j];
                t[j] = insert;
                insert = temp;
            }
            t[++N] = insert;
        } else {
            t[++N] = v;
        }
    }
    public int size() {
        return N;
    }
//    public T delMax() {
//        T max;
//        if (type.compareTo("order") == 0) {
//            max = t[N - 1];
//            N = N - 1;
//        } else {
//            max = t[0];
//            for (int i = 0 ; i < N ; i ++) {
//                if (!less(max, t[i])) max = t[i];
//            }
//        }
//        return max;
//    }
    /**
     * return true if a > b
     * @param a
     * @param b
     * @return
     */
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
}

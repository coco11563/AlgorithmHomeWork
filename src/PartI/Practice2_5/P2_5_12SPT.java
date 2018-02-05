package PartI.Practice2_5;

import java.util.ArrayList;
import java.util.Random;

//调度：编写一段程序SP，从标准输入输出流中读取任务名称和所需要的运行时间，用最短处理时间优先原则打印调度计划，使得任务完成平均时间最小
public class P2_5_12SPT {
    static class queue<T extends Comparable<T>> {
        private T[] a;
        private int N;
        private int end;
        public queue(T[] n) {
            N = n.length * 2;
            a = (T[])new Comparable[N];
            end = 0;
            for (int i = 0 ; i < N ; i++) {
                insert(n[i]);
            }
        }

        public queue() {
            N = 64;
            a = (T[])new Comparable[N];
            end = 0;
        }

        private void swim(int k) {
            while (k > 1 && less(a[k / 2], a[k])) {
                exchange(a, k / 2, k);
                k /= 2;
            }
        }

        private void sink() {
            int k  = 1;
            while (2 * k <= end - 1) {
                int j = 2 * k;
                if (j < end - 1 && less(a[2 * k], a[2 * k + 1])) j ++; //j < n 俩子节点
                if (!less(a[k], a[j])) break;
                exchange(a, k, j);
                k = j;
            }
        }

        public void insert (T a) {
            if (end > N / 2) resize(2 * N);
            this.a[end] = a;
            if (end != 0) {
                swim(end);
            }
            end ++;
        }

        private void resize(int i) {
            this.N = i;
            T[] temp = (T[])new Comparable[i];
            System.arraycopy(a, 0, temp, 0, end - 1);
            a = temp;
        }

        public T popMax() {
            if (end <= N / 4) resize(N / 2);
            exchange(a, 0, end - 1);
            T ret = a[end - 1];
            a[end - 1] = null;
            sink();
            end --;
            return ret;
        }

        private boolean less(T a, T b) {
            return a.compareTo(b) < 0;
        }

        private void exchange(T[] a, int ina, int inb) {
            T temp = a[ina];
            a[ina] = a[inb];
            a[inb] = temp;
        }

        private T getMin(T a, T b) {
            if (less(a ,b)) return a;
            else return b;
        }
    }

    public static void main(String args[]) {
        Random r = new Random();
        queue<Integer> test = new queue<>();
        for (int i = 0 ; i < 100 ; i ++) {
            test.insert(r.nextInt(1000));
        }
        for (int i = 0 ; i < 100 ; i ++) {
            System.out.println(test.popMax());
        }
    }
}

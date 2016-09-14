package Week_Two_Home_Work;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by coco1 on 2016/9/14.
 */
public class RandomizedQueue<T> extends AbstructRandomizedQueue<T> {

    private int count;
    private T[] item;
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void enqueue(T t) {
        if (t == null) {
            throw new NullPointerException("Try to enqueue a null item");
        }

    }
    private int generater() {
        return StdRandom.uniform(count);
    }
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Try to dequeue from a empty queue");
        }
        return null;
    }

    @Override
    public T sample() {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private class iterator implements Iterator<T>{
        private T[] item;
        private int i;
        @Override
        public boolean hasNext() {
            return item[i] != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more element in the Queue!");
            }
            return null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Don't use this function!");
        }
    }
}

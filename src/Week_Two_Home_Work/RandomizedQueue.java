package Week_Two_Home_Work;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by coco1 on 2016/9/14.
 */
public class RandomizedQueue<T> extends AbstructRandomizedQueue<T> {

    private int count;
    private Node front;//insert queue point
    private Node last;//out queue point
    private class Node {
        T t;
        Node next;  //out
        Node prior; //inset

        Node(T item) {
            t = item;
            next = null;
            prior = null;
        }
    }
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
        if (isEmpty()) {//empty
            front = last = new Node(t);
            count ++;
        } else {
            Node old_front = front;
            front.prior = new Node(t);
            front = front.prior;
            front.next = old_front;
            count ++;
        }
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Try to dequeue from a empty queue");
        }
        T t = front.t;
        front = front.next;
        return t;
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
        private Node iter;
        @Override
        public boolean hasNext() {
            return iter != null;
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

package StackQueueBag;

import java.util.Iterator;
import java.util.Objects;

/**
 * Created by coco1 on 2016/9/12.
 */
public class GenericBag<T> extends AbstructBag<T>{
    private Node first;
    private int size;
    private class Node {
        T item;
        Node next;
        public Node() {
        }
        Node(T s) {
            this.item = s;
            this.next = null;
        }
    }
    public GenericBag() {

    }
    public GenericBag(T t) {
        first = new Node(t);
        size ++;
    }

    @Override
    public void add(T t) {
        if(first == null) {
            first = new Node(t);
            size ++;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new BagIterator();
    }

    private class BagIterator implements  Iterator<T> {
        private Node current = first;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T t = current.item;
            current = current.next;
            return t;
        }

        @Override
        public void remove() {

        }
    }
}

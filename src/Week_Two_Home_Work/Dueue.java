package Week_Two_Home_Work;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by coco1 on 2016/9/14.
 */
public class Dueue<T> extends AbsrtuctDueue<T> implements Iterable<T>{
    private Node first;
    private Node last;
    private int count;
    private class Node {
        T t;
        Node next;
        Node prior;
        Node(T t1) {
            t = t1;
            next = null;
            prior = null;
        }
        Node(Node n) {
            this.t = n.t;
            this.next = n.next;
            this.prior = n.prior;
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
    public void addFirst(T item) {
        if (item == null) {
            throw new NullPointerException("Add first adding a Null Object!");
        }
        if (isEmpty()) {//empty
            first = last = new Node(item);
            count ++;
        } else {
            Node old_first = first;
            first.next = new Node(item);
            first = first.next;
            first.prior = old_first;
            count ++;
        }
    }

    @Override
    public void addLast(T item) {
        if (item == null) {
            throw new NullPointerException("Add last adding a Null Object!");
        }
        if (isEmpty()) {
            first = last = new Node(item);
            count ++;
        } else {
            Node old_last = last;
            last.prior = new Node(item);
            last = last.prior;
            last.next = old_last;
            count ++;
        }
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Remove First is trying to remove a null Object!");
        }
        T t = first.t;
        first = first.prior;
        first.next = null;
        count --;
        return t;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Remove Last is trying to remove a null Object!");
        }
        T t = last.t;
        last = last.next;
        last.prior = null;
        count --;
        return t;
    }
    private class iterator implements Iterator{
        private Node iter = first;
        @Override
        public boolean hasNext() {
            return iter != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more element!");
            }
            T s = iter.t;
            iter = iter.prior;
            return s;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Don't Use this method!");
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new iterator();
    }

    public static void main(String[] args) {
        Dueue<Integer> i = new Dueue<>();
        i.addFirst(1);
        i.addFirst(1);
        i.addFirst(3);
        i.addFirst(7);
        i.addLast(8);
        i.addLast(8);
        i.addLast(6);
        i.addLast(4);
        Iterator<Integer> iterator = i.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

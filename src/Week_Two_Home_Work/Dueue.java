package Week_Two_Home_Work;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by coco1 on 2016/9/14.
 */
public class Dueue<T> extends AbsrtuctDueue {
    Node first;
    Node last;
    int count;
    private class Node {
        T t;
        Node next;
        Node(T t1) {
            t = t1;
            next = null;
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
    public void addFirst(Object o) {
        if (o == null) {
            throw new NullPointerException("Add first adding a Null Object!");
        }
    }

    @Override
    public void addLast(Object o) {
        if (o == null) {
            throw new NullPointerException("Add last adding a Null Object!");
        }
    }

    @Override
    public Object removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Remove First is trying to remove a null Object!");
        }
        return null;
    }

    @Override
    public Object removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Remove Last is trying to remove a null Object!");
        }
        return null;
    }
    public class iterator implements Iterator{

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more element!");
            }
            return null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Don't Use this method!");
        }
    }
    @Override
    public Iterator iterator() {
        return null;
    }
}

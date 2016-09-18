package Week_Two_Home_Work.submit;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by coco1 on 2016/9/14.
 */
public class Deque<Item> implements Iterable<Item>{
    private Node first;
    private Node last;
    private int count;
    private class Node {
        Item t;
        Node next;
        Node prior;
        Node(Item t1) {
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
    public boolean isEmpty() {
        return count == 0;
    }
    public int size() {
        return count;
    }
    public void addFirst(Item item) {
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
    public void addLast(Item item) {
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
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Remove First is trying to remove a null Object!");
        }
        Item t = first.t;
        first = first.prior;
        count --;
        return t;
    }
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Remove Last is trying to remove a null Object!");
        }
        Item t = last.t;
        last = last.next;
        count --;
        return t;
    }
    private class iterator implements Iterator<Item>{
        private Node iter = first;
        @Override
        public boolean hasNext() {
            return iter != null;
        }
        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more element!");
            }
            Item s = iter.t;
            iter = iter.prior;
            return s;
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Don't Use this method!");
        }
    }
    
    public Iterator<Item> iterator() {
        return new iterator();
    }
    public static void main(String args[]) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
//        deque.addFirst(2);
//        deque.addLast(3);
//        deque.addLast(4);
//        deque.addFirst(5);
        StdOut.println(deque.removeLast());
        StdOut.println(deque.size());
    }
}

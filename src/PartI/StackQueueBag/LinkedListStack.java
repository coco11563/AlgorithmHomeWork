package StackQueueBag;

/**
 * Created by coco1 on 2016/9/11.
 */

import java.util.Iterator;

/**
 * this class is iterable
 *
 * all operation takes constant time in the worst case
 *
 * 16 bytes(object head)
 *
 * 8 bytes(inner class extra overhead)
 *
 * 8 bytes(reference to String)
 *
 * 8 bytes(reference to Node)
 *
 * 40 bytes per stack node  -------->40N
 *
 * every operations take constant time -------> guarantee
 */
public class LinkedListStack extends AbstructStack implements Iterable<String>{
    private Node first = null;
    private int count = 0;
    private class Node {
        String item;
        Node next;
        public Node() {
        }
        public Node(String s) {
            this.item = s;
            this.next = null;
        }
    }
    public int getCount() {
        return count;
    }
    @Override
    public boolean isEmpty() {
        return first == null;
    }
    @Override
    public String pop() {
        String item = first.item;
        first = first.next;
        count--;
        return item;
    }
    @Override
    public void push(String item) {
        Node n = new Node(item);
        n.next = first;
        first = n;
        count ++;
    }
    @Override
    public Iterator<String> iterator() {
        return new ListIterator();
    }
    private class ListIterator implements Iterator<String> {
        private Node current = first;
        @Override
        public boolean hasNext() {
            return current != null;
        }
        @Override
        public String next() {
            String s = current.item;
            current = current.next;
            return s;
        }
        @Override
        public void remove() {
            System.out.print("You shouldn't do this!");
        }
    }

}

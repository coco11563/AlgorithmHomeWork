package StackQueueBag;

/**
 * Created by coco1 on 2016/9/12.
 */
public class LinkedListQueue extends AbstructQueue {
    private Node end;
    private Node first;
    private int count;
    private class Node {
        Node(String s) {
            this.item = s;
        }
        String item;
        Node next;
    }
     public LinkedListQueue() {
        end = first = null;
        count = 0;
    }
    @Override
    void enqueue(String item) {
        Node in = new Node(item);
        if (!this.isEmpty()) {
            end.next = in;
            end = end.next;
            count += 1;
        } else {
            first = in;
            end = in;
            count ++;
        }
    }
    @Override
    String dequeue() {
        String item = first.item;
        first = first.next;
        if (isEmpty()) end = null;
        count -= 1;
        return item;
    }

    @Override
    boolean isEmpty() {
        return count == 0;
    }

    @Override
    int size() {
        return count;
    }
    public static void main(String args[]) {
        LinkedListQueue q = new LinkedListQueue();
        q.enqueue("s");
        q.enqueue("b");
        q.enqueue("j");
        q.enqueue("s");
        q.enqueue("j");
        while (!q.isEmpty()) {
            System.out.println(q.size());
            System.out.println(q.dequeue());
        }
    }
}

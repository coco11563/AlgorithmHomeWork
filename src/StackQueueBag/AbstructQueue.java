package StackQueueBag;

/**
 * Created by coco1 on 2016/9/12.
 */
public abstract class AbstructQueue {
    abstract void enqueue(String item);
    abstract String dequeue();
    abstract boolean isEmpty();
    abstract int size();
}

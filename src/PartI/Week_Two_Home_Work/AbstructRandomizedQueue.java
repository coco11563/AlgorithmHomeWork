package Week_Two_Home_Work;

import java.util.Iterator;

/**
 * Created by coco1 on 2016/9/12.
 * Corner cases. Throw a java.lang.NullPointerException if the client attempts to add a null item;
 * throw a java.util.NoSuchElementException if the client attempts to remove an item from an empty deque;
 * throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator;
 * throw a java.util.NoSuchElementException if the client calls the next() method in the iterator and there are no more items to return.
 */
public abstract class AbstructRandomizedQueue <Item> implements Iterable<Item> {
    public abstract boolean isEmpty();                 // is the queue empty?
    public abstract int size();                        // return the number of items on the queue
    public abstract void enqueue(Item item);           // add the item
    public abstract Item dequeue();                    // remove and return a random item
    public abstract Item sample();                     // return (but do not remove) a random item
    public abstract Iterator<Item> iterator();         // return an independent iterator over items in random order
}


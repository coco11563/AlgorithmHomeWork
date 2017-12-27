package StackQueueBag;

import java.util.Iterator;

/**
 * Created by coco1 on 2016/9/12.
 */
public abstract class AbstructBag<T> implements Iterable<T> {
    public abstract void add(T t);
    public abstract int size();
    public abstract Iterator<T> iterator();
}

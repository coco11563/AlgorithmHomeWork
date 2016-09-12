package Week_Two_Home_Work;

import java.util.Iterator;

/**
 * Created by coco1 on 2016/9/12.
 */
public abstract class AbsrtuctDueue<Item> implements Iterable<Item> {
        public abstract boolean isEmpty();               // is the deque empty?
        public abstract int size();                        // return the number of items on the deque
        public abstract void addFirst(Item item);          // add the item to the front
        public abstract void addLast(Item item);           // add the item to the end
        public abstract Item removeFirst();                // remove and return the item from the front
        public abstract Item removeLast();                 // remove and return the item from the end
        public abstract Iterator<Item> iterator();        // return an iterator over items in order from front to end
}

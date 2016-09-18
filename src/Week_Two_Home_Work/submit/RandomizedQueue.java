package Week_Two_Home_Work.submit;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import java.util.*;

/**
 * Created by coco1 on 2016/9/14.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private ArrayList<Item> item;
    public RandomizedQueue() {
        item = new ArrayList<Item>();
    }
    public boolean isEmpty() {
        return item.size() == 0;
    }
    public int size() {
        return item.size();
    }
    public void enqueue(Item t) {
        if (t == null) {
            throw new NullPointerException("Try to enqueue a null item");
        }
        item.add(t);
    }
    private int generater() {
        return StdRandom.uniform(item.size());
    }
    private int[] generaterArray() {
        int[] array = new int[item.size()];
        Set<Integer> set = new HashSet<>();
        int temp ;
        for (int i = 0 ; i < item.size() ; i ++){
            do {
                temp = generater();
            }
            while (set.contains(temp));
            array[i] = temp;
            set.add(temp);
            }
        return array;
    }
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Try to dequeue from a empty queue");
        }
        int number = generater();
        Item ret = item.get(number);
        item.remove(number);
        return ret;
    }
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Try to sample from a empty queue");
        }
        int poisition = generater();
        return item.get(poisition);
    }
    public Iterator<Item> iterator() {
        return new iterator();
    }
    private class iterator implements Iterator<Item>{
        private ArrayList<Item> items;
        private int[] randomArray;
        private int i;
        public iterator() {
            this.items = item;
            i = 0;
            randomArray = generaterArray();
        }
        @Override
        public boolean hasNext() {
            return i < randomArray.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more element in the Queue!");
            }
            return items.get(randomArray[i++]);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Don't use this function!");
        }
    }
}

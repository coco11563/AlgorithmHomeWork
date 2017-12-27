package Week_Two_Home_Work;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

import java.util.*;

/**
 * Created by coco1 on 2016/9/14.
 */
public class RandomizedQueue<T> extends AbstructRandomizedQueue<T> implements Iterable<T>{

    private int count;
    private T[] item;
    public RandomizedQueue() {
        count = 0;
        item = (T[])(new Object[1]);
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
    public void enqueue(T t) {
        if (t == null) {
            throw new NullPointerException("Try to enqueue a null item");
        }
        if(isEmpty()) {
            item[count] = t;
            count ++;
        } else if(count == item.length) {
                resize(item.length);
                item[count] = t;
                count ++;
            } else {
                this.item[count] = t;
                count ++;
            }
    }
    private int generater() {
        return StdRandom.uniform(count);
    }
    private int[] generaterArray() {
        int[] array = new int[count];
        Set<Integer> set = new HashSet<>();
        int temp ;
        for (int i = 0 ; i < count ; i ++){
            do {
                temp = generater();
            }
            while (set.contains(temp));
            array[i] = temp;
            set.add(temp);
            }
        return array;
    }
    public static void main(String args[]) {
        RandomizedQueue<Integer> as = new RandomizedQueue<>();
        as.enqueue(1);
        as.enqueue(2);
        as.enqueue(3);
        as.enqueue(4);
        as.enqueue(5);
        int[] array  = as.generaterArray();
        System.out.println(array[0] +"" +  array[1] + array[2] + array[3] + array[4]);
        Iterator<Integer> iterator = as.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Try to dequeue from a empty queue");
        }
        int number = generater();
        T ret = item[number];
        T[] new_item = (T[]) (new Object[count - 1]);
        for (int i = 0, j= 0 ; i < count ; i ++) {
            if (i == number) {
                continue;
            }
            new_item[j] = item[i];
            j ++;
        }
        count --;
        item = new_item;
        return ret;
    }
    @Override
    public T sample() {
        int poisition = StdRandom.uniform(count);
        return item[poisition];
    }

    @Override
    public Iterator<T> iterator() {
        return new iterator();
    }

    public void resize(int addlength) {
        T[] copy = (T[])(new Object[count + addlength]);
        System.arraycopy(item, 0, copy, 0,
                Math.min(count, count + addlength));
        item = copy;
    }

    private class iterator implements Iterator<T>{
        private T[] items;
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
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more element in the Queue!");
            }
            return item[randomArray[i++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Don't use this function!");
        }
    }
}

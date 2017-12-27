package StackQueueBag;

/**
 * Created by coco1 on 2016/9/11.
 */
//you have to declare the length

/**
 * will always stay in 25% full or 100% full
 *
 * 8n-32n memory used depand on how full this array is
 *
 * amount all faster than linkedlist stack(amortize time)--------->maybe loss packet!
 */
public class ArrayListStack extends AbstructStack{
    private String[] item;
    private int first;
    private int count;
    public ArrayListStack() {
        item = new String[1];
        first = 0;
        count = 1;
    }


    @Override
    public boolean isEmpty() {
        return first == 0;
    }

    @Override
    public String pop() {
        String s = item[--first];
        item[first] = null; //gc will collection it
        if (first > 0 && first == count/4) resize(-count/2);
        return s; //maybe Underflow
    }
    //when a array is full, create a new array of twice the size, and copy item
    @Override
    public void push(String s) {
        if(first == count) { //cost one to every operation ~ 3N (amortize analysis)
            resize(count);
        }
        item[first++] = s; //maybe Overflow
    }

    /**
     * 数组扩容或者减容
     */
    public void resize(int addlength) {
        String[] copy = new String[count + addlength];
        System.arraycopy(item, 0, copy, 0,
                Math.min(count, count + addlength));
        count = count + addlength;
        item = copy;
    }
}

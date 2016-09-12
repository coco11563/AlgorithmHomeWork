package StackQueueBag;

/**
 * Created by coco1 on 2016/9/12.
 */
public class ArrayQueue extends AbstructQueue{
    private String [] item;
    private int front;
    private int end;
    private int count;
    private int size;
    public ArrayQueue() {
        front = end = -1;
        item = new String[1];
        count = 0;
        size = 1;
    }
    @Override

    void enqueue(String item) {
        if(isEmpty()) {
            end = 0;
            front = 0;
            this.item[end] = item;
        } else {
            if (front != 0) {
                end = 0;
                this.item[end] = item;
            } else if(count == size) {
                resize(size);
                end = end + 1;
                this.item[end] = item;
            } else {
                end = end + 1;
                this.item[end] = item;
            }


        }
        count ++;
    }
    @Override
    String dequeue() {
        String s = item[front];
        item[front] = null; //gc will collect it
        if (front == item.length - 1) {
            front = 0;
        } else {
            front += 1;
        }
        count --;
        return s;
    }

    @Override
    boolean isEmpty() {
        return count == 0;
    }

    @Override
    int size() {
        return size;
    }
    public void resize(int addlength) {
        String[] copy = new String[size + addlength];
        System.arraycopy(item, 0, copy, 0,
                Math.min(size, size + addlength));
        size = size + addlength;
        item = copy;
    }
}

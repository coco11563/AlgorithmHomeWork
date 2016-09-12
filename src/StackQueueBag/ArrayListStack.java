package StackQueueBag;

/**
 * Created by coco1 on 2016/9/11.
 */
//you have to declare the length
public class ArrayListStack extends AbstructStack{
    private String[] item;
    private int first;
    private int count;
    public ArrayListStack(int n) {
        item = new String[n];
        first = 0;
        count = n;
    }


    @Override
    public boolean isEmpty() {
        return first == 0;
    }

    @Override
    public String pop() {
        String s = item[--first];
        item[first] = null; //gc will collection it
        return s; //maybe Underflow
//        return item[--first];
    }

    @Override
    public void push(String s) {
//        item[++first] = s;
        item[first++] = s; //maybe Overflow
    }

    /**
     * 数组扩容或者减容
     */
    public void FixedCapacityStack(int addlength) {
        String[] copy = new String[count + addlength];
        System.arraycopy(item, 0, copy, 0,
                Math.min(count, count + addlength));
        count = count + addlength;
        item = copy;
    }
    public static void main(String args[]) {
        ArrayListStack arrayListStack = new ArrayListStack(10);
        arrayListStack.push("i");
        arrayListStack.push("j");
        arrayListStack.push("k");
        arrayListStack.push("l");
        System.out.print(arrayListStack.pop());
        System.out.print(arrayListStack.pop());
        System.out.print(arrayListStack.pop());
        System.out.print(arrayListStack.pop());
        System.out.print(arrayListStack.isEmpty());
    }
}

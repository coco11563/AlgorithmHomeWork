package MergeSort_QuickSort;

import java.util.Iterator;

/**
 * Created by coco1 on 2016/9/23.
 */
public class Number implements Comparable<Number>{
    int num ;
    public Number (int n) {
        this.num = n;
    }
    public Number () {
        this.num = 0;
    }
    @Override
    public int compareTo(Number that) {
        if (this.num < that.num) return -1;
        if (this.num > that.num) return +1;
        return 0;
    }


}

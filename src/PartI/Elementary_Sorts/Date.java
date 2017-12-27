package Elementary_Sorts;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by coco1 on 2016/9/18.
 */
public class Date implements Comparable<Date> {
    private final int month, day, year;

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     *
     * @param that
     * @return 1 young
     * @return -1 old
     * @return 0 same
     *
     */
    @Override
    public int compareTo(Date that) {
        if (this.year < that.year) return -1;
        if (this.year > that.year) return +1;
        if (this.month < that.month) return -1;
        if (this.month > that.month) return +1;
        if (this.day < that.day) return -1;
        if (this.day > that.day) return +1;
        return 0;
    }
    public static boolean less(Date a,Date b) {
        return a.compareTo(b) > 0;
    }

    public static void main(String args[]) {
        Date a = new Date(1,15,1995);
        Date b = new Date(4,19,1994);
        StdOut.print(a.compareTo(b));
        StdOut.print(less(a,b));
    }
}

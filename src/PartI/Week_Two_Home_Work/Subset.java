package Week_Two_Home_Work;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by coco1 on 2016/9/14.
 */
public class Subset {
    public static void main(String[] args) {
        int k = Integer.valueOf(args[0]);

        Week_Two_Home_Work.submit.RandomizedQueue<String> rq = new Week_Two_Home_Work.submit.RandomizedQueue<String>();
        String str = StdIn.readString();

        rq.enqueue(str);

        while (!StdIn.isEmpty()) {
            str = StdIn.readString();
            rq.enqueue(str);
        }

        for (int i = 0; i < k; i++) {
            System.out.println(rq.dequeue());
        }

    }
}

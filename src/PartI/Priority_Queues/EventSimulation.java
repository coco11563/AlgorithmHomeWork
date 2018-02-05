package PartI.Priority_Queues;

import edu.princeton.cs.algs4.StdDraw;

/**
 * Created by coco1 on 2016/11/2.
 */
public class EventSimulation {
    public static void main(String argsp[]) {
        int N = 50;
        Ball[] balls = new Ball[N];
        for (int i = 0 ; i < N ; i ++) {
            balls[i] = new Ball();
        }
        while(true) {
            StdDraw.clear();
            for (int i = 0 ; i < N ; i ++) {
                balls[i].move(0.5);
                balls[i].draw();
            }
            StdDraw.show();
        }
    }
}

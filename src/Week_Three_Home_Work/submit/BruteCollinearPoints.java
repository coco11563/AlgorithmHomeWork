package Week_Three_Home_Work.submit;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by coco1 on 2016/9/23.
 */
public class BruteCollinearPoints {
    private int count;
    private ArrayList<LineSegment> segments = new ArrayList<>();
    private ArrayList<Integer> map = new ArrayList<>();
    public BruteCollinearPoints(Point[] copy) { // finds all line segments containing 4 points
        if (copy == null)
            throw new NullPointerException();
        Point[] points = new Point[copy.length];
        for (int i = 0; i < copy.length; i++) {
            points[i] = copy[i];
        }
        Arrays.sort(points);
        for (int i = 0; i < copy.length - 1; i++) {
            if (copy[i].compareTo(copy[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
        if (points == null) throw new NullPointerException();
        for (int i = 0 ; i < points.length ; i ++) {

            for (int j = i + 1; j < points.length ; j ++) {
                double slope_1 = points[i].slopeTo(points[j]);
                for (int k = j + 1; k < points.length ; k ++) {
                    double slope_2 = points[j].slopeTo(points[k]);
                    if (slope_1 != slope_2) {
                        continue;
                    }
                    int temp = 0;
                    int hash = 0;
                    for (int l = k + 1; l < points.length ; l++) {
                        double slope_3 = points[k].slopeTo(points[l]);
                            if (slope_2 == slope_3) { //这部分ok
                                temp = l;
                                hash = i * 10 + l * 100;
                                if (!map.contains(hash)) {
                                    segments.add(new LineSegment(points[i], points[temp]));
                                    map.add(hash);
                                }
                            } else {
                                continue;
                            }

                    }
                }
            }
        }
        count = segments.size();
    }
    public int numberOfSegments() { // the number of line segments
        return count;
    }
    public LineSegment[] segments() { // the line segments
        LineSegment[] ret = new LineSegment[segments.size()];
        int index = 0;
        for (LineSegment s : segments
             ) {
            ret[index] = s;
            index ++;
        }
        return ret;
    }
    private Point max (Point a , Point b , Point c, Point d) {
        Point maxinab = a.compareTo(b) == 1 ? a : b ;
        Point maxincd = c.compareTo(d) == 1 ? c : d ;
        return maxinab.compareTo(maxincd) == 1 ? maxinab : maxincd;
    }
    private Point min (Point a , Point b , Point c, Point d) {
        Point mininab = a.compareTo(b) == -1 ? a : b ;
        Point minincd = c.compareTo(d) == -1 ? c : d ;
        return mininab.compareTo(minincd) == -1 ? mininab : minincd;
    }
    public static void main(String args[]) {
    }
}

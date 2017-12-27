package Week_Five_Home_Work;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class PointSET {
    private final TreeSet<Point2D> point2DTreeSet;
    public PointSET() {// construct an empty set of points
        point2DTreeSet = new TreeSet<>();
    }
    public boolean isEmpty() {                   // is the set empty?
        return size() == 0;
    }
    public int size() {                        // number of points in the set
        return point2DTreeSet.size();
    }
    public void insert(Point2D p) {             // add the point to the set (if it is not already in the set)
        if (p == null) throw new IllegalArgumentException();
        point2DTreeSet.add(p);
    }
    public boolean contains(Point2D p) {          // does the set contain point p?
        if (p == null) throw new IllegalArgumentException();
        return point2DTreeSet.contains(p);
    }

    public void draw() {                     // draw all points to standard draw
        for (Point2D p : point2DTreeSet) {
            p.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) { // all points that are inside the rectangle (or on the boundary)
        if (rect == null) throw new IllegalArgumentException();
        List<Point2D> lip = new LinkedList<>();
        for (Point2D p : point2DTreeSet) {
            if (rect.contains(p)) lip.add(p);
        }
        return lip;
    }

    public Point2D nearest(Point2D p) {// a nearest neighbor in the set to point p; null if the set is empty
        if (p == null) throw new IllegalArgumentException();
        double dis = Double.MAX_VALUE;
        Point2D ret = null;
        for (Point2D pt : point2DTreeSet) {
            double dist = pt.distanceTo(p);
            if (dist < dis) {
                ret = pt;
                dis = dist;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
    }
}
